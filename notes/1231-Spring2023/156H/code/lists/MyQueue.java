package unl.soc.lists;

import java.util.LinkedList;
import java.util.List;

public class MyQueue<T> {
	
	private List<T> elements = new LinkedList<>();

	public T dequeue() {
		return this.elements.remove(this.elements.size()-1);
	}
	
	public void enqueue(T x) {
		this.elements.add(0, x);
	}

}
