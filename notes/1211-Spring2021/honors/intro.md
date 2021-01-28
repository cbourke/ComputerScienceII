
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
