# Computer Science II - CSCE 156/156H
## Spring 2020
### Introduction to OOP (in Java)

# Motivating Tutorial

## Objects/Classes

* Java is an *object-oriented* programming language (OOP)
* Encapsulation:
  1. Grouping of data
  2. Protection of data
  3. Grouping of functionality (methods) that act on that data

* Demonstration: let's design and implement an `Airport` class

## Observations

* Java is a *class-based* object oriented programming language
* Everything in Java:
  * is a class (which you can create or *instantiate* instances of)
  * or belongs to a class (and thus, need to use the dot operator to access)
* In Java, instances of classes (objects) can be created by using a constructor
  * By default, a no-argument "default" constructor is provided by the language
  * If you define your own constructor, the default constructor goes away
  * You can define any number of constructors
  * Use your IDE to its fullest: Eclipse use `Source` -> `Generate Constructor Using Fields`
* In Java, Encapsulation is achieved with the *visibility* keywords:
  * `private` - only the class and instances of the class can "see" the variable (and thus change it)
  * `protected` - only the class and its subclasses can "see" the variable
  * `public` - *any* piece of code can "see" the variable
* Generally, you should make all variables `private` unless there is a Very Good Reason (VGR) to do otherwise
* Use *composition* to its fullest: classes can have *instances* of other classes as *member variables* 
* Composition defines a *has-a* relationship: an `Airport` has a(n) `Address`
* YAGNI: take care not to *overengineer* your objects (example: a name object to hold half dozen strings, last, first, middle, other middle, title, suffix, etc.)  
* You Ain't Gonna Need It

* Up to now: our objects (classes) are mere *data containers* or data "holders"

## Overview

* History of OOP
* Facilitates the modeling of real-world entities
* Lends itself to modularity and decomposition
* Clear demarcation of responsibilities 
* ADT = Abstract Data Types: objects are a particular realization of ADTs
* Many OOP languages and many languages adopt OOP aspects: C++, Objective-C, Java and C#), PHP JavaScript, Python have adopted more OOP-style (among other styles)

## Objects

* Formally an object is a general entity characterized by:
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

* How does `Math.sqrt()` work?
* Who cares?  Procedural abstraction: complex computation is encapsulated inside a function
* You *use* the function, you don't need to worry about its details
* How does `LocalDate` represent a date?
  * 3 integers (year, day, month)
  * ISO 8601 formatted string `"2020-02-06T12:04:15:0.000CDT"`
  * a single number: unix epoch
  * Who cares?
* Abstraction: you don't need to worry about how an object is *represented* internally,
only how to use it
* Example: how does Java's `String` class work?

## Inheritance

* Inheritance is a mechanism by which objects can be *extended* to:
  * Inherit state and behavior from its *superclass* and/or
  * *Override* or augment state and behavior to provide more *specific* functionality
* Provides a way to *reuse* code
* Provides a way to *organize* code

* In Java, you can create a subclass by using the `extends` keyword
* If class `A extends B` then A is a subclass (child class), B is a super class (parent class)
* The `protected` keyword can be used to make variables and methods visible to subclasses (`private` fields are not visible to the subclass)
* All methods and state is inherited by the subclass
* Inheritance defines an *is-a* relation: a subclass IS AN instance of its super class (covariance), BUT, a superclass is *not necessarily* an instance of its subclass (contravariance), two "sibling" or "cousin" classes are NEVER an instance of each other (invariance)

* Sometimes you don't want to allow (by design) people to create a particular "generic" class 
* To disallow a user to create a "generic" class, make it `abstract`

* With inheritance, a class may be referred to as its superclass; ex: 
* To determine which method actually is called, Java uses "dynamic dispatch": a message is sent to the instance (object) and the object determines which method actually is called/invoked
* Dynamic Dispatch is achieved through a *virtual table*: a table of references to methods created at instantiation 

```java
Bird b1 = new Robin();
b1.move(); //prints "flying"
Bird b2 = new Ostrich();
b2.move(); //prints "runs"
```

