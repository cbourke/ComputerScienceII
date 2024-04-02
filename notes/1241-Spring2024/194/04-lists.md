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


```text










```
