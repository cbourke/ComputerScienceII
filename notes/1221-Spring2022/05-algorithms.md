
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

```text









```
