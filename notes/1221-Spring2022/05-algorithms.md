
# Algorithms & Algorithm Analysis
## CSCE 156 - Computer Science II - Spring 2022

### Motivating Demonstration

* Consider the index-based retrieval operation of a list data structure: `get(i)`
  * Linked List
  * Array List
* Both from a theoretical and emperical perspective, the code seems to have a "quadratic" behavior with respect to the amount of time it takes to execute
* Perspective: how bad is our linked list code?
* Quadratic regression (Correlation Coefficient: 0.994):
    $$t(n) = 6.4101E{-4}n^2 -0.11168n + 9.7822$$
  * 10 million elements: 17.59 hours
  * 100 million: 74 days
  * 1 billion: 20 years
* Linear behavior:
    * 10 million elements: < 1second
    * 100 million: 10 seconds
    * 1 billion: 2 minutes

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Actual time, CPU time
  * Memory
  * Power consumption
  * Network bandwidth/throughput
  * Circuit: number of wires/gates
  * Idleness/utilization
* These are all important *engineering* concerns and considerations
* Algorithms are thousands of years old; computers are only decades old
* The behavior and efficiency of algorithms is *invariant* over time and the type of hardware you use
* From a theoretical perspective, we need an abstract notion of "resource" that is not tied to a particular machine
* We want to analyze algorithms from an *abstract* general perspective
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
  * Makes use of good mathematical notation
  * Doesn't use any language specific constructs
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

* Subtle but important and may be tricky
* Input: a collection; size: the number of elements in the collection
* If your input is a matrix, or an image you could consider *both* dimensions: $n \times m$; WLOG: only consider ONE, $n \geq m$, then consider $n$
* Tricky: what if the input is a single integer (or number); the input size is: the number of bits required to represent the number, $\log_2(x) = \log(x)$

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
  (input sizes to a resource measure such as time)
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* In general, you want to assume the *worst case* scenario: you want to provide an *upperbound* on the worst running time or other resource that the algorithm could cost you.

## Asymptotics

* Step 5: Provide an asymptotic characterization of the complexity function
* Motivation: want to characterize an algorithm's performance and efficiency with respect to its input size
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

* Big-O characterizes the relative growth rate of two functions as $n \rightarrow \infty$
* $f(n)$'s growth rate is *bounded above* by $g(n)$'s growth rate
* Ultimately: you can ignore lower order terms, and constants
* It allows you to focus on the highest order term in an equation
* Notation:
  * Usually we right: $f(n) = O(g(n))$
  * The definition is not "tight"; it only provides a relative order of growth: less than or equal to
  * Interpretation: $g(n)$ grows at least as much as $f(n)$
  * The growth rate of $f(n)$ is no bigger than the growth rate of $g(n)$
* Examples:
  * $3n + 3 = O(n)$
  * $3n + 3 = O(n^2)$
  * $3n + 3 = O(n^3)$
  * NOT true: $n^2 = O(n)$

### Proofs

* Given two functions: $f(n)$ and $g(n)$ how do you prove that $f(n) = O(g(n))$
* Two techniques:
  * Approach A: find a "cross over point": the largest value of $n$ for which the two functions intersect (have the same value)
  * Set the two functions equal to each other:
    $$n^2 - 175n - 50 = 0$$
  * Roots: 0, -.28, **175.28**
  * The next integer larger than 175.28 is... 176
  * For all values of $n$ larger than or equal to 176, $175n^2 + 50n \leq n^3$
  * For $n_0 = 176, c = 1$, the inequality holds
  * Therefore, $f(n) = O(g(n))$
* Technique B:
  * Simply setup an inequality and make your function bigger and bigger until it matches $g(n)$
  * Goal: want to establish that
    $$175n^2 + 50n \leq c \cdot n^3$$
  * Process:
    $$175n^2 + 50n \leq 175n^2 + 50n^2$$
    ***For all $n \geq 0$***
    $$175n^2 + 50n^2 = 225n^2$$
    $$225n^2 \leq 225n^3$$
  * For $c = 225$, $n_0 = 0$, the inequality holds and therefore
  * Therefore, $f(n) = O(g(n))$
* Technique C: you set up a limit

$$\lim_{n\rightarrow\infty} \frac{f(n)}{g(n)}$$  
  * If the limit converges to zero, then $f(n) = O(g(n))$
  * If the limit diverges to $\infty$ then $g(n) = O(f(n))$
  * If the limit converges to $c > 0$ then they have the same rate of growth, $f(n) = \Theta(g(n))$

* Example: what is the relation between $175n^2 + 50n$ and $n^3$
  * Intuition: $175n^2 + 50n = O(n^3)$

* Examples:
  * Selection Sort: $\frac{n(n-1)}{2}$; claim: $O(n^2)$
  * Formal Proof: want to show that
    $$\frac{n(n-1)}{2} \leq cn^2$$
    for some $c$ and for some $n_0$ for all $n \geq n_0$
  * Technique B:
  $$\frac{n(n-1)}{2} = \frac{1}{2}n^2 - \frac{1}{2}n \leq \frac{1}{2}n^2$$
  * For $c = \frac{1}{2}$ and $n_0 = 0$ the inequality holds and $\frac{n(n-1)}{2} = O(n^2)$

* Insertion Sort:
  * $\frac{n(n+1)}{2}$; claim: $O(n^2)$
  * Technique B:
$$\frac{n(n+1)}{2} = \frac{1}{2}n^2 + \frac{1}{2}n \leq \frac{1}{2}n^2 + \frac{1}{2}n^2 = n^2$$
  * For $c = 1$, $n_0 = 0$, the inequality holds

## Analyzing Recursive algorithms

* Example: Quick Sort
* First Step: establish a function that captures the number of comparsisons in QS on an array of size $n$
  $$C(n) = \textrm{number of comparisons by QS on an array of size n}$
* There are two phases: partition (non-recursive part), two recursive calls
* Partitioning $n-1$ elements around a pivot requires $n-1$ comparisons
* Recurse on the left partition (which ideally is of $n/2$ elements): $C(n/2)$
* Recurse on the right partition (which ideally is of $n/2$ elements): $C(n/2)$
* All three added together:
  $$C(n) = 2C(n/2) + n-1$$
* $f(n) = n - 1 = O(n)$
* $$a = 2, b = 2, d = 1$$
* Inequality: $2 = 2^1$
* By case 2 of the master theorem, $C(n) = O(n \log(n))$

```text









```
