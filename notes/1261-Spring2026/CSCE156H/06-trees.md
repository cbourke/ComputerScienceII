
# Trees & Tree-Based Data Structures
## CSCE 156H - Computer Science II
### Spring 2026

## Introduction

* Data *structures* are good because *structure* can be exploited for efficient operations
* Motivation: want a data structure that supports **efficient**:
   * CRUD
   * Retrieval/search
   * Insertion
   * Deletion
* Linked Lists:
  * Insert/delete at the head/tail in constant time: $O(1)$
  * Arbitrary insertion/index-based search: $O(n)$
* Array-based lists:
  * Arbitrary index-based search: $O(1)$ (random access)
  * If sorted, we can exploit that structure using binary search $O(\log{n})$
  * Inserting/deleting from the start: $O(n)$
* Stacks/Queues/Deques:
  * ALL operations are *always* efficient
  * This was only because they were *restricted access* data structure
  * All operations are $O(1)$ but we cannot do arbitrary CRUD
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations!

## Trees

* A *graph* is a collection of *nodes* (or vertices) and *edges* that connect nodes
  * $G = (V,E)$, $V$ is the vertex set, $E$ is the edge set (a set of pairs)
  * Generally we use $n$ to denote the number of vertices $|V| = n$
  * We generally use $m$ to denote the number of edges, $|E| = m$
  * Edges are *undirected*: $(a, b) = (b, a)$
  * Observation: the maximum number of edges in a graph with $n$ nodes is: $m \leq {n\choose 2} = \frac{n(n-1)}{2} = O(n^2)$ (`{n\choose 2} `)
* A *tree* is a *connected*, *acyclic* graph (a graph with no cycles)
  * Connected means: from any vertex/node you can get to any other vertex or node through some *path*
  * Acyclic: there are no cycles (paths that start/end at the same vertex)
  * A *path* in a graph is a sequence of connected vertices
  * The *length* of  path is the number of edges on it = number of vertices on it $- 1$
  * We will only consider *simple* paths: those that do not traverse an edges more than once
  * A *cycle* is a path that begins and ends at the same node
  * The *length* of a cycle is equal to the number of edges on it = the number of vertices/nodes on it
  * A disconnected tree is called a "forest" (not going to consider these)

### Observations

* In a tree with $n$ nodes, there are exactly $n-1$ edges
  * Removal of any edge will disconnect the tree (forest)
  * Any additional edges will *induce* (create) a cycle
  * In a tree, $m = O(n)$
* In a tree, the number of paths between any two vertices is exactly **one** (There is exactly one path between any pair of nodes)

### Orientation

* Trees can be oriented top to bottom and left to right
* The top is a single node called a *root*
* The nodes under another node are called *children*
* The node above a node is its *parent*
* Any node with zero children is called a *leaf*
* There are parent-child relations (ancestors, descendants, grandchildren, grandparent)
* More structure: suppose that each node is limited to at most 2 children (no child, 1 child, 2 children)
  * You can have a left and/or right child (oriented left-to-right)
  * Neither
  * Left child, no right child
  * Right child, no left child
  * Both
  * This is called a *binary tree*
* Terminology:
  * The *depth* of a node $u$ is the length of the unique path from the root to $u$
  * The depth of the root node is 0
  * The depth of the tree itself is the maximal depth of any node

### How can we exploit this structure?

* Suppose you had a *full* binary tree: every node was present at every level/depth up until the maximal depth of the tree.
* If we can develop a way to insert/retrieve/delete elements from a binary tree such that
  * all operations are proportional to the depth *and*
  * we can guarantee that the depth is "small": $O(\log{n})$
* A "full" or nearly full binary tree has a guarantee on its depth: $d = O(\log{n})$ where $n$ is the number of nodes
* Unfortunately, there are "skewed" trees where the depth is linear: $d = O(n)$

### Traversals

* To be viable data structures, we need to ensure that all the data in a binary tree is *accessible*
* Not as clear as a linked list: up to two directions for each node!
* We'll look at 4 general traversal strategies that ensure that you can visit/access each node
* Preorder traversal: root-left-right
  * It uses a stack to keep track of subtrees/sub nodes that *still need to be processed*
  * We'll always prefer to go left *then* right
  * Simplest and easiest to implement
