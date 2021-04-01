package unl.cse.lists.honors;

import java.util.LinkedList;

public class Queue<T> {
	
	private LinkedList<T> underlyingList = new LinkedList<>();

	public void enqueue(T item) {
		this.underlyingList.addFirst(item);
	}
	
	public T dequeue() {
		return this.underlyingList.removeLast();
	}
}
