package uno.ece.lists;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {

	private int size;
	private T arr[];

	public MyArrayList() {
		this.arr = (T[]) new Object[10];
		this.size = 0;
	}

	/**
	 * Adds the given value <code>x</code> to the end
	 * of the list.
	 * 
	 * @param x
	 */
	public void addElement(T x) {
		this.insertElementAtIndex(x, this.size);
	}
	
	public void removeLastElement() {
		this.removeElementAtIndex(this.size-1);
	}

	public String toString() {

		if (this.size == 0) {
			return "[empty]";
		}
		String s = "[ ";
		for (int i = 0; i < this.size - 1; i++) {
			s += this.arr[i] + ", ";
		}
		s += this.arr[this.size - 1];
		s += " ]";
		return s;
	}

	/** 
	 * Retrieves the element at the given <code>index</code>.
	 * 
	 * Throws an exception for any invalid indices.
	 * 
	 * @param index
	 * @return
	 */
	public T getElementAtIndex(int index) {
		if(index < 0 || index >= this.size) {
			throw new RuntimeException("Invalid index: " + index);
		}
		return this.arr[index];
	}
	
	//TODO: documentation!
	public int getSize() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public T replaceElementAtIndex(T x, int index) {
		if(index < 0 || index >= this.size) {
			throw new RuntimeException("Invalid index " + index);
		}
		T temp = this.arr[index];
		this.arr[index] = x;
		return temp;
	}
	
	public T removeElementAtIndex(int index) {

		if(this.isEmpty()) {
			throw new RuntimeException("List is already empty!");
		}
		if(index < 0 || index >= this.size) {
			throw new RuntimeException("Invalid index " + index);
		}

		T temp = arr[index];
		for(int i=index; i<this.size; i++) {
			//shift down:
			this.arr[i] = this.arr[i+1];
		}
		size--;
		return temp;
	}

	public void insertElementAtIndex(T x, int index) {
		
		if(index < 0 || index > this.size) {
			throw new RuntimeException("Invalid index: " + index);
		}
		
		if(this.size == this.arr.length) {
			this.arr = Arrays.copyOf(this.arr, this.arr.length + 10000);
		}		

		//TODO: shift elements from index to size - 1 "down" or "over" to
		// make room
		for(int i=this.size-1; i>=index; i--) {			
			//move element arr[i] to arr[i+1]
			arr[i+1] = arr[i];
		}
		
		this.arr[index] = x;
		size++;
	}
	

}
