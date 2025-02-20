# Computer Science II - ECEN 156
## Spring 2025
### Object Oriented Programming

# Motivating Introduction

* Isotope Class Demo

### Creating and Designing Classes

* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java:
  * Allows you to model a "real world" object or idea or "entity"
* Classes allow for "Strong Encapsulation":
    1. The grouping of data
    2. The grouping of behavior (methods/functions/functionality) that acts on that data
    3. Protection of that data
* Generally speaking:
  * Book things belong in the `Book` class, Person things belong in the `Person` class, etc.
  * You should have one class per entity
  * You should protect your variables by making them `private` unless there is a Very Good Reason not to
* Generally look for ways to use *composition*
  * Composition: a class is *composed* of other classes
  * Example: a `Book` class owns an instance of a `Person` class (author)

## Objects

Formally an object is a general entity characterized by:
1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides (public methods in the object)

* Communication between objects is done via "message sending": you invoke a method on an object and that object executes the code inside that method (you call or invoke a method of the class)
* Communication is facilitated by the object's *interface*: the `public` methods that are available
* Otherwise, all other aspects of an object are *hidden* (information hiding/abstraction)
  * We don't want to have to worry about the representation NOR the implementation of an object, we just want to use it

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Implementation or "information hiding"
* Procedural abstraction: how does the `sqrt()` work?  Who cares?
* How does `LocalDate` represent a date?
  * 3 integers: year/month/day
  * A single String ISO8601
  * A single integer (unix epoch time)
  * Who cares?  We just want to use it.
* Relieves you of the need to know about any details of an object

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

* Don't break encapsulation by locating data or methods that act on that data outside the class
* bank "stuff" belongs in the `Bank` class, person "stuff" belongs in the `Person` class, etc.
* Good abstraction and encapsulation work together to make code:
  * More isolated
  * You can more easily reuse code
  * MOre easily testable because *side effects* are isolated
* Make sure your modeling is correct:
  * Is a person a string?
  * Is an address a string?

## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * A superclass/subclass relationship is an **is a** relationship: the subclass *is an* instance of the superclass
  * Alternative: parent/child relation
* Pattern:
  * Common functionality is shared in the super class while
  * Specific functionality is *overridden* in the subclass
  * Ex: Animal, Dog, Cat
* The *is-a* relationship **must be invariant**
  * A hierarchy of inheritance *must be well designed* up front, otherwise it collapses when you have an example that doesn't fit the hierarchy
  * Alternative: "prefer composition over inheritance"

### Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

### Observations:

* If a class `A` `extends` a class `B`:
  * `A` is the subclass/child class/derived
  * `B` is the superclass/parent class/
* Inheritance defines an "is-a" relationship
  * A `Dog` *is-a* `Animal`
  * An `Author` *is-a* `Person`
* If the relation is not invariant or not well-defined, avoid inheritance
* Three types of relations in inheritance:
  * Covariance: you can always treat a subclass as a super class (always safe)
  * Constravariance: sometimes you can treat a superclass as a subclass (but often times not): not always safe
  * Invariance: you can never treat "sibling" classes as another sibling class
*  You can **sometimes** use the `instanceof` keyword to determine the type or subtype of a variable
  * However, you should almost *NEVER* use this in a program
  * It is completely **wrong** to use `instanceof` to determine the control flow of your program.
  * The only time you should use this is when creating copies or instances of an object: there is no such thing as a "polymorphic constructor"
* Motivations for inheritance:
  * It provides a way for *code reuse*
  * It provides a way to *organize* code
  * Reduces redundancy
  * If well-designed, the introduction of new classes or new behavior or functionality **should not break any other piece of code**

### More Inheritance - Pitfalls

An inheritance hierarchy needs to be **very well defined**.
  * The *is-a* relationship *must* be invariant
  * Once a hierarchy has been established and code is now *dependent* on it, it *cannot be changed* without substantially breaking other code

Pitfalls
* Rectangle Problem
  * Your object relations in an inheritance hierarchy ALWAYS need to follow the is-a relationship
  * Another Example: Author, Director, Person hierarchy no longer makes sense if you have a person who is BOTH an author and director
  * This is a violation of the Liskov Substitution Principle (SOLID)
 * Antipattern (a bad behavior or bad but common mistake in code): yo-yo antipattern
  * You don't want your inheritance hierarchies to be too deep
  * YOU should prefer shallow hierarchies
  * Or: "prefer composition over inheritance"
  * YAGNI = You Ain't Gonna Need It
