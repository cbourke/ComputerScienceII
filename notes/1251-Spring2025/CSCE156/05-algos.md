
# Algorithms & Algorithm Analysis
## CSCE 156 - Computer Science II - Spring 2025

### Motivating Demonstration

* Performance of an index-based retrieval operation (`get(i)`) on a list data structure
  * Linked List
  * Array List
* The basic operation: `get(i)` "cost" much more when using a linked list
  * Linked list: requires starting at the beginning and making `i` traversals, in total: $\approx n^2$ total traversal operations
  * Array list: you can use random access and getting the `i`th element is only a constant number of operations; in total there will be $n$ operations
* Array-based: linear behavior
  * $n = 1M$: 0.009s
  * $n = 10M$: 0.030s
  * $n = 100M$: 0.300s
  * $n = 1B$: ~ 3s
* Linked list: quadratic regression (Correlation Coefficient: 0.994):
$$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * $n = 10M$: 17.59 HOURS
  * $n = 100M$: 74 DAYs
  * $n = 1B$: 20 years

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Processing/CPU/GPU time
  * Memory
  * Power
  * Network bandwidth or throughput
  * Circuit: number of wires/gates
  * Idleness/utilization: Virtualization
* These are important *engineering* concerns and considerations
* Algorithms are thousands of years old; computers are only decades old
* The behavior and efficiency of algorithms is *invariant* over time and the type of hardware you use
* From a theoretical perspective we need an *abstract* notion of "resource" that is not tied to a particular computing model
* What matters is the performance of a particular algorithm with respect to the *input size* and how the performance behaves as you give it larger and larger inputs $n \rightarrow \infty$
* Algorithms are *indepedent* of the hardware they are run on
  * Algorithms are thousands of years old (GCD)
  * An algorithm does not suddenly become "efficient" just because we upgrade our hardware
  * Individual operations (add, multiply, compare) become faster on newer hardware, but the *number* of operations stays the same
  * The rate of growth is an *invariant* of the algorithm: it classifies its efficiency 3000 years ago as well as 3000 years into the future
  * Larger inputs lead to slower performance = more resources

## Pseudocode

* pseudo = fake, code = code
* An algorithm is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, it is not a snippet of code
* A general outline that looks kinda-like code
* Good pseudocode:
  * Makes use of plain English
  * makes good use of mathematical notation
  * Provides necessary details without too many unnecessary details
  * There are no "rules" for pseudocode, just guidelines

## Algorithm Analysis

1. Identify the input
2. Identify the input size, $n$
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size
5. Provide a Big-O analysis

### Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases: it is less clear
* Ex: if you have *multiple* inputs
  * Keep things simple: focus only on one
  * You choose the most relevant or the input that has the biggest impact on the performance of the algorithm
  *  Example: linear search has both a collection and a key, choose the collection: its size will have the greatest impact on the performance of the algorithm
  * Example: Euclid's GCD algorithm (GCD = Greatest Common Divisor): you are given two integers as inputs $a, b$
  * WLOG = Without Loss of Generality, we can choose the "larger" of the two inputs: assume that $a \geq b$, then choose $a$

### Identify the Input Size

* Subtle but important and may be tricky!
* Obvious: a collection (set, list, array, etc.): the size of the collection (number of elements in it, cardinality)
  * Matrix: $n \times m$: WLOG assume that $n \geq m$ so that size of the matrix is $n \cdot m \leq n \cdot n = n^2$; to simplify further, you can assume that $N = n^2$, so the size is $N$
  * A single number (integer): $a$, what is the "size" of a single number?
  * The input size of a number (in general) is the number of bits required to represent it: $\log{(x)}$

### Elementary Operation

* Ultimately: we only want to focus on ONE operation in an algorithm
* We want a simple analysis
* We choose the operation that is the:
  * Most common
  * Most expensive
  * Most relevant
* Generally you do NOT consider anything related to the control structure of the algorithm  
* Examples:
  * Comparisons (searching or sorting algorithms)
  * Arithmetic Operations: additions/subtractions, multiplications/divisions
  * LInked list operations: node traversal (similar for graph algorithms)

### Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function* ($t$ is for time):
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
  * $\mathbb{N}$ (`\mathbb{N}`) is our domain because we do not have "negatively" sized nor "fractional" input sizes
  * $\mathbb{R}^+$ (`\mathbb{R}^+`) is our codomain because we do not want to consider "negative" resources
* We really only consider monotone increasing functions, ie NOT $\frac{1}{n}$ or $\sin{n}$
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* There is no clear guideline: everything is going to be slightly different
* You should generally focus on the *worst case* scenario


LEFT OFF: binary search analysis

```text












```
