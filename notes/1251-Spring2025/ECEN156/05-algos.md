
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


```text














```
