# Lists & List-based Data Structures
## CSCE 156H - Spring 2023

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays
* Leverages OOP principles (encapsulation, abstraction, polymorphism, inheritance)

### Arrays are "Bad"

* Once created, the size is essentially fixed
* We have to do a lot of bookkeeping to keep track of which indices have been "used" and which are free
* Deleting or removing elements may leave "gaps"

### Solution: List data structure

* Automatically grow and shrink without us having to worry about the details
* We want the ability to:
  * Add stuff
  * Retrieve stuff
  * Remove stuff
  * Modify stuff
* We want all the details to be *encapsulated* inside the data structure and not have to worry about them
* We want an abstraction: an interface that specifies how to *use* the list, not necessarily how it works

## Linked Lists
  * No underlying array, instead elements are stored inside of individual "nodes"
  * Each node references its *element* and the *next* node
  * Each node is "chained" or "linked" together
  * Each basic operation involves *traversing* the list

  * Variations
    * Doubly linked list: you keep track of the `next` and the `previous`
    * You can also keep track of the tail for easy access to the end of the list
    * Circularly linked lists
    * Unrolled linked lists: each node may contain an *array* of elements instead of just one
    * Skip list: linked list but with *some* random access (keep track of several nexts)

### Stacks

* Stacks are a LIFO (Last-In, First-Out) data structure:
  * `push`: add something to the "top" of the stack
  * `pop`: remove (and retrieve) the "top" element
* Stacks are a *restricted access data structure*
* Collections (lists, sets): these are "unstructured": they just hold stuff
* You can *impose* a structure on a list by sorting it!
* Sorting imposes an ordering on the *state* of the list
* A stack's structure is in its behavior: you are not allowed arbitrary access to its structure
* *Restricted Access Data Structures* impose an ordering on a collection's *behavior*
* We really like structure because it can be exploited for more efficient operations
* Optional Operations:
  * `peek`: retrieve but do not remove the top element
  * Option: what happens when you pop from an empty stack
  * Option: do you impose a capacity?
  * Option: how do you implement a stack?

### Queues

* FIFO = First In First Out
* A restricted access data structure
  * `enqueue`: insert something at the "end" of the line
  * `dequeue`: remove the element at the "front" of the line
* Optional: `peek` operation (return but do not remove the element at the top)
* Option: what happens when you dequeue from an empty queue
* Option: do you impose a capacity?
* Option: how do you implement a queue?

### Variations

* YOu *could* support the arbitrary (index-based) removal of elements
* You could allow arbitrary insertions: priority (VIPs, line jumpers)
* A Priority Queue allows you to define priorities for each element, the next one that gets dequeued is the one with the "highest" priority
* A more general data structure that can simulate both a stack and a queue is a `Deque` ("deck"): a double ended queue
  * You can insert and remove from *both* ends
  * If you work only at one end (adding/removing): stack
  * If you remove from one and add to the other: queue
  * 3 operations: insert at one end, remove from both?
  * Application: keeping track of operations and undoing them but limiting the number (ejecting the oldest one)
  * LRU = Least Recently Used Cache

### Applications

#### Stack

* program stack/call stack: keep track of which functions have been called and in which order to easily return to the calling function
* Storing breadcrumbs
  * Graph algorithms: DFS = Depth First Search
  * Simulation of recursion
  * Backtracking/optimization algorithms
  * Shunting Yard algorithm

#### Queues

* Buffers
* Server requests or a "Consumer Producer" scenario
* Resource Pools/connection pools


```text













```
