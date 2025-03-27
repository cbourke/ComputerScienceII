# Lists & List-based Data Structures
## CSCE 156H - Spring 2025

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays
* Leverages OOP principles (encapsulation, abstraction, inheritance, polymorphism)
* Iterative Design & Testing

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
  * C = Create: insert or add things to the list
  * R = Retrieve: get something already in the list (first, i-th, last element, the element that matches some criteria)
  * U = Remove and Replace
  * D = Destroy: delete something in the list

## Array-based lists

* Design the *interface* (the `public` methods, no necessarily a Java `interface`) first
* Design the *tests* next (TDD = Test Driven Development)
* Implement the interface
* Debug as necessary
* Update design, tests, implementation as you go along

## Secondary Functionality

* Other methods/functionality might be useful:
  * Bulk add/remove methods (`addAll()`, `removeAll()`)
  * Append, insert, merge two lists together, etc.
  * Copy constructor to allow for deep copies of your list
  * Compute a sublist: given start/end indices
  * Add functionality to *shrink* the list if necessary

## Improvements

* What is `MyIntegerArrayList` good for? ONLY integers :(
* What is `MyArrayList<T>` good for? EVERYTHING :)

# Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node:
  * Contains a piece of data (`T`)
  * A reference to the `next` node
  * Any convenience method(s)
* The linked list itself **only** has a reference to the `head` node
* The basic operation: a *traversal*: starting at the head, you traverse next, next, next, until you get to the node you want
* Consequence: there is no *random access*

```text












```
