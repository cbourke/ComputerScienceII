package unl.soc.trees;

public class BinaryTreeNode<T> {

	private T element;
	private BinaryTreeNode<T> parent;
	private BinaryTreeNode<T> leftChild;
	private BinaryTreeNode<T> rightChild;
	
	public BinaryTreeNode(T element) {
		super();
		this.element = element;
	}

	public BinaryTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<T> parent) {
		this.parent = parent;
	}

	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}
	
	public boolean hasLeftChild() {
		return this.leftChild != null;				
	}

	public boolean hasRightChild() {
		return this.rightChild != null;				
	}

	public boolean isLeftChild() {
		return (this.parent != null && this.parent.leftChild == this);
	}

	public boolean isRightChild() {
		return (this.parent != null && this.parent.rightChild == this);
	}

	public boolean isLeaf() {
		return (!this.hasLeftChild() && !this.hasRightChild());
	}

	public void setRightChild(BinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public T getElement() {
		return element;
	}
	
	public void setElement(T element) {
		this.element = element;
	}
	
	
}
