
# Trees & Tree-Based Data Structures
## ECEN 156 - Computer Science II
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
  * If sorted, you can use binary search to get $O(\log{n})$
* Stacks/Queues:
  * Efficient push/pop poll/offer operations
  * BUT: restricted access data structures
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations

## Trees

* A graph is a collection of *nodes* (or vertices) and *edges* connecting nodes
* Terminology:
  * Edges connect *pairs* of vertices
  * Edges are *unoriented*: $(a, b)$ is the same as $(b,a)$: our graphs will be *undirected*
  * Generally: the number of vertices is referred to as $n$, the number of edges is referred to as $m$
  * THere are no "multiedges" or edge weights
  * A *path* is a sequence of edges from a "source" vertex/node to a "destination" node
    * A path must be *simple*: we do not traverse an edge more than once
    * The length of a path is the number of edges one it
    * A single edge is a path of length 1
    * If there is no path between two vertices you can think of it as of "infinite" length
  * We will only consider *connected* graphs: where all vertices/nodes are connected by at least ONE path
  * A *cycle* in a graph is a path that begins/ends at the same node
* A *tree* is a connected simple graph with no cycles; it is an *acyclic* graph
  * A tree with $n$ vertices/nodes will have $n-1$ edges
  * Observation: between any two nodes there is exactly ONE and ONLY one path


### Orientation

* Trees can be oriented: top to bottom, left to right
* The top is the *root* of the tree
* Nodes may have parents/children (siblings, ancestors/descendants)
* A node with NO children is a *leaf*
* All other nodes are *internal nodes*
* More structure: we will limit the number of children to at most 2
  * Neither (leaf)
  * Left Child
  * Right Child
  * Both
* This is called a *binary tree*
* The *depth* of a node $u$ is the length of the *unique* path from the root to the node $u$
* The depth of the root node is 0
* The depth of the tree itself is the maximal depth of any node

### How can we exploit this structure?

* Suppose you had a *full* binary tree: every node was present at every level/depth up until the maximal depth of the tree.
* If we can develop a way to insert/retrieve/delete elements from a binary tree such that
  * All operations are proportional to the depth AND
  * The depth is "small"
* A "full" or nearly full binary tree has a guarantee on its depth: $d = O(\log{n})$ where $n$ is the number of nodes
  * if you can perform operations proportional to the depth...
  * operations will be efficient: $O(\log{n})$

## Traversals

* Preorder: root-left-right
  * Solution: using a stack
  * Solution: using recursion
* Inorder: left-root-right
* post-order: left-right-root
* All of them have stack-based and recursive solutions
* BFS = Breadth First Search, same as preorder but we use a queue!
* Efficiency:
  * Elementary operation: node traversal
  * Elementary operation: processing the data in the node
  * You only process a node exactly once, $O(n)$
  * If you use traversal: you visit each node at most 3 times, $3n = O(n)$
* We need more structure

## Binary Search Trees

* Generally we store elements in a tree node, you can also impose additional structure on that data: that it be *ordered*
* For our purposes: consider integers (ascending)
* Binary search trees contain data and a *key* (WLOG: assume keys are integers)
* To simplify: we will not allow duplicates
* For every node $u$ with key value $k$, every node in its left sub-tree has a key value *less* than $k$; every node in its right sub-tree has a key value *greater* than $k$

### Implementation & Operations

* Demonstration
* Search
* Insertion
* Deletion

## Heaps

* Heaps are not binary search trees, but they are *balanced binary trees*
  * Guarantee that their depth is $O(\log{n})$
  * There is not a BST property, there is a HEAP property
* Fullness property: every child is present at every level except for possibly the last (deepest) level but at that level, all nodes are "full to the left"
* Heap property: the key of every node is less than *both* its children (min heap)
* OBservations:
  * The fullness property guarantees that the depth of a heap is logarithmic: $O(\log{(n)})$
  * The minimum element is always at the root
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
* Implementation:
  * A tree representation is possible, but finding the "last" element or the "next" available slot is $O(d)$
  * Instead: consider...
* Application: a priority queue
  * Allows $O(\log{n})$ operations
* Application: 




```text













```
