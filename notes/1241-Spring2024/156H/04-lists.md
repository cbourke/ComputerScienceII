# Lists & List-based Data Structures
## CSCE 156H - Spring 2024

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays

```java
int arr[] = new arr[10];
List<Integer> numbers = new ArrayList<>();
numbers.add(42);
```

* Leverages OOP principles (encapsulation, abstraction, inheritance, polymorphism)

### Arrays are "Bad"

* once they are created, the size is *FIXED*: it cannot be changed
* We have to do a lot of bookkeeping to keep track of which indices have been "used" and which are free
* Deleting or removing elements may leave "gaps"
* Solution: design and implement (and test and refine) a dynamic list data structure
  * Automatically grows/shrinks depending on the elements you add/remove (we don't have to worry about the details outside of the data structure!)
  * Functionality:
    * Add things
    * Retrieve things
    * Delete things
    * Update things/modify things
  * All the details of how this gets done are *encapsulated* inside the data structure and we don't have to worry about it!
  * We want to use it *abstractly*: we provide a public *interface* (public methods that you can use) to actually use the list

## Array-based lists

* Write our own version of an `ArrayList`: how?
* Design the *interface* first
* Design the *tests* next
* Implement the interface
* Update design, tests, implementation as you go along

## Secondary Functionality

* Other methods/functionality might be useful:
  * Batch operations: `removeAll` or `addAll`
  * Splice method: insert a sublist inside of the list
  * Sublist: given a `startIndex`, `endIndex` return a new list of those subelements
  * Copy constructor: create a new deep copy of the list
  * Shrink the list if necessary
  * Return the last index: `size-1`
  * `getFirst()`, `getLast()`
  * `list.sorted()` vs `sort(list)`

## Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node references its *element* and the *next* node
* Each node is "chained" or "linked" together
* Each basic operation involves *traversing* the list

## Variations

* Keep track of both the head and the tail
* Doubly linked lists: each node has a next and a `previous`
* Circularly linked lists: have no head nor tail, they go around
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
* Skip list: linked list but with *some* random access (keep track of several nexts)

## Stacks

* Stacks are a *restricted access data structure*
* Collections (lists/sets): they store thiings in an "unstructured" manner
  * You can *impose* a structure: sort a list, this rearranges and *imposes* an order on the list
  * This is with respect to the *state* of the data structure
* Create structure via a data structure's behavior (methods, the public interface -- publicly available methods)
* Stack:
  * `push` you add an element to the "top" of the stack
  * `pop` you remove (and return) the element at the top of the stack
* LIFO = Last-In First-Out
* A stack is not a list, a list is not a stack: we prefer *composition* over inheritance!

## Queues

* Is a "line", a FIFO = First In First Out Datastructure
* No line jumping!  No cutting in line
* A restricted access data structure
  * `enqueue`: insert something at the "end" of the line
  * `dequeue`: remove the element at the "front" of the line

## In Practice

* Don't create your own!
* You use `List<>, ArrayList<>, LinkedList<>` or pull in a library
* Java: `Deque<>` ("deck"): double ended queue
  * YOu can add/remove from *both* ends of the deque
  * You can treat a `Deque<>` like a stack
  * You can treat a `Deque<>` like a queue
  * You do do MORE than one operation at both ends...
* Demo

### Applications

#### Stack

* Program stacks/call stacks
* You can use an in-memory stack to "simulate" and eliminate recursion
* Storing breadcrumbs: graph traversal algorithms
* Shunting Yard algorithm: some mathematic operations (see lab 14?)

#### Queues

* Buffers
* Server requests or a "Consumer Producer" scenario
* Resource Pools/connection pools



```
