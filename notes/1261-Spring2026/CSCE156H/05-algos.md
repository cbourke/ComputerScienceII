
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

#### Examples

* Linked List demo: developed a table and created a summation and solved for it (Gauss's): $\frac{n(n-1)}{2}$
* Linear Search: look at best/worst/average case, worst case: $n$ comparisons: $n$ comparisons
* Selection Sort: setup a summation and solved it: $\frac{n(n-1)}{2}$
* Binary Search (iterative version): we formulated a table and solved for how many rows/iterations the algorithm would execute (in the worst case): $\log{(n)}$
* Insertion Sort: we had to reason about the best case and the worst case because the inner while loop did not have a direct summation
  * Best case: $(n-1)$
  * Worst case: each iteration of the while loop required $i-1$ comparisons, summed up, $\frac{n(n-1)}{2}$

# Asymptotics

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

* We write: $f(n) = O(g(n))$ (Its better to write $f(n) \in O(g(n))$)
* Big-O characterizes the *relative rate of growth* of two functions as $n \rightarrow \infty$
* $f(n)$ has a less than or equal to rate of growth as $g(n)$
* $g(n)$'s growth rate is *at least* as big as that of $f(n)$
* They could have the *same* rate of growth: it is only a relative measure (We use $\Theta$)
* Examples:
  * $3n + 3 = O(n)$ (tightest possible bound)
  * $3n + 3 = O(n^2)$ (also true! but not as "tight" of a bound!)
  * $3n + 3 = O(n^3)$
  * $3n + 3 = O(2^n)$
  * $3n+3 \neq O(\log{n})$
  * $n^2 \neq O(n)$
* Big-O analysis provides an *upper bound* on the rate of growth of a function
* It is not necessarily a *tight bound* (it can be)

## Proofs

* Given two functions: $f(n)$ and $g(n)$ how do you prove that $f(n) = O(g(n))$
* Write out the functions: posit a guess
* Ex: $100n^2 + 50n$ vs $n^3$
  * Guess: $100n^2 + 50n = O(n^3)$
* Two techniques:
  * Approach A: find a "cross over point": the largest value of $n$ for which the two functions intersect (have the same value)
  * After that point, the second function will always be "bigger" than the first.  Ie you are finding $n_0$ for the definition
  * You are fixing $c = 1$ (or something) and then finding the $n_0$: the largest/biggest point where the two functions intersect (find roots)
* Technique B:
  * Simply setup an inequality and make your function bigger and bigger until it matches $g(n)$
  * You are fixing an $n_0$ (and possibly changing it) and then finding a $c$
  * You set up an inequality and simply make it bigger and bigger until it matches $cg(n)$
  * This is the *much* easier technique: all you have to do is make it bigger
  * Be careful: the inequalities *must* hold for all specific $n$ values
* Technique C: you set up a limit
  * Requires some calculus
  * Setup a limit!

* Categories of functions:
  * Constants: $O(1)$  (formula calculations)
  * Logarithmic: $O(\log{n})$ (binary search)
  * Linear: $O(n)$ (linear search)
  * Quasilinear:
  * Quadratic: $O(n^2)$ (selection sort, insertion sort, bad linked list iteration)
  * Cubic: $O(n^3)$ (matrix operations, matrix multipliation)
  * Polynomial: $O(n^k)$ (for constant $k$)
  * Exponential $O(2^n)$ (not all exponentials are created equally, $2^n = O(3^n)$ but not the other way!)
  * Superexponentials: $O(n!)$

## Analysis of Recursive Algorithms

### Analysis

1. You define a *recursive* function that captures the idea of "work" or the elementary operation
2. You setup a recursion: you capture the notion of "recursive" work: how many calls to the function do you make and how big is the input size for each call?
3. You "solve" the recursion

### Merge Sort

* Basic Idea:
  * Split the array into two roughly equal parts
  * ... until you have an array of size $\leq 1$
  * As you return from the recursion, you Merge the two sorted arrays together
* Using the MT, we know that Merge Sort has a guaranteed complexity of $O(n\log{n})$

### Quick Sort

* Basic Idea:
  * Choose a pivot element (say the first element)
  * Partition around that pivot element, items less than $p$ go to the left, items greater than or equal to $p$ go right
  * Recusively sort both left and right partitions
  * Until the partition is sized 1 or empty
* In the best/average case, it is  $O(n\log{n})$
* In the worst case (not likely) it is $O(n^2)$

### Review of Sorting Algorithms

* Selection Sort: $O(n^2)$, terrible time
* Insertion Sort: $O(n^2)$, but in practice was kinda good (better than selection sort) on *small* arrays
* Quick Sort: worst case $O(n^2)$ (but that's extremely unlikely), in average/best: $O(n\log{n})$
* Merge Sort: guaranteed running time of $O(n\log{n})$ (it "requires" additional space/memory)
* Tim Sort (from python, Tim Peters)
* Hybrid Sorting:
  * On "small" inputs a "slow" algorithm may be ironically faster
  * If the input is small: use insertion sort
  * If the input is large: use merge sort
  


```text












```
