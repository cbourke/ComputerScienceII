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

# Loops

* Python has `for` loops and `while` loops
* `for` loops are actually always "for each" loops...
* NOte:
  * Python does not support `i++`, but does support `i += 1`


```python

n = 10
# i = 0
# while i < n:
#     print(i)
#     i += 1

for i in range(n):
    print(i)
print("==========")
for i in range(2, n):
    print(i)

print("==========")
for i in range(2, n, 2):
    print(i)

print("==========")
for i in range(10, -1, -1):
    print(i)
print("Blast off")
```

# Collections

* Python has:
  * Lists - ordered collections of things, 0-indexed
  * Sets - unordered collections of *unique* elements
  * Dictionaries - maps mapping integers and/or strings to other elements

```python
import pprint

# a basic list
# use the square brackets and a comma delimited list to initialize
primes = [2, 3, 5, 7, 11, 13, 17]

# and empty list
my_stuff = []

# append adds stuff to the end
my_stuff.append(10)
my_stuff.append(15)
my_stuff.append(5)

pprint.pprint(my_stuff)

my_stuff.append(42)
pprint.pprint(my_stuff)

# removes and returns the last element
x = my_stuff.pop()
pprint.pprint(my_stuff)
print(x)

# arbirarily insert using an index
# you can use negative index values
my_stuff.insert(-3, 42)
pprint.pprint(my_stuff)

first = my_stuff[0]
print(first)
last = my_stuff[3]
print(last)

# you can use negative indexes:
huh = my_stuff[-1]
print(huh)

# Other tricks
# you can get a sublist:
# [x:y] will give values from index x to index y-1
sub_primes = primes[3:5]
pprint.pprint(sub_primes)

#both parameters are optional
# gives 3 thru the end of the list
sub_primes = primes[3:]
pprint.pprint(sub_primes)

# gives 0 thrue 3-1
sub_primes = primes[:3]
pprint.pprint(sub_primes)

# Sets are unordred:
# empty list:
stuff = set()
pprint.pprint(stuff)
stuff.add(10)
stuff.add(20)
stuff.add(30)
pprint.pprint(stuff)
stuff.add(30)
pprint.pprint(stuff)

foo = [2, 3, 3, 2, 2, 3, 4, 3, 2, 3]


bar = set(foo)
pprint.pprint(bar)
baz = list(bar)
pprint.pprint(baz)

# pitfalls
# python allows you to mix types, but you shouldn't:

foo = [2, 3, 4, "hello", "foo", 3.14159]
pprint.pprint(foo)

# dictionaries are mappings from integers or strings to any element
# you can map keys (integers or strings) to values (which can be ANYTHING)

foo = {
    #key: value,
    0: "hello",
    10: "goodbye",
    15: 3.14159,
    5: 3,
    "foo": "bar"
}

pprint.pprint(foo)
for key in foo.keys():
    print(f"{key} maps to {foo[key]}")

for value in foo.values():
    print(f"{value}")

for key,value in foo.items():
    print(f"{key} maps to {value}")

#add elements to a dictionary
foo["Chris"] = "Awesome!"
foo[42] = "Answer"
pprint.pprint(foo)

#change elements to a dictionary
foo["Chris"] = "Boring!"
pprint.pprint(foo)

foo.pop("Chris")
pprint.pprint(foo)

```

$$\{x | x \in \mathbb{N}\}$$

### Comprehensions

```python
import pprint

primes = [2, 3, 5, 7, 11, 13, 17]

# create a list with the values from primes but with 1 added to each...
primes_plus_one = []
for p in primes:
    primes_plus_one.append(p+1)

pprint.pprint(primes_plus_one)

# list comprehension
primes_plus_one = [ x + 1 for x in primes ]
pprint.pprint(primes_plus_one)

foo = [ x * 2 for x in primes_plus_one]
pprint.pprint(foo)

# set comprehensions
bar = { x + 10 for x in primes_plus_one }
pprint.pprint(bar)

# dictionary comprehensions
foo = {
    1: "one",
    2: "two",
    3: "three",
    4: "four",
}
pprint.pprint(foo)
bar = { v:k for k,v in foo.items() }
pprint.pprint(bar)
bar = { v:k+10 for k,v in foo.items() }
pprint.pprint(bar)

bar = [ (k+0.5,v) for k,v in foo.items() ]
pprint.pprint(bar)

# python also supports "tuples": pairs, triples, quadruples, quintuples, etc.
# foo = (10, 20, 30)
# pprint.pprint(foo)
# # tuples can be treated like lists but they are not lists, they are pairs, etc.
# x = foo[0]
# pprint.pprint(x)

baz = [ (b,a,2) for (a,b) in bar if a < 3.0]
pprint.pprint(baz)

```

