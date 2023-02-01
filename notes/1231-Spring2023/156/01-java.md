# Computer Science II - CSCE 156

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

## Observations

* In Java, all class names *should* be `UpperCamelCased`

```java

package soc.unl;

/**
 *
 * Author: Chris Bourke
 * Date: 2023-01-23
 *
 * This is a basic hello world program.
 *
 * You can write basic <code>like this</code>
 *
 * You can link to stuff using <a href="https://unlcornhacks.com/#/home">CornHacks!</a>
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

* Comments:
  * Documentation style comments: `/** ... */`
  * Multiline comments: `/*  ....  */`
  * Single line comment: `//`
  * Avoid CRUFT, code should be self-documenting
* All executable lines of code end with a semicolon: `;`
* Code blocks are denoted with opening/closing curly brackets: `{...}`
* Indentation is important for style, but otherwise whitespace is ignored

Differences:
* In Java there is no memory management!
* Java has automatic garbage collection!
* Portable: you write once, compile once, run anywhere
* Backwards compatible (for the most part)  

# Basics

## Variables

* Java is *statically typed*: all variables and their *types* MUST be declared before you use them

```java
int x = 42;
double y = 3.14159;
String message = "Goodbye World";

System.out.println(x);
System.out.println(y);
System.out.println(message);

//formatted output:
System.out.printf("x = %d, y = %.2f, message = %s\n", x, y, message);
System.out.printf("x = %10d, y = %10.3f, message = %-100s\n", x, y, message);
```

* Types:
	* `int` an integer with 32 bit representations limited to integers in the range -2147483648 to 2147483647
	* `double` is a floating point number with about 16-17 digits of accuracy
	* `char` - a single *unicode* character; but generally ignore because...
	* `String` is a built-in type:
		* Automatic concatenation: `s + t`
		* No memory management, no null-terminator, etc.
		* Strings are *immutable*!!!

## Operators

* Basic math: `+ - * /`
* In Java: you have to be careful with integer truncation; an integer divided
  by an integer is an integer so `10 / 20` results in 0
* You can use *explicit typecasting* to solve this: `a / (double) b`
* Basic math functions in the `Math` class: `Math.sqrt()`, `Math.sin()`, etc.


```java

		int a = 10;
		int b = 20;
		double x = 3.14159;
		double y = 10;
		String message = "Goodbye World";

		int c = a + b;
		c = a - b;
		c = a * b;
		//careful integer truncation:
		c = a / b;
		System.out.println(c);

		double z = a / (double) b;
		System.out.println(z);

		int large = 2147483647;
		System.out.println(large);
		//add one to large causing *overflow*
		large = large + 1;
		//or:
		large += 1;
		//or:
		large++;
		System.out.println(large);

		double foo = 1/3.0;
		System.out.printf("%.40f", foo);
		double bar = foo + foo + foo;
		System.out.println(bar);
```		

## Command Line Arguments

```java

		System.out.println("Hello WOrld");
		int n = args.length;
		if(n != 7) {
			System.err.println("Expected 7 arguments!");
			System.exit(1);
		}

		//convert the first argument to an integer:
		int x = Integer.parseInt(args[0]);
		double y = Double.parseDouble(args[1]);
		System.out.println(x);
		System.out.println(y);

		for(int i=0; i<args.length; i++) {
			System.out.println(args[i]);			
		}
```

# Conditionals

* You have traditional `if`, `else-if` and `if-else-if`

```java

		int hawksScore = 1;
		int flamesScore = 1;

		if(hawksScore > flamesScore) {
			System.out.println("Blackhawks win!");
		} else if(flamesScore > hawksScore) {
			System.out.println("Blackhawks lose!");			
		} else {
			System.out.println("Tie, Overtime!");			
		}

		boolean isStudent = false;
		if(isStudent) {
			System.out.println("You get free tickets!");
		} else {
			System.out.println("You pay full price!");
		}

```

* In Java you have full `boolean` variable types that you need to use (you cannot use numerical types as substitutes)
* You have the keywords `true` and `false` (case sensitive)
* You also have numerical comparison operators: `<, <=, >, >=, ==, !=`
* Numerical types can ONLY be used for numbers, not strings, not objects, etc.

```java

		double a = 10, b = 2, c = 30;

		double radical = Math.pow(b, 2) - 4 * a * c;
		if(radical < 0) {
			System.out.println("Complex Number!");
		}

		double result = Math.sqrt(radical);
		System.out.println(result);

		result = Math.log(0);
		System.out.println(result);
```

