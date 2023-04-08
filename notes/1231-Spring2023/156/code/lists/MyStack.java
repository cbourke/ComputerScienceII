package unl.soc.lists;

import java.util.LinkedList;
import java.util.List;

public class MyStack<T> {

	private int maxCapacity = 0;
	private List<T> elements;

	public MyStack() {
		elements = new LinkedList<>();
	}

	public MyStack(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		elements = new LinkedList<>();
	}

	/**
	 * Removes and returns the element at the top of this stack.
	 * 
	 * @return
	 */
	public T pop() {
		if (this.isEmpty()) {
			// return null;
			throw new IllegalStateException("You cannot pop from an empty stack!");
		}
		return this.elements.remove(this.elements.size() - 1);
	}

	/**
	 * Adds the given element <code>x</code> to the top of this stack.
	 * 
	 * @param x
	 */
	public void push(T x) {
		// if we don't wanna support nulls:
		if (x == null) {
			// 1. NOOP = No Operation
			// return;
			throw new IllegalArgumentException("this stack does not support nulls");
		} else if (this.isFull()) {
			throw new IllegalArgumentException("this stack is full");
		}
		this.elements.add(x);
	}

	public T peek() {
		//TODO: error handling
		return this.elements.get(this.elements.size()-1);
	}
	
	public boolean isEmpty() {
		return this.elements.size() == 0;
	}

	public boolean isFull() {
		return (this.maxCapacity != 0 && this.elements.size() == this.maxCapacity);
	}

}
