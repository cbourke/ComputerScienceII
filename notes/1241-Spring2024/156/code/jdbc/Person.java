package unl.soc;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a person.
 */
public class Person implements Comparable<Person> {

	private Integer personId;
	private String lastName;
	private String firstName;
	private LocalDate dateOfBirth;
	private List<String> email;
	
	public Person(Integer personId, String lastName, String firstName, LocalDate dateOfBirth) {
		this.personId = personId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.email = new ArrayList<>();
	}	
	
	public Person(String lastName, String firstName, LocalDate dateOfBirth) {
		this(null, lastName, firstName, dateOfBirth);
	}

	public Person(String lastName, String firstName) {
		this(lastName, firstName, null);
	}

	@Override
	public String toString() {
		return this.lastName + ", " + firstName + " emails: " + this.email;
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

	@Override
	public int compareTo(Person that) {
		int result = this.lastName.compareTo(that.lastName);
		if (result == 0) {
			result = this.firstName.compareTo(that.firstName);
		}
		return result;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public int getAgeInYears() {
		return Period.between(this.getDateOfBirth(), LocalDate.now()).getYears();
	}

	public void addEmail(String email) {
		this.email.add(email);
	}
	
	public List<String> getEmails() {
		return this.email;
	}


}
