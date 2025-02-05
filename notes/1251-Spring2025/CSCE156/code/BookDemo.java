package unl.soc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookDemo {

	public static List<Book> loadBooks() {
		// Question: which is the best book?
		//           what book or books were written by Murakami
		//           what is the oldest book?
		List<Book> books = new ArrayList<>();
		File f = new File("data/books.csv");
		try {
			Scanner s = new Scanner(f);
			//skip the first line:
			s.nextLine();
			while(s.hasNextLine()) {
				String line = s.nextLine();
				if(!line.isEmpty()) {
					//process the the line
					String tokens[] = line.split(",");
					int bookId = Integer.parseInt(tokens[0]);
					String title = tokens[1];
					Person author = new Person(tokens[2], tokens[3]);
					String isbn = tokens[4];
					double rating = Double.parseDouble(tokens[5]);
					String publisher = tokens[6];
					int pubYear = Integer.parseInt(tokens[7]);
					//LocalDate dateRead = LocalDate.parse(tokens[8]);
					Book b = new Book(bookId, title, author, isbn, rating, publisher, pubYear);					
					books.add(b);
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return books;
	}

	public static void main(String[] args) {
			
		List<Book> books = loadBooks();
		for(Book b : books) {
			System.out.println(b);
		}
		
		//sort books by title
		Comparator<Book> byTitle = new Comparator<>() {
			@Override
			public int compare(Book a, Book b) {
				return a.getTitle().compareTo(b.getTitle());
			}
		};
		Collections.sort(books, byTitle);
		System.out.println("\n-=-==--=-=-=-=-=-=-==-=-=-==-=-==--\n");
		for(Book b : books) {
			System.out.println(b);
		}
		
		//sort books by rating
		Comparator<Book> byRating = new Comparator<>() {
			@Override
			public int compare(Book a, Book b) {
				if(a.getRating() < b.getRating()) {
					return 1;
				} else if(a.getRating() > b.getRating()) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		Collections.sort(books, byRating);
		System.out.println("\n-=-==--=-=-=-=-=-=-==-=-=-==-=-==--\n");
		for(Book b : books) {
			System.out.println(b);
		}
		
		Book bestBook = books.getFirst();
		System.out.println("Best: " + bestBook);
		Book worstBook = books.getLast();
		System.out.println("Best: " + worstBook);
		
		//oldest?  Newest?
		Comparator<Book> byYear = Comparator.comparing(Book::getPublishYear);
		Collections.sort(books, byYear);
		System.out.println("\n-=-==--=-=-=-=-=-=-==-=-=-==-=-==--\n");
		for(Book b : books) {
			System.out.println(b);
		}
		
		//sort by author in descending order, but then by year in ascending order
		Comparator<Book> byAuthorThenYear = 
				Comparator.comparing(Book::getAuthor)
				          .reversed()
				          .thenComparing(Book::getPublishYear);
		Collections.sort(books, byAuthorThenYear);
		System.out.println("\n-=-==--=-=-=-=-=-=-==-=-=-==-=-==--\n");
		for(Book b : books) {
			System.out.println(b);
		}
		
		//find _a_ book by Haruki Murakami
		Person author = new Person("Haruki", "Murakami");
		Book murakamiKey = new Book(author);
		Comparator<Book> byAuthor = Comparator.comparing(Book::getAuthor);
		Collections.sort(books, byAuthor);
		int index = Collections.binarySearch(books, murakamiKey, byAuthor);
		Book murakamiBook = books.get(index);
		System.out.println(murakamiBook);
		
		//find ALL books organized by author
		//Map: Person => List<Book>
		Map<Person, List<Book>> authorToBooks = new HashMap<>();
		//for each book b:
		for(Book b : books) {
			//  get the author a 
			Person a = b.getAuthor();
			//  get the list of books in authorToBooks by author a
			List<Book> authorsBooks = authorToBooks.get(a);
			//  if this is the first time we've seen the book/author:
			if(authorsBooks == null) {
				//     create a new list of books
				authorsBooks = new ArrayList<>();
			}
			//  add the book b to the list
			authorsBooks.add(b);
			//  make sure it is put into the map
			authorToBooks.put(a, authorsBooks);
		}
		
		//print the map:
		for(Person a : authorToBooks.keySet()) {
			System.out.println(a);
			for(Book b : authorToBooks.get(a)) {
				System.out.println("\t" + b);
			}
		}
		

	}

}
