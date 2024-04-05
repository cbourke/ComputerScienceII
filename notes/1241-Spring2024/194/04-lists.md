# Lists & List-based Data Structures
## ECEN 156 - Spring 2024

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays

```java
int arr[] = new int[10];
List<Integer> numbers = new ArrayList<>();
numbers.add(42);
```

* Leverages OOP principles (encapsulation, abstraction, inheritance, polymorphism)

### Arrays are "Bad"

* Once created, the size is essentially fixed
* We have to do a lot of bookkeeping to keep track of which indices have been "used" and which are free
* Deleting or removing elements may leave "gaps"
* Solution: creating a dynamic list:
  * Automatically grows/shrinks without having to worry about the details
  * Functionality:
    * Add things
    * Remove things
    * Change/modify/update things
    * Retrieve things
  * We want all the details to be *encapsulated* inside the data structure and not have to worry about them
  * We want an abstraction: an *interface* (publicly available methods) that specifies how to *use* the list, not necessarily how it works

## Array-based lists

* Design the *interface* first
* Design the *tests* next
* Implement the interface
* Update design, tests, implementation as you go along

### Secondary Functionality

* Bulk operations: `addAll`, `clear`
* Get a new copy that is a sublist (elements from `start` to `end` indices), etc.
* Merge two lists together to get a new one
* Copy constructor: takes another list and creates a deep copy of the list

# Linked lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node references its *element* and the *next* node
* Each node is "chained" or "linked" together
* Each basic operation involves *traversing* the list

## Demonstration

### Variations

* Doubly linked list: you keep track of the `next` and the `previous`
* Circularly linked lists
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
* Skip list: linked list but with *some* random access (keep track of several nexts)

## Stacks

* Stacks are a data structure
* Stacks are a *restricted access data structure*
* Collections (lists, sets): these a bit "unstructured", they just hold stuff
  * Sorted lists/arrays have structure: you can exploit that ordering for efficient search (binary search)
  * Sorting *imposes* an ordering on the data structure's *state*
* A restricted access data structure creates structure through the object's *behavior*: public interface (the public methods)
* We restrict what you can do with the data structure
  * `push`: add an element to the top of the stack
  * `pop`: remove and return the element at the "top" of the stack

## Queues

* Is a "line", a FIFO = First In First Out Data structure
* No line jumping!
* A restricted access data structure
  * `enqueue`: insert something at the "end" of the line
  * `dequeue`: remove the element at the "front" of the line
* Same exact considerations and options as before

## Secondary Functionality

* `peek`: view/return the element that would be popped/dequeued next, but do not remove it
* Support a maximum capacity for either/both: `isFull()` `getRemainingCapacity()`, etc.
* What happens when you push/enqueue on a full stack/queue
* What happens when you pop/dequeue from an empty stack/queue

## Variation

* In practice you can use a `Deque` ("Deck"): double ended queue
* You can add/remove from BOTH ends
* This allows you to use any combination of push/pop or enqueue/dequeue

```text










```
