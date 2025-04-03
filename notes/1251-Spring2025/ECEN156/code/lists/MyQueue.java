package uno.ece.lists;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MyQueue<T> {

	private LinkedList<T> underlyingList;

	public MyQueue() {
		this.underlyingList = new LinkedList<>();
	}

	public void enqueue(T item) {
		this.underlyingList.addLast(item);
	}

	public T dequeue() {
		return this.underlyingList.removeFirst();
	}

	public int size() {
		return this.underlyingList.size();
	}

	public boolean isEmpty() {
		return this.underlyingList.size() == 0;
	}

	public T peek() {
		return this.underlyingList.getFirst();
	}

	public static void main(String args[]) {
		MyQueue<Integer> q = new MyQueue<>();

		for (int i = 10; i <= 100; i += 10) {
			q.enqueue(i);
		}

		while (!q.isEmpty()) {
			System.out.println(q.dequeue());
		}

		Deque<Integer> queue = new LinkedList<>();

		for (int i = 10; i <= 100; i += 10) {
			queue.offer(i);
		}

		while (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}

	}

}
