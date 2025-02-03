package uno.ece;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Demo {

	public static void main(String args[]) {
		
		Book b = new Book();

		List<Double> ratings = new ArrayList<>();
		File f = new File("data/books.csv");
		Scanner s;
		try {
			s = new Scanner(f);
			//waste the first line which is header data
			s.nextLine();
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (!line.isEmpty()) {
					//Book Id,Title,Last,First,ISBN13,Average Rating,Publisher,Original Publication Year,Date Read
					String tokens[] = line.split(",");
					double rating = Double.parseDouble(tokens[5]);
					ratings.add(rating);
					System.out.println(line);
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		Collections.sort(ratings);
		System.out.println(ratings);

	}

}