* Sometimes, you may NOT want a class to be subclasses: you DON'T want anyone to create or extend the behavior of a class
* Why?  You may not want them to change established behavior
* In java, you can prevent someone from extending your class by making it `final`
  * Other uses of `final` in Java: makes a variable into a constant (you cannot reassign the variable)
  * Using the `final` keyword on a method prevents subclasses from *overriding* the method
* Example: Java does not want you to extend the `Integer` class; it has well-defined and expected behavior.  Changing this well-understood behavior would be counter to expectations.

## More on Abstraction & Interfaces

* An `abstract` class is a class that:
  * Cannot be instantiated (you cannot create instances of the class)
  * You generally use an abstract class when it doesn't make sense to have instances of it (it may be too generic to create)
  * Within an `abstract` class you can have `abstract` methods: methods with no body/no implementation because you may not know how they *should* be implemented
* An *interface* is a *pure* abstract class: ALL of its methods are abstract and have no implementation
* Abstract classes lock you into a hierarchy of inheritance
* Interfaces can be implemented by any class (subclasses, subsets of subclasses, etc.) it doesn't lock you into a hierarchy
* Interfaces allow you to define (somewhat) multiple inheritance: you can implement as many interfaces as you want
* Example: Lab
* Example: Collections Library

### Pitfalls

