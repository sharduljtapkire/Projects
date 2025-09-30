# Generalised Data Structures and Algorithms Library 📚

This Java file, `Generalised_Data_Structures_library.java`, is a consolidated collection of fundamental **Data Structures** and **Algorithms** implementations. It offers a ready-to-use library covering various linked lists, queue, stack, binary search tree, sorting, and searching mechanisms, along with utility logic for number properties (prime, perfect, etc.).

## 🚀 Features

This single file contains the implementations for the following:

### Data Structures (Generic)

| Class Name | Type | Description |
| :--- | :--- | :--- |
| `QueueX<T>` | Queue (Linked List) | First-In, First-Out (FIFO) structure. |
| `StackX<T>` | Stack (Linked List) | Last-In, First-Out (LIFO) structure. |
| `SinglyLL<T extends Number>` | Singly Linear Linked List | Basic linked list. Includes number property logic. |
| `DoublyLL<T extends Number>` | Doubly Linear Linked List | Linked list with forward and backward links. Includes number property logic. |
| `SinglyCLL<T extends Number>` | Singly Circular Linked List | Linear list where the last node points back to the first. Includes number property logic. |
| `DoublyCLL<T extends Number>` | Doubly Circular Linked List | Circular list with forward and backward links. Includes number property logic. |
| `BST<T extends Comparable<T>>` | Binary Search Tree | A tree data structure with ordered nodes, supporting recursive traversals (Inorder, Preorder, Postorder) and search. |

### Algorithms

| Class Name | Functionality | Algorithms Included |
| :--- | :--- | :--- |
| `Sorting` | Sorting | **Bubble Sort**, **Selection Sort** |
| `Searching` | Searching | **Linear Search**, **Binary Search** (requires sorted input) |

### Number Property Utilities (Included in LL Classes)

The Linked List classes (`SinglyLL`, `DoublyLL`, `SinglyCLL`, `DoublyCLL`) include methods to perform operations specific to numeric data:

* `displayPerfect()`: Prints all **Perfect Numbers** in the list.
* `displayPrime()`: Prints all **Prime Numbers** in the list.
* `sumOfDigits()`: Prints the **sum of digits** for each number.
* `reverseDigits()`: **Reverses the digits** of each number in the list (e.g., 123 becomes 321).

---

## 🛠️ Getting Started

### Prerequisites

You need a Java Development Kit (JDK) installed on your system to compile and run the code.

### Running the Code

1.  **Save the file:** Save the provided code as `Generalised_Data_Structures_library.java`.
2.  **Compile:** Open your terminal or command prompt and compile the file:
    ```bash
    javac Generalised_Data_Structures_library.java
    ```
3.  **Run:** Execute the compiled class. The `main` method demonstrates the basic functionality of all implemented data structures and algorithms.
    ```bash
    java Generalised_Data_Structures_library
    ```

---

## 📝 Code Structure Overview

The file is organized with classes for each major data structure and algorithm:

```java
// Main Class: Generalised_Data_Structures_library (Contains main method for demonstration)
import java.util.NoSuchElementException;
import java.util.Arrays;

class QueueX<T> { ... }         // Queue Implementation
class StackX<T> { ... }         // Stack Implementation

class SinglyLL<T extends Number> { ... } // Singly Linear Linked List
class DoublyLL<T extends Number> { ... } // Doubly Linear Linked List
class SinglyCLL<T extends Number> { ... } // Singly Circular Linked List
class DoublyCLL<T extends Number> { ... } // Doubly Circular Linked List

class BST<T extends Comparable<T>> { ... } // Binary Search Tree

class Sorting { ... }           // Sorting Algorithms
class Searching { ... }         // Searching Algorithms
````

-----

## 💡 Usage Example (from `main` method)

```java
// Example: Testing SinglyLL
SinglyLL<Integer> singlyLL = new SinglyLL<>();
System.out.println("\nTesting Singly Linked List:");
singlyLL.insertFirst(6); // 6 is a perfect number
singlyLL.insertLast(28); // 28 is a perfect number
singlyLL.insertLast(17); // 17 is a prime number
singlyLL.display();
// Output: | 6 |-> | 28 |-> | 17 |->

singlyLL.displayPerfect();
// Output: Perfect Numbers: 6 28 

singlyLL.reverseDigits();
singlyLL.display();
// Output (after reverseDigits): | 6 |-> | 82 |-> | 71 |-> 
```

-----

## 👤 Author

  * **Shardul Tapkire**
  * **Date:** 29/09/2025

-----