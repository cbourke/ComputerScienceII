
# Algorithms & Algorithm Analysis
## CSCE 156 - Computer Science II
### Spring 2024

### Demonstration

* Performance of an index-based retrieval operation (`get(i)`) on a list data structure
  * Linked List
  * Array List
* Perspective: how bad was the linked-list version?
* Quadratic regression (Correlation Coefficient: 0.994):
$$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * $n = 10M$: 17.59 HOURS
  * $n = 100M$: 74 DAYs
  * $n = 1B$: 20 years
* Array-based: linear behavior
  * $n = 1M$: 0.009s
  * $n = 10M$: 0.030s
  * $n = 100M$: 0.300s
  * $n = 1B$: ~ 3s

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Processing time/CPU time
  * Memory
  * Power
  * Network bandwidth or throughput
  * Circuit: number of wires/gates
  * Idleness/utilization: Virtualization
* These are important *engineering* concerns and considerations
* Algorithms are thousands of years old; computers are only decades old
* The behavior and efficiency of algorithms is *invariant* over time and the type of hardware you use
* From a theoretical perspective we need an *abstract* notion of "resource" that is not tied to a particular computing model
* We want to analyze algorithms from an *abstract* general perspective
* What matters is the performance of a particular algorithm with respect to the *input size* and how the performance behaves as you give it larger and larger inputs $n \rightarrow \infty$
* Algorithms are *indepedent* of the hardware they are run on
  * Algorithms predate modern computers by thousands of years!
  * An algorithm does not suddenly become efficient just because we upgraded to a 4090
  * Individual operations become faster on faster machines, but the *number* of operations stays the same
  * The rate of growth is an *invariant* of the algorithm: it classifies its efficiency 3000 years ago as well as 3000 years into the future
  * Larger inputs lead to slower performance = more resources

## Pseudocode

* Pseudo = false/fake, code = code
* An algorithm is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, it is not a snippet of code
* A general outline that looks kinda-like code
* Good pseudocode:
  * Makes use of plain English
  * Makes use of good mathematical notation
  * Doesn't use any language-specific constructs
  * It provides necessary details without too many unnecessary details

## Algorithm Analysis

1. Identify the input
2. Identify the input size, $n$
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size
5. Provide a Big-O analysis

### Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases: the choice is not very clear
* What if you have "two" or more inputs
* Keep things simple: choose ONE input to focus
  * The most important, the most relevant, the most ...
* Example: linear search has both a collection and a key, choose the collection: its size will have the greatest impact on the performance of the algorithm
* Example: Euclid's GCD algorithm (GCD = Greatest Common Divisor): you are given two integers as inputs $a, b$
  * WLOG = Without Loss of Generality, choose the "larger" of teh two variables, assume that $a \geq b$ and choose $a$

### Identify the Input Size

* Subtle but important and may be tricky!
* Obvious: a collection (set, list, array, etc.): the size of the collection (number of elements in it, cardinality)
  * Matrix: $n \times m$, WLOG: assume that $n > m$ and so $n\cdot m \leq n^2$, so you can choose $n^2$ or you can choose $n$ or you can say $N = n^2$ and use $N$
  * A single number (integer): $a$
  * Tricky: a single number, $n$; number of bits it takes to represent $n$: $\log_2(n) = \log(n)$

### Elementary Operation

* Ultimately: we only want to focus on ONE operation in an algorithm
* Want to have a *simple* analysis
* You generally choose the operation that is:
  * most common
  * most expensive
  * most relevant
* YOu do NOT consider operations necessary for the control structure of the algorithm
* Examples:
  * Comparisons
  * Arithmetic operations: additions/subtractions, multiplications/divisons
  * LInked list operations: node traversal

### Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function* ($t$ is for time):
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
* $\mathbb{N}$ (`\mathbb{N}`) is our domain because we do not have "negatively" sized nor "fractional" input sizes
* $\mathbb{R}^+$ (`\mathbb{R}^+`) is our codomain because we do not want to consider "negative" resources
* We really only consider monotone increasing functions
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* There is no clear guideline: everything is going to be slightly different
* You should generally focus on the *worst case* scenario


### Examples

* Selection Sort
* Insertion Sort

## Asymptotics

* Step 5: Provide an asymptotic characterization of the complexity function
* Motivation: want to characterize an algorithm's performance/efficiency with respect to the input size $n$
* We're interested in how the algorithm performs as the input size $n$ grows larger: $n \rightarrow \infty$
* Everything performs well for "small" inputs
* We want a tool that will allow us to focus on:
  * The growth rate of the algorithm
  * Ignore constants and lower order terms
* Tool: Big-O analysis

### Definition

Definition: let $f(n)$ and $g(n)$ be two functions (for our purposes, they represent the complexity of two algorithms, $A, B$).  We say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$

* We write: $f(n) = O(g(n))$
* Big-O characterizes the *relative rate of growth* of two functions as $n \rightarrow \infty$
* $f(n)$ has a less than or equal to rate of growth as $g(n)$
* $g(n)$'s growth rate is *at least* as big as that of $f(n)$
* They could have the *same* rate of growth: it is only a relative measure
* Examples:
  * $3n + 3 = O(n)$ (tightest possible bound)
  * $3n + 3 = O(n^2)$ (looser bounds)
  * $3n + 3 = O(n^3)$
  * $3n + 3 = O(2^n)$
  * NOT true: $3n+3 = O(\log{n})$
  * NOT true: $n^2 = O(n)$
* Big-O analysis provides an *upper bound* on the rate of growth of a function
* It is not necessarily a *tight bound* (it can be)

### Proofs

* Given two functions: $f(n)$ and $g(n)$ how do you prove that $f(n) = O(g(n))$
* First step: write out your intuition
* Two techniques:
  * Approach A: find a "cross over point": the largest value of $n$ for which the two functions intersect (have the same value)
  * You are fixing $c = 1$ (or something) and then finding the $n_0$: the largest/biggest point where the two functions intersect (find roots)
  * Easy only if finding the roots is easy
* Technique B:
  * Simply setup an inequality and make your function bigger and bigger until it matches $g(n)$
  * You are fixing an $n_0$ (and possibly changing it) and then finding a $c$
  * You set up an inequality and simply make it bigger and bigger until it matches $cg(n)$
  * This is the *much* easier technique: all you have to do is make it bigger
* Technique C: you set up a limit
  * Requires some calculus
  * Some limits are obvious: if numerator/denominator are growing but the other is not, then it is obvious
  * You may require some other techniques: l'Hopital's Rule, etc.

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

## Analysis of Recursive Algorithms

### Quick Sort

* Idea: you choose a pivot element and *partition* around that element
* All elements less than $p$ are to the left, all elements greater are to the right
* Recurse until you have an array of size $\leq 1$

### Analysis

1. You define a *recursive* function that captures the idea of "work" or the elementary operation
2. You setup a recursion: you capture the notion of "recursive" work: how many calls to the function do you make and how big is the input size for each call?
3. You "solve" the recursion
   * OR: you can use the master theorem if the recurrence is of the proper form

### Merge Sort

* Basic Idea:
  * Ensure a good/equal split in the collection by breaking it into 2 parts FIRST
  * Until you have a sub collection/array of size $\leq 1$
  * As you return from the recursion, you merge the two sorted arrays together
* Using the MT, we know that Merge Sort has a guaranteed complexity of $O(n\log{n})$
* For 20 years Java used a *hybrid* mergesort
  * They used mergesort until it got to small $n= 7$, then they switched over to insertion sort
  * Now Tim Sort is used

### Binary Search

```text













```
