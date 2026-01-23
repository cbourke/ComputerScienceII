# Computer Science II - CSCE 156H
## Spring 2025
### Introduction to Python

# Basics

## Overview

* Guido van Rossum (1989/91)
* Interpreted language (scripting language)
* Dynamically typed
* Supports integers, floats (doubles), strings
* Icky: whitespace matters!
* Style: do things the "Pythonic" way: https://peps.python.org/pep-0008/
  * `lower_underscore_casing` for variables and function names
  * four spaces for indentation
  * `pylint` is a useful style guide

```python
"""
Chris Bourke
2025-01-22

Prints Hello World to the standard output
"""

def say_hello():
    """
    Prints "Hello World" to the standard output.
    """
    print('Hello World')

if __name__ == "__main__":
    say_hello()
```

* If not already installed, use: `pip install pylint`

## Basic Operators

```python


a = 10
b = 20

c = a + b
print(c)

c = a - b
print(c)

c = a * b
print(c)

# division, autoamtically changes to a double
c = a / b
print(c)

# integer truncation division
c = a // b
print(c)

# integer remainder division:
c = a % b
print(c)

# string concatenation
a = "Hello"
b = "World"
c = a + " " + b
print(c)

a = 10
#ERROR: message = "a = " + 10
message = 'a = ' + str(10)
print(message)

# Better output: f-strings
# print-f style formatting
message = f'a = {a}'
print(message)

message = f'a = {a:d}'
print(message)

message = f'a = {a:.2f}'
print(message)

message = f'Hello World'
print(message)

PI = 3.14159
PI = 3

```

### Misc Math

* Integers in python are arbitrary precision integers
* Floating point numbers are 64-bit IEEE754 (`double`s)
* Math library

```python

import math

x = 2
y = math.sqrt(x)
print(y)
```

## Basic I/O

