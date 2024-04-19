
# Trees & Tree-Based Data Structures
## CSCE 156 - Computer Science II
### Spring 2023

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
* Stacks/Queues:
  * Efficient push/pop poll/offer operations
  * BUT: restricted access data structures
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations

## Trees

* A graph is a collection of *nodes* (or vertices) and *edges* connecting nodes
* A *tree* is an *acyclic* graph (a graph with no cycles)
  * A *path* in a graph is a sequence of connected vertices
  * A *simple cycle* is a path that begins/ends at the same vertex
  * THe length of a path or cycle is the number of edges on it
  * A tree has no cycles
  * A disconnected tree is called a "forest"

* Observations
  * For a connected tree with $n$ vertices, there are $m = n-1$ edges
  * Between any two nodes there is *exactly* one path

## Orientation

* Trees can be oriented: top to bottom left to right
* A the top is a single node called the *root*
* This induces a parent-child relation (ancestors, descendants)
* Suppose we add even more structure: that for any node there can be at most *two* children: such a tree is a *binary tree*
* In a binary tree you can have 0 children (leaf) 1 child or 2 children
  * Further structure: orient the two children to left and right
* Terminology/definitions
  * The *depth* of a node $u$ is the length of the unique path from the root to that node
  * By convention, the depth of the root is 0
  * The depth of a tree itself is the maximal depth of any node

### How can we exploit this structure?

* Generally we will store data in each node
* Operations (search, insert, delete) will always begin at the root node
* IF we can define operations with efficiency proportional to the depth, we *may* be able to make them very efficient

### Implementation (Java)

* Demonstration
* Each of the traversal strategies is $O(n)$
* We need more structure

## Binary Search Trees

* Generally we store elements in a tree node, you can also impose additional structure on that data: that it be *ordered*
* Binary Search Tree: all nodes have a *key* element, $k$
* For every node, the key value of all nodes in its "left-sub-tree" have a key value strictly less than $k$
* For every node, the key value of all nodes in its "right-sub-tree" have a key value strictly greater than $k$

### Implementation & Operations

* Demonstration

## Heaps

* Motivation: BST are not necessarily *balanced*
* BSTs in general may have $d = O(n)$ which kills efficiency on all of the operations
* Solution: "balanced" BSTs, AVL, 2-3/B-trees, Red-Black trees; each guaranteeing that $d = O(\log(n))$
* Our solution: a *Heap* data structure: it is a binary tree that has the following properties:
  * It is *full*: every child is present at every level except for possibly the last (deepest) level but at that level, all nodes are "full to the left"
  * It satisfies the *heap property*: the key of every node is less than *both* its children (min heap)
* OBservations:
  * The fullness property guarantees that the depth of a heap is logarithmic: $O(\log{(n)})$
  * The minimum element is always at the root
  * if the operations can be made $O(d)$ then they are efficient
  * NOT a general purpose data structure
* Two basic operations:
  * Get and remove the minimum element
    * Save off the element to return it
    * Replace the root (minimum) with the "last" element at the bottom of the heap all the way to the right
    * Heapify downwards: make comparisons and swap with the MINIMUM child until you reach a leaf or the heap property is satisfied
    * In any case, the operation is $O(d) = O(\log{(n)})$
  * Add an element:
    * Insert at the "next" available slot at the bottom of the heap (satisfies the fullness property)
    * Heapify: compare it with its parent and swap until the heap property is satisfied or you reach the root node
    * In any case, the operation is $O(d) = O(\log{(n)})$
* A restricted access data structure!
* A typical application of a heap is a *priority queue*

```text















```
