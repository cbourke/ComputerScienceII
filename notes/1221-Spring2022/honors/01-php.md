
# CSCE 156H - Computer Science II Honors
## Quick Intro to PHP
### Spring 2022

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

# Variables

* All variable names must begin with a `$`
* Variable names *should* be written using `$lowerCamelCasing` and are case *sensitive*
* `$foo` is NOT the same as `$Foo`
* You don't delcare variables, you just use them
* You don't declare a variable's type, it changes with whatever value you give it
* PHP supports integers, floating pointer numbers, strings, booleans, arrays, objects
* PHP supports case *insensitive* keywords `true`, `false`
* No pointers, no memory management, automatic garbage collection
* String concatenation

```php
$firstName = "Chris";
$lastName = 'Bourke';
$fullName = $lastName . ", " . $firstName;
```

* You can define *global* constants using `define()`:

```php

$a = "Hello";
$b = 10;

$c = $a . "  \$b  ";

printf("%s\n", $c);

$radius = 1;
define('PI', 3.14159);
//old PHP: constants are case insensitive
//new PHP: case sensitive ?+
$area = pi * $radius * $radius;
printf("area = %f\n", $area);
define('PI', 3.0);

//math library is automatically included:
$y = sqrt(2);
printf("y = %f\n", $y);

```

## Type Juggling

* PHP is dynamically typed, so variable types are not fixed and can change
* In general `+` is always addition, parses the string as best as it can to a numerical value:
  * `"123"` parses to `123`
  * `"hello"` parses to `0`
  * `"123hello"` parse to `123`
  * `"3.14"` parses to `3.14`
  * `"3.14e4"` parses to `31400`
* In general, don't *rely* on type juggling!
* Instead: be explicit with your code and conversions

```php

$a = "10";
$b = "3.14e4";

$x = intval($a);
$y = doubleval($b);

printf("%d, %f\n", $x, $y);

if(is_numeric($a)) {
    printf("IS a number!\n");
} else {
    printf("not a number!\n");
}
//also:
if(is_int($y)) {
    printf("y is an integer\n");
} else {
    printf("y is NOT an integer\n");
}

if(is_float($y)) {
    printf("y is a float\n");
} else {
    printf("y is NOT an float\n");
}
```

* PHP also has a `null` type, it is case insensitive, `NULL`, `NuLl`
* You can test for null: `is_null($a)` returns true if `$a` is null or if it has not yet been set
* `isset($a)` returns true only if the variable has not been set or ever used before

## Operators

* Arithmetic: `+ - * / %`
* Increment: `++ --`
* Concatenation: `.`
* Logical operators: `!, &&, ||`
* Comparison operators: `< <= > >= == !=`
* Unfortunately: you *can* use the above operators with strings
* You may instead want to use `strcmp` and or `strcasecmp`
* You can use strict comparison to ensure variables have the same type AND value:
  * `===` and `!==`

### Bad Math

```php
$a = sqrt(-1);
$a = 1 / 0;

print($a);

if(is_nan($a)) {
    printf("not a number\n");
}

if(is_infinite($a)) {
    printf("infinite\n");

}
```

## Strings

* Strings are *mutable*
* You can treat them like character arrays, BUT
* NO null terminating characters!
* Many C string functions such as:
  * `strlen()` etc can be used!
  * `explode($delimiter, $str, [$limit])`

```php

$csvData = "H239e llo,Wo32\t1rld,Chr is,Comput\ner,123";

$tokens = explode(",", $csvData);

//print_r($tokens);

//zero or more occrrences of a digit:
$tokens = preg_split("/[0-9]*/", $csvData);
//one or more occrrences of a digit:
$tokens = preg_split("/[0-9]+/", $csvData);
//one or more occurrrences of whitepsace:
$tokens = preg_split("/(\s+)/", $csvData);
//regular old comma:
$tokens = preg_split("/,/", $csvData);
//any single occurrence of a vowel:
$tokens = preg_split("/[aeiou]/", $csvData);

print_r($tokens);

```

* `substr($str, $startIndex, [$length])` produces a new substring starting at the `$startIndex` to the end or of length `$length`
* `str_replace($needle, $replacement, $haystack)`

## "Arrays"

