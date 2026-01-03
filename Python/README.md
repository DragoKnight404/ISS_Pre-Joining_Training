# Python Pre-Boarding Assignment

## Overview
This repository contains a structured progression of Python scripts developed for the pre-joining training. The code goes beyond basic syntax to demonstrate **"Pythonic" best practices**, memory management nuances, and advanced Object-Oriented patterns. 

Each file is self-contained and focuses on specific logical domains, moving from fundamental data structures to complex architectural patterns like Generators and Magic Methods.

---

## Code Index & Concept Map

| File Name | Topic | Key Concepts Demonstrated |
| :--- | :--- | :--- |
| **`Python_Part1.py`** | **Data & Memory** | • **Memory References:** The trap of `b = a` vs `copy.deepcopy()`.<br>• **Slicing:** Negative indexing (`arr[-1]`) and step slicing (`arr[::-1]`).<br>• **Immutability:** Tuple vs List behavior.<br>• **Dictionary Safety:** Using `.get()` vs direct access. |
| **`Python_Part2.py`** | **Advanced Logic** | • **Identity vs Equality:** `is` (memory) vs `==` (value).<br>• **Loop-Else:** The unique Python "search failure" block.<br>• **Safe Iteration:** Modifying a list while looping (using slice copies `list[:]`). |
| **`Python_Part3.py`** | **Functions** | • **Flexible Args:** `*args` (Tuple packing) and `**kwargs` (Dict packing).<br>• **Crucial Trap:** The danger of Mutable Default Arguments (`def foo(l=[])`) and the `None` fix.<br>• **Multiple Returns:** Tuple unpacking. |
| **`Python_Part4.py`** | **Error Handling** | • **Full Workflow:** `try` -> `except` -> `else` (Success block) -> `finally` (Cleanup).<br>• **Best Practices:** Catching specific errors (`ZeroDivisionError`) vs generic `Exception`.<br>• **Business Logic:** Enforcing rules using `raise`. |
| **`Python_Part5.py`** | **OOP & Sugar** | • **Magic Methods:** Operator overloading (`__add__`, `__eq__`, `__str__`).<br>• **Inheritance:** `super()` and Method Overriding.<br>• **Syntactic Sugar:** List Comprehensions (`[x*2 for x in list]`).<br>• **Generators:** Lazy evaluation using `yield` for memory efficiency. |

---

## Key "Pythonic" Features Highlighted
This submission specifically avoids "Java-style" Python. Instead, it utilizes:
* **F-Strings** for clean interpolation: `f"Hello {name}"`.
* **List Comprehensions** for concise loops.
* **Context Managers** (implicit in file handling logic).
* **Generators** for handling potentially large data streams without memory overflow.

---

## Execution Instructions

### Prerequisites
* Python 3.6 or higher (Required for F-Strings).

### Running the Files
Each file includes a `if __name__ == "__main__":` block, allowing it to be run independently.

**To run a specific module:**
```bash
# Example: Running the OOP module
python part5.py