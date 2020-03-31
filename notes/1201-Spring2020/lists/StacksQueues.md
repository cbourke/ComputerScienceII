# List and List-based Data Structures 
## Stacks & Queues
### CSCE 156 - Spring 2020

### Introduction/Overview

* Collections (lists, sets) are *unstructured*: they just hold stuff
* Lists are *ordered*: ordered != sorted
* Sorting *imposes* a structure on a list but its part of the collection's *state*
* *Restricted Access* data structures: structure comes from the *behavior* 
* We really like structure: structure can be exploited for efficient algorithms and data processing

# Stacks

* A *stack* is a data structure that stores elements in a LIFO = Last-in, First-out manner
* The last element to be put into a stack is the first one to be removed/retrieved from the stack
* Stack has 2 basic operations:
   * push operation: places a new element at the top of the stack
   * pop operation: removes and retrieves the element at the top of the stack 
* Easiest implementation: use a list!
* Secondary functionality:
  * `isEmpty()`
  * Introduce a capacity (bounded stack): no more than n elements can be pushed onto the stack
  * What happens when you `pop` from an empty stack?
    * Throw an exception
    * Return a special flag value (`null` or `true/false`)
    * Noop = no operation (fails silently)
  * What happens when you `push` onto a full stack
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

* A "queue" is simply a line: no cuts!
* A queue is a data structure that stores elements in a FIFO manner, First-in First-out manner
* No line jumping: elements are 
  * Enqueue - (Push or offer): you place a new element at the "end" of the line (back of the line)
  * Dequeue - (poll) remove and return the element at the "start" of the line (head of the line)
* List implementation: use a linked list

### Variations

* You could allow arbitrary removal/insert/retrieval
* You could allow line jumpers: Priority Queue
* Deque ("deck"): allows both insert and removal from both ends (linked list): "double ended queue"
  * allows you to implement a stack and a queue with one implementation!
  * Application "undo" features 
  * LRU Cache = Least Recently Used Cache
  
### Applications

* Buffers 
* Servicing requests: requests (producers) may come in asynchronously: requests are stored in a queue (in the order they are received), and then workers (consumers) look at the queue and take and service a requests as they are able to
* Implementations of resource "pool" 

### In Practice

* You don't create your own stack or queue
* Java provides a `Deque<T>` inferface: use it as a stack or queue or deque










