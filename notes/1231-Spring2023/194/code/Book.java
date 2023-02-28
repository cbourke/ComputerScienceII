package uno.ece;

import java.time.LocalDate;

public class Book {

	private String title;
	private Person author;
	private LocalDate releaseDate;
	private double rating;
	
	public Book() {
		
	}	
	
	public double getRating() {
		return this.rating;
	}
		
	
	public String getTitle() {
		return title;
	}

	public Person getAuthor() {
		return this.author;
	}

	public LocalDate getReleaseDate() {
		return this.releaseDate;
	}
	
	public Book(String title, Person author, LocalDate releaseDate, double rating) {

		if(title == null) {
			throw new RuntimeException("You cannot create a book without a title");
		}
		this.author = author;
		this.title = title;
		this.releaseDate = releaseDate;
		this.rating = rating;
	}
	
	
	public Book(Person author) {
		super();
		this.author = author;
	}

	public String toString() {
		return String.format("%s by %s %d (%.2f)", title, this.author, releaseDate, rating);
	}
	
	public int getAge() {
		//TODO: current year - year released, return the result
		return 0;
	}
	
}
