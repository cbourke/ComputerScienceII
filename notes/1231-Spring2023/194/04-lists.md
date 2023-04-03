# Lists & List-based Data Structures
## ECEN 194 - Spring 2023

### Introduction/Overview

* Motivation: dynamic data structures (lists, sets, maps) are *way* better than simple arrays
* Leverages OOP principles (encapsulation, abstraction, polymorphism)

```java
int n = 10;
Integer arr[] = new Integer[n];
```

### Array-Based Lists

* Once created, the size is *fixed*, it does not grow it does not shrink
* Solution: use a *dynamic list* data structure:
  * Automatically grows/shrinks with new elements
  * You can add elements without worrying about the details (abstraction)
* Demonstration: create your own array-based list
  * Want a way to add things
  * Want a way to remove things
  * A way to retrieve things
  * A way to modify elements in the list
* Other functionality:
  * A way to clear the list
  * A way to shrink the list if its capacity is too much (is being wasted)
  * A way to add multiple elements (batch add)
  * A way to copy one list to another (copy constructor!)
  * Find a particular element (search)
  * insert at the beginning

### Linked Lists

* There is no underlying array, instead elements are stored inside of individual "nodes"
* Each node references an element and the *next* node
* Each node is thus "chained" together or "linked"
* Basic operations involve *traversing* the list

```text








```
