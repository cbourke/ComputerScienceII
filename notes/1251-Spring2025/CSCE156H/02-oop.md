# Computer Science II - CSCE 156H
## Spring 2025
### Object Oriented Programming

## Creating and Designing Classes: Reflection & Demo

* Isotope demo: imperative vs OOP
* Java is a "class"-based object oriented programming language
* Everything in Java is a class or belongs to a class
* A `class` in Java allows you to model a "real world" object or idea or "entity"

## Objects

Formally an object is a general entity characterized by:

1. Identity: an aspect of the object that distinguishes it from all other instances
2. State: a collection of properties (elements, attributes, data, fields, *member variables*)
3. Behavior: the functionality that acts on the object's state/data: what the object does or what services it provides: what methods it has

Observations:

* In Java, identity is determined by implementing the `equals()` and `hashCode()` methods: for now, just auto-generate them
* Communication between objects is done via "message sending": just a fancy way of saying an object's functions are invoked or called
* calling a function is telling an object what to do, not *how* to do it (that's the object's responsibility)
* Communication is facilitated by the object's *interface*: the `public` methods that are available
  * You can still have `private` methods, but they are not part of the interface
  * `private` methods *are* useful
* Objects are *constructed* rather than just assigned
  * In Java, constructor methods are named after the class; the have no return value, and can take any number of parameters
  * If you do not define a constructor, a no-argument (no parameter) default constructor is provided by Java
  * If you do define a constructor, the default goes away; you an always explicitly define it
