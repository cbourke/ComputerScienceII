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
	
	/**
	 * Adds the given element <code>x</code> at the given
	 * <code>index</code>, automatically shifting stuff down.
	 * 
	 * @param x
	 * @param index
	 */
	public void addElementAtIndex(T x, int index) {
		
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		
		if(this.arr.length == this.size) {
			this.arr = Arrays.copyOf(this.arr, this.arr.length+10);
		}		

		//shift stuff down to make room for x...
		//start at the end...
		//  copy arr[i] into arr[i+1]
		//until i = index
		for(int i=size-1; i>=index; i--) {
			this.arr[i+1] = this.arr[i];
		}
		this.arr[index] = x;
		this.size++;
		
	}
	
	private void checkIndex(int index) {
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
	}
	
	public T getElement(int index) {
		
		this.checkIndex(index);
		
		return this.arr[index];
	}
	
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Replaces the element at the given <code>index</code>
	 * with the element <code>x</code>.
	 * 
	 * @param x
	 * @param index
	 */
	public T replaceElement(T x, int index) {
		this.checkIndex(index);
		T temp = this.arr[index];
		this.arr[index] = x;
		return temp;
	}
	
	public T removeElement(int index) {
		this.checkIndex(index);
		T temp = this.arr[index];
		//shift stuff down starting at index
		for(int i=index; i<this.size; i++) {
			this.arr[i] = this.arr[i+1];
		}
		this.size--;
		
		//TODO: consider shrinking the array
		return temp;
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for(int i=0; i<this.size; i++) {
			sb.append(this.arr[i]);
			sb.append(", ");
		}
		sb.append(" ]");
		return sb.toString();
	}

}
