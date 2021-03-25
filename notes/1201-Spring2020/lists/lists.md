# List and List-based Data Structures 
## CSCE 156 - Spring 2020

### Introduction/Overview

* Motivation: Dynamic Data Structures are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

```java
int n = 10;
Integer numbers[] = new Integer[n];
```

* Once created, the size is fixed
* Solution: use a *dynamic list* data structure:
  * Automatically grow and shrink 
  * You can add things without having to worry about the details
* First iteration: create a list that can hold integers
* Problem: our first iteration was only for integers!
* Goal: create one list that can hold ANY type

<!-- 
  
  addToEnd
  print
  incorporate size
  
  -->
* Problem:
  * Adding an element to the start of an array-based list requires shifting elements down
  * If you had 1 million elements, this is 1 million operations
  * Adding an element at the end is more or less "free" unless you have to increase the capacity
  * Every time you resize: you end up performing N operations where N is the current size of the list
  * Some things are "cheap": retrieving an element at a particular index: because of random access

* Alternative Solution: A linked list
  * Each element is stored in a `Node`
  * Each node stores a reference to the next node in the list
  * Each node thus is linked together or "chained" to each other
  * Inserting a new node at the *head* of a linked list becomes fairly simple and "free" (efficient)
  * Other operations: inserting at an arbitrary index (or at the end) or retrieving an element become expensive, O(n)

* Demonstration:
  * insert at head
  * convenience methods: `size(), toString()` 
  * remove from head
  * insert at end (take care to keep track of previous)
  * index-based insert
  * retrieval
  * removal (index-based)
  
* Variations
  * Doubly Linked Lists
  * Circularly Linked Lists
  * Unrolled Linked Lists
  * Skip Lists
  
  
  
  
  
  
  
  