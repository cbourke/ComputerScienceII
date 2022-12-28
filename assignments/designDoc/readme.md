# Computer Science II
## Design Document
---

# Introduction

To be successful in their careers, computer science and engineering graduates
need, in addition to in-depth technical knowledge, the ability to communicate
and collaborate with a variety of audiences. To achieve this goal, it is
important that students have instruction and practice in communication skills
throughout their curriculum.  To this end, in addition to the technical
artifacts (code and archive files, test cases, etc.) you are required to
maintain and iteratively update/draft a technical design document to detail
various aspects of the design of your application.

The design document is a living document; it is expected that you will modify,
update and improve this document through each phase of this project.   This is
a technical document; it is not an essay or a personal account.  The
tone should be professional and the writing should be formal.  Each draft should
be written as if the document and project were already complete.

The format of this document will follow the IEEE Standard for Software Design
Description documents as described in the IEEE Standard 1016-2009.  The standard
gives a general outline for your document and provides details for specific
instances of this document.  A copy of the standards document has been provided
for you.  We have also provided you with a simplified Word template, which you
can and should use as a basis and guide to write your design document (an
alternative LaTeX version has also been provided).

The Design Document has several purposes.  It is intended to give you experience
in technical writing and design.  It is also a mechanism by which you are
to think about and plan an initial design for each phase of the project
**prior** to implementation.  Iteratively drafting an initial design will help
you to identify and resolve potential problems before implementation.  
To that end, the design document draft will be ***due 1 week prior to the actual
assignment due date***.  In each phase (each subsequent assignment) you will
update the design document, incorporating new components and features and
improving and correcting mistakes in prior iterations of the document.  
Each draft will be evaluated and we will give you feedback on improvements, but
only your final version will count toward your grade.  However, to ensure that
you are actually revising your drafts, some points will be awarded for each
draft submission.  Trivial submissions or failing to submit a draft on time
will result in no feedback and no points being awarded.

Evaluation & Grading

The Design Document should be **clear and comprehensive enough that a reasonably
technically competent person could reproduce your system design (though not
necessarily every detail) with identical functionality without access to your
actual code base.**  Our evaluation will be based on how well your Design
Document achieves this principle.  In addition, we will also evaluate your
technical writing skills by evaluating the items listed in the rubric below.

## Rubric (125 points total)

* Draft Submissions (completion): 5 pts each, 25 total
* Formatting & Structural Elements: 10pts
  * Document must follow the basic template provided (IEEE 1016-2009)
  * Must be well-written and well-structured
  * It should have a complete table of contents along with page numbers
  * Should not be overly verbose nor overly terse
  * Proper visual summaries are used (tables, lists, diagrams, figures, etc.)
  * This is a design document, *not* a process document: you are not documenting
    the phases, but the components, features, and designs and how they support
    the business model
* Spelling, grammar, tone: 10pts
  * Must be free of spelling and grammar errors
  * Do not use first person or personal pronouns; use third-person
  * Do not use informal language
  * Do not write the document as if it is an assignment; treat it as if it is
    a real technical design document.
  * Do not use past or future tense, use present tense
  * Write your draft *as if* the system is complete
  * Do *not* include things that a technical audience would already know
* Content:
  * Overall:
    * It should discuss the design, execution, and results of testing strategies for each component as well as any design or implementation changes that were made as a result of testing outcomes
    * It should be an advocate for the various design choices; describe key advantages (or avoided disadvantages)
  * Introduction: 20pts
    * This section should detail the overall project requirements, motivation.
      Who is the client, why are you designing/implementing this project, what
      is their business model, rules, etc.
  * Overall Design Description: 20pts
    * This section should  The overview section should provide an overview of
    the scope of the project.  
    * You should make reference to the major components but leave the details
      to the other sections.
    * You should detail how each component fulfills the requirements detailed in
      the introduction.
    * You should make reference to *why* certain components are being designed
      and *may* describe other functionality that is *not* part of the system
      (and why).
  * Database section: 10pts
    * An ER Diagram (sketch or generated) is required
    * Detail how significant features or design decisions support the requirements
      of the client's business model
    * How do the relations model the problem?
    * How does your design conform to 3NF?
    * Describe your testing strategy in detail: how many test cases do you have?
      How many of each type for each table?  Why does this provide assurance that
      the design is correct?  
    * What were the results of testing? Did you use a tool? Did you cite it?  If
      tests did not pass initially, how was this addressed (ie document bugs
      how testing revealed them and how they were resolved).
  * Class Design section: 10pts
    * A UML Diagram (sketch or generated) is required
    * Detail how significant features or design decisions support the requirements
      of the client's business model
    * Describe your testing strategy in detail: how many test cases do you have?
      How many of each type for each table?  Why does this provide assurance that
      the design is correct?  
    * What were the results of testing? Did you use a tool? Did you cite it?  If
      tests did not pass initially, how was this addressed (ie document bugs
      how testing revealed them and how they were resolved).
  * Database Connectivity section: 10pts
    * Describe your testing strategy in detail: how many test cases do you have?
      How many of each type for each table?  Why does this provide assurance that
      the design is correct?  What were the results of testing?
  * Data Structure section: 10pts
    * Detail how significant features or design decisions support the requirements
      of the client's business model
    * Describe your testing strategy in detail: how many test cases do you have?
      How many of each type for each table?  Why does this provide assurance that
      the design is correct?  What were the results of testing?

## Resources

### LaTeX Template

The LaTeX template contains a simple BibTeX bibliography example.
To compile manually (to PDF) use the following steps:

```
pdflatex designDocument-template.tex
bibtex designDocument-template
pdflatex designDocument-template.tex
pdflatex designDocument-template.tex
```

Or use an online LaTeX system such as <https://www.overleaf.com/>

### Word Template

A word template has also been provided, see `designDocument-Template.docx`

### Professional Example

A professional example from Deloitte has been provided, see `designDocument-Deloitte.pdf`

### IEEE Standard

The IEEE standard document is available through UNL's subscription (access
it on campus):
  * <https://standards.ieee.org/standard/1016-2009.html>
  * <https://ieeexplore.ieee.org/document/5167255>
