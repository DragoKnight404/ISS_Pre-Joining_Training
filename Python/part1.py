"""
Python Part 1: Deep Dive into Basics
Concepts Covered:
1. Variables: Dynamic Typing & Memory References
2. Strings: F-strings (Advanced formatting), Slicing, Immutability
3. Collections: 
   - Lists (Slicing, Negative Indexing, Reference Edge Cases)
   - Tuples (Unpacking, Single-element gotcha)
   - Sets (Operations like Union/Intersection)
   - Dictionaries (Safe access using .get(), merging)
4. Type Conversion: Explicit vs Implicit, Truthiness
"""
import copy # Needed for Deep Copy demonstration

def main():
    print("\n=== 1. VARIABLES & MEMORY ( The 'Reference' Trap ) ===")
    
    # Python variables are LABELS, not boxes.
    a = [1, 2, 3]
    b = a           # b points to the SAME object in memory as a
    b.append(4)
    
    # EDGE CASE: Changing 'b' changed 'a' too!
    print(f"Original 'a' after modifying 'b': {a}")  # Output: [1, 2, 3, 4] // it is a shallow copy
    print(f"Are 'a' and 'b' the same object? {a is b}") # True

    # CONSTANTS
    # Convention ONLY. Python will let you change this, but you shouldn't.
    PI = 3.14159 


    print("\n=== 2. STRINGS (Advanced) ===")
    
    quote = "Python is amazing"
    
    # EDGE CASE: Strings are IMMUTABLE. You cannot do quote[0] = 'J'
    # Slicing [Start : Stop : Step]
    print(f"Reversed String: {quote[::-1]}")       # gnizama si nohtyP
    print(f"Slice (0 to 6):  {quote[0:6]}")        # Python
    
    # F-String Formatting (Math & Padding)
    price = 50
    # .2f means 2 decimal places. >10 means right-align with width 10.
    print(f"Formatted Price: ${price:.2f}")        # $50.00
    print(f"Aligned:         |{price:>10}|")       # |        50|


    print("\n=== 3. COLLECTIONS DEEP DIVE ===")
    
    # --- A. LISTS (Advanced) ---
    numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    
    # Slicing Edge Cases
    print(f"Last item (Negative Index): {numbers[-1]}") # 9
    print(f"Every 2nd item: {numbers[::2]}")           # [0, 2, 4, 6, 8]
    
    # COPYING LISTS (Shallow vs Deep)
    nested_list = [[1, 2], [3, 4]]
    
    # Shallow Copy (Common Trap!)
    shallow = nested_list.copy()
    shallow[0][0] = 999 
    print(f"Original affected by Shallow Copy? {nested_list[0][0] == 999}") # True!
    
    # Deep Copy (The Fix)
    nested_list = [[1, 2], [3, 4]] # Reset
    deep = copy.deepcopy(nested_list)
    deep[0][0] = 999
    print(f"Original affected by Deep Copy?    {nested_list[0][0] == 999}") # False!

    # --- B. TUPLES (The 'One Item' Trap) ---
    single_item_tuple = (5,) # COMMA is mandatory for single item tuples
    not_a_tuple = (5)        # This is just an Integer
    print(f"Type of (5,): {type(single_item_tuple)}") 
    print(f"Type of (5):  {type(not_a_tuple)}")

    # Tuple Unpacking (Swapping variables without temp)
    x, y = 10, 20
    x, y = y, x 
    print(f"Swapped: x={x}, y={y}")

    # --- C. SETS (Math Operations) ---
    set_a = {1, 2, 3, 4}
    set_b = {3, 4, 5, 6}
    
    print(f"Union (All unique): {set_a | set_b}")        # {1, 2, 3, 4, 5, 6}
    print(f"Intersection (Common): {set_a & set_b}")    # {3, 4}
    print(f"Difference (A minus B): {set_a - set_b}")   # {1, 2}

    # --- D. DICTIONARIES (Safe Access) ---
    user = {"name": "Alice", "age": 25}
    
    # The Trap: user["salary"] throws KeyError if key doesn't exist
    # The Fix: .get() returns None (or default value) instead of crashing
    salary = user.get("salary", 50000) # Returns 50000 if "salary" missing
    print(f"Safe Salary Access: {salary}")
    
    # Merging Dictionaries (Python 3.9+)
    extra_info = {"city": "NY", "age": 26} # Note age overlap
    merged_user = user | extra_info # 'extra_info' overwrites 'user' duplicates
    print(f"Merged Dict: {merged_user}")


    print("\n=== 4. TYPE CONVERSION & TRUTHINESS ===")
    
    # TRUTHINESS (What counts as False?)
    # False values: False, 0, 0.0, "", [], {}, (), None
    values_to_test = [0, "Hello", "", [], [1], None]
    
    for v in values_to_test:
        if v:
            print(f" -> '{v}' is True-ish")
        else:
            print(f" -> '{v}' is False-ish")

    # EDGE CASE: Converting Float to Int truncates (cuts off), doesn't round
    print(f"int(9.99) is: {int(9.99)}") # Result is 9, not 10!

if __name__ == "__main__":
    main()