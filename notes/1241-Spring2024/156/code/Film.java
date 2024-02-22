package unl.soc;

public class Film {
	
	private final String eidr;
	private final String title;
	private final Person director;

	public Film(String eidr, String title, Person director) {
		super();
		this.eidr = eidr;
		this.title = title;
		this.director = director;
	}

	public String getEidr() {
		return eidr;
	}

	public String getTitle() {
		return title;
	}

	public Person getDirector() {
		return director;
	}

	
	

}
