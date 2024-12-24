# Computer Science II
## Assignment 0.0
### Spring 2025
---

# Overview

In the first assignment (numbered 0 because all self-respecting computing
professionals start counting at 0), we'll focus on getting used to your
new language (Java or Python) by doing some simple standalone programs.  
Follow instructions **carefully**, failure to do so may result in
points being deducted.  

* For this first assignment, all work must be your own, no partners or
  collaboration with other student(s) is allowed.  However, you may
  discuss problems *at a high level*.  The School of Computing's
  Academic Integrity Policy is in effect: <https://computing.unl.edu/academic-integrity-policy>

* Hand in all your source files and other artifacts through the webhandin
  and verify your programs are correct by running the grader.  

* **For those in the main section(s)**: your programs must be
  written in Java, should accept command line arguments as specified,
  and execute successfully on the grader.  All your classes should
  be in the `unl.soc` package and your source files should have the
  following names:
    * `ThreePoints.java`
    * `RunningUtils.java`, `ConvertMapTests.java`, `ElevationGainTests.java`
    * `RunReports.java`

* **For those in the honors section**: your programs must be written
  in python (unless you have no prior Java experience, in which case,
  you should do the Java version), should accept command line arguments
  as specified, and execute successfully on the grader.  Your source
  files should have the following file names:
    * `three_points.py`
    * `running_utils.py`, `test_running_utils.py`
    * `run_reports.py`

# Exercises

## Exercise 1 - Close Points (Basic I/O)

Points in a plane are identified by $(x, y)$ coordinates.  The
distance between two points $(x_a, y_a)$ and $(x_b, y_b)$ is
calculated using the equation:

$$\sqrt{ (x_b - x_a)^2 + (y_b- y_a)^2 }$$

Write a program that reads in *three points*, $(x_a, y_a)$, $(x_b, y_b)$,
and $(x_c, y_c)$ and computes the distance between them all and then
outputs the closest two points.  

For example, if we invoke your program from the command line using the
following **command line arguments**

`0 0 1.5 1 4.3 4.8`

it would correspond to points $(0, 0), (1.5, 1), (4.3, 4.8)$ and  your output
should look something like the following.

```text
Distance ab: 1.802776
Distance ac: 6.444377
Distance bc: 4.720169
Closest two points: ab
```

## Instructions

* You should perform some basic error checking and
  input validation, exiting with an appropriate message.
* Verify all the tests pass; you may resubmit as many times
  as you like until the due date.

## Exercise 2: Elevation Utilities (Arrays, Testing)

### Elevation Gain

At regular intervals a running app will measure the current elevation (meters above
sea level) and stored them in an array.  The *elevation gain* will need to be
reported to the user.  However, only positive differences in elevation count
toward the gain (up hill runs).  For example, if the array contained the following
elevation samples:

`{350, 352.5, 351.2, 355.2, 354.0}`

The elevation gain between the first two points (350, 352.5) is +2.5 and
would be counted while the elevation gain between the second pair of
points (352.5, 351.2) is -1.3 and would not count.  The next pair has
+4.0 and the last pair has -1.2.  Counting only the positives the total
elevation gain is 6.5.

### Elevation Converter

Consider an `n x m` map grid whose elevations are represented
by a two dimensional array of floating point numbers.  For example,
the following `3 x 5` grid:

```text
{ 9.50, 8.75, 7.25, 8.25, 8.25 }
{ 8.50, 9.35, 6.45, 6.50, 7.25 }
{ 7.50, 8.60, 4.50, 5.50, 5.75 }
```

The elevations are represented in meters.  However, to display
the map elevations to users who choose to use feet, they will
need to be converted *without* altering the original map data,
so a new `3 x 5` map needs to be created converted to feet
(1 meter = 3.28084 feet).  The new map would look like:

```text
{ 31.17 28.71 23.79 27.07 27.07 }
{ 27.89 30.68 21.16 21.33 23.79 }
{ 24.61 28.22 14.76 18.04 18.86 }
```

### Instructions & Unit Testing

For the Java version, we have provided a starter file with specific method
signatures.  We have also provided JUnit starter files that
have several test cases already written.  In addition to implementing all methods
as specified, you will be required to *add* additional test cases to the JUnit
testing suite.  Specifically, you need to add **at least 2 valid test cases** *for
each method* you need to implement.  

For the Python version, we have provided a starter file with specific function
signatures.  We have also provided a `unittest` starter file that
has several test cases already written.  In addition to implementing all function
as specified, you will be required to *add* additional test cases to the `unittest`
testing suite.  Specifically, you need to add at least 2 valid test cases *for
each function* you need to implement.  

## Exercise 3: Running App Data

NighKey has successfully tested a running app on several runs and now
need your help to validate the data.

The data for each run includes GPS data (latitude/longitude), elevation
data (in meters above sea level), and a unique date/time stamp in
(the proper) ISO 8601 format in a CSV formatted data file.  In each
file the first line is the header/metadata and each subsequent line
is a data point.  However, the data points are not necessarily in any
particular order.  For example:

```text
latitude,longitude,elevation,time
41.30359,-96.168361,378.8,2024-11-16T16:12:52
41.303385,-96.167966,378.4,2024-11-16T16:13:03
41.303608,-96.16839,378.9,2024-11-16T16:12:51
...
```

A full sample run will have thousands of data points.  **However**
the app does **not** necessarily record data for *every* second.
  - For any data "gap" of *less than* 10 seconds it is assumed that
    the app simply did not record data or the runner may have paused to
    (say) tie their shoe; in any case we will assume that the
    runner was *moving* during this gap.
  - For any data gap of 10 seconds **or more**, it is assumed that
    the runner paused the app for a longer water or bathroom
    break.  In this case we will assume the runner was *waiting*
    (not moving) during this gap.

