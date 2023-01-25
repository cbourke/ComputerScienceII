# Computer Science II - ECEN 194

## Spring 2023

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
package uno.ece;

public class HelloWorld {

	public static void main(String[] args) {

		System.out.print("Hello World!");
		int x = 42;
		double y = 3.14159;
		String s = "Goodbye";
		System.out.printf("x = %d, y = %10.2f, s = %s\n", x, y, s);
	}

}

```

Java has C-style syntax
  * Blocks of code delimited by opening/closing curly brackets
  * Executable statements are ended with semicolons
  * Comments: exactly the same: single line `//single line`
  * Multiline: `/* foo bar baz */`
  * Good style:
    * same consistent indentation/whitespace
  * Double quotes denote strings

Differences:
  * In Java there is no memory management!
  * Java has automatic garbage collection!
  * Portable: you write once, compile once, run anywhere
  * Backwards compatible (for the most part)  


```text




```
