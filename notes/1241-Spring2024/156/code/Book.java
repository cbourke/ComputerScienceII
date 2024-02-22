package unl.soc;

/**
 * Represents a Book
 */
public class Book {

	private String id;
	private String title;
	private Person author;
	private String isbn;
	private double rating;
	private int year;

	public Book(String id, String title, Person author, String isbn, double rating, int year) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.rating = rating;
		this.year = year;
	}

	public Book(Person author) {
		this(null, null, author, null, 0.0, 0);
	}

	public Book() {
	}

	// when you print out this object, print only the title
	public String toString() {
		return this.title + " by " + this.author + " (" + this.year + ", " + this.rating + ")";
	}

	public double getRating() {
		return this.rating;
	}

	public int getYear() {
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

	public int getAgeInYears() {
		return 2024 - this.year;
	}
	

}
