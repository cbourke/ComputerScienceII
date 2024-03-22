package uno.ece;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person implements Comparable<Person> {

	private final Integer personId;
	private final String lastName;
	private final String firstName;
	private LocalDate dateOfBirth;
	private final List<String> emails;

	public Person(Integer personId, String lastName, String firstName, LocalDate dateOfBirth) {
		this.personId = personId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.emails = new ArrayList<>();
	}

	public Person(String lastName, String firstName, LocalDate dateOfBirth) {
		this(null, lastName, firstName, dateOfBirth);
	}

	public String toString() {
		return this.lastName + ", " + this.firstName + "; emails: " + this.emails;
	}

	@Override
	public int compareTo(Person that) {
		int result = this.lastName.compareTo(that.lastName);
		if (result == 0) {
			// tie
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

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public int getYearsOld() {
		int years = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
		return years;
	}
	
	public void addEmail(String email) {
		this.emails.add(email);
	}

}
