# Computer Science II - CSCE 156
## Spring 2026
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
 * This is a basic hello world program
 *
 * ChrisBourke
 * 2026-01-12
 */
public class HelloWorld {

	public static void main(String args[]) {

		System.out.println("Hello World!!");
	}

}
```

## Comments

* Multiline comments begin with `/*` and end with `*/`
* Ex

```java
/* This is a multi line comment
   it can span as many lines
   as you want
 */
```

* Single line comments: `//`
* Doc style comments: `/**` and have a vertical line of starts + end with `*/`
* For now: all programs require an author header in doc-style comments

## Misc

* All executable lines end with a semicolon `;`
* Strings are denoted using double quotes: `"this is a string"`
* Standard output:
  * `System.out.println` prints with an endline character for you
  * `System.out.print`
  * `System.out.printf` prints in a formatted manner

## Variables

* Java is a statically typed language: you have to declare a variable before you can use it
* YOu also have to declare the variable's *type*
  * `int` integer (at most 2147483647, at least 2147483648)
  * `double` is a floating point number (fractional) that has 17 digits of accuracy
  * `char` a single Unicode character
* Variable names can contain lower case, uppercase letters and numbers, also `_`, etc.
  * By convention: all variables should be `lowerCamelCase`
  * Use *descriptive* variable names, avoid `x, y, foo`, etc.
  * Good examples: `numberOfStudents`, `upperBound` `isDone`
* Generally:
  * Avoid *cruft* code should be self-documenting
  * For now: we expect author documentation for *every class* and documentation for *non-trivial* methods (more later)
* In Java "strings" are a built-in type
  * Use `String`
  * No memory management
  * No null terminating character
  * String concatenation: use the `+` (you can *mix* numbers and strings!)

```java
// TODO: declare and print some variables
		int a = 42;
		double x = 4.125;

		//cannot do:
		//int b = 4.5;

		//yes you can:
		//double y = 42;

		char initial = 'C';

		System.out.println(a);
		System.out.println("a = " + a);

		System.out.println(x);

		//you can use formatted output:
		System.out.printf("x = %f\n", x);
		//for the purposes of printing ONLY, it will
		//round; the variable is NOT changed
		System.out.printf("x = %.2f\n", x);
		System.out.printf("x = %.20f\n", x);

		System.out.printf("x = %.20f\n", Math.PI);
		//more math stuff:
		//all the usual math functions are in the
		//Math class
		double y = Math.sin(Math.PI);
		System.out.println(y);
		y = Math.cos(Math.PI);
		System.out.println(y);

		y = Math.log(10);
		System.out.println(y);

		y = Math.log10(10);
		System.out.println(y);

		y = Math.sqrt(2.0);
		System.out.println(y);

		/*
		 * CRUFT, get rid of it!
		 Old debugging code:
		 y = Math.sqrt(10);
		 System.out.println(y);
		 */

		String firstName = "Chris";
		String lastName = "Bourke";
		String fullName = firstName + " " + lastName;

		System.out.println(fullName);
```

## Operations

* You have basic math: `+, -, *, /`
* However, you have *integer truncation* with division

```java
int a = 10;
		int b = 20;
		int c = a / b;
		System.out.println(c);

		double x = 10;
		double y = 20;
		double z = x / y;
		System.out.println(z);

		//fix:
		z = (double) a / b;
		System.out.println(z);
```

* We also have integer remainder division: `%`
  * `10 % 3` results in 1
  * `10 % 2` results in 0
  * `10 % 7` results in 3
* Often you want to use *constants* ("variables" that cannot be changed once set), use the modifier `final`

# Basic I/O

