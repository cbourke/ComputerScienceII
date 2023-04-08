# Lists & List-based Data Structures
## CSCE 156 - Spring 2023

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays
* Leverages OOP principles (encapsulation, abstraction, inheritance, polymorphism)

### Arrays are "Bad"

* Once created, the size is essentially fixed
* We have to do a lot of bookkeeping to keep track of which indices have been "used" and which are free
* Deleting or removing elements may leave "gaps"

* Solution: creating a dynamic list:
  * Automatically grow/shrink without us having to worry about the details
  * Functionality:
    * Add things
    * Retrieve things
    * Remove things
    * Modify things
  * We want all the details to be *encapsulated* inside the data structure and not have to worry about them
  * We want an abstraction: an interface that specifies how to *use* the list, not necessarily how it works

## Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node references its *element* and the *next* node
* Each node is "chained" or "linked" together
* Each basic operation involves *traversing* the list

## Efficiency Demo

* Recall:
  * Array-based lists have *bad performance* when adding elements to the start
  * Linked-lists *seemed* to have *good* performance when adding elements to the start
* So always use linked lists?
* No: performance simply reverses with each when you change the operation

### Variations

* Doubly linked list: you keep track of the `next` and the `previous`
* You can also keep track of the tail for easy access to the end of the list
* Circularly linked lists
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
* Skip list: linked list but with *some* random access (keep track of several nexts)

### Stacks

* Stacks are a *restricted access data structure*
* Collections (lists, sets): these a bit "unstructured", they just hold stuff
* Lists are ordered but not necessarily *sorted*
* Sorting *imposes* an ordering on the list's *state*
* A stack imposes a structure on the *behavior*
* The basic functionality:
  * `push`: add an element to the "top" of the stack
  * `pop`: remove (and return) the element at the top of the stack
* Optional Functionality:
  * Option: what happens when you pop from an empty stack
  * Do you impose an (optional) capacity?
  * How do you even implement a stack?
  * Optional: `peek` operation (return but do not remove the element at the top)

## Queues

* Is a "line", a FIFO = First In First Out Datastructure
* No line jumping!
* A restricted access data structure
  * `enqueue`: insert something at the "end" of the line
  * `dequeue`: remove the element at the "front" of the line
* Same exact considerations and options as before

## General Options

* You *could* support the arbitrary removal of elements (line leavers)
* You could allow arbitrary insertions: priority (VIPs, line jumpers)
* Priority Queue: allows you to define "priorities" and insert elements at a location such that the elements are served highest priority first
* A more general data structure that can simulate both a stack and a queue is a `Deque` ("deck"): a double ended queue

## In Practice

* Don't create your own!
* Generally you use what is provided by the language or library
* Java: `Deque<T>`

### Applications

#### Stack

* Program stacks/call stacks
* Storing breadcrumbs
* DFS graph algorithms (Depth first search)
* Simulate recursion
* Backtracking/optimization algorithms
* Shunting Yard algorithm

#### Queues

* Buffers
* Server requests or a "Consumer Producer" scenario
* Resource Pools/connection pools

```text













```
