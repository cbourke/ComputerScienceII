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
* Misc:
  * Java is a *statically* typed language: all variables and their *type* must be declared before you can use them
	* Same scoping rules as C: variables declared in a block ONLY exist in that block!
	* There are no default values specified for variables in Java, you are *forced* to initialize them to some value

# Standard I/O:

Standard output:
  * `System.out.println()` prints a line
  * `System.out.print()` prints without an endline
  * `System.out.printf()` which prints formatted

```java

		int x = 42;
		double y = 3.14159;
		String s = "Chris";

		System.out.printf("%d, %f, %s\n", x, y, s);
		System.out.printf("%d, %.2f, %s\n", x, y, s);
		System.out.printf("%d, %.20f, %s\n", x, y, s);
		System.out.printf("%d, %10.2f, %s\n", x, y, s);
```

Standard input:

```java
System.out.println("Please enter an integer:");

Scanner s = new Scanner(System.in);
int x = s.nextInt();
System.out.println("You entered: " + x);
System.out.printf("You entered: %d\n", x);
double y = s.nextDouble();
System.out.println("You entered: " + y);
```

## CLAs = Command Line Arguments

```java
System.out.printf("You entered %d arguments\n", args.length);
for(int i=0; i<args.length; i++) {
	System.out.printf("args[%d] = %s\n", i, args[i]);
}
//conversion:
int x = Integer.parseInt(args[0]);
double y = Double.parseDouble(args[1]);
double z = x + y;
System.out.println(z);
```

## Mathy Stuff

* Java automatically `import`s the `java.lang.Math` library
* To use it, use the class name, `Math` and a dot to call any math function...

```java
double x = Math.PI;
double y = Math.sqrt(x);
System.out.println(y);
y = Math.sin(x);
System.out.println(y);

```

# Conditionals

* Java has `if`, `if-else` and `if-else-if` statements
* exactly the same as C
* However: Java has real `boolean` variables
* You can use the keywords `true` and `false`, these are *case sensitive*

```java


		boolean isStudent = true;
		if(isStudent) {
			System.out.println("Discount Mav Tickets!");
		} else {
			System.out.println("Minimum $40");
		}

		int mavScore = 3;
		int ohioState = 3;

		if(mavScore > ohioState) {
			System.out.println("Mavs Win!");
		} else if(mavScore < ohioState) {
			System.out.println("St Cloud Wins :(");			
		} else {
			System.out.println("Shoot out");						
		}

```

Complex Statements can be written using logical operators:

* Logical and: `&&`
* Logical or: `||`
* The logical negation: `!`
* Numerical comparisons: `<, <=, >, >=, ==, !=`
* You *cannot* use these for string comparisons!
* Short circuiting just like C:
  * Suppose you have `a && b` and `a` is false: does it matter what the values of `b` is?
	* Suppose you have `a || b` and `a` is true: does it matter what the value of `b` is?

## Loops

* Java supports the usual `for` and `while` loops

```java
int n = 10;

for(int i=0; i<n; i++) {
	System.out.println(i);
}

System.out.println("=======");
int i = 0;
while(i < n) {
	System.out.println(i);
	i++;
}
```

* Generally you use `for` loops when you know how many iterations you are going to go for
* Generally you use `while` loops when you don't know how many iterations to do

```java
int n = 1234567891;

//how many digits are in n?
int counter = 0;
while(n != 0) {
	n = n / 10;
	counter++;
}
System.out.println(counter);
```

* Java also has "enhanced for loops" or "for each loops" (more on that later): "for each" loops

# Arrays and Collections

* java supports basic arrays
* There is no `malloc` and no `free` (no memory management), there are no pointers!
* To create an array you use the `new` keyword

```java
//create a new array called "arr" of size 10
// that can hold integers
int arr[] = new int[10];
arr[0] = 10;
arr[1] = 3;
arr[9] = 42;

for(int i=0; i<arr.length; i++) {
	System.out.println(arr[i]);
}
```

* invalid index values result in a `IndexOutOfBoundsException`
* The default values of an array are zeros (0, 0.0, `false`)
* The size of an array can be determined using `arr.length`
* Actually, arrays suck: once created they cannot grow, they cannot shrink, you have to still manually do a lot of work
* Better Solution: Dynamic Data Structures: `List`, `Set`, `Map`

# Lists

* A list is a data structure that holds elements in an *ordered* matter, allows duplicates and grows/shrinks automatically as you add/remove stuff

```java

		List<Integer> numbers = new ArrayList<>();
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		//allows duplicates:
		numbers.add(30);


		System.out.println(numbers);

		numbers.add(42);

		System.out.println(numbers);

		//add things to a particular index:
		numbers.add(0, 101);

		System.out.println(numbers);

		numbers.add(2, 202);

		System.out.println(numbers);

		//remove via index:
		numbers.remove(0);

		System.out.println(numbers);

		//size:
		System.out.println(numbers.size());

		//retrieve:
		//get the element at index 2:
		int x = numbers.get(2);
		System.out.println(x);
		//it does not remove:
		System.out.println(numbers);

		for(int i=0; i<numbers.size(); i++) {
			int y = numbers.get(i);
			System.out.println(y);
		}
		System.out.println("=====");
		for(int y : numbers) {
			System.out.println(y);
		}
```

# Set

* A set is an unordered collection of elements that does not allow duplicates
* A `Set` in Java is the same: no duplicates, unordered

```java

		Set<String> names = new HashSet<>();
		names.add("Chris");
		names.add("Craig");
		names.add("Michael");
		names.add("Kyle");

		System.out.println(names);

		names.remove("Chris");
		System.out.println(names);

		for(String s : names) {
			System.out.println(s);
		}

		//Convert a set to a list:
		List<String> namesOrdered = new ArrayList<>(names);
		System.out.println(namesOrdered);
		namesOrdered.add("Nico");
		System.out.println(namesOrdered);

		//convert a list to a set:
		List<Double> values = List.of(3.4, 5.6, 7.8, 9.1);
		Set<Double> unorderedValues = new HashSet<>(values);
		System.out.println(unorderedValues);
```

# Maps

* Maps are even better than all of them combined
* Key - value mapping



```text












```
