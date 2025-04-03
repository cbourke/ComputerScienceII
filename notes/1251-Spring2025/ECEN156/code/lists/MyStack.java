package uno.ece.lists;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class MyStack<T> {

	private LinkedList<T> underlyingList;

	public MyStack() {
		this.underlyingList = new LinkedList<>();
	}

	public void push(T item) {
		this.underlyingList.addLast(item);
	}

	public T pop() {
		if(this.isEmpty()) {
			return null;
		}
		return this.underlyingList.removeLast();
	}

	public int size() {
		return this.underlyingList.size();
	}

	public boolean isEmpty() {
		return this.underlyingList.size() == 0;
	}
	
	public T peek() {
		return this.underlyingList.getLast();
	}

	public static void main(String args[]) {
		MyStack<Integer> s = new MyStack<>();

		for (int i = 10; i <= 100; i += 10) {
			s.push(i);
		}

		while (!s.isEmpty()) {
			System.out.println(s.pop());
		}

		
		Deque<Integer> stack = new ArrayDeque<>();

		for (int i = 10; i <= 100; i += 10) {
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

}
