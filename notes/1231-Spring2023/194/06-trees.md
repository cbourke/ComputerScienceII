
# Trees & Tree-Based Data Structures
## ECEN 194 - Computer Science II
### Spring 2023

## Introduction

* We like data structures because structure can (potentially) be exploited for efficient operations
* Array: if it is sorted, then we can use binary search for fast searching
* Motivation: want a data structure that supports efficient:
  * Retrieval/search
  * Insertion
  * Removal
* Linked Lists:
  * Insertion/deletion at the head or tail: $O(1)$
  * Arbitrary insertion/index-based search: $O(n)$
* Array-based lists:
  * Insertion/deletion from the head: $O(n)$
  * Arbitrary index-based search: $O(1)$
* Stacks/Queues:
  * Offered efficient push/pop or offer/poll
  * BUT: restricted access data structures
* Ultimate goal: to develop a general purpose data structure that holds things and offers $O(\log{(n)})$ performance for ALL operations
* Trees (binary trees) provide a *potential* for efficient operations

## Tree

* A graph is a collection of *nodes* (vertices) and *edges* connecting those nodes
* A *tree* is an acyclic graph (a graph with no cycles)
  * A *path* in a graph is a sequence of connected vertices
  * A *simple cycle* is a path in a graph that begins and ends at the same node/vertex without traversing the same vertex or edge more than once
  * The *length* of a path or cycle is the number of edges along that path
  * A tree may be connected or disconnected
  * A *disconnected* tree is a "forest"

* Observations
  * No cycles in a tree
  * In a connected tree if there are $n$ vertices then there are exactly $n-1$ edges
  * In a tree, between any two nodes there is exactly ONE path

### Binary Trees

* Given a tree you can generally draw it any way you want
* You an also "orient" trees "downward"
* At the top is a single node call the root
* This induces a "parent-child" relationship
* You can also orient nodes in a left-to-right manner
* Further restriction: suppose that a tree node is only allowed to have at most two children (it can have zero, one or two children): this is a *binary tree*
* In a binary tree you can have a *left* child or a *right* child (or both or neither); you can also have a *parent*
  * if a node has no parent then it is the *root* of the tree
  * If the node has no children it is called a *leaf*
* Given an orientation of a tree you can talk about its *depth* and the *depth* of nodes
  * The depth of a node $u$ in a tree is the length of the *unique* path from the *root* to $u$
  * The depth of a *tree* itself is the maximal depth of any node

### How can we exploit this structure?

* Generally, we will store data in each node
* Operations will always begin at the root (search, insert, delete) will require traversing from the root node down to its descendants (possibly back up the tree)
* Observation: how many nodes are in a full binary tree of a certain/given depth $d$ and/or what is the depth of a "full" binary tree given the number of nodes $n$
* Observation: in a *full* binary tree, the depth is logarithmic: $d = O(\log{n})$
* ***IF*** we can make all operations proportional to the depth of the tree AND we can guarantee that the tree is "full" (or nearly full or "balance") then we've achieved our goal!

### Implementation

* See Java Code

## Operations

### Tree Traversals

* In general, you start at the root
* Goal: be able to enumerate/view/traverse every single node without missing any
* Preorder traversal
  * Recursively visits nodes in a root-left-right manner
* Inorder traversal
  * Recursively visits nodes in a left-root-right manner
* Postorder traversal
  * Recursively visits nodes in a left-right-root manner
* Breadth First Search: top-to-bottom, left-to-right (using a queue)

## Binary Search Trees

Observation:
  * Preorder/inorder/postorder/BFS are all linear algorithms: $O(n)$ ($n$ nodes in the tree)
  * Each node is "processed" exactly once and "visited" at most 3 times (still $O(n)$)
  * We need more structure!

Property/Definition:
  * Each  node contains data and a key (for our purposes the key will be the data)
  * The tree will have the following Binary Search Tree structure/property: for each node $u$, *every* key in $u$'s left tree will have a key value LESS than $u$, every node in $u$'s right tree will have a key value *greater* than $u$
  * To find an element, you simply need to traverse one way or the other until you find it (similar to binary search)
  * All searches will be $O(d)$ where $d$ is the depth of the tree
  * Observe: if the depth is "efficient" $d = O(\log{(n)})$ then searching is efficient
  * However this is not a guarantee



```text









```
