
# Algorithms & Algorithm Analysis
## ECEN 156 - Computer Science II - Spring 2025

### Motivating Demonstration

* Array-based: linear behavior
  * $n = 1M$: 0.009s
  * $n = 10M$: 0.030s
  * $n = 100M$: 0.300s
  * $n = 1B$: ~ 3s
  * $t(n) = an + b$
* Linked List:
  * each `get(i)` operation "cost" $i$ traversal operations
  * Overall it was about $\frac{n(n+1)}{2} \approx n^2$
  * Quadratic regression (Correlation Coefficient: 0.994):
$$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * $n = 10M$: 17.59 Days
  * $n = 100M$: 74 DAYs
  * $n = 1B$: 20 years

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Processing time/CPU time/wall time
  * Memory
  * Power
  * Network bandwidth or throughput
  * Circuit: number of wires/gates
  * Idleness/utilization: Virtualization (SaaS, IaaS)
* These are important *engineering* concerns and considerations
* Algorithms are thousands of years old; computers are only decades old
* The behavior and efficiency of algorithms is *invariant* over time and the type of hardware you use
* From a theoretical perspective we need an *abstract* notion of "resource" that is not tied to a particular computing model
* We want to analyze algorithms from an *abstract* general perspective
* What matters is the performance of a particular algorithm with respect to the *input size* and how the performance behaves as you give it larger and larger inputs $n \rightarrow \infty$
* Algorithms are *indepedent* of the hardware they are run on
  * Algorithms predate modern computers by thousands of years!
  * An algorithm does not suddenly become efficient just because we upgraded to a Nvidia 5090 (GPU)
  * Individual operations become faster on faster machines, but the *number* of operations stays the same
  * The rate of growth is invariant regardless of the hardware
  * Larger inputs lead to slower performance = more resources

## Pseudocode

* Pseudo = false/fake, code = code
* An algorithm is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, it is not a snippet of code
* A general outline that looks kinda-like code
* There are no "rules" for pseudocode
* Good pseudocode:
  * Makes use of plain English
  * It makes use of mathematical notation
  * Doesn't use any language-specific constructs
  * You should be able to *translate* the pseudocode into any programming language that you want
  * It provides necessary details without too many unnecessary details

## Algorithm Analysis

1. Identify the input
2. Identify the input size, $n$
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size
5. Provide a Big-O analysis

### 1. Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases: the choice is not very clear
  * What if we have *two* or more inputs?
  * Keep things simple: choose ONE to focus on
  * Generally choose the most important or most relevant to the algorithm
  * You don't choose one that makes the analysis trivial
* Example: linear search has both a collection and a key, choose the collection: its size will have the greatest impact on the performance of the algorithm
* Example: Euclid's GCD algorithm (GCD = Greatest Common Divisor): you are given two integers as inputs $a, b$
  * WLOG = Without Loss Of Generality, choose the "larger" of the two variables, suppose $a \geq b$

### 2. Identify the Input Size

* Subtle but important and may be tricky!
* Obvious: a collection (set, list, array, etc.): the size of the collection (number of elements in it, cardinality)
  * Matrix: $n \times m$, WLOG: assume that $n > m$ and so $m \cdot n \leq n^2$: ie we've eliminated a variable
  * You can then change your variable: input size is $N$ where $N = n^2$
  * A single number (integer): $a$; ex: determine if $a$ is prime or not
  * Tricky: a single number, $n$; number of bits it takes to represent $n$: $\log_2(n) = \log(n)$

### 3. Elementary Operation

* Ultimately: we only want to focus on ONE operation in an algorithm
* Want to have a *simple* analysis
* You generally choose the operation that is:
  * most common
  * most expensive
  * most relevant
* YOu do NOT consider operations necessary for the control structure of the algorithm
* Examples:
  * Comparisons (searching/sorting)
  * Arithmetic operations: additions/subtractions, multiplications/divisons
  * Linked list operations: node traversal or node "processing"

### 4.Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function* ($t$ is for time):
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
  * $\mathbb{N}$ (`\mathbb{N}`) is our domain because we do not have "negatively" sized nor "fractional" input sizes
  * $\mathbb{R}^+$ (`\mathbb{R}^+`) is our codomain because we do not want to consider "negative" resources
  * We really only consider monotone increasing functions
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* There is no clear rule: everything is going to be slightly different
* You should generally focus on the *worst case* scenario

