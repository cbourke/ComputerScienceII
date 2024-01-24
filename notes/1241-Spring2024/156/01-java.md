# Computer Science II - CSCE 156
## Spring 2024
### Introduction to Java

# Basics

## Overview

* Java is a class-based Objected Oriented Programming language
* Translation: everything in Java is either a `class` or belongs to a class
* Classes are simply units of code that are declared in their own source file: class name is `HelloWorld`, then the source file MUST be `HelloWorld.java`
* Source files are compiled to Java `.class` files that the
Virtual Machine (JVM) can run
* IDEs like Eclipse can hide these details
* Classes are organized in *packages*: packages are simply just directories (folders)
* Packages provide organization of your classes into folders and subfolders

## Hello World

```java
package unl.soc;

/**
 * Author: Chris Bourke
 * Date: 2024-01-22
 *
 * This program prints <code>"Hello World"</code> to the standard
 * output.
 */
public class HelloWorld {

	public static void main(String args[]) {

		System.out.printf("Hello World\n");

	}

}

```

* Class names follow a `UpperCamelCasing` convention
* Package declaration at the top allows you to organize code into folders and subfolders
* Everything is *case sensitive*
* Comments can use:
  * `/* multi line comments */`
  * Single line comments use `//`
  * use doc-style comments on all non-trivial classes and methods (functions)
  * Avoid *cruft*, code should be self-documenting
* All executable lines of code end with a semicolon: `;`
* Code blocks are denoted with opening/closing curly brackets: `{...}`
* Indentation is important for style, but otherwise whitespace is ignored
* Other observations:
  * No memory management!
  * Java has automatic garbage collection!
  * Portable: you write once, run anywhere
  * Backwards compatible (for the most part)  

# Variables

* Java is a *statically typed* language
  * You must declare a variable and its type before you use it
  * You cannot assign an invalid (different type) of value to (say) an integer
* Beware of limitations:
  * An `int` can represent at most $2^{31} - 1 = 2,147,483,647$
  * A `double` has at most 16-17 digits of accuracy
	* `char` - a single *unicode* character; but generally ignore because...
	* `String` is a built-in type:
    * No memory management
    * NO null terminating character
    * String concatenation: use the `+` (you can *mix* numbers and strings!)

```java
int x = 2147483647;
double y = 3.14159;
char c = 'C';
String message = "Hello how are you today?";

//not allowed:
//x = "Hello";
```

## Operations

* You have the basic math operators: `+ - * /`
* Careful: division with integers leads to truncation
* In Java: you have to be careful with integer truncation; an integer divided
  by an integer is an integer so `10 / 20` results in 0
* You can use explicit type casting to solve this: `a / (double) b`
* Basic Math:
  * You have the `Math` class/library
  * You can use it using `Math.sqrt(x)` or `Math.sin(x)`

## Output Formatting

```java

		double pi = 3.14159;

		System.out.println(pi);

		System.out.printf("%f\n", pi);
		System.out.printf("%.2f\n", pi);
		System.out.printf("%.4f\n", pi);
		System.out.printf("%f\n", pi);
		//a minimum of 20 columns with 4 decimals of accuracy:
		System.out.printf("%20.4f\n", pi);

		int x = 1234;
		System.out.printf("%d\n", x);
		System.out.printf("%20d\n", x);
		System.out.printf("%020d\n", x);
		System.out.printf("%-20d\n", x);

```

```











```