```python

numbers = [2, 8, 5, 3, 5, 42, 101, 13, 17]

# create a deep copy of an array
copy = [ x for x in numbers ]

evens = [ x for x in numbers if x % 2 == 0 ]
print(evens)

# get all evens that are also greater than 20:
foo = [ x for x in numbers if x % 2 == 0 and x > 20]

foo = [ str(x) for x in numbers  ]

foo = [ int(x) for x in foo ]


```

```python
import pprint

zips = [
  "05026",
  "56605",
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

# zip codes that begin with 6:
foo = [ x for x in zips if x.startswith("68") ]

# get all zips that are "even"
foo = [ x for x in zips if int(x) % 2 == 0 ]

foo = [ f"{x:05}" for x in [ int(y) for y in zips ] if x % 2 == 0 ]

# count up the number of times each zip code occurs in the data

foo = set(zips)

counts = { f"{k:05}":zips.count(f"{k:05}") for k in range(100000) }

# most common zip code:
maxVal = max(counts.values())

commonZips = [k for k,v in counts.items() if v == maxVal ]
print(commonZips)
```

```python
import pprint

data = [
  ("Lego Star Wars","LucasArts","2005","Xbox"),
  ("Lego Star Wars","LucasArts","2005","PlayStation 2"),
  ("Lego Star Wars","LucasArts","2005","PC"),
  ("Lego Star Wars","LucasArts","2005","Mac"),
  ("Tie Fighter","LucasArts","1994","PC"),
  ("X-Wing vs Tie Fighter","LucasArts","1997","PC"),
  (None,"Sony Computer Entertainment",None,None),
  (None,"Square Enix",None,None),
  (None,"Sega",None,None),
  ("Legend of Zelda","Nintendo","1987","NES"),
  ("Super Mario Brothers","Nintendo","1985","NES"),
  ("Zelda II: The Adventure of Link","Nintendo","1988","NES"),
  ("Tetris","Nintendo","1989","Game Boy"),
  ("Super Mario Land","Nintendo","1989","Game Boy"),
  ("GTA IV","Rockstar Games","2008","Xbox 360"),
  ("GTA IV","Rockstar Games","2008","PC"),
  ("GTA 3","Rockstar Games",None,None),
  ("GTA 2","Rockstar Games",None,None),
  ("GTA","Rockstar Games",None,None),
  (None,"Blizzard Entertainment",None,None),
  ("Mega Man 3","Capcom","1990","NES"),
  ("Mega Man 4","Capcom",None,None),
  ("Contra","Capcom",None,None),
  (None,"Atari",None,None),
  ("Portal","Valve","2007","PlayStation 3"),
  ("Portal","Valve","2007","PC"),
  ("Portal","Valve","2007","Xbox 360"),
  ("Portal 2","Valve","2011","Xbox 360"),
  ("Portal 2","Valve","2011","PlayStation 3"),
  ("Portal 2","Valve","2011","PC"),
  ("Portal 2","Valve","2011","Mac"),
  ("Katamari Damacy","Namco","2004","PlayStation 2"),
  ("Out of this World","Delphine Software","1992","PC"),
  ("Out of this World","Delphine Software","1992","Super NES"),
  ("Assassin's Creed","Ubisoft","2007","Xbox 360"),
  (None,"Ubisoft",None,None),
]

publishers = set([ publisher for (title,publisher,year,platform) in data ])

# we want a mapping:
# publishers to a set of the games they have published
# publishers to a int of the number of games they have published
publisherToGames = { publisher:set([title for (title,p,year,platform) in data if p == publisher and title is not None ]) for (title,publisher,year,platform) in data }
publisherCounts = { publisher:len(games) for publisher,games in publisherToGames.items() }

pprint.pprint(publisherCounts)
```

```text













```
