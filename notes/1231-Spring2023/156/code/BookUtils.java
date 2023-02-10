package soc.unl;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A collection of utility functions for {@link Book} 
 * instances.
 * 
 * @author cbourke
 *
 */
public class BookUtils {

	/**
	 * Loads book data from the given <code>fileName</code> and
	 * returns a <code>List</code> of {@link Book} instances.
	 * The file is assumed to be a CSV file in the specified format.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Book> loadCsvData(String fileName) {
		
		List<Book> library = new ArrayList<>();
		File f = new File(fileName);
		try {
			Scanner s = new Scanner(f);
			int n = Integer.parseInt(s.nextLine());
			for(int i=0; i<n; i++) {				
				String line = s.nextLine();
				String tokens[] = line.split(",");
				if(tokens.length != 5) {
					throw new IllegalStateException("Invalid number of tokens on line" + line);
				}
				String title = tokens[0];
				String authorFirstName = tokens[1];
				String authorLastName = tokens[2];
				int year = Integer.parseInt(tokens[3]);
				double rating = Double.parseDouble(tokens[4]);
				Person p = new Person(authorFirstName, authorLastName);
				LocalDate releaseDate = LocalDate.of(year, 1, 1);
				Book b = new Book(title, p, releaseDate, rating);
				library.add(b);
			}
			
			s.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return library;
	}
	
}
