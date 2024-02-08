package uno.ece;

import java.util.Objects;

public class Person implements Comparable<Person> {

	private final String lastName;
	private final String firstName;
	
	public Person(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public String toString() {
		return this.lastName + ", " + this.firstName;
	}

	@Override
	public int compareTo(Person that) {
		int result = this.lastName.compareTo(that.lastName);
		if(result == 0) {
			//tie
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


	
	
	

	
	
	

	
	
}
