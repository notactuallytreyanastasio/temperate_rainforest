import com.example.shufflesort.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class JavaExample {
    public static void main(String[] args) {
        System.out.println("=== Java Shuffle & Sort Demo ===\n");
        
        // Create a simple random generator
        SimpleRandom rng = new SimpleRandom(42);
        
        // Test with integers
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6, 10);
        System.out.println("Original numbers: " + numbers);
        
        // Shuffle the numbers
        List<Integer> shuffled = ShuffleSortGlobal.shuffle(numbers, rng);
        System.out.println("Shuffled numbers: " + shuffled);
        
        // Sort them back
        List<Integer> sorted = ShuffleSortGlobal.bubbleSort(shuffled, ShuffleSortGlobal::compareInts);
        System.out.println("Sorted numbers:   " + sorted);
        
        // Test with custom objects
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35),
            new Person("Diana", 28)
        );
        
        System.out.println("\nOriginal people:");
        for (Person p : people) {
            System.out.println("  " + p.toString());
        }
        
        // Shuffle people
        SimpleRandom rng2 = new SimpleRandom(123);
        List<Person> shuffledPeople = ShuffleSortGlobal.shuffle(people, rng2);
        System.out.println("\nShuffled people:");
        for (Person p : shuffledPeople) {
            System.out.println("  " + p.toString());
        }
        
        // Sort by age
        List<Person> sortedByAge = ShuffleSortGlobal.bubbleSort(shuffledPeople, 
            ShuffleSortGlobal::comparePersonsByAge);
        System.out.println("\nSorted by age:");
        for (Person p : sortedByAge) {
            System.out.println("  " + p.toString());
        }
    }
}