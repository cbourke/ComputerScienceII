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

	public boolean isEmpty() {
		return (this.underlyingList.size() == 0);
	}
	
	public T peek() {
		return this.underlyingList.get(this.underlyingList.size() - 1);
	}

	public static void main(String args[]) {

		Stack<Integer> s = new Stack<>();

		for (int i = 10; i <= 100; i += 10) {
			s.push(i);
		}

		while (!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}

}
