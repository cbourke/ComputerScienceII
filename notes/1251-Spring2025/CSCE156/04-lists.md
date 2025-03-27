# Lists & List-based Data Structures
## CSCE 156 - Spring 2025

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays
* Leverages OOP principles (encapsulation, abstraction, inheritance, polymorphism)

### Arrays are "Bad"

```java
int arr[] = new int[10];
```

* once they are created, the size is *FIXED*: it cannot be changed
* We have to do a lot of bookkeeping to keep track of which indices have been "used" and which are free
* Deleting or removing elements may leave "gaps"

Solution: design and implement a dynamic list data structure

* Automatically resizes (grows/shrinks) based on the elements you insert, add, or delete (we don't have to worry about the details: abstraction/encapsulation)
* Functionality:
  * C = Create: add things, insert things, etc.
  * R = Retrieve: get an element
  * U = Update: remove/add an element, modify elements
  * D = Delete: remove elements
* All of the details are going to be encapsulated inside the data structure
* We only want to use the data structure *abstractly*

## Array-based lists

* Design the *interface* (the `public` methods) first
* Design the *tests* next (TDD = Test Driven Development)
* Implement the interface
* Update design, tests, implementation as you go along

## Secondary Functionality

* Other methods/functionality might be useful:
  * Bulk add/remove methods (addAll, clear/removeAll)
  * Append, insert, merge
  * Copy constructor: allows you to create a new *deep* copy of the list
  * Add to the beginning/end of the list
  * Add the functionality to shrink the array if necessary
  * `subList`: returns a new sublist from an index `i` to index `j`

## Improvements

* What is `MyIntegerArrayList` good for?  Only integers :(
* What is `MyArrayList<T>` good for? EVERYTHIGN! :)

# Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node contains:
  * An element, and
  * a reference to the "next" node
* It works by linking one node to the next node in a *chain
* The first node is the "head" of the list
* The last node is the "tail" of the list
* There is no array
  * There is no random access: to get the `i`-th element requires *traversing* the list
* Basic operation:
  * Traversing the list node by node starting at the head





```text












```
