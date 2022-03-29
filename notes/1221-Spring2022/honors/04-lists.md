# Lists & List-based Data Structures
## CSCE 156H - Spring 2022

### Introduction/Overview

* Motivation: Dynamic Data Structures (lists, sets, maps) are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

### Arrays are insufficient

```java
int n = 10;
Integer numbers[] = new Integer[n];
```

* Once created, the size is fixed
* Alternative: create a dynamic list:
  * Automatically grows/shrinks based on the number of things you put into it
  * It automatically takes care of all the details for you
  * Core functionality:
    * Put stuff in
    * Retrieve stuff
    * Remove stuff
* Demonstration:
  * A array-based list of integers
