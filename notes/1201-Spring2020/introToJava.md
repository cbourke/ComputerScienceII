# Computer Science II - CSCE 156/156H
## Spring 2020
### Introduction to Java

# Basics

* Java is a class-based Objected Oriented Programming language
* Translation: everything in Java is either a `class` or belongs to a class
* Classes are simply units of code that are declared in their own source file: class name is `HelloWorld`, then the source file MUST be `HelloWorld.java`
* Source files are compiled to Java Virtual Machine (JVM) code
* IDEs like Eclipse can hide these details
* Classes are organized in *packages*: packages are simply just directories (folders)
* Packages provide organization of your classes

```java
package unl.cse;

public class HelloWorld {

  public static void main(String args[]) {
    System.out.println("Hello World");
  }

}
```

* C-style Syntax: 
  * You have a `main` method that acts as a main entry point
  * Curly brackets denote code blocks and can be nested
  * Semicolons end executable lines of code
  * Square brackets are used for arrays
  * String literals: strings are denoted with double quotes
* There is no (direct) memory management: it has automatic *garbage collection* 
* Portable: write once, compile once, run anywhere (as long as you have a JVM)
* Backwards compatible (mostly)
* Comments?
  * Single line comments: `//`
  * Multiline comments: `/* foo bar baz */`
  * Javadoc style comments
  
# Variables

* Java is *statically typed*: all variables must be declared (both their type and their name) before you can use them

```java
int x = 10;
double pi = 3.14159;
char initial = 'C';
//others: long, short, byte, float

boolean isStudent = true;
```  

* Java has a built-in `String` class

```java
String firstName = "Chris";
String lastName = "Bourke";
//the plus, when used with strings is 
// concatenation
String fullName = lastName + ", " + firstName;
```

* However, Strings in Java are *immutable*: once created they cannot be changed!

```java
String s = "hello";
//does not work because strings are 
//immutable:
//s[0] = 'H';
String t = s;
s = "Hello";

System.out.println(s);
System.out.println(t);

String u = s.toUpperCase();

System.out.println(s);
System.out.println(u);
```
* There are a LOT of methods (functions) in the string "library" that you can use to manipulate strings: RTM (Read The Manual)
* Careful: similar to C, Java has implicit and explicit type casting rules

```java

int a = 10;
int b = 20;
int c = a / b;
System.out.println(c);

double result = a / (double) b;
System.out.println(result);

int pi = 3.14159;
```

## Operators

* Basic arithmetic: `+ - * /`
* Also: modular division: `10 % 3` (results in the remainder, in this case, 1)
* Logical operators: `!`, `&&` (and), `||` (or)
* All logical operators *must* operate on `true` or `false` statements or boolean variables
* Inequality/equality operators: `==`, `!=`, `<=`, `>=`, `<`, `>`
* YOU CANNOT USE EQUALITY OPERATORS ON STRINGS

```java

		String a = "100";
		String b = "9";
		
		
		//to properly compare strings:
		if(a.compareTo(b) == 0) {
			System.out.println("equal!");
		} else if(a.compareTo(b) < 0) {
			System.out.println(a + " comes before " + b);			
		} else if(a.compareTo(b) > 0) {
			System.out.println(a + " comes after " + b);			
		}
		
		int x = Integer.parseInt(a);
		int y = Integer.parseInt(b);
		if(x > y) {
			System.out.printf("%d comes after %d\n", x, y);
		}
```

## Basic Output

* Java supports output to the *standard output* using `System.out`
  * `System.out.print()`
  * `System.out.println()` adds an endline character
  * `System.out.printf()` uses printf-style formatting

```java
double pi = 3.14159265;
System.out.printf("%f", pi);  //3.141593
System.out.printf("%.2f", pi); //3.14
System.out.printf("%.50f", pi); //
```
  
* You can also use `BigDecimal` or `BigInteger` for arbitrary precision math:

## Conditionals

