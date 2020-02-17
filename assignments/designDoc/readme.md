
# Design Document

Students in CSCE 156/156H (Computer Science II) maintain a design document
following the IEEE 1016 standard for Software Design Descriptions 
(see https://standards.ieee.org/standard/1016-2009.html) for their 
semester-long project.

Two templates are provided; an MS Word document and a LaTeX template.

## LaTeX Template

The LaTeX template contains a simple BibTeX bibliography example. 
To compile manually (to PDF) use the following steps:

```
pdflatex designDocument-template.tex
bibtex designDocument-template
pdflatex designDocument-template.tex
pdflatex designDocument-template.tex
```
