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
  * `lower_underscore_casing` for variables and function names
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
```

# Collections

* Python has:
  * Lists - ordered collections of things, 0-indexed
  * Sets - unordered collections of *unique* elements
  * Dictionaries - maps mapping integers and/or strings to other elements


```python

# Lists:

numbers = [5, 7, 3, 4, 0, 1, 2]
for x in numbers:
    print(x)

numbers.append(42)
pprint.pprint(numbers)

numbers.insert(0, 101)
pprint.pprint(numbers)

# negative indices wrap around the list...
numbers.insert(-1, 120)
pprint.pprint(numbers)

# remove something: you POP it
x = numbers.pop()
print(x)
print(numbers)

# remove from an arbitrary index:
x = numbers.pop(3)
print(numbers)

# retrieval is index-based:
x = numbers[4]
print(x)
```

Sets:

```python

# Sets:
numbers = {2, 6, 9, 4, 3}
pprint.pprint(numbers)

# invalid because sets are unordered:
# x = numbers[0]

numbers.add(42)
pprint.pprint(numbers)
# duplicate adds have no effect
numbers.add(42)
pprint.pprint(numbers)

numbers.remove(2)
pprint.pprint(numbers)

# convert a list to a set:
foo = [6, 4, 8, 9, 3, 3]
bar = set(foo)
pprint.pprint(bar)

# convert a set to a list:
foo = {2, 3, 5, 7}
bar = list(foo)
pprint.pprint(bar)

a = [1, 2, 3, 4]
# shallow copy:
b = a
a.append(42)
pprint.pprint(a)
pprint.pprint(b)

# deep copy:
c = list(a)
c.append(101)
pprint.pprint(a)
pprint.pprint(c)

# or:
d = copy.deepcopy(c)
```


* Dictionary demo:

```python

# dictionaries are *maps* that may map an integer or
# a string as a KEY to anything that is a VALUE

foo = {
    0: "hello",
    10: "world",
    5: 42,
    101: 3.5,
    "Hello": "World"
}

pprint.pprint(foo)

# iterating over key-value pairs in a dictionary:
for k,v in foo.items():
    print(f"{k} maps to {v}")

# iterating over just the keys:
for key in foo:
    value = foo[key]
    print(f"key = {key} and maps to {foo[key]}")

# iterating over just the values:
for val in foo.values():
    print(f"value = {val}")
```

* Comprehensions and Tuples:

```python

import pprint
import copy

# Comprehensions:
# a "comprehensio" is a way to build a new collection
numbers = [4, 6, 3, 5, 0, 1, 3, 5, 9, -42]

numbersPlusOne = [ x+1 for x in numbers ]
pprint.pprint(numbersPlusOne)

evens = [ x for x in numbers if x % 2 == 0 ]
pprint.pprint(evens)

positives = [ x for x in numbers if x > 0 ]
pprint.pprint(positives)

foo = {
    "one": 1,
    "two": 2,
    "three": 3
}

bar = { v:k for k,v in foo.items() }
pprint.pprint(bar)

bar = { k:v for k,v in foo.items() if v % 2 == 0}
pprint.pprint(bar)

# this gives a function reference, not a list of 0 thru 9:
indices = range(10)
pprint.pprint(indices)

indices = [ x for x in range(10) ]
pprint.pprint(indices)

# really cool: python supports *tuples*
pair = (10, 20)
triple = (5, "hello", 3.5)

square = [ (x, y) for x in range(5) for y in range(5)]
pprint.pprint(square)

for (x, y) in square:
    print(f"{x} and {y}")
```

# Strings

```python

import pprint
import copy

# more on strings:
# strings are immutable, once created they cannot be changed

a = "hello"
b = a.replace('h', 'H')
print(a)
print(b)

message = "Hello Nebraska!"
foo = message[6:]
print(foo)
foo = message[6:9]
print(foo)
foo = message[:6]
print(foo)

foo = message.replace('e', '')
print(foo)

# split CSV data:
csvData = "Hello,World,How,Are,You?"
foo = csvData.split(",")
pprint.pprint(foo)

csvData = "Hello,World,How,Are,You?"
foo = csvData.split("o")
pprint.pprint(foo)

```

# Functions

```python

# None is basically a null or NULL value
# NoneType is the none type
def foo(a = None, b = 42, c = 30):
   print("Hello")
   if a is None:
     x = b + c
   else:
     x = a + b + c
   print(f"x = {x}")
   a = 42


a = 10
b = 20
c = 30

foo()

print(f"{a}, {b}, {c}")

