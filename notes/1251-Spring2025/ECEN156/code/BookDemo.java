package uno.ece;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BookDemo {
	
	public static List<Book> loadBooks(String filePath) {

		List<Book> books = new ArrayList<>();
		File f = new File(filePath);
		Scanner s;
		try {
			s = new Scanner(f);
			// waste the first line which is header data
			s.nextLine();
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (!line.isEmpty()) {
					// Date Read
					String tokens[] = line.split(",");
					int bookId = Integer.parseInt(tokens[0]);
					String title = tokens[1];
					Person author = new Person(tokens[2], tokens[3]);
					String isbn = tokens[4];
					double rating = Double.parseDouble(tokens[5]);
					String publisher = tokens[6];
					int publicationYear = Integer.parseInt(tokens[7]);
					//LocalDate dateRead = LocalDate.parse(tokens[8]);

					Book b = new Book(bookId, title, author, isbn, rating, publisher, publicationYear);
					books.add(b);
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return books;
	}

	public static void main(String args[]) {

		List<Book> books = BookDemo.loadBooks("data/books.csv");
		
		//1. what is the best/worst book?
		//sort books by rating
//		Comparator<Book> cmpBookByRating = new Comparator<>() {
//
//			@Override
//			public int compare(Book a, Book b) {
//				if(a.getRating() < b.getRating()) {
//					return 1; //out of order
//				} else if(a.getRating() > b.getRating()) {
//					return -1; //in order, a's rating is better
//				} else {
//					return 0;
//				}
//			}
//			
//		};
		Comparator<Book> cmpBookByRating = Comparator.comparing(Book::getRating).reversed();
		Collections.sort(books, cmpBookByRating);
		for(Book b : books) {
			System.out.println(b);
		}
		Book bestBook = books.getFirst();
		Book worstBook = books.getLast();
				
		//2. what is the oldest/newest book?
		System.out.println("\n===============================\n");
		Comparator<Book> cmpBookByYear = Comparator.comparing(Book::getPublicationYear);
		Collections.sort(books, cmpBookByYear);
		for(Book b : books) {
			System.out.println(b);
		}
		Book oldestBook = books.getFirst();
		Book newestBook = books.getLast();
		
		//sort by author then by date
		System.out.println("\n===============================\n");
		Comparator<Book> cmpBookByAuthorYear = 
				Comparator.comparing(Book::getAuthor)
						  .reversed()
				          .thenComparing(Book::getPublicationYear);
		Collections.sort(books, cmpBookByAuthorYear);
		for(Book b : books) {
			System.out.println(b);
		}
		
		//3. Find a book by Haruki Murakami
		Person murakami = new Person("Haruki", "Murakami");
		Book murakamiKey = new Book(murakami);
		System.out.println("\n===============================\n");
		Comparator<Book> cmpBookByAuthor = 
				Comparator.comparing(Book::getAuthor);
		Collections.sort(books, cmpBookByAuthor);
		int index = Collections.binarySearch(books, murakamiKey, cmpBookByAuthor);
		Book murakamiBook = books.get(index);
		System.out.println(murakamiBook);	
		
		//I want ALL books organized by ALL authors
		//Map: Person => List<Book>
		Map<Person, List<Book>> authorToBooks = new HashMap<>();
		//for each book:
		for(Book b : books) {
			//   get the author
			Person author = b.getAuthor();
			//   use the author to find their list of books
			List<Book> authorBooks = authorToBooks.get(author);
			//    - what if no such list exists?  create it
			if(authorBooks == null) {
				authorBooks = new ArrayList<>();
			}
			//   add their book to the list
			authorBooks.add(b);
			// put it back into the map:
			authorToBooks.put(author, authorBooks);
		}		

		for(Person author : authorToBooks.keySet()) {
			System.out.println(author);
			for(Book b : authorToBooks.get(author)) {
				System.out.println("\t" + b);
			}
		}
		
		
		
	}

}
