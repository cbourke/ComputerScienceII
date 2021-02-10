
# CSCE 156H - Computer Science II Honors
## Quick Intro to PHP

* Created in 1995 by Rasmus Lerdorf (Personal Home Page Tools)
* Collection of C modules for creating dynamic web pages
* C/HTML influence: C-style syntax, lack of function overloading, case-insensitivity (!)
* PHP: Hypertext Preprocessor

## Key Aspects

* Interpreted, not compiled
* Familiarity: semicolons, curly brackets, name identifier rules, etc
* Dynamically typed: you do not declare variables or their type, you just use them!

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
* Variable names are case sensitive `$foo` is not the same as `$Foo`
* You don't declare variables, you just start using them
* Variable types can change depending on the value you assign
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
//constants are case insensitive (old PHP)
// case sensitive (PHP 8+):
$area = PI * $radius * $radius;

//you can NOT redefine constants:
define('PI', 3);
```

# Type Juggling

* Because PHP is dynamically typed, variable types can change, so when you mix types, weird stuff happens
* In general, "adding" (`+`) a string, parses that string (as best as it can) to get a numerical value
  * "123" parses to 123
  * "hello" parses to 0
  * "123hello" parses to 123
  * "3.14" parses to 3.14
  * "3.14e4" parses to 
* In general don't *rely* on type juggling
* Be explicit with your conversions

```php
$a = "120.12";
$val = doubleval($a);
$b = "120";
$val2 = intval($b);
```

* You have testing functions too:
  * `is_numeric($a)`  returns true if the value is a number or a "pure" numerical string
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
* The `==` and `!=` are "loose" comparison operators (type juggling will occur)
* The strict equality operators, `===` and `!==` will be true only if the *type* AND the *values* match


### Bad Math

```php

$a = 1 / 0; //INF, and a warning
$b = sqrt(-1);  //NAN

//checkers:
is_nan($b);
is_infinite($a);
```

## Strings

* Strings in PHP are *mutable*: they can be changed
* Strings are essentially just character arrays BUT there is no null terminating character to handle
* You can use other C functions such as 
  * `strlen()` returns the length of the string (THERE ARE NO NULL TERMINATING CHARACTERS)
  * `explode($delimiter, $str, [$limit])`
```php
$csvData = "Hello,World,Chris,Computer";

$foo = explode(",", $csvData);

for($i=0; $i<sizeof($foo); $i++) {
    printf("%s\n", $foo[$i]);
}
```
  * `preg_split($pattern, $str, [$limit])` splits along the given regular expression
```php


$csvData = "He llo,Wo\trld,Ch ris,Computer";

$foo = preg_split("/(\s+)/", $csvData);

for($i=0; $i<sizeof($foo); $i++) {
    printf("%s\n", $foo[$i]);
}
```  

* `substr($str, $startIndex, [$length])` gives a substring starting at `$startIndex`
* `str_replace($needle, $replace, $haystack)` replaces any instance of `$needle` in the string `$haystack` with the contents of `$replace`
```php

$s = "Hello World, How Are you?";

$t = str_replace("Hello", "Goodbye", $s);

printf("%s\n", $s);    
printf("%s\n", $t); 
```
* `preg_replace($needle, $replace, $haystack)` replaces based on a regular expression
  
## "Arrays"

* PHP doesn't have arrays
* PHP has "associative arrays" (neutered map)
* An "array" can map integers or strings to any values
* Integer index values do not have to be contiguous, they don't have to start at zero

```php
<?php

//create an empty array:
$arr = array();

//mixed array:
$arr["foo"] = "bar";
$arr[4] = 42;
$arr[1] = "hello";
$arr[5] = 3.14;
$arr[3.9] = "5foo";
$arr["7foo"] = "yup";

$arr[] = 101;
$arr[-10] = "goodbye";

print_r($arr);

$x = sizeof($arr);

printf("number of elements = %d\n", $x);

//nope:
for($i=0; $i<sizeof($arr); $i++) {
    print $arr[$i] . "\n";
}

//this is the easier/bettewr way:
foreach($arr as $key => $value) {
    print $key . " maps to " . $value . "\n";
}

//more


//create an empty array:
$arr = array(
    0 => "hello",
    10 => "foo",
    "bar" => "baz",
    "bin" => 3.14
    );


print_r($arr);

$arr["bar"] = 101;

print_r($arr);

//remove an element:
//nope:
$arr[0] = null;

//yup:
unset($arr[0]);
print_r($arr);

unset($arr);

print_r($arr);

?>
```

# Command Line Arguments

* Are stored in an array called `$argv`, the number of arguments is stored in `$argc` or you can use the `count($argv)` function
* The first argument is always the script file's name

```php
<?php

for($i = 0; $i < $argc; $i++) {
    printf("argv[%d] = %s\n", $i, $argv[$i]);
}

