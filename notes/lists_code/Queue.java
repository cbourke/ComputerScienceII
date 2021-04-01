package unl.cse.lists;

public class Queue<T> {

	private MyLinkedList<T> underlyingList = new MyLinkedList<>();
	
	public void enqueue(T item) {
		this.underlyingList.addToEnd(item);
	}
	
	public T dequeue() {
		return this.underlyingList.removeFromStart();
	}
}
