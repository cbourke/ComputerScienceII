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

# Arrays and Collections

* Java does support basic arrays
* There is no malloc/free: but you do use the keyword `new` to create a new array of a given size
* Arrays are 0-indexed: `arr[0]`, `arr[1]`, `arr[i]`,
* IF there are $n$ elements in `arr` the last one is at `arr[n-1]`
* There is no bookkeeping: the size of an array can be retrieved using `arr.length`
* In Java, there is a default of *zero* for all array elements


```java
double numbers[] = new double[10];
numbers[0] = 3.5;
numbers[8] = 1.25;
numbers[numbers.length-1] = 10.5;

System.out.println( Arrays.toString(numbers) );

//error: results in a ArrayIndexOutOfBoundsException:
//numbers[-1] = 1.5;

//2-D arrays in Java:
double matrix[][] = new double[2][3];

```

* Arrays are terrible, if you can use something else, you should...
* Java has `List` data structures that grow, expand, and have a dozen methods/functions that you can use with them built-in

```java

		//A List is an ordered collection of elements...
		List<Integer> myNumbers = new ArrayList<>();

		//add adds the element to the end of the list
		myNumbers.add(10);
		myNumbers.add(30);
		myNumbers.add(20);

		System.out.println(myNumbers);

		//get the element at index 0
		int x = myNumbers.get(0);
		System.out.println(x);

		//CRUD = Create Retrieve Update Destroy
		//destroy: remove the second element...
		myNumbers.remove(1);
		System.out.println(myNumbers);

		myNumbers.add(5);
		myNumbers.add(15);
		myNumbers.add(25);
		System.out.println(myNumbers);

		//adds 42 at index 4, shoving things over to make room
		myNumbers.add(4, 42);
		System.out.println(myNumbers);

		//you can determine the size of a list using .size():
		int n = myNumbers.size();

		//whole bunch of other useful methods:
		//sublists are inclusive/exclusive on the indices
		List<Integer> onlyPart = myNumbers.subList(3, 5);
		System.out.println(onlyPart);
```

* Java also allows you to use `Set`s
* A set is an *unordered* collection of *unique* elements

```java

		Set<Integer> myNumbers = new HashSet<>();

		//myNumbers.add("Hello");
		Set<String> names = new HashSet<>();
		names.add("Chris");
		names.add("Joe");
		names.add("Jane");

		//add adds the element to the end of the list
		myNumbers.add(10);
		myNumbers.add(30);
		myNumbers.add(20);
		myNumbers.add(40);
		myNumbers.add(5);

		System.out.println(myNumbers);

		//because sets are unordered, you cannot do anything
		// that is index based...
		for(Integer x : myNumbers) {
			System.out.println(x);
		}

		//destroy: remove the second element...
		//1 is not an index, it is the element you want to remove...
		myNumbers.remove(1);
		System.out.println(myNumbers);

		myNumbers.remove(5);
		System.out.println(myNumbers);

		//sets are also *unique* elements...
		//adding something that already exists has no effect
		myNumbers.add(40);
		myNumbers.add(40);
		myNumbers.add(40);
		myNumbers.add(40);
		System.out.println(myNumbers);

		//you can determine the size of a list using .size():
		int n = myNumbers.size();
		System.out.println(n);

		//you can convert lists to sets, sets to lists
		// you can also create deep copies of lists/sets

		//ex: shallow copy:
		Set<Integer> foo = myNumbers;
		System.out.println("=======");
		System.out.println(myNumbers);
		System.out.println(foo);
		foo.add(42);
		System.out.println("=======");
		System.out.println(myNumbers);
		System.out.println(foo);

		//deep copy of a set:
		foo = new HashSet<>(myNumbers);
		System.out.println("=======");
		System.out.println(myNumbers);
		System.out.println(foo);
		foo.add(101);
		System.out.println("=======");
		System.out.println(myNumbers);
		System.out.println(foo);

		//convert a set to list:
		List<Integer> bar = new ArrayList<>(foo);
		System.out.println(bar);

		//convert a list to a set:
		Set<Integer> baz = new HashSet<>(bar);
		System.out.println(baz);
```

```java

		//maps hold keys and values
		// maps map keys to values
		// keys are unique
		// values do not need to be unique

		//ex: map NUIDs to Names...
		Map<Integer, String> nuidToName = new HashMap<>();
		nuidToName.put(35140602, "Chris");
		nuidToName.put(1234, "Jane");
		nuidToName.put(5678, "Joe");

		System.out.println(nuidToName);

		//keys are unique, so this replaces the value
		nuidToName.put(1234, "Cody");
		System.out.println(nuidToName);

		//keys are unique, but values are not...
		nuidToName.put(1122, "Cody");
		System.out.println(nuidToName);

		//nuid to set of email addresses
		Map<Integer, Set<String>> nuidToEmails = new HashMap<>();
		Set<String> myEmails = new HashSet<>();
		myEmails.add("cbourke3@unl.edu");
		myEmails.add("chris.bourke@unl.edu");
		myEmails.add("c-cbourke3@huskers.unl.edu");
		nuidToEmails.put(35140602, myEmails);
		System.out.println(nuidToEmails);

		//retrieve
		String name = nuidToName.get(1234);
		System.out.println(name);

		//retrieve non-existant data...
		name = nuidToName.get(12345678);
		System.out.println(name);

		nuidToName.remove(1234);
		System.out.println(nuidToName);

		//iterating over a map...
for(Integer key : nuidToName.keySet()) {
	System.out.println(key + " maps to " + nuidToName.get(key));
}

```

# Strings

* Java has a `String` class
  * No memory management
	* No Null terminating characters in Java!
	* Automatic concatenation using `+` operator
	* You can also mix types
* Strings in Java are *immutable*: once created you cannot change the contents of the string












```text
















```
