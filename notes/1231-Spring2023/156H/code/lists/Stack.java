package unl.soc.lists;

import java.util.LinkedList;
import java.util.List;

public class Stack<T> {
	
	private int maxSize = 0; //0 = no limit
	private List<T> elements = new LinkedList<>();
	
	public Stack(int maxSize) {
		this.maxSize = maxSize;
	}

	public Stack() {
		this.maxSize = 0;
	}

	/**
	 * Pushes (adds) the given element <code>x</code> to the 
	 * top of the stack.
	 * 
	 * Throws an {@link IllegalArgumentException} if the element
	 * is <code>null</code>.
	 * 
	 * @param x
	 */
	public void push(T x) {
		if(x == null) {
			throw new IllegalArgumentException("This stack doesn't support nulls");
			//return;
		} else if(this.isFull()) {
			throw new IllegalArgumentException("Full stack!");
		}
		elements.add(x);
	}
	
	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return
	 */
	public T pop() {
		
		if(this.isEmpty()) {
			throw new IllegalStateException("attempted to pop an empty stack");
			//return null;
		}
		
		return elements.remove(this.elements.size()-1);
	}
	
	public T peek() {
		
		if(this.isEmpty()) {
			throw new IllegalStateException("attempted to peek at an empty stack");
			//return null;
		}
		
		return elements.get(this.elements.size()-1);
	}
	
	public boolean isEmpty() {
		return this.elements.isEmpty();
	}

	public boolean isFull() {
		if(this.maxSize == 0) {
			return false;
		} else {
			return (this.elements.size() == this.maxSize);
		}
	}

}