* Diamond Problem Antipattern
  * Animal/Dog/Cat
  * Some languages allow you to inherit from multiple classes!
  * It is ambiguous as to which behavior you are inheriting!
  * For languages (C++) that allow this, you have to write code to explicitly state what you are inheriting: defeats the purpose of code reuse anyway
  * Java (and most sane) programming languages do not allow this

### More Inheritance Tricks

* Java allows you to create `abstract` classes: classes that you are not allowed to instantiate instances of but that you can define state and behavior in
* In Java, you can create an `abstract` classes:
  * You can still inherit from an `abstract` class
  * However, you cannot create an instance of an `abstract` class
  * `abstract` classes can define state (variables) or methods that are inherited by any sublcass
  * You can have `abstract` subclasses but at some point it needs to be an actual implementation
  * You provide the behavior in NON-abstract *subclasses*
* An `interface` is a *pure* abstract class: it only has method signatures, no state, no implementations
  * This has the advantage that you can `implements` multiple interfaces!

# Polymorphism

* Polymorph = multiple form(s)
* Code: one piece of code (variable, method or class) can be written generically and applied to many types
* C: `qsort()`
* Java: `Collections.sort()`
* This allows code reuse at an even higher level!

## Subtype Polymorphism

* Is the "classic" polymorphism: you can treat a subtype as a supertype
* You can treat a `Robin` as a `Bird`
* Covariance: it always safe (as long as it is designed correctly)!
* If you don't need the specialized behavior of a subclass, you can write more generic code to apply to ALL subclasses

### Method Overloading

* Example: in C how many absolute value functions did you have? `abs(), fabs(), labs(), llabs()`
* In a language without method overloading, once you create a function with a given name, `foo`, that is the ONLY function that can have that name
* You end up "polluting the namespace": libraries will typically prepend ALL functions with some acronym: `gmp_function_name`
* In an OOP language: you have method overloading: you can have more than one method with the same name but with different parameter types!
* The compiler is able to determine which method you intended to call based on the input(s) you give it: this is known as "static dispatch"

### Operator Overloading

* Consider in Java:
  * `String + String`: `+` means concatenation
  * `int + int`: `+` is addition
  * `String + int`: convert the `int` to a string and concatenate
* C:
  * `int + int`: addition
  * `char * + char *`: memory address + memory address (nonsense)
* Other languages it may cause an error (python) or it may try to convert the string to a number and add (php)
* In java: only this particular instance is an operator overloaded
* C++: you can customize and change ANY operator to ANY operation
  * You could redefine `+` to mean subtraction!
  * most languages have *some* built in operator overloading
  * most languages do not allow user-defined operator overloading (good)
* `array + array`:
  * Append the second array to the first (creating a new array)
  * Math: coordinate-wise addition (vector addition)
  * Union: $A \cup B$
  * Confusion!
* In C++ or any language that *would* support this:
  * YOu have to write a function to define it
  * If you're gonna write a fucntion anyway, give it good name: `arrayAppend()`, `vectorAdd()`, `union()`
* Other things make no sense at all:
  * `Time + Time`
* Most languages do not allow customization of operator overloading and even if they did: you'd have to write a function

### Parameterized Polymorphism

* In Java you can create a parameterized class, variable, or method
* PECS: Producer Extends, Consumer Super
  * A collection (list, set) is a *producer* of elements; if you wish to pull them out and do something with them, then you need a "named" parameter: `<T extends Item>` (`T` is the name
  of type type being used)
  * A collection is a *consumer* of elements if you wish to put stuff in: `<? super Item>`: if you don't care about what is in the collection already; you just want to put stuff in

## SOLID Principles

## S = Single Responsibility Principle

* Good encapsulation: a class should represent one thing and represent it well
* Violation: God-Class: the class is responsible for everything, knows everything, does everything
* You can still violate it by having it do 2 things
  * `Person` vs `Address`
  * Loading Data, Converting Data, Saving Data
