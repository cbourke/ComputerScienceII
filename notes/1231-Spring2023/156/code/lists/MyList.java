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
	
	public void addElementToStart(T x);
	
	/**
	 * Replaces the element at the given <code>index</code>
	 * with <code>x</code>
	 * 
	 * @param x
	 * @param index
	 */
	public T replaceElementAtIndex(T x, int index);
	
	public T removeElementAtIndex(int index);
	
	public void addElementAtIndex(T x, int index);
	
	/**
	 * Returns the element stored at the given <code>index</code>.
	 * 
	 * @param index
	 * @return
	 */
	public T getElementAtIndex(int index);
	
	public int getSize();
	
}
