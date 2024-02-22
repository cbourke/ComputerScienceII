package unl.soc;

public class DirectorAlt  {
	
	//composition instead of inheritance
	private Person person;

	public Person getDirectorAsPerson() {
		return this.person;
	}
	
	public String getLastName() {
		return this.person.getLastName();
	}
}
