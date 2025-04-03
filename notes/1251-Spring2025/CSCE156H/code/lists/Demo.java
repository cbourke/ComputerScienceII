package unl.soc.lists;

import java.util.Deque;
import java.util.LinkedList;

public class Demo {

	public static void main(String[] args) {

		Deque<Integer> foo = new LinkedList<>();

		// treat it like a stack:
		foo.push(10);
		foo.push(20);
		foo.push(30);

		while (!foo.isEmpty()) {
			System.out.println(foo.pop());
		}
		
		//treat it like a queue:
		foo.offer(100);
		foo.offer(200);
		foo.offer(300);
		
		while (!foo.isEmpty()) {
			System.out.println(foo.poll());
		}

	}

}
