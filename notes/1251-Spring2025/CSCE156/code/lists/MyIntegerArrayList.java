package unl.soc.lists;

import java.util.Arrays;

public class MyIntegerArrayList {

	private int[] arr;
	private int size;

	public MyIntegerArrayList() {
		this.arr = new int[10];
		this.size = 0;
	}

	public MyIntegerArrayList(MyIntegerArrayList that) {
		// copy that into this...
		this.arr = Arrays.copyOf(that.arr, that.arr.length);
		this.size = that.size;
	}

	/**
	 * Adds the given element <code>x</code> to the end of this list.
	 * 
	 * @param x
	 */
	public void add(int x) {
		this.insert(x, this.size);
	}

	/**
	 * Inserts the given element <code>x</code> to this list at the given
	 * <code>index</code>.
	 * 
	 * @param x
	 * @param index
	 */
	public void insert(int x, int index) {
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
	public int get(int index) {

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
	public int delete(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index = " + index + " is invalid for a list of size " + this.size);
		}
		// TODO: consider "shrinking" this array if the unused capacity
		// exceeds a certain number
		int result = this.arr[index];
		for (int i = index; i < this.size - 1; i++) {
			this.arr[i] = this.arr[i + 1];
		}
		this.size--;
		return result;
	}

	private boolean isFull() {
		return (arr.length == this.size);
	}

	public int size() {
		return this.size;
	}

}
