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

* Demo

## Variations

* In practice: we don't write our own data structures (unless we *have* to)
  * In Java: `List<T>`, `ArrayList<T>` and `LinkedList<T>`
* You could also keep track of a *tail* element in a linked list
* You can keep track of a "previous" value in a linked list: doubly linked list
  * Advantage: you can traverse both directions
  * Disadvantage: twice as complex to implement
* Circularly linked list the tail points back to the head
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
* Skip Lists: linked list but with *some* random access (keep track of several nexts)

# List-Based Data Structures

## Stacks

* Stacks are *restricted access data structures*
* Collections (lists, sets, etc.) are "unstructured" data structures: they just hold stuf
* You can *impose* a structure on a list: sort it!
* Alternative: you can impose a structure on a data structure thorugh its behavior: its methods
* We will *restrict* access to *how* you interact with the data structure
* Basic operations:
  * `push`: add an element to the "top" of the stack
  * `pop`: remove (and return) the top element of the stack
* Stacks are a LIFO = Last-In First-Out data structure
* This is ensured behavior through the public interface
  * You are *generally* not allowed to arbitrarily insert elements anywhere you want
  * Implementation: use *composition* (instead of inheritance) to leverage existing data structures

### Secondary Functionality

* Method to determine the *size* of the stack
* A method to determine if it is empty
* A method to determine if it is "full"
* A method to "peek" at the top element: return the top element but do not remove it

## Queues

* A "queue" is a line: FIFO = First in First Out
* Two operations:
  * `enqueue`: add an element to the end of the queue
  * `dequeue`: remove/return the element at the front of the queue
* Similar considerations:
  * What happens if you enqueue to a "full" queue
  * What happens if you dequeue from an empty queue?
  * Does it make sense to implement a "peek" method

## In Practice

* You don't roll your own implementation unless you have to
* You use a built-in implementation
* List: `List<>`, `ArrayList<>`, `LinkedList<>`
* Stacks/Queues: Java provides a single implementation: `Deque<>` ("deck")
  * It can be treated as *either* a stack or queue or "double ended" queue: you can work from both ends (add/remove from both ends)
* This opens up the possibility of pushing/popping from *either* end
  * Why: enqueue and dequeue at the front and enqueue at the back?  You would want to allow "high priority line jumpers"
  * Why: would you want to push/pop at one end, but also pop at the other: you want to keep track of "undo" operations (but set a limit)

### Other variations

* You could support arbitrary removal of elements (line leavers)
* You could allow arbitrary insertion of elements, say according to some *priority*
* Priority Queue: allows you to define "priorities" and insert elements at a location such that the elements are served highest priority first

```text












```
