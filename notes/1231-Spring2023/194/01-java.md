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

# Variables

* Java supports:
  * `int` 32-bit integers (Max: 2147483647, min: -2147483648)
	* `double` floating point numbers with 64 bits of precision = 16 digits of precision
	* `char` yes, but why use them?
	* Use `String`s instead!

```java

		int x = 42;
		double y = 3.14159;
		String message = "Hello World";

		//basic operations:
		int a = 10;
		int b = 20;
		//integer truncation still occurs in Java:
		int c = a / b;
		System.out.println(c);

		//no:
		//a = 10.5;
		//okay, but why:
		a = (int) 10.5;
		//why?  Because:
		double d = a / (double) b;
		System.out.println(d);

		//operations:
		c = a + b;
		a = a - b;
		c = a * b;

		//math stuff:
		y = Math.sqrt(2);
		y = Math.sin(Math.PI);
```

# Standard I/O:

```java
		//standard input:
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter an integer: ");
		int x = s.nextInt();
		System.out.println("I read " + x);

		//standard output:
		System.out.println("Prints an endline character for you");
		System.out.print("Does not print an endline character for you\n");
		int x = 42;
		double y = 3.5;
		String t = "Hello";
		System.out.printf("x = %d, y = %10.2f, t = %s", x, y, t);
```

# CLAs = Command Line Arguments

* Java supports CLAs, use Eclipse: Run Configurations to specify CLAs

```java

		//check if there are a correct number:
		if(args.length != 2) {
			System.err.println("ERROR: expected 2 arguments");
			System.exit(1);
		}

		System.out.println("Number of CLAs = " + args.length);
		for(int i=0; i<args.length; i++) {
			System.out.printf("args[%d] = %s\n", i, args[i]);
		}

		//converting arguments:
		int x = Integer.parseInt(args[0]);
		double y = Double.parseDouble(args[1]);
```		

# Conditionals

* Java has `if`, `if-else` and `if-else-if` statements
* However: Java has `boolean` types built in
* In C: 0 = false, any non-zero value was true, this is NOT the case for Java
* Instead, you use `true` and `false`

```java

		boolean isStudent = true;
		if(isStudent) {
			System.out.println("Discount!");
		} else {
			System.out.println("NO Discount!");
		}

		int mavScore = 5;
		int dakotaScore = 3;
		if(mavScore > dakotaScore) {
			System.out.println("Mavs Win!");
		} else if(dakotaScore > mavScore) {
			System.out.println("Mavs Lost :(");			
		} else {
			System.out.println("Tie");						
		}
```

* More complex boolean expressions can be built using connectives:
* The logical and is expressed using `&&`
* The logical or is expressed using `||`
* The logical negation is `!`
* Numerical comparison operators: `<, <=, >, >=, ==, !=`
* HOWEVER: one differences is that you *CANNOT* use numerical comparisons for strings!

```java
String a = "9";
String b = "1200";
//you cannot compare strings:
//		if(a > b) {
//			System.out.println("Hello");
//		}

//compare lexicographically with case sensitivity:
int result = a.compareTo(b);
if(result < 0) {
	System.out.println(a + " comes before " + b);
} else if(result > 0) {
	System.out.println(b + " comes before " + a);
} else {
	System.out.println(a + " equals " + b);			
}

//compare lexicographically with case insensitivity:
result = a.compareToIgnoreCase(b);
if(result < 0) {
	System.out.println(a + " comes before " + b);
} else if(result > 0) {
	System.out.println(b + " comes before " + a);
} else {
	System.out.println(a + " equals " + b);			
}
```

# Loops

* Java supports both `for` and `while` loops

```java
int n = 10;
for (int i = 0; i < n; i++) {
	System.out.println(i);
}

int i = 0;
while(i < n) {
	System.out.println(i);
	i++;
}
```

* Java also has "enhanced for loops" (ie *foreach* loops)

