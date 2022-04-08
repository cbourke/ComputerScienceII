# Lists & List-based Data Structures
## CSCE 156 - Spring 2022

### Introduction/Overview

* Motivation: Dynamic Data Structures (lists, sets, maps) are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

### Arrays are "Bad"

* Once created, the size is essentially fixed
* We have to do a lot of bookkeeping to keep track of which indices have been "used" and which are free
* Deleting or removing elements may leave "gaps"
* Solution: a *dynamic list* data structure:
  * Automatically grow and shrink without us having to worry about it
  * We want to be able to:
    * add things
    * retrieve things
    * remove things
  * We want all the details to be *encapsulated* inside the data structure and not have to worry about them
  * We want an abstraction: an interface that specifies how to *use* the list, not necessarily how it works
* Other functionality:
    * A way to clear the list
    * A way to shrink the list if its capacity is too much (is being wasted)
    * A way to add multiple elements (batch add)
    * A way to copy one list to another (copy constructor!)
    * Find a particular element (search)
    * insert at the beginning
* Demonstration:
  * Create an "array-based list" to hold integers
  * Used abstraction to hide the details of how internally the array grew/shrunk to accommodate more elements
  * Used encapsulation to protect the list from arbitrary operations
  * Used inheritance at first to create a sorted list, realized it was bad and instead used composition
  * Used parameterization (polymorphism) to have one list that can hold *any* type

* Drawbacks:
  * Once the capacity is reached in the array, an entirely new copy must be created!
  * This operation requires $n$ copy operations where $n$ is the number of elements in the list!
  * If $n$ is small, so what
  * If $n$ is large, this becomes very expensive

## Alternative: Linked List

* It does not hold elements in an array
* Each element is held in a "node"
* Each node has the element and a reference to the "next" node
* Each node is placed in a "chain" and linked together
* Each basic operation involves *traversing* the list starting at the head

## Variations

* You could possibly keep track of both the `head` and `tail` elements
* Doubly linked lists: you can keep track of a `next` *and* a `previous`
* Circularly linked list: instead of  having a "tail", the "last" element points back to the first
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
* Skip list: linked list, but there are "express lanes"
* Restricted Access Data Structures:
  * Stacks
  * Queues

## Stacks

* Recall: call stacks
* LIFO data structure: Last-in first-out data structure
* Two operations:
  * Push - add an element to the "top" of the stack
  * Pop - remove the element at the top of the stack
* A stack is a *restricted access* data structure
* Its structure is defined by its behavior
* Other (optional) Operations:
  * `peek`: retrieve but do not remove the element at the top of the stack
  * determining the size of the stack;
  * whether or not it is empty
  * do we allow an "unbounded" stack (infinite size)
  * What happens when we try to pop from an empty stack
  * What happens when we try to push on a full stack
* If you use an array-based list, make sure to work from the same end and that it is efficient
* If you use a linked list, you only have to make sure the implementation keeps track of the head and the tail (if only one, you just have to work from the head OR tail)

## Queues

* Queues are lines: FIFO = First in First out
* Elements are *enqueued* at the end of the line (one end)
* Elements are *dequeued* at the front of the line

## Applications

* Stacks:
  * Program stacks
  * Anytime you want to generally keep track of "breadcrumbs"
  * Backtracking algorithms: may explore a "solution space" and backtrack to find a better or (more) optimal or valid solution
  * Use it to "simulate" recursion: instead of abusing the call stack, you use an in-memory stack allocated on the heap
  * Shunting Yard algorithms
* Queues
  * Buffer storage
  * More generally: any operation or task that is ordered
  * Producer Consumer patterns
* Variations
  * Priority Queues
  * Deques - Double-Ended Queues: you can use them as stacks, queues or combinations
  * push at both ends, pop only at one end: high priority line jumpers
  * push at one end, pop from both

```text














```
