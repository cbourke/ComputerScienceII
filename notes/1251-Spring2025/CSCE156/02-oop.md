# Computer Science II - CSCE 156
## Spring 2025
### Object Oriented Programming

## Creating and Designing Classes: Reflection & Demo

* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java allows you to model a "real world" object or idea or "entity"

## Objects

Formally an object is a general entity characterized by:

1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality that acts on the object's state/data: what the object does or what services it provides: its methods

* Identity is achieved in Java by generating the `equals()` and `hashCode()` methods
* Communication between objects is done via "message sending": just a fancy way of saying an object's functions are invoked or called
* Communication is facilitated by the object's *interface*: the `public` methods that are available
  * `private` methods may be useful, but are *not* part of the interface
* Objects are *constructed* rather than just assigned
  * In Java, constructor methods are named after the class; the have no return value, and can take any number of parameters
  * If you do not define a constructor, a no-argument (no parameter) default constructor is provided by Java
  * If you do define a constructor, the default goes away; you an always explicitly define it
  * You can have as many constructors as you want
  * Useful pattern: copy constructors
* Common patterns:
  * DRY Principle = Don't Repeat Yourself!
  * YAGNI = You Ain't Gonna Need It (don't over engineer your classes)
  * KISS = Keep It Simple, (i said) SIMPLE!

## Four Pillars:

* Abstraction
* Encapsulation
* Inheritance
* Polymorphism

## Abstraction

* Implementation or "information hiding"
* Consider: `Math.sqrt()`: who cares how it works, it just does
  * Procedural abstraction: the details are hidden inside the function, we don't have to worry about them
* Consider: `LocalDateTime`: how does it *represent* a date/time
  * ISO8601 formatted string?
  * several integers: one for each field: day, month, year, hour, minute, second, fractional second
  * a single number: unix epoch number
  * Who cares?  The representation is *internal* to the class
* Abstraction allows you to *reason about an object* without knowing how it works or how it is represented

## Encapsulation

Usually is comprised of three things:
1. The grouping of data into one logical unit
2. The protection of data from the outside world
3. The grouping of methods (behavior) that act on that data

Generally:
* All member variables should be `private` to protect them unless you have a Very Good Reason to do otherwise
* You can make as many constructors as you want in order to make your objects flexible
* You should avoid setter functions: you should prefer immutable objects
* Better patterns: copy constructors
* Another better pattern: Builder pattern (see the advanced activities in Lab 4)

## Inheritance

* Inheritance allows you to reuse a class by `extends`ing it and creating a *subclass*
  * A superclass/subclass relationship is an **is a** relationship: the subclass *is an* instance of the superclass
  * Alternative: parent/child relation
* Overall:
  * General functionality is located in the super class and shared among all of the subclasses
  * Specific functionality is located in the subclasses
* Classic example: Animal, Cat, Dog
* Specific functionality is *overridden* in the subclass
* Demonstration:
  * Extend our `Person` class so that we have `Author` and a `Director`
* Inheritance defines an *is-a* relationship: a `Dog` is-a `Animal`
* When a subclass `extends` a superclass, it *inherits* its state and functionality

### Tools:
  * UML Sketches: https://app.diagrams.net/
  * UML Generation: plantUML: https://github.com/cbourke/ComputerScienceII/blob/master/resources/uml.md

### Observations:

* If a class `A` `extends` a class `B`:
  * `A` is the subclass/child class/derived
  * `B` is the superclass/parent class/
* Inheritance defines an "is-a" relationship
  * A `Dog` *is-a* `Animal`
  * A `Cat` *is-a* `Animal`
  * An `Animal` is not necessarily a `Cat/Dog`
  * It is *never* the case that a `Cat` is-a `Dog`
* However, you really need to think about your inheritance design
  * Ex: there are some `Persons` that have directed films *and* written books
* Generally you can "prefer composition over inheritance"
  * Perfect use case: Director/Author
