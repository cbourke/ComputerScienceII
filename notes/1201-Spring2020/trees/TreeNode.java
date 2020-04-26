package unl.cse.bst;

public class TreeNode<T> {

    private final T value;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;
    private TreeNode<T> parent;

    public TreeNode(T value) {
        this(value, null);
    }

    public TreeNode(T value, TreeNode<T> parent) {
        this.value = value;
        this.parent = parent;
        this.rightChild = null;
        this.leftChild = null;
    }

    public TreeNode<T> setParent(TreeNode<T> parent) {
        this.parent = parent;
        return this;
    }

    public TreeNode<T> getParent() {
        return this.parent;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public TreeNode<T> setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
        return this;
    }

    public boolean hasLeftChild() {
        return this.leftChild != null;
    }
 
    public TreeNode<T> getLeftChild() {
        return this.leftChild;
    }

    public TreeNode<T> setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
        return this;
    }
    
    public boolean hasRightChild() {
        return this.rightChild != null;
    }

    public TreeNode<T> getRightChild() {
        return this.rightChild;
    }
    
    public boolean isLeaf() {
    	return (!this.hasLeftChild() && !this.hasRightChild());
    }

    public T getValue() {
        return this.value;
    }
    
    public String toString() {
    	return "(" + this.value + ")";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leftChild == null) ? 0 : leftChild.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((rightChild == null) ? 0 : rightChild.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		TreeNode other = (TreeNode) obj;
		
		if (leftChild == null) {
			if (other.leftChild != null)
				return false;
		} else if (!leftChild.value.equals(other.leftChild.value))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.value.equals(other.parent.value))
			return false;
		if (rightChild == null) {
			if (other.rightChild != null)
				return false;
		} else if (!rightChild.value.equals(other.rightChild.value))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
    
    

}
