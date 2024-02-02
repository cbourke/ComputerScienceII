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
* Java also has "enhanced" for loops to iterate over collections without an index variable
* Demo

```java

		int n = 10;

		for (int i = 0; i < n; i++) {
			System.out.println(i);
		}
		System.out.println("Done");

		int i = 0;
		while (i < n) {
			System.out.println(i);			
			i++;
		}

		int x = 0;
		if(x == 0) {
			System.out.println("There is 1 digit in 0");
		} else {
			//determine how many digits are in x...
			int numDigits = 0;
			while(x != 0) {
				x = x / 10;
				numDigits++;
			}
			System.out.println("There are " + numDigits + " digits");
		}

		int primes[] = {2, 3, 5, 7, 11, 13, 17};
		for(int j=0; j<primes.length; j++) {
			System.out.println(primes[j]);
		}

		for(int z : primes) {
			System.out.println(z);
		}
```

# Arrays and Collections

* Java has basic arrays
* They are 0-indexed
* They are created with a *fixed size*, they cannot grow, shrink, etc.
* Only ever use arrays if you *have to*

```java

		int primes[] = {2, 3, 5, 7, 11, 13};

		//you can get the size of an array using the .length property:
		System.out.println("THere are " + primes.length + " elements in primes");

		int numbers[] = new int[10];
		numbers[0] = 42;
		numbers[numbers.length-1] = 101;
		//default value for uninitialized array values is zero!
		System.out.println( Arrays.toString(numbers) );

		//ArrayIndexOutOfBoundsException:
		//numbers[10] = 42;
		//numbers[-1] = 101;
```

## Lists

```java

		List<Integer> numbers = new ArrayList<>();
		//add stuff:
		numbers.add(10);
		numbers.add(30);
		numbers.add(20);
		System.out.println(numbers);

		//CRUD = Create Retrieve Update Destroy
		//delete stuff:
		//remove the element at index 1:
		numbers.remove(1);
		System.out.println(numbers);

		//remove the element at index 0:
		numbers.remove(0);
		System.out.println(numbers);

		//remove the element at index 0:
		numbers.remove(0);
		System.out.println(numbers);

		numbers.add(10);
		numbers.add(30);
		numbers.add(20);
		System.out.println(numbers);
		//retrieve:
		// get the element at index 0:
		int x = numbers.get(0);
		System.out.println(x);
		System.out.println(numbers);

		//iterate:
		for(int i=numbers.size()-1; i>=0; i--) {
			System.out.println(numbers.get(i));
		}

		//enhanced for loop:
		for(Integer y : numbers) {
			System.out.println(y);
		}

		numbers.add(42);
		//adds 101 to index 3; ie *inserts* it
		numbers.add(3, 101);
		numbers.add(18);
		numbers.add(24);
		numbers.add(84);
		System.out.println(numbers);

		List<Integer> someNumbers = numbers.subList(3, 5+1);
		System.out.println(someNumbers);

```

* Sets are unordered collections of *unique* elements
  * There is no first element, second, etc.
  * There are no duplicates!

```java

		//A set is an *unordered* collection of *unique* elements
		Set<String> names = new HashSet<>();
		names.add("Chris");
		names.add("John");
		names.add("Jane");
		names.add("Cody");

		System.out.println(names);
		//will not work: there is no "first" element nor "last", etc.
		//names.get(0);

		names.add("Chris");
		System.out.println(names);

		//you cannot add dissimilar elements:
		//ie you cannot add an integer to a set of strings
		//  nor can you add a string to a set of integers
		//names.add(2024);

		names.remove("Chris");
		System.out.println(names);

		for(String name : names) {
			System.out.println(name);
		}

		//you can transform a list into a set and vice versa:
		List<String> foo = new ArrayList<>(names);
		System.out.println(foo);
		System.out.println(foo.get(0));

		foo.add("Chris");
		foo.add("Chris");
		foo.add("Chris");
		foo.add("Chris");
		foo.add("Chris");
		System.out.println(foo);

		Set<String> bar = new HashSet<>(foo);
		System.out.println(bar);```

```

* Maps are much more powerful than sets, lists or even python dictionaries!

```java

		//maps map a KEY to a VALUE
		//maps are *unordered*
		//keys are *unqiue*
		//values are NOT unique
		Map<Integer, String> foo = new HashMap<>();
		Map<Double, String> bar = new HashMap<>();

		foo.put(10, "ten");
		foo.put(20, "hello");
		foo.put(30, "goodbye");
		foo.put(35140602, "Chris");
		System.out.println(foo);
		foo.put(30, "how are you?");
		System.out.println(foo);
		foo.put(35140603, "Chris");
		System.out.println(foo);

		foo.remove(30);
		System.out.println(foo);

		foo.replace(20, "goodbye");
		System.out.println(foo);

		//retrieve using the key:
		String s = foo.get(35140602);
		System.out.println(s);

		//iterate over the values:
		for(String str : foo.values()) {
			System.out.println(str);
		}

		//iterate over the keys:
		for(Integer key : foo.keySet()) {
			System.out.println(key);			
		}

		//iterate over the keys and get the value:
		for(Integer key : foo.keySet()) {
			System.out.println(key + " maps to " + foo.get(key));			
		}

