package unl.soc.lists;

public abstract class MyAbstractList<T> {

	protected int size;

	/**
	 * Adds the given element <code>x</code> to the end of this list.
	 * 
	 * @param x
	 */
	public void add(T x) {
		this.insert(x, this.size);
	}

	public int size() {
		return this.size;
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
	 * TODO: write documentation
	 * 
	 * @param index
	 * @return
	 */
	public abstract T get(int index);

	/**
	 * Removes (and returns) the element at the given index.
	 * 
	 * @param index
	 */
	public abstract T delete(int index);

}
