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

```text










```
