package unl.soc;

/**
 * Represents a Book
 */
public class Book {
	
	String id;
	String title;
	Person author;
	String isbn;
	double rating;
	int year;

	//when you print out this object, print only the title
	public String toString() {
		return this.title + " by " + this.author + " (" + this.year + ", "+ this.rating + ")";
	}
	
	
	
	public double getRating() {
		return this.rating;
	}
	
	public double getYear() {
		return this.year;
	}



	public String getId() {
		return id;
	}



	public String getTitle() {
		return title;
	}






	public Person getAuthor() {
		return this.author;
	}





	public String getIsbn() {
		return this.isbn;
	}
	
	
	

}
