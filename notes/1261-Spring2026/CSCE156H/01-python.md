# Computer Science II - CSCE 156H
## Spring 2026
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

## Varables and Basic Operators

* You don't declare variables, you just use them

```python
import math

a = 10
b = 20

c = a + b
c = a - b
c = a * b
c = a / b

#print(c)

c = 2384723948732098473209874320984732098472390847239087429038472
c = c + c
#print(c)

# this is a comment by the way

# integer truncation division is supported:
c = a // b

# integer division with remainder: modulo or "mod"
a = 10
b = 7
c = a % b
print(c)

# strings
first_name = "Chris"
last_name = 'Bourke'
foo = """this is a multiline
string
yay!"""

# concatenation:
full_name = first_name + ' ' + last_name
print(full_name)

# can you mix stuff?
x = 3.5
foo = "x = " + str(x)
print(foo)

# better ways of doing output:
# use f-strings!
# f = formatted strings
a = 10
b = 3.5
c = 'Hello'
print(f'This is a formatted string, a = {a}, b = {b:.2f}, message = {c}')
print(f'This is a formatted string, a = {a:08d}, b = {b:.2f}, message = {c}')
print(f'hello!')

# you cannot create constants!
math.pi = 3.5
print(math.pi)

# By CONVENTION, you can create constants using the UPPER_UNDERSCORE_CASING
PI = 3.14159


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

n = len(sys.argv)
print(f'There were {n} CLAs')
print(f'argv[0] = {sys.argv[0]}')
print(f'argv[0] = {sys.argv[1]}')
print(f'argv[0] = {sys.argv[2]}')
print(f'argv[0] = {sys.argv[3]}')

# to convert CLAs to numbers:
a = int(sys.argv[1])
b = float(sys.argv[2])
print(f'converted values are {a} and {b}')

```

# Conditionals

* Python supports `if` and `else` keywords, but else-if structures are `elif`
* Other comparison operators: `<, <=, >, >=, !=, ==`

```python

oregan_score = 55
husker_score = 90

if husker_score > oregan_score:
    print('Huskers won!')
    print('yay!')
elif husker_score < oregan_score:
    print('Oregan won!')
    print('boo!')
else:
    print('tie, OT')
```

## Logical Operators:

* You use the keyword `not` instead of `!`
* You use the keyword `and` instead of `&&`
* You use the keyword `or` instead of `||`
* You use the keywords `True` and `False` (*case sensitive*)

```python

is_student = True
age = 31

if is_student and age < 21:
    print('free admission but no beer')
elif is_student and age > 30:
    print('free admission and free beer')
```


# Loops

* Python has `for` loops and `while` loops
* For loops are *always* foreach loops!

```python

n = 10

for i in range(n):
    print(i)

print('-=-=-=-=-=-=-=-=-=-=-')
# you can modify the defaults:
for i in range(2, n):
    print(i)

print('-=-=-=-=-=-=-=-=-=-=-')
# you can modify the iteration:
for i in range(3, n, 3):
    print(i)

i = 0
while i < n:
    print(i)
    i += 1

```

* Generally: `range(start, stop, step)`
  * `start` - where the loop starts
  * `stop` - where it stops (minus one)
  * `step` - how much it increments

# Collections

* Python has:
  * Lists - ordered collections of things, 0-indexed
  * Sets - unordered collections of *unique* elements
  * Dictionaries - maps mapping integers and/or strings to other elements

