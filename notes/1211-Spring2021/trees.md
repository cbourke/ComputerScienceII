
# Trees & Tree-Based Data Structures
## CSCE 156 - Computer Science II 
### Spring 2021

## Motivation

* Quest for a data structure that can hold elements and provide efficient:
  * Retrieval/Search
  * Insertion
  * Deletion
* Lists:
  * Linked Lists:
    * $O(1)$ time operations for insertion/deletion or retrieval from the head or tail
    * $O(n)$ time operation for an arbitrary operation or index-based operation
* Array-based:
  * $O(1)$ index-based retrieval due to random access
  * Index-based insertion/deletion: $O(n)$ because you have to shift elements
  * If sorted: you can exploit binary search for arbitrary searches
* Alternative: Trees have the potential to offer $O(\log(n))$ performance for all three operations

## Introduction to Trees

* A graph is a collection of *nodes* (or vertices) and *edges* connecting nodes
* A tree is an acyclic graph (a graph with no cycles)
* A *simple* cycle is a path in a graph that starts and ends the the same node never having traversed the same edge more than once
* A *path* in a graph is a series of nodes that are connected by edges
* Observations:
  * A *connected* tree with $n$ nodes will have $n-1$ edges
  * A tree *may* be "disconnected": some nodes may not be reachable from others, also known as a forest
  * In a connected tree, there is exactly one path connecting any pair of vertices

### Binary Trees

More definitions:

* We can orient trees top-to-bottom and left-to-right
  * parent-child relations
* The *length* of a path is the number of edges on it
* We only consider *simple paths*: a path that only traverses an edge or node at most once
* The *depth* of a node u is the length of the path from the root to u
* The *depth of a tree* itself is the maximal depth of any node in the tree
  * A single node tree ("seed") has depth 1
  * An empty tree has depth -1 (by convention)

* A *Binary Tree* is a tree such that every node has *at most* 2 children: left child, right child
  * A leaf is a node with no children
  * a binary tree node may have 0, 1 or 2 children
* Observations:
  * Because of this restriction, there is a limit to how many nodes may be present at any level
  * If the binary tree is essentially full, then we have that its depth is $d = O(\log(n))$
  * IF we can formulate basic operations: find, insert, delete, update with performance that is *proportional* to the depth $d$, then all operations *may* have complexity $O(\log{n})$
  
## Implementation (Java)  

* You can define a `TreeNode` that holds and element and 3 references to other tree nodes (parent, left, right)
* The tree itself is represented simply by the root node
* First task: implement some sort of traversal method: given the root, can you visit every single node once?

## Traversals

* In general, you start at the root
* Goal: be able to enumerate/view/traverse every single node without missing any
* Preorder traversal
  * Recursively visits nodes in a root-left-right manner
* Inorder traversal
  * Recursively visits nodes in a left-root-right manner
* Postorder traversal
  * Recursively visits nodes in a left-right-root manner

## Binary Search Trees

* A *binary search tree* is a binary tree such that every node u holds a key k such that
  * *Every* node in u's left sub-tree has a key value less than k
  * *Every* node in u's right sub-tree has a key value greater than k

* Three basic operations:
  * Search (find a particular node)
  * Insert
  * Delete
  
## Binary Heaps

* Motivation: BST are not necessarily *balanced*
* Balanced BSTs to exist: AVL, 2-3, Red-Black, etc. 
* Work by efficiently *rebalancing* nodes
* A heap is a binary tree that has the following properties
  * It is a *full* binary tree: at every level all nodes are present except for (possibly) the last (deepest) level which is "full-to-the-left"
  * It satisfies the "heap" property: the key in any node is less than (min heap) or greater than (max heap) *BOTH* of its children
* OBservation: 
  * In a max (min) heap, the maximum (minimum) element is always at the root
  * Because of the fullness property, the depth of a binary heap, $d = O(\log{n})$
* Heaps support 2 basic operations:
  * insert - adds ane element to the heap
  * getMin (getMax)
  



```text







```