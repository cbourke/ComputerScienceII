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

## Maps

* A `Map` can map an arbitrary type of object to any other type of object

```java


		Map<String, String> nickNames = new HashMap<>();
		//maps map keys to values
		nickNames.put("Christopher", "Chris");
		nickNames.put("Thomas", "Tommy");
		System.out.println(nickNames);

		String s = nickNames.get("Christopher");
		System.out.println(s);
		s = nickNames.get("Michael");
		System.out.println(s);

		Map<String, Integer> ages = new HashMap<>();
		ages.put("Tommy", 21);
		ages.put("Chris", 30);

		String name = nickNames.get("Christopher");
		int age = ages.get(name);
		System.out.println(age);
```

# Strings

* Java has a `String` class, no null terminating character, `'\0'`, no figuring out how big of an array you need, etc.  Java takes care of it for you.
* You can concatenate strings or any other type of object (which gives you a "string representation of it") using the `+` string concatenation operator
* In Java, `String`s are *immutable*: once created they cannot be changed

```java

		String s = "Hello World";
		System.out.println(s);
		String subs = s.substring(6);
		System.out.println(subs);

//		String t = s;
//		s = "Goodbye World!";
//		System.out.println(s);
//		System.out.println(t);

		StringBuilder sb = new StringBuilder();
		sb.append("Hello, ");
		sb.append(" how are you?");
		System.out.println(sb);
		sb.deleteCharAt(6);
		System.out.println(sb);
```

# Methods

* In Java we call functions "methods" because in Java everything is a class or belongs to a class
* For now, all our methods will be `static` methods: the methods belong to the class and not to instances of the class
* You can access/invoke methods using the class name + method name separated by a dot
* For now, we'll make all of our methods `public` so that any piece of code can use them/invoke them
* After `public static` you define the return type: `void, double, String,` etc. and use the `return` keyword


```java
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
```

# File I/O

## File Input

* There are dozens of ways of doing this; I'll show you the easiest

```java

		try {
			Scanner s = new Scanner(new File("data/data.csv"));

			//TODO: process the file
			while(s.hasNextLine()) {
				//a. read the line
				String line = s.nextLine();
				if(!line.isEmpty()) {
					// b. process the line: tokenize it along commas
					String tokens[] = line.split(",");
					// c. find the age and print it

					int age = Integer.parseInt(tokens[4]);
					System.out.println(tokens[0] + " is " + age + " years old");
				}
			}

			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
```

# File Output

* The easiest way is to use a `PrintWriter`

```java
File outputFile = new File("data/output2.txt");
try {
  PrintWriter pw = new PrintWriter(outputFile);
  pw.println("Hello, this is on one line");
  pw.print("there is no endline");
  pw.printf("%s, %d, %f\n", "Hello", 42, 3.14);
  pw.close();
} catch (FileNotFoundException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
}
```

# Error Handling

* Java supports using Exceptions for error handling
* An exception is an interuption fo the normal flow of control
* Potentially dangerous code can be surrounded by a `try-catch` block
* If an exception is *thrown* then the `catch` block will catch it and you can handle it in that block
* Java has two types of exceptions: a "checked" exception which *requires* you to surround the operation with a `try-catch` block and an "unchecked" exception
* So for most "checked" excpetions: go ahead and `try-catch` them, but then, release it: by throwing it as a new `RuntimeException`

```java


		int n = 0;
		File f = new File("data/input001.txt");
		try (Scanner s = new Scanner(f)){
			String str = s.nextLine();
			int x = Integer.parseInt(str);
			double y = x / n;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (NumberFormatException e) {
			//TODO: use a sensible default value
			System.out.println("Computing with default values...");
		} catch (ArithmeticException e) {
			//TODO: deal with this somehow
		} catch (Exception e) {
			//TODO: deal with a generic exception somehow
			throw new RuntimeException(e);
		}

		System.out.println("end of program");

//also, you can throw your own exceptions:
if(n == 0) {
  throw new RuntimeException("you cannot divide by zero, man!");
} else {
  double y = x / n;
}

```

## Searching & Sorting

```java

		List<Integer> a = Arrays.asList(-42, 4, 7, 3, 8, 2, 1);
		List<String> b = Arrays.asList("Apple", "apple", "zebra", "Banana", "orange");
		System.out.println(a);
		Collections.sort(a);
		System.out.println(a);

		System.out.println(b);
		Collections.sort(b);
		System.out.println(b);

		Comparator<Integer> cmpIntDesc = new Comparator<>() {

			@Override
			public int compare(Integer a, Integer b) {
				if(a < b) {
					return 1;
				} else if(a > b) {
					return -1;
				} else {
					return 0;
				}
			}

		};

		System.out.println(a);
		Collections.sort(a, cmpIntDesc);
		System.out.println(a);

		Comparator<String> cmpStringCaseInsensitive = new Comparator<>() {

			@Override
			public int compare(String a, String b) {
				return a.compareToIgnoreCase(b);
			}

		};

		System.out.println(b);
		Collections.sort(b, cmpStringCaseInsensitive);
		System.out.println(b);
```    

* Several variations of `Collections.binarySearch()`  are also available

```text






```    
