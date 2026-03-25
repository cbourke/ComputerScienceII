# Lists & List-based Data Structures
## CSCE 156H - Spring 2026

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays
* Leverages OOP principles (encapsulation, abstraction, inheritance, polymorphism)

### Arrays are "Bad"

```java
int arr[] = new int[10];
```

* Once they are created, the size is *FIXED*: it cannot be changed
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
  * Bulk add/remove methods: `addAll()` `removeAll()`
  * Sort?!
  * Append a list, insert or merge entire lists
  * `subList(i,j)`: return a new list from `i` (inclusive) to `j` (exclusive)
  * Copy constructor: makes deep copies of the list
  * Add to start/end
  * Functionality to shrink the array if too much space is wasted

## Improvements

* What is `IntegerArrayList` good for?  Only integers >:(
* What is `MyArrayList<T>` good for? EVERYTHING! :)

# Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node contains:
  * An element, and
  * a reference to the "next" node
* It works by linking one node to the next node in a *chain*
* The first node is the "head" of the list
* The last node is the "tail" of the list
* There is no array
  * There is no random access: to get the `i`-th element requires *traversing* the list
* Basic operation:
  * Traversing the list node by node starting at the head

## Variations

* You can add support for a *tail* element and a *previous* reference and make a "doubly linked list"
  * Advantage: you can traverse both directions
  * Disadvantage: twice as complex to implement
* Circularly linked list the tail points back to the head
  * You can use this for any circular data structures
  * Token ring network (example)
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
  * $m$ can be tuned to be at most one page size in memory
  * Potential for improvements in performance
  * Skip Lists: linked list but with *some* random access (keep track of several nexts) (local train vs express trains)

# Stacks

* Stacks are *restricted access data structures*
* Collections (lists, sets, etc.) are "unstructured": they just hold stuff
* YOu can *impose* a structure on (say) lists by sorting them: you rearrange the list so that they are "in order"; that's imposed on the data structure's *state*
* Alternative: create structure through an object/data structure's *behavior*: its publicly available interface (public methods)
* A stack does this by restricting how you interface with it:
* Basic operations:
  * `push` add an element to the "top" of the stack
  * `pop` remove (and return) the top element of the stack
* Stacks are a LIFO = Last-In First-Out data structure
* You are *generally* not allowed to arbitrarily insert elements anywhere you want
* Implementation: how?

# Queues


```text





```