### Examples

* Start-over traversal of a linked list
* Linear Search
* Binary Search (iterative version)
* Selection Sort
* Insertion Sort

## 5. Asymptotics

* Step 5: Provide an asymptotic characterization of the complexity function
* Motivation: want to characterize an algorithm's performance/efficiency with respect to the input size $n$
* We're interested in how the algorithm performs as the input size $n$ grows larger: $n \rightarrow \infty$
* Everything performs well for "small" inputs
* We want a tool that will allow us to focus on:
  * The growth rate of the algorithm (of a function characterizing an algorithm)
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
  * $3n + 5 = O(n)$ (tightest possible bound)
  * $3n + 5 = O(n^2)$ (looser bounds)
  * $3n + 5 = O(n^3)$
  * $3n + 5 = O(2^n)$
  * NOT true: $3n+5 = O(\log{n})$
  * NOT true: $n^2 = O(n)$
* Big-O analysis provides an *upper bound* on the rate of growth of a function
* It is not necessarily a *tight bound* (it can be)

### Proofs

* Given two functions: $f(n)$ and $g(n)$ how do you prove that $f(n) = O(g(n))$
* First step: write out your intuition
* Two techniques:
  * Approach A: find a "cross over point": the largest value of $n$ for which the two functions intersect (have the same value)
  * You are fixing $c = 1$ (or something) and then finding the $n_0$: the largest/biggest point where the two functions intersect (find roots)
* Technique B:
  * Simply setup an inequality and make your function bigger and bigger until it matches $g(n)$
  * You are fixing an $n_0$ (and possibly changing it) and then finding a $c$
* Technique C: you set up a limit
  * Requires some calculus
  * Some limits are obvious: if numerator/denominator are growing but the other is not, then it is obvious
  * You may require some other techniques: l'Hopital's Rule, etc.

### Categories of functions

* Constant: $O(1)$ (formula computation)
* Logarithmic: $O(\log{(n)})$ (binary search)
* Linear: $O(n)$ (linear search)
* Quasilinear: $O(n\log{(n)})$ (merge sort, quick sort)
* Quadratic: $O(n^2)$ (insertion sort, selection sort, naive linked list iteration)
* Cubic: $O(n^3)$ (matrix operations: multiplication, graph algorithms)
* Polynomial: $O(n^k)$
* Exponential $O(2^n)$
  * Not all exponentials are create equal: $2^n = O(3^n)$, but $3^n \neq O(2^n)$
* Superexponential: $O(n!)$

## Analysis of Recursive Algorithms

1. You define a *recursive* function that captures the idea of "work" or the elementary operation
2. You setup a recursion: you capture the notion of "recursive" work: how many calls to the function do you make and how big is the input size for each call?
3. You "solve" the recursion

### Merge Sort

* Basic Idea:
  * Ensure a good/equal split in the collection by breaking it into 2 parts FIRST
  * Until you have a sub collection/array of size $\leq 1$
  * As you return from the recursion, you merge the two sorted arrays together

### Quick Sort

* Basic Idea:
  * Given an array of $n$ elements, you choose a *pivot* element
  * We then *partition* the other elements *around* the pivot
  * Things less than the pivot will be placed in the left half
  * Things greater than the pivot will be placed in the right half
  * Recurse on each partition, left/right

### Summary

* Selection sort: terrible, $O(n^2)$
* Insertion Sort: $O(n^2)$, but *can* be better than selection sort in the best case/average case
* Merge Sort: guaranteed to be $O(n\log{n})$
* Quick sort: likely to be $O(n\log{n})$ but can be (in the worst case) $O(n^2)$
* Tim Sort: also $O(n\log{n})$ (adaptive), in practice it is very fast (Java, Python use this)
* Older Java used a *hybrid* algorithm:
  * For small inputs it uses a "slow" sorting algorithm because the recursion would be more expensive than the savings
  * For larger inputs it uses recursion (mergesort)
* Heap Sort: really smart data structure (heap): $O(n\log{n})$

```text














```