x = None
y = "Hello"
z = str(x) + y
print(z)
```

* Modularization:

demo.py:
```python

#import
#from my_library import foo
import my_library
#from my_library import *
import math
#from math import sqrt

a = 10
b = 20
c = 30
z = my_library.foo(a, b, c)
print(z)

y = math.sqrt(2)
print(y)
```

my_library.py:

```python

# None is basically a null or NULL value
# NoneType is the none value
def foo(a = None, b = 42, c = 30):
    print("Hello")
    if a is None:
      x = b + c
    else:
      x = a + b + c
    print(f"x = {x}")
    a = 42
    return x
```

# File I/O

* File output: use `open()` to open a file

```python

f = open("foo.txt", "w")

#write will not insert an endline by default
f.write("Hello HOw are you?\n")
f.write("I am fine\n")
a = 10
f.write( str(a) + "\n" )

f.write(f"a = {a}, neato!\n")

f.close()
```

* File Input:

```python

f = open("bar.txt", "r")
lines = f.readlines()
f.close()

print(lines)

# strip out whitespace using: trim?
stripped = []
for line in lines:
    x = line.strip()
    stripped.append(x)

stripped = [ line.strip() for line in lines ]

print(stripped)


```

# Error Handling

* Python does have an exception system
* You use the keywords `try` and `except`

```python
import traceback

a = 10
b = 20

try:
    c = a / b
    f = open("/etc/shadow", "r")
except ZeroDivisionError:
    traceback.print_exc()
    print("Division by zero, using default of 0...")
    c = 0
except:
    print("Something, I don't know what, happened")
finally:
    printf("Do anything that needs to be done unconditionally")
    #example: f.close()

print(c)
```

# Classes

* Python supports classes, though is not "really" an OOP language

```
class Student:

    # constructor:
    def __init__(self, nuid, firstName = "", lastName = None):
        self.nuid = nuid
        self.firstName = firstName
        self.lastName = lastName

    def __str__(self):
        return f"{self.lastName}, {self.firstName} ({self.nuid:08d})"


# call the constructor
me = Student(230602, "Chris", "Bourke")
print(me)

print(me.nuid)

me.nuid = 4
print(me)
```

Demo:

```python

from functools import total_ordering
import pprint

# Python supports classes, though is not "really" an OOP language
@total_ordering
class Student:

    def __eq__(self, other):
        return ((self.lastName, self.firstName, self.nuid) ==
                (other.lastName, other.firstName, other.nuid))
    def __lt__(self, other):
        return ((self.lastName, self.firstName, self.nuid) <
                (other.lastName, other.firstName, other.nuid))

    # constructor:
    def __init__(self, nuid, age, firstName = "", lastName = None):
        self.nuid = nuid
        self.age = age
        self.firstName = firstName
        self.lastName = lastName

    def __str__(self):
        return f"{self.lastName}, {self.firstName} ({self.nuid:08d})"


# call the constructor
a = Student(230602, 21, "Chris", "Bourke")
b = Student(1231, 16, "Alice", "Xeno")
c = Student(4213, 54, "Bob", "Bourke")

# If you do not want a full class, a tuple may be a good solution:

a = (230602, 21, "Chris", "Bourke")

#detour 1: you can return tuples from functions to return multiple values
def foo(x):
    return (x+1, x-1)

# assign values using tuples
(a, b) = foo(10)
print(a)
print(b)

x = 10
y = 20
#swapping in a one liner!
x, y = y, x

# How to sort normally...

numbers = [5, 8, 4, 6, 2, 0, 1]
print(numbers)
numbers.sort()
print(numbers)
numbers.sort(reverse=True)
print(numbers)

# strings?
names = ["Chris", "Bob", "alice", "Zoey"]
names.sort()
print(names)

# sorted copies: instead of using .sort(), use .sorted()
names = ["Chris", "Bob", "Alice", "Zoey"]
sortedCopy = sorted(names)
print(names)
print(sortedCopy)

a = Student(230602, 21, "Chris", "Bourke")
b = Student(1231, 16, "Alice", "Xeno")
c = Student(4213, 54, "Bob", "Bourke")
d = Student(1, 54, "Bob", "Bourke")

roster = [a, b, c, d]

# rosterAsStrings = [ str(s) for s in roster ]
# print(rosterAsStrings)

# roster.sort(key = lambda x : x.lastName, reverse=False )
roster.sort()
rosterAsStrings = [ str(s) for s in roster ]
print(rosterAsStrings)

```


```text










```
