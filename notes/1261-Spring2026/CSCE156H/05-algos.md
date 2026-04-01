
# Algorithms & Algorithm Analysis
## CSCE 156H - Computer Science II - Spring 2026

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
  * Power consumption
  * Network bandwidth or throughput
  * Circuit: number of wires/gates
  * Idleness/utilization: Virtualization
* These are important *engineering* concerns and considerations
* Algorithms are thousands of years old; computers are only decades old
* The behavior and efficiency of algorithms is *invariant* over time and the type of hardware you use
* From a theoretical perspective we need an *abstract* notion of "resource" that is not tied to a particular computing model
* What matters is the performance of a particular algorithm with respect to the *input size* and how the performance behaves as you give it larger and larger inputs $n \rightarrow \infty$
* Algorithms are *indepedent* of the hardware they are run on
  * An algorithm does not suddenly become "efficient" just because we upgrade our hardware
  * Individual operations (add, multiply, compare) become faster on newer hardware, but the *number* of operations stays the same
  * The rate of growth is an *invariant* of the algorithm: it classifies its efficiency 3000 years ago as well as 3000 years into the future
  * Larger inputs lead to slower performance = more resources

## Pseudocode

* pseudo = fake, code = code
* An algorithm is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, it is not a snippet of code
* A general outline that looks kinda-like code
* Good pseudocode **guidelines**:
  * Makes use of plain English
  * Makes use of mathematical expressions
  * Provides the necessary details without too many unnecessary details (Goldilocks)
  * There are no "rules" for pseudocode, just guidelines

### Examples

* Linear Search: given a collection of elements $A = \{a_1, a_2, \ldots, a_3\}$ and a key element $k$, determine if $k \in A$ ($k$ is in the collection $A$)

## Algorithm Analysis

1. Identify the input
2. Identify the input size, $n$
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size
5. Provide a Big-O analysis

### Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases: it is less clear
* Example: What if you have multiple inputs?
  * Keep things simple: focus only on one
  * You choose the most relevant or the input that has the biggest impact on the performance of the algorithm.
  * Ex: linear search: the size of the collection influences the for-loop performance far greater than the single key
  * Ex: Euclid's GCD algorithm: the input is two integers, WLOG = Without Loss of Generality, focus on the larger of the two, assume that $a \geq b$ and then focus on $a$

### Identify the Input Size

* Subtle but important and may be tricky!
* Obvious: collection (list, array, set, map, etc.): the size of the input is the number of elements in the collection (cardinality)
* Matrix: $n \times m$
  * WLOG: assume that $n \geq m$ and so $n\cdot m \leq n^2$ (focus on $n$)
  * WLOG: set $N = m\cdot n$ and then $N$ is  your input size
* Input is a single number: $x$
  * What is the "Size" of a single number?
  * The size is the number of symbols required to represent that number: base 10 or base 2
  * Mathematically: the size of a number is the magnitude of the number
  * The number of bits required to represent a number is $\log_2{(n)}$ but as self respecting Computer Scientists we just write $\log{(n)}$

### Elementary Operation

* Ultimately: we want to focus on ONE operation to analyze
* We want a *simple* analysis
* Most of time: choose the  
  * Most common
  * Most expensive
  * Most relevant
* Generally, we do NOT consider any operation necessary for the control structure of the algorithm
* Examples:
  * Comparisons (searching or sorting algorithms)
  * Arithmetic operations: additions/substractions or mults/divisions
  * Linked list operations: node traversals (similar for graph and tree operations)

### Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function* ($t$ is for time):
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
  * $\mathbb{N}$: captures the input size
  * Input sizes are NOT negative
  * Input sizes are not fractional
  * $\mathbb{R}^+$ (`\mathbb{R}^+`) is our codomain because we do not want to consider "negative" resources (no such thing as time travel!)
* We really only consider monotone increasing functions, ie NOT $\frac{1}{n}$ or $\sin{n}$
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* There is no clear guideline: everything is going to be slightly different
* You should generally focus on the *worst case* scenario



```text








```
