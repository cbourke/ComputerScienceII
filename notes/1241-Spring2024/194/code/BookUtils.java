package uno.ece;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A collection of utility functions for the {@link Book} object
 */
public class BookUtils {

	public static List<Book> loadBooks(String filePath) {

		List<Book> books = new ArrayList<>();
		File h = new File(filePath);
		try (Scanner s = new Scanner(h)) {
			s.nextLine();
			while (s.hasNextLine()) {
				String line = s.nextLine();
				String tokens[] = line.split(",");
				if(tokens.length == 7) {
					int id = Integer.parseInt(tokens[0]);
					String title = tokens[1];
					String lastName = tokens[2];
					String firstName = tokens[3];
					Person author = new Person(lastName, firstName);
					String isbn = tokens[4];
					double rating = Double.parseDouble(tokens[5]);
					int year = Integer.parseInt(tokens[6]);
					Book b = new Book(id, title, author, isbn, rating, year);
					books.add(b);
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return books;
	}

}