```java
int primes[] = { 2, 3, 5, 7, 11, 13, 17, 19 };
for(int i=0; i<primes.length; i++) {
	System.out.println(primes[i]);
}

//"enhanced" for loop
//read this as "for (each) integer x in the collection primes"
for(int x : primes) {
	System.out.println(x);
}
```

# Arrays

```python

//declaration of an integer array:
int numbers[] = new int[200];

numbers[0] = 42;
numbers[199] = 101;
//throws an exception:
//numbers[200] = 1234;
for(int x : numbers) {
	System.out.println(x);
}
```

* In Java, you can use the `.length` "property" to determine the size of an array
* You don't have to keep track of the "bookkeeping" like you do in C
* Illegal operations result in an Exception being thrown
* You use standard 0-indexing: indices run from `0, 1, 2, ... .length -1`
* You create new array using the `new` keyword (not malloc)
* You can't destroy arrays; garbage collection is automatic, there is no `free()`!

## Better Collections

* Array suck, do not use them
* Instead use `List`, `Set` or  `Map` data structures

```java

		//a list of integers:
		List<Integer> numbers = new ArrayList<>();
		System.out.println(numbers);
		numbers.add(2);
		numbers.add(3);
		numbers.add(5);
		numbers.add(42);
		System.out.println(numbers);
		numbers.remove(0);
		System.out.println(numbers);
		numbers.add(1, 42);
		System.out.println(numbers);
		int n = numbers.size();
		System.out.println(n);
		//regular loop:
		for(int i=0; i<numbers.size(); i++) {
			System.out.println( numbers.get(i) );
		}

		//you can also use sets
		//A set is an *unordered* collection of *unique* elements
		Set<String> names = new HashSet<>();
		names.add("Chris");
		names.add("Gabe");
		names.add("Alice");
		System.out.println(names);
		names.add("Chris");
		System.out.println(names);
		for(String name : names) {
			System.out.println( name );
		}
		names.contains("Chris"); //true
		names.contains("Bob");  //false

		//convert between the two:
		Set<Integer> uniqueNumbers = new HashSet<>(numbers);
		System.out.println(uniqueNumbers);
```

* Maps

```java

		//a map maps a key to a value
		Map<Integer, String> nuidMap = new HashMap<>();
		Map<String, Integer> reverseNuidMap = new HashMap<>();

		nuidMap.put(35140602, "Chris");
		nuidMap.put(12345678, "Bob");
		nuidMap.put(87654321, "Alice");
		System.out.println(nuidMap);

		reverseNuidMap.put("Chris", 35140602);
		reverseNuidMap.put("Bob", 12345678);
		reverseNuidMap.put("Alice", 87654321);
		System.out.println(reverseNuidMap);

		//retrieve an element using its key
		String name = nuidMap.get(35140602);
		System.out.println(name);

		name = nuidMap.get(11111111);
		System.out.println(name);
```		

# Strings

* Java has a `String` class:
  * no memory management
  * No null terminating character!
  * concatenation: use the `+`
* Strings in Java are *immutable*: once created they cannot be changed!
* There is a *mutable* version of strings: `StringBuilder`

```java

		String a = "hello world";
		String b = a.toUpperCase();
		System.out.println(a);
		System.out.println(b);

		//substrings:
		String s = a.substring(6);
		System.out.println(s);
		s = a.substring(0, 5);
		System.out.println(s);

		//processing CSV data
		String csvData = "Chris,Bourke,Schorr 105,Lincoln,NE";
		String tokens[] = csvData.split(",");
		System.out.println(Arrays.toString(tokens));
		for(String token : tokens) {
			System.out.println(token);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("hello");
		sb.append(" World");
		System.out.println(sb);
		sb.replace(0, 1, "H");
		System.out.println(sb);
		String t = sb.toString();
```

* Aside: shallow vs deep copies

