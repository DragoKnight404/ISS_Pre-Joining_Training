/**
 Part 2: Datatypes, Variables, Modifiers, Final, Constructors, Wrapper Classes
 This class demonstrates:
 1. Primitive vs Reference Datatypes
 2. Wrapper Classes (Autoboxing/Unboxing)
 3. Variable Types (Instance, Static, Local)
 4. The 'final' keyword
 5. Constructor Overloading
 */
class EmployeeData {

    // 1. INSTANCE VARIABLES (State)
    // belong to a specific object. Every employee has their own.
    int employeeId;           // Primitive Type (Integer)
    double monthlySalary;     // Primitive Type (Floating point)
    boolean isPermanent;      // Primitive Type (Boolean)
    String employeeName;      // Reference Type (Class)

    // 2. WRAPPER CLASSES
    // Object representation of primitives. Can be null.
    // Useful for Collections (Lists/Maps) which don't support primitives.
    Integer performanceRating; // Wrapper for 'int' (Default is null)

    // 3. STATIC VARIABLES (Class Variables)
    // Shared by ALL objects of this class.
    // If we change this, it changes for every object.
    static String companyName = "TechCorp Solutions";

    // 4. FINAL VARIABLES (Constants)
    // Value cannot be changed once assigned.
    // Standard naming convention for constants is UPPER_SNAKE_CASE.
    final int MAX_LEAVES = 20;

    // 5. CONSTRUCTORS
    public EmployeeData(int id, String name, double salary, boolean status) {
        this.employeeId = id;
        this.employeeName = name;
        this.monthlySalary = salary;
        this.isPermanent = status;
    }

    /**
     * Constructor Overloading:
     * A second constructor for when we only know the ID and Name.
     * Sets default values for other fields.
     */
    public EmployeeData(int id, String name) {
        this.employeeId = id;
        this.employeeName = name;
        this.monthlySalary = 0.0; // Default logic
        this.isPermanent = false; // Default logic
    }

    //6. METHODS (Demonstrating Local Variables)
    /**
     * Calculates the annual package.
     * Demonstrates 'Local Variables' - variables that die when the method ends.
     */
    public void calculateAnnualPackage() {
        // 'bonus' is a LOCAL variable. Not accessible outside.
        double bonus = 1000.00;

        // Unboxing demo: treating the Wrapper 'performanceRating' like a primitive 'int'
        // If rating is null, we treat it as 0
        int rating = (performanceRating != null) ? performanceRating : 0;

        if(rating > 4) {
            bonus += 500; // Extra bonus for high rating
        }

        double totalPackage = (this.monthlySalary * 12) + bonus;

        System.out.println("Annual Package for " + this.employeeName + ": Rs " + totalPackage);
    }

    public void showDetails() {
        System.out.println("ID: " + employeeId + " | Name: " + employeeName + " | Company: " + companyName);
        System.out.println("Leaves Allowed: " + MAX_LEAVES); // Accessing final variable
    }

}


public class Part2{
    public static void main(String[] args) {

        System.out.println("--- Scenario 1: Using Parameterized Constructor ---");
        // Creating an object with all datatypes
        EmployeeData emp1 = new EmployeeData(101, "Harsh Kasliwal", 5000.50, true);
        emp1.showDetails();
        emp1.calculateAnnualPackage();

        System.out.println("\n--- Scenario 2: Using Overloaded Constructor ---");
        // Creating an object with limited data
        EmployeeData emp2 = new EmployeeData(102, "Aditya Nair");
        emp2.showDetails();

        System.out.println("\n--- Scenario 3: Static Variable Impact ---");
        // Changing a STATIC variable affects ALL objects
        EmployeeData.companyName = "Global Tech Industries";

        System.out.println("After company rebrand:");
        System.out.print("Emp 1 Company: " + emp1.companyName); // Accessed via object (discouraged but works)
        System.out.println(" | Emp 2 Company: " + emp2.companyName);

        // emp1.MAX_LEAVES = 25; // <--- This would cause a COMPILER ERROR because it is 'final'

        Integer a = Integer.valueOf(10);
        Integer b = 20; // Autoboxing primitive -> object
        int c = b; // object -> primitive

        //Java caches Integer values from -128 to 127.
        a=100; b=100;
        System.out.println(a == b); // true

        a=200; b=200;
        System.out.println(a == b); // false
        System.out.println(a.equals(b)); // true // correct way to perform comparison

        //Wrapper objects are immutable
        a=5;
        a=a+5; // this creates a new Integer object, the compiler does this task automatically
        // equivalent to a = Integer.valueOf(a.intValue() + 5);

    }
}