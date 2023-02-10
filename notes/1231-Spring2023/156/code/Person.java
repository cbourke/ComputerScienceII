package soc.unl;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a person
 * 
 * @author cbourke
 *
 */
public class Person implements Comparable<Person> {

	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;

	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public String toString() {
		return String.format("%s, %s", this.lastName, this.firstName);
	}

	@Override
	public int compareTo(Person that) {
		// compare this person to that person
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

}
