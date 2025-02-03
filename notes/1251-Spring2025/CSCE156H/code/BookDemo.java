package unl.soc;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookDemo {

	public static List<Book> loadBookData(String filePath) {

		List<Book> books = new ArrayList<>();

		try (Scanner s = new Scanner(new File(filePath))) {
			s.nextLine();
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (!line.isEmpty()) {
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
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		return books;

	}
	
	public static void main(String args[]) {
		
		List<Book> books = BookDemo.loadBookData("data/books.csv");
				
		//what is the best/worst book?
		//1. sort by ratings
		//  a. Create a comparator for books by rating
//		Comparator<Book> byRating = new Comparator<>() {
//
//			@Override
//			public int compare(Book a, Book b) {
//				if(a.getRating() < b.getRating()) {
//					return 1;
//				} else if(a.getRating() > b.getRating()) {
//					return -1;
//				} else {
//					return 0;
//				}
//			}
//			
//		};
		
		//more modern:
		Comparator<Book> byRatingAsc = Comparator.comparing(Book::getRating);
		Comparator<Book> byRatingDesc = byRatingAsc.reversed();
		
		//lambda function/functional style
		Collections.sort(books, (a, b) -> Double.compare(b.getRating(), a.getRating()));
		for(Book b : books) {
			System.out.println(b);
		}

		//oldest/newest:
		System.out.println("\n===============================\n");
		Comparator<Book> byYear = Comparator.comparing(Book::getPublicationYear);
		Collections.sort(books, byYear);
		for(Book b : books) {
			System.out.println(b);
		}

		//author then by year
		System.out.println("\n===============================\n");
		Comparator<Book> byAuthorYear = 
				Comparator.comparing(Book::getAuthor)
				          .thenComparing(Book::getPublicationYear);
		Collections.sort(books, byAuthorYear);
		for(Book b : books) {
			System.out.println(b);
		}
		
		//searching
		//find A murakami book: using binary search
		Person murakami = new Person("Haruki", "Murakami");
		Book murakamiKey = new Book(murakami);
		//1. sort the collection by author
		Comparator<Book> byAuthor = 
				Comparator.comparing(Book::getAuthor);
		Collections.sort(books, byAuthor);
		int index = Collections.binarySearch(books, murakamiKey, byAuthor);
		Book someMurakamiBook = books.get(index);
		System.out.println("\n===============================\n");
		System.out.println(someMurakamiBook);

		//organize all books by their authors
		//map: Author => List<Book>
		Map<Person, List<Book>> authorToBooks = new HashMap<>();
		//outline:
		// for each book:
		//   find the author's key/list
		//   if list exists: add the book
		//   if list does not exist: create it and add the book
		
		
		
	}

}
