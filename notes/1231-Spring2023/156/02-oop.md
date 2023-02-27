# Computer Science II - CSCE 156
## Spring 2023
### Object Oriented Programming

## Creating and Designing Classes: Reflection & Demo

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

* In Java identity is implementing using the `equals()` and `hashCode()` methods
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
    * It is direclty representable OR
    * An object already exists for it
* Generally your objects are *composed* of other objects or data
  * When an object "owns" an instance of another object it is called "composition"
* Avoid the "god-antipattern": a class should only know about its own stuff, it should not *know everything, do everything, be everything*

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
  * Ex: Animal/Cat/Dog

Demonstration: draft a collection of financial asset classes
  * Savings Account: an account that pays *interest* on a *balance* monthly
  * Annuity: a product that pays a fixed monthly amount over a certain number of years

Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

Observations:
* If a class $A$ `extends` a class $B$:
  * $A$ is the subclass, $B$ is the superclass
  * $A$ is the child class, $B$ is the parent class
* Inheritance forms an *is-a* relationship
  * A `Robin` is-a `Bird`
  * An `Annuity` is-an `Account`
* Three types of relations:
  * Covariant - treat a subclass as a superclass (always safe)
  * Contravariant - treating a superclass as a subclass (not always safe, often dangerous)
  * Invariant - treating  a sibling class as a sibling (never safe)
* You can use the `instanceof` keyword to make a contravariance safe, but you should almost *always* **never** do this
  * Generally you only use this with constructors (copy constructors)
* Motivations for inheritance:
  * Provides a way for code reuse
  * Reduces redundancy
  * It is well-designed if the introduction of a new class does not break any of the other code
  * It provides a way to organize code
* If you have a class that is not well-defined, make it `abstract`
  * `abstract` classes may have state and methods
  * `abstract` classes may have `abstract` methods: methods that do not have a body  but force subclasses to specify behavior
  * With `abstract` classes you can prevent someone from creating instances of them
* Sometimes you want to *prevent* subclassing
  * Example: should you be able to create your own `MyInteger` class?
  * You can make a class `final` which prevents anyone from subclassing it
  * Recall: a `final` variable makes it into a constant
  * You can also make *methods* `final` which prevents anyone from overriding them in a subclass
  * By default in Java, all methods are "virtual": they *can* be overridden in the subclass
* A *pure* abstract class is one in which NO functions are defined, ie all functions are `abstract`
  * in Java, you would use an `interface`
  * This allows you in Java to `implements` an interface and more so...
  * In Java you can only `extends` one class (single inheritance hierarchy) but
  * you can `implements` multiple interfaces

### Pitfalls

* Inheritance is great but it can go too far
* Some languages support *multiple inheritance*: you can inherit or "extend" from multiple classes (not interfaces)
  * Java is not one of them
  * C++ does allow this
  * Diamond problem: if you inherit from multiple classes, which methods are inherited?
  * Cat, Dog are both Animals
  * Create a class `CatDog` that inherits from *both* a `Cat` and a `Dog`
  * The inheritance is ambiguous, not well-defined
  * Java avoids this by forcing a single-inheritance hierarchy: you can only `extends` one class!
* Antipattern: yo-yo problem
  * Inheritance hierarchies should generally be shallow
  * Deep hierarchies mean you are always looking up and down the chain to identify state/behavior
* Antipattern: Rectangle Problem
  * Mutable objects may cause their state to change so that they are not the thing that you are modeling

## Polymorphism

* Polymorph = multiple form(s)
* code: a variable, method, or class can be *generically* apply to more than one type
  * Example: C `qsort()` can be used for any type of variable
  * Exmaple: Python: `a.sort()` or `sorted(a)`
  * Java: we use explicit parameterization: `List<Integer> numbers`
* It allows you to write generic code, one function/method that can be applied to *any* type

### Subtype Polymorphism

* You can treat a class as its superclass
* Ex: you can treat a `Robin` as a `Bird`
* If you don't need specific/specialized behavior you can treat a bunch of different types as one single type (superclass)

### Method Overloading

* Ex: how do you compute the absolute value in C?
  * `abs()`, `fabs()`, `labs()`, `llabs()`
  * C does not have function overloading: when you create a function, you can only use that ONE name for that ONE function
  * Python does not have function overloading: parameters are optional and you can define "default" values
