# Lists & List-based Data Structures
## CSCE 156 - Spring 2024

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays
* Leverages OOP principles (encapsulation, abstraction, inheritance, polymorphism)

### Arrays are "Bad"

```java
int arr[] = new int[10];
```

* once they are created, the size is *FIXED*: it cannot be changed
* We have to do a lot of bookkeeping to keep track of which indices have been "used" and which are free
* Deleting or removing elements may leave "gaps"

* Solution: design and implement a dynamic list data structure
  * Automatically resizes (grows/shrinks) based on the elements you insert, add, or delete (we don't have to worry about the details: abstraction/encapsulation)
  * Functionality:
    * Add things
    * Retrieve things
    * Remove things
    * Modify things
  * All the details of how this gets done are *encapsulated* inside the data structure and we don't have to worry about it!
  * We want to use it *abstractly*: we provide a public *interface* (public methods that you can use) to actually use the list

## Array-based lists

* Design the *interface* first
* Design the *tests* next
* Implement the interface
* Update design, tests, implementation as you go along

## Secondary Functionality

* Other methods/functionality might be useful:
  * Bulk add/remove methods (removeAll, addAll)
  * Append, insert, merge in
  * Copy constructor that allows you to create a new deep copy of the list, etc.
  * Compute a sublist: given start/end indices
  * Add functionality to *shrink* the list if necessary

# Linked Lists

* No underlying array, instead elements are stored inside of individual "nodes"
* Each node references its *element* and the *next* node
* Each node is "chained" or "linked" together
* Each basic operation involves *traversing* the list

## Secondary Functionality

* You can add support for the tail element
* You can add batch methods: clear(), addAll, removeAll

### Variations

* You can support *doubly linked lists*: each node has a next and a *previous*
* You can keep track of a tail and a head element
* You can write *circularly linked lists*
* Unrolled linked lists: each node may contain an *array* of elements instead of just one
* Skip list: linked list but with *some* random access (keep track of several nexts)

## Stacks

* Stacks are a *restricted access data structure*
* Collections (lists, sets, etc.) are "unstructured": they just hold stuff
* YOu can *impose* a structure on (say) lists by sorting them: you rearrange the list so that they are "in order"; that's imposed on the data structure's *state*
* Alternative: create structure through an object/data structure's *behavior*: its publicly available interface (public methods)
* A stack does this by restricting how you interface with it:
* Basic operations:
  * `push`: adding an element to the "top" of the stack
  * `pop`: removing (and returning) the element at the top of the stack
* Stacks are a LIFO = Last In First Out data structure
* This is ensured behavior through the public interface
* You are (generally) NOT allowed to interact arbitrarily with the stack

## Queues

* Is a "line", a FIFO = First In First Out Datastructure
* No line jumping! No cutting in line
* A restricted access data structure
  * `enqueue`: insert something at the "end" of the line
  * `dequeue`: remove and return the element at the "front" of the line
* Same exact considerations and options as before

# In Practice

* YOu generally don't roll your own: you use what is built into the language or library
* `List<>, ArrayList<>, LinkedList<>`
* Stacks/Queues: `Deque` ("deck"): double-ended queue
  * You can push/pop from both ends of the deque
  * It allows you to treat it like a stack AND a queue with one single data strcuture implementation!
* Other applications of a deque...

```text












```
