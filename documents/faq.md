# Frequently Asked Questions
**[School of Computing](https://computing.unl.edu/)**  
**[College of Engineering](https://engineering.unl.edu/)**  
**[University of Nebraska-Lincoln](https://unl.edu)**  
**[University of Nebraska-Omaha](https://http://unomaha.edu/)**  

This is a collection of *frequently asked questions* (FAQ)

# Canvas Support Management

## Where do I go for help with Canvas?

Canvas is UNL's Learning Management System (LMS) available at
<https://canvas.unl.edu>.  For technical support see
<https://services.unl.edu/service/learning-management-system-lms-canvas-unl>

# Eclipse & Java

You may use any Integrated Development Environment (IDE) you wish, but instructions
and support for this course will be limited to the Eclipse IDE.  If you choose
to use a different IDE, you will need to make adjustments accordingly so that
your code, artifacts, and submissions still work as expected.

## Installation

  - You'll first need to install the Java Developer Kit (JDK):  
    <https://www.oracle.com/java/technologies/downloads/>  
    This is *not* the same thing as "Java" (which is usually the JRE or
    Java Runtime Environment); the JDK is necessary to *develop* in Java.

  - Install the Eclipse Integrated Development Environment (IDE):  

    1. Download the Eclipse Installer: <https://www.eclipse.org/downloads/>  
    2. Run it and install the **Eclipse IDE for Enterprise Java and Web Developers**

# Linux Server

You have access to a (SUSE) linux server hosted at `nuros.unl.edu`.  You
can login to this server using SSH (Secure Shell) using your Canvas login and
password.  Your Canvas login will look something like `cbourke3`; it is *not*
your NUID.

# SQL Development

## MySQL Workbench

You may use any SQL client/IDE you wish, but for this course we recommend using
MySQL Workbench (see <https://dev.mysql.com/downloads/workbench/>); it is
free and has all the functionality you'll need.

### Installation - Windows

For windows users, you may have better luck with the following installation:  
  1. Use the installer for Windows: <https://dev.mysql.com/downloads/windows/installer/8.0.html>
     (mysql-installer-web-community); select "No thanks, just start my download..."
  2. Run the installer download (an MSI); give permissions, etc.
  3. Select **client only**, next
  4. Click execute (those are not check boxes/radio boxes, its just a stupid graphics decision)
  5. Agree to licenses, install; **you are not yet done**
  6. Next (to Download) and Execute AGAIN; if it fails, go back and try again;
     you need workbench, shell and router next/next/finish/etc
  7. You can close the command window that opens up, but otherwise mysql workbench will launch

## Connecting to the MySQL Server

You have access to a MySQL server hosted on `nuros.unl.edu` using your
Canvas login ID and a *separate, different* password that should have been
sent to you via your school email.  Using MySQL Workbench, connect to this
server with:
  * Host: `nuros.unl.edu`
  * Port: `3306`
  * Username: your Canvas login ID (ex: `cbourke3`)
  * Ignore any warnings about an incompatible server

## Changing Your Password

You can change your password once connected by executing the following SQL
command:  

  `set password=password("mynewpassword");`

Do *not* use a password that you use on *any* other system; this password should
not be considered secure and you may be submitting code and other artifacts with
this password stored in plaintext.  Do no lose this password as there is no
(convenient) way to reset it (yet).

## Alternatives

If you cannot get MySQL Workbench to work, you can use the installation in the
labs or you can connect to our lab computers remotely.  
  1. Download a VMWare Horizon Client: <https://myhorizon.vmware.com>
  2. Use it to connect to `cse-vmcs-01.unl.edu`  
  3. Log in with your `@unl.edu` using `username1@unl.edu`
  4. Once you have logged in, select "Terminal Server" and you will have a
     remotely accessible Windows Desktop that will allow you to use MySQL Workbench.

# codePost.io

## What is codePost?

[codePost.io](https://codepost.io) is a website that we push your code and
other artifacts to in order to grade it for design, documentation, and style.
Line-by-line code feedback is provided by graders.  

## How do I get access?

You can access codePost at <https://codepost.io>.  You should have received
an invite email from your instructor.  Be sure to check your spam
folder and respond ASAP; the links expire after a few days.

## I didn't get the invite or it expired, what do I do?

Try going to https://codepost.io/forgot-password and resetting your password
(even if you never initially set one).  Be sure to use your **huskers email**
(example: `jstudent42@huskers.unl.edu`) or whatever primary email is
associated with your canvas profile.

# Grading

## How and when are labs graded?

Labs are due at midnight, the same day as the lab.  We run automated
scripts to grade labs and post scores to Canvas as soon as possible
the following day.  Once completed, a brief retrospective is posted
to Piazza.

## How and when are assignments graded?

Learning Assistants have 48 business hours to grade assignments and
then Course Leaders take another 48 hours to check the grading process
to ensure consistency.  Grades are posted to Canvas as soon as this
process is complete.  A retrospective is then posted to Piazza.

# Collaboration

## What are the collaboration policies for this course?

* The use of any and all AI tools is **strictly prohibited** for this course and
  you may not use them for any purpose.  These tools have their use cases and
  are appropriate in some contexts.  However, the time to utilize them is not
  when you are learning new concepts for the first time.

* **Labs** - All students are highly encouraged to work in pairs for all labs.
  In lab, we will facilitate team ups by pairing you with another student if
  you wish.  You may always work alone if you prefer.  Groups of 3 or more are
  not prohibited but are **discouraged** because it is too easy to diffuse
  the responsibility for learning.  

* **Assignment 1** - No collaboration with other students is allowed.  
  The ability to do Assignment 1 is a good indicator whether or not you are
  properly prepared for this course (good working knowledge of at least one
  programming language).  

* For all other assignments (2-8) and the design document, you may work in pairs
  but may not work in groups of 3 or more.  See the full details and *requirements*
  for doing so provided in the project overview document.

## I need to end a partnership, is that okay?

Certainly.  If a partnership is not working out for whatever reason, you can
and *should* make a change.

* Make sure that your partner is aware that the partnership is ending, don't
  just ghost them.

* Whatever code and artifacts you have worked on together up to that point, make
  sure you each have a copy to work off of going forward.  Code shared from an
  only partnership will not be considered plagiarism.  You should clarify in
  your readme past partnerships.

* Make sure to leave the partner group on Canvas immediately.

* Yes, you may form a new partnership if you wish or you may work alone.
