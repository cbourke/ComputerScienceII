# Computer Science II - CSCE 156/156H

## Spring 2022

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
 * Date: 2022/01/20
 *
 * A basic Hello World program
 *
 * @author cbourke
 *
 */
public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello World");
	}

}
```

C-Style Syntax:
* Comments are the same: single is `//comment`, multiline is `/* ... */`
* Semicolons at the end of all executable lines
* Brackets denote code blocks: begin and end chunks of code (functions, classes, loops)
* Command line arguments are supported
* Square brackets for arrays
* Double quotes denote strings
* Good style:
  * same consistent indentation/whitespace

Differences:
* In Java there is no memory management!
* Java has automatic garbage collection!
* Portable: you write once, compile once, run anywhere
* Backwards compatible (for the most part)  

# Variables

* Java is *statically typed*: all variables and their types must be declared before you use them and types cannot change

```java

		int x = 42;
		x = (int) 3.14;
		double pi = 3.14;
		pi = 3;
		char initial = 'C';
		String name = "Chris";
		String fullName = name + " " + "Bourke";

		String number = "42";
		int y = Integer.parseInt(number);
		String otherNumber = "3.14";
		double z = Double.parseDouble(otherNumber);

		System.out.println(y);
		System.out.println(z);


    boolean isStudent = false;
		isStudent = true;

		//nope:
		//isStudent = 1;

```

## Command Line Arguments

* Like most languages, you can pass command line arguments (CLAs) to a program when you start it
* For Java, these are available in the `main` method's `args` array

```java

		System.out.println("Number of arguments: " + args.length);
		//first argument:
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
```

# Conditionals

* `if`, `if-else`, `if-else-if` are all supported
* All conditions *MUST* be boolean expressions or statements or variables

```java

		boolean isStudent = true;
		boolean isSenior = true;
		int x = 20;
		if(x > 42 && (isStudent || isSenior) ) {
			System.out.println("Discount!");
		} else {
			System.out.println("Nope!");			
		}
```

* `&&` is the and operator
* `||` is the or operator
* `!` s the negation

# Loops

* `for`, `while`, `do-while` are all supported

```java
for(int i=0; i<10; i++) {
  System.out.println("Hello " + i);
}

//nope: System.out.println("Hello " + i);			
int i = 0;
while(i < 10) {
  System.out.println("Hello " + i);
  i++;
}
```

# Arrays

* Java does support traditional arrays
* There is no memory management, so to allocate memory you use the `new` keyword and
* You do not need to worry about cleaning up/garbage collection

```java

		//int n = 10;
		int numbers[] = new int[10];
		numbers[0] = 42;
		numbers[9] = 101;
		//numbers[-1] = 4;
		//numbers[10] = 4;
		for(int i=0; i<numbers.length; i++) {
			System.out.println("numbers[" + i + "] = " + numbers[i]);
		}

		for(int x : numbers) {
			System.out.println(x);
		}
  ```

## Lists

```java

		List<String> names = new ArrayList<>();
		names.add("Chris");
		names.add("Joe");
		names.add("Jane");
		System.out.println(names);
		System.out.println(names.size());
		names.remove("Chris");		
		System.out.println(names.size());
		//names.add(10);
		System.out.println(names);
		//remove via an index:
		names.remove(1);
		System.out.println(names);
		names.add("John");
		names.add("Jane");
		names.add("Darrell");
		names.add("Darrell");
		System.out.println(names);
		names.remove("Darrell");
		System.out.println(names);
```



```text




```
