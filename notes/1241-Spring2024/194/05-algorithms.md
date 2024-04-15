# Algorithms & Algorithm Analysis
## ECEN 156
### Spring 2024

### Motivating Demonstration

* Quadratic regression (Correlation Coefficient: 0.994):
    $$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * $n = 10M$: 17.59 hours
  * $n = 100M$ 74 days
  * $n = 1B$: 20 years
* Array-based list:
  * $n = 1M$: 0.009s
  * $n = 10M$: 0.030s
  * $n = 100M$: 0.300s
  * $n = 1B$: ~ 3s

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Time/CPU Cycles
  * Memory
  * Power consumption
  * Network bandwidth/throughput
  * Circuit: number of wires/gates
  * Idleness/utilization: Virtualization/Containerization
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
* Good pseudocode
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
* But in some cases it may not be clear
* Ex: linear search: you are given a collection $A$ and a key $k$
  * WLOG = Without Loss of Generality, simplify your analysis and choose ONE of the inputs (if multiple), the one most relevant to the behavior of the algorithm
* Ex: Euclid's GCD algorithm: Two integers, $a, b$
  * WLOG: focus on the *larger* of the two, suppose $a > b$ and just say the input is $a$
* In general: only focus on ONE input

### Identify the Input Size

* Subtle but important and may be tricky
* Input: a collection; size: the number of elements in the collection
* If your input is a matrix, or an image you could consider *both* dimensions: $n \times m$; WLOG: only consider ONE, $n \geq m$, then consider $n$ or $n^2$ or $N = n^2$
* Tricky: what if the input is a number (integer)?

### Elementary Operation

* Ultimately: we only want to focus on ONE operation in an algorithm
* You generally want to choose the most:
  * expensive or
  * common or
  * relevant operation
* You do *NOT* consider operations necessary for the control structure of the algorithm
* Examples:
  * Comparisons
  * Arithmetic operations: additions/substractions or mults/divs
  * Linked List operations: node traversals

### Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function* ($t$ is time)
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
* Domain is $\mathbb{N}$ (`\mathbb{N}`) captures the input size $\mathbb{N} = \{0, 1, 2, 3, 4, \ldots \}$
  * There are no negative input sizes
  * There are no fractional input sizes
* Codomain is positive reals
  * An algorithm costs negative time: impossible
  * It can cost fractional values: 3.5 seconds, 4.5GB
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* In general, you want to assume the *worst case* scenario: you want to provide an *upperbound* on the worst running time or other resource that the algorithm could cost you.

## Examples

* Selection Sort: 
```text








```
