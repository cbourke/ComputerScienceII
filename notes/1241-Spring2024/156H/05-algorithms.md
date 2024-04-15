
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

## Asymptotics

* Step 5: Provide an asymptotic characterization of the complexity function
* Motivation: want to characterize an algorithm's performance/efficiency with respect to the input size $n$
* Want to ignore lower order terms and any constants
* Want to focus on the order of growth/rate of growth
* We're interested in how the algorithm performs as the input size $n$ grows larger: $n \rightarrow \infty$
* Everything performs well for "small" inputs
* Tool: Big-O Analysis

### Definition

Definition: let $f(n)$ and $g(n)$ be two functions (for our purposes, they represent the complexity of two algorithms, $A, B$).  We say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$

* Big-O characterizes the relative growth rate of two functions as $n \rightarrow \infty$
* We often write $f(n) = O(g(n))$
* $f(n)$'s growth rate is *bounded above* by $g(n)$'s growth rate
* $g(n)$'s rate of growth is *at least as big* as $f(n)$
* This is not necessarily a *tight* characterization
* Example:
  * $3n+3 = O(n)$ (tight)
  * More true: $3n+3 = \Theta(n)$ (TIGHT characterization)
  * $3n+3 = O(n^2)$ (true, but not tight)
  * $3n + 3 = O(n^3)$
  * $3n + 3 = O(2^n)$
  * BUT: $n^2 = O(n)$ (NOT true)
  * It is true that $n^2 = \Omega(n)$ (lower bound)

### Proofs

* Given two functions: $f(n), g(n)$ how do you prove that $f(n) = O(g(n))$
  * First step: express your intuition
* Two (algebraic) techniques:
  * Approach A: find the "cross over point": the largest value of $n$ for which the two functions intersect (they have the same value)
  * A works really well for simple polynomials, not so much for higher order polynomials or logarithms, or exponentials, etc.
  * Approach B: fix $n_0 = 0$ (or something small) and find a constant $c$ such that the inequality holds
  * Easiest technique: setup an inequality and make the right hand side bigger and bigger until it equals $g(n)$
  * Technique C: setup a limit of the two functions
$$\lim_{n\rightarrow\infty} \frac{f(n)}{g(n)}$$  
  * If the limit converges to zero, then $f(n) = O(g(n))$
  * If the limit diverges to $\infty$ then $g(n) = O(f(n))$
  * If the limit converges to $c > 0$ then they have the same rate of growth, $f(n) = \Theta(g(n))$ (BOTH $f(n) = O(g(n))$ AND $g(n) = O(f(n))$

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


```text












```