$x = intval($argv[1]);
$y = doubleval($argv[2]);

?>
```

# File I/O

## File Input

* Three steps: open the file, process it, close it

```php
$fileName = "data.txt";

//you can check if a file exists:
if( !file_exists($fileName) ) {
  printf("ERROR: cannot open file %s\n", $fileName);
  exit(1);
}
//get a file "handle"
$h = fopen($fileName, "r");

//read a line:
$line = fgets($h);

//remove the endline character:
$line = trim($line);

//read until the end of the file:
while ( !feof($h) ) {
  $line = fgets($h);
  $line = trim($line);
  print("%s\n", $line);
}

fclose($h);
```

* Convenience methods:

```php
$contents = file_get_contents($fileName);
```

# File Output

```php
$out = fopen("output.txt", "w");
fprintf($out, "this is some output");
fprintf($out, "%d \t %.2f", 42, 89.4);
fclose($out);
```

* Convenience method:

```php
$output = "hello world";
flie_put_contents("outfile.txt", $output);
```

* Somewhat cool: you can post/get an arbitrary protocol over the network (ex: https)

# Functions

* You can declare a function using the `function` keyword
* You can declare it anywhere
* All function names are case *insensitive* (!?!)
* Advice: still use the `lowerCamelCasing` convention everywhere
* PHP parameters can be made "optional" or you can provide a default value

```php

function foo($a, $b = null, $c = 101) {
    $result = $a + $b + $c;
    printf("Hello, result = %d\n", $result);
    return $result;
}

$r = foo(10, 32);

printf("r = %f\n", $r);
```

* By default, all parameters are passed by value
```php


function swap(&$a, &$b) {
  //printf("a = %d, b = %d\n", $a, $b);
  $t = $a;
  $a = $b;
  $b = $t;
  return;
}

function addOne(&$arr) {
    for($i=0; $i<count($arr); $i++) {
        $arr[$i] = $arr[$i] + 1;
    }
}


$x = 10;
$y = 20;

printf("x = %d, y = %d\n", $x, $y);
swap($x, $y);
printf("x = %d, y = %d\n", $x, $y);

$arr = array(10, 20, 30);
print_r($arr);
addOne($arr);
print_r($arr);
```

# Exceptions

* PHP does support exceptions, actually only one

```php

<?php

throw new exception("message of what happened");

$a = 10;
$b = 0;

if($b === 0) {
  throw new exception("cannot divide by zero");  
} else {
    $c = $a / $b;
}

try {
    if($b === 0) {
      throw new exception("cannot divide by zero");  
    } else {
        $c = $a / $b;
    }
    //potentially dangerous code
} catch(Exception $e) { //older: catch($e)
    print "Exception occurred: " . $e->getMessage() . "\n";
}


?>
```

# Sorting

* `sort` - a natural sort
* `usort` - provide a comparator as a string
* more: https://www.php.net/manual/en/array.sorting.php 



### Exercise: Break My Password

Passwords are often stored as hashed strings.  One such (bad) hash function supported directly in PHP is SHA-1 (using `sha1("password")`):

$$password -> 5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8$$

What's my password if my hash is 
 $$b9b8cf57aeeda5f21ae6fc3b3447e7f69beb7480$$
Hint: my password is a dictionary word from `/usr/share/dict/american` with a 4 digit number appended.

```php
<?php

class Student {
    
    private $firstName;
    private $lastName;
    private $nuid;
    private $gpa;

    public function __construct($lastName, $firstName, $nuid, $gpa) {
        $this->firstName = $firstName;
        $this->lastName = $lastName;
        $this->nuid = $nuid;
        $this->gpa = $gpa;
    }
    
    public function __toString() {
        return $this->getName();
    }

    public function getName() {
      return $this->lastName . ", " . $this->firstName;   
    }
    
    public function getFirstName() {
        return $this->firstName;
    }

    public function getLastName() {
        return $this->lastName;
    }
    
    public function setLastName($lastName) {
        if($lastName === "" ||
           gettype($lastName) !== "string") {
               //bad data
               throw Exception("You must provide a non-empty string as a last name");
           }
        $this->lastName = $lastName;
    }
    
}

$s1 = new Student("Burke", "Chris", "1234", 4.0);
$s1->setLastName("Bourke");
$s2 = new Student("Smith", "Joe", "5678", 2.0);
$s3 = new Student("Apple", "Jane", "1212", 3.0);
$s4 = new Student("Apple", "Alison", "1213", 3.5);
$roster = array($s1, $s2, $s3, $s4);

function cmpStudentByName($a, $b) {
    $result = strcmp($a->getLastName(), $b->getLastName());
    if($result === 0) {
        $result = strcmp($a->getFirstName(), $b->getFirstName());
    }
    return $result;
}

usort($roster, "cmpStudentByName");
foreach($roster as $s) {
    print($s."\n");
}
?>
```

```text







```
