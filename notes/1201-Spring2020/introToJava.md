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
  






