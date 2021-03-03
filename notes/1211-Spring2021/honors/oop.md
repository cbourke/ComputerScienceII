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
  * Inherit state and behavior from its *superclass* and/or
  * *Override* or augment state and behavior to provide more *specific* functionality
* Provides a way to *reuse* code
* Provides a way to *organize* code

In Java:
* We use the `extends` keyword to subclass classes
* If `A extends B`, then `A` is the subclass (child class) of `B` which is the superclass (parent class)
* Subclassing defines an `is-a` relationship

## Polymorphism

* Polymorpism = poly = many, morph = forms
* Code: a variable, method, or class may be applicable to multiple types or classes
  * C: how do you sort? We only used one sorting function, `qsort`
* Polymorphism allows you to write *generic* code: you can write ONE function that can apply to may things

### Subtype Polymorphism

* You can declare a variable that can apply to more than one type of subclass

```java
Robin r = new Robin();
Ostrich o = new Ostrich();

Bird b = r;
//or:
b = o;

b.move(); //if Robin, then it would fly(); 
//if Ostrich, it would run()

//contravariance:
r = b; //r is refering to the Ostrich

```

* In general you want to prefer the most general superclass that you can
* UNless... There is a Very Good Reason to refer to it as the subclass
* Only treat a variable as its subtype if you absolutely need to;
* Ex: when you create copies of objects, they need to be the same type!

### Ad-hoc polymorphism

#### Function Overloading

* In C: what function or functions do you use to compute the absolute value? A: `abs(), fabs(), labs(), llabs()`
* C and other languages that do not support function overloading only ever allow you to use a function name once
* Function overloading allows you to use the same name for multiple functions as long as the types or number of parameters differ

```java

public static void foo(int) { ... }

public static void foo(double) { ... }

public static void foo(double, String) { ... }

Utils.foo(42.5, "hello");
```

* a compiler determines which version to call at *compile time* based on the input parameter types and number of them
* This is known as "static dispatch"

#### Operator Overloading

* In Java: 
  * `String + String` (concatenation)
  * `int + int` (addition)
  * `String + int` (concatenation)
* PHP:
  * `number + number` (addition)
  * `number + string` (addition, type juggling)
  * `string + string` (addition, type juggling)
  * `string . string` (concatenation)
* Python:
    * `String + String` means concatenation
    * `number + number` means addition
    * `number + String` is a runtime error
* BE CAREFUL!
  * `Set + Set` (union)
  * `List + List` (appending, vector addition, union, etc.)
  * `Time + Time` (?!?!)
* Operators have a very well-defined and well understood meaning by most programmers and people
* Using operator overloading removes/changes those well-understood meanings
* Most programming languages limit or don't even allow you to do your own operator overloading (C++ does)
* Even when you overload an operator, you have to write a function to do so.  Why not just use that function?

## Parameterized Polymorphism

* Java allows you to *parameterize* a class, method or variable to be applicable to any type, `T`

```java
//the variable a's value can *vary*
int a;
a = 42;
a = 101;
//BUT, the type of a is fixed: it is always going to be an int

//x is a variable whose *value* can vary
//but T is its TYPE, which can also vary
T x;
``` 

* Effectively makes the variable's *type* also variable
* You essentially want to use parameterization to make as generic of code as possible so that you can apply it to as many different TYPES as you can

## Best Practices & Pitfalls in OOP

### Encapsulation

* Each class should only be responsible for ONE thing
* avoid the god-class antipattern
* Keep member variables `private` unless there is a Very Good Reason&trade; to do otherwise
* THe `final` keyword is your friend: it makes a variable into a constant; once set it can never be changed (doing so is a compilation error)
* Use *patterns* like *copy constructors*
- Don't break encapsulation by locating behavior outside a class

## Abstraction

