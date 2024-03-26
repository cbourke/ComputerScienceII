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



```text












```
