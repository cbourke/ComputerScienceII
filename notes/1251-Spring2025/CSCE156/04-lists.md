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
* What is `MyArrayList<T>` good for? EVERYTHING! :)

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
  * Skip Lists: linked list but with *some* random access (keep track of several nexts)

# Stacks

* Stacks are *restricted access data structures*
* Collections (lists, sets, etc.) are "unstructured": they just hold stuff
* YOu can *impose* a structure on (say) lists by sorting them: you rearrange the list so that they are "in order"; that's imposed on the data structure's *state*
* Alternative: create structure through an object/data structure's *behavior*: its publicly available interface (public methods)
* A stack does this by restricting how you interface with it:
* Basic operations:
  * `push`: add an element to the "top" of the stack
  * `pop`: remove (and return) the top element of the stack
* STacks are a LIFO = Last-In First-Out data structure
* This is ensured behavior through the public interface
* You are *generally* not allowed to arbitrarily insert elements anywhere you want
* Implementation: use composition to leverage existing data structures

## Secondary Functionality

* A method to determine the size
* A method to "peek" at the top element: return the top element but do not remove it

# Queues

* A "queue" is a line: FIFO = First in First Out
* Its a line:  you get in line at the back, the next person helped is the person at the front
* Two operations:
  * `enqueue`: add an element to the end of the queue
  * `dequeue`: remove/return the element at the front of the queue

## General Options

* You *could* support the arbitrary removal of elements (line leavers)
* You could allow arbitrary insertions: priority (VIPs, line jumpers)
* Priority Queue: allows you to define "priorities" and insert elements at a location such that the elements are served highest priority first
* You could support (both for queues and stacks): a limited capacity
  * What happens when you enqueue/push onto a full queue/stack?
  * What happens when you dequeue/pop from an empty queue/stack


## In Practice

* You don't roll your own implementation unless you have to
* You use an implmeentation off the shelf
* Lists: `List<>`, `ArrayList<>`, `LinkedList<>`
* Stacks/Queues: Java provides a single interface that gives you everything: `Deque<T>` ("deck")
  * It can be treated as *either* a stack or queue or "double ended" queue: you can work from both ends (add/remove from both ends)
* WHy work at both ends:
  * push/pop at one end and "pop" at the other
* Both `LinkedList<>` and `ArrayDeque<>` are implementations of a `Deque<>`: they are both efficient
* But what about our implementations: which one is best for stack/queue? `MyLinkedList<>`, `MyArrayList<>`
  * `MyLinkedList<>`:  If you want to add/remove from the head/tail (as long as you keep track of both): how efficient is this? It only requires a constant number of operations
  * If you want to add/remove with an array: You end up shifting $n$ elements down, if $n$ is large: $n = 1M$ then this is a LOT

```text












```
