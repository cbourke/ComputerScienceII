# Computer Science II - ECEN 156
## Spring 2025
### Introduction to Java

# Basics

* Java is a class-based Objected Oriented Programming language
* Translation: everything in Java is either a `class` or belongs to a class
* Classes are simply units of code that are declared in their own source file: class name is `HelloWorld`,
* Style: all classes are `UpperCamelCased`
* Source files are compiled to Java Virtual Machine (JVM) code
* Java: write once, compile once, run anywhere
* IDEs (Integrated Development Environments) like Eclipse can hide these details
* Classes are organized in *packages*: packages are simply just directories (folders)
* Packages provide organization of your classes

```java
package uno.ece;

/**
 * <h1>Chris Bourke</h1>
 * <h2>2025-01-21</h2>
 *
 * <p>
 * A basic Hello World Program
 * </p>
 *
 * For the history of Hello World see
 * <a href="https://en.wikipedia.org/wiki/%22Hello,_World!%22_program">here</a>
 */
public class HelloWorld {

	/**
	 * This method (function) computes the sum of
	 * two values, <code>a</code> and <code>b</code>.
	 *
	 * @param a the first value
	 * @param b the second value
	 * @return the sum of the two values
	 */
	public static int add(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {

		System.out.println("Hello World!");
		System.out.println("Goodbye WOrld!");

	}
}
```

## Overview

C-style syntax:
  * Semicolons end executable lines of code
  * You have blocks of code denoted using curly brackets `{...}`
  * Comment:
    * Single line comments: `//`
    * Multiline comments: `/* ... */`
    * Doc-style comments: author documentation, class documentation, method (function) documentation for all *non-trivial* functions
  * Good style: whitespace generally does not matter, but you should indent properly
  * Fix formatting: shift-command-f (mac), windows?
  * Double quotes: denote strings, single quotes: single `char` values

Differences:
  * In Java there is no memory management!
  * Java has automatic garbage collection!
  * Portable: you write once, compile once, run anywhere
  * Backwards Compatible!

# Variables

* Java supports:
  * `int`: 32-bit integer (signed 2-s complement): (Max: 2147483647, min: -2147483648)
  * `double` floating point numbers with 64 bits of precision = 16 digits of precision
  * `char` yes, but why use them?
  * Instead: use `String`s
* Java has truncation with integer division just like C

```java
int a = 10;
int b = 20;
double c = a / b;
//prints 0.0 because of truncation:
System.out.println(c);
//type casting corrects the issue:
c = a / (double) b;
//prints 0.5
System.out.println(c);
```
* Basic Operators:
  * `+, -, *, /`
  * Remainder Division: `%`

```java
int a = 9;
int b = 2;
//gives the remainder of a / b:
int c = a % b;
System.out.println(c);
```

* String concatenation:
  * You can combine (concatenate) two strings or a string any *anything else* using `+`

```text









```
