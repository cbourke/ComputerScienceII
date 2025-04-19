
# Trees & Tree-Based Data Structures
## CSCE 156 - Computer Science II
### Spring 2025

## Introduction

* Data *structures* are good because *structure* can be exploited for efficient operations
* Motivation: want a data structure that supports efficient:
   * CRUD
   * Retrieval/search
   * Insertion
   * Deletion
* Linked Lists:
  * Insert/delete at the head/tail in constant time: $O(1)$
  * Arbitrary insertion/index-based search: $O(n)$
* Array-based lists:
  * Arbitrary index-based search: $O(1)$ (random access)
  * If sorted, we can exploit that structure using binary search $O(\log{n})$
  * Inserting/deleting from the start: $O(n)$
* Stacks/Queues/Deques:
  * ALL operations are *always* efficient
  * BUT: they were *restricted access* data structures
  * All operations are $O(1)$ but we cannot do arbitrary CRUD
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations!

## Trees

* A *graph* is a collection of *nodes* (or vertices) and *edges* that connect nodes
  * Generally we use $n$ to denote the number of vertices
  * We use $m$ to denote the number of edges
  * Edges are *undirected*: $(a, b) = (b, a)$
  * Observation: the maximum number of edges in a graph with $n$ nodes is $m \leq \frac{n(n-1)}{2} = O(n^2)$
* A *tree* is a *connected*, *acyclic* graph (a graph with no cycles)
  * A *path* in a graph is a sequence of connected vertices
  * The *length* of a path is the number of edges on it = the number of vertices/nodes minus 1
  * We will only consider *simple* paths: those that do not traverse an edges more than once
  * A *cycle* is a path that begins and ends at the same node
  * The *length* of a cycle is equal to the number of edges on it = the number of vertices/nodes on it
  * It also has to be *connected*
  * A disconnected tree is called a "forest" (not going to consider these)

### Observations

* In a tree with $n$ nodes, there are exactly $n-1$ edges
  * Removal of any edge will disconnect the tree (forest)
  * Any additional edges will *induce* (create) a cycle
  * In a tree, $m = O(n)$
* In a tree, the number of paths between any two vertices is exactly **one** (There is exactly one path between any pair of nodes)

### Orientation

* Trees can be oriented top to bottom and left to right
* A the top is a single node called the *root*
* The nodes under another node are called *children*
* The node above a node is its *parent*
* Any node with zero children is called a *leaf*
* There are parent-child relations (ancestors, descendants, grandchildren, grandparent)
* More structure: suppose that each node is limited to at most 2 children (no child, 1 child, 2 children)
  * You can have a left and/or right child (oriented left-to-right)
  * Neither
  * Left child
  * Right child
  * Both
* This is called a *binary tree*
* Terminology:
  * The *depth* of a node $u$ is the length of the unique path from the root to $u$
  * THe depth of the root node is 0
  * The depth of the tree itself is the maximal depth of any node

## Java Demo

### How can we exploit this structure?

```text















```
