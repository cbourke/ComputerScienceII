package uno.ece.lists;

import java.util.Arrays;

public abstract class MyAbstractList<T> {

	protected int size;

	public MyAbstractList() {
		this.size = 0;
	}

	/**
	 * Adds the given element <code>x</code> to the end of this list.
	 * 
	 * @param x
	 */
	public void add(T x) {
		this.add(x, this.size);
	}

	public int size() {
		return this.size;
	}

	public abstract void add(T x, int index);

	/**
	 * TODO
	 * 
	 * @param value
	 * @return
	 */
	public boolean contains(T value) {
		return (this.indexOf(value) != -1);
	}

	/**
	 * Returns the element at the given <code>index</code>.
	 * 
	 * @param index
	 * @return
	 */
	public abstract T retrieve(int index);

	/**
	 * Removes and returns the element at the given <code>index</code>
	 * 
	 * @param index
	 * @return
	 */
	public abstract T remove(int index);

	/**
	 * Replaces (and returns) the element at <code>index</code> with the given
	 * element <code>x</code>
	 * 
	 * @param x
	 * @param index
	 */
	public abstract T replace(T x, int index);

	/**
	 * Returns the *first* index at which the given value is stored. Returns -1 in
	 * the case that value is not in this list.
	 * 
	 * @param value
	 * @return
	 */
	public abstract int indexOf(T value);

	/**
	 * TODO: document
	 * 
	 * @param value
	 * @return
	 */
	public abstract int valueCount(T value);

}
