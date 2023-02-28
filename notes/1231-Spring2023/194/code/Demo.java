package uno.ece;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		
		
		
		List<Integer> numbers = Arrays.asList(4, 7, 3, 2, 7, 8, 9);
		
		Comparator<Integer> desc = new Comparator<>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return -o1.compareTo(o2);
			}
			
		};
		
		Collections.sort(numbers, desc);
		System.out.println(numbers);

	}

}
