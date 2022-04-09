# Lists & List-based Data Structures
## CSCE 156 - Spring 2021

### Introduction/Overview

* Motivation: Dynamic Data Structures (lists, sets, maps) are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

```java
int n = 10;
Integer numbers[] = new Integer[n];
```

* Once created, the size is fixed
* Solution: use a *dynamic list* data structure:
  * Allows you to grow and shrink automatically
  * You can add things without worrying about the details (OOP)
  * Want a way to add things
  * Want a way to remove things
  * Want a way to retrieve things
  * Want a way to modify things
* Other functionality:
  * A way to clear the list
  * A way to shrink the list if its capacity is too much (is being wasted)
  * A way to add multiple elements (batch add)
  * A way to copy one list to another (copy constructor!)
  * Find a particular element (search)
  * insert at the beginning

* Linked Lists
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

* Stacks are a *restricted access data structure*
* Collections (lists, sets): these are a bit "unstructured" - they just hold stuff
* Lists are ordered but not *sorted*
* Sorting a list *imposes* an order on a collection's *state*
* *Restricted Access Data Structures* impose an ordering on a collection's *behavior*
* We really like structure because it can be exploited for more efficient operations

* A *stack* is a LIFO = Last-In First-Out data structure: we only allow access to "one end" of the stack (top)
  * You only have two basic operations
  * `push`: add something to the top of the stack
  * `pop`: retrieve and remove the element at the top of the stack
  * Optional: `peek` operation (return but do not remove the element at the top)
  * Option: what happens when you pop from an empty stack
  * Option: do you impose a capacity?
  * Option: how do you implement a stack?

## Queue

* A queue is a line: FIFO = First-In, First-Out data structure
* Two basic operations:
  * `Enqueue` - insert something to the end of the line (tail)
  * `Dequeue` - retrieve and remove the element at the head of the line

## Variations

* You *could* allow arbitrary removal/insert/retrieval
* You could allow line jumpers: priority queue
* Deque ("deck"): allows both insert and remove operations from *both ends* ("double ended queue")
  * Allows you to implement a stack by ignoring one end
  * It allows you to implement a queue ignoring one operation at both ends
  * Undo features or LRU caches = Least Recently Used Cache

## In Practice

* Don't roll your own!
* You generally do not want to implement your own stack, queue, priority queue, etc.
* Java: `Deque<T>`

### Applications

#### Stack

* call stacks
* Keeping track of "breadcrumbs"
* Graph algorithms: DFS = Depth First Search
* Simulate recursion
* Backtracking/optimization algorithms
* Shunting Yard algorithm

#### Queues

* Buffers
* Server requests or a "Consumer Producer" scenario
* Resource Pools

```text





```
