package unl.soc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookUtils {

	/**
	 * Loads up a collection of books from the given file/path
	 * and returns a list of them.
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<Book> loadBooks(String filePath) {

		List<Book> books = new ArrayList<>();
		// file input:
		File f = new File(filePath);
		Scanner s;
		try {
			s = new Scanner(f);

			// burn the first line:
			s.nextLine();

			while (s.hasNextLine()) {
				String line = s.nextLine();
				String tokens[] = line.split(",");
				if (tokens.length == 7) {
					Person a = new Person(tokens[2], tokens[3]);
					double rating = Double.parseDouble(tokens[5]);
					int year = Integer.parseInt(tokens[6]);
					Book b = new Book(tokens[0], tokens[1], a, tokens[4], rating, year);
					books.add(b);
				}
			}

			s.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (NoSuchElementException nsee) {
			System.out.println("Okay, no data in the file, moving on....");
		}
		return books;
	}
	
	//breaking encapsulation: 
	public static int getAgeInYears(Book b) {
		return 2024 - b.getYear();
	}


}
