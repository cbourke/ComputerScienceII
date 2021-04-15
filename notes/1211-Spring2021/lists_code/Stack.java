package unl.cse.lists;

public class Stack<T> {
	
	private MyLinkedList<T> underlyingList = new MyLinkedList<>();
	//alternative: just be careful! private MyList<T> underlyingList = new MyList<>();
	private int maxCapacity = 100;
	
	public void push(T item) {
		if(item == null) {
			//don't allow it?
		} else if(this.underlyingList.getSize() == maxCapacity) {
			//deal with it
		}
		this.underlyingList.addToStart(item);
	}
	
	public boolean isEmpty() {
		return this.underlyingList.getSize() == 0;
	}
	
	public T pop() {
		if(this.isEmpty()) {
			//throw new IllegalStateException("stack is empty");
			return null;
		}
		return this.underlyingList.removeFromStart();
	}
	
	public T peek() {
		return this.underlyingList.getElementAtIndex(0);
	}
	
	

}
