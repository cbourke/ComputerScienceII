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

```java
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

```text













```