* OOP languages (Java) support function overloading: you can define multiple versions of a function with the *same* name as long as either the number (arity) or type of parameters are different
* At compile time a compiler is able to figure out *which* version to invoke by looking at what you passed to it: the number or type of variable
* "Static Dispatch": (Static = compile time), dispatch

### Operator Overloading

* Consider in Java:
  * `String + String` means concatenation
  * `int + int` means addition
  * `String + int` means convert the int to a string and concatenate
* Python:
  * `string + string` means concatenation
  * `int + int` means addition
  * `string + int` means ERROR: instead you use `str(int) + string`
* C:
  * `char * + char *`: non-sense (are adding two memory addresses)
* Operator overloading is when an operator: `+, -, *, /`, etc has more than one meaning in different contexts
* Languages use this *very sparingly*
  * `List + List`: appending
  * `List + List`: vector addition (component-wise addition)
  * Union, intersection, etc.
  * `Duration + Duration`
  * `Time + Time`
* SOME programing languages allow you to override *ANY* and *ALL* operators: C++
* At the end of the day, you have to write a function that defines what the overload means anyway: ex: `a.append(b)`

### Parameterized Polymorphism

* In Java you can create a parameterized class, variable, or method
* Demonstration: we wish to add up the value of all our accounts
* Observations:
  * Using sub-type polymorphism allows us to only have to define *one* method
    that applies to `List`s, `Sets`s, using `Collection`
  * Cannot use sub-type polymorphism on parameters due to *type erasure*
* PECS: Producer Extends, Consumer Super
  * A collection (list, set) is a *producer* of elements; if you wish to pull them out and do something with them, then you need a "named" parameter: `<T extends Item>` (`T` is the name
  of type type being used)
  * A collection is a *consumer* of elements if you wish to put stuff in: `<? super Item>`: if you don't care about what is in the collection already; you just want to put stuff in

## SOLID Principles

## S = Single Responsibility Principle

* Simple restatement of good encapsulation
* One class = one idea = one responsibility
* A class (or method) should be responsible for one thing: do one thing and do it well
* Don't have "god" classes
* A `Person` class should not be responsible for an `Address`, it should *own* (by composition) an address class
* DRY = Don't Repeat Yourself

## O = Open/Closed Principle

* Every unit (module, class, method) should be *open for extension* and *closed for modification*
* Classical inheritance:
  * Superclasses provide *general* behavior that should not be modified (once the heirarchy has been designed)
  * Subclasses provide *specific* behavior that *can* be modified
* Suppose you had some well-defined functionality in a superclass and you had 5 subclasses that all depended on it.  
  * Suppose you have a new subclass that requires different behavior
  * Solution: modify the superclass
* If there is no general or "default" behavior: don't define it!!  Keep it `abstract`
* Alternative: break functionality down into smaller parts
* Have a good, well-thought out inheritance because it is extremely difficult if not impossible to change later on
* Should an `Integer` class be subclassed to redefine what an integer is?  Java made this *closed* for modification by making it `final`

## Liskov Substitution Principle

* If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.
* Subtype polymorphism!

```java
ArrayList<Integer> numbers = new ArrayList<>();
LinkedList<Integer> numbers2 = new LinkedList<>();
List<Integer> foo = numbers; //works due to subtype polymorphism
foo = numbers2;
```

* Violation of Liskov: Rectangle problem

## Interface Segregation Principle

* No "client" code (code that uses other code) should depend on methods it does not care about
* Example `ClickEventHandler` is an interface that defines two methods:
  * `onClick()`
  * `onDoubleClick()`
* Instead: all interfaces should be as small as possible
* KISS = Keep It Simple, Student

## Dependency Inversion Principle

* High-level modules (classes) should not depend on low-level modules
* Both should depend on abstractions

```java

PersonCsvDataLoader foo = new PersonCsvDataLoader(fileName);
List<Person> people = foo.getPersons();

PersonXmlDataLoader foo = new PersonXmlDataLoader(fileName);
List<Person> people = foo.loadPersonData();

PersonDatabaseDataLoader foo = new PersonDatabaseDataLoader(fileName);
List<Person> people = foo.connectAndQueryPersonTable();

public interface PersonDataLoader {

  public List<Person> loadData();

}

List<Person> people = foo.loadData();

public interface DataLoader <T> {

  public List<T> loadData();

}

```

```text










```
