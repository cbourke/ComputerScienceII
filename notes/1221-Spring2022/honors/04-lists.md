# Lists & List-based Data Structures
## CSCE 156H - Spring 2022

### Introduction/Overview

* Motivation: Dynamic Data Structures (lists, sets, maps) are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

### Arrays are insufficient

```java
int n = 10;
Integer numbers[] = new Integer[n];
```

* Once created, the size is fixed
* Alternative: create a dynamic list:
  * Automatically grows/shrinks based on the number of things you put into it
  * It automatically takes care of all the details for you
  * Core functionality:
    * Put stuff in
    * Retrieve stuff
    * Remove stuff
* Demonstration:
  * A array-based list of integers

## Linked Lists

  * Each element is stored in a *node*
  * Each node contains an element *and* a reference to the "next" node

## Variations

* You can keep track of both a `head` and a `tail` element to simplify some operations
* Doubly linked lists: `next` and a `previous`
* Circularly linked lists
* Unrolled linked lists
* Skip lists
* Restricted Access Data Structures:
  * Stack
  * Queue

## Stacks & Queues

* Collections (list, sets) are *unstructured*
* Lists have orderings: first, second, .. last element, etc. BUT
* Lists are not necessarily *sorted*
* Even if we were to sort a list, it would be *imposing* a structure on the list's *state*
* One way to design a data structure with structure is to limit the access that the outside world has to it
* *Restricted Access Data Structures*: impose ordering on the collection's *behavior*
* Stacks: LIFO = Last-In First-Out
* Queues: FIFO = First-in first out
* We really like structure: structure can be exploited for efficient algorithms and data processing

### Stacks

* A *stack* is a data structure that stores elements in a LIFO = Last-In First-Out manner
* The last element to be put into a stack is the first one to be removed/retrieved from the stack
* Stack has 2 basic operations:
   * push operation: places a new element at the top of the stack
   * pop operation: removes and retrieves the element at the top of the stack
* Basic Implementation: list-based implementation
* Applications:
  * Call stack (programs have call stacks to keep track of where they have been)
  * Generally: stacks allow you to keep track of "breadcrumbs"
  * Graph traversals: DFS = Depth First Search
  * Tree traversals: preorder, inorder, postorder
  * Simulate Recursion: unfolding recursion
  * Backtracking optimization algorithms
  * Shunting Yard Algorithm
* In practice: in Java, use a `Deque`

### Queues

* Queues are FIFO = First In First Out
* Same design considerations and efficiency concerns as a stack
* You can use a linked list to implement a queue, you just have to work at different ends
* Operations:
  * enqueue: push, offer
  * dequeue: pop, poll
* Applications
  * Buffers: storing things in order
  * Producer Consumer Patterns

### Variations

* Line jumpers: *priority queue*
* Double ended Queue: *Deque*: you can operate at both ends (insert/remove at head and tail)
* Deques can be used to simulate stacks AND queues with one implementation
* In Java: `Deque` (`ArrayDeque`, `LinkedList`)
* Consider: push at both ends, pop at one end
* Consider: pop at both ends, push at one: an "undo" stack
* OR: an LRU Cache: Least Recently Used Cache



```text







```
