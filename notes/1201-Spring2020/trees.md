
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
    * O(1) time operations for insertion/retrieival/deletion from the head and tail
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

## More





