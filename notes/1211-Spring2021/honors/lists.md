# List and List-based Data Structures 
## CSCE 156 - Spring 2021

### Introduction/Overview

* Motivation: Dynamic Data Structures are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

```java
int n = 10;
Integer numbers[] = new Integer[n];
```
* Once created, the size is fixed
* Solution: use a *dynamic list* data structure:
  * Automatically grows (maybe shrinks) as we put stuff in (or take stuff out)
  * You can add stuff without thinking about the details of how it gets added
  * Support basic operations:
  * Create: put stuff into the list
  * Retrieve: get stuff out of the list
  * Update: replace items
  * Destroy: remove items from the list
  * Batch operations: add several elements
  * Copy constructors?
  * finding/searching a list