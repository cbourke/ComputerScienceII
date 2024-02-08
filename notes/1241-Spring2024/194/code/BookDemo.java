package uno.ece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookDemo {

	public static void main(String args[]) {

		//worst book
		//best book
		//oldest book
		//all books by Terry Pratchett

//		Person a = new Person();
//		a.lastName = "Anderson";
//		a.firstName = "Poul";
//		Book myCurrentBook = new Book(338325,"The High Crusade",a,"9780743475280",3.89,1960);
		
		List<Book> books = BookUtils.loadBooks("data/book_data.csv");

		for(Book b : books) {
			System.out.println(b);
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		
		//TODO: sort them by rating in descending order
		Comparator<Book> cmpBookByRatingDescending = new Comparator<>() {
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

		Collections.sort(books, cmpBookByRatingDescending);
		
		Book bestBook = books.get(0);
		Book worstBook = books.get(books.size()-1);
		System.out.println("Best:  " + bestBook);
		System.out.println("Worst: " + worstBook);
		
		Comparator<Book> cmpBookByYear = Comparator.comparing(Book::getYear);
		Collections.sort(books, cmpBookByYear);
		Book oldestBook = books.get(0);
		System.out.println("Oldest: " + oldestBook);

		//print all books in chronological order
		// but if published in the same (2023): best to worst
		Comparator<Book> cmpBookByYearThenByRating = Comparator
				.comparing(Book::getYear)
				.reversed()
				.thenComparing(Book::getRating)
				.reversed();

		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		for(Book b : books) {
			System.out.println(b);
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		
		//print all books by author: last name, first name
		// then by publish year: newest to oldest
		Comparator<Book> cmpBookByAuthorThenYear = Comparator
				.comparing(Book::getAuthor)
				.thenComparing(Book::getYear)
				.reversed();

		Collections.sort(books, cmpBookByAuthorThenYear);
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		for(Book b : books) {
			System.out.println(b);
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

		//all books by Terry Pratchett
		//use: binarySearch
		// 1. sort by author
		// 2. binary search by author...
		Person terry = new Person("Pratchett", "Terry");
		Book bookKey = new Book(terry);
		Comparator<Book> cmpBookByAuthor = Comparator
				.comparing(Book::getAuthor);
		Collections.sort(books, cmpBookByAuthor);
		int index = Collections.binarySearch(books, bookKey, cmpBookByAuthor);		
		System.out.println("Found book " + books.get(index) + " at index " + index);
		
		
		//better data processing using "smart data structures": MAPS
		// Map: Author -> List of Books they've written
		Map<Person, List<Book>> authorMap = new HashMap<>();

		//first try: try getting all of the (unique) authors
		Set<Person> authors = new HashSet<>();
		//for each book: 
		//   place the author into the set...
		for(Book b : books) {
			authors.add(b.getAuthor());
		}
		for(Person p : authors) {
			System.out.println(p);
		}

		//for each book B:
		for(Book b : books) {
			// a) get the author...
			Person author = b.getAuthor();
			// b) get the list of B's authors's books
			List<Book> authorsBooks = authorMap.get(author);
			if(authorsBooks == null) {
				authorsBooks = new ArrayList<>();
				authorMap.put(author, authorsBooks);
			}
			// c) add the book B to this list
			authorsBooks.add(b);
		}
		
		//for each author, print (nicely) all their books...
		//dump the keyset to a list...
		List<Person> sortedAuthors = new ArrayList<>(authorMap.keySet());
		Collections.sort(sortedAuthors);
		for(Person author : sortedAuthors) {
			List<Book> authorsBooks = authorMap.get(author);
			System.out.println(author);
			for(Book b : authorsBooks) {
				System.out.println("\t" + b);
			}
		}

		Book b = books.get(0);
		System.out.println(b);
		System.out.println(b.getAgeInYears());

		
	}
}