- Don't use *leaky abstractions*
- poor object design: requires knowledge about the internal workings of a class in order to use it
- Automatic vs manual transmission
- Washing machine: washing may be a series of fill(), spin(), empty(), fill(), spin() empty(), spin() when it could have simply been wash()
- Ex: C strings vs Java Strings
- Ex: `LocalDate` is not leaky: we had to look at the source to find it is 3 ints
- Ex: `List.addAtIndex(x, i)` just works.  Leaky: would require you to 
  `List.shiftAt(i)` to make room, THEN `List.addAtIndex(x, i)`
- Later: array-based lists vs linked-lists

## Inheritance

* Some languages support *multiple inheritance*
  * Animal, Cat, Dog
  * Maybe (don't know why) you want to create a `CatDog`
  * Results in the "diamond problem" if you inherit from multiple classes, which method(s) actually get inherited
  * Ambiguity: you have to *explicitly* code which class you end up inheriting from (C++)
  * In Java: multiple inheritance is not allowed: you can only ever `extends` one class
  * But: Java *does* allow you to implement multiple interfaces
  * Java 8: interfaces can now have a `default` behavior (generally you want to avoid this!)
* Problem: Yo-yo antipattern
  * Deep inheritance hierarchies can get very complicated
  * They cause you to look up and down the hierarchy to make sense of the design
  * Instead: keep your hierarchies shallow OR...
  * "Prefer composition to inheritance"
* Rectangle Problem:
  * Suppose we create classes for `Shape, Square, Rectangle`
  * Usually results from a flawed hierarchy design
  * Semantically, a Square may be changed so that it is no longer a square
  * Instead: do not lock yourself into a rigid hierarchy *if* you objects have the potential to change
  * Make sure your hierarchies are well-thought out and thoroughly tested

## Polymorphism

- avoid operator overloading; prefer well-named methods
- PECS: Producer Extends, Consumer Super
- A collection is a *producer* of elements; you wish to pull them out and do something with them.  You need a minimal amount of information `<T extends BankingAccount>`
- A collection is a *consumer* of elements; you wish to put stuff: `<? super Bar>`: you don't care about what is in the collection already, you only care that you can put a `Bar` into it
- Be sure to make all of your parameterizations goldilocks: make it as general as possible, but not so general that you lose all information

# SOLID Principles

## S = Single Responsibility Principle

* Encapsulation: a class should only have one responsibility
* A `Person` class should be responsible for `Person` things, an `Address`
class should be responsible for `Address` things
* Classes should generally be small
* Large classes = more responsibility = less reusability

## O = Open/Closed Principle

* Every unit (module, class, method) should be *open for extension* and *closed for modification*
* Classical inheritance: extend the behavior in subclasses, but DO NOT modify the behavior in the super class
* If there is no one "default" behavior, make it `abstract` or break the functionality into smaller parts
* Have a good, well-thought out inheritance because it is extremely difficult if not impossible to change later on

## Liskov Substitution Principle

* If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.
* Recall: the Shape-Rectangle-Square problem: having mutable methods that can semantically change an object's type violate the Liskov Substitution Principle

## Interface Segregation Principle

* No client code should be forced to depend on methods it does not use
* You should keep interfaces as small as possible; ie one or two (or zero) methods each
* Example: `ClickEventHandler` interface
* SUppose this interface defined:
  * `onClick()`
  * `onDoubleClick()`
  * forces you to implement both events

## Dependency Inversion Principle

* High-level modules (classes) should not depend on low-level modules
* Both Should depend on abstractions
* Example: say you have 3 libraries on a phone app that compute location
  * Library A:  
`double GPSLocator.getLatitude()`  
`double GPSLocator.getLongitude()`
  * Library B: 
`AndroidLocation AndroidNative.getLocation()`
 (`AndroidLocation.getLatitude(), AndroidLocation.getLongitude()`
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
 
 * You can invert the dependency: define an interface `Locator` that specifies one method: `Location getLocation()`
 * You can implement 3 wrapper classes: `GPSLocator`, `AndroidLocator`, `CellLocator`, each one owns an instance (via composition) of a GPS locator library


```text









```