package unl.soc.lists;

import java.util.Arrays;

public interface MyList<T> {

	/**
	 * Adds the given element <code>x</code> to the end
	 * of this list.
	 * 
	 * @param x
	 */
	public void addElement(T x);
	
	/**
	 * Adds the given element <code>x</code> at the given
	 * <code>index</code>, automatically shifting stuff down.
	 * 
	 * @param x
	 * @param index
	 */
	public void addElementAtIndex(T x, int index);
	
	public T getElement(int index);
	
	public int getSize();
	
	/**
	 * Replaces the element at the given <code>index</code>
	 * with the element <code>x</code>.
	 * 
	 * @param x
	 * @param index
	 */
	public T replaceElement(T x, int index);
	
	public T removeElement(int index);
}
