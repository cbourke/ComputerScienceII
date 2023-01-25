# Computer Science II - CSCE 156H

## Spring 2023

### Introduction to Python

# Basics

* Guido van Rossum (1989/91)
* Interpreted language (scripting language)
* Dynamically typed
* Supports integers, floats (doubles), strings
* Icky: whitespace matters!
* Style: do things the "Pythonic" way: https://peps.python.org/pep-0008/
  * `loewr_underscore_casing` for variables and function names
  * four spaces for indentation

# Variables

```python


x = 42

y = "Hello"

z = 3.14159

print("Hello World\n\n")

print(x)
print(y)
print(z)

message = f"x = {x:010d}, y = {y}, z = {z:20.10f}"

print(message)

message = message + "end of class!"

print(message)

a = 10
print(a)

a = "ten"
print(a)

a = 2147483647
print(a)

a += 1
print(a)

a = 238740938740293187409231874930821749382749018237490823749081273490812374908273409872309148732109847302918743921874239807
print(a)

b = 3.141592490837240981273409827130498721309487213098471209387409218374092387409821374091283740981237409821374092387
print(b)

a = 10
b = 20

# python automatically converts to floats:
# no integer trunction
c = a / b

print(c)

# if you *want* integer truncation:
c = a // b

print(c)

# More mixing types:

a = 10
b = 20
c = a + b #regular addition

a = "Hello "
b = "World"
c = a + b # regular concatenation
print(c)

a = 10
b = "Hello"

# invalid: you cannot mix types with +
#c = a + b
#print(c)

# forces a into a string for concatenation:
c = str(a) + b
print(c)

# convert to an int
b = "20"
c = a + int(b)
print(c)

# convert to a float:
b = "20.5"
c = a + float(b)
print(c)

# whoop neither works:
# b = "hello"
# b = "10hello"
# c = a + int(b)
# print(c)




```

* Integers in python support arbitrarily large values
* Floating point numbers are not arbitrary (usually) have 16 digits of accuracy
* Careful: indentation matters and the standard is to use 4 spaces for each level of indentation
* Style points:
  * Variable names should use a `lower_underscore_casing` convention (same with function names)
* Observations:
  * No semicolons at the end of executable lines
  * No curly brackets for code blocks, etc.

## CLAs = Command Line Arguments

* You can provide command line arguments to a python script using the `sys` library
* To bring in any python library use `import`
* The first argument, `sys.argv[0]` is always the executable script file name
* After that, all other values are user-provided CLAs: `sys.argv[1]`

```python

import sys

# sys.argv is a list of command line arguments
print(sys.argv)

# len tells you how many things are in a list
n = len(sys.argv)
print(n)

for i in range(n):
    print(sys.argv[i])

x = int(sys.argv[1])
y = float(sys.argv[2])

if len(sys.argv) != 8:
    print("not eight CLAs")
elif len(sys.argv) < 8:
    print("less than 8")
else:
    print("there are 8!")

# for(int i=0; i<n; i++) {
#     printf("argv[%d] = %s\n", sys.argv[i])
# }
```

# Conditionals

* You have traditional `if` and `else` keywords
* You do not use curly brackets, instead you use `:` at the end of the conditional statement

```python
if <statement>:
  <executable block>
else:
  <alternative block>

```

* There is no `else if`, instad you use `elif`

```python


huskerScore = 80
purdue = 80

if huskerScore > purdue:
    print("Huskers WIn!")
    print("Yay!")
elif purdue > huskerScore:
    print("Boilermakers Win!")
    print("Boo!")
else:
    print("tie, go to OT")
```

* Boolean operators:
  * You can use the keywords `or` and `and` as well as a combination of parentheses to create more complex expressions

```python


a = 10
b = 20
c = 30

radical = b ** 2 - 4 * a * c

if radical < 0 or a == 0:
    print("not going to work to compute roots...")

if a > b and b > 0:
    print("Something")

# python also has boolean types:
isStudent = True
isStudent = False

a = 10
b = 20
isStudent = a > b

# you can, but shouldn't use integers: 0 = false, non-zero = true
# isStudent = 0

if isStudent:
    print("Discount...")
else:
    print("Full price")
```

## Loops

```python


# you have basic for loops in Python, generally you use the range() function:
for i in range(10):
    print(i)

# by default, range(n) starts at 0, increments by 1 and ends at n-1

print("==========")

# this starts at 5 and goes to 9
for i in range(5, 10):
    print(i)

print("==========")

# starts at 5 goes to 9 by values of 2
for i in range(5, 10, 2):
    print(i)

print("==========")

# you also have while loops:
i = 5.5
while i < 10.5:
    print(i)
    i += 1.5

# however, you'll usually be incrementing over a *collection*
# In python you have:
#  Lists
#  Sets
#  Dictionaries

numbers = [5, 7, 3, 4, 0, 1, 2]
for x in numbers:
    print(x)
```

```text










```
