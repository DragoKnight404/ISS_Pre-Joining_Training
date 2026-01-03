"""
Python Part 4: Error Handling & Exceptions
Concepts Covered:
1. Basic Structure: try, except, else, finally.
2. Specific Exceptions: Catching what you expect (vs "Catch-All").
3. The 'else' block: Code that runs ONLY if success.
4. The 'finally' block: Cleanup code that runs NO MATTER WHAT.
5. Raising Exceptions: Creating custom errors using 'raise'.
"""

def main():
    print("\n=== 1. BASIC ERROR HANDLING FLOW ===")
    
    # Scenario: Safe Division Calculator
    calculate_division(10, 2)   # Happy Path
    calculate_division(10, 0)   # Error Path (ZeroDivision)
    calculate_division(10, "5") # Error Path (TypeError)


    print("\n=== 2. RAISING CUSTOM EXCEPTIONS ===")
    
    try:
        register_user("admin", -5) # Age is invalid
    except ValueError as e:
        print(f"Registration Failed: {e}")
    except Exception as e:
        # This is a "Catch-All". Use sparingly!
        print(f"Unexpected System Error: {e}")


# ==========================================
# FUNCTION DEFINITIONS
# ==========================================

def calculate_division(numerator, denominator):
    print(f"\n--- Attempting {numerator} / {denominator} ---")
    
    try:
        # 1. TRY: The Dangerous Code
        result = numerator / denominator
    
    except ZeroDivisionError:
        # 2. EXCEPT: Specific Handler for dividing by zero
        print("Error: You cannot divide by zero!")
        
    except TypeError:
        # 2. EXCEPT: Specific Handler for wrong data types
        print("Error: Inputs must be numbers, not strings!")
        
    else:
        # 3. ELSE: Runs ONLY if 'try' succeeded (No errors)
        # Why use this? It keeps the 'try' block clean. 
        # Only put the dangerous line in 'try', put the follow-up logic here.
        print(f"Success! Result is: {result:.2f}")
        
    finally:
        # 4. FINALLY: Runs ALWAYS (Success or Fail)
        # Used for closing files, DB connections, or releasing resources.
        print("Cleanup: Operation attempt finished.")


def register_user(username, age):
    """
    Demonstrates creating limits using 'raise'.
    """
    print(f"\nProcessing user: {username}...")
    
    # LOGIC CHECK
    if age < 0:
        # RAISE: Manually triggers an error.
        # We use ValueError because the *type* is right (int), but *value* is wrong.
        raise ValueError("Age cannot be negative.")
    
    if age < 18:
        raise ValueError("User must be 18 or older.")
        
    print(f"User {username} registered successfully.")

if __name__ == "__main__":
    main()