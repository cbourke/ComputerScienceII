
# Trees & Tree-Based Data Structures
## CSCE 156 - Computer Science II
### Spring 2022

## Introduction

* We like data structures because structure can be exploited for efficient operations
* Motivation: want a data structure that supports efficient:
  * Retrieval/search
  * Insertion
  * Deletion
* Linked Lists:
  * Insertion/deletion from the head or tail: $O(1)$ (constant)
  * Arbitrary insertion/index-based search: $O(n)$ (linear)
* Array-based List:
  * Insertion/deletion from the start of the list: $O(n)$
  * Arbitrary index-based search: $O(1)$
* Stack/Queue:
  * Push/Pop operations were both $O(1)$
  * BUT: restricted-access data structures
* Searching an array (or array-based list): if sorted, we can use binary search to have $O(\log{n})$ search operations
* Ultimate goal: efficient operations for ALL operations!
* Trees provide a *potential* for efficient operations

## Trees

* A graph is a collection of *nodes* (or vertices) and *edges* connecting nodes
* A *tree* is an acyclic graph (a graph with no cycles)
  * A *path* in a graph is a sequence of connected vertices
  * A *simple cycle* is a path in a graph that begins and ends at the same node/vertex without traversing the same vertex or edge more than once
  * The *length* of a path or cycle is the number of edges along that path
  * A tree may be connected or disconnected
  * A *disconnected* tree is a "forest"

### Binary Trees

* You can generally "orient" trees "downward"
* At the top is a single node, called the "root"
* This induces a "parent-child" relationship
* You can also orient nodes in a left-to-right manner
* Further restriction: suppose that a tree node is only allowed at most 2 children (it can have zero, one or two children); this is a *binary tree*
* In a binary tree you can have a *left* child or a *right* child (or both or neither); you can also have a *parent*
  * If a node has no parent it is the *root* of the tree
  * If a node has no children it is called a *leaf*
* Given an orientation of a tree you can talk about the *depth* of nodes
  * THe depth of a node $u$ is the length of the path from the root to the node $u$
  * The depth of a *tree* itself is the maximal depth of any node
* Since trees are acyclic, from one vertex to any other vertex, there is exactly ONE path

### How can we exploit this structure?

* Generally, we will store data in each node
* Operations will always begin at the root and (search, insert, delete) will require traversing from the root to any descendent node
* Observation: in a *full* binary tree, the depth is logarithmic: $d = O(\log{n})$
* **IF** we can make all operations (insertion, deletion, search) proportional to the depth of a tree, then we've achieved our goal!
* It also requires that the tree be (at least nearly) full!
* You can have a binary tree that is NOT full! Skewed trees

### More structure

* Generally we store elements in a tree node, you can also impose additional structure on that data: that it be *ordered*
* Binary Search Tree: all nodes have a *key* element, $k$
* For every node, the key value of all nodes in its "left-sub-tree" have a key value strictly less than $k$
* For every node, the key value of all nodes in its "right-sub-tree" have a key value strictly greater than $k$

### Implementation (Java)

* Demonstration

### Tree Traversals

* In general, you start at the root
* Goal: be able to enumerate/view/traverse every single node without missing any
* Preorder traversal
  * Recursively visits nodes in a root-left-right manner
* Inorder traversal
  * Recursively visits nodes in a left-root-right manner
* Postorder traversal
  * Recursively visits nodes in a left-right-root manner



```text










```
