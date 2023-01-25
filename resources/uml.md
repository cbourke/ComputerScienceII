

# Universal Modeling Language (UML)

UML is a general-purpose modeling language that provides a standard way to visualize
components and relations in a system, typically a software system.  One common use
case for UML is to describe classes and relations between classes in an object-oriented
programming language.

Many tools exist for developing and working with UML.  One way you can use UML is
to design a system (or collection of classes) visually and then tools can be used
to auto-generate compatible code from the model.  The other use is when you already
have classes designed and implemented and a tool can be used to reverse engineer
those classes to generate a visual diagram.

## PlantUML

PlantUML is an Eclipse plugin that provides a way to reverse engineering your
classes and produce a diagram or diagrams.  Below are quick instructions for
installing and using PlantUML.  More information can be found here:

* https://plantuml.com/eclipse

### Installation & Usage

For both Windows and Mac, in Eclipse:

1. Click `Help` > `Eclipse Marketplace`, search for `PlantUML` (version 1.1.25
   as of these instructions).  Follow the steps for installation and accept the
   license, trust the sources, etc.  You may be prompted to restart Eclipse.
2. Click `Window` > `Show View` > `Other...`; filter/search for PlantUML.  This
   opens a new tab/view.
3. Select any class or *multiple* classes and it will render a diagram in the
   PlantUML tab/view.
4. Right click the view/diagram to export as a PNG file.

**Note**: for Mac users, you may also need to install

Mac: you need to install GraphViz/dot manually (the windows plugin comes packaged
with it).  To do this, follow the installation instructions using `brew` here:
https://plantuml.com/graphviz-dot
