
# Trees & Tree-Based Data Structures
## CSCE 156H - Computer Science II
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
  * Insertion/deletion from the start: $O(n)$
  * Arbitrary index-based search: $O(1)$
  * If sorted: you can also exploit binary search!
* Stacks/Queues:
  * Efficient push/pop poll/offer operations: $O(1)$
  * BUT: they are not general purpose: they *restricted* access in order to give you efficiency
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations

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
* All other nodes are "internal" nodes
* This induces a parent-child relation (ancestors, descendants)
* More structure: suppose that each node is limited to at most 2 children (no child, 1 child, 2 children)
  * Neither
  * Left child
  * Right child
  * Both
* This is known as a *binary tree*
* Terminology:
  * The *depth* of a node $u$ is the length of the unique path from the root to $u$
  * The depth of the tree itself is the maximal depth of any node
  * Depth of the root: 0 by convention

### How can we exploit this structure?

* Suppose you had a *full* binary tree: every node was present at every level/depth up until the maximal depth of the tree.
* If we can develop a way to insert/retrieve/delete elements from a binary tree such that
  * all operations are proportional to the depth *and*
  * we can guarantee that the depth is "small"
* A "full" or nearly full binary tree has a guarantee on its depth: $d = O(\log{n})$ where $n$ is the number of nodes
  * if you can perform operations proprotional to the depth...
  * operations will be efficient: $O(\log{n})$

### Implementation (Java)

* Demonstration

### Traversal Strategies

* Preorder: root-left-right
* Inorder: left-root-right
* Preorder: left-right-root
* BFS = Breadth First Search
* Efficiency:
  * Elementary Operation: processing a node: $O(n)$: every node is processed exactly once
  * Elementary Operation: node traversal: at most $3n = O(n)$ node/edge traversals!
  * Catch: these are designed to traverse the *ENTIRE* tree

## Binary Search Trees

* Generally we store elements in a tree node, you can also impose additional structure on that data: that it be *ordered*
* In a binary search tree: you store both data and a *key* element
* BST Property: for a node with key $k$, EVERY node in its left subtree has a key value LESS than $k$, EVERY node in its right subtree has a key value GREATER than $k$
* Practical issues:
  * For our purposes, we'll key the data and key the same (You can always keep track of 2 things)
  * They will be integers (every object can be given an integer representation; in Java you use the `.hashCode()`)
  * They will be distinct (YOu can always break ties somehow)
* To search requires at most $d + 1$ comparisons, $O(d)$
* However, the depth is not guaranteed to be $O(\log{n})$, it can be $d = O(n)$ in the worst/degenerate case

### Operations

* Search:
  * start at the root
  * traverse left/right until found or go past a leaf
  * Complexity: $O(d)$
  * However, there is no guarantee that $d = O(\log{n})$, it could be "degenerate" (a linked list) and $d = O(n)$

* Insert:
  * Always insert as a leaf
  * If its duplicate: reject it somehow

  * Delete
    * Step 1: Find the element (if no such element: noop or exception, etc.)
    * If it is a leaf: simply delete it (careful for edge cases: deleting the root with no children)
    * If it has only ONE child: promote the single child up to be the new child of the node's parent (careful: left/right with respect to the parent and left/right with respect to the promoted child)
    * If it has both children: go either
      * left tree and find the max value
      * right tree and find the min value
      * promote one or the other to the deleted node
      * delete the promoted node (it is guaranteed to have at most one child!)
    * Just be careful with corner cases
    * Finding it: $O(d)$ (no guarantee on the depth)
    * What about finding the max value? Just traverse right until you can go no further; $O(d)$

## Heaps

* Heaps are not binary search trees, but they are *balanced binary trees*
  * Guarantee that their depth is $O(\log{n})$
  * There is not a BST property, there is a HEAP property
* Fullness property: every child is present at every level except for possibly the last (deepest) level but at that level, all nodes are "full to the left"
* Heap property: the key of every node is less than *both* its children (min heap)
* OBservations:
  * The fullness property guarantees that the depth of a heap is logarithmic: $O(\log{(n)})$
  * The minimum element is always at the root
  * if the operations can be made $O(d)$ then they are efficient
  * NOT a general purpose data structure
* Restricted Access Data Structure
  * get-and-remove-min: $O(d) = O(\log{n})$
  * add-element: $O(d) = O(\log{n})$
* Add Element
  * Insert the new node as the "last" element
  * Heapify: compare it with its parent and swap if out of order until either a) the heap property is satisfied or b) it becomes the new root
  * $O(d)$ comparisons (assuming that we can "jump" to the last element)
* Remove (and return) the min element:
  * Save the root off into a temp. variable
  * Replace the root with the "last" element
  * Heapify downward: swap this element with the min child until the heap property is satisfied or you reach the bottom of the tree
  * $O(d)$


```text










```
