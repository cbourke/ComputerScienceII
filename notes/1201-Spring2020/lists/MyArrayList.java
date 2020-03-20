package unl.cse.lists;

import java.util.Arrays;

public class MyArrayList <T> {
	
	private static final int SIZE = 10;
	private T arr[];
	private int size;
	
	public MyArrayList() {
		this.arr = (T []) new Object[SIZE];
		this.size = 0;
	}
	
	private void increaseCapacity() {
		this.arr = Arrays.copyOf(this.arr, this.arr.length + SIZE);
	}
	
	public T getElementAtIndex(int index) {
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("index out of bounds!");
		}
		return this.arr[index];
	}
	
	/**
	 * Adds the given element x to the end of the list
	 * @param x
	 */
	public void addElementToEnd(T x) {		
		addElementAtIndex(x, this.size);
	}
	
	/**
	 * Adds the given element x to the start of the list,
	 * shifting other elements down as necessary
	 * @param x
	 */
	public void addElementToStart(T x) {
		addElementAtIndex(x, 0);
	}
	
	/**
	 * Adds the element x to the list at the given index, 
	 * shifting elements down as necessary.
	 * @param x
	 * @param index
	 */
	public void addElementAtIndex(T x, int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("index out of bounds!");
		}
		//check capacity
		if(this.arr.length == this.size) {
			increaseCapacity();
		}	
			
		//shift elements down
		for(int i=this.size; i>index; i--) {
			this.arr[i] = this.arr[i-1];
		}

		this.arr[index] = x;
		this.size++;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.size; i++) {
			sb.append(this.arr[i] + ", ");
		}
		return sb.toString();
	}
	
	public static void main(String args[]) {
		MyArrayList<Integer> list = new MyArrayList<>();
		for(int i=0; i<11; i++) {
			list.addElementToEnd(i*10);
		}
		list.addElementToStart(42);
		System.out.println(list);
		list.addElementAtIndex(99, 6);
		System.out.println(list);
		
		int y = list.getElementAtIndex(6);
		System.out.println(42);
		
		MyArrayList<String> names = new MyArrayList<>();
		names.addElementAtIndex("Chris", 0);
		names.addElementAtIndex("Joe", 1);
		names.addElementAtIndex("Jane", 0);
		names.addElementAtIndex("Alan", 2);
		System.out.println(names);

	}

}
