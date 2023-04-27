
# Trees & Tree-Based Data Structures
## CSCE 156 - Computer Science II
### Spring 2023

## Introduction

* Data *structures* are good because *structure* can be exploited for efficient operations
* Motivation: want a data structure that supports efficient:
  * Retrieval/search
  * Insertion
  * Deletion
* Linked Lists:
  * Insertion/delete from the head or tail: $O(1)$
  * Arbitrary insertion/index-based search: $O(n)$
* Array-based lists:
  * Insertion/deletion from the start: $O(n)$
  * Arbitrary index-based search: $O(1)$
* Stacks/Queues:
  * Efficient push/pop poll/offer operations
  * BUT: restricted access data structures
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations

## Trees

* A graph is a collection of *nodes* (or vertices) and *edges* connecting nodes
* A *tree* is an *acyclic* graph (a graph with no cycles)
  * A *path* in a graph is a sequence of connected vertices
  * A *simple cycle* is a path that begins/ends at the same vertex
  * THe length of a path or cycle is the number of edges on it
  * A tree has no cycles
  * A disconnected tree is called a "forest"

* OBservations
  * For a connected tree with $n$ vertices, there are $m = n-1$ edges
  * Between any two nodes there is *exactly* one path

## Orientation

* Trees can be oriented: top to bottom left to right
* A the top is a single node called the *root*
* This induces a parent-child relation (ancestors, descendants)
* Suppose we add even more structure: that for any node there can be at most *two* children: such a tree is a *binary tree*
* In a binary tree you can have 0 children (leaf) 1 child or 2 children
  * Further structure: orient the two children to left and right
* Terminology/definitions
  * The *depth* of a node $u$ is the length of the unique path from the root to that node
  * By convention, the depth of the root is 0
  * The depth of a tree itself is the maximal depth of any node

### How can we exploit this structure?

* Generally we will store data in each node
* Operations (search, insert, delete) will always begin at the root node
* IF we can define operations with efficiency proportional to the depth, we *may* be able to make them very efficient

### Implementation (Java)

* Demonstration



```text















```
