package uno.ece;

import java.util.Objects;

public class Person implements Comparable<Person> {

	private String firstName;
	private String lastName;

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

	@Override
	public String toString() {
		return this.lastName + ", " + this.firstName;
	}

	@Override
	public int compareTo(Person that) {
		//last name then by first name:
		int result = this.lastName.compareTo(that.lastName);
		if(result == 0) {
			//compare the first name because they have the same last name
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
