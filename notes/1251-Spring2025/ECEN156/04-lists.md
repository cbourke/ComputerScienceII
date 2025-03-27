# Lists & List-based Data Structures
## ECEN 156 - Spring 2025

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

* Bulk operations: `addAll`, `clear`
* Get a new (deep) copy that is a sublist (elements from `start` to `end` indices), etc.
* Merge two lists together to get a new one
* Copy constructor: takes another list and creates a deep copy of the list
* Convenience functions:
  * `contains()`
  * `indexOf()`
  * `update()`: replace a given element with another

## Improvements

* What is `MyIntegerArrayList` good for? Only `int` types :(
* We want a list that can be applicable to ALL types

# Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node has:
  * A piece of data
  * A reference to the next node in the list
* All the nodes are chained together
* The first node is the "head"
* The last node is the "tail" (whose "next" is `null`)
* Basic operation: list/node traversal
* You always start at the head, traverse node by node
* Thus you have no "random access"

```text












```
