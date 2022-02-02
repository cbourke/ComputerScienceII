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

    //"enhanced" (for each) loop
		for(int x : numbers) {
			System.out.println(x);
		}
  ```

## Lists

* A `List` is an ordered collection of elements
* Typically you use an `ArrayList`
* It dynamically and automatically grows/shrinks as you add/remove stuff

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

* A `Set` is an *unordered* collection of *unique* elements
* No order (no first element, no second, etc.)
* No duplicates, adding stuff multiple times has no effect
* Use an enhanced for loop to iterate over a set

```java

		Set<String> names = new HashSet<>();

		names.add("Chris");
		names.add("Joe");
		names.add("Jane");
		names.add("John");
		names.add("Darrell");
		names.add("Darrell");
		System.out.println(names);
		names.add("Bob");
		System.out.println(names);

		for(String name : names) {
			System.out.println(name);
		}
		//names.remove("Chris");
		System.out.println(names);
		if(names.contains("CHRIS")) {
			System.out.println("I'm still in there!");
		}
		```

```java

		//map names to ages...
		//string (keys) representing names
		// will map to values representing an age
		Map<String, Integer> names = new HashMap<>();

		names.put("Chris", 32);
		names.put("Joe", 45);
		names.put("Jane", 39);
		names.put("John", 22);
		names.put("Darrell", 24);
		names.put("Darrell", 23);
		names.put("Bob", 92);
		System.out.println(names);

		names.put("Bob", 20);
		System.out.println(names);
		names.remove("Bob");
		System.out.println(names);

		Integer joesAge = names.get("Joe");
		System.out.println(joesAge);

		for(String name : names.keySet()) {
			Integer value = names.get(name);
			System.out.println(name + " is " + value + " years old!");
		}
```

# Strings

* Java has a `String` class:
  * no memory management
	* No null terminating character!
	* concatenation: use the `+`
* Strings are immutable: once created, they cannot be changed

```java

		String msg = "Hello World!";
		String converted = msg.toUpperCase();

		System.out.println(msg);
		System.out.println(converted);

		//get a particular character from a string:
		char c = msg.charAt(0);
		System.out.println(c);

		//substring?
		String sub = msg.substring(6, 11);
		System.out.println(sub);

		String csvData = "Chris,Bourke,Schorr,105";
		String toks[] = csvData.split(",");
		System.out.println(Arrays.toString(toks));
		List<String> tokens = List.of(toks);
		System.out.println(tokens);
		Set<String> uTokens = Set.of(toks);
		System.out.println(uTokens);


				StringBuilder sb = new StringBuilder();
				sb.append("hello");
				sb.append(" World!");
				sb.append("\n");
				System.out.println(sb);
				sb.setCharAt(0, 'H');
				System.out.println(sb);
				String finalStr = sb.toString();
				System.out.println(finalStr);
