/////////////////////////////////////////////////////////////////////////////
//
// File Name     : Generalised_Data_Structures_library.java
// Description   : A merged file containing implementations for various data structures and algorithms.
//                 Includes Queue, Stack, SinglyLL, DoublyLL, SinglyCLL, DoublyCLL, BST,
//                 Sorting (Bubble, Selection), and Searching (Linear, Binary).
//                 Also includes additional logic for number properties (prime, perfect, etc.).
// Author        : Shardul Tapkire
// Date          : 29/09/2025
//
/////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;
import java.util.Arrays;

///////////////////////////////////////////////////////////////////////
//
// Class       : QueueX
// Description : Generic Queue implementation [from Program647a.java]
//
///////////////////////////////////////////////////////////////////////
class QueueX<T> {
    private class Node {
        T data;
        Node next;
        Node(T value) { this.data = value; this.next = null; }
    }

    private Node first;
    private int count;

    public QueueX() {
        first = null;
        count = 0;
    }

    public void enqueue(T value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = newNode;
        } else {
            Node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        count++;
    }

    public T dequeue() {
        if (first == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        T value = first.data;
        first = first.next;
        count--;
        return value;
    }

    public void display() {
        Node temp = first;
        while (temp != null) {
            System.out.print("| " + temp.data + " |-");
            temp = temp.next;
        }
        System.out.println();
    }

    public int count() {
        return count;
    }
}

///////////////////////////////////////////////////////////////////////
//
// Class       : StackX
// Description : Generic Stack implementation [from Program647a.java]
//
///////////////////////////////////////////////////////////////////////
class StackX<T> {
    private class Node {
        T data;
        Node next;
        Node(T value) { this.data = value; this.next = null; }
    }

    private Node first;
    private int count;

    public StackX() {
        first = null;
        count = 0;
    }

    public void push(T value) {
        Node newNode = new Node(value);
        newNode.next = first;
        first = newNode;
        count++;
    }

    public T pop() {
        if (first == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        T value = first.data;
        first = first.next;
        count--;
        return value;
    }

    public void display() {
        Node temp = first;
        while (temp != null) {
            System.out.print("| " + temp.data + " |-");
            temp = temp.next;
        }
        System.out.println();
    }

    public int count() {
        return count;
    }
}

///////////////////////////////////////////////////////////////////////
//
// Class       : SinglyLL
// Description : Generic Singly Linear Linked List with extra logic [from Program647b.java]
//
///////////////////////////////////////////////////////////////////////
class SinglyLL<T extends Number> {
    private class Node {
        T data;
        Node next;
        Node(T value) { data = value; next = null; }
    }

    private Node first;
    private int count;

    public SinglyLL() {
        first = null;
        count = 0;
    }

    public void insertFirst(T value) {
        Node newNode = new Node(value);
        newNode.next = first;
        first = newNode;
        count++;
    }

    public void insertLast(T value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = newNode;
        } else {
            Node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        count++;
    }

    public void deleteFirst() {
        if (first == null) return;
        first = first.next;
        count--;
    }

    public void deleteLast() {
        if (first == null) return;
        if (first.next == null) {
            first = null;
            count--;
            return;
        }
        Node temp = first;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        count--;
    }

