package unl.soc.lists;

import java.util.LinkedList;

public class Stack<T> {

	private LinkedList<T> underlyingList;

	public Stack() {
		this.underlyingList = new LinkedList<T>();
	}

	public void push(T item) {
		this.underlyingList.add(item);
	}

	public T pop() {
		return this.underlyingList.remove(this.underlyingList.size() - 1);
	}

	public int size() {
		return this.underlyingList.size();
	}

	public T peek() {
		return this.underlyingList.getLast();
	}

	public static void main(String args[]) {
		Stack<Integer> s = new Stack<>();

		// s.insert(42, 25);
		s.push(10);
		s.push(20);
		s.push(30);
		s.push(40);

		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}

}
