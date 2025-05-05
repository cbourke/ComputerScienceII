
# Trees & Tree-Based Data Structures
## CSCE 156H - Computer Science II
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
  * Arbitrary index-based search: $O(1)$
  * If sorted, you can also use binary search for fast arbitrary element search (key-based)
  * Inserting/deleting from the start: $O(n)$
* Stacks/Queues/Deques:
  * ALL operations are *always* efficient
  * BUT: they were *restricted access* data structures
  * All operations are $O(1)$ but we cannot do arbitrary CRUD
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations!

## Trees

* A graph is a collection of *nodes* (or vertices) and *edges* connecting nodes
  * The vertex/node set is denoted $V$ and the size $|V| = n$
  * The edge set is denoted $E$ and the size is denoted $|E| = m$
  * All graphs will be simple: no multiedges between any two nodes, no self-loops, etc.
  * All graphs will be *undirected*: $(a, b) = (b, a)$
* A *tree* is a *connected*, *acyclic* graph (a graph with no cycles)
  * A *path* in a graph is a sequence of connected vertices
  * The *length* of a path is the number of edges on the path = the number of nodes minus 1
  * A *cycle* is a path that begins/ends at the same node
  * The length of a cycle is equal to the number of edges on it = the number of nodes on it
  * all paths/cycles will be *simple*

### Observations

* In a tree with $n$ vertices/nodes, there are exactly $n-1$ edges
  * Any additional edge would *induce* (create) a cycle
  * Removal of any edge will disconnect it and create a forest
* In a tree, between any two nodes there is *exactly* one and only one path!

### Orientation

* Trees can be oriented: top to bottom, left to right
* A the top is a single node called the *root*
* This induces a parent-child relation (ancestors, descendants)
* Any node with zero children is called a *leaf*
* More structure: suppose that each node is limited to at most 2 children (no child, 1 child, 2 children)
* Orient children: left to right
  * Neither children (leaf)
  * Left child, no right child
  * Right child, no left child
  * Both children
* This is known as a *binary tree*
* The *depth* of a node $u$ is the length of the unique path from the root to $u$
  * The depth of the root node is $0$ by convention
  * The depth of the tree itself is the maximal depth of any node

### How can we exploit this structure?

* Suppose you store elements/data in your nodes, total of $n$ nodes
* Suppose you had a *full* binary tree: every node was present at every level/depth up until the maximal depth of the tree.
  * What is the depth of this tree in terms of $n$?
  * $d = O(\log{n})$
  * __IF__ we can come up with a way to do CRUD that is proportional to the depth of the tree AND $d = O(\log{n})$: we win!  
  * However, a binary tree may not be full...

### Implementation (Java)

* Demonstration

### Traversals

* YOu need a way to access data in a binary tree
* You need to be systematic about it
* Preorder traversal: root-left-right
  * It uses a stack to keep track of subtrees/sub nodes that *still need to be processed*
  * We'll always prefer to go left *then* right
  * Simplest and easiest to implement
* Inorder Traversal: left-root-right
  * Binary Search Trees: provides a sorted order
* Postorder traversal: left-root-right
  * Tree Deletion
* Each one has a recursive solution
  * You simply change when you process it, before, middle or after recursing!
  * Still prefer in-memory stacks, don't abuse the call stack!
* BFS: breadth first search: top to bottom, left to right
  * Uses a queue, but otherwise nearly exact same code as preorder
* Efficiency: each one is designed/intended to process *every* node
  * Nodes are only processed ONCE
  * Nodes may be visited up to three times, but only processed once
  * As a consequence: all of them are $O(n)$
  * $n$ nodes, $n-1$ edges
  * IF you consider a node *traversal* your elementary operation: it is still $O(n)$
* Original goal: to have efficient operations for search/retrieve and insert/delete

## Binary Search Trees