* Java supports basic `if`, `if-else`, `if-else-if` statements
* In Java, however, all conditionals must be used with *boolean* statements or variables

```java
//bad style:
int x = 10;
if( x == 10 ) 
  System.out.println("equal to ten");
  System.out.println("foo");

  //good style:
  int x = 10;
  if( x == 10 ) {
    System.out.println("equal to ten");
  }
```

## Loops

* For loops are supported as well as while loops

```java
for(int i=1; i<=10; i++) {
  System.out.println(i);
}

System.out.println("=========");

int i = 1;
while(i <= 10) {
  System.out.println(i);			
  i++;
}
```

* "Enhanced" for loop (for each loop)

```java
int arr[] = { 2, 3, 5, 7, 11, 13, 17 };
		for(int i=0; i<7; i++) {
			System.out.println("element = " + arr[i] );
		}
		
		for(int x : arr) {
			System.out.println("element = " + x);
		}

    
String names[] = { "Chris", "Joe", "Jane", "Kris", "Anthony", "Nolan" };
for(String name : names) {
  System.out.println(name);
}
```

## Arrays

* Java supports traditional arrays
* No memory management, so technically no `malloc`
* Instead: to create new dynamic arrays use the `new` keyword

```java

		int arr[] = new int[10];
		
		arr[0] = 42;
		arr[9] = 101;
		
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		double brr[] = new double[10];
		String crr[] = new String[10];
```

### Dynamic Collections

* Plain old arrays suck
* Instead, prefer to use dynamic collections: `List`, `Set`, `Map`

```java

		List<Integer> numbers = new ArrayList<int>();
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		System.out.println(numbers);
		numbers.add(0, 40);
		System.out.println(numbers);
		numbers.remove(3);
		System.out.println(numbers);
		System.out.println("size of list is " + numbers.size());
		for(Integer x : numbers) {
			System.out.println(x);
		}
		for(int i=0; i<numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}
```

* A `Set` is an *unordered* collection of *unique* elements (no duplicates are allowed)

```java
Set<String> names = new HashSet<String>();
names.add("Chris");
names.add("Joe");
names.add("Jane");
System.out.println(names);
names.add("Jane");
System.out.println(names);
names.remove("Chris");
System.out.println(names);
//String s = names.get(0);
//		for(int i=0; i<names.size(); i++) {
//			System.out.println(names.get(i));
//		}
for(String name : names) {
  System.out.println(name);
}
```

* A `Map` is a key-value pair collection
* Elements can be accessed using a key which maps to a value

```java
Map<Integer, String> myMap = new HashMap<>();
myMap.put(10, "Ten");
myMap.put(20, "Twenty");
myMap.put(30, "Foo");
System.out.println(myMap);
//iterating:
//for each key in the map myMap:
for(Integer key : myMap.keySet()) {
  String value = myMap.get(key);
  System.out.println(key + " => " + value);
}
myMap.put(30, "Bar");
myMap.put(40, "Bar");
System.out.println(myMap);

//dump the keyset to a list:
List<Integer> keys = new ArrayList<>();
for(Integer key : myMap.keySet()) {
  keys.add(key);
}
Collections.sort(keys);
for(Integer key : keys) {
  String value = myMap.get(key);
  System.out.println(key + " maps to " + value);
}
```

## Strings

* Java provides a huge standard library of methods to compute and manipulate strings
* Just RTM: Read The Manual

```java
String fullName = "Christopher Michael Bourke";
		
		String middleName = fullName.substring(12, 19).toUpperCase();
		System.out.println(middleName+"|");
		
		String data = "Chris,Bourke,1234,Schorr 105";
		String tokens[] = data.split(",");
		System.out.println(Arrays.toString(tokens));
```

* Strings are immutable, but you can use `StringBuilder` to get a mutable string		
```java
StringBuilder sb = new StringBuilder();
sb.append("Chris");
sb.append(" Bourke");

System.out.println(sb);
sb.append(", PhD");
System.out.println(sb);

sb.insert(5, " Michael");
System.out.println(sb);

String fullName = sb.toString();
System.out.println(fullName);
```

