
# Algorithms & Algorithm Analysis
## CSCE 156H - Computer Science II
### Spring 2024

### Motivating Demonstration


* Quadratic regression (Correlation Coefficient: 0.994, $\mu s$):
    $$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * $n = 10M$: 17.59 hours
  * $n = 100M$: 74 days
  * $n = 1B$: 20 years
* Array-based:
  * $n = 1M$: 0.009s
  * $n = 10M$: 0.030s
  * $n = 100M$: 0.300s
  * $n = 1B$: ~ 3s
* Performance of an index-based retrieval operation (`get(i)`) on a list data structure
  * Linked List
  * Array List

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Time: CPU cycles/CPU time/etc
  * Memory
  * Power/heat dissipation
  * Network bandwidth or throughput
  * Circuit: number of wires/gates
  * Idleness/utilization: virtualization
* These are important *engineering* concerns and considerations
* Algorithms are thousands of years old; computers are only decades old
* The behavior and efficiency of algorithms is *invariant* over time and the type of hardware you use
* From a theoretical perspective we need an *abstract* notion of "resource" that is not tied to a particular computing model
* We want to analyze algorithms from an *abstract* general perspective
* What matters is the performance of a particular algorithm with respect to the *input size* and how the performance behaves as you give it larger and larger inputs $n \rightarrow \infty$
* Algorithms are *indepedent* of the hardware they are run on
  * Algorithms predate the modern computer by thousands of years!
  * Algorithms do not suddenly become efficient because you upgrade your hardware
  * Individual operations become faster and more efficient, but the *number* of operations remains the same
  * The rate of growth of an algorithm remains *invariant* regardless of the hardware
  * larger inputs lead to slower performance = more resources

## Pseudocode

* An algorithm is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, it is not a snippet of code
* Good pseudocode:
  * Makes use of plain English
  * Makes use of good mathematical notation
  * Doesn't use any specific language constructs
  * It provides the necessary details without giving unnecessary details

## Algorithm Analysis

1. Identify the input
2. Identify the input size
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size
5. Characterize the algorithm's behavior (growth rate) using Big-O analysis

### Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases, the choice may not be clear
* In general: keep it simple!  Focus on ONE of the inputs
  * The one that is the most relevant to the algorithm's performance
  * OR the one most relevant to your resource measure
* If you have multiple of the *same* type; choose one
* Example: Euclid's GCD algorithm $gcd(a, b)$
  * WLOG = Without Loss of Generality, assume that $a \geq b$
  * Then choose $a$ as your single input

### Identify the Input Size

* Subtle and not always obvious
* Collection (set, list, array): the number of elements in the collection (cardinality)
* Other items:
  * Matrix: $n \times m$; WLOG: $n \geq m$, $n\cdot m \leq n^2$, then focus on $n^2$ or even simplify it to $N = n^2$ or just $n$
  * Image: same, assume the larger of teh two dimensions
  * What about a single number, $a \in \mathbb{Z}$
  * Tricky: a single number, $n$; number of bits it takes to represent $n$: $\log_2(n) = \log(n)$

### Elementary Operation

* Ultimately: we only want to focus on ONE operation in an algorithm
* You generally want to choose the most:
  * expensive or
  * most common
  * Most relevant operation
* You do NOT generally consider operations responsible for the control flow of an algorithm (for or while loops)
* Examples:
  * Comparisons
  * Arithmetic operations: addition/subtract, multiplication/division
  * Linked List operations: node traversal

### Analysis


* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function* ($t$ for time):
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$

* $\mathbb{N}$ (`\mathbb{N}`): quantifies the input size,
  * There are no negatively sized inputs
  * No fractional input sizes
* $\mathbb{R}^+$ (`\mathbb{R}^+`) is our codomain
  * There is no algorithm that "pays" you
  * There is no algorithm that takes negative time or negative memory
  * You can have fractional values: 3.5 seconds, 89.4MB
* It may require some art, summations, calculations, formulas, etc.
* In general, you want to assume the *worst case* scenario: you want to provide an *upperbound* on the worst running time or other resource that the algorithm could cost you.

### Examples

* Selection Sort
* Insertion Sort

```text











```
