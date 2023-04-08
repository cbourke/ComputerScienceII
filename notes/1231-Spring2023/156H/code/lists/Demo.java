package unl.soc.lists;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import unl.soc.Person;

public class Demo {

	public static void main(String[] args) {

		PriorityQueue<Person> q = new PriorityQueue<>();

		while(true) {
			q.enqueue(new Person("Alpha", "Beta"), 10);
			q.dequeue();
		}

	}

}
