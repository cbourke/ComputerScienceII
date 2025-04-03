package unl.soc.lists;

import java.util.LinkedList;

public class Queue<T> {

	private LinkedList<T> underlyingList;

	public Queue() {
		this.underlyingList = new LinkedList<>();
	}

	public void enqueue(T item) {
		this.underlyingList.add(item);
	}

	public T dequeue() {
		if(this.isEmpty()) {
			return null;
		}
		return this.underlyingList.removeFirst();
	}

	public int size() {
		return this.underlyingList.size();
	}

	public boolean isEmpty() {
		return (this.underlyingList.size() == 0);
	}
	
	public T peek() {
		return this.underlyingList.getFirst();
	}

	public static void main(String[] args) {

		Queue<Integer> q = new Queue();
		for (int i = 10; i <= 100; i += 10) {
			q.enqueue(i);
		}

		while (!q.isEmpty()) {
			System.out.println(q.dequeue());
		}
		
		System.out.println(q.dequeue());
		

	}

}
