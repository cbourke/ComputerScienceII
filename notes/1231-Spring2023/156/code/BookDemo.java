package soc.unl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	public static void main(String[] args) {

		//TODO: load book data from a CSV file
		List<Book> library = BookUtils.loadCsvData("data/book.csv");

		//TODO: Report the best and worst book
		Comparator<Book> byRating = Comparator.comparing(Book::getRating).reversed();
		Comparator<Book> byAuthor = Comparator
				.comparing(Book::getAuthor);
//		Comparator<Book> byRating = new Comparator<Book>() {
//
//			@Override
//			public int compare(Book a, Book b) {
//				if(a.getRating() > b.getRating()) {
//					return -1;
//				} else if(a.getRating() < b.getRating()) {
//					return 1;
//				} else {
//					return 0;
//				}
//			}
//			
//		};
		Collections.sort(library, byRating);
		Book bestBook = library.get(0);
		Book worstBook = library.get(library.size()-1);
		System.out.println("Worst: " + worstBook);
		System.out.println("Best: " + bestBook);
		for(Book b : library) {
			System.out.println(b + " age is " + b.getAge());
		}
		
		
		Person frank = new Person("Frank", "Herbert");
		Book bookKey = new Book(frank);
		Collections.sort(library, byAuthor);
		int index = Collections.binarySearch(library, bookKey, byAuthor);
		System.out.println("Index = " + index);
		Book franksBook = library.get(index);
		System.out.println(franksBook);

		//TODO: Find *all* books by a certain author (say Frank Herbert)
		// modify: Organize books based on each other
		//  Map: Author's last name -> SET of their books
		//      "Herbert" -> { Dune, Heretics of Dune, ... }
		Map<Person, Set<Book>> bookMap = new HashMap<>();
		for(Book b : library) {
			Set<Book> authorsBooks = bookMap.get(b.getAuthor());			
			//if this is the first time we've seen this author (last name)
			if(authorsBooks == null) {
				//the set was not in the map, so create it
				//then create a new bin/box = a new set and place it into the map
				authorsBooks = new HashSet<>();
				bookMap.put(b.getAuthor(), authorsBooks);
			}
			//else we've seen this before, add it to the bin/box/set
			authorsBooks.add(b);
		}
		//System.out.println(bookMap);
		
		//TODO: produce a book report: how many books by each author?
		//iterate over each key (last name)
		//  for each one, find the set of books and find the count in that set
		//   report it: printing it out
		for(Person a : bookMap.keySet()) {
			Set<Book> s = bookMap.get(a);
			int numberOfBooks = s.size();
			System.out.printf("%-20s %d\n", a, numberOfBooks);
		}
		

		return;

	}

}
