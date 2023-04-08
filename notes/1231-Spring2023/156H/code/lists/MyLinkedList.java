package unl.soc.lists;

public class MyLinkedList<T> implements MyList<T>  {
	
	private int size;
	private Node<T> head;
	
	public MyLinkedList() {
		this.size = 0;
		this.head = null;
	}
	
	public Node<T> getNodeAtIndex(int index) {
		Node<T> current = this.head;

		for(int i=0; i<index; i++) {
			current = current.getNext();
		}
		
		return current;
	}

	@Override
	public void addElement(T x) {
		
		//create new node
		Node<T> newTail = new Node<>(x);

		if(this.head == null) {
			this.head = newTail;
			this.size++;
			return;
		}

		//traverse to *just* before the end of the list
		// get the last node
		Node<T> current = this.head;
		while( current.hasNext() ) {
			current = current.getNext();
		}
		current.setNext(newTail);		
		this.size++;
	}

	@Override
	public void addElementAtIndex(T x, int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else if(index == 0) {
			//insert at the head:
			Node<T> newNode = new Node<>(x);
			newNode.setNext(this.head);
			this.head = newNode;		
			this.size++;
		} else {
			Node<T> previous = this.getNodeAtIndex(index-1);
			Node<T> current = previous.getNext();
			Node<T> newNode = new Node<>(x);
			//make the previous point to the new node:
			previous.setNext(newNode);
			//make the newNode point to current:
			newNode.setNext(current);
			this.size++;
		}
	}

	@Override
	public T getElement(int index) {
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		return this.getNodeAtIndex(index).getElement();
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public T replaceElement(T x, int index) {
		
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else if(index == 0) {
			Node<T> oldHead = this.head;
			Node<T> replacement = new Node<>(x);
			replacement.setNext(oldHead.getNext());
			this.head = replacement;
			return oldHead.getElement();
			
		} else {

			Node<T> previous = this.getNodeAtIndex(index-1);
			Node<T> toReplace = previous.getNext();
			Node<T> next = toReplace.getNext();
			Node<T> replacement = new Node<>(x);
			
			previous.setNext(replacement);
			replacement.setNext(next);
			
			return toReplace.getElement();
		}
	}

	@Override
	public T removeElement(int index) {
		
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else if(index == 0) {
			//remove the head
			T x = this.head.getElement();
			this.head = this.head.getNext();
			this.size--;
			return x;
		} else {
			Node<T> previous = this.getNodeAtIndex(index-1);
			Node<T> current = previous.getNext();
			previous.setNext(current.getNext());
			this.size--;			
			return current.getElement();
		}
	}
	
	public String toString() {

		if(this.head == null) {
			return "[empty]";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Node<T> current = this.head;
		while( current.hasNext() ) { 
			sb.append(current.getElement());
			sb.append(", ");
			//traverse to the next node
			current = current.getNext();
		}
		sb.append(current.getElement());
		sb.append(" ]");
		return sb.toString();
	}
	
	

}
