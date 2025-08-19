# Shuffle & Sort Library

A cross-language library implementing Fisher-Yates shuffle and bubble sort algorithms, built with Temper.

## Features

- **Fisher-Yates Shuffle**: Efficient O(n) algorithm for randomly shuffling lists
- **Bubble Sort**: Simple O(n²) sorting algorithm with customizable comparison functions
- **Cross-Language Support**: Compiles to C#, Java, JavaScript, Lua, Python, and Rust
- **Type Safety**: Generic implementations work with any data type
- **Custom Comparators**: Sort using any comparison logic
- **Built-in Test Suite**: Comprehensive unit tests included

## Algorithm Implementations

### Fisher-Yates Shuffle
The Fisher-Yates shuffle (also known as Knuth shuffle) generates a random permutation of a finite sequence in O(n) time. Our implementation:
- Creates a mutable copy of the input list
- Iterates from the end backwards
- Swaps each element with a randomly selected element before it
- Returns a new shuffled list

### Bubble Sort
A simple sorting algorithm that repeatedly steps through the list, compares adjacent elements and swaps them if they're in wrong order. Our implementation:
- Creates a mutable copy of the input list
- Uses a custom comparison function for flexibility
- Includes optimization to stop early if list becomes sorted
- Returns a new sorted list

## Building the Library

```bash
# From the shuffle_sort directory
../../temper build
```

This generates libraries for all target languages in `../../temper.out/`

## Running Tests

```bash
# Test with JavaScript
../../temper test -b js

# Test with other backends (requires appropriate runtime)
../../temper test -b py    # Requires Python 3.11+
../../temper test -b java  # Requires Java
../../temper test -b csharp # Requires .NET
```

## Usage Examples

### JavaScript
```javascript
import { shuffle, bubbleSort, compareInts, SimpleRandom } from 'shuffle-sort';

const rng = new SimpleRandom(42);
const numbers = [5, 2, 8, 1, 9, 3];
const shuffled = shuffle(numbers, rng);
const sorted = bubbleSort(shuffled, compareInts);
```

### Java
```java
import com.example.shufflesort.*;

SimpleRandom rng = new SimpleRandom(42);
List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);
List<Integer> shuffled = ShuffleSortGlobal.shuffle(numbers, rng);
List<Integer> sorted = ShuffleSortGlobal.bubbleSort(shuffled, ShuffleSortGlobal::compareInts);
```

### C#
```csharp
using ShuffleSort;
using static ShuffleSort.ShuffleSortGlobal;

var rng = new SimpleRandom(42);
var numbers = new List<int> { 5, 2, 8, 1, 9, 3 };
var shuffled = Shuffle(numbers, rng);
var sorted = BubbleSort(shuffled, CompareInts);
```

### Python
```python
from shuffle_sort import shuffle, bubble_sort, compare_ints, SimpleRandom

rng = SimpleRandom(42)
numbers = [5, 2, 8, 1, 9, 3]
shuffled = shuffle(numbers, rng)
sorted_nums = bubble_sort(shuffled, compare_ints)
```

### Rust
```rust
use shuffle_sort::{shuffle, bubble_sort, compare_ints, SimpleRandom, RandomGenerator};

let rng = SimpleRandom::new(42);
let numbers = vec![5, 2, 8, 1, 9, 3];
let shuffled = shuffle(numbers.clone(), RandomGenerator::new(rng)).unwrap();
let sorted = bubble_sort(shuffled, compare_ints);
```

### Lua
```lua
local shuffle_sort = require("shuffle_sort")

local rng = shuffle_sort.SimpleRandom(42)
local numbers = {5, 2, 8, 1, 9, 3}
local shuffled = shuffle_sort.shuffle(numbers, rng)
local sorted = shuffle_sort.bubbleSort(shuffled, shuffle_sort.compareInts)
```

## Custom Types

The library includes a `Person` class for demonstrating sorting of custom objects:

```javascript
// JavaScript example
const people = [
    new Person("Alice", 30),
    new Person("Bob", 25)
];
const sortedByAge = bubbleSort(people, comparePersonsByAge);
const sortedByName = bubbleSort(people, comparePersonsByName);
```

## API Reference

### Functions
- `shuffle<T>(items: List<T>, rng: RandomGenerator): List<T> | Bubble` - Shuffles a list
- `bubbleSort<T>(items: List<T>, compare: fn(T, T): Int): List<T>` - Sorts a list

### Comparison Functions
- `compareInts(a: Int, b: Int): Int` - Compare integers
- `compareFloats(a: Float64, b: Float64): Int` - Compare floating point numbers
- `compareStrings(a: String, b: String): Int` - Compare strings
- `comparePersonsByAge(a: Person, b: Person): Int` - Compare Person objects by age
- `comparePersonsByName(a: Person, b: Person): Int` - Compare Person objects by name

### Classes
- `SimpleRandom(seed: Int)` - A simple linear congruential generator (not cryptographically secure!)
- `Person(name: String, age: Int)` - Example custom type

### Interfaces
- `RandomGenerator` - Interface for random number generators with `nextInt(max: Int): Int | Bubble`

## Test Results

All 6 unit tests pass:
- ✅ Shuffle preserves all elements
- ✅ Shuffle actually shuffles
- ✅ Bubble sort sorts integers
- ✅ Bubble sort sorts strings  
- ✅ Bubble sort sorts custom objects
- ✅ Shuffle then sort returns to original order

## License

MIT

## Notes

- The `SimpleRandom` class uses a linear congruential generator and is NOT cryptographically secure
- For production use, implement `RandomGenerator` with your platform's secure random number generator
- Bubble sort has O(n²) complexity; for large datasets, consider more efficient algorithms