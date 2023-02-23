# Computer Science II - CSCE 156H
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
* First demonstration: design a class that models a radioactive isotope

## Objects

* Formally an object is a general entity characterized by:
1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides

* Generally objects/classes should adhere to the **Single Responsibility Principle**: they should one thing and that one thing they should do well.
* Communication between objects is done by "message sending": you invoke a method on an object and the object/class is responsible for the behavior
  * Bad Encapsulation (breaking encapsulation): locating methods that act on the data in a class outside of the class
* Communication is facilitated through an object's *interface*: its `public`-facing methods or variables
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
  * Careful: YAGNI = You Ain't Gonna Need It (don't overdesign)
  * You break real-world objects or ideas into smaller and smaller parts until...
    * It is directly representable OR
    * An object already exists for it
* Generally objects are *composed* of other objects; this is known as "composition"
  * One object "owns" an instance of another object
* Patterns: are good practices to follow when creating objects
* Anti-patterns: bad habits to get into
* Example: "god anti-pattern": if you design a class that knows everything (has all the variables) does everything (has all the methods) and is everything (there is only one class), then it is the "god class"

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Abstraction is implementation or "information hiding"
* Review: procedural abstraction: How does `Math.sqrt()` work?  Who cares?
* The details of an implementation or representation of an object are abstracted away inside the class
  * How does Java represent a `String`?
  * How does Java represent a `LocalDate`?
    * 3 integers: year/month/day
    * String: ISO 8601 standard
    * A single number: number of milliseconds since an epoch (unix epoch: Jan 1, 1970)
    * Answer: who cares?

## Encapsulation

Strong encapsulation is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

* Don't break encapsulation by locating data or methods that act on that data outside the class
* Bank stuff belongs in the `Bank` class, not the `main` or `Demo` class, not the `Isotope` class
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
* Three types of relations in inheritance:
  * Covariant - always safe: you treat the subclass as a superclass
  * Contravariant - sometimes safe, sometimes dangerous, you need to explicitly check; you treat a superclass as a subclass
  * Invariant - never safe, you treat one class as a sibling
* You can, but should not use the `instanceof` keyword to check type safety
* Motivations for inheritance:
  * Code reuse
  * reduction of redundancy
  * Much cleaner "hierarchy" of elements
  * You can treat many types as one (polymorphism, later)
* Misc:
  * An `interface` is a pure `abstract` class: generally it only defines public interface (methods)
  * You can define an `abstract` class to: 1) prevent instantiation of instances (you cannot create instances of an `abstract` class) 2) avoid being forced to define ill-defined methods
  * You can use the keyword `final` on a class!
    * A `final` variable means you cannot change it (constant)
    * A `final` method means you cannot *override* it in the subclass: you prevent people from making changes to the behavior of your class
    * A `final` class makes it so you *cannot* `extends` it
    * Non-`final` methods are called "virtual" methods
* A *pure* abstract class is one in which NO functions are defined, ie all functions are `abstract` is an `interface`
  * You use the keyword `implements` instead of `extends`
  * You can `implements` more than one interface

### Pitfalls

* Some languages support *multiple inheritance*: you can inherit or "extend" from multiple classes (not interfaces)
  * Java is not one of them: it is a "single inheritance" hierarchy, you can only `extends` one class, not multiple
  * C++ does allow it
  * Diamond problem:
  * The inheritance is ambiguous, not well-defined
* Yo-yo problem
  * Deep inheritance heirarchies are bad: it forces you to look up and down at various documentation to see when adn where behavior was introduced or overridden or etc.
* Antipattern: Rectangle Problem
  * Shape, Rectangle, Square


## Polymorphism

* Polymorph = Many form(s)
* Code: a variable, method, or class may be generically programmed so that it can apply or be used with/on many different types
  * C: how did we sort?  We used `qsort()` which took *any* type of variable
  * Comparators for `qsort`: `int cmp(const void *a, const void *b)`
  * Python: we used `sort()` or `sorted()` which took *any* type of variable
  * Java: has static typing and so type safety can be verified at compile time

### Subtype polymorphism

* When you use inheritance you can treat a subclass as a superclass
  * Convariance is always safe!
  * This allows you to treat specific types as general types to simplify code
  * Ex: you only need ONE list of `Account`s to keep track of all accounts!
  * examples
  ```java
  Bird b = new Robin();
  List<Integer> numbers = new ArrayList<>();
  Collection<Integer> otherNumbers = numbers;

  ```

### Function Overloading

* This is not to be confused with *overriding* (overriding happens with inheritance in a subclass)
* Overloading does not involve inheritance
* A function/method is overloaded if you have multiple functions of the same name but different type or arity (number of) parameters
* Example: C has no function overloading
  * Consequence: `abs()`, `fabs()`, `labs()`, `llabs()`
  * You are forced to create multiple functions with the same basic functionality
  but with different names
* Java solves this by allowing you to define multiple functions with the same name
  * Ex: there are four absolute value functions that each take a different type
  * BUT you have the same name
  * This is because the function, `abs` has been overloaded
* The compiler is responsible for determining *which* function gets called/invoked based on the type of variable you provide
* This is known as *static dispatch* (static = at compile time, dispatch = )
* as long as the number of type of parameters is different, the function is different and the compiler is able to determine which one you mean by looking at the input parameters

### Operator Overloading

In Java:
  * `String + String`, plus means: concatenation
  * `int + int`, plus means: addition
  * `String + int`, plus means: change the int to a string representation and concatenate
Python:
  * `string + string`: concatenation
  * `int + int`: addition
  * `int + string`: error, you *must* do: `str(int) + string`
* Operator overloading means: giving more than one meaning to an operator in a programming language
* C++:
  * You can do anything with anything: wild west
  * `List + List`: [1, 2, 3] + [4, 5, 6] = [1,2,3,4,5,6]: append
  * `List + List`: [1, 2, 3] + [4, 5, 6] = [5,7,9]: vector addition
  * `List + List`: [1, 2, 3] + [4, 5, 6] = ?? Union, intersection, etc
  * WAAAY too confusing
  * `Time + Time` huh?
  * `Graph + Graph` ? union of vertices?  of edges?  
  * `Student + Student`
  * extremely ill-defined for all but the most obvious types (strings, numbers, etc.)
* Java does have *limited* built-in operator overloading (`+`) but does not allow you to define *user*-defined operator overloading

## Parameterized Polymorphism

* Java allows you to parameterize classes, methods or variables

```text

















```
