
# Algorithms & Algorithm Analysis
## CSCE 156H - Computer Science II - Spring 2022

### Motivating Demonstration

* Consider searching a linked list (`indexOf` method)
* Our "elementary operation" is traversing a node in the list
* For the "naive" approach:
  * required about $n^2$ traversals
  * The "algorithm" in this case was a "quadratic" algorithm
  * This is *extremely slow*
  * For every order of magnitude increase in the list size, we have a 100-fold increase in time complexity (the time it takes to execute the algorithm)

* Perspective
* Quadratic regression (Correlation Coefficient: 0.994):
    $$t(n) = 6.4101E{-4}n^2 -0.11168n + 9.7822$$
  * $n$ = 10 million: 17.59 hours
  * 100 million: 74 days
  * 1 billion: 20+ years
* Linear solution:
  * 1 million: < .06 seconds
  * 100 million: 10 seconds
  * 1 billion: 2 minutes

## Introduction

* It should be clear that programs "cost" resources
  * Time: real time, CPU time, CPU cycles
  * Memory
  * power consumption
  * Network throughput, bandwidth
  * heat dissipation
  * Number of wires or gates in a circuit
  * idleness/utilization
* These are all extremely important *engingeering* considerations
* If you upgrade your hardware to be twice as fast, your algorithm is not twice as good, it remains the same
* There are the same number of steps involved regardless of the hardware you run it on!
* Goal: analyze algorithms independent of any particular hardware, language, or other engineering consideration
* What matters is the performance of an algorithm with respect to its *input size*, not any particular machine, language, architecture, etc.
* We are interested in the *growth rate* of the resources consumed by the algorithm

## Algorithms

* An *algorithm* is an unambiguous sequence of instructions for solving a problem
* An algorithm is NOT:
  * A Java program, a python script, etc.
  * snippet of code, a function, method, etc.
* To describe an algorithm we use *pseudocode*
* Good pseudocode
  * makes good use of plain English
  * makes good use of math notation
  * doesn't use language-specific constructs
  * It gives *essential* details and omits unnecessary details
  * Idea: you can take good pseudocode and translate it to any programming language

## Algorithm Analysis

  1. Identify the input
  2. Identify the input size, n
  3. Identify the *elementary operation*
  4. Determine how many times the elementary operation is executed with respect to the input size, n
  5. Characterize the algorithm's behavior (growth rate) using Big-O Analysis

### Identify the Input

* Generally given as part of the pseudocode
* In some cases, you may have multiple pieces of input
* Example:
  * Linear search: a collection AND a key
  * Euclid's GCD algorithm has two inputs: two integers, $a, b$
* In general you keep it simple: select ONE of the inputs as your input (usually the most relevant one)
  * Linear search: the collection
  * Euclid's GCD: assume that $a \geq b$, then choose $a$ (ie the "larger" one)
  * Suppose you consider BOTH $a, b$, then the input "size" is no greater than $2a \geq a + b$

### Identify the input size

* Usually you use the variable $n$ to denote the size of the input
* Generally easy:
  * Collection: size of the collection
  * Matrix or an image, $n \times m$: simplify, asume $n \geq m$, then $m + n \leq 2n$, $n \times m \leq n^2$
* What if the input is a single number (integer): $x$
  * What is the "input size" of a number
  * A constant number of bits?  32 or 64
  * 1234 and 94723948723984723987492387
  * The input size of a number is the number of digits or bits or other *symbols* needed to represent it
  * The number of digits to represent $x$ is $\log_{10}(x)$
  * The number of bits to represent $x$ is...
  $\log_2{(x)}$

* Suppose I gave you $k$ bits to represent a number...
* With $k = 1$ bits the max number you could represent is... 1
* With $k = 2$ bits the max number you could represent is... 11 = 3
* With $k = 3$ bits the max number you could represent is... 111 = 7
* With $k$ digit the max number you could represent is... $2^k - 1$
* Reverse the question: given $x = 2^k-1$ the number of bits $k$ to represent it is...
* $\log_2{(x+1)} = k$

### Elementary Operation

