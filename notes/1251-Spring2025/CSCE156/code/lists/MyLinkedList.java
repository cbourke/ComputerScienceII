package unl.soc.lists;

/**
 * TODO: documentation
 * 
 * @param <T>
 */
public class MyLinkedList<T> extends MyAbstractList<T> {

	private Node<T> head;

	public MyLinkedList() {
		this.head = null;
		this.size = 0;
	}

	/**
	 * Inserts the given element <code>x</code> to this list at the given
	 * <code>index</code>.
	 * 
	 * @param x
	 * @param index
	 */
	public void insert(T x, int index) {
		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("Index = " + index + " is invalid for a list of size " + this.size);
		}

		Node<T> newNode = new Node<>(x);
		// insert it at the head...
		if (index == 0) {
			// 2. set this.head to the new node
			newNode.setNext(this.head);
			this.head = newNode;
		} else {
			// 1.5 retrieve i-1th node and i-th node
			Node<T> previous = getNodeAtIndex(index - 1);
			Node<T> current = previous.getNext();
			// 2. make the prev node point to new node
			previous.setNext(newNode);
			// 3. make the new node point to the current node
			newNode.setNext(current);
		}
		this.size++;

	}

	/**
	 * TODO; documentation private because otherwise it is a leaky abstraction
	 * 
	 * @param index
	 * @return
	 */
	private Node<T> getNodeAtIndex(int index) {

		Node<T> currentNode = this.head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	public T get(int index) {
		Node<T> node = this.getNodeAtIndex(index);
		return node.getData();
	}

	/**
	 * Removes (and returns) the element at the given index.
	 * 
	 * @param index
	 */
	public T delete(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index = " + index + " is invalid for a list of size " + this.size);
		}

		T result;
		if (index == 0) {
			result = this.head.getData();
			this.head = this.head.getNext();
		} else {
			Node<T> previous = this.getNodeAtIndex(index - 1);
			Node<T> current = previous.getNext();
			result = current.getData();
			// make previous point to current's next
			previous.setNext(current.getNext());
		}
		this.size--;
		return result;
	}
}
