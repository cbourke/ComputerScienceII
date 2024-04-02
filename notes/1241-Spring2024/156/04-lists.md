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




```text












```
