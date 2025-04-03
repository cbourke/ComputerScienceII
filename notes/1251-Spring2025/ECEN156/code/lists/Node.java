package uno.ece.lists;

/**
 * TODO: documentation 
 * 
 * @param <T>
 */
public class Node<T> {

	private final T item;
	private Node<T> next;

	public Node(T item) {
		this.item = item;
		this.next = null;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public T getItem() {
		return item;
	}
	
	public boolean hasNext() {
		return (this.next != null);
	}
	
	public boolean isTail() {
		return (this.next == null);
	}

}
