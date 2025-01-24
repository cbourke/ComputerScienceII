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

```text










```