```java

		int primes[] = {2, 3, 5, 7, 11, 13};
		//Shallow copy:
		int primesTwo[] = primes;

		primesTwo[0] = 4;
		System.out.println(Arrays.toString(primes));
		System.out.println(Arrays.toString(primesTwo));

		primes[0] = 2;
		//deep copy:
		primesTwo = Arrays.copyOf(primes, 20);
		primesTwo[0] = 4;
		System.out.println(Arrays.toString(primes));
		System.out.println(Arrays.toString(primesTwo));
```

## Methods

* In Java, "functions" are referred to as "methods"
* Methods are functions that are located inside a class
* For now all our methods will be `static`: they "belong" to the class itself and can be called using the class name
* Math library: `sqrt()`

```java
package uno.ece;

import java.util.Arrays;

public class Demo {

	//write a method that takes an array of integers and adds one to each value
	// {2, 3, 5, 7, 11} -> {3, 4, 6, 8, 12}

	public static void addOne(double x) {
		x++;
	}

	/**
	 * Adds one to each element in the given array.
	 *
	 * @param arr
	 */
	public static void addOne(int arr[]) {

		for(int i=0; i<arr.length; i++) {
			arr[i]++;
		}
	}

	/**
	 * Creates a new copy of the given array with one added
	 * to each element, returning the new copy.
	 * @param arr
	 */
	public static int[] addOneCopy(int arr[]) {

		int copy[] = Arrays.copyOf(arr, arr.length);
		Demo.addOne(copy);
		return copy;
	}


	public static void main(String[] args) {

		//syntax: Class.functionName()
		double y = Math.sqrt(2);
		//other examples:
		int x = Integer.parseInt("123");

		//arrays are passed by reference
		int primes[] = {2, 3, 5, 7, 11, 13};
		System.out.println(Arrays.toString(primes));
		Demo.addOne(primes);
		System.out.println(Arrays.toString(primes));

		//variables are passed by value
		y = 10;
		Demo.addOne(y);
		System.out.println(y);

		//note: Java has "function overloading"
		Math.abs(y);


	}

}
```

## File I/O

* File Output: many ways, the easiest is to use a `PrintWriter`

```java
File f = new File("data/foo.txt");
PrintWriter pw;
try {
	pw = new PrintWriter(f);
	pw.println("Hello World");
	pw.print("This is a line without an endline");
	pw.print("See?\n");
	pw.printf("%d, %s, %f\n", 123, "Hello", Math.PI);
	pw.close();
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
```

* File Input line by line:

```java

		//file input: easiest is to use a Scanner
		File f = new File("data/bar.txt");
		try {
			Scanner s = new Scanner(f);
			//read the file line by line
			while(s.hasNextLine()) {
				String line = s.nextLine();
				System.out.println(line);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
```

# Error Handling

* Java uses Exceptions for error handling
* An exception is an interruption of the normal flow of control
* You can surround a *potentially dangerous* piece of with a `try-catch` block

```java

		//normal math errors, no exceptions are thrown
		double a = 10;
		double b = 0;
		double c = a / b;
		c = Math.log(b);
		c = Math.sqrt(-a);
		System.out.println(c);

		Double x = 10.0;
		Double y = null;
		Double z;

		//unchecked exception:
		//z = x / y;

		try {
			z = x / y;
			System.out.println(z);
		} catch(Exception e) {
			System.err.println("Something happened, something went wrong...");
			e.printStackTrace();
			//TODO: decide what you want to do in this case
			//we set a default value of 10
			z = 10.0;
		}

		System.out.println("Hello");
		//some exceptions are "unchecked" (like above): handling it with try-catch
		//  is *optional*
		//some exceptions are "checked": you are forced to surround the code
		//  with a try-catch block, regardless of how you handle the exception
		File f = new File("data/baz.txt");
		try {
			Scanner s = new Scanner(f);
		} catch (FileNotFoundException e) {
			//to deal with checked exceptions, you "catch and release"
			throw new RuntimeException(e);
		}
```

## TODO: Classes, Searching & Sorting


```text








```