```pyhton
import pprint

# create an empty list:
primes = []
primes = list()

#adds to the end of the list
primes.append(42)

pprint.pprint(primes)

primes = [2, 3, 5, 7, 11, 13]

pprint.pprint(primes)

# the size of a list can be found using len()
n = len(primes)
print(n)

for i in range(len(primes)):
    print(f'primes[{i}] = {primes[i]}')

for x in primes:
    print(f'{x}')

#primes.append([10, 20, 30])
#pprint.pprint(primes)

primes.extend([10, 20, 30])
pprint.pprint(primes)

primes.append("hello")
pprint.pprint(primes)

names = list()

names.append("Chris")
names.append("John")
names.append("Jane")
names.append("Alex")
pprint.pprint(names)

names.insert(0, "Vucivec")
pprint.pprint(names)

# remove via index
names.pop(1)
pprint.pprint(names)

# cool stuff: you can get a sub list using the following syntax

# gets the elements 0 thru 2-1 = 1
sub_names = names[0:2]
pprint.pprint(sub_names)

sub_names = names[2:]
pprint.pprint(sub_names)

sub_names = names[:3]
pprint.pprint(sub_names)

# in general this is [inclusive:exclusive]

# you can use negative indexing:
pprint.pprint(names)
x = names[-1]
print(x)

# you cannot use indexing that is larger:
# pprint.pprint(names)
# x = names[4]
# print(x)

names = set()
names.add("Chris")
names.add("Dansby")
names.add("Nico")
names.add("Alex")

pprint.pprint(names)
names.add("Alex")
pprint.pprint(names)

for name in names:
    print(name)

# convert sets to lists
names_list = list(names)
pprint.pprint(names_list)
names_list.insert(0, "Matt")
names_list.append("Alex")
pprint.pprint(names_list)

# ... and lists to sets
names_set = set(names_list)
pprint.pprint(names_set)

# Dictionaries
name_map = {
    3: "Alex",
    2: "Nico",
    54: "PCA",
    3.5: "Foo",
    4.5: "Foo",
    "Jane": "Austin",
    "PI": 3.14,
    "PI": 3.14,
    "E": 2.71,
    "foo": "bar",
    "Foo": "baz"
}

pprint.pprint(name_map)

# you can add/remove after the fact:

name_map["Ian"] = "Happ"

# retrieve:
x = name_map["foo"]
print(x)

# reset/update:
name_map["foo"] = "hello"
pprint.pprint(name_map)
name_map.pop("foo")
pprint.pprint(name_map)

# iteration over key/value pairs:
for key,value in name_map.items():
    print(f'{key} => {value}')

# iteration over keys
for x in name_map:
    print(x)

# iteration over values:
for x in name_map.values():
    print(x)
```

# Comprehensions

```python
import pprint
import sys

primes = [2, 3, 5, 7, 11, 13, 17, 19, 23]

# sum up the primes:
total = sum(primes)

# create a new list of primes that are doubled
result = [ x*2 for x in primes ]
pprint.pprint(result)

# simple initializations
result = [ 1 for i in range(10) ]
pprint.pprint(result)

numbers = [5, 7, 3, 4, 8, 9, 2, 3, 4, 1, 10]
# create a new list of only even numbers from a random list
evens = [ x for x in numbers if x % 2 == 0 ]
pprint.pprint(evens)

# sets
odds = {x for x in numbers if x % 2 == 1}
pprint.pprint(odds)

# maps
foo = { k:k+1 for k in numbers }
pprint.pprint(numbers)
pprint.pprint(foo)

# read in command line arguments and convert them to an integer array
numbers = [ int(x) for x in sys.argv[1:] ]
pprint.pprint(numbers)

# tuples
# doubles, triples, quadruples, quintuples, etc.
pair = (10, 10.5)
pprint.pprint(pair)
first = pair[0]
second = pair[1]

person = ("Chris", "Bourke", 35140602)
first_names = ["Chris", "Jane", "John"]
last_names = ["Bourke", "Smith", "Smithee"]

people = { last_names[i]:(first_names[i], last_names[i]) for i in range(len(first_names))}

pprint.pprint(people)

#for i = 0...n-2
#  for j = i+1...n-1

n = 4
all_unique_pairs = [ (i, j) for i in range(n-1) for j in range(i+1, n)]
pprint.pprint(all_unique_pairs)

for z in all_unique_pairs:
    x, y = z
    print(x)
    print(y)
```

# Strings

* Strings are a built-in type in Python
* Strings are immutable, once created you cannot change them
* Library of functions: https://docs.python.org/3/library/string.html

```python

s = 'chris'
#nope:
#s[0] = 'C';

# substrings do work:
s = 'C' + s[1:]
print(s)

csv_data = 'Chris,Bourke,105 Schorr,402-472-5008,School of Computing,Lincoln,NE'
data = csv_data.split(',')
pprint.pprint(data)

# regular expression verion:
data = re.split("[0-9]{3}", csv_data)
# look up also: re.find, re.findAll ?
pprint.pprint(data)

# regular expresion for whitespace:
csv_data = 'Chris,Bo  urke,105 Scho\trr,402-47\n2-5008,School of Computing,Lincoln,NE'
data = re.split("\\s+", csv_data)
pprint.pprint(data)

```
# Functions, Modules