You will write a program that produces several reports based on these
assumptions.

## Elevation Report

The first report you will output will be the highest and lowest elevations
recorded during the run.  Your first report should look something like the
following.

```text
=====================
 Elevation Report
=====================
Highest Elevation: 385.60m
Lowest Elevation:  334.70m
```

## Elevation Gain Report

The *elevation gain* only includes the elevation increase from
one data point to the next (regardless of time gaps).  For example an
elevation change from 383.2m to 383.5m would be +.3m and would count as a
gain.  An elevation change from 383.2m to 383.0m would be -.2m and would
not count toward the total gain.

Your second report will compute the total elevation gain during the run and
should look something like the following.

```text
=====================
 Elevation Gain Report
=====================
Elevation Gain: +185.50m
```

## Distance Report

The next report will report the total distance of the run.  The
distance can be computed between two points using the latitude/longitude.
The distance between these two locations can be computed using the
Spherical Law of Cosines:

$$d = \arccos{(\sin(\varphi_1) \sin(\varphi_2) + \cos(\varphi_1) \cos(\varphi_2) \cos(\Delta) )} \cdot R$$

where

-   $\varphi_1$ is the latitude of location $A$, $\varphi_2$ is the
    latitude of location $B$

-   $\Delta$ is the difference between location $B$'s longitude and
    location $A$'s longitude

-   $R$ is the (average) radius of the earth, 6,371 kilometers

Your report should look something like the following.

```text
=====================
 Distance Report
=====================
Total distance: 16.89km
```

## Time Reports

This report will compute the total *moving time* and total *wait time*
during the run (as defined above) as well as the total time of the run
(the sum of the moving and wait time).  We've provided some starter code
to help you make these computations and formatting.  Your report will
need to be formatted as follows.

```text
=====================
 Time Report
=====================
Moving time:    01:34:16 (5656s)
Wait time:      00:10:22 (622s)
Total time:     01:44:38 (6278s)
```

## Data Anomalies

The running app has been developed for both Android and iOS.  NighKey
wants to make sure the data collected is consistent between these two
versions.  A second data file has been made available for each run.
The final report will report any *inconsistencies* or *missing data*
between the two datasets:

- Report any data point in data set A (identified by date/time stamp)
  that is *missing* in data set B, *or* any data point in B that is
  missing in A.
- Report any (matching) data point between the two datasets whose
  location data (latitude or longitude) differs by more than 0.001
  degrees.
- Report a total number of missing and inconsistent records

Your output should look something like the following.

```text
==========================================
Inconsistent/Missing Data Reports
==========================================
Data point 2024-11-16T17:07:50  41.248660, -96.155317 (342.60) missing in Data Set B
Data points are inconsistent:
	 2024-11-16T17:38:56  41.279074, -96.157908 (353.90)
	 2024-11-16T17:38:56  41.279074, -96.257908 (353.90)
Data point 2024-11-16T17:53:37  41.300846, -96.159428 (360.70) missing in Data Set B
Data point 2024-11-16T17:44:26  41.286694, -96.157942 (382.90) missing in Data Set A
Number of missing data points: 3
Number of inconsistent data points: 1
```

Your report is *required to be efficient*; a brute-force approach
(comparing all possible pairs of data) may not work and is not acceptable.  
You will need to use efficient searching and sorting techniques.  

# Installing and Using JUnit 5 in Eclipse

## Installing

First, we need to add the JUnit library to your project.  First: make
sure you are in your *Java Perspective* (*not* the "Java EE" perspective).

1. Right-click your project in the Project Explorer
2. Select "Build Path" and then "Add Libraries..."
3. Select JUnit then "Next"; make sure to select JUnit 5 and click "Finish"

You should be able to copy any provided JUnit source code
into your project now.  

## Using JUnit

To run a JUnit test suite, simply open the testing file and hit the
"play" button as you would a normal program.  Eclipse will
automatically compile a report of the number of tests passed or
failed along with any messages provided for failed test cases.

Note that there is no `main` method in a JUnit test suite.  Instead,
JUnit uses "reflection" to automatically find unit tests (methods
identified by the `@Test` annotation) and run them.  

## Optional Project Setup

In larger Java projects it is typical to keep source code and
testing code in separate locations.  With JUnit this is usually
done by putting source code in a `src/main/java` folder and JUnit
test code in a `src/test/java` folder.  In addition, JUnit tests are
usually located in the same package structure as the classes
they are testing.

To setup your Eclipse project this way:
1. Right-click your project and select "Build Path" and select "New Source Folder"
2. Create the source folder `src/main/java` (repeat this for `src/test/java`)
3. Move any code you might have had in the original `src` folder to the appropriate new folder

# Rubric

Each exercise will be graded based on the following items.  However, exercise
three will have a total of 41 correctness points.

## Style (2 pts)

-   Appropriate variable and function/method identifiers

-   Style and naming conventions are consistent

-   Good use of whitespace; proper indentation

-   Clean, readable code

## Documentation (2 pts)

-   Well written comments that clearly explain the purpose of each
    non-trivial piece of code

-   Comments explain the "what" and "why"

-   Comments are not overly verbose or overly terse

-   Code itself is "self-documenting"; it explains the "how"

## Program Design (5 pts)

-   Code is well-organized and efficient

-   Code is modular; substantial pieces of it could be reused; few
    redundancies

-   Code is easily understood and maintainable

-   It is clear that sufficient testing has been performed

-   Corner cases and bad input have been anticipated and appropriate
    error handling has been implemented

## Program Correctness (16 pts)

-   Source code compiles and executes as expected

-   Program runs as specified: correctly reads any input; correctly
    formatted output

-   Test cases successfully execute
