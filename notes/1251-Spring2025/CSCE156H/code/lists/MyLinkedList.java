package unl.soc.lists;

public class MyLinkedList<T> extends MyAbstractList<T> {

	private Node<T> head;

	/**
	 * Constructs an initially empty linked list
	 */
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
			throw new IllegalArgumentException("index " + index + " doesn't make senses for list of size " + this.size);
		}

		// create a new node:
		Node<T> u = new Node<>(x);
		// insert the new element...
		// case 1: insert into an empty list...
		if (index == 0) {
			// make u point to (old) head
			u.setNext(this.head);
			// make the head point to the new node..
			this.head = u;
		} else {
			// 0a. get the previous = (i-1)th node
			Node<T> previous = this.getNodeAtIndex(index - 1);
			// 0b. get the current = (i)th node
			Node<T> current = previous.getNext();
			// 1. make u point to current
			u.setNext(current);
			// 2. make previous point to u
			previous.setNext(u);
		}

		this.size++;

	}

	/**
	 * Returns the node at the given index.
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

	/**
	 * Returns the element at the given <code>index</code>.
	 * 
	 */
	public T get(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("index " + index + " doesn't make senses for list of size " + this.size);
		}

		Node<T> u = this.getNodeAtIndex(index);
		return u.getItem();
	}

	/**
	 * Removes and returns the element at the given <code>index</code>.
	 * 
	 * @param index
	 */
	public T remove(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("index " + index + " doesn't make senses for list of size " + this.size);
		}

		Node<T> u = this.getNodeAtIndex(index);
		T item = u.getItem();

		// TODO: remove the node...
		// Case 1: remove the head
		if (index == 0) {
			this.head = this.head.getNext();
		} else {
			// case 2: removing some internal node...
			Node<T> previous = this.getNodeAtIndex(index - 1);
			Node<T> current = previous.getNext();
			Node<T> next = current.getNext();
			// make previous's next point to next
			previous.setNext(next);
		}

		// TODO: what about the tail? TEST IT

		this.size--;

		return item;
	}
}