```

# Strings

* Java has a `String` class/type
  * No memory management
  * No null-terminating character
  * Basic concatenation: `+`
* In Java, strings are *immutable*: once created, the contents of the string *cannot* be changed

```java

		int x = 10;
		String message = "Hello, the value of x = " + x;
		System.out.println(message);

		String a = "school of computing";
		String b = a.replace("s", "S");
		String c = b.replace("c", "C");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);

		String d = a.substring(10);
		System.out.println(d);

		String e = a.substring(0, 6);
		System.out.println(e);

		System.out.println("The string e is of length " + e.length());

		//Java does have a *mutable* version of a string:
		StringBuilder sb = new StringBuilder();
		sb.append("school");
		sb.append(" of ");
		sb.append("computing;");
		sb.deleteCharAt(sb.length()-1);
		sb.replace(0, 1, "S");
		sb.replace(10, 11, "C");
		System.out.println(sb);
		String s = sb.toString();
		System.out.println(s);

		String csvData = "Chris,Bourke,105 Schorr,chris.bourke@unl.edu,School of Computing";
		//String tokens[] = csvData.split("\\s+");
		String tokens[] = csvData.split(",");
		for(int i=0; i<tokens.length; i++) {
			System.out.printf("tokens[%d] = %s\n", i, tokens[i]);
		}
```

## Methods/Functions

* In Java functions are called "methods"
* Methods are just function inside a class
* For now, all our methods will be `public static`: they will belong to the class
  * `public` means that any part of your code can call/invoke the function; everyone can "see" the function/method
	* `static` means it belongs to the class
	* When invoking a `static` method use `ClassName.functionName()`
* To invoke or "call" a `static` function, you use the class name + the function name
* It is either necessary or best practice to access/call those methods using the dot operator: ex: `Math.sqrt()`, `Demo.addOne()`
* You also specify a return type: the type of variable the method returns
* If non-`void` you MUST return a value, you use the keyword `return` (for `void` methods you should still use a `return;` statement)
* All non-trivial methods need documentation!

```java
/**
 * This is a non-sense demonstration function only written
 * to demonstrate syntax for creating a function.
 *
 * @param x
 * @param y
 * @param z
 * @return
 */
public static double foo(int x, double y, String z) {
	double sum = x + y;
	x = 5;
	System.out.println("x is now " + x);
	System.out.println("I was given the string " + z);

	return sum;
}

public static void messWithStringBuilder(StringBuilder sb) {
	sb.append("Consider it messed with");
}


/**
 * Prints a report of all the sales to a hardcoded file, see .....
 *
 * Be careful calling this function as it expects all the
 * data to be loaded prior to calling it.
 */
public static void produceReport() {
	//TODO: implement this at some point
}

public static void messWithString(String s) {
	//you cannot mess with s because it is immutable
}


//no docsnecesssary on mains
public static void main(String args[]) {

	double xx = 2.0;
	double y = Math.sqrt(xx);
	System.out.printf("x, y = %f, %f\n", xx, y);

	double result = Demo.foo( (int) xx, y, "Hello World!");
	System.out.println(result);

	System.out.printf("x, y = %f, %f\n", xx, y);

	StringBuilder sb = new StringBuilder();
	sb.append("Hello\n");
	Demo.messWithStringBuilder(sb);
	System.out.println(sb);
```

Misc Observations:
 * In Java, single variables are passed by value
 * However, objects/mutable lists, sets, etc. are passed by reference
 * Java supports function *overloading*: You can define multiple functions with the same name as long as their parameters are different

## Style Notes

* ALL variable names should be `lowerCamelCase` and nouns (pluralize collections only)
* ALL method names should be `lowerCamelCase` and should be verbs
* ALL class names should be `UpperCamelCase`, never pluralized, nouns
* Use proper Java-style whitespace

# File I/O

## File Output

* Open the file, write to the file, **close the file**

```java

		int a = 10;
		double b = 3.5;
		//file output:
		File f = new File("data/myOutput.txt");
		PrintWriter pw;
		try {
			pw = new PrintWriter(f);
			pw.println("Hello, this is one line");
			pw.print("This is a line without an endline");
			pw.print("now I'll put the endline in: \n");
			pw.printf("Now the Variables: %d, %f\n", a, b);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
```



```











```
