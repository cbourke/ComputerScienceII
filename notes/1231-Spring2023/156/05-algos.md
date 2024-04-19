
# Algorithms & Algorithm Analysis
## CSCE 156 - Computer Science II - Spring 2023

### Motivating Demonstration

* Performance of an index-based retrieval operation (`get(i)`) on a list data structure
  * Linked List
  * Array List
* Perspective: how bad is our linked list code?
* Quadratic regression (Correlation Coefficient: 0.994):
    $$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * $n = 10M$: 17.59 hours
  * $n = 100M$: 74 days
  * $n = 1B$: 20 years
* Array-based:
  * $n = 1M$: 0.009s
  * $n = 10M$: 0.030s
  * $n = 100M$: 0.300s
  * $n = 1B$: ~ 3s

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Actual time/CPU time
  * Memory
  * Power Consumption
  * Network bandwidth or throughput
  * Circuit: number of wires/gates
  * Idleness/utilization
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
* But in some cases it may not be clear
* Example: if there are multiple inputs: choose the one that is most relevant to the behavior of the algorithm
* Example: Euclid's GCD algorithm: given two integers, $a, b$ output their greatest-common-divisor: WLOG: assume that $a \geq b$ ie the input size of $a$ is at least as big as $b$, so only focus on $a$
* In general: only focus on ONE input
* Examples: a collection is usually better than a single value/item

### Identify the Input Size

* Subtle but important and may be tricky
* Input: a collection; size: the number of elements in the collection
* If your input is a matrix, or an image you could consider *both* dimensions: $n \times m$; WLOG: only consider ONE, $n \geq m$, then consider $n$ or $n^2$ or $N = n^2$
* Tricky: what if the input is a number (integer)?
* Tricky: what if the input is a single integer (or number); the input size is: the number of bits required to represent the number, $\log_2(x) = \log(x)$

### Elementary Operation

* Ultimately: we only want to focus on ONE operation in an algorithm
* You generally want to choose the most:
  * expensive or
  * the most common or
  * the most *relevant* operation
* You do *NOT* consider operations necessary for the control structure of the algorithm
* Examples:
  * Comparisons
  * Arithmetic operations: additions/subtractions or mults/divisions
  * Linked List operations: node traversals

### Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function*:
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
  (input sizes to a resource measure such as time)
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* In general, you want to assume the *worst case* scenario: you want to provide an *upperbound* on the worst running time or other resource that the algorithm could cost you.

## Examples

* Selection Sort
* Insertion Sort

## Asymptotics

* Step 5: Provide an asymptotic characterization of the complexity function
* Motivation: want to characterize an algorithm's performance/efficiency with respect to the input size $n$
* We're interested in how the algorithm performs as the input size $n$ grows larger: $n \rightarrow \infty$
* Everything performs well for "small" inputs
* We want a tool that will allow us to focus on:
  * The growth rate of the algorithm
  * Ignore constants
* Tool: Big-O analysis

### Definition

Definition: let $f(n)$ and $g(n)$ be two functions (for our purposes, they represent the complexity of two algorithms, $A, B$).  We say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$

* Big-O characterizes the relative growth rate of two functions as $n \rightarrow \infty$
* $f(n)$'s growth rate is *bounded above* by $g(n)$'s growth rate
* Ultimately: you can ignore lower order terms, and constants
* It allows you to focus on the highest order term in an equation
* Notation:
  * Usually write $f(n) = O(g(n))$
  * The definition is not "tight"; it only provides a relative order of growth: less than or equal to
  * Interpretation: $g(n)$ grows at least as much as $f(n)$
  * The growth rate of $f(n)$ is no bigger than the growth rate of $g(n)$
* Examples:
  * $3n+3 = O(n)$
  * $3n+3 = O(n^2)$
  * $3n + 3 = O(n^3)$
  * $3n + 3 = O(2^n)$
  * BUT: $n^2 = O(n)$ (NOT true)

### Proofs

* Given two functions: $f(n)$ and $g(n)$ how do you prove that $f(n) = O(g(n))$
* Two (algebraic) techniques:
  * Approach A: find a "cross over point": the largest value of $n$ for which the two functions intersect (have the same value)
* Technique B:
  * Simply setup an inequality and make your function bigger and bigger until it matches $g(n)$
* Technique C: you set up a limit

$$\lim_{n\rightarrow\infty} \frac{f(n)}{g(n)}$$  
  * If the limit converges to zero, then $f(n) = O(g(n))$
  * If the limit diverges to $\infty$ then $g(n) = O(f(n))$
  * If the limit converges to $c > 0$ then they have the same rate of growth, $f(n) = \Theta(g(n))$

* Example: what is the relation between $175n^2 + 50n + 1$ and $n^3$
  * Intuition: $175n^2 + 50n = O(n^3)$

* Examples:
  * Selection Sort: $\frac{n(n-1)}{2}$; claim: $O(n^2)$

* Categories of functions:
  * Constant: $O(1)$ (formula computation)
  * Logarithmic: $O(\log{(n)})$ (binary search)
  * Linear: $O(n)$ (linear search)
  * Quasilinear: $O(n\log{(n)})$ (efficient sorting algos)
  * Quadratic: $O(n^2)$ (selection sort, insertion sort)
  * Cubic: $O(n^3)$
  * Polynomial: $O(n^k)$
  * Exponential $O(2^n)$
  * Superexponential: $O(n!)$

### Analysis of Recursive Algorithms

* Quick Sort

1. You define a *recursive* function that captures the idea of "work" or the elementary operation
2. You setup a recursion: you capture the notion of "recursive" work: how many calls to the function do you make and how big is the input size for each call?
3. You "solve" the recursion

```text












```
