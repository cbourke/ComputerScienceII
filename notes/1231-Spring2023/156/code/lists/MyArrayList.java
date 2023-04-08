package unl.soc.lists;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {

	private int size;
	private T arr[];
	
	public MyArrayList() {
		this.arr = (T[]) new Object[10];
		this.size = 0;
	}
	
	/**
	 * Adds the given element <code>x</code> to the end
	 * of this list.
	 * 
	 * @param x
	 */
	public void addElement(T x) {
		this.addElementAtIndex(x, this.size);
	}
	
	public void addElementToStart(T x) {
		this.addElementAtIndex(x, 0);
	}
	
	/**
	 * Replaces the element at the given <code>index</code>
	 * with <code>x</code>
	 * 
	 * @param x
	 * @param index
	 */
	public T replaceElementAtIndex(T x, int index) {
		//TODO: consider creating a private index checker
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		T temp = this.arr[index];
		this.arr[index] = x;
		return temp;
	}
	
	public T removeElementAtIndex(int index) {

		//TODO: think about possibly shrinking the array if necessary
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		
		T temp = this.arr[index];
		
		for(int i=index; i<this.size-1; i++) {
			this.arr[i] = this.arr[i+1];
		}
		this.size--;
		
		return temp;

	}
	
	public void addElementAtIndex(T x, int index) {

		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}

		
		if(this.arr.length == this.size) {
			this.arr = Arrays.copyOf(this.arr, this.arr.length + 1000);
		}		

		//shift stuff up from index... the end
		// start from the end and work up to index...
		//   copy arr[i] into arr[i+1]
		for(int i=this.size-1; i>=index; i--) {
			arr[i+1] = arr[i];
		}
		this.arr[index] = x;
		this.size++;
	}
	
	/**
	 * Returns the element stored at the given <code>index</code>.
	 * 
	 * @param index
	 * @return
	 */
	public T getElementAtIndex(int index) {
		
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		
		return this.arr[index];
	}
	
	public int getSize() {
		return this.size;
	}
	
	public String toString() {
		
		if(this.size == 0) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for(int i=0; i<this.size-1; i++) {
			sb.append(this.arr[i] + ", ");
		}
		sb.append(this.arr[this.size-1]);
		sb.append(" ]");
		return sb.toString();
	}

}