* Common patterns:
  * Make it so that specific constructors call more general constructors (DRY = DOn't Repeat Yourself)
  * YAGNI = You Ain't Gonna Need It: don't over-engineer your objects
  * You break real-world objects or ideas into smaller and smaller parts until...
    1. it is representable by a built-in type: `String, int, double` or
    2. An object already exists for you
* Generally your objects are *composed* of other objects or data
  * Ex: `Book` class is composed of `String`s, `int`s, `double`s and `Person` (it "owns" an instance of the `Person` class)
  * This is known as *composition*
* Avoid the "god anti-pattern": a class should have a *SINGLE* purpose or SINGLE representation: Single Responsibility Principle

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
  * Who cares?  THe representation is *internal* to the class
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

## Demo:

* Design classes to support assets: a stock, an annuity
  * Stocks: shares and share price
  * Annuity: monthly payment and number of years


### Observations

* What happens if you have well-defined subclases but not-so-well-defined superclasses
* In Java you can create an `abstract` class
  * You use the keyword `abstract`
  * Then you can have `abstract` methods: methods with no *default* behavior, no method body, etc.
  * Then subclasses that inherit this `abstract` method, MUST provide an implementation
  * Even if you have a constructor, you *cannot* instantiate an abstract class!
* In Java you can also create a *pure* abstract class: `interface`
  * An interface allows you to define any number of methods with NO default behavior
  * This allows classes to `implements` multiple interfaces

### More Inheritance - Pitfalls

An inheritance hierarchy needs to be **very well defined**.
  * The *is-a* relationship *must* be invariant
  * Once a hierarchy has been established and code is now *dependent* on it, it *cannot be changed* without substantially breaking other code


#### Rectangle Problem:
  * Shape, Rectangle, Square
  * Your object relations in an inheritance hierarchy ALWAYS need to follow the is-a relationship
  * Our example: Author, Director, Person: not a great design because it doesn't allow a person that is both
  * Our example: Author, Director, Person: not a great design because it doesn't allow a person that is both
  * This is an example of a violation of the Liskov Substitution Principle (SOLID)  
  * Prefer composition over inheritance

#### Yo-yo Antipattern
  * Antipattern = common, but bad habit that you see in code
  * Pattern: common pattern that is useful and good design in code
  * Deep inheritance hierarchies (20 classes/levels) deep are bad
  * Keep your hierarchy shallow, OR
  * "Prefer Composition over Inheritance": it gets you the same or similar benefits of code reuse but without locking you into the hierarchy!

#### Diamond Problem/Antipattern
  * Really only C++ allows you to do this: you can inherit from *multiple* classes
  * The problem: what gets inherited from what superclass?
  * Java does not allow this (good), most programming languages do not allow this
  * Even in C++: you end up having to disambiguate it: you have to explicitly write code that you inherit from one or the other
    * Anyone using it has to RTM
    * You have to write code anyway, defeats hte purpose of code reuse

# Polymorphism

* Poly = Many, Morph = Form
* Code: one piece of code (variable, method or class) can be written generically and applied to many types
* C: `qsort()` can be used for any type because it uses `void *`
* Python: `list.sort()` as well
* Any language will only have one sort method in general which can be applied to any type
* All you have to do is *configure* it with a comparator

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
  * `String + String`: `+` means concatenation
  * `int + int`: `+` means addition
  * `String + int`: `+`: means (conver the `int` to a string, then...) concatenation
* Python:
  * `string + string`: concatenation
  * `int + int`: addition
  * `string + int`: ERROR, you need to use `string + str(int)`
* C:
  * `char * + char *`: memory addition: weird things happen
  * C does not have ANY operator overloading
* C++: full operator overloading: you can write code to redefine ANY operator.  ex: you redefine `+` to mean subtraction
* This is bad in general:
  * `List + List`:
    * Appending (python)
    * Coordinatewise vector addition
    * $A \cup B$: union
  * `Time + Time`: meaningless
* If you do use operator overloading:
  * You have to write a method/fucntion to define it anyway
  * If you're writing a function, *give it a meaningful name* and use it!

### Parameterized Polymorphism

* Constructor vs Destructor/Destroyer or "clean up" function
* PECS: Producer Extends, Consumer Super
  * A collection (list, set) is a *producer* of elements; if you wish to pull them out and do something with them, then you need a "named" parameter: `<T extends Item>` (`T` is the name
  of type type being used)
  * A collection is a *consumer* of elements if you wish to put stuff in: `<? super Item>`: if you don't care about what is in the collection already; you just want to put stuff in

## SOLID Principles

* STUPID Principles: https://williamdurand.fr/2013/07/30/from-stupid-to-solid-code/

## S = Single Responsibility Principle

* Good encapsulation: a class should represent one thing and represent it well
* Violation: God-Class: the class is responsible for everything, knows everything, does everything
* You can still violate it by having it do 2 things
  * `Person` vs `Address`
  * Load data, Convert it, Save data
* YAGNI Principle: You Aint Gonna Need It: don't over engineering things
  * `Person`: `UUID, name, phone, ...`
* Be careful: you still need good encapsulation and good **composition**
  * `Company`: `UUID, name, ...`: `primaryContact`: what type is that? `Person`
* Avoid "leaky abstractions":
  * Code should not require users to use it in a *specific* way
  * If you were forced to call `initialize_math()` before you called any `Math` function
  * Ex: emails in a person class

## O = Open/Closed Principle

* Every unit (module, class, method) should be *open for extension* and *closed for modification*
* Classical inheritance:
  * Superclasses provide a *general* behavior that should *NOT* be changed otherwise:
    * It must have been a bad design to begin with OR
    * changing it breaks all other code
* Violation: `instanceof` to determine business logic (the value of an account, the cost of items)

```java
if(object instanceof Stock) {
  //do stock thiings here
} else if(object instanceof Annuity) {
  //do annuity things here
}

if(object.getType().equals("Stock")) {
  //do stock thiings here
} else if(object.getType().equals("Annuity")) {
  //do annuity things here
}

```

* The only time you *should* (in fact have to) use the `instanceof` keyword or type checks: when you call a constructor

* In Java: you can enforce this principle using the `final` keyword:
  * `final` variables mean you are making them constant
  * A `final` class cannot be extended: ex: `Integer`
  * A method can be made `final` and subclasses cannot override it!

  ## Liskov Substitution Principle

  * If S is a subtype of T then objects of type T may be replaced with objects of type S without altering any of the desired properties of T.
  * Subtype polymorphism!

  ```java
  ArrayList<Integer> numbers = new ArrayList<>();
  LinkedList<Integer> numbers2 = new LinkedList<>();

  List<Integer> foo = numbers; //okay, should NOT break anything!
  foo = numbers2; //also should not break
  ```

  * Violation: Rectangle/Square example
  * Author, Director, Person

  ## Interface Segregation Principle

  * No "client" code (code that uses other code) should depend on methods it does not care about
  * Example `ClickEventHandler` is an interface that defines two methods:
    * `onClick()`
    * `onDoubleClick()`
  * A bad design would *force* you to implement both of these things even if you didn't want to...
    * I just want to have a button that people click and I don't care about when they double click
  * Instead: all interfaces should be as small as possible

  ## Dependency Inversion Principle

  * High-level modules (classes) should not depend on low-level modules
  * Ex:
    * YOu write code that connects to a (Free) MySQL database with very-speciic MySQL methods, etc.
    * Now you've grown as a company and need to migrate to MSSQL
    * Throw away all DB code and connect to the MSSQL server: rewrite EVERYTHING
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

  * "Inverting" a dependency means creating an interface between the "highlevel" object/class/thing and the low-level library that `implements` it
  * In general, you should prefer *loose coupling* so that software components can easily be interchanged



```text














```
