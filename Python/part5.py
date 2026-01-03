"""
Python Part 5: OOP & Pythonic Features
Concepts Covered:
1. Classes: __init__, self (Java's 'this').
2. Inheritance: super() and Method Overriding.
3. Magic Methods (Dunder): __str__, __eq__, __add__.
4. List Comprehensions: The 'one-liner' loops.
5. Generators: The 'yield' keyword (Lazy Evaluation).
"""

def main():
    print("=== 1. OOP & MAGIC METHODS ===")
    
    # Creating Objects
    emp1 = Employee("Alice", 50000)
    emp2 = Employee("Bob", 60000)
    emp3 = Employee("Alice", 50000) # Same data as emp1

    # Using __str__ (Like Java toString)
    print(f"Employee 1: {emp1}") 

    # Using __eq__ (Custom Equality Logic)
    # Without __eq__, this would be False (different memory addresses)
    print(f"Is Emp1 == Emp3? {emp1 == emp3}") 

    # Using __add__ (Operator Overloading)
    # We defined '+' to combine salaries. 
    combined_salary = emp1 + emp2
    print(f"Combined Dept Budget: ${combined_salary}")


    print("\n=== 2. INHERITANCE ===")
    
    mgr = Manager("Charlie", 90000, "IT Dept")
    print(mgr) # Uses Manager's custom string format
    print(f"Bonus: ${mgr.calculate_bonus()}")


    print("\n=== 3. LIST COMPREHENSIONS (Pythonic Loops) ===")
    
    numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    
    # JAVA WAY:
    # evens = []
    # for n in numbers:
    #     if n % 2 == 0: evens.append(n)
    
    # PYTHON WAY: [Expression for Item in List if Condition]
    evens = [n for n in numbers if n % 2 == 0]
    squares = [n**2 for n in numbers]
    
    print(f"Evens: {evens}")
    print(f"Squares: {squares}")


    print("\n=== 4. GENERATORS (Yield vs Return) ===")
    
    # Generators don't store data in memory. They generate it on the fly.
    # Essential for big data (e.g., reading a 10GB file line by line).
    print("Countdown Generator:")
    for num in simple_countdown(3):
        print(f"Tick: {num}")

# ==========================================
# CLASS DEFINITIONS
# ==========================================

class Employee:
    # __init__ is the Constructor
    def __init__(self, name, salary):
        self.name = name       # Public attribute
        self._salary = salary  # _Convention for 'protected' (Internal use)

    # Defines how the object looks when printed.
    def __str__(self):
        return f"[Employee: {self.name}, Salary: ${self._salary}]"

    # Defines logic for '==' operator.
    def __eq__(self, other):
        # Two employees are 'equal' if names and salaries match
        return self.name == other.name and self._salary == other._salary

    # Defines logic for '+' operator.
    def __add__(self, other):
        return self._salary + other._salary
    
    def calculate_bonus(self):
        return self._salary * 0.10 # 10% bonus

class Manager(Employee):
    def __init__(self, name, salary, dept):
        # super() calls the Parent's __init__
        super().__init__(name, salary)
        self.dept = dept

    # OVERRIDING a method
    def calculate_bonus(self):
        return self._salary * 0.20 # Managers get 20% bonus

    # OVERRIDING __str__
    def __str__(self):
        return f"[Manager: {self.name}, Dept: {self.dept}]"


# ==========================================
# GENERATOR FUNCTION
# ==========================================
def simple_countdown(n):
    while n > 0:
        yield n # Pauses here, returns value, waits for next call
        n -= 1

if __name__ == "__main__":
    main()