* String comparisons: you cannot use `<, >`, etc.

```java
String a = "apple";
String b = "banana";

int result = a.compareTo(b);
result = a.compareToIgnoreCase(b);

if(result < 0) {
	System.out.println(a + " comes before " + b);
} else if(result > 0) {
	System.out.println(b + " comes before " + a);			
} else {
	System.out.println(a + " equals " + b);			
}
```

* You can create more complex logical expression using connectives
  * Logical or: `||`
	* Logical and: `&&`
	* Both of them require TWO characters (single characters are bit-wise operators)
	* Logical negation: `!`		

```java

		int a = 10, b = 20, c = 30;
		if( (a > b && c == 30) || b == 20) {
			//...
		}

		if(a < b || c != 30) {
			//...
		}
```

# Loops

* Java has both `for` loops and `while` loops

```java

		int n = 10;
		for (int i = 0; i < n; i++) {
			System.out.println(i);
		}

		int j = 0;
		while(j < n) {
			System.out.println(j);			
			j++;
		}

		int primes[] = {2, 3, 5, 7, 11, 13, 17, 19};
		for(int i=0; i<primes.length; i++) {
			System.out.printf("primes[%d] = %d\n", i, primes[i]);
		}

		//read as: for each integer x in the collection primes...
		for(int x : primes) {
			System.out.println(x);
		}		

```

# Arrays and Collections

* Basic arrays are supported, but you should not use them (unless you have to):

```java

		int n = 100;
		//create an array of 100 integers
		int arr[] = new int[n];
		//set the first value to 42:
		arr[0] = 42;
		//set the last element to 101:
		arr[arr.length-1] = 101;
		//IndexOutOfBounds Exceptions:
		//arr[-1] = 3;
		//arr[100] = 4;
```

```java

		List<Integer> numbers = new ArrayList<>();
		numbers.add(10);
		numbers.add(5);
		numbers.add(20);
		System.out.println(numbers);
		numbers.add(42);
		System.out.println(numbers);
		numbers.add(42);
		System.out.println(numbers);

		numbers.add(0, 101);
		System.out.println(numbers);

		numbers.add(3, -4);
		System.out.println(numbers);

		//remove stuff:
		numbers.remove(1);
		System.out.println(numbers);


		for(int i=0; i<numbers.size(); i++) {
			int x = numbers.get(i);
			System.out.println(x);
		}

		for(int x : numbers) {
			System.out.println(x);
		}
```

# Sets

```java

		//sets are unordered collections *unique* elements
		Set<Integer> numbers = new HashSet<>();
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		System.out.println(numbers);
		numbers.add(30);
		numbers.add(40);
		System.out.println(numbers);
		numbers.remove(40);
		System.out.println(numbers);

		for(int x : numbers) {
			System.out.println(x);
			//error: numbers.add(42);
		}
```

* Maps

```java
Map<Integer, String> nuidToName = new HashMap<>();

nuidToName.put(35140602, "Chris");
nuidToName.put(123, "Alice");
nuidToName.put(456, "Bob");
System.out.println(nuidToName);

Map<String, Integer> nameToNuid = new HashMap<>();

Map<Double, Integer> foo = new HashMap<>();
```

# Strings

* Java has a `String` class
  * No memory management
  * No null-terminating character
  * Basic concatenation: `+`
* Strings in Java are *immutable*: once created they cannot be changed, only new strings (modifications of other strings) can be created

```java

		String s = "hello world!";
		String t = s.toUpperCase();
		System.out.println(s);
		System.out.println(t);

		//mutable string version:
		StringBuilder sb = new StringBuilder();
		sb.append("hello world");
		System.out.println(sb);
		sb.append("!");
		System.out.println(sb);
		sb.replace(0, 1, "H");
		System.out.println(sb);
		sb.replace(6, 7, "W");
		System.out.println(sb);
		String str = sb.toString();

		String s = "Hello World";
		String firstWord = s.substring(0, 5);
		System.out.println(firstWord);
		String secondWord = s.substring(6);
		System.out.println(secondWord);

		String csvData = "Chris,Bourke,105 Schorr,School of Computing";
		String tokens[]  = csvData.split(",");

		System.out.println(Arrays.toString(tokens));
		for(String str : tokens) {
			System.out.println(str);
		}
```

## Methods

* In Java functions are called "methods"
* Methods are just function inside a class
* For now, all our methods will be `public static`: they will belong to the class
* To invoke or "call" a `static` function, you use the class name + the function name

```text















```
