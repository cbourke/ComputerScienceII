package unl.cse.lists.honors;

import java.util.Comparator;

public class MyLinkedList<T> {

	private Node<T> head;
	// TODO: extend this functionality to include a tail
	// private Node<T> tail;
	private int size;
	private Comparator<T> cmp;

	public MyLinkedList() {
		this(null);
	}
	
	public MyLinkedList(Comparator<T> cmp) {
		this.head = null;
		this.size = 0;
		this.cmp = cmp;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}
	
	private Node<T> getNodeAtIndex(int index) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("index " + index + " is out of bounds");
		}
		Node<T> current = this.head;
		for(int i=0; i<index; i++) {
			current = current.getNext();
		}
		return current;
	}

	public void addToEnd(T x) {
		this.addElementAtIndex(x, this.size);
	}

	public T getElementAtIndex(int index) {
		return this.getNodeAtIndex(index).getElement();
	}

	public void addElementAtIndex(T x, int index) {
		
		if(index < 0 || index > size) {
			throw new IllegalArgumentException("index " + index + " is out of bounds");
		}
		if(index == 0) {
			addToStart(x);
		} else {

			//get the node at index i...
			Node<T> previous = getNodeAtIndex(index-1);
			Node<T> current = previous.getNext();
			Node<T> newNode = new Node<>(x);
			newNode.setNext(current);
			previous.setNext(newNode);
			this.size++;
		}
	}

	public void addToStart(T x) {
		Node<T> newHead = new Node<T>(x);
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
	
	public int getSize() {		
		return this.size;
	}
	

	public int indexOf(T key) {
		if(this.cmp == null) {
			throw new IllegalStateException("You didn't call the right constructor, jabroni");
		}
		
		Node<T> current = this.head;
		int i = 0;
		while(current != null) {
			if(this.cmp.compare(key, current.getElement()) == 0) {
				return i;
			}
			current = current.getNext();
			i++;
		}

		return -1;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<T> current = this.head;
		while(current != null) {
			//append it:
			sb.append(current.getElement() + ", ");
			current = current.getNext();
		}
		return sb.toString();
	}

}
