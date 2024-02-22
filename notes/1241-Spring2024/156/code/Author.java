package unl.soc;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Author extends Person {

	private List<Book> authoredBooks;
	
	public Author(String lastName, String firstName, LocalDate dateOfBirth) {
		super(lastName, firstName, dateOfBirth);
		this.authoredBooks = new ArrayList<>();
	}

	public void addBook(Book b) {
		if(b == null) {
			return;
		}
		this.authoredBooks.add(b);
	}
	
	public List<Book> getBooks() {
		return this.authoredBooks;
	}
	

	public static void main(String args[]) {
		
		
		Person douglasAdams = new Author("Adams", "Douglas", LocalDate.of(1952, 3, 11));		

		Book dirkGently = new Book("456", "Dirk Gently's Holistic Detective Agency", douglasAdams, "ABC002", 5.0, 1987);
		Book teaTime = new Book("123", "The Long Dark Tea-Time of the Soul", douglasAdams, "ABC001", 5.0, 1988);
		
		Author douglasAdamsAsAuthor = (Author) douglasAdams;
		douglasAdamsAsAuthor.addBook(dirkGently);
		douglasAdamsAsAuthor.addBook(teaTime);
		
		System.out.println(douglasAdams);

		Person davidLynch = new Director("Lynch", "David", LocalDate.of(1946, 1, 20));		

		Film eraserhead = new Film("F001", "Eraserhead", davidLynch);
		Film dune = new Film("F002", "Dune", davidLynch);
		
		if(douglasAdams instanceof Director) {
			Director davidLynchAsDirector = (Director) douglasAdams;
			davidLynchAsDirector.addFilm(eraserhead);
			davidLynchAsDirector.addFilm(dune);
		}
		
		System.out.println(davidLynch.getAgeInYears());
		System.out.println(douglasAdams.getAgeInYears());
		
		Person p = new Person("Mann", "Michael", null);
		//directed Heat
		// wrote Heat 2
		
	}

}
