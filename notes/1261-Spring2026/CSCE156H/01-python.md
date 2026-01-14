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




```text













```
