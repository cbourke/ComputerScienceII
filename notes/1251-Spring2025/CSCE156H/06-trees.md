
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

```text







```
