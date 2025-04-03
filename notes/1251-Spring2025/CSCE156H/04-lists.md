# Lists & List-based Data Structures
## CSCE 156H - Spring 2025

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

* Other methods/functionality might be useful:
  * Bulk add/remove methods (`addAll()`, `removeAll()`)
  * Append, insert, merge two lists together, etc.
  * Copy constructor to allow for deep copies of your list
  * Compute a sublist: given start/end indices
  * Add functionality to *shrink* the list if necessary

## Improvements

* What is `MyIntegerArrayList` good for? ONLY integers :(
* What is `MyArrayList<T>` good for? EVERYTHING :)

# Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node:
  * Contains a piece of data (`T`)
  * A reference to the `next` node
  * Any convenience method(s)
* The linked list itself **only** has a reference to the `head` node
* The basic operation: a *traversal*: starting at the head, you traverse next, next, next, until you get to the node you want
* Consequence: there is no *random access*

## Variations

* You can add support to keep track of the tail element
  * Makes adding stuff at the end more efficient
* You can support *doubly linked lists*: each node has a next and a *previous*
  * You can traverse in both directions
  * Twice as much work in the implementation (more corner cases)
* You can write *circularly linked lists*
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
  * Tunable and offers better performance
* Skip list: linked list but with *some* random access (keep track of several nexts)

# Stacks

* Stacks are a *restricted access data structure*
* Collections (lists, sets, etc.) are "unstructured": they just hold stuff
* You can *impose* a structure on (say) lists by sorting them: you rearrange the list so that they are "in order"; that's imposed on the data structure's *state*
* Alternative: create structure through an object/data structure's *behavior*: its publicly available interface (public methods)
* A stack does this by restricting how you interface with it:
* Basic operations:
  * `push`: adding an element to the "top" of the stack
  * `pop`: remove (and return) the element at the "top" of the stack
* Stacks are a LIFO = Last In First Out data structure
* Additional Functionality:
  * Method to determine the size
  * `isEmpty()`
  * `isFull()`: you could have a capacity restricted version of a stack
  * Look at, but do not remove teh element at the top: `peek()`
* ~~In practice: use Java's `Stack`~~ (better: use a `Deque` (later))

# Queues

* Is a "line", a FIFO = First In First Out Datastructure
* No line jumping! No cutting in line
* A restricted access data structure:
  * `enqueue`: insert something at the end of the queue
  * `dequeue`: remove and return from the "front" of the queue


## Other Considerations

* What happens when you pop/dequeue from an empty stack/queue?  Throw exception, return a special value, etc?
* You could design stack/queue with a max capacity
  * What happens when you push/enqueue onto a "full" stack/queue?
* You *could* support the arbitrary removal of elements (line leavers)
* You could allow arbitrary insertions: priority (VIPs, line jumpers)
* Priority Queue: allows you to define "priorities" and insert elements at a location such that the elements are served highest priority first
* A more general data structure that can simulate both a stack and a queue is a `Deque` ("deck"): "a double ended queue"
* Why work at both ends?
  * Push/pop from one end and pop from the other: supporting a limited number of undo operatoins
  * Push/pop at one end, push at the other
* Applications:
  * Stack:
    * call stack
    * Storing breadcrumbs
    * DFS = Depth First Search of a graph
    * Simulate recursion
    * Backtracking/optimization algorithms
    * Shunting Yard algorithm
  * Queues:
    * Buffer
    * Server requests or a "Consumer Producer" scenario
    * Resource Pools/connection pools
* What is the best implementation?  Array-based or Linked List?
  * A linked list that keeps track of both head/tail is efficient (constant number of operations) for add/removing from both ends
  * **Our** array-based implementation: if there are $n$ elements, it requires $n$ shifts/operations to insert/remove from the head
  * Java's `Deque<>` is "clever"

```text












```
