using System;
using System.Collections.Generic;
using ShuffleSort;
using static ShuffleSort.ShuffleSortGlobal;

class CSharpExample
{
    static void Main()
    {
        Console.WriteLine("=== C# Shuffle & Sort Demo ===\n");
        
        // Create a simple random generator
        var rng = new SimpleRandom(42);
        
        // Test with integers
        var numbers = new List<int> { 5, 2, 8, 1, 9, 3, 7, 4, 6, 10 };
        Console.WriteLine("Original numbers: " + string.Join(", ", numbers));
        
        // Shuffle the numbers
        var shuffled = Shuffle(numbers, rng);
        Console.WriteLine("Shuffled numbers: " + string.Join(", ", shuffled));
        
        // Sort them back
        var sorted = BubbleSort(shuffled, CompareInts);
        Console.WriteLine("Sorted numbers:   " + string.Join(", ", sorted));
        
        // Test with custom objects
        var people = new List<Person>
        {
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35),
            new Person("Diana", 28)
        };
        
        Console.WriteLine("\nOriginal people:");
        foreach (var p in people)
        {
            Console.WriteLine($"  {p}");
        }
        
        // Shuffle people
        var rng2 = new SimpleRandom(123);
        var shuffledPeople = Shuffle(people, rng2);
        Console.WriteLine("\nShuffled people:");
        foreach (var p in shuffledPeople)
        {
            Console.WriteLine($"  {p}");
        }
        
        // Sort by age
        var sortedByAge = BubbleSort(shuffledPeople, ComparePersonsByAge);
        Console.WriteLine("\nSorted by age:");
        foreach (var p in sortedByAge)
        {
            Console.WriteLine($"  {p}");
        }
    }
}