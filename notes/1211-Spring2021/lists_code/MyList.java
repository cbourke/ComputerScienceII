package unl.cse.lists;

import java.util.Arrays;
import java.util.Comparator;

public class MyList<T> {

	private T arr[];
	private int size;
	private Comparator<T> cmp;

	@SuppressWarnings("unchecked")
	public MyList() {
		this.arr = (T[]) new Object[10];
		this.size = 0;
	}

	public MyList(Comparator<T> cmp) {
		this.arr = (T[]) new Object[10];
		this.size = 0;
		this.cmp = cmp;
	}

	private void increaseCapacity() {
		this.arr = Arrays.copyOf(this.arr, this.arr.length+10);
	}
	
	public T getElementAtIndex(int index) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("index " + index + " is out of bounds");
		}		
		return this.arr[index];
	}
	
	public void addElementAtIndex(T x, int index) {
		
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("index " + index + " is out of bounds");
		}
		
		if(this.size == this.arr.length) {
			this.increaseCapacity();
		}

		//shift elements to the right from the end to the index
		for(int i=this.size-1; i>=index; i--) {
			this.arr[i+1] = this.arr[i];
		}
		this.arr[index] = x;
		this.size++;
	}

	public void addToEnd(T x) {
		this.addElementAtIndex(x, this.size);
	}
	
	public void addToStart(T x) {
		this.addElementAtIndex(x, 0);		
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int indexOf(T key) {
		if(this.cmp == null) {
			throw new IllegalStateException("You didn't call the right constructor, jabroni");
		}
		for(int i=0; i<this.size; i++) {
			if(this.cmp.compare(key, this.arr[i]) == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public String toString() {
		if(this.size == 0) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<this.size-1; i++) {
			sb.append(this.arr[i]);
			sb.append(", " );
		}
		sb.append(this.arr[this.size-1]);
		return sb.toString();
	}
	
}