* YAGNI Principle: You Aint Gonna Need It: don't over engineering things
  * Person: UUID, last name, first name, DOB, etc.
* Avoid "leaky abstractions":
  * Code should not require users to use it in a *specific* way
  * If you were forced to call `initialize_math()` before you called any `Math` function
* Ex: exposing how emails are stored: a particular list, a set, an array, etc.  
  * `public ArrayList<String> getEmails()`
  * Better: `public List<String> getEmails()`
  * Best: return a copy of the emails as a new `Collection<String>`
  * Even worse: `public String[] getEmails()`

## O = Open/Closed Principle

* Every unit (module, class, method) should be *open for extension* and *closed for modification*
* Classical inheritance:
  * Superclasses provide a *general* behavior that should *NOT* be changed otherwise:
    * It must have been a bad design to begin with OR
    * changing it breaks all other code
* Violation: `instanceof` to determine business logic (the value of an account, the cost of items)

```java
if(object.getType().equals("Stock")) {
  //do stock thiings here
} else if(object.getType().equals("Annuity")) {
  //do annuity things here
}

//also wronger:
if(object instanceof Stock) {
  //do stock thiings here
} else if(object instanceof Annuity) {
  //do annuity things here
}

```

* The only time you *should* (in fact have to) use the `instanceof` keyword or type checks: when you call a constructor

* In Java: you can enforce this principle using the `final` keyword:
  * `final` variables mean you are making them constant
  * A `final` class cannot be extended: ex: `Integer`
  * A method can be made `final` and subclasses cannot override it!


## L = Liskov Substitution Principle

* If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.
* Subtype polymorphism!

```java
ArrayList<Integer> numbers = new ArrayList<>();

List<Integer> numbers = new ArrayList<>();
```

* Violation: Rectangle/Square example
* Author, Director, Person
* The "is-a" relation has to **always hold**

## I = Interface Segregation Principle

* No "client" code (code that uses other code) should depend on methods it does not care about
* Example `ClickEventHandler` is an interface that defines two methods:
  * `onClick()`
  * `onDoubleClick()`
* IF we only wanted to implement `onClick()`: we'd still be forced to implement `onDoubleClick()`:
  * NOOP = No Operation
  * Call the other method: `onDoubleClick()`: call `onClick()`
  * Better UI design: you disable the button on a click
* Instead: all interfaces should be as small as possible


## D = Dependency Inversion Principle

* High-level modules (classes) should not depend on low-level modules
* Ex:
  * YOu write code that connects to a (Free) MySQL database with very-speciic MySQL methods, etc.
  * Vendor lock: You are locked into a particular "vendor" (software sales) and it is very expensive to change
  * Instead: you should have written generic code for an *interface* that each one of the databases *implements*

* Example
* Library A:  
`double GPSLocator.getLatitude()`  
`double GPSLocator.getLongitude()`
* Library B:  
`AndroidLocation AndroidNative.getLocation()`  
 `AndroidLocation.getLatitude()`   `AndroidLocation.getLongitude()`
 * Library C: `CellTowerGPS.getLatitudeRad(), CellTowerGPS.getLongitudeRad()`

 ```java

 if(Library A) {
   return new Location(GPSLocator.getLatitude(), GPSLocator.getLongitude());
 } else if(Library B) {
   AndroidLocation l =  AndroidNative.getLocation();
   return new Location(l.getLatitude(), l.getLongitude());  
 } else if(Library C) {
    return new Location(Utils.radiansToDegrees(CellTowerGPS.getLatitudeRad()),
     Utils.radiansToDegrees(CellTowerGPS.getLongitudeRad()));
 }
```

* Inversion:

```java

public interface Locator {

  public Location getLocation();

}

public AndroidLocator implements Locator {


  public Location getLocation() {
    AndroidLocation l =  AndroidNative.getLocation();
    return new Location(l.getLatitude(), l.getLongitude());  

  }

}

//client code: doesn't care about the type of Locator
Locator l = new AndroidLocator();
Location = l.getLocation();

```

* "Inverting" a dependency means creating an interface between the "highlevel" object/class/thing and the low-level library that `implements` it
* In general, you should prefer *loose coupling* so that software components can easily be interchanged


```text















```
