package uno.ece.lists;

import java.util.Arrays;

public interface MyList<T> {

	public void addElement(T x);
	
	public void removeLastElement();

	public T getElementAtIndex(int index);
	
	public int getSize();
	
	public boolean isEmpty();
	
	public T replaceElementAtIndex(T x, int index);
	
	public T removeElementAtIndex(int index);

	public void insertElementAtIndex(T x, int index);
}