* Inorder traversal: left-root-right
  * Binary Search Trees: provides a sorted order
* Postorder traversal: left-right-root
  * Tree Deletion
* BFS = Breadth First Search traversal
  * Uses a queue, but otherwise nearly exact same code as preorder
* Efficiency: each one is designed/intended to process *every* node
  * Nodes are only processed ONCE
  * Nodes may be visited up to three times, but only processed once
  * As a consequence: all of them are $O(n)$
* Original goal: to have efficient operations for search/retrieve and insert/delete

## Binary Search Trees

* Generally we store elements in a tree node, you can also impose additional structure on that data: that it be *ordered*
* Binary Search Tree: all nodes have a *key* element, $k$
  * Every node in the left tree of $k$ is *less* than $k$
  * Every node in the right tree of $k$ is *greater* than $k$
  * This property holds for *every* node
* Simplification:
  * For purposes, all keys will be integers
  * In Java all keys are `int` types: `hashCode()`
  * We will assume that all keys are *unique*: there is always a way to break ties (memory address, surrogate database key, etc.)

### Operations

* CRUD
  * Create: insert stuff into a BST
  * Retrieve: search a BST
  * Destroy: remove a node from a BST
  * ALL OPERATIONS **MUST** preserve the BST property

* Search:
  * You always start at the root
  * You make a comparison and traverse either left or right depending on your key
  * Until: you find what you are looking for
  * OR until: you fall of the edge of a tree (unsuccessful search)
  * Observation: at most $d+1 = O(d)$ key comparisons are made
  * Recall that you can have "skewed" trees where $d = O(n)$ and so search could be inefficient, $O(n)$
  * However, if it is "balanced" then $d = O(\log{n})$

* Delete
  * Step 1: Find the element (if no such element: noop or exception, etc.)


## Heaps

* Motivation: BST are not necessarily *balanced*
* BSTs in general may have $d = O(n)$ which kills efficiency on all of the operations
* Solution: "balanced" BSTs, AVL, 2-3/B-trees, Red-Black trees; each guaranteeing that $d = O(\log(n))$
* Our solution: a *Heap* data structure: it is a binary tree that has the following properties:
  * It is *full*: every child is present at every level except for possibly the last (deepest) level but at that level, all nodes are "full to the left"
  * It satisfies the *heap property*: the key of every node is less than *both* its children (min heap)
* Observations:
  * This is *not* a general purpose data structure: searching would require $O(n)$
  * The minimum element is always at the root
  * The fullness property guarantees that the depth of a heap is logarithmic: $O(\log{(n)})$
* Two basic operations:
  * Get and remove the minimum element
  * Add an element
  * This ends up being a *restricted access* data structure
* Get and remove the minimum element
  * Save off the root element to return it
  * Replace the root with the "last" element (the element at the lowest level, all the way to the right)
  * Heapfiy/fix the heap: swap the element with the minimum of is left/right child until a) the heap property is satisified or b) you reach a leaf node
  * $O(d) = O(\log{n})$ (this assumes that you have free or efficient access to the "last" node)
* Add an element
  * preserve the fullness property *first*
  * adding the new element to the "last" spot: the next available slot at the deepest level, all the way to the left
  * Swap with a parent all the way up the tree until a) the heap property is satisfied or b) you reach the root
  * $O(d)$ comparisons are made; this is $O(\log{n})$ because of the fullness property!
  * But: this assumes you have "free" access to the "next" available slot
* Implementation:
  * Use a basic array
  * Ignore index 0
  * Each element stored at index $i$ has a left child at $2i$ and right child at $2i+1$, parent at $\lfloor\frac{i}{2}\rfloor$
  * Random access means you can "walk" along the tree efficiently.
  * Keeping track of the size $n$ means you have access to the last element (index: $n$) and the next available spot: index = $n+1$
* Implementation?
  * Check out Lab 15
* Applications
  * Efficient priority queue
    * Minimum (max priority) is always at the top; you can get and remove it $O(\log{n})$
    * Enqueueing something is also $O(\log{n})$
  * Sorting
    * Put stuff in, take stuff out, magically sorted!
    * $O(n\log{(n)})$

```text









```
