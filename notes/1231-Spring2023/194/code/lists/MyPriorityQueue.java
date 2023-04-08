package uno.ece.lists;

import java.util.LinkedList;
import java.util.List;

public class MyPriorityQueue<T> {

	private List<T> elements = new LinkedList<>();
	
	public T dequeue() {
		return elements.remove(0);
	}
	
	public void enqueue(T x) {
		//TODO: priority: search for where x belongs based on its
		// priority and insert it there.
		elements.add(x);
	}
	
	public boolean isEmpty() {
		return (this.elements.size() == 0);
	}


}
