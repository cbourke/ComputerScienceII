
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
* Be default, associative arrays can be treated like tradiational 0-indexed arrays
* No fixed size (grow dynamically)
* Under the hood: hash maps

