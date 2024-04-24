
# Trees & Tree-Based Data Structures
## CSCE 156 - Computer Science II
### Spring 2024

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
  * Arbitrary index-based search: $O(1)$
  * If sorted, you can also use binary search for fast arbitrary element search (key-based)
  * Inserting/deleting from the start: $O(n)$
* Stacks/Queues:
  * ALL operations are *always* efficient
  * BUT: they were *restricted access* data structures
  * All operations are $O(1)$ but we cannot do arbitrary CRUD
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations!

## Trees

* A graph is a collection of *nodes* (or vertices) and *edges* connecting nodes
* A *tree* is a *connected*, *acyclic* graph (a graph with no cycles)
  * A *path* in a graph is a sequence of connected vertices
  * A *simple cycle* is a path that begins/ends at the same vertex
  * The length of a path or cycle is the number of edges on it
  * A tree has no cycles
  * A disconnected tree is called a "forest"

### Observations

* In a tree with $n$ vertices/nodes, there are exactly $n-1$ edges
  * Any additional edge will *induce* (create) a cycle
  * Removal of any edge will disconnect the tree (forest)
* In a tree, between any two nodes there is *exactly* one path between them

### Orientation

* Trees can be oriented: top to bottom, left to right
* A the top is a single node called the *root*
* Any node with zero children is called a *leaf*
* This induces a parent-child relation (ancestors, descendants)
* More structure: suppose that each node is limited to at most 2 children (no child, 1 child, 2 children)
  * Neither
  * Left child
  * Right child
  * Both
* This is called a *binary tree*
* Terminology:
  * The *depth* of a node $u$ is the length of the unique path from the root to $u$
  * The depth of the root node is $0$ by convention
  * The depth of the tree itself is the maximal depth of any node

### How can we exploit this structure?

* Suppose you had a *full* binary tree: every node was present at every level/depth up until the maximal depth of the tree.
* If we can develop a way to insert/retrieve/delete elements from a binary tree such that
  * all operations are proportional to the depth *and*
  * we can guarantee that the depth is "small"
* A "full" or nearly full binary tree has a guarantee on its depth: $d = O(\log{n})$ where $n$ is the number of nodes
  * if you can perform operations proportional to the depth...
  * operations will be efficient: $O(\log{n})$

### Implementation (Java)

* Demonstration

### Traversals

* Preorder traversal: root-left-right
* Inorder traversal: left-root-right
* Postorder traversal: left-root-right




```text













```
