package uno.ece.trees;

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
	
	public boolean hasRightChild() {
		return (this.rightChild != null);
	}

	public boolean hasLeftChild() {
		return (this.leftChild != null);
	}

	public void setRightChild(BinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public boolean isLeaf() {
		return (this.leftChild == null && this.rightChild == null);
	}

	public boolean isRoot() {
		return (this.parent == null);
	}
	
	public boolean isLeftChild() {
		return (parent != null && parent.getLeftChild() == this);
	}

	public boolean isRightChild() {
		return (parent != null && parent.getRightChild() == this);
	}

}
