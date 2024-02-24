# Computer Science II - CSCE 156H
## Spring 2024
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

Formally an object is a general entity characterized by:

1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality of the object or what it *does* or the services it provides

## Object Design

* "Semantics dictate design"
* Careful: YAGNI = You Ain't Gonna Need It (don't overdesign)
* You break real-world objects or ideas into smaller and smaller parts until...
  * It is directly representable OR
  * An object already exists for it
* Generally objects are *composed* of other objects; this is known as "composition"
  * One object "owns" an instance of another object or a
  * Collection of objects (sort of like "aggregation")
* Patterns: are good practices to follow when creating objects
* Anti-patterns: bad habits to get into
* Example: "god anti-pattern": if you design a class that knows everything (has all the variables) does everything (has all the methods) and is everything (there is only one class), then it is the "god class"

## Constructors

* Objects are *constructed* not assigned
* In Java:
  * A default no-argument constructor is provided if you don't create one
  * When you define your own constructor, the default goes away
  * YOu can restore it by explicitly defining it...
* Another useful pattern: copy constructor
* Even better: the builder pattern (see the advanced activities in Lab 4)
* Bidirectional compositioin: A owns an instance of B and B owns an instance of A
  * Be careful: you need a way to solve the "chicken and egg problem"
  * You need a way to avoid infinite ping pong (infinite recursion back and forth)

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Abstraction is implementation or "information hiding"
* Review: procedural abstraction: How does `Math.sqrt()` work?  Who cares, it just works
* The details of an implementation or representation of an object are abstracted away inside the class
  * How does Java represent a `String`?
  * How does Java represent a `LocalDate`?
    * 3 integers: year/month/day
    * ISO 8601 string?
    * A single number: number of seconds since Jan 1 1970

## Encapsulation

Strong encapsulation is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

* Don't break encapsulation by locating data or methods that act on that data outside the class
* Bank stuff belongs in the `Bank` class, not the `main` or `Demo` class, not the `Book` class
* Student "stuff" belongs in the `Student` class
* Book stuff belongs in the `Book` class, etc.
* Good abstraction and encapsulation work together to make code:
  * Isolated
  * Side effects are controlled: no globals, you can validate data, etc.
  * Reusable


## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * Common functionality and state/behavior is located in the `super` class
  * Place specialized or specific behavior/state in the *subclass*
  * The subclass/superclass relationship forms an *is a* relationship (always safe)
    * A dog is an animal
    * A director is a person
    * A robin is a bird
* Three types of relations in inheritance:
  * Covariant - always safe: you treat the subclass as a superclass
  * Contravariant - sometimes safe, sometimes dangerous, you need to explicitly check; you treat a superclass as a subclass
  * Invariant - never safe, you treat one class as a sibling
* You can, but should not *generally* use the `instanceof` keyword to check type safety
  * The only time you may *need* to use `instanceof` is when calling a constructor (especially a copy constructor)
  * Any time you find yourself using `instanceof` to determine logical flow: **you are doing something wrong**
* Motivations for inheritance:
  * Code reuse: You write code and place it in a superclass and all subclasses automatically inherit that code
  * Reduces redundancy, reduces potential failure points
  * Much cleaner way to organize code into *hierarchies*
 * Tools:
    * UML Sketches: https://app.diagrams.net/
    * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

### More Inheritance - Pitfalls

An inheritance hierarchy needs to be **very well defined**.
  * The *is-a* relationship *must* be invariant
  * Once a hierarchy has been established and code is now *dependent* on it, it *cannot be changed* without substantially breaking other code

Rectangle Problem: Shape, Rectangle, Square
  * Your object relations in an inheritance hierarchy ALWAYS need to follow the is-a relationship
  * Our example: Author, Director, Person: not a great design because it doesn't allow a person that is both
  * This is an example of a violation of the Liskov Substitution Principle (SOLID)  

Yo-yo Antipattern
  * Deep inheritance hierarchies (20 classes/levels) deep are bad
  * Keep your hierarchy shallow, OR
  * "Prefer Composition over Inheritance": it gets you the same or similar benefits of code reuse but without locking you into the hierarchy!

Diamond Problem/Antipattern
  * Some (one?) language allows you to inherit from multiple classes (C++)
  * The problem: what gets inherited from what superclass?
  * Java does not allow multiple inheritance!  Yay!
  * Even if you allowed it, you still have to write code to disambiguate your code, defeats the purpose of code reuse with inheritance

### More Inheritance - Solutions

* What happens if you have well-defined subclases but not-so-well-defined superclasses
* In Java you can create an `abstract` class
  * You use the keyword `abstract`
  * Then you can have `abstract` methods: methods with no *default* behavior, no method body, etc.
  * Then subclasses that inherit this `abstract` method, MUST provide an implementation
  * Even if you have a constructor, you *cannot* instantiate an abstract class!