* Generally we store elements in a tree node, you can also impose additional structure on that data: that it be *ordered*
* Binary Search Tree: all nodes have a *key* element, $k$
  * For simplicity, our keys will all be integers
  * All our keys will be *unique*: you can always find a tie breaker: a memory address, a surrogate database key
  * For demonstration the key and the data will be the same
* Binary Search Tree Property: For every node with key value $k$: ALL nodes in its left subtree have key values LESS than $k$.  ALL nodes in its right subtree have key values GREATER than $k$


### Basic Operations: CRUD

* Searching:
  * Start at the root
  * You make comparisons to go left or right until you find what you are looking for
  * Complexity: at most $d+1$ comparisons which is $O(d)$
  * However, there is no guarantee that $d = O(\log{n})$, it could be "degenerate" (a linked list) and $d = O(n)$

* Insert:
  * You start the root
  * YOu do a basic search until you a) find the element (noop = no operation) or b) find the "space" in which it *would* have existed, insert it as a new leaf at that location
  * Insert as a new leaf node; taking care of the left/right child of the parent and the parent reference

* Delete
  * Step 1: Find the element (if no such element: noop or exception, etc.)
  * Case 1: the node is a leaf
    * No problems with deleting it
    * Make its parent point to null (careful: is the node a left or right child?)
  * Case 2: the node has one child
    * We can "promote" the child up
    * It is no different than deleting in a linked list
  * Case 3: both children are present
    * Go to the left subtree and find the maximum value; promote it and delete that node
    * Go to the right subtree and find the minimum value; promote it and delete that node

* Efficiency:
  * Elementary operations: comparisons, node walks
  * Searching: $O(d)$
  * Insertion: $O(d)$ (you search for it, $O(d)$, a constant number of reference changes)  
  * Deletion: two searches: $2*O(d) = O(d)$, delete of a node with only 1 child
  * All operations are $O(d)$
  * However, there is no guarantee that $d = O(\log{n})$
  * One solution: *balanced* binary search trees: AVL Trees, Red-black trees, $b$-trees, a couple of dozen others with various properties and applications, each one guarantees that $d = O(\log{n})$

## Heaps

* Motivation: BST are not necessarily *balanced*
* BSTs in general may have $d = O(n)$ which kills efficiency on all of the operations
* Our solution: a *Heap* data structure: it is a binary tree that has the following properties:
  * It is *full*: every child is present at every level except for possibly the last (deepest) level but at that level, all nodes are "full to the left"
  * It satisfies the *heap property*: the key of every node is less than *both* its children (min heap)
* Observations:
  * The minimum element is always at the root
  * The fullness property guarantees that the depth of a heap is logarithmic: $O(\log{(n)})$
  * Not a general purpose data structure: we cannot arbitrarily insert/search/delete elements efficiently
* Two basic *efficient* operations
  * Get and remove the minimum element
  * Insert an element into the heap
* Insert an element into the heap:
  * Always insert at the next available slot (lowest level, the left most available slot)
  * Yes, you can do this efficiently on a tree (using Math!)
  * Then: heapify (fix the heap): you swap the new value with its parent until the heap has been fixed (heap property is satisfied)
  * At most, you need to perform $d$ swaps/comparisons: $O(d) = O(\log{n})$
* Get and remove the minimum element
  * Save off the root and "remove" it
  * Replace the root with the "last" element (assuming you can get the last element efficiently)
  * HEapfiy/fix the heap: swap the element with the minimum of is left/right child until a) the heap property is satisified or b) you reach a leaf node
  * $O(d)$ for swaps/comparisons
  * $O(d) = O(\log{n})$ (this assumes that you have free or efficient access to the "last" node)
* Implementation
  * A tree (nodes, left/right/parent) is possible, but a bit complex (and requires some mathy stuff to find the next gap/last element)
  * A better implementation is an array
* Applications
  * Priority Queue: things are waiting, but the thing with the highest/lowest (max/min heap) priority is the next thing out (efficient for both operations, this is a restricted access data structure)
  * Sorting

```text







```
