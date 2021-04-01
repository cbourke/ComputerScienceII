# List and List-based Data Structures 
## CSCE 156H - Spring 2021

### Introduction/Overview

* Motivation: Dynamic Data Structures are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

```java
int n = 10;
Integer numbers[] = new Integer[n];
```
* Once created, the size is fixed
* Solution: use a *dynamic list* data structure:
  * Automatically grows (maybe shrinks) as we put stuff in (or take stuff out)
  * You can add stuff without thinking about the details of how it gets added
  * Support basic operations:
  * Create: put stuff into the list
  * Retrieve: get stuff out of the list
  * Update: replace items
  * Destroy: remove items from the list
  * Batch operations: add several elements
  * Copy constructors?
  * finding/searching a list
  
## Linked Lists

* Each element is stored in a *node* 
* Each node contains an element *and* a reference to the "next" node

* Variations
  * Doubly Linked Lists
  * Circularly Linked Lists
  * Unrolled Linked Lists
  * Skip Lists

## Stacks & Queues

* Collections (list, sets) are *unstructured* 
* Lists have an *ordering*: first, second, last element, etc. BUT...
* Lists are not necessarily *sorted*
* sorting *imposes* structure on a list's *state*
* *Restricted Access Data Structures*: impose ordering on the collection's *behavior*
* We really like structure: structure can be exploited for efficient algorithms and data processing

### Stacks

* A *stack* is a data structure that stores elements in a LIFO = Last-In First-Out manner
* The last element to be put into a stack is the first one to be removed/retrieved from the stack
* Stack has 2 basic operations:
   * push operation: places a new element at the top of the stack
   * pop operation: removes and retrieves the element at the top of the stack 
   * Applications:
     * Call stacks (programs)
     * Keeping track of "breadcrumbs"
     * Graph traversals: DFS = Depth First Search algorithm, Tree Traversals 
     * Simulate Recursion: unfolding recursion
     * Backtracking algorithms: optimization
     * Shunting Yard Algorithm 
   * Additional functionality:
     * `size()`
     * `peek()`: retrieves but does not remove the top element
     * many other additional pieces of functionality

### Queues

* Queues are essentially lines: FIFO = First In First Out
* No line jumping: elements are 
  * Enqueue - (Push or offer): you place a new element at the "end" of the line (back of the line)
  * Dequeue - (poll) remove and return the element at the "start" of the line (head of the line)
```text





```