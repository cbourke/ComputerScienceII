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

```text









```
