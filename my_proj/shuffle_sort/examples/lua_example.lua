-- Lua example of using the shuffle-sort library
local shuffle_sort = require("shuffle_sort")

print("=== Lua Shuffle & Sort Demo ===\n")

-- Create a simple random generator
local rng = shuffle_sort.SimpleRandom(42)

-- Test with integers
local numbers = {5, 2, 8, 1, 9, 3, 7, 4, 6, 10}
print("Original numbers: " .. table.concat(numbers, ", "))

-- Shuffle the numbers
local shuffled = shuffle_sort.shuffle(numbers, rng)
print("Shuffled numbers: " .. table.concat(shuffled, ", "))

-- Sort them back
local sorted = shuffle_sort.bubbleSort(shuffled, shuffle_sort.compareInts)
print("Sorted numbers:   " .. table.concat(sorted, ", "))

-- Test with custom objects
local people = {
    shuffle_sort.Person("Alice", 30),
    shuffle_sort.Person("Bob", 25),
    shuffle_sort.Person("Charlie", 35),
    shuffle_sort.Person("Diana", 28)
}

print("\nOriginal people:")
for _, p in ipairs(people) do
    print("  " .. p:toString())
end

-- Shuffle people
local rng2 = shuffle_sort.SimpleRandom(123)
local shuffled_people = shuffle_sort.shuffle(people, rng2)
print("\nShuffled people:")
for _, p in ipairs(shuffled_people) do
    print("  " .. p:toString())
end

-- Sort by age
local sorted_by_age = shuffle_sort.bubbleSort(shuffled_people, shuffle_sort.comparePersonsByAge)
print("\nSorted by age:")
for _, p in ipairs(sorted_by_age) do
    print("  " .. p:toString())
end