## Methods

* In Java, "functions" are called "methods"
* For now, all our methods will be `static` methods: the methods belong to the class and not to instances of the class
* You can access/invoke/run static methods using the classname + dot + the method name (plus any parameters you want to pass to it)

```java
package unl.cse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is my demo class
 * 
 * @author cbourke
 *
 */
public class Demo {

	/**
	 * This method computes arbitrarily large factorial values, for more information
	 * see <a href="https://en.wikipedia.org/wiki/Factorial">wikipedia</a>
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger factorial(int n) {
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

	/**
	 * Computes the Euclidean Distance between the two 
	 * points <code>(x1,y1)</code> and <code>(x2,y2)</code>
	 * @param x1 the x component of the first point
	 * @param y1 the y component of the first point
	 * @param x2 the x component of the second point
	 * @param y2 the y component of the second point
	 * @return
	 */
	public static double euclideanDistance(double x1, double y1, double x2, double y2) {

		return Math.sqrt( Math.pow((x1-x2), 2) +  Math.pow((y1-y2), 2));
		
	}

	public static void main(String args[]) {

		int n = 1000;
		BigInteger fact = Demo.factorial(n);
		System.out.printf("%d! = %s\n", n, fact.toString());

	}

}

```

# Error Handling

* Java supports using Exceptions for error handling
* An exception is an interruption of the normal flow of control
* Potentially dangerous code can be surrounded by a `try-catch` block
* If an exception is thrown, and you catch that type of exception, then you can *handle* it with any code you put in the catch block
* Example:

``java
package unl.cse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is my demo class
 * 
 * @author cbourke
 *
 */
public class Demo {

	/**
	 * This method computes arbitrarily large factorial values, for more information
	 * see <a href="https://en.wikipedia.org/wiki/Factorial">wikipedia</a>
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger factorial(int n) {
		
		if(n < 0) {
			throw new RuntimeException("factorial is undefined for negative values");
		}
		
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

	/**
	 * Computes the Euclidean Distance between the two 
	 * points <code>(x1,y1)</code> and <code>(x2,y2)</code>
	 * @param x1 the x component of the first point
	 * @param y1 the y component of the first point
	 * @param x2 the x component of the second point
	 * @param y2 the y component of the second point
	 * @return
	 */
	public static double euclideanDistance(double x1, double y1, double x2, double y2) {

		return Math.sqrt( Math.pow((x1-x2), 2) +  Math.pow((y1-y2), 2));
		
	}

	public static void main(String args[]) {

		try {
			int n = -10;
			BigInteger fact = Demo.factorial(n);
			System.out.printf("%d! = %s\n", n, fact.toString());
			
			int x = Integer.parseInt("hello");
			System.out.println(x);
		} catch(NumberFormatException nfe) {
			//you can decide how to handle the exception here
			//TODO: reread the input from the user
		} catch(RuntimeException re) {
			//handle this exception slightly differently
		} catch(Exception e) {
			//okay, I don't know what happened, let's rethrow it:
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("okay..");
	}

}
```

## File I/O

```java

		try {
			// 1. open a file and process it for input
			Scanner s = new Scanner(new File("data/input.txt"));

			// 2. scan the input: get each line and process it
			while (s.hasNextLine()) {
				String line = s.nextLine();
				// also strip all whitespace from the line:
				line = line.replaceAll("\\s+", "");
				System.out.println(line);
			}

			// 3. close it
			s.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InputMismatchException ime) {
			throw new RuntimeException("bad file man");
		}

		// file output

		try {
			File output = new File("data/output.txt");
			PrintWriter pw = new PrintWriter(output);
			pw.println("Hello, this is the first line");
			pw.print("another line, put in your own endline character\n");
			pw.printf("%d, %10.2f", 123, Math.PI);
			pw.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
```