* Some languages support *multiple inheritance*
  * Animal, Cat, Dog
  * Maybe (don't know why) you want to create a `CatDog`
  * Results in the "diamond problem" if you inherit from multiple classes, which method(s) actually get inherited
  * Ambiguity: you have to *explicitly* code which class you end up inheriting from (C++)
  * In Java: multiple inheritance is not allowed: you can only ever `extends` one class
  * But: Java *does* allow you to implement multiple interfaces
  * Java 8: interfaces can now have a `default` behavior
* Problem: Yo-yo antipattern
  * Deep inheritance hierarchies can get very complicated
  * Deep hierarchies cause you to look up and down to make sense of the design
  * Instead: keep your hierarchies shallow
  * Instead: use composition instead of inheritance
* Rectangle Problem:
  * Suppose we create classes for `Shape, Square, Rectangle`
  * Usually results from a flawed hierarchy design
  * Semantically, a Square may be changed so that it is no longer a square
  * Instead: do not lock yourself into a rigid hierarchy *if* you objects have the potential to change
  * Make sure your hierarchies are well-thought out and thoroughly tested

## Polymorphism

* Polymorphism = Poly=many + morph=form = Many forms
* Code: a variable, method, or class may be applicable to many different types of variables or classes (have many forms)
  * C: how do you sort? `qsort`: it takes a generic array and a comparator: it is GENERIC, it can be applied to an array of any type
* Subtype polymorphism
  * A class can be treated as any of its superclasses
  * Example: you want an array (list) to hold multiple types of accounts
  * You can refer to each subtype as its super type (`BankAccount`) so you only need to worry about having one list
  * You can iterate over them and/or treat them all the same (as the same super type)

* Function overloading: 
  * In C, there are several functions to compute the absolute value: `fabs(), labs(), llabs(), abs()`, etc.
  * Function overloading is the ability to define multiple functions with the same name but *different* parameters
  * Example: Java's `Math.abs()`: there are 4 different absolute value functions, all operating on different types
  * You can have as many functions with the same name as long as they differ in either the number (arity) or type of parameters
  * The compiler can tell *which* function you want to call based on the argument(s) you give it.
  * Determining which function is actually called is called "static dispatch" (because it is done at compile time)

* Operator Overloading
  * In Java: `String + String` means: concatenation
  * In Java: `int + int` means: addition
  * In Java: `String + int` means: concatenation
  * Some operators (like +, etc.) may have different meanings in different contexts and depending on the types used with it
  * What would `Set + Set` mean? 
    * Union
  * What would `List + List` mean?
    * Union?
    * Append?
    * What?
  * What would `Time + Time` mean?
  * Arbitrary operator overloading is supported in *some* languages: C++
  * Java provides *some* operator overloading, but does not allow you to define your own
  * Operator overloading can be extremely ambiguous: you are taking operators (+, -, =, etc) with *well understood* meanings and *changing them* 
  * Generally you should avoid (but understand) operator overloading
  * PHP: there is no overloading, + *always* means addition, to get concatenation, you use `.`
  * Python:
    * `String + String` means concatenation
    * `number + number` means addition
    * `number + String` is a runtime error

```c
char *msg = "Hello";
int x = 10;
int y = x + msg;
```

```python

a = 10
b = "hello, "
#error: msg = b + a
msg = b + str(a)
```
  
## Parameterized Polymorphism

* Java allows you to *parameterize* a class, method or variable
* Effectively makes the variable's *type* also variable
* You essentially want to use parameterization to make as generic of code as possible so that you can apply it to as many different TYPES as you can
* Saves you from having many multiple implementations for different types
* You can also place *bounds* on your parameters to give you a *minimal interface*  using the `<T extends Foo>` syntax: then you are guaranteed that `T` is of type `Foo` (at least)

# SOLID Principles

* collection of design principles for OOP created by Robert Martin, Michael Feathers early 2000s

## S = Single Responsibility Principle

* Encapsulation: a class should only have one responsibility
* Classes should not be "god" classes
* A `Person` class should be responsible for `Person` things, an `Address`
class should be responsible for `Address` things
* Load data from CSV files, then serialize it to XML or JSON files
* Classes should generally be small
* Large classes = more responsibility = less reusability

## O = Open/Closed Principle

* Every unit (module or class) should be *open for extension* and *closed for modification*
* Classical inheritance: extend the behavior in subclasses, but DO NOT modify the behavior in the super class
* You can add fields or methods to subclasses to augment its state or behavior
* Superclasses provide more general behavior that should NOT be modifiable: it should work *generally* and *generically* for all current types and any future types
* Have a good, well-thought out inheritance because it is extremely difficult if not impossible to change later on

## Liskov Substitution Principle

* If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.
* Recall: the Shape-Rectangle-Square problem: having mutable methods that can semantically change an object's type violate the Liskov Substitution Principle

## Interface Segregation Principle

* No client code should be forced to depend on methods it does not use
* Example: `ClickEventHandler` interface that specified *both* `onClick()` and `onDoubleClick()` methods
* When designing an interface: keep them as small as possible, maybe 1 or 2 methods at most (or even zero methods!)

## Dependency Inversion Principle

* High-level modules (classes) should not depend on low-level modules
* Both Should depend on abstractions
* Example: say you have 3 libraries on a phone app that compute location
  * Library A: `double GPSLocator.getLatitude(), double GPSLocator.getLongitude()`
  * Library B: `AndroidLocation AndroidNative.getLocation()` (`AndroidLocation.getLatitude(), AndroidLocation.getLongitude()`
  * Library C: `CellTowerGPS.getLatitudeRad(), CellTowerGPS.getLongitudeRad()`

```java
if(Library A) {
  return new Location(GPSLocator.getLatitude(), GPSLocator.getLongitude());
} else if(Library B) {
  AndroidLocation l =  AndroidNative.getLocation();
  return new Location(l.getLatitude(), l.getLongitude());  
} else if(Library C) {
  return new Location(Utils.radiansToDegrees(CellTowerGPS.getLatitudeRad()), Utils.radiansToDegrees(CellTowerGPS.getLongitudeRad()));
}
```

* The code above is the "client" code: it depends on the low-level classes `GPSLocator, AndroidNative, CellTowerGPS`
* You can invert the dependency: define an interface `Locator` that specifies one method: `Location getLocation()`
* You can implement 3 wrapper classes: `GPSLocator`, `AndroidLocator`, `CellLocator`, each one owns an instance (via composition) of a GPS locator library
* Each wrapper class `implements` the interface





  
  
  