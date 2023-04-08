# Lists & List-based Data Structures
## ECEN 194 - Spring 2023

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays
* Leverages OOP principles (encapsulation, abstraction, polymorphism)

```java
int n = 10;
Integer arr[] = new Integer[n];
```

### Array-Based Lists

* Once created, the size is *fixed*, it does not grow it does not shrink
* Solution: use a *dynamic list* data structure:
  * Automatically grows/shrinks with new elements
  * You can add elements without worrying about the details (abstraction)
* Demonstration: create your own array-based list
  * Want a way to add things
  * Want a way to remove things
  * A way to retrieve things
  * A way to modify elements in the list
* Other functionality:
  * A way to clear the list
  * A way to shrink the list if its capacity is too much (is being wasted)
  * A way to add multiple elements (batch add)
  * A way to copy one list to another (copy constructor!)
  * Find a particular element (search)
  * insert at the beginning

### Linked Lists

* There is no underlying array, instead elements are stored inside of individual "nodes"
* Each node references an element and the *next* node
* Each node is thus "chained" together or "linked"
* Basic operations involve *traversing* the list

#### Variations

  * Doubly linked list: you keep track of the `next` and the `previous`
  * You can also keep track of the tail for easy access to the end of the list
  * Circularly linked lists
  * Unrolled linked lists: each node may contain an *array* of elements instead of just one
  * Skip list: linked list but with *some* random access (keep track of several nexts)

### Stacks

* Stacks are a *restricted access data structure*
* Collections (lists, sets): these are a bit "unstructured" - they just hold stuff
* Lists are ordered but not *sorted*
* Sorting a list *imposes* an order on a collection's *state*
* *Restricted Access Data Structures* impose an ordering on a collection's *behavior*
* "Ordering": LIFO = Last-In, First-Out
  * You only have two basic operations
  * `push`: add something to the top of the stack
  * `pop`: retrieve and remove the element at the top of the stack
  * Optional: `peek` operation (return but do not remove the element at the top)
  * Option: what happens when you pop from an empty stack
  * Option: do you impose a capacity?
  * Option: how do you implement a stack?

## Queues

* A FIFO = First in first out data structure
* A restricted access data structure
  * `enqueue`: insert something at the "end" of the line
  * `dequeue`: remove the element at the "front" of the line
* Optional: `peek` operation (return but do not remove the element at the top)
* Option: what happens when you dequeue from an empty queue
* Option: do you impose a capacity?
* Option: how do you implement a queue?

### Variations

* You *could* support the arbitrary removal of elements (line leavers)
* You could allow arbitrary insertions: priority (VIPs, line jumpers)
* A Priority Queue allows you to define priorities for each element, the next one that gets dequeued is the one with the "highest" priority
* A more general data structure that can simulate both a stack and a queue is a `Deque` ("deck"): a double ended queue
  * You can insert and remove from both ends
  * Application: keeping track of operations and undoing them but limiting the number (ejecting the oldest one)
  * LRU = Least Recently Used Cache

## In Practice

* Don't create your own!
* Generally you use what is provided by the language or library
* Java: `Deque<T>`

### Applications

#### Stack

* Program stacks/call stacks
* Storing breadcrumbs: storing where you came from
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
