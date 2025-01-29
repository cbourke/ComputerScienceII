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

```java

		double a = 10;
		double b = 20;
		double c = 30;

		// x = -b +- sqrt(b^2 - 4ac) / 2a
		if (a != 0 && b * b - 4 * a * c >= 0) {
			double root1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			double root2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		} else {
			System.out.println("Can't handle that, sorry");
		}
```

# Loops

* Java has `for` loops and `while` loops
* Java also has "enhanced for loops"

```java

		int n = 10;
		for(int i=0; i<n; i++) {
			System.out.println(i);
		}

		System.out.println("=========");
		int i = 0;
		while(i < n) {
			System.out.println(i);
			i++;
		}


		//goal: given a value n, determine how many
		// digits are in n
		//TODO: fix this code for zero and negative values
		int n = 0;
		int originalValue = n;
		int digitCount = 0;
		while(n > 0) {
			//divide by 10
			n /= 10;
			//update a counter
			digitCount++;
		}
		System.out.printf("There are %d digits in %d\n", digitCount, originalValue);


		//FizzBuzz:
		// print numbers 1 to 100
		//  except: if the number is divisible by 3 then print "fizz"
		//          if the numbe ris divisilbe by 5 then print "buzz"
		//          if divisible by *both* 3 and 5: print "fizzbuzz"
		int n = 100;
		for(int i=1; i<=n; i++) {
			if(i % 15 == 0) {
				System.out.println("fizzbuzz");
			} else if(i % 3 == 0) {
				System.out.println("fizz");
			} else if(i % 5 == 0) {
				System.out.println("buzz");
			} else {
				System.out.println(i);
			}
		}
```

# Arrays and Collections

* Java has basic arrays
* They are 0-indexed
* They are created with a *fixed size*, they cannot grow, shrink, etc.
* Only ever use arrays *if you have to*
* There is no memory management: no `malloc`, no `free`, no pointers, etc.
* ONce you start using: lists, sets, maps, you will *never* want to use an array again!

```java
int numbers[] = new int[10];
numbers[0] = 42;
numbers[1] = 101;
//last element:
numbers[9] = 202;
//other elements not initialized: default value of 0
for(int i=0; i<numbers.length; i++) {
	System.out.printf("arr[%d] = %d\n", i, numbers[i]);
}

//enhanced for loop:
// if you don't care about the index variable i, you can do:
for(int x : numbers) {
	System.out.println(x);
}
```

```java

		//2-D Arrays
		//matrices, table

		//create an integer matrix/table of size 3 x 5:
		int mat[][] = new int[3][5];
		for(int i=0; i<mat.length; i++) { //for each row
			for(int j=0; j<mat[i].length; j++) { //for each column
				mat[i][j] = i*j + 10;
			}
		}

		for(int i=0; i<mat.length; i++) { //for each row
			System.out.print("[ ");
			for(int j=0; j<mat[i].length-1; j++) { //for each column
				System.out.print(mat[i][j] + ", ");
			}
			System.out.print(mat[i][mat[i].length-1]);
			System.out.print(" ]\n");
		}

		for(int i=0; i<mat.length; i++) {
			System.out.println( Arrays.toString(mat[i]) );
		}
```

# Lists

* A dynamic ordered data structure that automatically grows/shrinks as you add stuff/remove stuff from it!
* They hold any type of value/variable
* They have dozens of methods that you can use to manipulate them
* Still: 0-indexed, holds stuff in an *ordered* manner and allow duplicates

```java

		List<Integer> numbers = new ArrayList<>();
		//nope: numbers.add("Hello");
		//.add adds the element to the end of the list
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		System.out.println(numbers);

		numbers.add(40);
		System.out.println(numbers);

		//adds 42 to index 1:
		numbers.add(1, 42);
		System.out.println(numbers);

		//nope, out of bounds:
//		numbers.add(100, 42);
//		System.out.println(numbers);

		//remove element from index 3
		numbers.remove(3);
		System.out.println(numbers);

		//get the element at index 2:
		int x = numbers.get(2);
		System.out.println(x);
		System.out.println(numbers);

		//get the size of a list:
		int size = numbers.size();
		System.out.println(size);

		for(int i=0; i<numbers.size(); i++) {
			System.out.printf("numbers of %d = %d\n", i, numbers.get(i));
		}

		for(int num : numbers) {
			System.out.println(num);
		}
```

