package unl.soc.lists;

/**
 * TODO: Documentation
 * 
 * @param <T>
 */
public class Node<T> {

	private T item;
	private Node<T> next;

	public Node(T item) {
		super();
		this.item = item;
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

	public String toString() {
		return "(" + this.item + ")";
	}

	public boolean hasNext() {
		return (this.next != null);
	}

	public boolean isTail() {
		return (this.next == null);
	}

}
