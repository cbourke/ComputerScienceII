# Computer Science II - CSCE 156H
## Spring 2024
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

## Hello World

```python
"""
Author: Chris Bourke
Date: 2024-01-24

Prints hello world to the standard output
"""

def say_hello():
    """
    This function prints hello world to the standard output
    """
    print("Hello World")

if __name__ == "__main__":
    say_hello()


```

## Basic Operators

```python
import math

a = 10
b = 3.141543247329

#all of the usual operators are supported:
c = a + b
c = a / b
c = a - b
c = a * b


a = 10
b = 20
# no truncation, automatically converts to floating point numbers:
c = a / b

# force truncation:
c = a // b

# remainder
c = a % 3
print(c)

#mathy stuff:
c = math.sqrt(a)
print(c)
c = math.sin(3.14159)
print(c)

c = math.sin(math.pi)
print(c)

x = 0
#illegal, exception, death:
#c = 1 / x

#illegal, exception, death
c = math.log(0.0)

print(c)

print("done")
```

### Concatenation, Problems, Solutions

```python

a = "hello"
b = "world"
msg = a + " " + b
print(msg)

a = 10
b = 20
c = a + b # addition

# you cannot mix types when concatenating
a = "hello"
b = 20
#c = a + b

# to "fix" this you need to explilcitly convert at least one:
c = a + str(b)

a = "10"
b = 20
c = int(a) + b

a = "10.5"
b = 20
c = float(a) + b
print(c)
```

### Command line arguments, Conversions

```python
import sys

# number of command line arguments:
numberOfArgs = len(sys.argv)

print(numberOfArgs)
print(sys.argv[0])
print(sys.argv[1])
print(sys.argv[2])
print(sys.argv[3])

x = int(sys.argv[1])
y = float(sys.argv[2])

print(f"{numberOfArgs}, x = {x}, y = {y:10.4f}")
```

# Conditionals

* Python supports the `if` and `else` keywords
* At the end of the condition, you use `:` and indentation
* There is no `else if`, instead you use `elif`
* Comparison operators:
  * `<, <=, >, >=, !=, ==`
  * YOU *can* but generally should not, use `==` and `!=` on strings, it *will* compare the contents based on lexicographic ordering


```python
import math
import sys

if len(sys.argv) != 4:
    print("ERROR: you must provide 3 coefficients")
    exit(1)

a = float(sys.argv[1])
b = float(sys.argv[2])
c = float(sys.argv[3])

# compute the roots of a quadratic equation with
# coefficients a, b, c:

if b*b - 4 * a * c < 0:
    print("ERROR: complex numbers unsupported")
elif a == 0:
    print("ERROR: you have a linear function, not a quadratic, donk")
else:
    root1 = (-b + math.sqrt(b * b - 4*a*c)) / (2 * a)
    root2 = (-b - math.sqrt(b * b - 4*a*c)) / (2 * a)

    print(f"the roots of {a}x^2 + {b}x + {c} are {root1} and {root2}")
```

```python

a = "apple"
b = "Apples"

if a == b:
    print(f"{a} is equal to {b}")
elif a < b:
    print(f"{a} comes before {b}")
else:
    print(f"{a} comes after {b}")

```

* What about logical operators:
  * <strike>You do have unary operator `!` (not, negation) </strike>
  * You can use the `not` keyword to negate statements
  * No `||` operator, instead you use `or`
  * No `&&` operator, instead use `and`
  * You also have keywords `True` and `False` (*case sensitive*)
  * You can assign truth values to variables (boolean variables)

```text













```
