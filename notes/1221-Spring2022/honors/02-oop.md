# Computer Science II - CSCE 156/156H
## Spring 2022
### Object Oriented Programming

## Intro: Creating and Designing Classes Demo

* See `Isotope.java`

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

* Information or implementation hiding
* YOu don't have to worry about how an object is represented in order to use it!  
* Ex: `LocalDate`: how does it represent a date?
  * 3 integers
  * String: ISO 8601 `2022-02-08T14:17:55`
  * A single number (unix epoch/unix time)
* How is a `String` represented in Java?  How does an `ArrayList` store things? How does it grow/shrink
* Abstraction means not having to think about these "low level" details

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

* Don't break encapsulation by locating data or methods that act on that data outside the class
* Bank stuff belongs in the `Bank` class, not the `main` or `Demo` class
* Student "stuff" belongs in the `Student` class
* Good abstraction and encapsulation work together to make code:
  * Isolated
  * More easily testable
  * Control/limit "side effects"
  * Reusable

## Inheritance

Scenario: a retail store sells products and subscription
services.  Products are items that have a price and a
customer may purchase any number of them (ex: USB memory stick).
Subscriptions have an annual rate and are purchased based
on the start date and end date.

* KISS = Keep It Simple Stupid
* YAGNI = You Aint Gonna Need It
* DRY = Don't Repeat Yourself

* Inheritance allows you to create "super classes" with common, general behavior and/or state
* Then you can *derive* or `extends` to "subclasses" with more specific behavior or specialized behavior
* If a class $A$ `extends` class $B$ then
  * $A$ is the subclass, $B$ is the superclass
  * $A$ is the child class, $B$ is the parent class
  * Inheritance forms an *is-a* relationship:
    * `Product` is-a `Item`
    * `Subscription` is-a `Item`
    * Is an `Item` necessarily a `Product`?
    * Is a `Product` a `Subscription`?
  * Inheritance is achieved at runtime by building "virtual tables": the methods and state for a particular object are created at *instantiation* (`new` keyword) and stored in a v-table
  * When calling an object's methods, *dynamic dispatch* is used to 'lookup' the appropriate method in the vtable and execute its code
* Advantages:
  * We like organization; a hierarchy is a natural organized structure
  * We like code reuse and reduction of redundancy
  * Also: subtype polymorphism (later)

### Misc

* Sometimes you don't *want* to allow someone to subclass your class, you can make it `final` to prevent them
  * Making a method `final` makes it "non-virtual" and you cannot override it in any subclass
  * By default in Java, all methods are virtual, you make them non-virtual with the `final` keyword
  * Contrast: C++: all methods are non-virtual, you must make them virtual using the keyword `virtual`
* You can make a class `abstract` and it may have `abstract` methods
  * An `abstract` class cannot be instantiated
  * `abstract` methods have no body/no definition
  * In any non-abstract subclass you MUST provide a definition/implementation
* An `interface` is a pure-abstract class
  * All of its methods are `abstract`
  * Java is a single-inheritance heirarchy: you can only `extends` one class
  * In general, a hierarchy *locks you in* to that design; it is not easy to change
  * However, you can `implements` multiple interfaces with no problem
  * Often you do this to leave your hierarchy flexible and use "mixins"

### Pitfalls

* Some languages support *multiple inheritance*
  * Ex: `Dog, Cat` are both `Animal`
  * Suppose I create a `CatDog` class that inherits from both `Dog` and `Cat`
  * Problem: ambiguity: `speak()`: does it inherit from `Cat` or `Dog`?
  * Diamond Problem: you don't know where the inheritance comes from without reading the documentation!
  * In Java: no problem, single inheritance only
* Antipattern: Yo-Yo problem
  * In general, keep your inheritance hierarchy shallow and simple!
  * Reason: once created, you are pretty much locked into that heirarchy
* Rectangle Problem:
  * SUppose we ahve a `Shape, Square, Rectange`
  * changes can change an instance's properties, but hte type cannot change!
  * Violation of the Liskov Substitution Principle


## Polymorphism

* Polymorphism = Poly=many + morph=form = Many forms
* Code: a variable, method, or class may be applicable to many different types of classes or have many forms
  * C: how do you sort?  `qsort()`: it operated on generic (void) types
  * Its generic programming: you can create one variable, method or class to have a general generic solution to multiple types
  * Java: there are several different forms of polymorphism

### Subtype Polymorphism

* You can treat a class as its superclass
* Example:

```java
Item i1 = new Product(...);
Item i2 = new Subscription(...);
Collection<Items> receipt = new HashSet<>();
receipt.add(i1);
receipt.add(i2);
```

### Method Overloading

* Ex: how do you compute the absolute value in C?
* `abs(int), fabs(double), labs(), llabs()`
* Same functionality, but different names
* Java: `Math.abs()`: there are 4 diffent methods each taking a different type of parameter, but doing the same thing!
* BUT: in an OOP language, we can use the same function name, this is known as function overloading
* Careful: function overriding is with inheritance (subclasses can *override* inherited virtual functions)
* How does this work?  Compilers are dumb, but they are smart enough to figure out what type of variable you are passing
* This mechanism is called static dispatch

### Operator Overloading

* Operators in a language may have multiple meanings depending on their operands
* Example Java:
  * `String + String`: concatenation
  * `int + int`: addition
  * `String + int`: convert the number to a string and concatenate
* PHP:
  * `int + int`: addition
  * `String + int`: type juggle the string to a number (if possible) and addition
  * `String + String`: type juggle x2 and add
  * string concatenation: `string . string`
* Python
  * `int + int`: addition
  * `string + string`: concatenation
  * `string + int`: error
* other contexts:
  * `List + List`: append, union, vector addition
  * `Set + Set`: ??
  * `Duration + Duration`
  * `Time + Time`: ill defined
* Some languages (PHP, Java, Python) have pre-defined meanings for operator overloading
* Some languages (C++) allow you to arbitrarily overload operators!
  * You *could* define anything you want
  * You *could* redefine + to be subtraction
  * Not a good idea to use this in practice
  * In any case, even if you do, you're ultimately writing a function to do so

### Parameterized Polymorphism

* In Java, you can create variables:

`int x = 43;`
* When declared, a variable has a set *type*
* You can parameterize a variable, method, or class so that the type is NOT set

`T x;`

* `T` refers to a generic variable type that is not known as compile time (necessarily)

* PECS: Producer Extends, Consumer Super
  * A collection is a *producer* of elements if you take stuff out and do something with it.  You need a minimal amount of information: `<T extends Item>`
  * A collection is a *consumer* of elements if you wish to put stuff in: `<? super Item>`: you don't care about what is in the collection already, you just want to put some new stuff in it
* In general, if you care about the type, you need to use a named parameter `<T>`
* If you don't care, you *might* be able to use the wildcard: `<?>`

```text












```
