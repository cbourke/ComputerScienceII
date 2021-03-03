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

## Best Practices & Pitfalls 

### Encapsulation

* Single responsibility: classes should only ever be responsible for one thing.
* In general, keep member variables `private` unless there is a Very Good Reason&trade;
* In fact, you should prefer *immutable* objects, the `final` keyword is your friend
* Use alternatives: use *copy constructors*  use *patterns*
* Don't break encapsulation by locating behavior outside of a class

## Abstraction

* Don't use *leaky abstractions*
* Poor design requires a "user" to know the internal workings of a class in order to use it
* Washing machine: `wash()`, it could be a series of `fill(), spin(), empty(), fill(), spin(), empty(), spin()`
* Ex: strings in C versus strings in Java
* `LocalDate` is not leaky: we simply had to use it
- Ex: `List.addAtIndex(x, i)` just works.  Leaky: would require you to    `List.shiftAt(i)` to make room, THEN `List.addAtIndex(x, i)`

## Inheritance

* Some languages support *multiple inheritance* 
  * Leads to the "diamond problem": where do you inherit your behavior from?
  * Animal, Cat, Dog
  * Maybe (don't know why) you want to create a `CatDog`
* Problem: yo-yo antipattern
  * Deep inheritance hierarchies can get very complicated
  * Deep hierarchies cause you to look up and down to make sense of the design
  * Instead: keep your hierarchies shallow
  * Better yet: "prefer composition to inheritance"
* Rectangle Problem:
  * Suppose we have classes to model a `Shape, Square, Rectangle`
  * Usually results from a flawed hierarchy design
  * Semantically, a Square may be changed so that it is no longer a square
  * Instead: do not lock yourself into a rigid hierarchy *if* you objects have the potential to change
  * Make sure your hierarchies are well-thought out and thoroughly tested

  ## Polymorphism

  - avoid operator overloading; prefer well-named methods
  - PECS: Producer Extends, Consumer Super
    - A collection is a *producer* of elements; you wish to pull them out and do something with them.  You need a minimal amount of information `<T extends Foo>`
    - a collection is a *consumer* of elements: you wish to put stuff into the collection (but you don't really care what is already in it): use `<? super Bar>`
    - Make parameterizations as general as you can, but not too general

# SOLID principles

## S = Single Responsibility Principle

* Good encapsulation: a class should only have one responsibility
* don't have god classes
* A `Person` class should be responsible for `Person` things, an `Address`
class should be responsible for `Address` things
* DRY = Don't Repeat Yourself

## O = Open/Closed Principle

* Every unit (module, class, method) should be *open for extension* and *closed for modification*
* Classical inheritance: extend the behavior in subclasses, but DO NOT modify behavior in the superclass
* In general, you can add fields or methods to a subclass, but you don't want to change the methods/fields in a superclass
* Superclasses provide more general behavior that should NOT be modifiable.  
* If there is no "general" or "default" behavior, then DONT DEFINE IT: instead make your class/methods `abstract` 
* Alternative: break functionality down into smaller parts
* Have a good, well-thought out inheritance because it is extremely difficult if not impossible to change later on

## Liskov Substitution Principle

* If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.

## Interface Segregation Principle

* No client code should be forced to depend on methods it does not use or care about
* Example: `ClickEventHandler` interface
* Suppose that interface deifned TWO methods:
  * `onClick()`
  * `onDoubleClick()`
* You are *forced* to define what happens in both events
* When designing an interface, keep them as simple as possible: maybe only 1 or two (or NO) methods

## Dependency Inversion Principle

* High-level modules (classes) should not depend on low-level modules
* Both should depend on abstractions
* Example: say you have 3 libraries on a  phone app that compute location
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
    return new Location(Utils.radiansToDegrees(CellTowerGPS.getLatitudeRad()), Utils.radiansToDegrees(CellTowerGPS.getLongitudeRad()));
 }
```

* The above is the "client" code: high level module 
* THe libraries are the low level modules
* We have a *tight coupling* with the high/low modules
* Alternative: define an interface: `Location getLocation()`
* You can then implement 3 "wrapper" classes that handle the complexity of each: 
`GPSLocator`, `AndroidLocator`, `CellLocator`, each `implements LocationGetter`
* Each one may "own" an instance of their lower library library via composition, 
* logic to convert the library's location representation into *our* `Location` representation is inside each wrapper class
* Now our client code doesn't have to worry about low level modules: we only have to program toward `Location getLocation()`
* Another example: database connectivity

```text









```