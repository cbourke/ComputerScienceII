# Computer Science II - ECEN 194
## Spring 2023
### Object Oriented Programming


## Objects

### Creating and Designing Classes: Demo

* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java:
  * allows you to model a "real world" object or idea or "entity"
  * allows you to *group* multiple pieces of data together into one logical unit
  * also allows you to *encapsulate* methods within the class that *act on* or *operate on* that class's data (ie functionality)
  * IE: it allows you to group functionality together with the data that it operates on
  * Java classes also allow you to *protect* data from outside of the class
* Demonstration: design a class that models a radioactive isotope

## Objects

* Formally an object is a general entity characterized by:
1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides

* Communication between objects is done via "message sending": you invoke a method on an object and that object executes the code inside that method
* Communication is facilitated by the object's *interface*: the `public` methods that are available
* Objects are *constructed* rather than just assigned
  * In Java, by default a no-argument constructor is provided
  * If you define your OWN constructor, it goes away
  * You can always restore it by explicitly defining it
  * You can define as many constructors as you wish to make your objects more usable
* Common patterns:
  * Copy constructors that take another instance of the object and make a deep copy
  * Partial copy constructors: copy constructors that take an object and other values to make a (partial) copy of the object
* Object design:
  * "Semantics dictate design"
  * Don't over-design: YAGNI = You Aint Gonna Need It
  * You break real-world objects or ideas into smaller and smaller parts until...
    * It is directly representable OR
    * An object already exists for it
* Generally your objects are *composed* of other objects or data
  * When an object "owns" an instance of another object it is called "composition"
* Avoid the "god-antipattern": a class should only know about its own stuff, it should not *know everything, do everything, be everything*
* Follow the **Single Responsibility Principle**

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Implementation or "information hiding"
* You shouldn't have to worry about how an object is represented, just how to use it (its interface: its publicly facing methods)
* Ex: how does `LocalDate` represent an actual date?
  * 3 integers: year/month/day
  * String: ISO 8601 standard
  * A single number: number of milliseconds since an epoch (unix epoch: Jan 1, 1970)
  * Answer: who cares?
* How does Java represent a `String`?

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

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * Common functionality and state/behavior is located in the `super` class
  * Specific behavior/specialization is provided or *overridden* in the subclass
  * Example: Animal, Dog, Cat

Demonstration: draft a collection of financial asset classes
  * Savings Account: an account that pays *interest* on a *balance* monthly
  * Annuity: a product that pays a fixed monthly amount over a certain number of years

Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

Observations:

* If a class `A` `extends` a class `B`:
  * `B` is the subclass/child class/derived
  * `A` is the superclass/parent class/
* Inheritance defines an "is-a" relationship
  * A `Dog` *is-a* `Animal`
  * An `Annuity` *is-a* `Account`
* Three types of relationships in inheritance:
  * Covariant - always safe (treat a subclass as a superclass)
  * Contravariant - sometimes safe but not likely (treating a superclass as a subclass)
  * Invariant - Never safe (treat a sibling class as a different class)
* You can, but should almost *never* use the `instanceof` keyword to check type safety
  * Acceptable use case: using copy constructors
* Motivation:
  * Provides a way to *reuse* code, avoid repeating code (by placing in the super class)
  * Provides a way to *organize* code
* If you have a class that is not well-defined, make it `abstract`
  * `abstract` classes may have state and methods
  * `abstract` classes may have `abstract` methods: methods that do not have a body but force subclases to specify behavior
  * With `abstract` classes you can prevent someone from creating instances of them
* Sometimes you want to *prevent* subclassing
  * Example: should anyone be allowed to change the behavior/expectations of say `Integer`
  * Example: some variables should be constant: `Math.PI`: to make it constant, yo uuse the `final` keyword
  * You can use `final` on a class to prevent subclassing
  * You can use `final` on an individual method to prevent *overriding* that method
  * By default all methods in Java are "virtual" (they *can* be overridden); using `final` makes them non-virtual
* A *pure* abstract class is one in which NO functions are defined, ie all functions are `abstract`
   * in Java, you would use an `interface`
   * This allows you in Java to `implements` an interface and more so...
   * In Java you can only `extends` on class (single inheritance hierarchy) but
   * you can `implements` multiple interfaces

### Pitfalls

* Some languages support *multiple inheritance*: you can inherit from more than one class
  * Java is not one of them: instead use interfaces
  * C++ does allow this
  * This is the "diamond problem": there is an ambiguity in what is inherited
  * C++ solves this by requiring you to *explicitly* state what you are inheriting
  * Java avoids this by forcing a single-inheritance hierarchy: you can only `extends` one class!
* Antipattern: yo-yo problem
  * Deep inheritance hierarchies can be very confusing
  * Generally keep your hierarchies shallow
* Antipattern: Rectangle Problem

## Polymorphism

* Polymorph = Many Forms
* Code: a variable, method, or class can be generically programmed so that it can apply to or be used with multiple different types
  * C: how do you sort? `qsort()`: it is a "generic" function
  * In Java: polymorophism appears in many forms

### Subtype Polymorphism:

* Treating a subtype as a superclass so that you can treat it more generally
* Covariance which is always safe

### Function Overloading

* Recall from C: how do you compute the absolute value?
  * `abs(), fabs(), labs(), llabs()`
  * Without "function overloading" you can only ever have ONE function with the same name
* Observe Java: `Math.abs()`: multiple functions with the same name but different type or arity (number of) parameters
* At compile time the compiler is smart enough to deduce which function you actually meant to call
* This is known as "static dispatch" (static = at compile time, dispatch = dispatch)
* as long as the number of type of parameters is different, the function is different and the compiler is able to determine which one you mean by looking at the input parameters

### Operator Overloading

* In Java:
  * `String + String` means concatenation
  * `int + int` means addition
  * `String + int` means convert the integer to a string and concatenate
* Python:
  * `String + String` means concatenation
  * `int + int` means addition
  * `String + int` ERROR, you are required to explicitly cast the int as a string: `str(int)`  
* C:
  * `int + int` is addition
  * `char * + char *` is "memory addition"
* In some languages some operators (`+`) have more than one meaning depending on the context or *types* they are used on
* C++:
  * Wild-west/anarchy language: you can overload ANY operator
  * `List + List`: [1, 2, 3] + [5, 6, 7] = [1, 2, 3, 5, 6, 7] (append)
  * `List + List`: [1, 2, 3] + [5, 6, 7] = [6, 8, 10] (vector addition)
  * There are other reasonable interpretations, it is better to just make a well-defined/well-named function to take care of it
  * `Duration + Duration`
  * `Time + Time` ill-defined
  * `Set + Set`: union, intersection, symmetric difference
* Consequently, most languages have *some* built-in operator overloading, but don't allow user-defined operator overloading

## Parameterized Polymorphism

* Java allows you to use a parameterized class, method or variable

```c

int cmp(const void *a, const void *b) {
  //force a, b to become integer pointers:
  const int *x = (const int *)a;
  const int *y = (const int *)b;
}

qsort()

```


```text












```
