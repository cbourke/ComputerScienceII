# Misc Resources

## ObjectAid Eclipse Plugin

ObjectAid is an Eclipse plugin/tool that allows you to reverse engineer
Java code and produce an editable UML diagram.  Though there are many
software tools like this, many are overly complicated or are paid products.

Unfortunately, ObjectAid is not currently actively supported (the original
author Felix Mayer was tragically killed by a hit-and-run driver).  However,
older versions can still be installed and used.  The following instructions
work on Eclipse Version: 2022-12 (4.26.0) and Java 17 and are based on the
following post: https://stackoverflow.com/questions/68589918/objectaid-unhandled-event-loop-exception

## Instructions

### Installation

First, download **our** ObjectAid archive file (we've already modified it
for you) provided in this repo: `objectaid-1.2.4.zip` and extract the contents
somewhere on your computer.

1. Launch Eclipse and select **Help** then **Install New Software...***
2. Click **Add** then **Local**
3. Select the *directory* (folder) that you extracted (*not* the zip file); it
   should be named `objectaid-1.2.4`
4. Select the checkbox next to "ObjectAid UML Explorer" and click Next
5. When prompted, select "All" and "Trust Selected" to complete the installation

### Workaround

Unfortunately, this workaround still requires you to manually edit a configuration
file (`eclipse.ini`).  Follow the instructions *exactly* unless you know what
you are doing.

#### Windows

1. Find and open the folder in which Eclipse was installed; in this folder
   is the Eclipse executable, `eclipse.exe` and the `eclipse.ini` configuration
   file.
2. Open the `eclipse.ini` file (double click it and/or use notepad to open it).
3. Add the following lines *immediately after* the line containing `-vmargs`:

```text
-vmargs
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED
--add-opens=java.base/java.text=ALL-UNNAMED
--add-opens=java.desktop/java.awt.font=ALL-UNNAMED
```

4. Save the file, close it and restart Eclipse

#### Mac

1. You first need to determine where you've installed Eclipse.  You should have
   specified this when you originally installed Eclipse.  For example, it may
   have been installed in `/User/jstudent/eclipse/jee-2022-12`
2. Open a terminal and execute the following command:
   `open /Users/jstudent/eclipse/jee-2022-12/Eclipse.app/Contents/Eclipse/eclipse.ini -a TextEdit`
   where the initial part (`/Users/jstudent/eclipse/jee-2022-12/`) is replaced
   with the location of your Eclipse installation.  This will open a TextEdit
   instance in which you can now edit this file.
3. Add the following lines *immediately after* the line containing `-vmargs`:

```text
-vmargs
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED
--add-opens=java.base/java.text=ALL-UNNAMED
--add-opens=java.desktop/java.awt.font=ALL-UNNAMED
```

4. Save the file, close it and restart Eclipse

### Usage

1. Right click your Eclipse project and create a new folder, name it `UML`
2. Right click your project again and select "New", then "Other..." and search
   for "ObjectAid Class Diagram"; select and click next
3. Select the `UML` folder you created, specify a name, click "Finish"
4. A new window/file will be created with the extension `.ucls`
5. Select all the Java classes that you want to add to the diagram, drag and
   drop them onto the diagram and the UML will automatically be generated.
6. You can move the diagrams around to get it to look how you want, then right
   click the window and choose "Save Image As..." to generate a png image of
   your diagram.