* Three types of relations:
  * Covariant relationship: you can treat a subclass as a super class (Dog **is an** Animal); ALWAYS safe
  * Contravariant relationship: you can *sometimes* treat a subclass as a superclass (an Animal is sometimes a Dog); sometimes safe
  * Invariant relationship: a cat is never a dog, a dog is never a cat, NEVER safe
* Sometimes it is okay to check before you perform a contravariance...
  * You can use `instanceof` keyword to determine if it is of the appropriate type
  * HOWEVER: you generally don't want to do this unless you *have* to
  * If you find yourself doing this *often* then it is likely a *bad design*
  * Generally the only time you *have* to use `instanceof` and do a contravariance is when  you are making copies (calling a copy constructor)
* Motivations for inheritance:
  * It provides a way to utilize *code resuse*
  * It provides a way to reduce redundancy
  * It provides a way to reason about and organize your classes into a *hierarchy*
  * It is well-designed if the introduction of a new class does not break any of the other code

### More Inheritance - Pitfalls

An inheritance hierarchy needs to be **very well defined**.
  * The **is-a** relationship *must* be invariant
  * Once a hierarchy has been established and code is not *dependent* on the hierarchy, it *cannot be changed* (not without breaking all other code)

### Demo

* Create some classes to support assets: `Stock` and `Annuity`

#### Observations

* What happens if you have well-defined subclasses, but not-so-well-defined superclasses
  * In Java, you can create an `abstract` class:
  * It is a class that you can inherit from
  * YOu can define methods and state in your class, but also
  * You can define `abstract` methods: methods that have no "default behavior"
  * You provide the behavior in NON-abstract subclasses
  * You cannot instantiate (create) an abstract class even if it has a constructor!
* An `interface` takes this to another level
  * An interface is a pure abstraction: it only defines abstract methods, no state, no behavior
  * You can `implements` multiple interfaces: it provides more flexiblity
  * It means that you can define a collection of abstract methods and then a class can `implements` that interface

### Pitfalls

* Rectangle Problem: Shape, Rectangle, Square
  * Your object relations in an inheritance hierarchy ALWAYS need to follow the is-a relationship
  * Another example: Author, Director, Person hierarchy: it no longer made sense when we had a person that was *both*
  * If you have mutable methods (setters) that can change what an object "is" that violate the hierarchy, it is probably not a well-designed hierarchy
  * This is a violation of the Liskov Substitution Principle (SOLID)
* Antipattern (a bad behavior or bad but common mistake in code): yo-yo antipattern
  * Suppose you have a *deep* inheritance hierarchy: 10 or 20 classes deep
  * It is best to keep a hierarchy *shallow*: limit the number of classes/subclasses
  * Or: "prefer composition over inheritance"
* Diamond Problem Antipattern
  * Animal, Dog, Cat
  * Suppose you could inherit from *multiple classes*
  * You can create "something" that inherits from both `Cat` *and* `Dog`
  * For this reason, MOST OOP languages do not allow multiple inheritance
  * C++ is the only one (that I know of) that does allow this
  * Java does not allow multiple inheritance
  * HOwever: most languages allow you to create more "general" ideas of inheritance: `abstract` classes and `interface`s (pure abstract classes)
  * Java is a *single inheritance hierarch*: all things `extends` from `Object`

# Polymorphism

* Polymorph = multiple form(s)
* Code: one piece of code (variable, method, class) can be written *generically* so that it can be applied to many types
* Other languages:
  * Python: `list.sort()`
  * C: `qsort()`
  * Java: `Collections.sort()`
* It allows you to write generic code, one function/method that can be applied to *any* type

### Subtype Polymorphism

* This is the "classic" or "default" type of polymorphism
* You can treat any subtype as a supertype so that you can apply generic code to it
* Ex: `Robin` can be treated as a `Bird`
* Ex: `ArrayList` can be treated as a `List`

### Method Overloading

* Ex: from C, how many "absolute value" functions are there?
  * `abs()` (integers), `fabs()` (floating point numbers), `labs()`, `llabs()`
  * C does not have function overloading: if you write a function ONLY that function can have that name
  * Python doesn't have it either
  * Java does: how many absolute value functions does the math library have?