```

### Methods

* In java functions are called "methods"
* Methods must be defined within a class
* For now, all our methods will be `static`: they will belong to the class
* It is either necessary or best practice to access/call those methods using the dot operator: ex: `Math.sqrt()`, `Isotope.getMass()`
* For now, all of our methods will be `public`
* The visibility keywords:
  * `public` - any other piece of code can "see" and thus call that method
	* `private` - only the class can "see" the method
	* `protected` - the class and its subclasses can "see" it
	* (no keyword) - "package" protected and any class in the same package can "see" it
* You also specify a return type: the type of variable the method returns
* If non-`void` you MUST return a value, you use the keyword `return` (for `void` methods you should still use a `return;` statement)
* All non-trivial methods need documentation!

## File I/O

### File Input

```java
//read in a file and process it line by line
		try( Scanner s = new Scanner(new File("data/file.txt")) ) {
			while(s.hasNextLine()) {
				String line = s.nextLine();
				System.out.println(line);				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String contents;
		try {
			contents = Files.readString(Paths.get("data/file.txt"));
			System.out.println(contents);

			List<String> lines = Files.readAllLines(Paths.get("data/file.txt"),
					  StandardCharsets.UTF_8);
			System.out.println(lines);




		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
```

### File Output

```java
try {
	PrintWriter pw = new PrintWriter(new File("src/unl/cse/Demo.java"));
	pw.println("Hello world!");
	pw.println("Hello world!");
	pw.println("Hello world!");
	pw.print("no endline!");
	pw.printf("%s, %d\n", "goodbye", 42);
	pw.close();
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
```

# Error Handling

* Java supports using Exceptions for error handling
* An exception is an interruption of the normal flow of control
* You can surround a *potentially dangerous* piece of with a `try-catch` block
* If/when an exception or error is `throw`n control flow goes to the first or most appropriate `catch` block in which you can decide how to *handle* the excpetion
* Generally you allow or want exceptions to be fatal: to end the program
  * You can provide some reasonable default "handling" code if you want
	* You can print an error message/stack trace and exit
	* Ignore it
	* You can rethrow it and let the JVM handle it

```java

		int n = 0;
		File f = new File("data/file.txt");
		Scanner s;
		try {
			s = new Scanner(f);
			String lineOne = s.nextLine();
			int x = Integer.parseInt(lineOne);
			int c = x / n;
		} catch(FileNotFoundException e)  {
			//e.printStackTrace();
			//TODO: do something else
			//System.exit(1);
			throw new RuntimeException(e);
		} catch(NumberFormatException e) {
			//define a default: x = 10;
			throw new RuntimeException(e);
		} catch(Exception e) {
			throw new RuntimeException(e);			
		} finally {
			//s.close();
		}

//		if(n == 0) {
//			throw new RuntimeException("You cannot divide by zero, noob");
//		}

		System.out.println("end program");
```

## Searching & Sorting

* IN Java, you use the built-in sorting method, `Collections.sort`
* To get a custom ordering you need a `Comparator`
* General contract:
  * It takes two objets, `a, b`
	* It returns *something* negative if `a < b`
	* It returns *something* positive if `a > b`
	* It returns zero if `a == b`

```java

		//create "something" to sort integers in *decreasing order*
		Comparator<Integer> cmpIntDescending = new Comparator<>() {
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

		Comparator<String> cmpStrIgnoreCase = new Comparator<>() {
			@Override
			public int compare(String a, String b) {
				return a.compareToIgnoreCase(b);
			}			
		};

		Comparator<Character> cmpCharsDesc = new Comparator<>() {
			@Override
			public int compare(Character a, Character b) {
				return -a.compareTo(b);
			}			
		};

		List<Integer> list = Arrays.asList(8, 3, 9, 2, 7, 1, 0, 4, 7);
		System.out.println(list);
		Collections.sort(list, cmpIntDescending);
		System.out.println(list);

		List<String> names = Arrays.asList("Chris", "bob", "Joe", "Anita", "Anita", "Bob");
		System.out.println(names);
		Collections.sort(names, cmpStrIgnoreCase);
		System.out.println(names);

		List<Character> chars = Arrays.asList('a', 'A', 'B', '!', 'z', 'b', 'q', 'y');
		System.out.println(chars);
		Collections.sort(chars, cmpCharsDesc);
		System.out.println(chars);

		List<Student> roster = Arrays.asList(
				new Student("Chris", "Bourke", "1234", 3.5),
				new Student("Joe", "Bourke", "4567", 3.25),
				new Student("Anita", "Bourke", "2222", 3.75),
				new Student("J.", "Anderson", "08392", 3.15),
				new Student("Q.", "Zoe", "328179", 2.5)	);		

		Comparator<Student> cmpStudentByName = new Comparator<>() {
			@Override
			public int compare(Student a, Student b) {
				int r = a.getLastName().compareTo(b.getLastName());
				if(r == 0) {
					//must break the same last name tie using the first name...
					return a.getFirstName().compareTo(b.getFirstName());
				}
				return r;
			}			
		};

//		Comparator<Student> cmpStudentByGpa = new Comparator<>() {
//			@Override
//			public int compare(Student a, Student b) {
//				if(a.getGpa() < b.getGpa()) {
//					return 1;
//				} else if(a.getGpa() > b.getGpa()) {
//					return -1;
//				} else {
//					return 0;
//				}
//			}			
//		};

		Comparator<Student> cmpStudentByGpa = Comparator.comparing(Student::getGpa).reversed();

		Comparator<Student> cmpStudent = Comparator.comparing(Student::getLastName)
				.thenComparing(Student::getFirstName)
				.thenComparing(Student::getGpa)
				.reversed();


		System.out.println(roster);
		Collections.sort(roster, cmpStudentByGpa);
		System.out.println(roster);


```

Searching:

```java

		int key = 7;

		List<Integer> list = Arrays.asList(8, 3, 9, 2, 7, 7, 7, 7, 7, 1, 0, 4, 7);
		System.out.println(list);

		//basic linear search:
		int index = list.indexOf(key);
		System.out.printf("\"found\" %d at index %d\n", key, index);

		//linear search on sets:
		Set<Integer> setOfNums = new HashSet<>(list);
		boolean exists = setOfNums.contains(key);
		System.out.println(exists);

		Collections.sort(list);
		System.out.println(list);

		//binary search on lists:
		index = Collections.binarySearch(list, key);
		System.out.printf("\"found\" %d at index %d\n", key, index);
```

```text




```
