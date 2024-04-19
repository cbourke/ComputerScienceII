
# Trees & Tree-Based Data Structures
## CSCE 156H - Computer Science II
### Spring 2024

## Introduction

* Data *structures* are good because *structure* can be exploited for efficient operations
* Motivation: want a data structure that supports efficient:
  * Retrieval/search
  * Insertion
  * Deletion
* Linked Lists:
  * Insertion/delete from the head or tail: $O(1)$
  * Arbitrary insertion/index-based search: $O(n)$
* Array-based lists:
  * Insertion/deletion from the start: $O(n)$
  * Arbitrary index-based search: $O(1)$
  * If sorted: you can also exploit binary search!
* Stacks/Queues:
  * Efficient push/pop poll/offer operations: $O(1)$
  * BUT: they are not general purpose: they *restricted* access in order to give you efficiency
* Ultimate goal: efficient operations for a *general purpose* collection data structure
* Trees provide a *potential* for efficient operations

###

* Observations:
  * Trees with $n$ nodes have $n-1$ edges
  * FOr any two nodes there is exactly ONE path between them

* More structure:
  * Orient our trees

```text










```
