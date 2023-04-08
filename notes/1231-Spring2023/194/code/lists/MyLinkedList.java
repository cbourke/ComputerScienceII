package uno.ece.lists;

public class MyLinkedList<T> implements MyList<T> {
	
	private int size;
	private Node<T> head;
	
	public MyLinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * Returns the node at the given index
	 * @param i
	 * @return
	 */
	private Node<T> getNode(int index) {
		
		Node<T> current = this.head;
		for(int i=0; i<index; i++) {
			//traverse:
			current = current.getNext();
		}
		return current;
	}

	@Override
	public void addElement(T x) {
		
		//create a new node containing x:
		Node<T> newNode = new Node<T>(x);

		if(this.size == 0) {
			this.head = newNode;
			this.size++;
			return;			
		}

		//find the last node...
		Node<T> last = this.getNode(this.size-1);
		//make the last node point to this newNode:
		last.setNext(newNode);
		this.size++;		
	}

	@Override
	public void removeLastElement() {
		this.removeElementAtIndex(this.size-1);		
	}

	@Override
	public T getElementAtIndex(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		Node<T> indexedNode = this.getNode(index);
		return indexedNode.getElement();
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public T replaceElementAtIndex(T x, int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		
		Node<T> newNode = new Node(x);
				
		Node<T> previous = this.getNode(index-1);
		Node<T> current = previous.getNext();
		Node<T> next = current.getNext();

		//previous needs to point to newNode
		previous.setNext(newNode);
		//newNode needs to point to next
		newNode.setNext(next);
		return current.getElement();
	}

	@Override
	public T removeElementAtIndex(int index) {
		
		if(index < 0 || index >= this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else if(index == 0) {
			//remove the head
			T x = this.head.getElement();
			this.head = this.head.getNext();
			this.size--;
			return x;
		} else {
			Node<T> previous = this.getNode(index-1);
			Node<T> current  = previous.getNext();
			Node<T> next     = current.getNext();
			previous.setNext(next);
			this.size--;
			return current.getElement();
		}		
	}

	@Override
	public void insertElementAtIndex(T x, int index) {
		
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index: " + index);
		} else if(index == 0) {
			//add a new head node...
			Node<T> newHeadNode = new Node<>(x);
			newHeadNode.setNext(this.head);
			this.head = newHeadNode;
			this.size++;
		} else {
			//add somewhere in the middle...
			Node<T> newNode = new Node<>(x);
			Node<T> previous = this.getNode(index-1);
			Node<T> current = previous.getNext();
			//make previous point to the new node:
			previous.setNext(newNode);
			//make newNode point to the current
			newNode.setNext(current);
			this.size++;
		}
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.isEmpty()) {
			return "[empty]";
		}
		sb.append("[ ");
		Node<T> current = this.head;
		//you are not at the end of the list
		while(current.hasNext()) {
			//append the current node's element
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
