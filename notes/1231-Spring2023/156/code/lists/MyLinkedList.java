package unl.soc.lists;

public class MyLinkedList<T> implements MyList<T> {

	private int size;
	private Node<T> head;
	private Node<T> tail;

	public MyLinkedList() {
		this.size = 0;
		this.head = null;
	}

	private Node<T> getNodeAtIndex(int index) {

		Node<T> current = this.head;
		for (int i = 0; i < index; i++) {
			// traverse to the next node
			current = current.getNext();
		}
		return current;
	}

	@Override
	public void addElement(T x) {
		// adds the given element x to the end of the list...
		// 1. create a new node...
		Node<T> newTail = new Node<T>(x);

		// 2. if the list is empty, make head point to this new tail...
		if (this.size == 0) {
			this.head = newTail;
			this.size++;
			return;
		}

		// find the last node...
		Node<T> tail = this.getNodeAtIndex(this.size - 1);
		// make the last node point to the new node
		tail.setNext(newTail);
		this.size++;
		return;
	}

	@Override
	public void addElementToStart(T x) {
		Node<T> newHead = new Node<T>(x);
		newHead.setNext(this.head);
		this.head = newHead;
		this.size++;
	}

	@Override
	public T replaceElementAtIndex(T x, int index) {

		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else if (index == 0) {
			// want to replace the head...
			T y = head.getElement();
			Node<T> replacement = new Node<>(x);
			replacement.setNext(this.head.getNext());
			this.head = replacement;
			return y;
		} else {
			Node<T> previous = this.getNodeAtIndex(index - 1);
			Node<T> current = previous.getNext();
			Node<T> replacement = new Node<>(x);
			previous.setNext(replacement);
			replacement.setNext(current.getNext());
			return current.getElement();
		}
	}

	@Override
	public T removeElementAtIndex(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else if (index == 0) {
			// want to replace the head...
			T y = head.getElement();
			this.head = this.head.getNext();
			return y;
		} else {
			Node<T> previous = this.getNodeAtIndex(index - 1);
			Node<T> current = previous.getNext();
			Node<T> next = current.getNext();
			previous.setNext(next);
			return current.getElement();
		}
	}

	@Override
	public void addElementAtIndex(T x, int index) {

		if (index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else if (index == 0) {
			this.addElementToStart(x);
		} else {
			Node<T> previous = this.getNodeAtIndex(index - 1);
			Node<T> next = previous.getNext();
			Node<T> newNode = new Node<>(x);
			previous.setNext(newNode);
			newNode.setNext(next);
		}
	}

	@Override
	public T getElementAtIndex(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else {
			return this.getNodeAtIndex(index).getElement();
		}
	}

	@Override
	public int getSize() {
		return this.size;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");

		Node<T> current = this.head;
		while (current != null) { // while not at the end of the list...
			// concatenate the current node
			sb.append(current.getElement() + ", ");
			// traverse to the next node
			current = current.getNext();
		}
		sb.append(" ]");
		return sb.toString();
	}

}
