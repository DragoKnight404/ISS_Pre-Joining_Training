"""
Python Part 3: Functions Deep Dive
Concepts Covered:
1. Basic Anatomy: def, docstrings, parameters vs arguments.
2. Return Values: Returning multiple items (Tuple Packing).
3. Flexible Arguments: *args (Positional) and **kwargs (Keyword).
4. THE TRAP: Mutable Default Arguments (Why you should never use empty lists as defaults).
"""

def main():
    print("\n=== 1. RETURN VALUES (Multiple Returns) ===")
    
    # Python functions can return multiple values naturally.
    # Under the hood, Python packs them into a single TUPLE.
    val1, val2, val3 = get_system_stats() # Unpacking the result
    print(f"CPU: {val1}%, RAM: {val2}GB, Status: {val3}")


    print("\n=== 2. *ARGS (Variable Positional Arguments) ===")
    
    # *args collects extra positional arguments into a TUPLE.
    # Useful when you don't know how many items a user will pass.
    calculate_total("Groceries", 10, 20, 30, 5.50) 
    calculate_total("Electronics", 500)


    print("\n=== 3. **KWARGS (Variable Keyword Arguments) ===")
    
    # **kwargs collects extra keyword arguments into a DICTIONARY.
    # Useful for configuration settings or optional flags.
    create_user_profile("alice_01", email="alice@test.com", role="Admin", active=True)


    print("\n=== 4. THE MUTABLE DEFAULT ARGUMENT TRAP (Crucial!) ===")
    
    # Scenario: A function to add items to a student's backpack.
    print("--- Call 1: Alice ---")
    add_item_bad("Pencil") 
    
    print("--- Call 2: Bob (EXPECTED: ['Pen'], ACTUAL: ['Pencil', 'Pen']) ---")
    # Bob inherits Alice's backpack because the list was created ONCE at definition time!
    add_item_bad("Pen") 
    
    print("--- The Fix ---")
    add_item_good("Pencil")
    add_item_good("Pen") # Bob gets his own fresh list now.


# ==========================================
# FUNCTION DEFINITIONS
# ==========================================

def get_system_stats():
    """
    Simulates fetching system metrics.
    Returns: A tuple (cpu_load, ram_usage, status_msg)
    """
    cpu = 45
    ram = 16
    status = "Healthy"
    return cpu, ram, status # Automatically creates a tuple (45, 16, "Healthy")

def calculate_total(category, *prices):
    """
    *prices will become a tuple containing all numbers passed after 'category'.
    """
    print(f"--- Category: {category} ---")
    print(f"Raw Args Tuple: {prices}")
    
    total = sum(prices) # We can loop over it or sum it directly
    print(f"Total Cost: ${total:.2f}")

def create_user_profile(username, **details):
    """
    **details will become a dictionary of keys and values.
    """
    print(f"--- Creating User: {username} ---")
    print(f"Raw Kwargs Dict: {details}")
    
    # Use .get() to safely access optional fields
    user_email = details.get("email", "No Email Provided")
    print(f"Saved email: {user_email}")

# --- THE BAD WAY ---
def add_item_bad(item, backpack=[]): 
    # PROBLEM: The list [] is created when Python READS this line (Definition Time),
    # not when the function runs. All calls share this ONE list.
    backpack.append(item)
    print(f"Backpack contents: {backpack}")

# --- THE GOOD WAY ---
def add_item_good(item, backpack=None):
    # FIX: Use None as the sentinel value.
    if backpack is None:
        backpack = [] # New list created at RUNTIME for every call.
    
    backpack.append(item)
    print(f"Backpack contents: {backpack}")

if __name__ == "__main__":
    main()