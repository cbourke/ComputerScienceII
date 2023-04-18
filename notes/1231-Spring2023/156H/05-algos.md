
# Algorithms & Algorithm Analysis
## CSCE 156H - Computer Science II - Spring 2023

### Motivating Demonstration

* Performance of an index-based retrieval operation (`get(i)`) on a list data structure
  * Linked List
  * Array List
* Perspective: how bad is our linked list code?
* Quadratic regression (Correlation Coefficient: 0.994):
    $$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * 10M: ~18 hours
  * 100M: 74 days
  * 1B: 20 years
* Linear behavior:
  * 10 million elements: < 1second
  * 100 million: 10 seconds
  * 1 billion: 2 minutes

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Actual time/CPU time
  * Memory
  * Actual money
  * Bandwidth/throughput
  * Power consumption
  * Circuits: number of wires/gates
  * Idleness/Utilization
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

* An algorithm is "an unambiguous sequence of instructions for solving a problem"
* This is not a mathematical definition (for that, see Turing Machines or Lambda Calculus)
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
* In some cases, the choice may not be clear
* In general: keep it simple, focus on ONE input if there are multiple
* You focus on the most relevant input that may affect the runtime of your algorithm
* Euclid's GCD algorithm: you have 2 inputs, $a, b$; WLOG (without loss of generality) assume that $a \geq b$ so you can focus on the "larger" one, $a$

### Identify the Input Size

* Subtle but important and may be tricky
* Obvious/straighforward examples:
  * Collection: the size of the collection (number of elements in it)
  * Matrix: $n \times m$ matrix? it could be simplified to just $n$
  * Image: $w \times h$, just assume $w \geq h$ simplify to $w$
  * Tricky: a single number, $n$; number of bits it takes to represent $n$: $\log_2(n) = \log(n)$


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
* $\mathbb{N}$ (`\mathbb{N}`) is our domain because we do not have "negatively" sized nor "fractional" input sizes
* $\mathbb{R}^+$ (`\mathbb{R}^+`) is our codomain because we do not want to consider "negative" resources
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* In general, you want to assume the *worst case* scenario: you want to provide an *upperbound* on the worst running time or other resource that the algorithm could cost you.

## Asymptotics

* Step 5: Provide an asymptotic characterization of the complexity function
* Motivation: want to characterize an algorithm's performance/efficiency with respect to the input size $n$
* We're NOT interested in small values of $n$
* We want to know how our algorithm performs as $n \rightarrow \infty$
* We want a tool that will allow us to:
  * Focus on the growth rate (ignore lower order terms)
  * ignore constants
* The tool we use for this is "Big-O" analysis
* O is omicron $O$, There is no `\Omicron` in $\LaTeX$

### Definition

Definition: let $f(n)$ and $g(n)$ be two functions (for our purposes, they represent the complexity of two algorithms, $A, B$).  We say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$

* Notation:
  * It is best/most proper to say $f(n) \in O(g(n))$
  * It is okay to say that $f(n) = O(g(n))$
* Intuition:
  * $f(n)$ is *bounded above* (after a certain point) by $g(n)$
  * $g(n)$ is *asymptotically* larger than $f(n)$
  * $f(n)$ is *asymptotically* less than $g(n)$
* Proof techniques:
  * A: Fix $c=1$ and find the "cross over points" to prove that the inequality holds after the largest cross over point ($n_0$)
  * B: Fix $n_0 = 0$ and find *a* $c$ that satisfies the inequality

## Categories of Functions

* Constant: $O(1)$ (formula computation)
* Logarithm: $O(\log{(n)})$ (binary search)
* Linear $O(n)$ (linear search)
* Quasilinear: $O(n\log{(n)})$
* Quadratic $O(n^2)$ (insertion sort, selection sort)
* Cubic $O(n^3)$ (transitive closure)
* Polynomial: $O(n^k)$
* Exponential: $O(2^n)$
* Superexponential: $O(n!)$


```text








```
