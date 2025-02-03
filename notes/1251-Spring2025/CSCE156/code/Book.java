package unl.soc;

import java.time.LocalDate;

public class Book {

	private int bookId;
	private String title;
	private Person author;
	private String isbn;
	private double rating;
	private String publisher;
	private int publishYear;
	private LocalDate dateRead;
	
	
	public Book(int bookId, String title, Person author, String isbn, double rating, String publisher, int publishYear,
			LocalDate dateRead) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.rating = rating;
		this.publisher = publisher;
		this.publishYear = publishYear;
		this.dateRead = dateRead;
	}

	public Book(int bookId, String title, Person author, String isbn, double rating, String publisher,
			int publishYear) {
		this(bookId, title, author, isbn, rating, publisher, publishYear, null);
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

	public int getPublishYear() {
		return publishYear;
	}

	public LocalDate getDateRead() {
		return dateRead;
	}

	@Override
	public String toString() {
		return String.format("%s by %s (%.2f) in %d", this.title, this.author, this.rating, this.publishYear);
	}

	
	
	
	
	
	
	
	
	

}
