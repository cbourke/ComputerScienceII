
# Trees & Tree-Based Data Structures
## CSCE 156H - Computer Science II
### Spring 2022

## Introduction

* We like data structures because structure can be exploited
  for efficient operations
* Motivation: want a data structure that supports efficient:
    * Retrieval/search
    * Insertion
    * Deletion
* Linked Lists:
  * Insertion or deletion from the head or tail: $O(1)$ (constant)
  * Arbitrary insertion/index-based search: $O(n)$
* Array-based list:
  * insertion/deletion at the front: $O(n)$
  * Arbitrary index-based search: $O(1)$ (due to random access)
* Stacks/Queues:
  * Efficient push/pop operations: $O(1)$
  * But: structure came from the *restricted access*
* Searching an array (if sorted): binary search offered $O(\log{n})$ search operations
* Ultimate goal: efficient operations for ALL basic operations
* Trees: provide a *potential* for efficient operations (all three)

## Trees

* A graph is a collection of *nodes* (vertices) and *edges*
* Nodes can be connected by a *path*
  * For our purposes: all paths will be *simple* paths (they will not visit the same vertex nor traverse the same edge more than once)
  * The length of a path is the number of edges along it
  * A *cycle* is a path that starts/ends at the same vertex
* A tree is an acyclic graph (a graph without cycles)
* Observations:
  * If a tree is connected and has $n$ vertices, how many edges does it have? A: $n-1$ edges
  * If a tree is *disconnected* then it has multiple connected components and is called a *forest* (we will assume all of our trees are *connected*)
* The length of a path or cycle is the number of edges along it

### Binary Trees

* You can "orient" trees in a top-down, left-to-right manner
* Terminology:
  * The node at the "top" is the *root* node
  * Nodes at the bottom are leaves
  * You can also speak about a parent-child relationship
* Suppose you have another restriction: for each node, there is at most 2 children (left child, right child)
* Ie you can have a left child, no right child, vice versa, no children, or both
* A node with no children is a *leaf* node
* The *depth* of a node is the length of the unique path from the root to that node.
* The depth of a *tree itself* is the maximal depth of any node
* Suppose you have a "full" binary tree: every child exists except for at the last level (which are all leaves): how many nodes are in such a tree of depth $d$?
* If we can develop a binary tree such that the three basic operations are proportional to the depth of the tree AND we can guarantee depth is $O(\log{n})$ then we've achieved our goal

### Implementation (Java)

* See the Java classes

## Tree Traversals

* Unlike a linked list, a tree has upto two option at each node: left and right
* This means that to *traverse* a tree, you need a traversal algorithm
  * Preorder Traversal
  * Inorder Traversal
  * Postorder Traversal


```text








```
