/**
 * Part 7: Collections & Sorting
 * Concepts Covered:
 * 1. List (ArrayList): Ordered, allows duplicates.
 * 2. Set (HashSet): Unordered, UNIQUE elements only (requires hashCode/equals).
 * 3. Map (HashMap): Key-Value pairs.
 * 4. Sorting:
 * - Comparable (Natural ordering: by ID)
 * - Comparator (Custom ordering: by Name)
 */
import java.util.*;

// 1. CUSTOM OBJECT (Implementing Comparable for Default Sorting)
class Student implements Comparable<Student> {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // CRITICAL FOR SETS/MAPS:
    // Without these, Java thinks two Students with ID 101 are different objects.
    @Override
    public int hashCode() {
        return Objects.hash(id); // Generate unique hash based on ID
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return id == other.id; // Two students are equal if their IDs are equal
    }

    // CRITICAL FOR SORTING (Comparable Interface):
    // Defines the "Natural Ordering" (Default sort by ID)
    @Override
    public int compareTo(Student other) {
        // Returns negative if this.id < other.id, positive if >, 0 if equal
        return this.id - other.id;
    }

    @Override
    public String toString() {
        return "[ID=" + id + ", Name=" + name + "]";
    }
}

// 2. CUSTOM COMPARATOR (For Custom Sorting)
// Allows us to sort by something OTHER than ID (e.g., Name)
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name); // String's built-in comparison
    }
}

public class Part7 {
    public static void main(String[] args) {

        // --- PREPARATION: Create some Student objects ---
        Student s1 = new Student(103, "Charlie");
        Student s2 = new Student(101, "Alice");
        Student s3 = new Student(102, "Bob");
        Student s4 = new Student(101, "Alice"); // Duplicate of s2 (Same ID)

        // ==========================================
        // 1. LIST (ArrayList)
        // Keeps insertion order. Allows Duplicates.
        // ==========================================
        System.out.println("--- 1. List<Student> (Ordered, Duplicates Allowed) ---");
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4); // This duplicate will be added

        for (Student s : studentList) {
            System.out.println(s);
        }

        // ==========================================
        // 2. SET (HashSet)
        // Unordered. NO Duplicates.
        // Requires hashCode() and equals() in Student class to work
        // ==========================================
        System.out.println("\n--- 2. Set<Student> (Unique Only) ---");
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(s1);
        studentSet.add(s2);
        studentSet.add(s3);
        studentSet.add(s4); // This duplicate is IGNORED because ID 101 exists

        for (Student s : studentSet) {
            System.out.println(s);
        }

        // ==========================================
        // 3. MAP (HashMap)
        // Key-Value Pairs. Keys must be unique.
        // Prompt asked for Map<Student, Student>.
        // Use Case: Mapping a Student to their Project Partner.
        // ==========================================
        System.out.println("\n--- 3. Map<Student, Student> (Key->Value) ---");
        Map<Student, Student> projectPartners = new HashMap<>();

        // Key: Alice, Value: Bob (Alice is partnered with Bob)
        projectPartners.put(s2, s3);
        // Key: Charlie, Value: Alice
        projectPartners.put(s1, s2);

        // Fetch partner for Alice
        Student partner = projectPartners.get(s2);
        System.out.println("Alice's Partner is: " + partner.name);

        // ==========================================
        // 4. SORTING (Comparable vs Comparator)
        // ==========================================
        System.out.println("\n--- 4. Sorting ---");

        // A. Natural Sort (using compareTo in Student class -> ID)
        Collections.sort(studentList);
        System.out.println("Sorted by ID (Comparable): " + studentList);

        // B. Custom Sort (using NameComparator -> Name)
        Collections.sort(studentList, new NameComparator());
        System.out.println("Sorted by Name (Comparator): " + studentList);
    }
}