    public void display() {
        Node temp = first;
        while (temp != null) {
            System.out.print("| " + temp.data + " |-> ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int count() {
        return count;
    }

    ////////////////////////// Extra logic //////////////////////////
    public void displayPerfect() {
        System.out.print("Perfect Numbers: ");
        Node temp = first;
        while (temp != null) {
            if (isPerfect(temp.data.intValue())) System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void displayPrime() {
        System.out.print("Prime Numbers: ");
        Node temp = first;
        while (temp != null) {
            if (isPrime(temp.data.intValue())) System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void sumOfDigits() {
        System.out.print("Sum of digits: ");
        Node temp = first;
        while (temp != null) {
            int num = temp.data.intValue(), sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            System.out.print(sum + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void reverseDigits() {
        Node temp = first;
        while (temp != null) {
            int num = temp.data.intValue(), rev = 0;
            while (num != 0) {
                rev = rev * 10 + num % 10;
                num /= 10;
            }
            temp.data = (T) Integer.valueOf(rev);
            temp = temp.next;
        }
    }

    ////////////////////////// Helper methods //////////////////////////
    private boolean isPerfect(int no) {
        int sum = 0;
        for (int i = 1; i <= no / 2; i++) {
            if (no % i == 0) sum += i;
        }
        return sum == no;
    }

    private boolean isPrime(int no) {
        if (no < 2) return false;
        for (int i = 2; i <= no / 2; i++) {
            if (no % i == 0) return false;
        }
        return true;
    }
}

///////////////////////////////////////////////////////////////////////
//
// Class       : DoublyLL
// Description : Generic Doubly Linear Linked List with extra logic [from Program647b.java]
//
///////////////////////////////////////////////////////////////////////
class DoublyLL<T extends Number> {
    private class Node {
        T data;
        Node next, prev;
        Node(T value) { data = value; next = null; prev = null; }
    }

    private Node first, last;
    private int count;

    public DoublyLL() {
        first = last = null;
        count = 0;
    }

    public void insertFirst(T value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        count++;
    }

    public void insertLast(T value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        count++;
    }

    public void deleteFirst() {
        if (first == null) return;
        if (first == last) {
            first = last = null;
            count--;
            return;
        }
        first = first.next;
        first.prev = null;
        count--;
    }

    public void deleteLast() {
        if (first == null) return;
        if (first == last) {
            first = last = null;
            count--;
            return;
        }
        last = last.prev;
        last.next = null;
        count--;
    }

    public void display() {
        Node temp = first;
        while (temp != null) {
            System.out.print("| " + temp.data + " | <-> ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int count() {
        return count;
    }

    ////////////////////////// Extra logic //////////////////////////
    public void displayPerfect() {
        System.out.print("Perfect Numbers: ");
        Node temp = first;
        while (temp != null) {
            if (isPerfect(temp.data.intValue())) System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void displayPrime() {
        System.out.print("Prime Numbers: ");
        Node temp = first;
        while (temp != null) {
            if (isPrime(temp.data.intValue())) System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    
    public void sumOfDigits() {
        System.out.print("Sum of digits: ");
        Node temp = first;
        while (temp != null) {
            int num = temp.data.intValue(), sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            System.out.print(sum + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void reverseDigits() {
        Node temp = first;
        while (temp != null) {
            int num = temp.data.intValue(), rev = 0;
            while (num != 0) {
                rev = rev * 10 + num % 10;
                num /= 10;
            }
            temp.data = (T) Integer.valueOf(rev);
            temp = temp.next;
        }
    }

    ////////////////////////// Helper methods //////////////////////////
    private boolean isPerfect(int no) {
        int sum = 0;
        for (int i = 1; i <= no / 2; i++) {
            if (no % i == 0) sum += i;
        }
        return sum == no;
    }

    private boolean isPrime(int no) {
        if (no < 2) return false;
        for (int i = 2; i <= no / 2; i++) {
            if (no % i == 0) return false;
        }
        return true;
    }
}


///////////////////////////////////////////////////////////////////////
//
// Class       : SinglyCLL
// Description : Generic Singly Circular Linked List with extra logic [from Program647a.java]
//
///////////////////////////////////////////////////////////////////////
class SinglyCLL<T extends Number> {
    private class Node {
        T data;
        Node next;
        Node(T value) { this.data = value; this.next = null; }
    }

    private Node first;
    private Node last;
    private int count;

    public SinglyCLL() {
        first = null;
        last = null;
        count = 0;
    }

    public void insertFirst(T value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = last = newNode;
            last.next = first;
        } else {
            newNode.next = first;
            first = newNode;
            last.next = first;
        }
        count++;
    }

    public void insertLast(T value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = last = newNode;
            last.next = first;
        } else {
            last.next = newNode;
            last = newNode;
            last.next = first;
        }
        count++;
    }

    public void display() {
        if (first == null) return;
        Node temp = first;
        do {
            System.out.print("| " + temp.data + " | -> ");
            temp = temp.next;
        } while (temp != first);
        System.out.println();
    }

    public int count() {
        return count;
    }
    
    ////////////////////////// Extra logic //////////////////////////
    public void displayPerfect() {
        System.out.print("Perfect Numbers: ");
        if (first == null) return;
        Node temp = first;
        do {
            if (isPerfect(temp.data.intValue())) System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != first);
        System.out.println();
    }

    public void displayPrime() {
        System.out.print("Prime Numbers: ");
        if (first == null) return;
        Node temp = first;
        do {
            if (isPrime(temp.data.intValue())) System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != first);
        System.out.println();
    }

    public void sumOfDigits() {
        System.out.print("Sum of digits: ");
        if (first == null) return;
        Node temp = first;
        do {
            int num = temp.data.intValue();
            int sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            System.out.print(sum + " ");
            temp = temp.next;
        } while (temp != first);
        System.out.println();
    }
    
    public void reverseDigits() {
        if (first == null) return;
        Node temp = first;
        do {
            int num = temp.data.intValue();
            int rev = 0;
            while (num != 0) {
                rev = rev * 10 + num % 10;
                num /= 10;
            }
            temp.data = (T) Integer.valueOf(rev);
            temp = temp.next;
        } while (temp != first);
    }
    
    ////////////////////////// Helper methods //////////////////////////
    private boolean isPerfect(int no) {
        int sum = 0;
        for (int i = 1; i <= no / 2; i++) {
            if (no % i == 0) sum += i;
        }
        return sum == no;
    }

    private boolean isPrime(int no) {
        if (no < 2) return false;
        for (int i = 2; i <= no / 2; i++) {
            if (no % i == 0) return false;
        }
        return true;
    }
}

///////////////////////////////////////////////////////////////////////
//
// Class       : DoublyCLL
// Description : Generic Doubly Circular Linked List with extra logic [from Program647a.java]
//
///////////////////////////////////////////////////////////////////////
class DoublyCLL<T extends Number> {
    private class Node {
        T data;
        Node next;
        Node prev;
        Node(T value) { this.data = value; this.next = null; this.prev = null; }
    }

    private Node first;
    private Node last;
    private int count;

    public DoublyCLL() {
        first = null;
        last = null;
        count = 0;
    }

    public void insertFirst(T value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = last = newNode;
            first.prev = last;
            last.next = first;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
            last.next = first;
            first.prev = last;
        }
        count++;
    }

    public void insertLast(T value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = last = newNode;
            first.prev = last;
            last.next = first;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
            last.next = first;
            first.prev = last;
        }
        count++;
    }

    public void display() {
        if (first == null) return;
        Node temp = first;
        do {
            System.out.print("| " + temp.data + " | <=> ");
            temp = temp.next;
        } while (temp != first);
        System.out.println();
    }

    public int count() {
        return count;
    }

    ////////////////////////// Extra logic //////////////////////////
    public void displayPerfect() {
        System.out.print("Perfect Numbers: ");
        if (first == null) return;
        Node temp = first;
        do {
            if (isPerfect(temp.data.intValue())) System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != first);
        System.out.println();
    }

    public void displayPrime() {
        System.out.print("Prime Numbers: ");
        if (first == null) return;
        Node temp = first;
        do {
            if (isPrime(temp.data.intValue())) System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != first);
        System.out.println();
    }
    
     public void sumOfDigits() {
        System.out.print("Sum of digits: ");
        if (first == null) return;
        Node temp = first;
        do {
            int num = temp.data.intValue();
            int sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            System.out.print(sum + " ");
            temp = temp.next;
        } while (temp != first);
        System.out.println();
    }

    public void reverseDigits() {
        if (first == null) return;
        Node temp = first;
        do {
            int num = temp.data.intValue();
            int rev = 0;
            while (num != 0) {
                rev = rev * 10 + num % 10;
                num /= 10;
            }
            temp.data = (T) Integer.valueOf(rev);
            temp = temp.next;
        } while (temp != first);
    }

    ////////////////////////// Helper methods //////////////////////////
    private boolean isPerfect(int no) {
        int sum = 0;
        for (int i = 1; i <= no / 2; i++) {
            if (no % i == 0) sum += i;
        }
        return sum == no;
    }

    private boolean isPrime(int no) {
        if (no < 2) return false;
        for (int i = 2; i <= no / 2; i++) {
            if (no % i == 0) return false;
        }
        return true;
    }
}


///////////////////////////////////////////////////////////////////////
//
// Class       : BST
// Description : Generic implementation of Binary Search Tree [from Program647d.java]
//
///////////////////////////////////////////////////////////////////////
class BST<T extends Comparable<T>> {
    private class Node {
        T data;
        Node left, right;
        Node(T value) {
            data = value;
            left = right = null;
        }
    }

    private Node root;

    public BST() {
        root = null;
    }

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, T value) {
        if (node == null) return new Node(value);
        if (value.compareTo(node.data) < 0)
            node.left = insertRec(node.left, value);
        else
            node.right = insertRec(node.right, value);
        return node;
    }

    public void inorder() {
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + " ");
            inorderRec(node.right);
        }
    }
    
    public void preorder() {
        System.out.print("Preorder: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }
    
    public void postorder() {
        System.out.print("Postorder: ");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node node) {
        if (node != null) {
            postorderRec(node.left);
            postorderRec(node.right);
            System.out.print(node.data + " ");
        }
    }

    public boolean search(T value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node node, T value) {
        if (node == null) return false;
        if (value.equals(node.data)) return true;
        if (value.compareTo(node.data) < 0)
            return searchRec(node.left, value);
        else
            return searchRec(node.right, value);
    }
}

///////////////////////////////////////////////////////////////////////
//
// Class       : Sorting
// Description : Implementation of Bubble Sort and Selection Sort [from Program647d.java]
//
///////////////////////////////////////////////////////////////////////
class Sorting {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[minIndex]) minIndex = j;
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}

///////////////////////////////////////////////////////////////////////
//
// Class       : Searching
// Description : Implementation of Linear Search and Binary Search [from Program647d.java]
//
///////////////////////////////////////////////////////////////////////
class Searching {
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) return mid;
            if (arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}


/////////////////////////////////////////////////////////////////////////////
//
// Main Class to demonstrate all data structures and algorithms
//
/////////////////////////////////////////////////////////////////////////////
public class Generalised_Data_Structures_library
{
    public static void main(String[] args) 
    {
        // Testing QueueX
        QueueX<Integer> queue = new QueueX<>();
        System.out.println("Testing Queue:");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();
        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();
        
        // Testing StackX
        StackX<Integer> stack = new StackX<>();
        System.out.println("\nTesting Stack:");
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.display();
        System.out.println("Popped: " + stack.pop());
        stack.display();
        
        // Testing SinglyLL
        SinglyLL<Integer> singlyLL = new SinglyLL<>();
        System.out.println("\nTesting Singly Linked List:");
        singlyLL.insertFirst(6);
        singlyLL.insertLast(28);
        singlyLL.insertLast(17);
        singlyLL.insertLast(13);
        singlyLL.display();
        singlyLL.displayPerfect();
        singlyLL.displayPrime();
        singlyLL.sumOfDigits();
        singlyLL.reverseDigits();
        singlyLL.display();
        
        // Testing DoublyLL
        DoublyLL<Integer> doublyLL = new DoublyLL<>();
        System.out.println("\nTesting Doubly Linked List:");
        doublyLL.insertFirst(7);
        doublyLL.insertLast(496);
        doublyLL.insertLast(23);
        doublyLL.display();
        doublyLL.displayPerfect();
        doublyLL.displayPrime();
        doublyLL.sumOfDigits();
        doublyLL.reverseDigits();
        doublyLL.display();
        
        // Testing SinglyCLL
        SinglyCLL<Integer> singlyCLL = new SinglyCLL<>();
        System.out.println("\nTesting Singly Circular Linked List:");
        singlyCLL.insertFirst(11);
        singlyCLL.insertLast(12);
        singlyCLL.insertLast(2);
        singlyCLL.display();
        singlyCLL.displayPerfect();
        singlyCLL.displayPrime();
        singlyCLL.sumOfDigits();
        singlyCLL.reverseDigits();
        singlyCLL.display();
        
        // Testing DoublyCLL
        DoublyCLL<Integer> doublyCLL = new DoublyCLL<>();
        System.out.println("\nTesting Doubly Circular Linked List:");
        doublyCLL.insertFirst(18);
        doublyCLL.insertLast(6);
        doublyCLL.insertLast(17);
        doublyCLL.display();
        doublyCLL.displayPerfect();
        doublyCLL.displayPrime();
        doublyCLL.sumOfDigits();
        doublyCLL.reverseDigits();
        doublyCLL.display();
        
        // Testing BST
        BST<Integer> bst = new BST<>();
        System.out.println("\nTesting Binary Search Tree:");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.inorder();
        bst.preorder();
        bst.postorder();
        System.out.println("Search 60: " + bst.search(60));
        System.out.println("Search 100: " + bst.search(100));
        
        // Testing Sorting
        int[] arr1 = {5, 2, 9, 1, 5, 6};
        System.out.println("\nTesting Bubble Sort:");
        Sorting.bubbleSort(arr1);
        System.out.println("Sorted: " + Arrays.toString(arr1));
        
        int[] arr2 = {3, 8, 2, 7, 4};
        System.out.println("\nTesting Selection Sort:");
        Sorting.selectionSort(arr2);
        System.out.println("Sorted: " + Arrays.toString(arr2));
        
        // Testing Searching
        int[] arrSearch = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("\nTesting Linear Search:");
        System.out.println("Position of 4: " + Searching.linearSearch(arrSearch, 4)); //Should return 3
        System.out.println("Position of 10: " + Searching.linearSearch(arrSearch, 10)); //Should return -1
        
        System.out.println("\nTesting Binary Search:");
        System.out.println("Position of 5: " + Searching.binarySearch(arrSearch, 5)); //Should return 4
        System.out.println("Position of 20: " + Searching.binarySearch(arrSearch, 20)); //Should return -1
    }
}
