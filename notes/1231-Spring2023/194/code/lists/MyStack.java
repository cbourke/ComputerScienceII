package uno.ece.lists;

import java.util.LinkedList;
import java.util.List;

public class MyStack<T> {
	
	private static final int DEFAULT_MAX_SIZE = 100;
	private final int maxSize;
	private List<T> stack = new LinkedList<>();
	
	public MyStack() {
		this.maxSize = DEFAULT_MAX_SIZE;
	}

	public MyStack(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * Pushes (adds) the given element <code>x</code>
	 * to the top of the stack.
	 * 
	 * @param x
	 */
	public void push(T x) {
		
		if(x == null) {
			throw new IllegalArgumentException("This stack does not support nulls");
		} else if(this.stack.size() == 100) {
			throw new IllegalStateException("Stack is full!");
		}
		
		this.stack.add(0, x);
	}
	
	/**
	 * Removes and returns the element at the top of this
	 * stack.
	 * 
	 * @return
	 */
	public T pop() {
		
		if(this.stack.size() == 0) {
			//empty stack
			//return null;
			throw new IllegalStateException("Emtpy stack!");
		}
		
		return this.stack.remove(0);
	}
	
	public T peek() {
		return this.stack.get(0);
	}
	
	public boolean isEmpty() {
		return (this.stack.size() == 0);
	}

}
