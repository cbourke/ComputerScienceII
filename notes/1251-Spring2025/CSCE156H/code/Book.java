package unl.soc;

import java.time.LocalDate;

/**
 * Represents a book
 */
public class Book {

	private int bookId;
	private String title;
	private Person author;
	private String isbn;
	private double rating;
	private String publisher;
	private int publicationYear;
	private LocalDate dateRead;

	public Book(int bookId, String title, Person author, String isbn, double rating, String publisher,
			int publicationYear, LocalDate dateRead) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.rating = rating;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
		this.dateRead = dateRead;
	}

	public Book(int bookId, String title, Person author, String isbn, double rating, String publisher,
			int publicationYear) {
		this(bookId, title, author, isbn, rating, publisher, publicationYear, null);
	}
	
	public Book(Person author) {
		//TODO: clean this up
		this.author = author;
	}

	public int getBookId() {
		return bookId;
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

	public String getPublisher() {
		return publisher;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public LocalDate getDateRead() {
		return dateRead;
	}

	@Override
	public String toString() {
		return String.format("%s by %s (%.2f) in %d", this.title, this.author, this.rating, this.publicationYear);
	}

}
