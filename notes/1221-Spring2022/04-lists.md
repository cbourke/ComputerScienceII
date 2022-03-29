# Lists & List-based Data Structures
## CSCE 156 - Spring 2022

### Introduction/Overview

* Motivation: Dynamic Data Structures (lists, sets, maps) are better than arrays
* Leverage OOP Principles (Encapsulation, Abstraction, Polymorphism) to create dynamic data structures with different properties

### Arrays are "Bad"

* Once created, the size is essentially fixed
* We have to do a lot of bookkeeping to keep track of which indices have been "used" and which are free
* Deleting or removing elements may leave "gaps"
* Solution: a *dynamic list* data structure:
  * Automatically grow and shrink without us having to worry about it
  * We want to be able to:
    * add things
    * retrieve things
    * remove things
  * We want all the details to be *encapsulated* inside the data structure and not have to worry about them
  * We want an abstraction: an interface that specifies how to *use* the list, not necessarily how it works
* Other functionality:
    * A way to clear the list
    * A way to shrink the list if its capacity is too much (is being wasted)
    * A way to add multiple elements (batch add)
    * A way to copy one list to another (copy constructor!)
    * Find a particular element (search)
    * insert at the beginning
* Demonstration:
  * Create an "array-based list" to hold integers
  * Used abstraction to hide the details of how internally the array grew/shrunk to accommodate more elements
  * Used encapsulation to protect the list from arbitrary operations
  * Used inheritance at first to create a sorted list, realized it was bad and instead used composition
  * Used parameterization (polymorphism) to have one list that can hold *any* type

* Drawbacks:
  * Once the capacity is reached in the array, an entirely new copy must be created!
  * This operation requires $n$ copy operations where $n$ is the number of elements in the list!
  * If $n$ is small, so what
  * If $n$ is large, this becomes very expensive

## Alternative: Linked List

* It does not hold elements in an array
* Each element is held in a "node"
* Each node has the element and a reference to the "next" node
* Each node is placed in a "chain" and linked together


```text














```
