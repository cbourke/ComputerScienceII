# Databases & SQL
## CSCE 156 - Spring 2022

### OOP Review

#### Assignment 3

* *Preliminary* stats: REDACTED
* Many with bad/no output: output (and parsing) should be the *last* thing you do
* Bad correctness caused by bad design
* Design comes first
* Resubmission Opportunity

#### Review

* `Isotope`, `Subscription`, `Product`
* Suppose IFFI updates its business model to add an `Annuity` asset type
* An annuity:
  * Is purchased for an upfront cost and
  * pays fixed monthly amount for
  * a fixed number of years
  * misc: $25 fee to manage
* Example:
  * Purchased (`ANN01`, `A1 Annuity`) for $25,000 on 2022-04-01
  * that pays $500 per month for
  * 5 years

#### Sniff Test

* If you can design and implement this in **ONE** class and not change a single
  line of code (other than parsing code), then you likely have a good design.
* If not: you have a code smell/bad design and need to fix it
* Good design: loose coupling
* Good design: SOLID
  * Single Responsibility
  * Open for extension, closed for modification
  *
* Demo


```text









```