* You can prompt for input (we won't)
* Command Line Arguments
  * you need to `import sys`
  * All arguments are stored in the `sys.argv` list
  * The size (number of arguments): `len(sys.argv)` (length of a list)
  * Stored as strings starting at index `0`

```python
import sys

print(f'There are {len(sys.argv)} CLAs')

# the first one is always the script file name
print(f'sys.argv[0] = {sys.argv[0]}')
print(f'sys.argv[1] = {sys.argv[1]}')
print(f'sys.argv[2] = {sys.argv[2]}')

# convert a value to an integer:
x = int(sys.argv[1])
y = float(sys.argv[2])
```

* Basic output: `print()`
* Use f-strings: they are awesome
* Same basic placeholders as `printf`: integers `d`, floats: `f`

```python
import math

# Python does not actually support constants
# instead: you use the convention of UPPER_UNDERSCORE_CASING
NUMBER_OF_STUDENTS = 17

# Don't do this: math.pi = 3.5
print(math.pi)
print(f'{math.pi}')
print(f'{math.pi:f}')
print(f'{math.pi:.4f}')
print(f'{math.pi:10.4f}')

x = 42
print(f'{x:10.4f}')
print(f'{x:10d}')
print(f'{x:010d}')

```

# Conditionals

* Python supports `if` and `else` keywords, but else-if structures are `elif`
* At the end of the condition, you use `:` and indentation
* There is no `else if`, instead you use `elif`

```python


husker_score = 1
ohio_score = 4

if husker_score > ohio_score:
    print('Huskers Win')
    print('Celebrate')
elif husker_score < ohio_score:
    print('Huskers Lose')
    print('Weep')
else:
    print('Tie')
    print('Extra Innings')
```

* Comparison Operators: `<, <=, >, >=, !=, ==`
  * YOu *can* use these for strings

```python

x = 42

if x == 42:
    print("foo")

a = 'apple'
b = 'Banana'

if a < b:
    print(f'{a} comes before {b}')
elif a > b:
    print(f'{b} comes before {a}')
else:
    print(f'{a} equals {b}')
```

* Logical Operators:
  * You use the keyword `not` instead of `!`
  * You use the keyword `and` instead of `&&`
  * You use the keyword `or` instead of `||`
  * You use the keywords `True` and `False` (*case sensitive*)


```python

is_student = False

if is_student:
    print('discount')
```

# Loops

* Python has `for` loops and `while` loops
* For loops are *always* foreach loops!

```python

n = 10
# range will create a list/collection of integers 0, 1, 2, ... n-1
for i in range(n):
    print(i)

i = 0
while i < n:
    print(i)
    i += 1

print('===========')
# different starting value:
for i in range(2, n):
    print(i)

print('===========')
# different iterator value:
for i in range(0, n, 3):
    print(i)
```

# Collections

* Python has:
  * Lists - ordered collections of things, 0-indexed
  * Sets - unordered collections of *unique* elements
  * Dictionaries - maps mapping integers and/or strings to other elements

```python
import pprint

# empty list:
primes = []
print(len(primes))

# empty list:
primes = [2, 3, 5, 7, 11, 13, 17]
# len gives the size of the list
print(len(primes))

# not the pythonic way:
for i in range(len(primes)):
    x = primes[i]
    print(x)

for x in primes:
    print(x)

# add stuff:
primes.append(19)
primes.append(23)

pprint.pprint(primes)

# remove the last element:
x = primes.pop()
print(x)
pprint.pprint(primes)

primes.insert(0, 42)
# negative values wrap around to the end
primes.insert(-3, 101)
pprint.pprint(primes)

# larger values are just to the end!?
primes.insert(12, 202)
pprint.pprint(primes)

# Retrieval is based on indices:
first = primes[0]
# negatives wrap around
foo = primes[-1]
print(foo)

#larger values in either direction are errors
#bar = primes[-30]
#print(bar)

# You can access a subarray using index values:
# from index 3 up to 5
foo = primes[3:6]
print(primes)
print(foo)

# starts at index 0 up to 5
foo = primes[:6]
print(primes)
print(foo)

# start at index 3 all the way to the end
foo = primes[3:]
print(primes)
print(foo)

## Sets

names = set()
names.add("Chris")
names.add("Dansby")
names.add("Kyle")
names.add("Kyle")

pprint.pprint(names)

# set to a list:
names_list = list(names)
names_list.append("Kyle")
pprint.pprint(names_list)

# list to a set:
primes_set = set(primes)
pprint.pprint(primes_set)

# Dictionaries

name_map = {
    3.5: "Foo",
    0: "Chris",
    3: "Kyle",
    "Kyle": 45,
    "Foo": 4.5
}

pprint.pprint(name_map)

x = name_map["Kyle"]
print(x)
name_map["Kyle"] = 43
pprint.pprint(name_map)

# Key elements are unique
# Two different keys can map to the same value
# but one key maps to one value

# add stuff:
name_map['Craig'] = -1
pprint.pprint(name_map)

#empty map
map = {}
n = 100
for i in range(n):
    map[i] = i + 1
pprint.pprint(map)
```

# Comprehensions

```python
import sys
import pprint

primes = [2, 3, 5, 7, 11, 13, 17, 19, 23]

# all the primes great than or equal to 10
foo = [ x for x in primes if x >= 10 ]
pprint.pprint(foo)

evens = [x for x in primes if x % 2 == 0]
pprint.pprint(evens)

large_primes = [x for x in primes if x > 10000000]
pprint.pprint(large_primes)

foo = [ x + 1 for x in primes ]
pprint.pprint(foo)

foo = { x + 1 for x in primes }
pprint.pprint(foo)

# maps:
foo = { v:v+1 for v in primes }
pprint.pprint(foo)

# iterating over a map
# iterate over keys:
for k in foo.keys():
    print(k)

# iterate over values:
for v in foo.values():
    print(v)

# iterate over key-value pairs
for k,v in foo.items():
    print(f'{k} maps to {v}')

# identity matrix n x n
n = 5
ident = [ [ 1 if j == i else 0 for j in range(n) ] for i in range(n) ]
pprint.pprint(ident)

# comprehensions are good for "simple" stuff
# can get really complicated for stuff that is even a little "not simple"

pprint.pprint(sys.argv)
args = [ int(x) for x in sys.argv[1:] ]
pprint.pprint(args)

# Tuples
# tuples are ordered sequences of values
# singleton, pairs, triples, quadruples
pair = (1, "one")
pprint.pprint(pair)
triple = (1, 2, "shoe")
pprint.pprint(triple)

#for i = 0...n-2
#  for j = i+1...n-1

all_pairs_with_self_reference = [ (i, j) for i in range(n) for j in range(i, n)]

all_unique_pairs = [ (i, j) for i in range(n-1) for j in range(i+1, n)]

all_pairs_all = [ (i, j) for i in range(n) for j in range(0, n)]
pprint.pprint(all_pairs_all)

# n choose 2: n*(n-1) / 2 + diagonal = n
print(len(all_pairs_with_self_reference)) #15
# n choose 2: n*(n-1) / 2
print(len(all_unique_pairs)) #10
# entire matrix
print(len(all_pairs_all)) #25

pprint.pprint(all_pairs_with_self_reference)

numbers = set( [1, 2, 3, 4] )
names = set( ["Kyle", "Chris", "Criag", "Joe"])

#A x B = { (a,b) for a in A and b in B}
#(1, "Kyle"), (1, "Chris"), (2, "Kyle")

cross_product = [ (a,b) for a in numbers for b in names ]
pprint.pprint(cross_product)
```

```python
import sys
import pprint

zips = [
  "05026",
  "56605",
  "56605",
  "56605",
  "56605",
  "63260",
  "24873",
  "33992",
  "41588",
  "99493",
  "94624",
  "68508",
  "68508",
  "68508",
  "68508",
  "68508",
  "41743",
]

# grab all zipcodes that start with 0
zero_zips = [ x for x in zips if x.startswith("0")]
pprint.pprint(zero_zips)

# how many times does each zip code appear?
count = { zip:zips.count(zip) for zip in zips}
pprint.pprint(count)

count_rev = { v:k for k,v in count.items() }
pprint.pprint(count_rev)

# most common COUNT of the most common zip:
x = max(count_rev.keys())
print(x)

# map a count to a list of zip codes that appear that many times
count_rev = { k:set([x for x in zips if zips.count(x) == k]) for k in count_rev.keys() }
pprint.pprint(count_rev)
```

# Strings

* Strings are a built-in type in Python
* Strings are immutable, once created you cannot change them

```python
import sys
import pprint
import re


csv_data = "Chris,Bo\turke,105 Sch\n\n\norr,402-472-5008,School of Computing"
data = csv_data.split(",")
pprint.pprint(data)


data = re.split("[0-9]{3}",csv_data)

data = re.split("\\s+",csv_data)
pprint.pprint(data)
```

# Functions, Modules

* Modules are little more than files
* You can create a "library" or "module" by putting methods or variables or code into a file: `library.py`
* There is no call by reference: there are no pointers!

* `library.py`
```python
"""
This would be documentation for the library as a whole
"""
import math

def euclidean_distance(x1, y1, x2, y2):
    """
    This function computes the Euclidean distance between
    the two points `(x1, y1)` and `(x2, y2)`
    """
    return math.sqrt( (x1-x2) ** 2 + (y1-y2) ** 2 )

def foo(x, y, z = 10):
    bar = x + y
    baz = y + z
    x = -42
    return bar + baz

def get_sum(list):
    result = sum(list)
    list.append(42)
    return result

```

* `demo.py`

```python
import sys
import pprint
import re

# import library
from library import euclidean_distance
from library import foo
from library import get_sum

dist = euclidean_distance(2, 2, 4, 4)
print(dist)

x = foo(10, 20, 30)
print(x)

x = foo(10, 20)
print(x)

y = "ten"
x = foo(y, "twenty", "thirty")
print(x)
print(y)

foo = [10, 20, 30]
total = get_sum(foo)
pprint.pprint(foo)
```

# Basic File I/O

* YOu can open to a file using `open()`
* Very similar to `fopen`

## Output

```python
# file output
# f is a "file handle" that points to the file
f = open("data.txt", "w")

x = 42
y = 3.14159
z = "Chris"
f.write("Hello world!\n")
f.write(f'{x}\n')
f.write(f'{x}, {y:.2f}, {z}\n')
f.close()
```

## Input

* `readlines()` retains endline characters
* Trailing empty lines are retained!

```python
f = open("data.txt", "r")
lines = f.readlines()

lines = [ line.rstrip() for line in lines if not line.isspace() ]

pprint.pprint(lines)
```

## Demo

* `book.py`

```python
from functools import total_ordering

@total_ordering
class Book:
    """
    Represents a book
    """

    def __init__(self, id, title, last, first, isbn, rating, year):
        self.id = int(id)
        self.title = title
        self.last = last
        self.first = first
        self.title = title
        self.isbn = isbn
        self.rating = float(rating)
        self.year = int(year)

    def __str__(self):
        return f'{self.title} by {self.last}, {self.first} ({self.rating:.2f})'

    def __repr__(self):
        return f'{self.title} by {self.last}, {self.first} ({self.rating:.2f})'

    def __eq__(self, other):
        return self.id == other.id

    def __lt__(self, other):
        return self.id < other.id

```

* `book_demo.py`

```python
import pprint
from book import Book

f = open("books.csv", "r")
lines = f.readlines()

lines = [ line.rstrip() for line in lines[1:] if not line.isspace() ]

books = []
for line in lines:
    tokens = line.split(",")
    book = Book(*tokens)
    books.append(book)

pprint.pprint(books)

# What's the best book
numbers = [3, 5, 6, 7, 8, 4, 0]
numbers.sort(reverse=True)
pprint.pprint(numbers)

books.sort(key = lambda book : book.rating, reverse=True)
pprint.pprint(books)
best_book = books[0]
pprint.pprint(best_book)

# Oldest?  Newest?
books.sort(key = lambda book : book.year)
oldest_book = books[0]
newest_book = books[-1]

pprint.pprint(oldest_book)
pprint.pprint(newest_book)

# All book(s) by douglas adams
adams_books = [ b for b in books if (b.first,b.last) == ("Douglas", "Adams") ]
pprint.pprint(adams_books)

# sort by author then by year
books.sort(key = lambda book : (book.last, book.first, book.year) )
pprint.pprint(books)

author_to_books = { (b.first,b.last):[bb for bb in books if (bb.first,bb.last) == (b.first,b.last)] for b in books }
print("\n\n\n\n")
pprint.pprint(author_to_books)
```



```text













```
