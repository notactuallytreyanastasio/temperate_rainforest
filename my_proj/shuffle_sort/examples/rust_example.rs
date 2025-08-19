use shuffle_sort::{shuffle, bubble_sort, compare_ints, SimpleRandom, Person, compare_persons_by_age, RandomGenerator};

fn main() {
    println!("=== Rust Shuffle & Sort Demo ===\n");
    
    // Create a simple random generator
    let rng = SimpleRandom::new(42);
    
    // Test with integers
    let numbers = vec![5, 2, 8, 1, 9, 3, 7, 4, 6, 10];
    println!("Original numbers: {:?}", numbers);
    
    // Shuffle the numbers
    let shuffled = shuffle(numbers.clone(), RandomGenerator::new(rng.clone())).unwrap();
    println!("Shuffled numbers: {:?}", shuffled);
    
    // Sort them back
    let sorted = bubble_sort(shuffled, compare_ints);
    println!("Sorted numbers:   {:?}", sorted);
    
    // Test with custom objects
    let people = vec![
        Person::new("Alice".to_string(), 30),
        Person::new("Bob".to_string(), 25),
        Person::new("Charlie".to_string(), 35),
        Person::new("Diana".to_string(), 28),
    ];
    
    println!("\nOriginal people:");
    for p in &people {
        println!("  {}", p.to_string());
    }
    
    // Shuffle people
    let rng2 = SimpleRandom::new(123);
    let shuffled_people = shuffle(people.clone(), RandomGenerator::new(rng2)).unwrap();
    println!("\nShuffled people:");
    for p in &shuffled_people {
        println!("  {}", p.to_string());
    }
    
    // Sort by age
    let sorted_by_age = bubble_sort(shuffled_people, compare_persons_by_age);
    println!("\nSorted by age:");
    for p in &sorted_by_age {
        println!("  {}", p.to_string());
    }
}