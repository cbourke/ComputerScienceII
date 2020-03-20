package unl.cse.lists;

import java.util.LinkedList;
import java.util.List;

public class MyLinkedList<T> {

	private Node<T> head;
	private int size;
	
	public MyLinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	public T getElementAtIndex(int index) {
		return getNodeAtIndex(index).getItem();
	}
	
	private Node<T> getNodeAtIndex(int index) {
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("index out of bounds!");
		}
		
		//find the node at the given index...
		Node<T> curr = this.head;
		for(int i=0; i<index; i++) {
			curr = curr.getNext();
		}
		return curr;
	}
	
	public T removeFromIndex(int index) {
		if(index == 0) {
			T item = this.head.getItem();
			this.removeFromHead();
			return item;
		}
		Node<T> prev = getNodeAtIndex(index-1);
		Node<T> curr = prev.getNext();
		
		//make prev point to curr's next node:
		prev.setNext(curr.getNext());
		this.size--;
		return curr.getItem();
	}
	
	public void insertAtIndex(T item, int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("index out of bounds!");
		}
		if(index == 0) {
			this.insertAtHead(item);
		} else if(index == this.size) {
			this.insertAtTail(item);
		} else {
			Node<T> newNode = new Node(item);
			Node<T> prevNode = this.getNodeAtIndex(index-1);
			Node<T> currNode = prevNode.getNext();
			newNode.setNext(currNode);
			prevNode.setNext(newNode);
			this.size++;
		}
		

	}
	
	public void insertAtHead(T item) {
		Node<T> newHead = new Node<>(item);
		newHead.setNext(this.head);
		this.head = newHead;
		this.size++;
	}
	
//	public T replaceElementAtIndex(T item, int index) {
//		//TODO: implement this
//	}

	public void insertAtTail(T item) {
		if(this.isEmpty()) {
			insertAtHead(item);
			return;
		}
		Node<T> curr = this.head;
		while(curr.getNext() != null) {
			curr = curr.getNext();
		}
		Node<T> newTail = new Node<>(item);
		curr.setNext(newTail);
		this.size++;
	}

	public T removeFromHead() {
		if(this.isEmpty()) {
			throw new IllegalStateException("You cannot remove from an empty list");
		}
		T item = this.head.getItem();
		this.head = this.head.getNext();
		this.size--;		
		return item;
	}

	public int getSize() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return (this.size == 0);
	}
	
	public String toString() {
		if(this.isEmpty()) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();		
		Node<T> curr = this.head;
		while(curr != null) {
			sb.append(curr.getItem() + ", ");
			curr = curr.getNext();
		}
		return sb.toString();
	}
	
	public static void main(String args[]) {
		MyLinkedList<Integer> list = new MyLinkedList<>();

		list.insertAtTail(142);
		System.out.println(list);
		System.out.println("size = " + list.getSize());

		
		for(int i=10; i>=1; i--) {
			list.insertAtHead(i*10);
		}
		System.out.println(list);
		System.out.println("size = " + list.getSize());

		list.insertAtTail(42);
		System.out.println(list);
		System.out.println("size = " + list.getSize());
		
		int x = list.getElementAtIndex(11);
		System.out.println(x);
		
		list.insertAtIndex(99, 2);
		System.out.println(list);
		System.out.println("size = " + list.getSize());
		
		list.removeFromIndex(12);
		System.out.println(list);
		System.out.println("size = " + list.getSize());
//		int n = list.getSize();
//		for(int i=0; i<n; i++) {
//			int x = list.removeFromHead();
//			System.out.println(x);
//		}
//		System.out.println(list);
		
		List<String> names = new LinkedList<>();

	}
}
