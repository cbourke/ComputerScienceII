# Computer Science II - CSCE 156
## Spring 2026
### Introduction to Java

# Basics

* Java is a class-based Objected Oriented Programming language
* Translation: everything in Java is either a `class` or belongs to a class
* Classes are simply units of code that are declared in their own source file: class name is `HelloWorld`, then the source file MUST be `HelloWorld.java`
* Source files are compiled to Java `.class` files that then run on the JVM = Java Virtual Machine
* IDEs like Eclipse hide all of these details
* Classes are organized in *packages*: packages are simply just directories (folders)
* Packages provide organization of your classes into folders and subfolders

## Hello World Example

```java
package unl.soc;

/**
 * This is a basic hello world program
 *
 * ChrisBourke
 * 2026-01-12
 */
public class HelloWorld {

	public static void main(String args[]) {

		System.out.println("Hello World!!");
	}

}
```

## Comments

* Multiline comments begin with `/*` and end with `*/`
* Ex

```java
/* This is a multi line comment
   it can span as many lines
   as you want
 */
```

* Single line comments: `//`
* Doc style comments: `/**` and have a vertical line of starts + end with `*/`
* For now: all programs require an author header in doc-style comments

## Misc

* All executable lines end with a semicolon `;`
* Strings are denoted using double quotes: `"this is a string"`
* Standard output:
  * `System.out.println` prints with an endline character for you
  * `System.out.print`
  * `System.out.printf` prints in a formatted manner

## Variables

* Java is a statically typed language: you have to declare a variable before you can use it
* YOu also have to declare the variable's *type*
  * `int` integer (at most 2147483647, at least 2147483648)
  * `double` is a floating point number (fractional) that has 17 digits of accuracy
  * `char` a single Unicode character
* Variable names can contain lower case, uppercase letters and numbers, also `_`, etc.
  * By convention: all variables should be `lowerCamelCase`
  * Use *descriptive* variable names, avoid `x, y, foo`, etc.
  * Good examples: `numberOfStudents`, `upperBound` `isDone`
* Generally:
  * Avoid *cruft* code should be self-documenting
  * For now: we expect author documentation for *every class* and documentation for *non-trivial* methods (more later)
* In Java "strings" are a built-in type
  * Use `String`
  * No memory management
  * No null terminating character
  * String concatenation: use the `+` (you can *mix* numbers and strings!)

```java
// TODO: declare and print some variables
		int a = 42;
		double x = 4.125;

		//cannot do:
		//int b = 4.5;

		//yes you can:
		//double y = 42;

		char initial = 'C';

		System.out.println(a);
		System.out.println("a = " + a);

		System.out.println(x);

		//you can use formatted output:
		System.out.printf("x = %f\n", x);
		//for the purposes of printing ONLY, it will
		//round; the variable is NOT changed
		System.out.printf("x = %.2f\n", x);
		System.out.printf("x = %.20f\n", x);

		System.out.printf("x = %.20f\n", Math.PI);
		//more math stuff:
		//all the usual math functions are in the
		//Math class
		double y = Math.sin(Math.PI);
		System.out.println(y);
		y = Math.cos(Math.PI);
		System.out.println(y);

		y = Math.log(10);
		System.out.println(y);

		y = Math.log10(10);
		System.out.println(y);

		y = Math.sqrt(2.0);
		System.out.println(y);

		/*
		 * CRUFT, get rid of it!
		 Old debugging code:
		 y = Math.sqrt(10);
		 System.out.println(y);
		 */

		String firstName = "Chris";
		String lastName = "Bourke";
		String fullName = firstName + " " + lastName;

		System.out.println(fullName);
```

## Operations

* You have basic math: `+, -, *, /`
* However, you have *integer truncation* with division

```java
int a = 10;
		int b = 20;
		int c = a / b;
		System.out.println(c);

		double x = 10;
		double y = 20;
		double z = x / y;
		System.out.println(z);

		//fix:
		z = (double) a / b;
		System.out.println(z);
```

* We also have integer remainder division: `%`
  * `10 % 3` results in 1
  * `10 % 2` results in 0
  * `10 % 7` results in 3
* Often you want to use *constants* ("variables" that cannot be changed once set), use the modifier `final`

# Basic I/O

* Interactive Input: you can read keyboard inputs if you want (but generally you don't want to)
* NExt time: command line arguments (CLAs)

```text














```