* In Java you can also create a *pure* abstract class: `interface`
  * An interface allows you to define any number of methods with NO default behavior
  * This allows classes to `implements` multiple interfaces

# Polymorphism

* Poly = Many, Morph = Form
* Code: one piece of code (variable, method or class) can be written generically and applied to many types
* C: `qsort()`, Python: `.sort()`
* Java: `Collections.sort()`

### Subtype Polymorphism

* This is the "classic" OOP polymorphism
* You can treat any subtype as a supertype
* You can treat a `Robin` as a `Bird`
* You can treat an `ArrayList` as a `List`
* It simplifies code and means you only have to write on list or one method to process that list (or other types)

### Method Overloading

* C: how many absolute value functions does C have?  `abs()`, `fabs()`, `labs()`, `llabs()`, etc.
* This is because C does not have method overloading: once you have defined a function with a name `foo` there can be NO other function with that name!
* OOP allows method overloading: you can define multiple functions with the same name but with different paratemers/input types
* The compiler is "smart enough" to know which function you want based on the paratmer type you call it with: if you call it with an `int` as input, it calls one version, if you call it with a `double` it calls another version, etc.
* This mechanism is known as "static dispatch"

### Operator Overloading

* Consider in Java:
  * `String + String` what does `+` mean in this context?  Concatenation
  * `int + int`: `+` now means addition
  * `String + int`: `+` now means: convert the int to a string and concatenate
* Python:
  * `string + string`: concatenation
  * `int + int`: addition
  * `string + int`: ERROR
* C:
  * `char * + char *` memory addition: weird things happen
  * C does not have ANY operator overloading
* C++: full operator overloading: you can write code to redefine ANY operator.  ex: you redefine `+` to mean subtraction
* This is bad in general:
  * `List + List`: $A \cup B$ union, append: append the second list to the first, vector addition: $[a, b, c] + [x, y, z] = [a+x, b+y, c+z]$
  * `Time + Time`: meaningless!
* In a langauge like C++: you have to write a function that defines what an operator means anyway, why not just use the function

### Parameterized Polymorphism

* Java allows you to parameterize classes, methods or variables

## SOLID Principles

## S = Single Responsibility Principle

* Good encapsulation: a class should represent one thing and represent it well
* Violation: God-Class: the class is responsible for everything, knows everything, does everything
* You can still violate it by having it do 2 things
  * A class that loads data **and** saves data
  * Person class should not be responsible for Address stuff
* Careful: you can go overboard: would a `Name` class be appropriate?
* YAGNI = You Aint Gonna Need It
* Avoid "leaky abstractions":
   * Code should not require users to use it in a specific way or order
   * `sqrt()`: before you called this, you had to call the `initialize_math_library()` function and then call the `initializ_math_internals()`

```java
//works.. but
loadAuthors()
loadBooks()


//does not work:
loadBooks()
loadAuthors()
```

* YOu want to determine a `Person`'s age: if you have a `getAge()` method: great! But if you instead have `getBirthDayAndComputeItYourself()`

## O = Open/Closed Principle

* Every unit (module, class, method) should be *open for extension* and *closed for modification*
* Classical inheritance:
  * Superclasses provide a *general* behavior that should *NOT* be changed otherwise:
    * It was a bad design
    * Changes break all the code that depends on it
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

* In Java: you can enforce this principle using the `final` keyword:
  * `final` variables mean they are constants!
  * A `final` class cannot be extended
  * A method can be made `final` and any subclasses cannot override it! cannot change it!

## Liskov Substitution Principle

* If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.
* Subtype polymorphism!

```java
ArrayList<Integer> numbers = new ArrayList<>();
LinkedList<Integer> numbers2 = new LinkedList<>();

List<Integer> foo = null;

//NO Problem:
foo = numbers;

//NO problem:
foo = numbers2;
```

* Violation: Rectangle/Square example
* Author, Director, Person
* Prefer composition over inheritance

## Interface Segregation Principle

* No "client" code (code that uses other code) should depend on methods it does not care about
* Example `ClickEventHandler` is an interface that defines two methods:
  * `onClick()`
  * `onDoubleClick()`
* A bad design would *force* you to implement both of these things even if you didn't want to...
* Instead: all interfaces should be as small as possible

## Dependency Inversion Principle

* High-level modules (classes) should not depend on low-level modules
* Ex:
  * You can write code to connect to a database and load data
  * Suppose you write the code toward a very specific database: MySQL
  * Now you want to switch over to MSSQL or Oracle
  * You now have to throw all your code away and start over

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
 else if(Library C) {
    return new Location(Utils.radiansToDegrees(CellTowerGPS.getLatitudeRad()),
     Utils.radiansToDegrees(CellTowerGPS.getLongitudeRad()));
 }
```

* INversion:

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

* OOP, SOLID, STUPID, XML, YAGNI, JSON, etc.

```text






```
