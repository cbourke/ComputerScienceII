
# CSCE 156H - Computer Science II Honors
## Quick Intro to PHP

* Created in 1995 by Rasmus Lerdorf (Personal Home Page Tools)
* Collection of C modules for creating dynamic web pages
* C/HTML influence: C-style syntax, lack of function overloading, case-insensitivity (!)
* PHP: Hypertext Preprocessor

## Key Aspects

* Interpreted, not compiled
* use semicolons, curly brackets for code blocks, naming identifier rules
* Support "classes" (as of version 3)
* Dynamically typed: you do not declare variables or their type, you just use and assign them

## Boiler Plate

* A typical PHP script begins with `<?php` and ends with `?>`

```php
<?php
  print "Hello World\n";
?>
```

* You can interleave HTML and PHP


```html
<html>

<body>

<h1>My First HTML/PHP Program</h1>
<?php

  print "Hello World\n";

?>

<p>Today is a good day.</p>

<?php

$a = 10;
printf("<p>The value of a is %d</p>", $a);

?>

</body>
</html>
```

## Variables

* All variables names must begin with a `$`
* Naming conventions: `$lowerCamelCasing`
* Names are case sensitive
* You don't declare variables, you just assign (and reassign)
* Internally, PHP supports integers, doubles, strings, booleans, arrays, objects
* PHP supports `true` and `false` (case insensitive, but use lower case)
* No pointers, no memory management, no dynamic allocation, automated garbage collection
* Concatenation is done with a period

```php
$firstName = "Chris";
$lastName = 'Bourke';
$fullName = $lastName . ", " . $firstName;
```

* You can define *global* constants using `define`

```php
define('PI', 3.14159);
//constants are case insensitive:
$area = PI * $radius * $radius;
//or:
$area = pi * $radius * $radius;

//you can NOT redefine constants:
define('PI', 3);
```

### Type Juggling

* Because PHP is dynamically typed, variable types can change, so when you mix types, weird stuff happens
* In general, "adding" (`+`) a string, parses that string (as best as it can) to get a numerical value
  * "123" parses as 123
  * "hello" parses as 0
  * "123hello" parses as 123
  * "3.14" parses as 3.14
  * "3.43e4" parses as 34300
* BUT in general: do not rely on juggling
* Be explicit in your conversions:

```php
$a = "120.12";
$val = doubleval($a);
$b = "120";
$val2 = intval($b);
```

* you can test string values and variables:
  * `is_numeric($a)` returns true if the value is a number or a "pure" numerical string
  * `is_numeric("120")` true, but `is_numeric("120foo")` false
  * `is_int($a)` is true if the variable `$a`'s type is an integer
  * `is_float($a)` 
* PHP has the keyword `null` (case insensitive)
* You can also test for null: `is_null($a)` returns true if `$a` is null or if it has not yet been set
* You can use `isset($a)` which returns true only if hte variable `$a` has not been set, false if it has never been used before


## Operators

* Arithmetic: `+ - * / %`
* Increment: `++ --`
* Concatenation: `.`
* Logical operators: `!, &&, ||`
* Comparison operators: `< <= > >= == !=`
* Unfortunately: you *can* use the above operators with strings
* Instead you should use: `strcmp` or `strncmp` or `strcasecmp`
* What about comparing strings to strings, integers to integers
* Equality operators (strict): `=== !==` true/false only if the arguments' types AND values match

### Bad Math

```php

$a = 1 / 0; //INF, and a warning
$b = sqrt(-1);  //NAN

//checkers:
is_nan($b);
is_infinite($a);
```

## Strings

* Static strings are denoted with single or double quotes
* Strings are *mutable* by treating them as a zero-indexed array
* You can use other C functions such as 
  * `strlen()` returns the length of the string (THERE ARE NO NULL TERMINATING CHARACTERS)
  * `explode($delimiter, $str, [$limit])`
  * `preg_split($pattern, $str, [$limit])` splits along the given regular expression
  * `substr($str, $startIndex, [$length])` gives a substring starting at `$startIndex`
  * `str_replace($needle, $replace, $haystack)` replaces any instance of `$needle` in the string `$haystack` with the contents of `$replace`
  * `preg_replace($needle, $replace, $haystack)` replaces based on a regular expression

## Arrays

* PHP doesn't have arrays
* PHP has "associative arrays"
* Be default, associative arrays can be treated like traditional 0-indexed arrays
* No fixed size (grow dynamically)
* Under the hood: hash maps

