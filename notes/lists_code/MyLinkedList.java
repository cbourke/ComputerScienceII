package unl.cse.lists;

import java.util.Comparator;

public class MyLinkedList<T> {

	private Node<T> head;
	// optional: private Node<T> tail;
	private int size;
	private final Comparator<T> cmp;

	public MyLinkedList() {
		this.head = null;
		this.size = 0;
		this.cmp = null;
	}
	public MyLinkedList(Comparator<T> cmp) {
		this.head = null;
		this.size = 0;
		this.cmp = cmp;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	public int getSize() {
		return this.size;
	}

	public T getElementAtIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("index " + index + " is out of bounds");
		}
		Node<T> current = this.getNodeAtIndex(index);
		return current.getElement();
	}

	private Node<T> getNodeAtIndex(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}

	public void addElementAtIndex(T x, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("index " + index + " is out of bounds");
		}
		if (index == 0) {
			this.addToStart(x);
		} else {
			Node<T> previous = this.getNodeAtIndex(index - 1);
			Node<T> current = previous.getNext();
			Node<T> newNode = new Node<T>(x);
			newNode.setNext(current);
			previous.setNext(newNode);
			this.size++;
			return;
		}
	}
	
	public void addToEnd(T x) {
		this.addElementAtIndex(x, this.size);
	}

	public void addToStart(T x) {
		Node<T> newHead = new Node(x);
		newHead.setNext(this.head);
		this.head = newHead;
		this.size++;
	}

	public T removeFromStart() {
		T x = this.head.getElement();
		this.head = this.head.getNext();		
		this.size--;
		return x;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.isEmpty()) {
			sb.append("[empty]");
		} else {
			Node<T> current = head;
			while (current != null) {
				sb.append(current.getElement() + ", ");
				current = current.getNext();
			}
		}
		return sb.toString();
	}

	public int indexOf(T key) {
		if(this.cmp == null) {
			throw new IllegalStateException("You didn't call the right constructor, jabroni");
		}
		Node<T> current = this.head;
		for(int i=0; i<this.size; i++) {
			if(this.cmp.compare(key, current.getElement()) == 0) {
				return i;
			}
			current = current.getNext();
		}
		return -1;
	}
}
