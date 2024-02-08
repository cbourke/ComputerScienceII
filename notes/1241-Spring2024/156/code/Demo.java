package unl.soc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 * Class used for in-class demonstrations.
 * 
 * Author: Chris Bourke Date: 2024-01-01
 * 
 */
public class Demo {

	public static void main(String args[]) {

		//we want to know the highest rated book
		//what is the lowest rated book?
		//what is the oldest book
		//what are all the books by Terry Pratchett? 

		List<Book> books = BookUtils.loadBooks("data/book_data.csv");

		System.out.println("Books, original order:");
		for(Book b : books) {
			System.out.println(b);
		}
		System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n\n");
		
		//Sort the books with respect to rating (descending)
		//Collections.sort(books);
		//Best book is the first
		//worst book is last
		
		Comparator<Book> cmpBookByRating = new Comparator<Book>() {

			@Override
			public int compare(Book a, Book b) {
				if(a.rating < b.rating) {
					return 1;
				} else if(a.rating > b.rating) {
					return -1;
				} else {
					return 0;
				}
			}
			
		};
		
		Collections.sort(books, cmpBookByRating);
		System.out.println("Books, by rating:");
		for(Book b : books) {
			System.out.println(b);
		}
		System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n\n");
		
		
		Comparator<Book> cmpBookByYear = Comparator
				.comparing(Book::getYear)
				.reversed()
				.thenComparing(Book::getRating);
		
		Collections.sort(books, cmpBookByYear);
		System.out.println("Books, by year:");
		for(Book b : books) {
			System.out.println(b);
		}
		System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n\n");

		//first attempt: search for books by Terry Pratchett
		
		Comparator<Book> byAuthor = Comparator
				.comparing(Book::getAuthor);

		//search using *binary search*...
		Person author = new Person("Pratchett", "Terry");
		Book bookKey = new Book();
		bookKey.author = author;
		Collections.sort(books, byAuthor);
		int index = Collections.binarySearch(books, bookKey, byAuthor);
		System.out.printf("Found a book, %s at index %d\n", books.get(index), index);

		//Even better way: USE MAPS!
		//Author -> [LIST of the books they've written]
		Map<Person, List<Book>> authorMap = new HashMap<>();
		//1. get a set of all authors
		Set<Person> authors = new HashSet<>();
		for(Book b : books) {
			authors.add(b.author);
		}

		// For each book B:
		//    get the set in the map of B's author...
		//    add the book B to the author's set....
		for(Book b : books) {
			List<Book> authorsBooks = authorMap.get(b.author);
			//if thisi s the first time we've seen this author...
			// authorsBooks will be null...
			if(authorsBooks == null)  {
				authorsBooks = new ArrayList<>();
			}
			//add the book to the author's list
			authorsBooks.add(b);
			//3.   add the book list to the map:
			//     Author -> List[Books]
			authorMap.put(b.author, authorsBooks);
		}

		//for each author in the map...
		//   print out the books one to a line...
		//   BUT print out authors in order...
		//first: dump all of the authors to a list to sort it...
		List<Person> myAuthors = new ArrayList<>(authorMap.keySet());
		//second: sort it in order of lastname/firstname
		Collections.sort(myAuthors);
		for(Person a : myAuthors) {
			System.out.println(a);
			for(Book b : authorMap.get(a)) {
				System.out.println("\t" + b);
			}
		}


	}

}
