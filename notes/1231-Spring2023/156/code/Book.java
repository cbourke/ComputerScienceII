package soc.unl;

import java.time.LocalDate;
import java.time.Period;

/**
 * This class models a Book
 * 
 * @author cbourke
 *
 */
public class Book {

	private String title;
	private Person author;
	private LocalDate releaseDate;
	private double rating;

	public Book(String title, Person author, LocalDate releaseDate, double rating) {

		if (title == null) {
			throw new IllegalArgumentException("You cannot create a book with no title");
		}
		this.title = title;
		this.author = author;
		this.releaseDate = releaseDate;
		this.rating = rating;
	}

	public Book(Person author) {
		// TODO: maybe refractor so that you don't have this repeated line
		this.author = author;
	}

	@Override
	public String toString() {
		return String.format("%s by %s in %d (%.2f)", this.title, this.author, this.releaseDate.getYear(), this.rating);
	}

	public String getTitle() {
		return title;
	}

	public Person getAuthor() {
		return this.author;
	}

	public int getReleaseDate() {
		return this.getReleaseDate();
	}

	public double getRating() {
		return rating;
	}
	
	/**
	 * Returns the age of this book in years
	 * 
	 * @return
	 */
	public int getAge() {
		LocalDate today = LocalDate.now();
		Period p = this.releaseDate.until(today);
		return p.getYears();
	}

}
