// JavaScript example of using the shuffle-sort library
import { shuffle, bubbleSort, compareInts, SimpleRandom, Person, comparePersonsByAge } from '../../../temper.out/js/shuffle-sort/index.js';

console.log("=== JavaScript Shuffle & Sort Demo ===\n");

// Create a simple random generator
const rng = new SimpleRandom(42);

// Test with integers
const numbers = [5, 2, 8, 1, 9, 3, 7, 4, 6, 10];
console.log("Original numbers:", numbers);

// Shuffle the numbers
const shuffled = shuffle(numbers, rng);
console.log("Shuffled numbers:", shuffled);

// Sort them back
const sorted = bubbleSort(shuffled, compareInts);
console.log("Sorted numbers:  ", sorted);

// Test with custom objects
const people = [
    new Person("Alice", 30),
    new Person("Bob", 25),
    new Person("Charlie", 35),
    new Person("Diana", 28),
];

console.log("\nOriginal people:");
people.forEach(p => console.log(`  ${p.toString()}`));

// Shuffle people
const rng2 = new SimpleRandom(123);
const shuffledPeople = shuffle(people, rng2);
console.log("\nShuffled people:");
shuffledPeople.forEach(p => console.log(`  ${p.toString()}`));

// Sort by age
const sortedByAge = bubbleSort(shuffledPeople, comparePersonsByAge);
console.log("\nSorted by age:");
sortedByAge.forEach(p => console.log(`  ${p.toString()}`));