```php

//empty array:
$arr = array();

$arr = array(10, 20, 30);

//mixed type array
$arr = array(10, 3.5, "ten");

//add an element to the end:
$arr[30] = "hello";

$arr["ten"] = 10;

$arr["3.5"] = "40foo";

//try: find the size of the array
$n = count($arr);
print "there are $n things in the array!\n";
for($i=0; $i<$n; $i++) {
  print $arr[$i] . "\n";
}

//better solution: use a foreach loop
foreach($arr as $key => $val) {
  print $key . " maps to " . $val . "\n";
}

$arr = array(
  "Christopher" => "Chris",
  "Joseph" => "Joe",
  "Angela" => "Angie"


);

//other syntax:
$arr[30] = "foo";
$arr[40] = "bar";
$arr[] = "baz";


print_r($arr);

//delete "baz"
unset($arr[41]);

//delete the entire array:
unset($arr);

```

# Command Line Arguments

* Are stored in an array called `$argv`, the number of arguments is stored in `$argc` or you can use the `count($argv)` function
* The first argument is always the script file's name


# File I/O

* Basics: open the file, process it, close it
* PHP Supports `fopen`, `fclose`, `fgets`

```php
$fileName = "input.txt";

//check before you open that it exists:
if( !file_exists($fileName) ) {
  printf("ERROR: cannot open file %s\n", $fileName);
  exit(1);
}

$h = fopen($fileName, "r");

//read one line of the input file:
$line = fgets($h);

//remove the endline character:
$line = trim($line);

printf("%s\n", $line);

//read all the lines:
while( !feof($h) ) {
  $line = fgets($h);
  $line = trim($line);
  printf("%s\n", $line);
}

fclose($h);

//file output:
$out = fopen("output.txt", "w");
fprintf($out, "this is some output");
fprintf($out, "this is too and a number: %d\n", 123);
fclose($out);
```

* Short cuts: PHP has a `file_get_contents()`
* Short cut for output: `file_put_contents()`
* Very cool extensions: you can use any protocol or URL with PHP's file I/O functions

```php
$contents = file_get_contents("input.txt")

//it need not be a file:
$contents = file_get_contents("http://espn.com");

file_put_contents("output.txt", $outputString);

file_put_contents("sftp://cse.unl.edu/~cbourke/", $outputString);

```

## Functions

* You can declare your own functions using the `function` keyword

```php
function foo($a, $b, $c) {
  if($a > 0) {
    return "hello";
  } else if($a === 0) {
    return 42;
  } else {
    return array();
  }
}
```

* later on, use a function by invoking it: `foo(10, 20, 30)`
* HOWEVER: function names are case insensitive, `Foo()`, `foo()` and `FoO()` are all the same function calls
* HOWEVER: there is no function overloading (there can only ever be *one* function named `foo`)
* THEREFORE: all arguments (parameters) become *optional*
* You an specify function parameter *defaults* so that if someone does not provide a parameter, a default will still be available

```php

function addOne(&$arr) {
  for($i=0; $i<count($arr); $i++) {
    $arr[$i]++;
  }
}

function swap($a, $b) {
  $t = $a;
  $a = $b;
  $b = $t;
  return;
}

function swapByRef(&$a, &$b) {
  $t = $a;
  $a = $b;
  $b = $t;
  return;
}

$x = 10;
$y = 20;
swap($x, $y);

printf("x = %d, y = %d\n", $x, $y);


swapByRef($x, $y);

printf("x = %d, y = %d\n", $x, $y);

$myArray = array(10, 20, 30, 40);
print_r($myArray);
addOne($myArray);
print_r($myArray);

//you get a "deep" copy for "free":
$copy = $myArray;
$copy[0] = 42;
print_r($copy);
print_r($myArray);

//you can have reference assignment too
//even though you don't have pointers
$a = 10;
$b = &$a;
$a = 42;
printf("%d\n", $a);
printf("%d\n", $b);

```

# Exceptions

* PHP does support exceptions, actually only really one


```php
//you can throw an exception:
throw new Exception("message of what got donked here");

try {
  //potentially dangerous code here
} catch($e) {
  print "Exception occurred: " . $e->getMessage() . "\n";
}
```


### Exercise: Break My Password

Passwords are often stored as hashed strings.  One such (bad) hash function supported directly in PHP is SHA-1 (using `sha1("password")`):

$$password -> 5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8$$

What's my password if my hash is 
 $$b9b8cf57aeeda5f21ae6fc3b3447e7f69beb7480$$
Hint: my password is a dictionary word from `/usr/share/dict/american` with a 4 digit number appended.

```php
<?php

$hash = "b9b8cf57aeeda5f21ae6fc3b3447e7f69beb7480";

$h = fopen("american", "r");

while( !feof($h) ) {
  $line = trim(fgets($h));

  for($i=0; $i<1; $i++) {
    //append a 4 digit number...
    $line = $line . 2019;

    $lineHash = sha1($line);

    //printf("%s => %s\n", $line, $lineHash);

    if( strcmp($lineHash, $hash) == 0) {
      printf("Possible match found: %s\n", $line);
    }
  }
}
printf("Done");

?>
```




