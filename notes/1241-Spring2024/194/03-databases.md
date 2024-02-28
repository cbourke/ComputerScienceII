# Databases & SQL
## ECEN 156 - Spring 2024

### Introduction/Overview

* Flat data files are insufficient for storing data
  * Bad Formatting
  * Inconsistency
  * No types: int, double, strings, we ONLY have strings in a file!
  * Redundancy of data leads to potential failure points and data anomalies
  * Any processing requires processing the ENTIRE file: extremely inefficient: not sorted, not organized, etc. it is one on giant "BLOB" = "Binary Large OBject"
  * There are file lock issues: no concurrency
  * There is no security: one file is an all-or-nothing thing
  * No integrity of data: you can do anything to any record to any column (no protection of data)

### Solution: use a RDBMS (Relational Database Management System)

* MySQL (MariaDB), MSSQL, Oracle, PostgreSQL, etc.
* 1970 Edgar Codd (IBM)
* Key aspects: data is stored in separate tables
* Tables have columns (pieces of data with a particular TYPE: int, double, varchar) and rows (individual records)
* Each column has a *type* and a *name*
* Each row corresponds to a single record
* Each record in a table is *uniquely* identified a *primary key* (PK) that are generally automatically created by the database
* Records in different tables are *related* to each other through *foreign keys* (FKs)
* Foreign keys define relations between tables:
  * One-to-many relationship: one record in table $A$ may be related to many records in table $B$
  * Many-to-one relationship: same thing but from the perspective of table $B$
  * Many-to-many relationship: one record in $A$ can reference many in $B$ AND vice versa
  * One-to-one relationship: one record in $A$ relates to one record in $B$ (maybe ignore this one)

## Getting Started

* We suggest using mysql workbench: https://www.mysql.com/products/workbench/
* You all have access to a database named after your canvas login (`cbourke3`) on a server named `cse-linux-01.unl.edu`
* Your password should have been sent to you via your huskers email this morning
* DO NOT use this password for anything else, do NOT change your mysql password to something you care about or use for other things
* If you lose it and/or need to change it: you'll need to contact `manager@cse.unl.edu`

```text









```
