# Computer Science II - CSCE 156

## Spring 2023

### Introduction to Java

# Basics

* Java is a class-based Objected Oriented Programming language
* Translation: everything in Java is either a `class` or belongs to a class
* Classes are simply units of code that are declared in their own source file: class name is `HelloWorld`, then the source file MUST be `HelloWorld.java`
* Source files are compiled to Java Virtual Machine (JVM) code
* IDEs like Eclipse can hide these details
* Classes are organized in *packages*: packages are simply just directories (folders)
* Packages provide organization of your classes

## Observations

* In Java, all class names *should* be `UpperCamelCased`

```java

package soc.unl;

/**
 *
 * Author: Chris Bourke
 * Date: 2023-01-23
 *
 * This is a basic hello world program.
 *
 * You can write basic <code>like this</code>
 *
 * You can link to stuff using <a href="https://unlcornhacks.com/#/home">CornHacks!</a>
 *
 * @author cbourke
 *
 */
public class HelloWorld {

	public static void main(String[] args) {

		System.out.println("Hello World");

	}

}

```

* Comments:
  * Documentation style comments: `/** ... */`
  * Multiline comments: `/*  ....  */`
  * Single line comment: `//`
  * Avoid CRUFT, code should be self-documenting
* All executable lines of code end with a semicolon: `;`
* Code blocks are denoted with opening/closing curly brackets: `{...}`
* Indentation is important for style, but otherwise whitespace is ignored

Differences:
* In Java there is no memory management!
* Java has automatic garbage collection!
* Portable: you write once, compile once, run anywhere
* Backwards compatible (for the most part)  

# Variables

* Java is *statically typed*: all variables and their *types* MUST be declared before you use them

```java
int x = 42;
double y = 3.14159;
String message = "Goodbye World";

System.out.println(x);
System.out.println(y);
System.out.println(message);

//formatted output:
System.out.printf("x = %d, y = %.2f, message = %s\n", x, y, message);
System.out.printf("x = %10d, y = %10.3f, message = %-100s\n", x, y, message);
```

```text












```
