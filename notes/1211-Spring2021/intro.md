# Computer Science II - CSCE 156/156H

## Spring 2021

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

/**
 * Author: Chris Bourke
 * Date: 2021/01/28
 * Purpose: a basic hello world program.
 */
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
    isStudent = false;
    ```    
    
* Java has a "built in" `String` class that is really easy to use

```java
String s = "computer";

//use the + operator for concatenation:
String t = s + " science";
System.out.println(t);
```

## Command Line Arguments

* Like most languages, you can pass command line arguments (CLAs) to a program when you start it
* For Java, these are available in the `main` method's `args` array
* The first one is at `args[0]`
* The number of them is `args.length`
* You should be doing basic error handling
* Every argument is passed as a `String`

```java
//convert a to an integer:
int x = Integer.parseInt(args[0]);
System.out.println(x);
//conver to a double:
double y = Double.parseDouble(args[1]);
System.out.println(y);
```

# Conditionals

* Java supports basic `if`, `if-else`, `if-else-if` statements
* In Java, however, all conditionals must be used with *boolean* statements or variables

```java
boolean foo = true;
int x = 10;
int y = 20;
if ( !foo ) {
  System.out.println("Hello");
} else if(x >= 10) {
  System.out.println("Goodbye");
} else { 
  System.out.println("Uh...");			
}
```

# Loops

* Java has `for` loops and `while` loops and "enhanced for loops"

```java
for(int i=1; i<=10; i++) {
  System.out.println(i);
}

int i = 1;
while(i <= 10) {
  System.out.println(i);	
  i++;
}
System.out.println(i);	
```

# Arrays and Collections

* Java supports traditional arrays
* There is no memory management; Java will clean up after you (no `free` function)
* Use the `new` keyword to create a new array of a particular type and size
* YOu can always use the `.length` property to find the size of an array

```java
		int primes[] = { 2, 3, 5, 7, 11, 13 };
		for (int i = 0; i < primes.length; i++) {
			System.out.println(primes[i]);
		}
		
		int arr[] = new int[5];
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		double brr[] = new double[5];
		for (int i = 0; i < brr.length; i++) {
			System.out.println(brr[i]);
		}
```

* Don't use them, they suck
* Instead, use *dynamic collections*~

## Lists

* A list is an ordered collection of stuff
* The typical implementation is an `ArrayList`

```java

		List<Integer> primes = new ArrayList<>();
		List<Double> prices = new ArrayList<>();
		List<String> names = new ArrayList<>();

		names.add("Bob");
		names.add("Alice");
		String name = "Chris";
		names.add(name);
		System.out.println(names);

		primes.add(1);
		primes.add(2);
		primes.add(3);
		primes.add(5);

		System.out.println(primes);

		prices.add(2.50);
		prices.add(3.14);
		prices.add(89.00);

		System.out.println(prices);

//		prices.remove(10);

		System.out.println(prices);

		// to get the length/size of a collection:
		System.out.println("There are " + prices.size() + " elements in prices");
		for (int i = 0; i < prices.size(); i++) {
			System.out.println(prices.get(i));
		}

		// you can also use an enhanced for loop:
		for (Double price : prices) {
			System.out.println(price);
		}
			
```

## Sets

* A set is an *unordered* collection of *unique* elements (no duplicates)
* Unordered: there is no first element, second, etc.

```java

		Set<String> names = new HashSet<>();

		names.add("Bob");
		names.add("Alice");
		String name = "Chris";
		names.add(name);

		System.out.println(names);

		for(String s : names) {
			System.out.println(s);			
		}
```
    
    
```text






```    
    
    
    