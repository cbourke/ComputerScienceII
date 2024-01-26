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

## Command Line Arguments

```java
if(args.length != 3) {
	System.err.println("ERROR: expected 3 command line arguments");			
	System.exit(1);
}

//you can get the number of command line arguments using:
int numberOfArguments = args.length;
System.out.println(numberOfArguments);
System.out.println(args[0]);
System.out.println(args[1]);
System.out.println(args[2]);

//convert strings to integers:
int x = Integer.parseInt(args[0]);

//convert strings to doubles:
double y = Double.parseDouble(args[1]);

System.out.println(x);
System.out.println(y);
```

# Conditionals

* You have traditional `if`, `else-if` and `if-else-if`
* Numerical Comparison operators: `<, <=, >, >=, !=, ==`, ONLY for numbers!
* To compare strings you need to use a function: `compareTo()` which orders strings in *lexicographic* order according to the ASCII table, you can also use `compareToIgnoreCase()`
  * It returns 0 if the strings have the same content
  * It returns *someething* negative if `a` comes before `b`
  * It returns *something* positive if `b` comes before `a`
* Java also has a `boolean` variable type and keywords `true` and `false`
* You can create more complex statements using logical operators: "and" and "or"
  * The and operator is `&&` and is true if and only if both of its operands are true
  * The or operator is `||` is true if and only if at least ONE of its operands (or both) are true
	* The negation operator flips the truth value of an expression or boolean variable: `!`

```java

		int hawksScore = 3;
		int oilersScore = 3;

		if (hawksScore > oilersScore) {
			System.out.println("Hawks win!");
		} else if (hawksScore < oilersScore) {
			System.out.println("Hawks lose!");
		} else {
			System.out.println("Overtime");
		}

		String x = "!hello";
		String y = "99";

		if(x.compareTo(y) < 0) {
			System.out.println(x + " comes before " + y);
		} else if(x.compareTo(y) > 0) {
			System.out.println(y + " comes before " + x);			
		} else {
			System.out.println(y + " equals " + x);						
		}

		boolean isStudent = true;
		isStudent = false;

		if(isStudent) {
			System.out.println("Discount of 20%");
		}

		int a = 10;
		int b = 50;
		int c = 30;

		if(b*b - 4*a*c >= 0 && a != 0) {
			//ax^2 + bx + c = 0
			double root1 = (-b + Math.sqrt(b*b - 4*a*c)) / (2*a);
			double root2 = (-b - Math.sqrt(b*b - 4*a*c)) / (2*a);
			System.out.printf("The roots of %dx^2 + %dx + %d are %f and %f", a, b, c, root1, root2);
		} else {
			System.err.println("Cannot do that operation");
		}
```

# Loops

* Java has `for` loops and `while` loops



```











```
