package unl.soc.lists;

import java.util.Arrays;

public class MyArrayList<T> extends MyAbstractList<T> {

	private T[] arr;

	public MyArrayList() {
		this.arr = (T[]) new Object[10];
		this.size = 0;
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
			throw new IllegalArgumentException("index " + index + " doesn't make senses for list of size " + this.size);
		}
		if (this.size + 1 == this.arr.length) {
			this.arr = Arrays.copyOf(this.arr, this.arr.length + 10);
		}
		for (int i = this.size; i >= index; i--) {
			this.arr[i + 1] = this.arr[i];
		}
		this.arr[index] = x;
		this.size++;

	}

	/**
	 * Returns the element at the given <code>index</code>.
	 * 
	 */
	public T get(int index) {
		// TODO: implement
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("index " + index + " doesn't make senses for list of size " + this.size);
		}

		return this.arr[index];
	}

	/**
	 * Removes and returns the element at the given <code>index</code>.
	 * 
	 * @param index
	 */
	public T remove(int index) {

		// 0. test bounds
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("index " + index + " doesn't make senses for list of size " + this.size);
		}
		// 1. save off the index-th element to return it
		T result = this.arr[index];
		// 2. remove it:
		// [10, 20, 30, 40, 50], remove 20: [10, 30, 40, 50]
		// by shifting everything down starting at index
		for (int i = index; i < this.size - 1; i++) {
			this.arr[i] = this.arr[i + 1];
		}
		this.size--;

		return result;
	}

	private boolean isFull() {
		return (this.arr.length == this.size);
	}

	/**
	 * Returns a new (deep copy) sublist of this list from index i (inclusive) to
	 * index j (exclusive)
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public MyArrayList subList(int i, int j) {

		// TODO: error handling
		if (j < i || i < 0 || j > this.size) {
			throw new IllegalArgumentException("index(es) doesn't make senses for list of size " + this.size);
		}

		MyArrayList result = new MyArrayList();
		result.arr = (T[]) new Object[j - i];
		for (int k = i; k < j; k++) {
			result.arr[k - i] = this.arr[k];
		}
		result.size = j - i;
		return result;

	}

	public String toString() {
		if (this.size == 0) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < this.size - 1; i++) {
			sb.append(this.arr[i] + ", ");
		}
		sb.append(this.arr[this.size - 1] + "]");
		return sb.toString();
	}

}
