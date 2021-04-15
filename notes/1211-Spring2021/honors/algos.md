
# Algorithms & Algorithm Analysis
## CSCE 156H - Computer Science II - Spring 2021

### Motivating Demonstration 

* Consider the inefficient `indexOf` method from our linked list...
* The "elementary operation" was a linked node traversal
* Naive solution:
  * Naive painter: you restart at the beginning of the list every time
  * Leads to a quadratic performance:
  $$t(n) = an^2 + bn + c$$
  * A better solution: take your paint can with you. leading to a *linear* algorithm:
  $$t(n) = an + b$$
* How bad is the quadratic?
  * An increase of 10 fold in the input size leads to a 100x slower algorithm
  * linear version: only 10x slower
* Perspective: 
  * Quadratic regression (Correlation Coefficient: 0.994):
  $$t(n) = 6.4101E{-4}n^2 -0.11168n + 9.7822$$
  * Quadratic Predictions: how long will it take to search a linked list naively
    * 10 million elements: 17.49 hours
    * 100 million elements: 74 days
    * 1 billion: 20+ years
  * Linear predictions:
    * 10 million elements: 1 second
    * 100 million: 10 seconds
    * 1 billion 2 minutes

## Introduction

* It should be clear: computational processes require some *expense* or *resource* 
* Examples: 
  * Time = CPU Cycles
  * Memory
  * Power consumption
  * Network bandwidth/throughput
  * Number of wires/gates in a circuit
  * Idleness/Utilization
* The above are all extremely important *engineering* concerns
* From a theoretical point of view, they should not matter
* Historical: Euclid's GCD algorithm: 2300 years ago
* An algorithm's complexity is *invariant* and *independent* of any hardware you may run it on
* We need a way to capture our intuitive notion that algorithms "cost" something and that with larger and larger input sizes they cost even more

## Pseudocode

* An *algorithm* is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a Java program, it is not a method or function, it is not a snippet of code
* Good pseudocode
  * makes good use of English
  * makes good use of mathematical notation
  * doesn't use language-specific constructs or syntax
  * It gives *essential* details while omitting unnecessary details
  * Example: 

## Algorithm Analysis 

  1. Identify the input
  2. Identify the input size, n
  3. Identify the *elementary operation* 
  4. Determine how many times the elementary operation is executed with respect to the input size, n
  5. Characterize the algorithm's behavior (growth rate) using Big-O Analysis

### Identify the Input

* Generally given as part of the pseudocode
* In some cases, the choice may not be clear
* Examples:
  * Linear search takes a collection $A$ and a key $k$
  * Euclid's GCD algorithm takes two integers, $a, b$: simplify!  Make a reasonable assumption: WLOG: assume $a \geq b$, then we'll consider $a$ as the input

### Identify the input size

* Subtle but important
* Example:
  * A collection: the number of elements in the collection 
  * Image that is $n \times m$ pixels?
  * Simplify: assume that $n > m$, then $n * m \leq n^2$
  * Further: just consider the larger dimension, $n$
  * Number: input is a single integer, $x$?
    * Naive answer: '1'
    * Correct answer: the size of a number is the number of bits required to represent it, $n = \log_2(x)$
    * In CS, all of our bases are base-2, so, $\log(n)$

### Elementary Operation

* Ultimately: you want to characterize the algorithm's complexity; the amount of "work" it does
* We need ONE operation to represent this work
* In general, we want the most:
  * common or
  * most expensive
  * most relevant operation
* Any operation necessary for the control structures of the algorithm are generally not relevant
* examples:
  * Comparisons
  * Arithmetic operations: additions/subtractions, multiplications/divisons
  * node or vertex traversal in a list or graph

### Analysis

* You analyze the algorithm to determine how many times the elementary operation is executed with respect to the input size, $n$
* That is, we come up with a *resource function*:
  $$f:\mathbb{N} \rightarrow \mathbb{R}^+$$
(input sizes to a resource measure)
* May involve coming up with a summation, solving it, etc.
* In general, you want to focus on the worst case scenario to provide an absolute *upperbound* on the resource(s) required to run an algorithm

## Asymptotics

* Motivation: want to characterize an algorithm's performance and efficiency with respect to its input size
* We're interested in its performance as $n$ *grows*
* For larger and larger inputs, how does the growth rate of the function that characterizes and algorithm's efficiency: what kind of "order" can we give it
* We want to:
  * ignore constants
  * ignore lower order terms
* the tool we use is Big-O analysis

### Definition

Definition: let $f(n)$, $g(n)$ be functions, we say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that 
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$  
* Big-O characterizes a function's order of growth as $n \rightarrow \infty$
* It says that $g(n)$ has a growth rate at least as big as $f(n)$
* The growth rate of $f(n)$ is bounded by the growth rate of $g(n)$
* Asymptoticaloy, $g(n)$ is at least as big as $f(n)$
* It allows you to ignore lower order terms
* It allows you to ignore constants 
* It allows you to focus on the most significant term in a function
* Misc:
  * We usually write $f(n) = O(g(n))$
  * The "real" way to write this is $f(n) \in O(g(n))$
  * The definition is not "tight": that it establishes a *relative* ordering of function rates of growth
  * $3n + 3 = O(n)$
  * It is also true that $3n+3 = O(n^2)$
  * It is also true that $3n+3 = O(2^n)$

### Proofs

* Given two functions, $f(n), g(n)$ how do you prove that $f(n) = O(g(n))$
* Two techniques:
  * Approach A: find the "cross over" point where one function becomes larger than the other for all values greater than the point: fix $c = 1$ and find $n_0$
  * Approach B: Fix $n_0 = 0$ and find a $c$ such that the inequality is satisfied

## Revisit

* Binary Search
* Quick Sort
* Merge Sort

### Analysis of Recursive Algorithm


```text






```
