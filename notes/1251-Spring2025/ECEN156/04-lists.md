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

## Improvements

* Leverage *inheritance* to reduce redundancy *and* have a common interface
* More testing

### Variations

* you can also keep track of a tail as well as a head
* You can support *doubly linked lists*: each node has a next and a *previous*
  * Advantage: you can traverse in both directions
  * Disadvantage: twice as much code
* You can write *circularly linked lists*
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
* Skip list: linked list but with *some* random access (keep track of several nexts)

# Stacks

* Stacks are a *restricted access data structure*
* Collections (lists, sets, etc.) are "unstructured": they just hold stuff
* You can *impose* a structure on (say) lists by sorting them: you rearrange the list so that they are "in order"; that's imposed on the data structure's *state*
* Alternative: create structure through an object/data structure's *behavior*: its publicly available interface (public methods)
* A stack does this by restricting how you interface with it: two basic operations
  * `push`: adds an element to the "top" of the stack
  * `pop`: removes and returns the element at the "top" of the stack
* Stacks are LIFO = Last-In First-Out data structures: the last one you pushed in is the first one you pop out
* This is ensured behavior through the public interface
* Secondary Functionality:
  * size method
  * `isEmpty()` method
  * `isFull()` method IF your stack is size limited
  * Functionality for pushing onto a full stack and popping off an empty stack
  * `peek()`: returns but does not remove the top element
* You are (generally) NOT allowed to interact arbitrarily with the stack

# Queues

* A queue is a *line*: you get into the back of the line, the person at the front of the line is served next
* A queue is a FIFO data structure: First-In First-Out
* No line jumping! No cutting in line
* A restricted access data structure
  * `enqueue`: insert something at the "end" of the line
  * `dequeue`: remove and return the element at the "front" of the line
* Same exact considerations and options as before

# In Practice

* YOu generally don't roll your own: you use what is built into the language or library
* `List<>, ArrayList<>, LinkedList<>`
* Stacks/Queues: `Deque` ("deck"): double-ended queue
  * You can push/pop from both ends of the deque
  * It allows you to treat it as a stack AND a queue with one single implmentation! 

```text












```
