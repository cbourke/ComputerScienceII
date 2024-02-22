package unl.soc;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person implements Comparable<Person> {

	private String lastName;
	private String firstName;
	private LocalDate dateOfBirth;

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public Person(String lastName, String firstName, LocalDate dateOfBirth) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}
	
	public Person(String lastName, String firstName) {
		this(lastName, firstName, null);
	}

	
	/**
	 * Copy constructor (produces a brand new, deep copy of the given person).
	 * (Pure copy constructor: it only takes a Person object)
	 */
	public Person(Person person) {
		this.firstName = person.firstName;
		this.lastName = person.lastName;
		this.dateOfBirth = person.dateOfBirth;
	}
	
	public Person(Person person, LocalDate dateOfBirth) {
		this.firstName = person.firstName;
		this.lastName = person.lastName;
		//assign dateOfBirth to the parameter variable instead of copying it...
		this.dateOfBirth = dateOfBirth;
	}

	public String toString() {
		return this.lastName + ", " + this.firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public int compareTo(Person that) {
		int result = this.lastName.compareTo(that.lastName);
		if (result == 0) {
			result = this.firstName.compareTo(that.firstName);
		}
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}
	
	public int getAgeInYears() {
		return Period.between(this.getDateOfBirth(), LocalDate.now()).getYears();
	}



}
