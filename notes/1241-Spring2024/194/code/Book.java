package uno.ece;

import java.time.LocalDate;

/**
 * Represents a Book
 */
public class Book {

	private final int id;
	private final String title;
	private final Person author;
	private final String isbn;
	private final double rating;
	private final int year;


	public Book(int id, String title, Person author, String isbn, double rating, int year) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.rating = rating;
		this.year = year;
		
	}

	public Book(Person author) {
		this(0, null, author, null, -1.0, 0);
	}

	@Override
	public String toString() {
		return this.title + " by " + this.author + " (" + this.rating + " " + this.year + ")";
	}

	public int getYear() {
		return this.year;
	}

	public int getAgeInYears() {
		// TODO: improve this...
		return 2024 - this.year;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Person getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public double getRating() {
		return rating;
	}

}
