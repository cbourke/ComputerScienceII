package uno.ece.lists;

public class MyLinkedList<T> extends MyAbstractList<T> {

	private Node<T> head;

	public MyLinkedList() {
		this.head = null;
	}

	/**
	 * Adds the given element <code>x</code> to the list at the given
	 * <code>index</code>
	 * 
	 * @param x
	 */
	public void add(T x, int index) {
		if (index < 0 || index > this.size()) {
			throw new RuntimeException("index = " + index + " is not valid for a list of size " + this.size());
		}

		Node<T> newNode = new Node<>(x);

		// Edge case 1: insertion into an empty list/at the head
		if (index == 0) {
			newNode.setNext(this.head);
			this.head = newNode;
		} else {
			// find the node at index - 1
			Node<T> previous = getNodeAtIndex(index - 1);
			Node<T> current = previous.getNext();
			// previous -> current...
			// insert the new node between previous, current
			// create new node
			// make new node point to current
			newNode.setNext(current);
			// previous -> current AND newNode -> current
			// make previous point to new node
			previous.setNext(newNode);
			// previous -> newNode -> current
		}
		this.size++;
		return;
	}

	/**
	 * Returns the element at the given <code>index</code>.
	 * 
	 * @param index
	 * @return
	 */
	public T retrieve(int index) {
		if (index < 0 || index >= this.size) {
			throw new RuntimeException("index = " + index + " is not valid for a list of size " + size);
		}
		Node<T> node = getNodeAtIndex(index);
		return node.getItem();
	}

	private Node<T> getNodeAtIndex(int index) {
		Node<T> currentNode = this.head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	/**
	 * Removes and returns the element at the given <code>index</code>
	 * 
	 * @param index
	 * @return
	 */
	public T remove(int index) {
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("bad size");
		}
		if(index == 0) {
			//remove the head
			T item = this.head.getItem();
			this.head = this.head.getNext();
			this.size--;
			return item;
		} else {
			Node<T> previous = this.getNodeAtIndex(index-1);
			Node<T> current = previous.getNext();
			previous.setNext(current.getNext());
			this.size--;
			return current.getItem();
		}
	}

	/**
	 * Replaces (and returns) the element at <code>index</code> with the given
	 * element <code>x</code>
	 * 
	 * @param x
	 * @param index
	 */
	public T replace(T x, int index) {
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("bad size");
		}
		//TODO: what happens when index = 0
		if(index == 0) {
			T item = this.head.getItem();
			Node<T> replacement = new Node<>(x);
			replacement.setNext(this.head.getNext());
			this.head = replacement;
			return item;
		} else {
			//1. get the previous to index:
			Node<T> previous = this.getNodeAtIndex(index-1);
			Node<T> current = previous.getNext();
			Node<T> replacement = new Node<>(x);
			previous.setNext(replacement);
			replacement.setNext(current.getNext());
			return current.getItem();
		}
	}

	/**
	 * Returns the *first* index at which the given value is stored. 
	 * Returns -1 in the case that value is not in this list.
	 * 
	 * @param value
	 * @return
	 */
	public int indexOf(T value) {
		Node<T> currentNode = this.head;
		int index = 0;
		while (currentNode != null) {
			// 1. check its item against value
			if (currentNode.getItem().equals(value)) {
				return index;
			}
			currentNode = currentNode.getNext();
			index++;
		}
		return -1;
	}

	/**
	 * Returns the number of instances of the given value in the list.
	 * 
	 * @param value
	 * @return
	 */
	public int valueCount(T value) {
		int count = 0;
		Node<T> currentNode = this.head;
		while (currentNode != null) {
			// 1. check its item against value
			if (currentNode.getItem().equals(value)) {
				count++;
			}
			// 2. move to the next node...
			currentNode = currentNode.getNext();
		}
		return count;
	}
}
