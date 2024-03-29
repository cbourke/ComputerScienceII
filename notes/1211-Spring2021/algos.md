
# Algorithms & Algorithm Analysis
## CSCE 156 - Computer Science II - Spring 2021

### Motivating Demonstration

* Consider searching a linked list (`indexOf` method)
* Our "elementary operation" is traversing a node in the list
* For the "naive" approach:
  * required about $n^2$ traversals
  * The "algorithm" in this case was a "quadratic" algorithm
  * This is *extremely slow*
  * For every order of magnitude increase in the list size, we have a 100-fold increase in time complexity (the time it takes to execute the algorithm)

* How bad is this in reality?
* The "better" solution: take your paint can with you, only traverse each node at most once
  * There is at most 1 traversal per node
  * The "complexity" of this algorithm can be quantified witha  linear function: $n$
  * if we increase the input size by 10 fold, then the algorithm will only be 10 times slower

* Perspective:
  * Quadratic regression (Correlation Coefficient: 0.994):
      $$t(n) = 6.4101E{-4}n^2 -0.11168n + 9.7822$$
  * 10 million elements: 17.59 hours
  * 100 million: 74 days
  * 1 billion: 20+ years
* Linear behavior:
  * 10 million elements: < 1second
  * 100 million: 10 seconds
  * 1 billion: 2 minutes

## Introduction

* It should be clear that programs/algorithms require computational resources
  * time, CPU cycles, physical time
  * memory
  * power consumption
  * network bandwidth/throughput
  * Number of gates or wires in a circuit
  * idleness/utilization
* The above are extremely important *engineering* considerations
* From a theoretical perspective, we need an abstract notion of "resource" that is not tied to a particular machine
* We want to analyze algorithms from an *abstract perspective*
* What matters is the performance of an algorithm with respect to its *input size*, not any particular machine, language, architecture, etc.
* We are interested in the *growth rate* of the resources consumed by the algorithm
* Algorithms are *indepedent* of the hardware they are run on
  * Algorithms predate the modern computer by thousands of years!
  * Algorithms do not suddenly become efficient because you upgrade your hardware
  * Individual operations become faster and more efficient, but the *number* of operations remains the same
  * The rate of growth of an algorithm remains *invariant* regardless of the hardware
  * larger inputs lead to slower performance = more resources

### Pseudocode

* An algorithm is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, it is not a snippet of code
* Good pseudocode:
  * it makes use of plain English
  * It makes good use of mathematical notation
  * It doesn't have any language-specific constructs
  * It gives the essential details of an algorithm while...
  * Omitting any unnecessary details
  * Example: find the maximum element in a collection
* Note:
  * there are no "rules" for good pseudocode, only guidelines
  * Adopt any style you like, keep it consistent

## Algorithm Analysis

1. Identify the input
2. Identify the input size, $n$
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size
5. Characterize the algorithm's behavior (growth rate) using Big-O analysis

### Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases, the choice may not be clear
* In general, you only want to focus on ONE input
* Examples:
  * Linear search has 2 inputs: a collection and a single key element: focus on the size of the collection
  * Example: Euclid's GCD algorithm has 2 integer inputs, without loss of generality: focus only on one (the larger input)

### Identify the Input Size

* Subtle but important and maybe tricky
* If your input is a collection: then the size is the number of elements in that collection
* Image: height x width = n x m
  * n*m
  * assume n > m, then $n*m < n^2$
  * or, just simplify it to one dimension: $n$
* A single integer, $x$:
  * Is it *not* just "one" input
  * Numbers have a *representation* in a computer (or on paper)
  * The input size of a number is the number of bits used to represent that number, $n = \log_2(x)$
  * All of our bases's will be base-2 and we simply write, $\log(x)$

### Elementary Operation

* Ultimately: we only want to focus on ONE operation in an algorithm
* You generally want to choose the most:
  * expensive or
  * common or
  * relevant operation
* You generally also exclude operations that are necessary for the control structures of the algorithm
* examples:
  * Comparisons
  * Arithmetic operations: additions/subtractions, multiplications/division
  * traversing nodes in a linked list

### Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function*:
* $t: \mathbb{N} \rightarrow \mathbb{R}^+$ (input sizes to a resource measure such as time)
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* In general, you want to assume the *worst case* scenario: you want to provide an *upperbound* on the worst running time or other resource that the algorithm could cost you.

## Asymptotics

* Motivation: want to characterize an algorithm's performance and efficiency with respect to its input size
* We're interested in its performance as $n$ (input size) *grows
* For larger and larger inputs, how does the growth rate of the function that characterizes and algorithm's efficiency: what kind of "order" can we give it
* We want to:
  * ignore contsants
  * ignore lower order terms
* The tool we use for this is "Big-O" analysis

### Definition

Definition: let $f(n)$, $g(n)$ be functions, we say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$  
* Big-O characterizes a function's order of growth as $n \rightarrow \infty$
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
  * Approach A: find the "cross over" point where one function will become larger for all values larger than that cross over point: it is essentially fixing $c = 1$ in the inequality and finding $n_0$  
  * Approach B: we fix $n_0 = 0$ and then find a $c$ such that the inequality holds: start with $f(n)$ and make it bigger and bigger until you get $g(n)$

## Searching & Sorting revisisted...

* Binary Search
* Master Theorem
* Quick Sort
* Merge Sort

### aNalyzing recursive algorithms

1. You define a function that captures the idea of "work" or the elementary operation
2. You setup a recursion: you capture the notion of how much *non*-recursive work you do, and then how much recursive work you do.
3. You solve the recurrence or you use Master Theorem

```text





```
