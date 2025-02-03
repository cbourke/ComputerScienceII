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

public class Demo {

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
		Book worstBook = books.getLast();

	}

}
