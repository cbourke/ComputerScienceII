package unl.cse.lists.honors;

public class Stack<T> {
	
	private MyLinkedList<T> underlyingList = new MyLinkedList<T>(); 
	//TODO: consider implementing a maximum capacity
	private int maxCapacity = 100;
	
	public void push(T item) {
		if(this.underlyingList.getSize() == this.maxCapacity) {
			return; //noop
		}
		this.underlyingList.addToStart(item);
	}
	
	public T pop() {
		if(this.underlyingList.isEmpty()) {
			//return null;
			throw new IllegalStateException("You cannot pop an empty stack!!");
		}
		return this.underlyingList.removeFromStart();
	}
	
	public T peek() {
		return this.underlyingList.getElementAtIndex(0);
	}

}
