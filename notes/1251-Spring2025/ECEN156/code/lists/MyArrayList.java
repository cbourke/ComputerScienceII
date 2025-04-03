package uno.ece.lists;

import java.util.Arrays;

public class MyArrayList<T> extends MyAbstractList<T> {

	private T arr[];

	public MyArrayList() {
		this.arr = (T[]) new Object[10];
	}

	/**
	 * Adds the given element <code>x</code> to the list at the given
	 * <code>index</code>
	 * 
	 * @param x
	 */
	public void add(T x, int index) {
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
	public T retrieve(int index) {
		checkBounds(index);
		return this.arr[index];
	}

	/**
	 * Removes and returns the element at the given <code>index</code>
	 * 
	 * @param index
	 * @return
	 */
	public T remove(int index) {
		checkBounds(index);
		// TODO: consider shrinking hte array if the unused capacity is "too large"
		T result = this.arr[index];
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
	public T replace(T x, int index) {
		checkBounds(index);
		// TODO: write tests for this
		T result = this.arr[index];
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
	public int indexOf(T value) {
		for (int i = 0; i < this.size; i++) {
			// == will compare objects' memory addresses
			// also: TODO: consider integrating a Comparator into this class
			if (this.arr[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * TODO: document
	 * 
	 * @param value
	 * @return
	 */
	public int valueCount(T value) {
		int count = 0;
		for (int i = 0; i < this.size; i++) {
			// TODO same thing here.
			if (this.arr[i].equals(value)) {
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
