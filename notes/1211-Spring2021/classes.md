# Computer Science II - CSCE 156/156H

## Spring 2021

### Java Classes/Objects

* Java is a class-based object oriented programming language
* Everything in Java is either a class or belongs to a class
* A class in Java:
  * allows you to *group* multiple pieces of data together into one logical unit (*encapsulation*)
  * it is essentially a blueprint for creating *instances* of the class
  * each instance has its own member variable values
  * to create instances of a class you *instantiate* them using a constructor (a special method that specifies how an instance is create/initialized) and the `new` keyword
* Constructors are used to specify how to create objects
  * By default, a no-argument "default" constructor is provided by Java
  * The moment you define your own constructor, the default constructor goes away
  * you can define and implement as many constructors as you want
  * In general, constructors should call other constructors instead of repeating code (DRY Principle = Don't Repeat Yourself)
  * you can easily generate both getters and setters in your code using your IDE (Eclipse)
* In general, you should limit the scope of variables
  * this limits the possible changes in your program
  * Thus, in general, your variables should all be `private`: so nothing outside your class can access or change them 
  * You can then control access through getters and setters
  * You can generally auto-generate this boilerplate code
