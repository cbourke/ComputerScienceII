# Lists & List-based Data Structures
## CSCE 156 - Spring 2026

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
  * C = create: add things, insert things into the list
  * R = retrieve: get an element
  * U = Update: remove/add an element or modify an element
  * D = Delete: remove elements from the list
* All of the details are going to be encapsulated inside the data structure
* We only want to use the data structure *abstractly*

## Array-based lists

* Design the *interface* (the `public` methods) first
* Design the *tests* next (TDD = Test Driven Development)
* Implement the interface
* Update design, tests, implementation as you go along

## Secondary Functionality

* Other methods/functionality might be useful:
  * Bulk add/remove methods (`addAll(), clear()`)
  * Find element, contains element
  * Copy constructor: allows you to create a *deep* copy of your list
  * Add to the beginning/end of the list
  * Add functionality to "shrink" the list if it is "too big"
  * `subList(i, j)`: a method to return a new list that is a sublist from `i` to `j` (inclusive/exclusive)

## Improvements

* What is `MyIntegerArrayList` good for?  Only integers :(
* What is `MyArrayList<T>` good for? EVERYTHING! :)

# Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node contains:
  * An element, and
  * a reference to the "next" node
* It works by linking one node to the next node in a *chain*
* The first node is the "head" of the list
* The last node is the "tail" of the list
* There is no array!
  * Consequence: there is no random access
  * Getting to the `i`-th element costs you `i` operations

## Design, Implementation and Testing

```text






```