* Interactive Input: you can read keyboard inputs if you want (but generally you don't want to)
* Command line arguments (CLAs)
  * Non-interactive input: you provide *command line arguments* as input to your program

```java
int numArgs = args.length;
System.out.println(numArgs);

//convert them:
int x = Integer.parseInt(args[0]);
double y = Double.parseDouble(args[1]);
String foo = args[2];

System.out.printf("x = %d, y = %f, foo = %s\n", x, y, foo);
```

# Conditionals

* You have traditional `if`, `else-if` and `if-else-if`

```java
int huskerScore = 90;
int oregonScore = 55;

if(huskerScore > oregonScore) {
	System.out.println("Huskers Win!");
} else if(huskerScore < oregonScore){
	System.out.println("Oregon Wins");
} else {
	System.out.println("Tie, go to OT");
}
```

* Numerical comoparison operators: `<, <=, >, >=, !=, ==`
  * These are ONLY for numbers, NEVER strings
  * Java has `boolean` variables

```java
boolean isStudent = false;
isStudent = true;

if(isStudent) {
	System.out.println("BB ticket discount!");
}

```

* Boolean operators: logical and, or not, etc.
  * Logical And: `&&`  
  * Logical Or: `||`  
	* Logical negation: `!`

```java
if( !(isStudent && age < 21) ) {
	System.out.println("BB ticket discount! no beer");
}
```

* More examples:

```java

		double a = 10;
		double b = 20;
		double c = 1;

		double root1 = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
		double root2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);

		if(b*b - 4*a*c < 0) {
			System.out.println("I cannot handle complex roots");
		} else {
			System.out.println("Roots: " + root1 + ", " + root2);
		}

		double x = a / 0;
		System.out.println(x);

		x = Math.log(0);
		System.out.println(x);
```

# Loops

* Java has `for` loops and `while` loops
* Java also has "enhanced for loops"

```java

		int n = 10;
		for(int i=0; i<n; i++) {
			System.out.println(i);
		}

		for(int i=2; i<n; i+=3) {
			System.out.println(i);
		}

		System.out.println("=-=-=-=-=-=-=-=");
		int i = 0;
		while(i<n) {
			i++;
			System.out.println(i);
		}
		System.out.println(i);

		//enhanced for loops:
		// you can iterate over an array, list, set, etc.
		int primes[] = {2, 3, 5, 7, 11, 13, 17};
		for(i=0; i<primes.length; i++) {
			System.out.printf("primes[%d] = %d\n", i, primes[i]);
		}
		//for each integer x in the collection primes...
		for(int x : primes) {
			System.out.println(x);
		}
```

# Arrays and Collections

* Java has basic arrays
* They are 0-indexed
* They are created with a *fixed size*, they cannot grow, shrink, etc.

```java
int n = 10;
int numbers[] = new int[n];

numbers[0] = 1;
numbers[n-1] = 42;
System.out.println(Arrays.toString(numbers));

//both invalid, results in an exception
numbers[10] = 102;
numbers[-1] = 102;

```

## Lists

* Lists are *ordered* collections of similar elements
* Duplicates are allowed
* Dozens of methods to help process data

```java

		List<Integer> numbers = new ArrayList<>();
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		System.out.println(numbers);
		numbers.add(40);
		System.out.println(numbers);
		numbers.add(0, 50);
		System.out.println(numbers);
		numbers.add(3, 60);
		System.out.println(numbers);

		numbers.remove(0);
		System.out.println(numbers);
		numbers.remove(2);
		System.out.println(numbers);

		//get the 1-th element:
		int x = numbers.get(1);
		System.out.println(x);
		for(int y : numbers) {
			System.out.println(y);
		}
```

## Sets

* A set is an *unordered* collection of *unique* elements (no duplicates)
* ex:

```java

		Set<String> names = new HashSet<>();

		names.add("Chris");
		names.add("Alex");
		names.add("Jane");
		names.add("John");

		System.out.println(names);

		//duplicates are not allowed
		names.add("Chris");
		System.out.println(names);

		//instead, you use a for loop:
		for(String name : names) {
			System.out.println(name);
		}

		names.remove("Chris");
		System.out.println(names);

		//convert the set to a list:
		List<String> namesList = new ArrayList<>(names);
		System.out.println(namesList);
		namesList.add("Alex");
		System.out.println(namesList);

		//convert a list back to a set:
		Set<String> noDups = new HashSet<>(namesList);
		System.out.println(noDups);
```

## Maps

* A map is a key-value pair collection
* It stores elements using a key that maps to a value

```java

		//nuid to last name
		Map<Integer, String> nuidToLastName = new HashMap<>();

		//values may contain duplicates, but...
		nuidToLastName.put(35140602, "Bourke");
		nuidToLastName.put(12345678, "Bourke");
		nuidToLastName.put(12121212, "Bourke");
		System.out.println(nuidToLastName);

		//but keys cannot, keys are unique!
		nuidToLastName.put(35140602, "Jones");
		System.out.println(nuidToLastName);

		//retrieval: you need the key
		String foo = nuidToLastName.get(35140602);
		System.out.println(foo);

		foo = nuidToLastName.get(55555555);
		System.out.println(foo);

		//iterate over a map:
		//iterate over the keys:
		for(Integer key : nuidToLastName.keySet()) {
			String value = nuidToLastName.get(key);
			System.out.println(key + " maps to " + value);
		}

		nuidToLastName.remove(35140602);
		System.out.println(nuidToLastName);

		//iterate over a map's values:
		for(String name : nuidToLastName.values()) {
			System.out.println(name);
		}
```

# Strings

* Java has a `String` class/type
  * No memory management
  * No null-terminating character
  * Basic concatenation: `+`
* In Java, strings are *immutable*: once created, the contents of the string *cannot* be changed
* LOTS of nifty methods to call in the string library: RTM = Read the Manual!

```java

		String name = "chris";
		System.out.println(name);
		String upper = name.toUpperCase();
		System.out.println(name);
		System.out.println(upper);
		//this creates a brand new string:
		name = "Christopher Bourke";

		//dozens of nice methods:
		String lastName = name.substring(12);
		System.out.println(lastName);
		String firstName = name.substring(0, 5);
		System.out.println(firstName);

		System.out.println(firstName.length());

		//comparing strings
		String a = "apple";
		String b = new String("apple");

		if(a.equals(b)) {
			System.out.println("equal!");
		} else {
			System.out.println("not equal");
		}

		//ordering is based on ASCII table = lexicographic ordering
		a = "123";
		b = "9";

		if(a.compareTo(b) < 0) {
			System.out.println(a + " comes before " + b);
		} else if(a.compareTo(b) > 0) {
			System.out.println(b + " comes before " + a);
		} else {
			System.out.println(a + " equals " + b);

		}
```

## Methods/Functions

* In Java functions are called "methods"
* Methods are just function inside a class

```java
/**
 *
 * Computes the Euclidean distance between the two given points <code>(x1, y1)</code>
 * and <code>(x2, y2)</code>.  For more information, see <a href="https://en.wikipedia.org/wiki/Euclidean_distance">here</a>
 *
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @return
 */
public static double getEuclideanDistance(double x1, double y1, double x2, double y2) {

	double distance = Math.sqrt( (x1-x2) * (x1-x2) + (y1-y2) * (y1-y2) );
	return distance;
}
```

* All NON-Trivial methods require full doc-style comments!
* All of your first assignment classes require author documentation!
* All functions are pass-by-value for variables (there are no pointers!)
* However, if you pass collections (set, list, map) then they *are* passed by reference
  * A method *can* make changes to the collection!
* Generally:
  * use `lowerCamelCasing` for function names
  * They should be *verbs*
* For now all our functions will be `public static`
  * `public` - all of your code can "see" it and therefore use it
  * `static` - it belongs to the class and so you call it or invoke it using the `ClassName.functionName()`
* Java supports method overloading: more than one function can have the same name!
  * Ex: `Math.abs`: there are 4 versions!
* Use `return` to return a variable value to the calling function
* You can define a `void` function that does not return anything
* For this class: ALL non-trivial functions *require* doc-style comments/documentation
  * Trivial function: `main`

# File I/O

* There are always 3 steps:
  1. Open The file
	2. Process: read or write
	3. Close the file

## File Output

```java

		int x = 42;
		double y = 3.5;
		String name = "Jane";

		File f = new File("data/output.txt");
		PrintWriter pw;
		try {
			pw = new PrintWriter(f);
			pw.println("Hello World!");
			pw.print("Goodbye Everyone!\n");
			pw.printf("x = %d, y = %f, hello %s\n", x, y, name);
			pw.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
```

## File Input

* Use a `Scanner` and read line-by-line

```java

		//read in the books.csv data line by line
		File f = new File("data/books.csv");
		String line = null;
		try {
			Scanner s = new Scanner(f);
			line = s.nextLine();
			while(s.hasNextLine()) {
				line = s.nextLine();
				//process the data in each line...
				//tokenize each line and get each piece of data separately
				//Book Id,Title,Author last,Author first,ISBN,Average Rating,Publisher,Original Publication Year,Date Added
				String tokens[] = line.split(",");
				int bookId = Integer.parseInt(tokens[0]);
				String title = tokens[1];
				String authorLastName = tokens[2];
				String authorFirstName = tokens[3];
				String isbn = tokens[4];
				double averageRating = Double.parseDouble(tokens[5]);
				//TODO: finish this up!
				System.out.printf("%s by %s, %s\n", title, authorLastName, authorFirstName);
			}

			s.close();
		} catch (Exception e) {
			System.err.println("Something when wrong on this line: " + line);
			throw new RuntimeException(e);
		}

```

# Sorting


```java

		List<Integer> numbers = Arrays.asList(5, 8, 3, 4, 2, 0, 3);
		System.out.println(numbers);
		// the default "natural" ordering is always ascending (non-decreasing)
		Collections.sort(numbers);
		System.out.println(numbers);

		// I want a different order: descending!
		Comparator<Integer> cmpIntDesc = new Comparator<>() {

			@Override
			public int compare(Integer a, Integer b) {
				if (a < b) {
					// they are out of order = in ascending order
					// return something positive
					return 1;
				} else if (a > b) {
					// they are in order, something negative
					return -1;
				} else {
					return 0;
				}
			}

		};

		Collections.sort(numbers, cmpIntDesc);
		System.out.println(numbers);

		Comparator<Integer> cmpIntDescending = Comparator.reverseOrder();

		List<String> names = new ArrayList<>();
		names.add("allen");
		names.add("Chris");
		names.add("Jane");
		names.add("John");
		names.add("Allen");
		Collections.sort(names);
		System.out.println(names);

		Comparator<String> cmpIgnoreCaseStrRev = new Comparator<>() {

			@Override
			public int compare(String a, String b) {

				int result = -a.compareToIgnoreCase(b);
				return result;
			}

		};		

		Collections.sort(names, cmpIgnoreCaseStrRev);
		System.out.println(names);

		Comparator<String> cmpIgnoreCaseDesc = Comparator.comparing(String::toUpperCase);
		Collections.sort(names, cmpIgnoreCaseDesc);
		System.out.println(names);


```

* Search: always use binary search!  It is exponentially faster!

```java
List<Integer> numbers = Arrays.asList(5, 8, 3, 4, 2, 0, 3);
int key = 5;
Comparator<Integer> cmpIntDesc = Comparator.reverseOrder();
Collections.sort(numbers, cmpIntDesc);
System.out.println(numbers);
int index = Collections.binarySearch(numbers, key, cmpIntDesc);
if (index >= 0) {
	System.out.printf("SUCCESS: element %d is at index %d\n", key, index);
} else {
	System.out.printf("FAILURE: no such element\n");
}
```

# Classes

* Classes are a mechanism by which you can do **encapsulation**
  * Group data together into one logical unit
  * Protect the data (`private`)
  * Group functionality that operates on that data within the same unit (for now: constructors, `toString()`, getters)


```text














```
