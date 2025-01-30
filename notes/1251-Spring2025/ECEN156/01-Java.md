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

* Aside: 2-D arrays (matrices, tables):

```java
//3 rows, 5 columns
int matrix[][] = new int[3][5];
for(int i=0; i<matrix.length; i++) {
	for(int j=0; j<matrix[i].length; j++) {
		matrix[i][j] = i + 2;
	}
}
```

```java

		//A map maps keys to values
		Map<Integer, String> nuidToName = new HashMap<>();
		nuidToName.put(35140602, "Chris");
		nuidToName.put(12345678, "Joe");
		nuidToName.put(87654321, "Jane");

		System.out.println(nuidToName);

		//retrieve: you need the key
		String name = nuidToName.get(35140602);
		System.out.println(name);

		//retrieve something that does not exist:
		name = nuidToName.get(1);
		if(name == null) {
			System.out.println("Cannot find value with key 1");
		} else {
			System.out.println(name);			
		}

		//replace stuff:
		String oldValue = nuidToName.put(35140602, "Kris");
		System.out.println(nuidToName);

		nuidToName.remove(12345678);
		System.out.println(nuidToName);

		//iterate over the key values
		for(Integer key : nuidToName.keySet()) {
			System.out.println(key);
		}

		//iterate over the values themselves:
		for(String value : nuidToName.values()) {
			System.out.println(value);
		}

		//iterate over "both"
		for(Integer key : nuidToName.keySet()) {
			String value = nuidToName.get(key);
			System.out.println(key + " maps to " + value);
		}
```

```java

		List<String> zipCodes = new ArrayList<>();
		zipCodes.add("68508");
		zipCodes.add("68508");
		zipCodes.add("68508");
		zipCodes.add("68116");
		zipCodes.add("68116");
		zipCodes.add("90210");
		zipCodes.add("12345");

		// goal: determine how many times each zip code
		// appears
		// 2. what is the most common zip code?
		Map<String, Integer> zipCodeCount = new HashMap<>();
		// outline:
		// for each zipcode in zipCodes:
		// find the zipcode counter in the map
		// 1. If this is the first time we see it, set it to 1
		// 2. If it is not the first time, set it ++
		for (String zip : zipCodes) {
			Integer count = zipCodeCount.get(zip);
			if (count == null) {
				// first time we've seen zip
				zipCodeCount.put(zip, 1);
			} else {
				count++;
				zipCodeCount.put(zip, count);
			}
		}
		System.out.println(zipCodeCount);

		int maxValue = Collections.max(zipCodeCount.values());
		System.out.println(maxValue);

		// create a new map that maps:
		// a counter (number of times it appears) to a Set
		// of all zip codes that appear that many times

		// create the map:
		Map<Integer, Set<String>> counterToZips = new HashMap<>();
		// for each key (zip):
		for (String zip : zipCodeCount.keySet()) {
			int count = zipCodeCount.get(zip);
			Set<String> zips = counterToZips.get(count);
			// if its the first time we've seen it..
			if (zips == null) {
				// initialize the set
				zips = new HashSet<>();
				// add the zipcode to the set
			}
			zips.add(zip);
			counterToZips.put(count, zips);
		}
		System.out.println(counterToZips);
```

# Strings

* Java has a `String` class
  * There is no null-terminator
	* There is no memory management
	* There is a large library of string functions to use
* Extra: see https://regex101.com/ for regular expressions!
* Or: https://regexr.com/

```java

		String name = "chris";
		System.out.println(name);
		String foo = name.toUpperCase();
		System.out.println(name);
		System.out.println(foo);

		char initial = name.charAt(0);
		System.out.println(initial);

		String bar = name.replace('c', 'C');
		System.out.println(bar);

		String differentName = new String(name);

		String message = "Hello " + "World";

		//comma separated values:
		//data tokens separated by commas
		String csvData = "Chris,Bo urke,35140602,chris.bo\turke@u   nl\n.edu,472-5008";
		String tokens[] = csvData.split(",");
		System.out.println( Arrays.toString(tokens) );
		for(String token : tokens) {
			System.out.println(token);
		}

		tokens = csvData.split("\\s+");
		System.out.println( Arrays.toString(tokens) );

		String s = csvData.replaceAll("[0-9]+", "foo");
		System.out.println(s);
```

* Comparing Strings

```java
//lexicographic order: according to the asciitable.com
String a = "123";
String b = "9";

int result = a.compareTo(b);
if(result < 0) {
	System.out.println(a + " comes before " + b);
} else if(result > 0) {
	System.out.println(b + " comes before " + a);
} else {
	System.out.println(a + " equals " + b);			
}
```

# Methods/Functions

* In Java, "functions" are referred to as "methods"
* `public`: any part of the code can "see" your method/function and thus call/use it
* `static` means that the method belongs to the class; consequence: you invoke the function (call the function) using the `ClassName.functionName()`
* You declare functions inside a relevant class and provide the definition
* The documentation, signature, and definition are all together
* All functions are pass by value
* Generally:
  * Use `lowerCamelCasing` for method names
	* method names should be *verbs*
	* ALL non-trivial methods require java-doc style documentation
	* You can have multiple functions with the same name (function overloading) as long as their parameters are different
	* Java supports method overloading: you can have multiple functions of the same name as long as they take different parameters

# File I/O

* File I/O involves opening a file, processing it, and then closing
* File output:

```java

		int a = 42;
		double b = 3.14159;
		String c = "Chris";

		//open a file:
		File f = new File("/etc/passwd");
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.println("Hello Wolrd!!!");
			pw.printf("%d, %f, %s\n", a, b, c);
			pw.print("Goodbye World\n");
			pw.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
```

* File input:

```java
List<Double> ratings = new ArrayList<>();
File f = new File("data/books.csv");
Scanner s;
try {
	s = new Scanner(f);
	//waste the first line which is header data
	s.nextLine();
	while (s.hasNextLine()) {
		String line = s.nextLine();
		if (!line.isEmpty()) {
			//Book Id,Title,Last,First,ISBN13,Average Rating,Publisher,Original Publication Year,Date Read
			String tokens[] = line.split(",");
			double rating = Double.parseDouble(tokens[5]);
			ratings.add(rating);
			System.out.println(line);
		}
	}
	s.close();
} catch (FileNotFoundException e) {
	throw new RuntimeException(e);
}
Collections.sort(ratings);
System.out.println(ratings);
```

# Classes

* Classes in Java allow you to *encapsulate* more than one piece of data into one logical unit: a class
* Classes also allow you to include *methods* that act on that data or process that data
* Class Design:
  * Break an entity down into its parts until...
	* You can represent it by a built-in type: `int, double, String`
	* Or there are mulitiple pieces of "subdata": create a new class, unless...
	* a class already exists to represent that entity
* A class can consist of other instances of other classes: `Book` class "owns" several strings and is *composed* of other classes: `String, LocalDate, Person`: "composition"
* Another purpose of writing classes: *abstraction*: we don't want to have to worry about how data is represented, we just want to interact with it!

```text












```