* Ultimately you want to characterize the algorithm's complexity or hte amount of "work" it does
* We only want to focus on ONE operation
  * Linear Search: comparison
  * Math operations: addition/subtraction, multiplication/division
  * Linked Lists: node traversal (graphs, trees)

### Analysis

* You simply come up with a summation or formula to characterize how many times the elementary operation is executed with respect to the input size, $n$
* $f: \mathbb{N} \rightarrow \mathbb{R}^+$
* this might involve a summation, solving summations, etc.
* Generally we will be interested in an *upper bound* on the complexity of an algorithm

### Example: Linear Search

1. Input: The collection $A$
2. Input size: $n$, the number of elements in $A$
3. Elementary Operation: comparisons on line 2
4. **Worst case: $n$**, best case: $1$, average case: $n/2$
5. $O(n)$ (linear!)

### Example: Binary Search

```python
Input: a collection A[1..n], a key k
Output: an element a in A such that a = k,
if no sch element, output None

left = 1
right = n

while(left <= right):
  m = (left + right) / 2
  if a[m] = k:
    output a[m]
  elif k < a[m]:
    # search the left half...
    right = m - 1
  else
    # a[m] < k:
    # search the right half...
    left = m + 1
output None
```

### More Examples

* Searching & Sorting
* Selection Sort
* Insertion Sort
* Quick Sort
* Merge Sort
* Binary Search

### Asymptotics

* Motivation: want to characterize an algorithm's performance or complexity with respect to its input size
* $t(n)$ (time): the number of elementary operations executed with respect to the input size, $n$
* We're more interested in how the algorithm will perform with larger and larger inputs
* IE as $n \rightarrow \infty$
* Ultimately:
  * Ignore lower order terms (only focus on the most significant term that contributes to the growth of the function)
  * Ignore constants (you can always upgrader your hardware!)
* the tool we use is Big-O analysis

### Definition

**Definition** Let $f(n)$, $g(n)$ be functions (suppose that these function characterize two algorithms, $A, B$) we say that $f(n)$ is "big-O" of $g(n)$ and write that $f(n) = O(g(n))$ if there exists a positive constant $c$ and an integer $n_0$ such that
  $$f(n) \leq c g(n)$$
for all $n \geq n_0$
  * Big-O characterizes the growth rate or order of growth of a function
  * $g(n)$'s growth rate is *at least* as big as $f(n)$
  * $f(n)$'s growth rate is *bounded above* by $g(n)$
* It is not a strict relative comparison:
  * $3n+5 = O(n)$
  * $3n+5 = O(n\log(n))$
  * $3n+5 = O(n^2)$
  * $3n+5 = O(n^3)$
  * $3n+5 = O(2^n)$
* THe following are NOT true:
  * $3n^2 + 5n = O(n)$
  * $2^n = O(n)$
  * We do NOT write: $O(n) = 3n+ 5$
* Take note of the notation:
  * Strictly speaking, the equality is not correct
  * LHS: is a function
  * RHS: class of functions; it is a *set* of functions
  * Technically correct notation: $f(n) \in O(g(n))$
* Example: $f(n) = 175n^2 + 50n$, $g(n) = n^3$
  * Intuitively: $f(n) = O(g(n))$

### Proofs

* Given two functions, $f(n), g(n)$ how do you prove that $f(n) = O(g(n))$
* Two techniques:
  * Approach A: find the "cross over" point where one function becomes larger than the other for all values greater than the point: fix $c = 1$ and find $n_0$
  * Approach B: Fix $n_0 = 0$ and find a $c$ such that the inequality is satisfied
* By setting up an inequality and finding roots, you take the largest value of $n$ (largest root value) and then the inequality will hold for $n_0$ being the largest value and $c = 1$

### Asymptotics on Recursive Algorithms

* Recall Quick Sort:
  * Setup a function, $C(n)$ = number of comparisons by Quick Sort on an array of size $n$
  * $C(n) = 2C(n/2) + n - 1$
  * $n-1$ comparisons to partition the array into two *roughly* equal sub arrays
  * Two recursive calls to each sub array, each requiring $C(n/2)$ comparsisons

`.DS_Store`

```text









```
