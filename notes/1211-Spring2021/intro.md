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