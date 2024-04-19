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

### Examples

* Selection Sort
* Insertion Sort

## Asymptotics

* Step 5: Provide an asymptotic characterization of the complexity function
* Motivation: want to characterize an algorithms' performance/efficiency with respect to the input size $n$
* We want to:
  * ignore lower order terms
  * ignore constants
* We're interested in how the algorithm performs as the input size $n$ grows larger: $n \rightarrow \infty$
* We don't care about "small" inputs
* Tool: Big-O analysis

### Definition

Definition: let $f(n)$ and $g(n)$ be two functions (for our purposes, they represent the complexity of two algorithms, $A, B$).  We say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$

* Big-O characterizes the relative growth rate of two functions as $n \rightarrow \infty$
* We write that $f(n) = O(g(n))$ "f of n is Big-O of g of n" (Big-O: omicron)
* $f(n)$'s growth rate is *bounded above* by $g(n)$'s growth rate
* $g(n)$ is an UPPER BOUND on $f(n)$
* Ultimately: this allows us to ignore lower order terms and constants!  
  * We do not generally write $f(n) = O(3n^2 + n + 2)$
  * Instead we would write $f(n) = O(n^2)$
* This is not a "tight" characterization
  * $3n+3 = O(n)$ (tight)
  * $3n+3 = O(n^2)$ (loose)
  * $3n + 3 = O(n^3)$
  * $3n + 3 = O(2^n)$
  * NOT TRUE: $n^2 = O(n)$

### Proofs

* Given two functions: $f(n)$ and $g(n)$ how do you prove that $f(n) = O(g(n))$
* First step: declare your intuition
* Two (algebraic) techniques:
  * Approach A: you find the "last" "crossover point": you find the largest value such that the two functions are equal
  * Approach A is essentially fixing $c = 1$ and finding $n_0$ and demonstrating that hte inequality holds
  * Approach B: Fixing $n_0 = 0$ (or something small) and finding a $c$ that works (demonstrates the inequality)
  * Setup the inequality and make $f(n)$ bigger and bigger until it looks like $g(n)$
* Calc technique:
$$\lim_{n\rightarrow\infty} \frac{f(n)}{g(n)}$$  
  * If the limit converges to zero, then $f(n) = O(g(n))$
  * If the limit diverges to $\infty$ then $g(n) = O(f(n))$
  * If the limit converges to $c > 0$ then they have the same rate of growth, $f(n) = \Theta(g(n))$: it means that both $f(n) = O(g(n))$ *and* $g(n) = O(f(n))$

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

1. You define a *recursive* function that captures the idea of "work" or the elementary operation
2. You setup a recursion: you capture the notion of "recursive" work: how many calls to the function do you make and how big is the input size for each call?
3. You "solve" the recursion: Use the Master Theorem to provide an asymptotic analysis (if applicable)

#### Quick Sort

* Basic Idea: you choose a pivot element $p$, and partition around it
* Everything in the left partition is less than $p$, everything in the right is greater than $p$
* Recursively quick sort the left and the right (2 recursive calls), until your sub array/collection is one or fewer elements
* Assuming an even split: you make 2 recursive calls on inputs half as large
  $$C(n) = 2C(n/2) + (n -1)$$
* We then used the Master Theorem to determine that QS (best/average case) is
  $$O(n\log{(n)})$$
* However, in the worst case, the split may be uneven: $C(0)$, $C(n-1)$:
  $$C(n) = C(0) + C(n-1) + (n-1)$$
* Wost case ends up being
  $$O(n^2)$$

#### Merge Sort

* Guarantees the best case running time: it splits the input in half *first* guaranteeing an even split
* Splits the input up into 2 equal halves until you get down to 1 or 0 elements
* By definition these will be sorted sub arrays/collections
* On the way back up from the recursion, you *merge* two sorted arrays together

#### Misc

* For 20 years the default sorting algorithm in Java: hybrid merge sort
  * It uses merge sort to do the recursion BUT it doens't use $n = 1$ as a base case
  * It instead uses a threshold: $n=7$; at which point it no longer recurses
  * It then uses insertion sort (the best "in practice" slow algorithm) to sort the 7 elements
* More recent versions use a variation of Tim Sort: $O(n\log{n})$ but even better in practice than other "fast" sorting algorithms

### Binary Search

* If an array is sorted, you can use binary search to do FAST searching of elements
* Idea: compare the key to the middle element
  * If equal: stop, found the element
  * If $k < m$: recurse to the left
  * If $k > m$: recurse to the right

```text











```
