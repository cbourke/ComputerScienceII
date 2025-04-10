
# Algorithms & Algorithm Analysis
## CSCE 156H - Computer Science II - Spring 2025

### Motivating Demonstration

* Performance of an index-based retrieval operation (`get(i)`) on a list data structure
  * Array List: you could use random access, so there are a constant number of operations; $\approx n$ operations overall
  * Linked List: you had to traverse starting at the head
    * Each index-based retrieval required $i$ traversals
    * Overall: $\approx n^2$ operations
* Perspective: how bad was the linked-list version?
* Array-based: linear behavior
  * $n = 1M$: 0.009s
  * $n = 10M$: 0.030s
  * $n = 100M$: 0.300s
  * $n = 1B$: ~ 3s
* Quadratic regression (Correlation Coefficient: 0.994):
$$t(n) = 0.00064101n^2 -0.11168n + 9.7822$$
  * $n = 10M$: 17.59 HOURS
  * $n = 100M$: 74 DAYs
  * $n = 1B$: 20 years

## Introduction

* It should be clear that programs/algorithms require computational resources
  * Processing time/CPU/GPU time
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
  * An algorithm does not suddenly become efficient just because we upgraded to a 5090
  * Individual operations become faster on faster machines, but the *number* of operations stays the same
  * Larger inputs lead to slower performance = more resources

## Pseudocode

* Pseudo = false/fake, code = code
* An algorithm is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, it is not a snippet of code
* A general outline that looks kinda-like code
* There are no pseudocode "rules"
* Good pseudocode:
  * Makes use of plain English
  * Makes use of good mathematical notation
  * Doesn't use any language-specific constructs
  * It provides necessary details without too many unnecessary details

## Algorithm Analysis

1. Identify the input
2. Identify the input size, $n$
3. Identify the *elementary operation*
4. Determine how many times the elementary operation is executed with respect to the input size: $t(n)$ = time/resource required to execute the algorithm on an input of size $n$
5. Provide a Big-O analysis (asymptotics)

### 1. Identifying the Input

* Generally this is given as part of the pseudocode
* In some cases: the choice is not very clear
* YOu want to focus on the ONE input that is:
  * The most relevant
  * Has the greatest impact on your algorithm's performance
* Example: linear search has both a collection and a key, choose the collection: its size will have the greatest impact on the performance of the algorithm
* Example: Euclid's GCD algorithm that takes two integers $a, b$ as inputs
  * WLOG = Without Loss of Generality
  * WLOG assume that $a \geq b$, then focus only on $a$  

### 2. Identify the Input Size

* Seems simple but can be tricky
* Obvious: a collection (set, list, array, etc.): the size of the collection (number of elements in it, cardinality)
* Matrix: $n \times m$
  * WLOG: assume that $n \geq m$, thus, $n \cdot m \leq n \cdot n = n^2$
  * You can rename the input size variable: $N = n^2$, then only focus on $N$
* What if the input is a single number, $x$ or $n$
  * IS it "1" (only one input, so the size is 1)
  * IS it "32"? (32 bits in a 2-s complement signed integer)
  * Is it "$n$" ie the number itself (No, this is "unary")
  * Answer: the number of bits required to represent $n$, $\log{(n)}$

### 3. Elementary Operation

* Ultimately: we only want to focus on ONE operation in an algorithm
* You generally choose the operation that is:
  * most common
  * most expensive
  * most relevant
* Want to have a *simple* analysis
* YOu do NOT consider operations necessary for the control structure of the algorithm
  * comparisons/additions in a for loop
  * you want to focus on what is happening INSIDE the loop
* Examples:
  * Comparisons (searching/sorting)
  * Linked List or a Graph: a node traversal or examination
  * Arithmetic operations: additions/subtractions, multiplications/divisons

### 4. Analysis

* Analyze how many times the elementary operation is executed with respect to the input size, $n$
* You come up with a *resource function* ($t$ is for time):
  $$t: \mathbb{N} \rightarrow \mathbb{R}^+$$
  * $\mathbb{N}$ (`\mathbb{N}`) is our domain because we do not have "negatively" sized nor "fractional" input sizes
  * $\mathbb{R}^+$ (`\mathbb{R}^+`) is our codomain because we do not want to consider "negative" resources
  * We also limit attention to *monotone* increasing functions
* It may require some art, some summations or other calculations, finding closed forms solutions or even super advanced math
* There is no clear guideline: everything is going to be slightly different
* You should generally focus on the *worst case* scenario





```text












```
