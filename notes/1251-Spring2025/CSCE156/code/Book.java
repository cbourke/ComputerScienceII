package unl.soc;

import java.time.LocalDate;
import java.util.Objects;

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
	
	public Book(Person author) {
		this.author = author;
		//TODO: clean this up
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

	@Override
	public int hashCode() {
		return Objects.hash(author, bookId, dateRead, isbn, publishYear, publisher, rating, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && bookId == other.bookId
				&& Objects.equals(dateRead, other.dateRead) && Objects.equals(isbn, other.isbn)
				&& publishYear == other.publishYear && Objects.equals(publisher, other.publisher)
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
				&& Objects.equals(title, other.title);
	}

	
	
	
	
	
	
	
	
	
	

}
