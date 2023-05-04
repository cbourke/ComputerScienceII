package uno.ece.trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class Demo {

	public static void main(String[] args) {

		
		Comparator<Integer> INT_CMP_REV = new Comparator<>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
			
		};

		Comparator<Integer> natural = INT_CMP_REV.reversed();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(natural);
		Random r = new Random();
		for(int i=0; i<10; i++) {
			int x = r.nextInt(100);
			pq.offer(x);
		}
		System.out.println(pq);
		while(!pq.isEmpty()) {
			int y = pq.poll();
			System.out.print(y+", ");
		}

		
	}

}
