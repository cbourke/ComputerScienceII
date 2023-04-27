
# Trees & Tree-Based Data Structures
## CSCE 156H - Computer Science II
### Spring 2023

## Introduction

* We like data structures because structure can be exploited for efficient operations
* Motivation: want a data structure that supports efficient:
  * Retrieval/search
  * Insertion
  * Deletion
* Linked Lists:
  * Efficient $O(1)$ insertion/deletion at the head
  * Arbitrary index-based operations: $O(n)$
* Array-based lists:
  * Efficient index-based find operations $O(1)$ (random access)
  * Insertion/deletion from the start: $O(n)$
* Stacks/Queues:
  * Efficient operations push/pop enqueue/dequeue
  * Limited access data structures
* Searching an array (or array-based list): if sorted, we can use binary search to have $O(\log{n})$ search operations
* Ultimate goal: efficient operations for ALL operations!
* Trees provide a *potential* for efficient operations

## Trees

* A graph is a collection of *nodes* (or vertices) and *edges* connecting nodes
* A *tree* is an acyclic graph (a graph with no cycles)
  * A *path* in a graph is a sequence of vertices each connected by an edge
  * A *cycle* in a graph is a path that begins/ends at the same vertex
  * The *length* of a path or cycle is the number of edges along that path
  * We only consider *simple* paths/cycles that don't repeat an edge or vertex
  * A tree is a graph with no cycles

* Observations
  * For a connected tree with $n$ nodes, there will be $n-1$ edges
  * Between any two nodes there is exactly ONE unique path

* Orientation
  * We'll orient graphs top to bottom, left to right
  * At the top we have the *root*
  * It induces a parent-child relationship: ancestors, descendants, "family" relations
  * A node with no children is a *leaf* node


### Binary Trees

* A tree that has at most 2 children per node is a *binary tree*
* Each node can have one child, 2 children or no children
* Orient the child nodes so that there is a *left* child and a *right* child
  * If a node has no parent it is the *root* of the tree
  * If a node has no children it is a *leaf*
  * The depth of a node $u$ is the length of the unique path from the root to $u$
  * The depth of a tree is the maximal depth of any node
  * The depth of the root is zero

### How can we exploit this structure?

* Generally, we will store data in each node
* Operations will always start at the root (search, insert, delete) and will require traversing from the root to descendant nodes
* *IF* we can make all operations proportional to the depth, they *may* be efficient
* If we can make all operations proportional to $d$ AND $d = O(\log{(n)})$ then we have efficient operations for all operations!

### Implementation (Java)

* Demonstration

### Tree Traversals

```text






```
