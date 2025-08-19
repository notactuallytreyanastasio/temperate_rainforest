# Shuffle and Sort Algorithms

A library implementing Fisher-Yates shuffle and bubble sort algorithms for cross-language use.

## Random Number Generation

For shuffling, we need a random number generator interface that can be implemented differently on each backend.

    export interface RandomGenerator {
      // Generate a random integer between 0 (inclusive) and max (exclusive)
      nextInt(max: Int): Int | Bubble;
    }

## Fisher-Yates Shuffle

The Fisher-Yates shuffle (also known as Knuth shuffle) is an algorithm for generating a random permutation of a finite sequence.

    export let shuffle<T>(items: List<T>, rng: RandomGenerator): List<T> | Bubble {
      // Create a mutable copy to shuffle
      let builder = new ListBuilder<T>();
      builder.addAll(items);
      
      // Fisher-Yates shuffle: iterate from the end backwards
      var i = builder.length - 1;
      while (i > 0) {
        // Pick a random index from 0 to i
        let j = rng.nextInt(i + 1);
        
        // Swap elements at positions i and j
        let temp = builder[i];
        builder[i] = builder[j];
        builder[j] = temp;
        
        i -= 1;
      }
      
      builder.toList()
    }

## Bubble Sort

Bubble sort is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements and swaps them if they're in the wrong order.

    export let bubbleSort<T>(
      items: List<T>, 
      compare: fn(T, T): Int
    ): List<T> {
      // Create a mutable copy to sort
      let builder = new ListBuilder<T>();
      builder.addAll(items);
      
      let n = builder.length;
      
      // Bubble sort algorithm
      for (var i = 0; i < n - 1; i += 1) {
        var swapped = false;
        
        for (var j = 0; j < n - i - 1; j += 1) {
          // Compare adjacent elements
          if (compare(builder[j], builder[j + 1]) > 0) {
            // Swap if they're in wrong order
            let temp = builder[j];
            builder[j] = builder[j + 1];
            builder[j + 1] = temp;
            swapped = true;
          }
        }
        
        // If no swaps were made, the list is sorted
        if (!swapped) {
          break;
        }
      }
      
      builder.toList()
    }

## Comparison Functions

Common comparison functions for sorting.

    export let compareInts(a: Int, b: Int): Int {
      if (a < b) { 
        -1 
      } else if (a > b) { 
        1 
      } else { 
        0 
      }
    }
    
    export let compareFloats(a: Float64, b: Float64): Int {
      if (a < b) { 
        -1 
      } else if (a > b) { 
        1 
      } else { 
        0 
      }
    }
    
    export let compareStrings(a: String, b: String): Int {
      if (a < b) { 
        -1 
      } else if (a > b) { 
        1 
      } else { 
        0 
      }
    }

## Custom List Types for Testing

Let's create some custom types to test our algorithms with.

    export class Person(
      public name: String,
      public age: Int,
    ) {
      public toString(): String {
        "${name} (age ${age})"
      }
    }
    
    export let comparePersonsByAge(a: Person, b: Person): Int {
      compareInts(a.age, b.age)
    }
    
    export let comparePersonsByName(a: Person, b: Person): Int {
      compareStrings(a.name, b.name)
    }

## Simple Random Generator

A simple random generator for testing. Note: This is NOT cryptographically secure!

    export class SimpleRandom(
      public var seed: Int,
    ) extends RandomGenerator {
      public nextInt(max: Int): Int | Bubble {
        // Linear congruential generator (simple PRNG)
        // Using bitwise AND to avoid overflow issues
        seed = ((seed * 1103515245 + 12345) & 2147483647);
        // Ensure positive result - using abs for simplicity
        let absValue = if (seed < 0) { -seed } else { seed };
        absValue % max
      }
    }

## Unit Tests

### Testing Fisher-Yates Shuffle

    test("shuffle preserves all elements") {
      let original = [1, 2, 3, 4, 5];
      let rng = new SimpleRandom(42);
      let shuffled = shuffle(original, rng);
      
      // Check same length
      assert(shuffled.length == original.length);
      
      // Check all elements are present (by sorting both)
      let sortedOriginal = bubbleSort(original, compareInts);
      let sortedShuffled = bubbleSort(shuffled, compareInts);
      
      for (var i = 0; i < sortedOriginal.length; i += 1) {
        assert(sortedOriginal[i] == sortedShuffled[i]) {
          "Element ${sortedOriginal[i]} missing after shuffle"
        }
      }
    }

    test("shuffle actually shuffles") {
      let original = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
      let rng = new SimpleRandom(123);
      let shuffled = shuffle(original, rng);
      
      // Count how many elements are in different positions
      var differentPositions = 0;
      for (var i = 0; i < original.length; i += 1) {
        if (original[i] != shuffled[i]) {
          differentPositions += 1;
        }
      }
      
      // At least some elements should be in different positions
      assert(differentPositions > 0) {
        "Shuffle didn't change any positions"
      }
    }

### Testing Bubble Sort

    test("bubble sort sorts integers") {
      let unsorted = [5, 2, 8, 1, 9, 3];
      let sorted = bubbleSort(unsorted, compareInts);
      
      assert(sorted.length == 6);
      assert(sorted[0] == 1);
      assert(sorted[1] == 2);
      assert(sorted[2] == 3);
      assert(sorted[3] == 5);
      assert(sorted[4] == 8);
      assert(sorted[5] == 9);
    }
    
    test("bubble sort sorts strings") {
      let unsorted = ["zebra", "apple", "mango", "banana"];
      let sorted = bubbleSort(unsorted, compareStrings);
      
      assert(sorted[0] == "apple");
      assert(sorted[1] == "banana");
      assert(sorted[2] == "mango");
      assert(sorted[3] == "zebra");
    }
    
    test("bubble sort sorts custom objects") {
      let people = [
        new Person("Alice", 30),
        new Person("Bob", 25),
        new Person("Charlie", 35),
        new Person("Diana", 28),
      ];
      
      let sortedByAge = bubbleSort(people, comparePersonsByAge);
      assert(sortedByAge[0].name == "Bob");
      assert(sortedByAge[1].name == "Diana");
      assert(sortedByAge[2].name == "Alice");
      assert(sortedByAge[3].name == "Charlie");
      
      let sortedByName = bubbleSort(people, comparePersonsByName);
      assert(sortedByName[0].name == "Alice");
      assert(sortedByName[1].name == "Bob");
      assert(sortedByName[2].name == "Charlie");
      assert(sortedByName[3].name == "Diana");
    }

### Integration Test: Shuffle then Sort

    test("shuffle then sort returns to original order") {
      let original = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
      let rng = new SimpleRandom(999);
      
      // Shuffle the list
      let shuffled = shuffle(original, rng);
      
      // Sort it back
      let sorted = bubbleSort(shuffled, compareInts);
      
      // Should match original
      for (var i = 0; i < original.length; i += 1) {
        assert(sorted[i] == original[i]) {
          "Position ${i}: expected ${original[i]}, got ${sorted[i]}"
        }
      }
    }
