package unl.soc.trees;

import java.util.Comparator;

public class BinarySearchTree<T> {

	private Comparator<T> cmp;
	BinaryTreeNode<T> root;

	public BinarySearchTree(Comparator<T> cmp) {
		this.cmp = cmp;
	}

	/**
	 * Inserts the given element <code>x</code> into this binary tree.
	 * Returns <code>true</code> if the insertion was successful (changed
	 * the tree), <code>false</code> if no operation occurred
	 * @param x
	 * @return
	 */
	public boolean add(T x) {
		
		if(this.root == null) {
			//empty tree
			BinaryTreeNode<T> newRoot = new BinaryTreeNode<>(x);
			this.root = newRoot;
			return true;
		}

		BinaryTreeNode<T> previous = null;
		BinaryTreeNode<T> current = this.root;
		while (current != null) {
			int result = this.cmp.compare(x, current.getElement());
			if (result == 0) {
				return false;
			} else if (result < 0) {
				// go left
				previous = current;
				current = current.getLeftChild();
			} else if (result > 0) {
				// go right
				previous = current;
				current = current.getRightChild();
			}
		}
		//insert at the current node...
		//1. create a new node:
		BinaryTreeNode<T> newLeaf = new BinaryTreeNode<>(x);
		newLeaf.setParent(previous);
		//newLeaf needs to be previous's left child or right child?
		if(this.cmp.compare(x, previous.getElement()) < 0) {
			//left
			previous.setLeftChild(newLeaf);
		} else {
			//right
			previous.setRightChild(newLeaf);
		}
		return true;
	}

	/**
	 * Returns <code>true</code> if this tree contains the given
	 * element <code>x</code>, <code>false</code> otherwise.
	 * 
	 * @param x
	 * @return
	 */
	public boolean contains(T x) {

		BinaryTreeNode<T> u = this.getNode(x);
		return (u != null);

	}
	
	private BinaryTreeNode<T> getNode(T x) {

		BinaryTreeNode<T> current = this.root;
		while (current != null) {
			int result = this.cmp.compare(x, current.getElement());
			if (result == 0) {
				return current;
			} else if (result < 0) {
				// go left
				current = current.getLeftChild();
			} else if (result > 0) {
				// go right
				current = current.getRightChild();
			}
		}
		return null;
	}
	
	/**
	 * Removes the given element from this BST if it exists.
	 * If no such element exists, it returns <code>false</code>.
	 * Returns <code>true</code> if it had an effect on the tree.
	 * 
	 * @param x
	 * @return
	 */
	public boolean remove(T x) {
		
		//1. find the node (if it exists) containing x
		BinaryTreeNode<T> u = this.getNode(x);
		if(u == null) {
			return false;
		}
		deleteNode(u);
		return true;
		
	}
	
	private void deleteNode(BinaryTreeNode<T> u) {
		
		if(u.isLeaf()) {
			if(u.isLeftChild()) {
				u.getParent().setLeftChild(null);
			} else if(u.isRightChild()) {
				u.getParent().setRightChild(null);
			}
		} else if(!u.hasLeftChild()) {
			//u must have a right child...
			//make u's parent's RIGHT OR LEFT child point to u's right child			
			if(u.isLeftChild()) {
				u.getParent().setLeftChild(u.getRightChild());				
			} else if(u.isRightChild()) {
				u.getParent().setRightChild(u.getRightChild());
			}
			u.getRightChild().setParent(u.getParent());
			
		} else if(!u.hasRightChild()) {
			//u must have a left child...
			//make u's parent's RIGHT OR LEFT child point to u's left child			
			if(u.isLeftChild()) {
				u.getParent().setLeftChild(u.getLeftChild());				
			} else if(u.isRightChild()) {
				u.getParent().setRightChild(u.getLeftChild());
			}
			u.getLeftChild().setParent(u.getParent());
		} else {
			//has both children...
			//1. Go to the left child...
			BinaryTreeNode<T> current = u.getLeftChild();
			//2. Keep going right to find the max until there is no right child...
			while(current.hasRightChild()) {
				current = current.getRightChild();
			}
			//current is the max
			//3. Replace u's element with the max's element
			u.setElement(current.getElement());
			//4. Delete the "current" (max) node...
			deleteNode(current);
		}
		
		
	}

}