* Modules are little more than files
* You can create a "library" or "module" by putting methods or variables or code into a file: `library.py`
* All modules (files) and non-trivial methods will require proper doc-style documentation
* In python, there can only be one function with a particular name

* `utils.py`:

```python
"""
A collection of utilities

Chris Bourke
2026-01-21
"""
import math


def euclidean_distance(x1, y1, x2, y2):
    """
    Computes the Euclidean distance between the two
    points `(x1,y1)` and `(x2,y2)`
    """
    result = math.sqrt( (x1-x2) ** 2 + (y1-y2) ** 2 )
    return result

def foo(x, y = 2, z = 1):
    if type(x) != float and type(x) != int:
        print("ERROR: x must be a number")
        return 0
    return x + y * z

```

* demo:

```python
import pprint
import sys
import re
import utils
from utils import euclidean_distance
from utils import foo

distance = euclidean_distance(0,0,1,1)
print(distance)

result = foo(10, 20, 30)
print(result)

result = foo(10)
print(result)

result = foo(10, z = 30, y = 20)
print(result)

result = foo("ten", 20, 30)
print(result)



```

# Basic File I/O

* You can open to a file using `open()`
* Very similar to `fopen`

```python

#file input
f = open('data.txt', 'r')
lines = f.readlines()
# endline characters are retained!
pprint.pprint(lines)

lines = [line.rstrip() for line in lines if not line.isspace() ]
pprint.pprint(lines)

#be sure to close your resources!
f.close()

# file output
f = open('message.txt', 'w')
f.write('Hello world!\n')
x = 42
f.write(f'x = {x}\n')
f.write(f'pi is {math.pi:.4f}')
f.close()
```

# Classes

* Use `class ClassName`
* Use `UpperCamelCasing`
* Use `lower_underscore_casing` for variables
* No predefined "member variables"

## Book Demo

```python
import pprint
import sys
import re
import utils
import math
from book import Book


f = open('my_goodreads_data.csv', 'r')
lines = f.readlines()

#pprint.pprint(lines)
# step 1 : read all of them in as tuples of strings

books = []
for line in lines[1:]:
    tokens = line.split(',')
    book = Book(*tokens)
    books.append(book)

#pprint.pprint(books)

books = [ Book(*line.rstrip().split(',')) for line in lines[1:] ]

#pprint.pprint(books)

# what is the "best" book?
# what is the "worst" book?
# what is the oldest book?
# what is the newest book?
# what are the books written by Terry Pratchett

# what is the oldest book?

#let's sort instead

# numbers = [4, 6, 8, 3, 2, 1, 5, 3, 4]
# numbers.sort()
# pprint.pprint(numbers)

# numbers.sort(reverse=True)
# pprint.pprint(numbers)

# names = ["Joe","Jane", "Chris", "bob"]
# names.sort()
# # sorting is lexicographic for strings
# pprint.pprint(names)

# years = ["1908", "400", "1916", "2026"]
# years.sort()
# pprint.pprint(years)

# sort books by publish_year
books.sort(key = lambda book : (book.author_last_name, book.author_first_name, -book.rating), reverse=True )
pprint.pprint(books)

# sort by year
books.sort(key = lambda book : book.publish_year)
oldest_book = books[0]
newest_book = books[-1]

pprint.pprint(f'oldest: {oldest_book}')
pprint.pprint(f'newest_book: {newest_book}')

pratchett_books = [book for book in books if (book.author_first_name,book.author_last_name) == ('Terry', 'Pratchett')]
pprint.pprint(pratchett_books)

# is there a binary search? No
# create a map that maps an author (tuple: first/last) to a list of their books

authors = { (b.author_last_name,b.author_first_name) for b in books }

author_to_books = { a:[b for b in books if (b.author_last_name,b.author_first_name) == a ] for a in authors }
pprint.pprint(author_to_books)

```

```text













```
