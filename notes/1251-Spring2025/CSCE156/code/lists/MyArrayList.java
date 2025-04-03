package unl.soc.lists;

import java.util.Arrays;

public class MyArrayList<T> extends MyAbstractList<T> {

	private T[] arr;

	public MyArrayList() {
		this.arr = (T[]) new Object[10];
		this.size = 0;
	}

	public MyArrayList(MyArrayList<T> that) {
		// copy that into this...
		this.arr = Arrays.copyOf(that.arr, that.arr.length);
		this.size = that.size;
	}

	/**
	 * Inserts the given element <code>x</code> to this list at the given
	 * <code>index</code>.
	 * 
	 * @param x
	 * @param index
	 */
	public void insert(T x, int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("Index = " + index + " is invalid for a list of size " + this.size);
		}
		// 1. if it is already full, then increase capacity
		if (this.isFull()) {
			this.arr = Arrays.copyOf(this.arr, this.arr.length + 10);
		}

		// [10, 20, 30], insert 42 at index 0 -> [42, 10, 20, 30]
		// 2. make room for it by shoving everything down by one index...
		for (int i = this.size - 1; i >= index; i--) {
			this.arr[i + 1] = this.arr[i];
		}

		this.arr[index] = x;
		this.size++;

	}

	/**
	 * Returns the value stored at the given <code>index</code>.
	 * 
	 * @param index
	 * @return
	 */
	public T get(int index) {

		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Index = " + index + " is invalid for a list of size " + this.size);
		}

		return this.arr[index];
	}

	/**
	 * Removes (and returns) the element at the given index.
	 * 
	 * @param index
	 */
	public T delete(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index = " + index + " is invalid for a list of size " + this.size);
		}
		// TODO: consider "shrinking" this array if the unused capacity
		// exceeds a certain number
		T result = this.arr[index];
		for (int i = index; i < this.size - 1; i++) {
			this.arr[i] = this.arr[i + 1];
		}
		this.size--;
		return result;
	}

	private boolean isFull() {
		return (arr.length == this.size);
	}


}