* PHP does not have actual arrays
* PHP has "associative arrays" (neutered map)
* Associative arrays map either integers or strings (as keys) to some other value
* Integer values do not have to be contiguous or start at zero but that is the default

```php
<?php

$arr = array();

//appends using the next available index
$arr[4] = 42;
$arr[] = 10;
$arr[] = 20;
$arr[] = 30;
$arr[0] = 101;
$arr[-1] = 200;
$arr["foo"] = 12;
$arr["bar"] = 3.14;
$arr["baz"] = "hello";
$arr["10.5"] = "huh?";
$arr[11.5] = "huh?";
$arr[0] = 27;

unset($arr[0]);

print(count($arr) . "\n");
print_r($arr);

$brr = array(
  0 => "Hello",
  "foo" => "bar",
  10 => 3.14159
);

for($i=0; $i<count($arr); $i++) {
    print($arr[$i] . "\n");
}

foreach($arr as $key => $value) {
    print($key . " maps to " . $value . "\n");
}
?>
```

## Command Line Arguments

* `$argc` is the number of arguments
* `$argv` is a 0-indexed array of arguments
* `$argv[0]` is the script name

```php


$a = 10;
print("a = $a\n");

printf("number of arguments: %d\n", $argc);
foreach($argv as $index => $arg) {
    printf("argv[%d] = %s\n", $index, $arg);
}

$x = intval($argv[4]);
$y = doubleval($argv[5]);

printf("end of program\n");

```

# File I/O

* Three basic steps:
  1. Open a file
  2. Process it
  3. Close

```php

$contents = file_get_contents("data.txt");

printf($contents);

//$lines = preg_split("/\n/", $contents);
$lines = explode("\n", $contents);

print_r($lines);

//file output:
$h = fopen("otherData.txt", "w");
fprintf($h, "hello world\n");
$a = 3.14;
fprintf($h, "%.20f\n", $a);

fclose($h);

//oneliner:
$msg = "Hello World how are you?";
file_put_contents("newFile.txt", $msg);
$msg = "new message";
file_put_contents("newFile.txt", $msg);

printf("end of program!\n");

```

# Functions

* You can declare a function using the `function` keyword
* You can declare it anywhere, however
* Function names are case *insensitive*

```php

function sum($a, $b = null, $c = 50) {
  $total = $a + $b + $c;
  return $total;
}


$x = 10;
$y = 20;
$z = 30;

$result = sum($x);
print($result);

```

Pass by value/reference:

```php

function swap(&$a, &$b) {
printf("swap: %d, %d\n", $a, $b);
    $t = $a;
    $a = $b;
    $b = $t;
printf("swap: %d, %d\n", $a, $b);
}


$x = 10;
$y = 20;

printf("%d, %d\n", $x, $y);
swap($x, $y);
printf("%d, %d\n", $x, $y);



function sumArr(&$a) {
    $total = 0;
    for($i=0; $i<count($a); $i++) {
        $a[$i] = $a[$i] + 1;
    }
    foreach($a as $k => $val) {
        $total += $val;
    }
    return $total;
}

$arr = array(10, 20, 30);
print_r($arr);
$s = sumArr($arr);
print_r($arr);
printf("total = %d\n", $s);
```

# Exceptions

* PHP does support Exceptions and exception handling, but ONLY one type: `Exception`

# Sorting

* `sort` - a "natrual" sort
* `usort` - user-defined sort

```php
function cmpStudentByName($a, $b) {
    if($a->getLastName() < $b->getLastName()) {
        return -1;
    } else if($a->getLastName() > $b->getLastName()) {
        return 1;
    } else {
        return 0;
    }
}

class Student {
  private $firstName;
  private $lastName;
  private $nuid;

  public function __construct($lastName, $firstName, $nuid) {
    $this->firstName = $firstName;
    $this->lastName = $lastName;
    $this->nuid = $nuid;
  }

  public function __toString() {
      return sprintf("%s, %s (%08s)\n", $this->lastName, $this->firstName, $this->nuid);
  }

  public function getLastName() {
      return $this->lastName;
  }

}

$roster = array(
    new Student("Zoe", "Jane", "123413"),
    new Student("Bourke", "Chris", "1234"),
    new Student("Johnson", "Bob", "123412")
);
print_r($roster);
usort($roster, "cmpStudentByName");
print_r($roster);

```

```text








```
