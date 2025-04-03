package unl.soc.lists;

public abstract class MyAbstractList<T> {

	protected int size;

	/**
	 * Adds the given element <code>x</code> to the end of the list.
	 * 
	 * @param x
	 */
	public void add(T x) {
		this.insert(x, this.size);
	}

	/**
	 * Inserts the given element <code>x</code> to this list at the given
	 * <code>index</code>.
	 * 
	 * @param x
	 * @param index
	 */
	public abstract void insert(T x, int index);

	/**
	 * Returns the element at the given <code>index</code>.
	 * 
	 */
	public abstract T get(int index);

	/**
	 * Removes and returns the element at the given <code>index</code>.
	 * 
	 * @param index
	 */
	public abstract T remove(int index);

	/**
	 * Returns the current size of the list.
	 * 
	 * @return
	 */
	public int size() {
		return this.size;
	}

}
