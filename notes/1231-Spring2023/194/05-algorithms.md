
# Algorithms & Algorithm Analysis
## ECEN 194 - Computer Science II - Spring 2023

### Motivating Demonstration

* Consider the index-based retrieval operation of a list data structure: `get(i)`
  * Linked List
  * Array List
* Quadratic regression (Correlation Coefficient: 0.994):
    $$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * 10 million elements: 17.59 hours
  * 100 million: 74 days
  * 1 billion: 20 years
* Linear behavior:
    * 10 million elements: < 1second
    * 100 million: 10 seconds
    * 1 billion: 2 minutes

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Actual time/CPU time
  * Memory
  * Power consumption
  * Network: bandwidth/throughput
  * Circuits: wires/gates
  * Idleness/utilization
* These are all important *engineering* concerns and considerations
* Algorithms are abstract: they are thousands of years old
* We want to view our algorithms' efficiency in abstract general terms, not in terms of any specific hardware or software
* The behavior and efficiency of algorithms is *invariant* over time and the type of hardware you use
* From a theoretical perspective, we need an abstract notion of "resource" that is not tied to a particular machine
* What matters is the performance of the algorithm with respect to the *input size* not any particular machine, language, architecture, etc.
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
  * Makes use of good math notation
  * Doesn't use any language-specific constructs
  * It provides necessary details without too many unnecessary details

## Algorithm Analysis

1. Identify the input
2. Identify the input size, $n$
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size
5. Characterize the algorithm's behavior (growth rate) using Big-O analysis


### Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases, the choice may not be clear
* In general: keep it simple, focus on ONE input if there are multiple
* Focus on the most relevant input that may affect the runtime of your algorithm
  * Example: linear search the most appropriate input would be the collectin, not the key (because it can grow in size, but there is only ever one key)
  * Example: Euclid's GCD algorithm takes two integers: instead of considering both, only consider the largest of the two; WLOG: assume that $a \geq b$ (if not, then swap them!)

### Identify the Input Size

* Subtle but important and may be tricky!
* Input: a collection then the size or "cardinality"
* If the input is a matrix, $n \times m$, input size is $n\cdot m$?  WLOG: assume that $n > m$, input size is at most $n^2$ then substitute: $N = n^2$
* An image that is $n \times m$ pixels?
* What if your input is an integer $n$?; the input size is: the number of bits required to represent the number, $\log_2(x) = \log(x)$

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
* You come up with a *resource function*:
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* In general, you want to assume the *worst case* scenario: you want to provide an *upperbound* on the worst running time or other resource that the algorithm could cost you.

# Examples

* Linear Search
* Selection Sort

## Asymptotics

* Provide an asymptotic characterization of the complexity function $t(n)$
* Motivation: want to characterize an algorithm's performance and efficiency with respect to the input size $n$
* We're interested in the performance for larger and larger input sizes, as $n \rightarrow \infty$
* For small inputs, the different between two algorithms may not be that much
* We want a tool that will allow us to:
  * Focus on the growth rate (ignore lower order terms)
  * ignore constants
* The tool we use for this is "Big-O" analysis

### Definition

Definition: let $f(n)$ and $g(n)$ be two functions (for our purposes, they represent the complexity of two algorihtms, $A, B$).  We say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$





```text










```
