
# Trees & Tree-Based Data Structures
## ECEN 156 - Computer Science II
### Spring 2025

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
  * Arbitrary index-based search: $O(1)$ (random access)
  * If sorted, you can also use binary search for fast arbitrary element search (key-based) $O(\log{n})$
  * Inserting/deleting from the start: $O(n)$
* Stacks/Queues:
  * ALL operations are *always* efficient
  * BUT: they were *restricted access* data structures
  * All operations are $O(1)$ but we cannot do arbitrary CRUD
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations!

## Trees

* A *graph* is a collection of *nodes* (vertices) and *edges* connecting nodes
  * We generally have a node set: $V = \{v_1, v_2, \ldots, v_n\}$ (there are generally $n$ vertices)
  * We generally have an edge set: $E$, there are $m$ edges
  * Each edges is *undirected*: $(a, b) = (b, a)$
* A *tree* is a *connected*, *acyclic* graph (a graph with no cycles)
  * A *path* in a graph is a sequence of connected vertices
  * The *length* of a path is the number of edges on it
  * All paths will be *simple*
  * A *cycle* is a path that begins and ends at the same node (simple cycles only)
  * The *length* of a cycle is equal to the number of edges on it which is equal to the number of nodes on it
  * A tree has no cycles
  * A forest is a collection of trees (that are disconnected); they are important, but we will not consider them

### Observations

* In a tree with $n$ vertices/nodes, there are exactly $n-1$ edges
  * Any additional edge will *induce* (create) a cycle
  * Removal of any edge will disconnect the tree (forest)
  * Therefore, $|E| = O(n)$
* In a tree, between any two nodes there is *exactly* one path between them

### Orientation

* Trees can be oriented: top to bottom, left to right (because we are storing data in them)
* At the top is the *root* node
* Any node below a node is a *child*, the node above the child is its *parent*
* You can use other terminology: descendents/ancestors, (family oriented)
  * Careful: tree nodes only ever have one parent
* Any node with zero children is called a *leaf*
* More structure: suppose that each node is limited to at most 2 children (no child, 1 child, 2 children)
  * Neither
  * Left child
  * Right child
  * Both
* This is called a *binary tree*
* Terminology/definitions:
  * The *depth* of a node $u$ is the length of the unique path from the root to $u$
  * The depth of the root node is $0$ by convention
  * The depth of the tree itself is the maximal depth of any node

### How can we exploit this structure?

* Suppose you had a *full* binary tree: every node was present at every level/depth up until the maximal depth of the tree.
  * For a full binary tree, the depth is $d = O(\log{n})$
* If we can develop a way to insert/retrieve/delete elements from a binary tree such that
  * all operations are proportional to the depth *and*
  * we can guarantee that the depth is "small"
  * Then everything is efficient: $O(\log{n})$
* A "full" or nearly full binary tree has a guarantee on its depth: $d = O(\log{n})$ where $n$ is the number of nodes
  * However, general trees are not necessarily full
  * You can have "degenerate" trees: $d = O(n)$ (a linked list is an example)

## Binary Search Trees

* Generally we store elements in a tree node, you can also impose additional structure on that data: that it be *ordered*
* Binary Search Tree: all nodes have a *key* element, $k$
  * For our purposes: the data and the key will be the same, we'll use integers and assume them to be unique
* For every node with key value $k$: ALL nodes in its left subtree have key values LESS than $k$.  ALL nodes in its right subtree have key values GREATER than $k$


```text
















```