# Sets

* `Set`s are *unordered* collections of *unique* elements
  * There is no first, last or `i`-th element!

```java

		Set<String> names = new HashSet<>();
		names.add("Chris");
		names.add("Kyle");
		names.add("Grace");
		names.add("Craig");

		System.out.println(names);

		names.add("Craig");
		System.out.println(names);

		int size = names.size();
		System.out.println(size);

		names.remove("Chris");
		System.out.println(names);

		names.add("Chris");
		for(String name : names) {
			System.out.println(name);
		}

		//change a set into a list:
		//Deep copy
		List<String> foo = new ArrayList<>(names);
		names.add("Joe");
		System.out.println(names);
		System.out.println(foo);

		//converting a list to a set
		Set<String> bar = new HashSet<>(foo);
```

## Maps

* Kinda like a "dictionary" in python
* It is a key-value pair mapping: it maps keys to values
* Keys are unique: you cannot have a key map to more than one thing
* Values can be duplicates: you can have different keys mapping to the same value
* Powerful (better than dictionaries): you can map *any* type to *any* type!


```java

		//key, value
		Map<Integer, String> nuidToName = new HashMap<>();
		nuidToName.put(35140602, "Chris");
		nuidToName.put(12345678, "Grace");
		nuidToName.put(87654321, "Alan");
		System.out.println(nuidToName);

		nuidToName.put(87654321, "Joe");
		nuidToName.put(87654320, "Joe");
		System.out.println(nuidToName);

		//retrieve
		String me = nuidToName.get(35140602);
		System.out.println(me);

		String you = nuidToName.get(99999999);
		System.out.println(you);
		if(you == null) {
			System.out.println("Not able to find you");
		}

		nuidToName.remove(87654320);
		System.out.println(nuidToName);

		//iterate over values
		//1. iterate over the values in the list:
		for(String name : nuidToName.values()) {
			System.out.println(name);
		}

		//2. iterate over the keys:
		for(Integer nuid : nuidToName.keySet()) {
			String name = nuidToName.get(nuid);
			System.out.println(nuid + " maps to " + name);
		}
```

## Exercise: Zip Codes

```java

		List<String> zipCodes = new ArrayList<>();
		zipCodes.add("68116");
		zipCodes.add("68503");
		zipCodes.add("68503");
		zipCodes.add("68116");
		zipCodes.add("68116");
		zipCodes.add("68503");
		zipCodes.add("68116");
		zipCodes.add("68503");
		zipCodes.add("68116");
		zipCodes.add("90210");
		zipCodes.add("12345");

		System.out.println(zipCodes);
		//report how many times each zip code appears
		Map<String, Integer> zipCounts = new HashMap<>();

		for(String zip : zipCodes) {
			Integer count = zipCounts.get(zip);
			if(count == null) {
				count = 1;
			} else {
				count++;
			}		
			zipCounts.put(zip, count);
		}
		System.out.println(zipCounts);

```

# Strings

* Java has a `String` class/type
  * No memory management
  * No null-terminating character
  * Basic concatenation: `+`
* In Java, strings are *immutable*: once created, the contents of the string *cannot* be changed
* LOTS of nifty methods to call in the string library: RTM!

```java

		String name = "chris";
		System.out.println(name);
		String foo = name.toUpperCase();
		System.out.println(name);
		System.out.println(foo);

		String bar = name.substring(3);
		System.out.println(bar);

		String baz = name.substring(1,3);
		System.out.println(baz);
```












```text














```
