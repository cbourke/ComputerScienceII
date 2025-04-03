package uno.ece.lists;

import java.util.Arrays;

public class MyIntegerArrayList {

	private int arr[];
	private int size;
	// TODO: anything else?

	public MyIntegerArrayList() {
		this.arr = new int[10];
		this.size = 0;
	}

	/**
	 * Adds the given element <code>x</code> to the end of this list.
	 * 
	 * @param x
	 */
	public void add(int x) {
		this.add(x, this.size);
	}

	/**
	 * Adds the given element <code>x</code> to the list at the given
	 * <code>index</code>
	 * 
	 * @param x
	 */
	public void add(int x, int index) {
		if (index < 0 || index > this.size) {
			throw new RuntimeException("index = " + index + " is not valid for a list of size " + size);
		}
		if (this.size + 1 == this.arr.length) {
			increaseCapacity();
		}
		// shove stuff down to make room for x...
		for (int i = this.size - 1; i >= index; i--) {
			this.arr[i + 1] = this.arr[i];
		}
		this.arr[index] = x;
		this.size++;
	}

	/**
	 * Returns the element at the given <code>index</code>.
	 * 
	 * @param index
	 * @return
	 */
	public int retrieve(int index) {
		checkBounds(index);
		return this.arr[index];
	}

	/**
	 * Removes and returns the element at the given <code>index</code>
	 * 
	 * @param index
	 * @return
	 */
	public int remove(int index) {
		checkBounds(index);
		// TODO: consider shrinking hte array if the unused capacity is "too large"
		int result = this.arr[index];
		for (int i = index; i < this.size - 1; i++) {
			this.arr[i] = this.arr[i + 1];
		}
		this.size--;
		return result;
	}

	/**
	 * Replaces (and returns) the element at <code>index</code> with the given
	 * element <code>x</code>
	 * 
	 * @param x
	 * @param index
	 */
	public int replace(int x, int index) {
		checkBounds(index);
		// TODO: write tests for this
		int result = this.arr[index];
		this.arr[index] = x;
		return result;
	}

	/**
	 * Returns the *first* index at which the given value is stored. Returns -1 in
	 * the case that value is not in this list.
	 * 
	 * @param value
	 * @return
	 */
	public int indexOf(int value) {
		for (int i = 0; i < this.size; i++) {
			if (this.arr[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * TODO
	 * 
	 * @param value
	 * @return
	 */
	public boolean contains(int value) {
		return (this.indexOf(value) != -1);
	}

	/**
	 * TODO: document
	 * 
	 * @param value
	 * @return
	 */
	public int valueCount(int value) {
		int count = 0;
		for (int i = 0; i < this.size; i++) {
			if (this.arr[i] == value) {
				count++;
			}
		}
		return count;
	}

	private void increaseCapacity() {
		this.arr = Arrays.copyOf(this.arr, this.arr.length + 10);
	}

	private void checkBounds(int index) {
		if (index < 0 || index >= this.size) {
			throw new RuntimeException("index = " + index + " is not valid for a list of size " + size);
		}
	}

	/**
	 * Returns the current size of this list.
	 * 
	 * @return
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns <code>true</code> if the internal array has reached its capacity.
	 * 
	 * (private to make it not a leaky abstraction)
	 * 
	 * @return
	 */
	private boolean isFull() {
		return (this.arr.length == this.size);
	}

}
