
# Algorithms & Algorithm Analysis
## CSCE 156 - Computer Science II - Spring 2026

### Motivating Demonstration

* Performance of an index-based retrieval operation (`get(i)`) on a list data structure
  * Linked List
  * Array List
* The basic operation: `get(i)` "cost" much more when using a linked list
  * Linked list: requires starting at the beginning and making `i` traversals, in total: $\approx n^2$ total traversal operations
  * Array list: you can use random access and getting the `i`th element is only a constant number of operations; in total there will be $n$ operations
* Linked list: quadratic regression (Correlation Coefficient: 0.994):
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
  * Processing/CPU/GPU time
  * Memory/RAM, secondary storage
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
  * Algorithms are thousands of years old (Euclid's GCD algorithm)
  * An algorithm does not suddenly become "efficient" just because we upgrade our hardware
  * Individual operations (add, multiply, compare) become faster on newer hardware, but the *number* of operations stays the same
  * The rate of growth is an *invariant* of the algorithm: it classifies its efficiency 3000 years ago as well as 3000 years into the future
  * Larger inputs lead to slower performance = more resources

## Pseudocode

* Pseudo = fake, code = code
* An algorithm is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, it is not a snippet of code
* A general outline that looks kinda-like code
* Good pseudocode:
  * Makes use of plain English
  * makes use of mathematical notation
  * Provides necessary details without unnecessary details (Goldilocks)
* There are no "rules" for pseudocode, just guidelines
* Example: linear search

## Algorithm Analysis

* How good or bad is a particular algorithm?


1. Identify the input
2. Identify the input size, $n$
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size
5. Provide a Big-O analysis

### Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases: it is less clear
* Ex: what if you have multiple inputs?
  * Keep things simple: focus only on ONE
  * You choose the most relevant or the input that has the biggest impact on the performance of the algorithm
  * Ex: linear search: choose the collection, because its size affects the performance more than the single key
  * Example: Euclid's GCD algorithm (GCD = Greatest Common Divisor): you are given two integers as inputs $a, b$
  * WLOG = Without Loss Of Generality, consider only the "larger" of the two inputs

### Identify the Input Size

* Subtle but important and may be tricky!
* Obvious examples: a collection's size is the number of elements in the collection
* Matrix: $n \times m$ matrix (less obvious)
  * WLOG: consider the larger value, assume $n \geq m$ and focus on $n$
  * WLOG: suppose that $N = n\cdot m$ and then use $N$ as your input
  * A single number (integer): $a$, what is the "size" of a single number?
  * A: the number of bits to represent the number $a$
  * $=\log_2{(a)} = \log{(a)}$

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
  * Linked list operations: node traversal (similar for graph algorithms)

### Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function* ($t$ is for time):
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
* $\mathbb{N}$ (`\mathbb{N}`): $0, 1, 2, 3, \ldots$
  * Represents $n$, the input size
  * We do not consider negative values
  * We do not consider fractional values
* $\mathbb{R}^+$ (`\mathbb{R}^+`), real numbers, but only the positives
  * We don't consider negative values in the codomain: an algorithm takes -10 seconds?
* All our functions will be monotone increasing (not $\sin{n}$)
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* There is no clear guideline: everything is going to be slightly different
* You should generally only consider the *worst case* scenario

#### Examples

* Linked List (bad usage of): created a table to quantify the number of operations, summed them up (Gauss's Formula)
* Linear search: did consider best/worst/average, looked only at the worst case
* Selection Sort: setup a couple of summations, solved them and used Gauss's Formula
* Insertion Sort:
  * Idea: for each element $a_i$, you insert it where it needs to be
  * Assume: elements $a_1, \ldots, a_{i-1}$ are sorted; then we insert $a_i$ by *shifting* elements up until $a_i$ is where it needs to be
* Binary Search: create a table, but we had to use math to reason about how many iterations (rows in the table) there were

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
* "After a certain point ($n_0$) the function $g(n)$ will always be larger than $f(n)$ within some constant
* $f(n)$ has a less than or equal to rate of growth as $g(n)$
* $g(n)$'s growth rate is *at least* as big as that of $f(n)$
* They could have the *same* rate of growth: it is only a relative measure
* Ex:
  * $3n+3= O(n)$ (tightest possible bound)
  * $3n+3 = O(n^2)$
  * $3n + 3 = O(n^3)$
  * $3n + 3 = O(2^n)$
  * NOT true: $3n+3 = O(\log{n})$
  * NOT true: $n^2 = O(n)$
* Big-O analysis provides an *upper bound* on the rate of growth of a function
* It is not necessarily a *tight bound* (it can be)

### Proofs

* Given two functions: $f(n)$ and $g(n)$ how do you prove that $f(n) = O(g(n))$
* Ex: $100n^2 + 50n$ vs $n^3$
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
  * Be careful: the inequalities *must* hold for all specific $n$ values
* Technique C: you set up a limit
  * Requires calculus
  * Even easier if you know how to solve limits

* Categories of functions:
  * Constants: $O(1)$  (formula calculations)
  * Logarithmic: $O(\log{(n)})$ (binary search)
  * Linear: $O(n)$ (linear search)
  * Quasilinear (almost linear): $O(n\log{(n)})$ (fast sorting algorithms)
  * Quadratic: $O(n^2)$ (naive linked list operations, selection sort, insertion sort)
  * Cubic: $O(n^3)$ (matrix operations)
  * Polynomial: $O(n^k)$ (for constant $k$)
  * Exponential $O(2^n)$ (not all exponents are equal), $2^n = O(3^n)$ but $3^n \neq O(2^n)$ (combinations)
  * Superexponential: $O(n!)$ (permutations)

## Analysis of Recursive Algorithms

### Analysis


1. You define a *recursive* function that captures the idea of "work" or the elementary operation
2. You setup a recursion: you capture the notion of "recursive" work: how many calls to the function do you make and how big is the input size for each call?
3. You "solve" the recursion

### Binary Search

* Binary Search (recursive version) makes 1 non-recursive comparison for each call
* Makes at most 1 recursive call on an input half the size
* Recurrence:
  $$C(n) = C\left(\frac{n}{2}\right) + 1$$
* Master Theorem: $a = 1, b = 2, d = 0$, so by case 2, $C(n) = O(\log{(n)})$

### Merge Sort

* Basic Idea:
  * Split the input array into two equal parts first
  * continue until you have a subcollection of size $\leq 1$
  * As you return from the recursion, you merge the two sorted arrays together
* Using the MT, we know that Merge Sort has a guaranteed complexity of $O(n\log{n})$

### Quick Sort

* Basic Idea:
  * You choose a pivot element, $p$
  * Partition all the other elements *around* $p$: those smaller to left, those larger go right
  * Place $p$ in the middle
  * Recurse on the left and the right until... you have an array of size $\leq 1$
* In the best case, QS is $O(n\log{n})$
* However, in the worst case, it is $O(n^2)$ (uneven split/partition)

### Review of Sorting Algorithms

* Selection Sort: $O(n^2)$, terrible time
* Insertion Sort: $O(n^2)$, but in practice was kinda good (better than selection sort)
* Quick Sort: worst case $O(n^2)$ (but that's extremely unlikely), in average/best: $O(n\log{n})$
* Merge Sort: guaranteed running time of $O(n\log{n})$ (it "requires" additional space/memory)
* C:
  * `qsort` (*orignally* was a quick sort implement): however, it likely "simulated" recursion
* Python: `sort()`, `sorted()` (implemented using Tim Sort by Tim Peters)
  * Was then adopted by Java and other languages
* Hybrid Sorting:
  * On "small" inputs, a slow algorithm may be quicker
  * On large inputs, we use the faster algorithm
  * We switch to the slower once the recursion has cut it in size to the "small" input


```text










```
