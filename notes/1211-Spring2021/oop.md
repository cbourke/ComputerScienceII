# Object Oriented Programming

## Objects

* Formally, an object is a general entity characterized by: 
1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides

* Communication between objects is done via *message sending*: you simply invoke a method on an object and the object determines which code to run
* Communication is facilitated using the object's *interface*: a Java class's "interface" is its publicly available methods
* Objects are *constructed* instead of assigned
  * In Java, by default a no-arg constructor is provided (it assigns default `null` or zero values to an object's state)
  * In Java if you define a constructor (you can define as many as you want), the default goes away
  * Copy constructors, partial constructors, etc.
* Objects are designed: 
  * A general approach is that "semantics dictate design"
  * You break a real-world entity into smaller parts until...
    * It is simple enough to just represent it directly (String, number, etc.) or:
    * An object already exists for it: just (re)use it
* Generally prefer *composition* when relating objects to each other (alternatively: inheritance, more on that later)
* Avoid the god-antipattern: a class should only know about its own stuff, it should not *know everything, do everything, be everything* 

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* implementation hiding
* you need not worry about the details of how an object or class is implemented or how it works (behavior)
* You simply use it using its publicly available interface
* How is time represented?  How is a duration represented?  How is a `String` represented

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

## Inheritance

* Inheritance is a mechanism by which objects can be *extended* to:
  * Inherit both the state and behavior from its "parent class" or "superclass" and/or
  * You can *Override* or augment state and behavior (add or modify) to provide more specific functionality
* Provides a way to *reuse* code
* Provides a way to *organize* code

In Java:
* In java you use the `extends` keyword
* If $A$ `extends` $B$ then $A$ is a subclass, $B$ is the superclass
* In Java, all non-private methods are inherited and all non-private state can be seen by the subclass
* A subclass *is-a* (an) instance of its superclass:
  * Robin is a Bird
  * Bird is not necessarily an Ostrich

## Polymorphism

* Polymorpism = poly = many, morph = forms
* Code: a variable, method or class may be applicable to many different types of variables or classes
  * C: how do you sort? You use ONE function to sort *any* type of variable(s): `qsort`
  * You have "generic" coding: code that can be applied to multiple pieces of data
* Subtype Polymorphism:

```java
Robin r = new Robin();
Ostrich o = new Ostrich();
Bird b = r; //b is referencing a Robin
b = o; //b is referencing an Ostrich

//contravariance:
r = b; //at this point, b is referencing an Ostrich, this is dangerous
 //a Ostrich cannot become a Robin
o = r;
```

* In general you *should* use subtype polymorphism: treat variable as its most general type.  
* Only treat a variable as its subtype if you absolutely need to;
* Ex: when you create copies of objects, they need to be the same type!

```java
//treat the array list as just a List:
List<String> names = new ArrayList<>();
//treat the array list as a collection
Collection<String> names = new ArrayList<>();
```

### Function overloading
  * In C: what function or functions do you use to compute the absolute value? `abs(), fabs(), labs, llabs()...`
  * In programming languages *without* function overloading, any function must have a unique name.  you cannot have multiple functions with the same name
  * A programming language that supports function overloading allows you to create multiple functions with the same name as LONG AS you have a different number of different type of parameters
```java

public static void foo(int) { ... }

public static void foo(double) { ... }

public static void foo(String) { ... }
...

Utils.foo("hello");
```
  * Static dispatch: the compiler can figure out which function you meant to call based on the types of variables you passed to it

### Operator Overloading

* In Java: 
  * `String + String` (concatenation)
  * `int + int` (addition)
  * `String + int` (concatenation)
  * `Object + String` (concatenation: all objects have a `toString()`)
* C++:
  * `int + int` (addition)
  * `Set + Set` (add the first to the second, union, disjoint union, multiset?)
  * `List + List` (union, appending, component-wise addition ie vector addition)
  * `Time + Time` (?!?!)
* Python:
    * `String + String` means concatenation
    * `number + number` means addition
    * `number + String` is a runtime error
* PHP:
  * `number + number` means addition
  * `number + String` try to parse the string as a number, if you fail, treat it as zero
  * `string + string`: try to parse BOTH as numbers
  * String concatenation: `$s . $t`

* Some languages allow you to provide custom operator overloading
  * in general this is a Bad Idea: the meaning is left up to interpretation
  * In any case, to enable operator overloading, you have to define how it works; ie you have to write a function!

## Parameterized Polymorphism

* Java allows you to *parameterize* a class, method or variable to be applicable to any type, `T`

```java
//a is a variable whose VALUE can vary
int a;
a = 42;
a = 101;
//BUT, a's type is always fixed, it is an int

//x is a variable whose *value* can vary
//The "type" can ALSO vary
T x;
```

* Effectively makes the variable's *type* also variable
* You essentially want to use parameterization to make as generic of code as possible so that you can apply it to as many different TYPES as you can

```text









```