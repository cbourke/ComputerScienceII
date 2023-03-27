package unl.soc.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a person
 * 
 * @author cbourke
 *
 */
public class Person implements Comparable<Person> {

	private Integer personId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private List<String> emails;

	public Person(String firstName, String lastName) {
		this(null, firstName, lastName, null);
	}

	public Person(String firstName, String lastName, LocalDate dateOfBirth) {
		this(null, firstName, lastName, dateOfBirth);
	}

	public Person(Integer personId, String firstName, String lastName, LocalDate dateOfBirth) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.emails = new ArrayList<>();

	}

	public int getPersonId() {
		return this.personId;
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
	
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
	public List<String> getEmails() {
		return emails;
	}

	@Override
	public String toString() {
		return String.format("%s, %s (%d) %s", this.lastName, this.firstName, this.personId, this.emails);
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