* You can have more than one function with the same name but with different types of arguments (inputs/parameters)
* The compiler is "smart enough" to figure out which one you want to call based on the value you give it: `int` it calls the version that takes an integer, `double`: it calls the version that takes a `double`
* This mechanism is known as "static dispatch" (the compiler *dispatches* the function call at *compile time* that's what static means)

### Operator Overloading

* Consider in Java:
  * `String + String`: string concatenation
  * `int + int`: addition
  * `String + int`: string concatenation
* Consider C:
  * `int + int`: addition
  * `char * + char *`: memory address + memory address = some other memory address
* Python:
  * `string + string`: concatenation
  * `int + int`: addition
  * `int + string`: error!  You are forced to use `str(int) + string`
* C and python do not support *operator overloading*
* Operator overloading: an operator (`+`) can have more than one meaning depending on the types you apply it!
* Java: does support *limited* operator overloading
* Generally operator overloading is a bad idea:
  * `Time + Time` mean?
  * `List + List` mean?
  * Python: `list + list`: list concatenation/appending
  * Alternative: Union: $A \cup B$
  * Intersection?
  * Math: coordinate-wise addition (vector addition)
  * Ideally: it should be completely understood, unambiguous
  * C++ is the only language that supports full user-defined operator overloading
    * You *could* redefine `+` to mean subtraction!
    * Even if you do use operator overloading: you have to write a function to do!  So you may as well give that function a name!  A *meaningful* name
* Operator overloading is a thing, but **should be extremely limited**

### Parameterized Polymorphism

* In Java you can create a parameterized variable, class or method
* That one variable can be any type, method can act on any type, class can be associated with any type!
* Demonstration
* Allows you to write one piece of code that can apply to many types
  * `Collections.sort()`
* PECS: Producer Extends, Consumer Super
  * A collection (list, set) is a *producer* of elements; if you wish to pull them out and do something with them, then you need a "named" parameter: `<T extends Item>` (`T` is the name
  of type type being used)
  * A collection is a *consumer* of elements if you wish to put stuff in: `<? super Item>`: if you don't care about what is in the collection already; you just want to put stuff in

## SOLID Principles

## S = Single Responsibility Principle

* Good encapsulation: a class should represent one thing and represent it well
* Violation: God-Class: the class is responsible for everything, knows everything, does everything
  * Are persons and companies the same thing?
  * Be careful: you still need good modeling and good composition
  * Loading Data, Converting Data, Saving Data
* YAGNI Principle: You Aint Gonna Need It: don't over engineering things
* Avoid "leaky abstractions":
  * Code should not require users to use it in a *specific* way
  * If you were forced to call `initialize_math()` before you called any `Math` function
* Ex: exposing how emails are stored: a particular list, a set, an array, etc.  

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
LinkedList<Integer> numbers2 = new LinkedList<>();

//better:
List<Integer> numbers = new ArrayList<>();
```

* Violation: Rectangle/Square example
* Author, Director, Person


## I = Interface Segregation Principle

* No "client" code (code that uses other code) should depend on methods it does not care about
* Example `ClickEventHandler` is an interface that defines two methods:
  * `onClick()`
  * `onDoubleClick()`
* A bad design would *force* you to implement both of these things even if you didn't want to...
  * `onDoubleClick()` calls `onClick()`
  * NOOP = No Operation
* Instead: all interfaces should be as small as possible



## D = Dependency Inversion Principle

* High-level modules (classes) should not depend on low-level modules
* Ex:
  * You write code that connects to a (Free) MySQL database with very-specific MySQL methods, etc.
  * Now you've grown as a company and need to migrate to MSSQL
  * Throw away all DB code and connect to the MSSQL server: rewrite EVERYTHING
  * Vendor lock: the cost of switching is greater than the cost of staying with the current vendor
  * Instead: you should have written generic code for an *interface* that each one of the databases *implements*

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

* "Inverting" a dependency means creating an interface between the "highlevel" object/class/thing and the low-level library that `implements` it
* In general, you should prefer *loose coupling* so that software components can easily be interchanged



```text















```
