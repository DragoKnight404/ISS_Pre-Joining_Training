"""
Python Part 2: Advanced Control Flow
Concepts Covered:
1. Conditional Logic: Truthiness, 'is' vs '==', ternary operators.
2. Loops: 
   - 'enumerate' and 'zip' (The Pythonic iterators).
   - Modifying lists while iterating (The Trap).
3. Loop Control: break, continue, pass.
4. Loop-Else: The "No Break" clause (Unique to Python).
"""

def main():
    print("\n=== 1. CONDITIONAL LOGIC DEEP DIVE ===")
    
    # A. The "is" vs "==" Trap
    # '==' checks VALUE (Do they look the same?)
    # 'is' checks MEMORY ID (Are they the exact same object?)
    list_a = [1, 2, 3]
    list_b = [1, 2, 3]
    
    print(f"Values are equal? (a == b): {list_a == list_b}") # True
    print(f"Objects are same? (a is b): {list_a is list_b}") # False (Different memory addresses)
    
    # Edge Case: Small Integers (Python caches numbers -5 to 256)
    x = 100
    y = 100
    print(f"Small Int Cache (100 is 100): {x is y}") # True (Optimization)

    # B. Ternary Operator (One-line if-else)
    score = 85
    status = "Pass" if score >= 50 else "Fail"
    print(f"Status: {status}")


    print("\n=== 2. LOOPS & ITERATION PATTERNS ===")
    
    names = ["Alice", "Bob", "Charlie"]
    colors = ["Red", "Green", "Blue"]

    # A. Enumerate (Don't use 'range(len(names))')
    # Use enumerate to get both Index and Value cleanly
    print("--- Enumerate ---")
    for index, name in enumerate(names):
        print(f"Index {index}: {name}")

    # B. Zip (Iterate two lists at once)
    print("\n--- Zip ---")
    for name, color in zip(names, colors):
        print(f"{name} likes {color}")

    # C. The "Modifying while Iterating" Trap
    print("\n--- The Trap: Removing items while looping ---")
    numbers = [1, 2, 3, 4, 5]
    
    # WRONG WAY: This skips items because the index shifts left!
    # for n in numbers:
    #     if n % 2 == 0:
    #         numbers.remove(n) 
    
    # CORRECT WAY: Iterate over a COPY (numbers[:])
    for n in numbers[:]: 
        if n % 2 == 0:
            numbers.remove(n)
    print(f"Correctly filtered list: {numbers}")


    print("\n=== 3. LOOP CONTROL (Break, Continue, Pass) ===")
    
    for i in range(5):
        if i == 1:
            print("Skipping 1 (Continue)")
            continue
        if i == 3:
            print("Stopping at 3 (Break)")
            break
        
        # Pass: A placeholder that does nothing.
        # Useful for empty functions or TODOs.
        if i == 0:
            pass 
        
        print(f"Processed {i}")


    print("\n=== 4. THE LOOP-ELSE BLOCK (Deep Dive) ===")
    # Concept: The 'else' block runs ONLY if the loop completed naturally.
    # It does NOT run if the loop was stopped by a 'break'.
    
    target_id = 99
    database_ids = [10, 20, 30, 40, 50]

    print(f"Searching for {target_id}...")
    
    # Scenario: Search Logic WITHOUT a 'found' flag variable
    for db_id in database_ids:
        if db_id == target_id:
            print("Found it!")
            break
    else:
        # This runs because we NEVER hit 'break'
        print("Search completed: ID not found in database.")

    # Contrast: Finding a value
    target_id = 30
    print(f"\nSearching for {target_id}...")
    for db_id in database_ids:
        if db_id == target_id:
            print("Found it!")
            break # Hits break, so 'else' is SKIPPED
    else:
        print("ID not found.")

if __name__ == "__main__":
    main()