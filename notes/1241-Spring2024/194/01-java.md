# Computer Science II - ECEN 156
## Spring 2024
### Introduction to Java

# Basics

* Java is a class-based Objected Oriented Programming language
* Translation: everything in Java is either a `class` or belongs to a class
* Classes are simply units of code that are declared in their own source file: class name is `HelloWorld`, then the source file MUST be `HelloWorld.java`
* Source files are compiled to Java Virtual Machine (JVM) code
* IDEs like Eclipse can hide these details
* Classes are organized in *packages*: packages are simply just directories (folders)
* Packages provide organization of your classes

## Hello World

```java
package uno.ece;

/**
 * <p>Author: Chris Bourke</p>
 * <p>Date: 2024-01-23</p>
 *
 * Prints the message "Hello World" to the standard output.
 *
 */
public class HelloWorld {

	public static void main(String args[]) {

		System.out.println("Hello World");		


	}


}
```

## Overview

C-style syntax:
  * Semicolons end executable lines of code
  * You have blocks of code denoted using curly brackets
  * Comment:
    * Single line comments: `//`
    * Multiline comments: `/* ... */`
    * Doc-style comments: author documentation, class documentation, method (function) documentation for all *non-trivial* functions
  * Good style: whitespace generally does not matter, but you should indent properly
  * Double quotes denote strings

Differences:
  * In Java there is no memory management!
  * Java has automatic garbage collection!
  * Portable: you write once, compile once, run anywhere
  * Backwards compatible (for the most part)  

# Variables

* Java supports:
  * `int` 32-bit integers (Max: 2147483647, min: -2147483648)
	* `double` floating point numbers with 64 bits of precision = 16 digits of precision
	* `char` yes, but why use them?
	* Use `String`s instead!
* Java has truncation with integer division just like C
* Supported operators: `+ - * /` and `%`
  * The `%` operator is integer division with a remainder

# Standard I/O:

Standard output:
  * `System.out.println()` prints a line
  * `System.out.print()` prints without an endline
  * `System.out.printf()` which prints formatted

Standard input:
```java
Scanner s = new Scanner(System.in);

System.out.println("Please enter a number:");
double x = s.nextDouble();
System.out.println("You entered " + x);
```

### CLAs = Command Line Arguments

```java
//expect 3 command line arguments: integer, double, string
if( args.length != 3 ) {
  System.err.println("Expected an integer, double and string");
  System.exit(1);
}

int a = Integer.parseInt(args[0]);
double b = Double.parseDouble(args[1]);
String s = args[2];

System.out.printf("I read %d, %f and %s\n", a, b, s);
```

## Mathy Stuff

* Java automatically `import`s the `java.lang.Math` library
* To use it, use the class name, `Math` and a dot to call any math function...

```java

		int x = 0;
		double y = Math.sqrt(x);

		y = Math.log(x);

		//exception: y = 1 / x;

		System.out.println(y);

		y = y * 0;

		System.out.println(y);
```

# Conditionals

* Java has `if`, `if-else` and `if-else-if` statements
* exactly the same as C
* However: Java has `boolean` types built in
* You can use the keywords `true` and `false`, these are *case sensitive*

```java

		int hawksScore = 2;
		int krakenScore = 2;

		if(hawksScore > krakenScore) {
			System.out.println("Hawks Win!");
		} else if(hawksScore < krakenScore) {
			System.out.println("Seattle Win!");
		} else {
			System.out.println("Going to OT!");
		}

		boolean isStudent = false;
		if(isStudent) {
			System.out.println("Free Tickets!");
		} else {
			System.out.println("Pay $20");
		}
```

Complex Statements can be written using logical operators:
  * The logical and: `&&`
  * The logical or: `||`
  * The logical negation: `!`
  * Numerical Comparison: `<, <=, >, >=, ==, !=`
  * Short circuiting just like C:
    * IF the first value in an or statement is true, the second value is ignored
    * IF the first value in an AND statement is false, the second value is ignored
  * YOu cannot use comparison operators with `String`s

```java
String a = "123";
String b = "99";

if(a.compareTo(b) < 0) {
  System.out.println(a + " comes before " + b);
} else if(a.compareTo(b) > 0) {
  System.out.println(b + " comes before " + a);			
} else {
  System.out.println(a + " is equal to " + b);						
}
```

* You can use `a.compareToIgnoreCase(b)`

# Loops

* Java supports the usual `for` and `while` loops
* Generally you use `for` loops when you know how many iterations you are going to go for
* Generally you use `while` loops when you don't know how many iterations to do

```java

		int n = 10;
		for(int i=0; i<n; i++) {
			System.out.println(i);
		}

		int i = 0;
		while(i < n) {
			System.out.println(i);
			i++;
		}

		int x = -123;
		int original = x;
		//determine how many digits are in x...
		int count = 0;
		while(x != 0) {
			count++;
			x /= 10;
		}
		System.out.println("There are " + count + " digits in " + original);
```

* Java also has "enhanced for loops" or "for each loops"

```java
int primes[] = {2, 3, 5, 7, 11, 13, 17, 19, 23};

int total = 0;
for(int x : primes) {
  total += x;
}
System.out.println("Sum of the first " + primes.length + " primes is " + total);
```

```text
















```
