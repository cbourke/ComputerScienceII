# Lists & List-based Data Structures 
## CSCE 156 - Spring 2021

### Introduction/Overview

* Motivation: Dynamic Data Structures (lists, sets, maps) are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

```java
int n = 10;
Integer numbers[] = new Integer[n];
```

* Once created, the size is fixed
* Solution: use a *dynamic list* data structure:
  * Allows you to grow and shrink automatically
  * You can add things without worrying about the details (OOP)
  * Want a way to add things
  * Want a way to remove things
  * Want a way to retrieve things
  * Want a way to modify things
* Other functionality:
  * A way to clear the list
  * A way to shrink the list if its capacity is too much (is being wasted)
  * A way to add multiple elements (batch add)
  * A way to copy one list to another (copy constructor!)
  * Find a particular element (search)
  * insert at the beginning