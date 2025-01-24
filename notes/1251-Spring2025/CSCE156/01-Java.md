# Computer Science II - CSCE 156
## Spring 2025
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
 * Chris Bourke
 * chris.bourke@unl.edu
 * 2025-01-22
 *
 * Prints "Hello World" to the standard output.
 */
public class HelloWorld {

	public static void main(String args[]) {

		System.out.println("Hello World");

	}
}

```

* Class names follow a `UpperCamelCasing` convention
* Variables in Java are `lowerCamelCased`
* Everything is *case sensitive*
* Comments:
  * Multiline: `/* ... */`
  * Single line: `//`
  * Doc-style comments: `/**` + vertical line of stars + `*/`
  * Avoid *cruft*, code should be self-documenting
  * For now: we expect author documentation for *every class* and documentation for *non-trivial* methods (more later)

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
    * No null terminating character
    * String concatenation: use the `+` (you can *mix* numbers and strings!)

```java
int x = 42;
double y = 100.5;
char initial = 'C';
String message = "Hello World";

String foo = "Hello" + " " + "World";
String bar = "The value of x is " + x;
System.out.println(bar);
```

## Operations

* You have basic math operations: `+, -, *, /`
* Division with integers leads to *truncation*!!

```java
int a = 10;
int b = 20;
//fails: becomes zero
double c = a / b;
System.out.println(c);

//typecasting: force b to become a double for the purposes
// of division
c = a / (double) b;
System.out.println(c);
```
* You also have integer remainder division: `%`
  * `10 % 3` results in a remainder of 1
	* `10 % 2` results in a remainder of 0
	* `10 % 7` results in a remainder of 3
* Basic Math:
  * The `Math` class is automatically imported into all Java code
	* Use it as: `Math.sqrt()`, `Math.sin()`

```java

		//final creates a read-only constant
		final double EPSILON = 0.000000001;

		double x = Math.PI;

		double y = Math.sqrt(x);
		System.out.println(y);

		y = Math.sin(x);
		System.out.println(y);

		if( Math.abs(0 - y) < 0.000000001) {
			System.out.println("Close enough");
		}
```

# Basic I/O

* Interactive Input: you can read keyboard inputs if you want (but generally you don't want to)

```java
Scanner s = new Scanner(System.in);
System.out.println("enter an integer: " );
int x = s.nextInt();
System.out.println("you entered " + x);
double y = s.nextDouble();
String foo = s.next();
```

* Non-interactive input: you provide *command line arguments* as input to your program
* Standard Output
  * `System.out.println()` prints anything and adds an endline character for you `\n`
	* `System.out.print()`: same but no endline character
	* `System.out.printf()`: print-formatted style

```java

		System.out.println("You entered " + args.length + " arguments");
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
		System.out.println(args[3]);
		System.out.println(args[4]);

		// convert the first argument to an integer:
		int x = Integer.parseInt(args[0]);
		double y = Double.parseDouble(args[1]);
		System.out.println("x, y = " + x + ", " + y);
		System.out.printf("x, y = %d, %f\n", x, y);

		//default: 6 decimals of accuracy
		System.out.printf("%f\n", Math.PI);
		System.out.printf("%.8f\n", Math.PI);
		System.out.printf("%.30f\n", Math.PI);
		//it rounds but does not change the variable
		System.out.printf("%.4f\n", Math.PI);
		//print at least 10 columns:
		System.out.printf("%10.2f\n", Math.PI);
```

# Conditionals

* You have traditional `if`, `else-if` and `if-else-if`

```java

		int huskerScore = 4;
		int wisconsinScore = 0;

		if(huskerScore > wisconsinScore) {
			System.out.println("Huskers Win!");
		} else if(huskerScore < wisconsinScore) {
			System.out.println("Wisconsin Win Somehow!");			
		} else {
			System.out.println("Extra Innings!");			
		}
```

* Numerical Comparison operators: `<, <=, >, >=, !=, ==`, ONLY for numbers!
* YOu cannot use them for strings
* Note: we use `!=` for not equal not `not equal`
* Difference from C: Java has proper `boolean` variables and the keywords `true` and `false`

```java
boolean isStudent = true;
if(isStudent) {
	System.out.println("Free Tickets!");
} else {
	System.out.println("Full Price");
}
```

* And operator: `&&` (not `and`)
  * Is true only if both operands are true
* Or Operator: `||` (not `or`)
  * Is true if one or the other or both operands are true
* Negation Operator: `!` (not `not`)

```text









```
