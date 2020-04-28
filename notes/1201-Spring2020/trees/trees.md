
# Trees & Tree-Based Data Structures
## CSCE 156/156H - Computer Science II 
### Spring 2020

## Motivation

* Quest for a data structure that can hold elements and provide efficient:
  * Retrieval/Search
  * Insertion
  * Deletion
* Lists:
  * Linked Lists:
    * O(1) time operations for insertion/retrieval/deletion from the head and tail
    * O(n) for most other arbitrary operations
  * Array-based: 
    * If sorted, O(log(n)) search feature
    * O(1) index-based retrieval
    * O(n) insertion/deletion
* Alternative: Trees have the potential to offer O(log(n)) complexity for all three operations

## Introduction to Trees

* A tree is an acyclic graph (a graph with no cycles)
* A tree is a collection of:
  * Nodes (vertices hold data)
  * Edges (undirected: there is no orientation)
* A tree can be *oriented*
  * At the top of the tree is a single node called the *root*
  * At the bottom of the tree are *leaves*
  * A node that is neither a root nor a leaf is an *internal node*
  * Each node may have a *parent* and/or *children*
  * You can also use *ancestor* and *descendant* terminology
* A *binary* tree is an oriented tree such that every node has at most 2 children
  * Orientation can also be done horizontally: *a right child* a *left child*
* Additional Terminology
  * A *path* in a tree is a sequence of edges connecting nodes
  * The *length* of a path is the number of edges on it
  * We only consider *simple paths*: a path that only traverses an edge or node at most once
  * The *depth* of a node u is the length of the path from the root to u
  * The *depth of a tree* itself is the maximal depth of any node in the tree
    * A single node tree ("seed") has depth 1
    * An empty tree has depth -1 (by convention)

## Observations

* Because a binary tree is acyclic and connected, there is exactly one path between any two nodes, there is exactly one path from the root to any node
* Even more structure: at any depth, there is a maximum number of nodes
  * Consider a "full" binary tree of depth d
  * A full binary tree with n elements will have depth d = O(log(n))
  * *If* we can establish the basic operations (insert, update, delete, etc.) and they are proportional to the depth of a tree, then we've achieved an O(log(n)) complexity for all operations!

## Basic Tree Traversals

* In general, you start at the root
* Goal: be able to enumerate/view/traverse every single node without missing any
* Preorder traversal
  * Recursively visits nodes in a root-left-right manner
* Inorder traversal
  * Recursively visits nodes in a left-root-right manner
* Postorder traversal
  * Recursively visits nodes in a left-right-root manner

### Implementations

* Basic Java Implementation
* Recursive Implementations
* Stack-based Implementations
* Tree Walk
* Breadth First Search

## Binary Search Trees

* A *binary search tree* is a binary tree such that every node u holds a key k such that
  * *Every* node in u's left sub-tree has a key value less than k
  * *Every* node in u's right sub-tree has a key value greater than k

* Three basic operations:
  * Search (find a particular node)
  * Insert elements (preserving the BST property)
    * You always insert a new element as a leaf
    * Existing elements (inserting duplicates) can be treated as a no-op
  * Delete elements
    * Step 1: find the element
    * Step 2: delete
      * Case 1: if it is a leaf, simply rearrange the references
      * Case 2: if it has only one child, simply promote that child up
      * Case 3: if it has *both* children: 
        * Retrieve the minimum element in the right-subtree or the maximum element in the left subtree and move its key to replace the key you're deleting
        * Remove the "promoted" key (which is guaranteed to have at most one child)


* All three operations will be O(d) where d is the depth of the BST
  * If the BST is balanced, then d = O(log(n)) where n is the number of keys, thus giving us O(log(n)) performance!
  * Unfortunately, in general, there is no guarantee that the depth will be O(log(n))
  * There can be degenerative cases where the d = O(n)

## Heaps

* Motivation: BST are not necessarily *balanced*
* Balanced BSTs to exist: AVL, 2-3, Red-Black, etc. 
* Work by efficiently *rebalancing* nodes
* Alternative: Heaps
* A heap is a binary tree that has the following properties
  * It is is a *full* binary tree: at every level every node is present except possibly the last row which is full-to-the-left ()
  * It satisfies the "heap property": the key in any node is less (min heap) than *both* of its children
* Observations
  * Fullness guarantees the depth of the tree is logarithmic d = O(log(n))
  * The root node (top of the heap) contains the minimum element in the heap
* Heaps support two basic operations:
  * getMin - retrieves and removes the minimum element from the heap
  * insert - adds an element to the heap

* Applications
  * Priority Queue: insert things (according to a priority), remove the next "highest" priority element: offers both efficient O(log(n)) operations 
  * Application: heap sort: put stuff into a heap, take stuff out of a heap!








