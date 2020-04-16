
# Algorithms & Algorithm Analysis
## CSCE 156/156H - Computer Science II - Spring 2020

### Motivating Demonstration 

* Consider the simple *algorithm* of summing a list of numbers
* Our "elementary operation": index-based retrieval method
* In general:
  * Array-based list: a simple memory offset computation
  * Because an array-based list has *random access* 
  * An index-based find "algorithm" is essentially "free"
  * Constant-time operation
  * For a list of size n, then you are doing n of these operations
  * The complexity of your algorithm can be captured by some linear function:  t(n) = an + b 
  
* An index-based find operation on a linked list is NOT constant
  * To access the i-th element, requires i node traversals/operations
  * In total, the complexity of using a linked list devolves into a quadratic behavior: t(n) = an^2 + bn + c

* How bad is this?
* With linear performance, increasing the size of your list ("input size") means that you are performing more operations
* With a linear performance, the growth is linear 
* If you increase the size of your list by a factor of 10, then the number of operations increases by a factor of 10 => your algorithm (program) will take ~10 as long to execute
* With a quadratic performance, an increase in the input size of a factor of 10 will increase the number of operations (and thus time) by a factor of 100
* Perspective: 
  * Quadratic regression (Correlation Coefficient: 0.994):
    $$t(n) = 6.4101E{-4}n^2 -0.11168n + 9.7822$$
  * Quadratic Predictions: how long will it take to sum lists of sizes
    * 10 million elements: 17.49 hours
    * 100 million elements: 74 days
    * 1 billion: 20+ years
  * Linear Predictions:
    * 10 million elements: 1 second!
    * 100 million elements 10 seconds
    * 1 billion 2 minutes

## Introduction

* Clear: computational processes have some sort of *expense* or *resource* 
* Examples:
  * Time/computation time/CPU time
  * Memory
  * Power
  * Network bandwidth/throughput
  * Number of gates or wires
  * Idleness/utilization
* The above are all extremely important *engineering* considerations
* From a theoretical algorithm point of view: they are all just constants that do not matter
* What matters: is the performance of the algorithm with respect to the input size's *growth rate*
* Algorithms are *independent* of the hardware they are being run on
  * Algorithsm predate modern computing by thousands of years
  * Algorithms do not suddenly become efficient because you upgrade your hardware, 
  * Operations become more efficient, but the NUMBER of operations would remain the same
  * The growth rate of the algorithm's performance would stay the same
  * Larger inputs -> slower performance = more resources
  * The amount of data and number of operations is what we want to focus on!

### Pseudocode

* An *algorithm* is an unambiguous sequence of instructions for solving a problem
* An algorithm is not a java program, it is not a method, it is not a function, snippet of code, etc.
* Good pseudocode:
  * makes good use of English
  * Makes good use of mathematical notation
  * Doesn't use language specific constructs 
  * Gives the *essential* details of an algorithm while 
  * Omitting any unnecessary details
* Example:
  * Find the maximum element in a collection

## Algorithm Analysis 

1. Identify the input
2. Identify the input size, n
3. Identify the *elementary operation* 
4. Determine how many times the elementary operation is executed with respect to the input size, n
5. Characterize the algorithm's behavior (growth rate) using Big-O Analysis

### Identify the Input

* Generally given as part of the pseudocode
* In some cases, the choice may not be clear
* In general, you only want to focus on a single input
* In general, you only want to focus on the most important input
  * Example: Linear search has 2 inputs: a collection and a key element: you should focus on the collection
  * Example: Euclid's GCD algorithm: there are two integer inputs; so WLOG = Without Loss of Generality you can focus on only one of the numbers, generally choose the larger.  WLOG, assume that a >= b; so a is our input
  
### Identify the Input Size

* Subtle but important and maybe tricky step
* Collection inputs: the size of the collection (the number of elements in it)
* Image: h x w (total number of pixels, or choose the larger dimension)
* Number: an integer x: the number of bits it takes to represent x
  * How many bits does it take to represent an integer x?
  * ~n = log_2(x) bits
  * Why is this important?  Seemingly "simple" algorithms can have bad analysis if you misidentify the input size
  
### Elementary Operation

* Ultimately: you want to characterize the algorithm's complexity; the amount of "work" it does
* Thus you need to identify what "work" means
* In general you choose the most common or expensive operation
* In general, you exclude things that are necessary for the control structure of the algorithm.
* Examples:
  * Comparisons
  * Arithmetic operations: addition/subtraction, multiplications/divisions 
  * Graph/list: node traversals/examinations

### Analysis

* Analyze how many times the elementary operation is executed with respect to the input size
* That is, we want to produce a *resource function*
* f: N -> R^+ (natural numbers to the positive real numbers)
  * N: capture the input size
  * R^+: captures the amount of work
* It may require a calculation of summations, finding closed form solutions, or more complex math
* In general, you want a *worst case* analysis: you want to provide an *upperbound* on the amount of work your algorithm will perform

## Asymptotics

* Motivation: want to characterize an algorithm on how efficient it is with respect to the input size, n
* How does the function (step 4) grow with respect to n? 
* For larger and larger n, what is the growth rate
* For small input sizes, the practical differences between two algorithms don't matter that much
* In addition: we don't want to have to worry about 
  * constants
  * lower order terms
* Thus the tool that we use to characterize the rate of growth of resources functions is Big-O Analysis, or "Asymptotics"

### Definition

* Definition: Let $f(n), g(n)$ be functions, we say that $f(n)$ is Big-O of $g(n)$ if there is a positive constant $c$ and an integer $n_0$ such that 
  $$f(n) \leq c g(n)$$  
* Big-O characterizes a function's order of growth as $n \rightarrow \infty$
* It allows you to ignore lower order terms
* It allows you to ignore constants 
* It allows you to focus on the most significant term in a function
* Misc:
  * We *can* write $f(n) = O(g(n))$
  * The "real" way to write this is $f(n) \in O(g(n))$
  * The definition is not "tight": that it establishes a *relative* ordering of function rates of growth
  * $3n + 3 = O(n)$
  * $3n + 3 = O(n^2)$
  * $3n + 3 = O(2^n)$
  * However, $3n^2 + 2n - 3 \neq O(n)$

### Proofs

* Given two functions, $f(n)$ and $g(n)$ how do you *prove* that $f(n) = O(g(n))$
* Two techniques:
  * Approach A: find the cross over point where one function becomes larger than the other (fixing $c = 1$ and finding $n_0$ so that all $n \geq n_0$ the inequality holds)
  * Approach B: given two functions, find an inequality that works for some $n_0$: that is, fix $n_0$ and find $c$

## Searching & Sorting Revisited

* Demonstrations
* Binary Search (Recursive)
* Master Theorem
* Insertion Sort
* Quick Sort
* Merge Sort




