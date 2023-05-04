package uno.ece.trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<T> {

	private Comparator<T> cmp;
	private int size;
	// package protected so that we can demo in TreeUtils
	BinaryTreeNode<T> root;

	public BinarySearchTree(Comparator<T> cmp) {
		this.cmp = cmp;
		this.size = 0;
		this.root = null;
	}

	public List<T> inOrder() {
		List<T> result = new ArrayList<>();
		inOrder(this.root, result);
		return result;
	}

	private void inOrder(BinaryTreeNode<T> current, List<T> result) {

		// base case:
		// if the current node is invalid, don't do anything!
		if (current == null) {
			return;
		}

		// if the left child exists, recurse on it...
		inOrder(current.getLeftChild(), result);
		// process the current node
		result.add(current.getElement());
		// if the right child exists, recurse on it...
		inOrder(current.getRightChild(), result);
	}

	public boolean insert(T x) {

		if (this.size == 0) {
			this.root = new BinaryTreeNode<T>(x);
			this.size++;
			return true;
		}

		BinaryTreeNode<T> previous = null;
		BinaryTreeNode<T> current = this.root;
		// 2. while you are on a current node:
		// compare the current node to x:
		// a. it matches: stop return true
		while (current != null) {
			if (this.cmp.compare(x, current.getElement()) == 0) {
				// do not allow duplicates
				// throw new IllegalStateException("Dups are not allows!");
				// NOOP = No Operation
				return false;
			} else if (this.cmp.compare(x, current.getElement()) < 0) {
				// b. x < current: traverse LEFT
				previous = current;
				current = current.getLeftChild();
			} else if (this.cmp.compare(current.getElement(), x) < 0) {
				// c. current < x: traverse RIGHT
				previous = current;
				current = current.getRightChild();
			}
		}
		// place the new element as a leaf of the previous:
		BinaryTreeNode<T> newLeaf = new BinaryTreeNode<T>(x);
		// TODO: we should set the parent too!
		newLeaf.setParent(previous);
		// which one?
		if (this.cmp.compare(x, previous.getElement()) < 0) {
			// insert as LEFT
			previous.setLeftChild(newLeaf);
		} else if (this.cmp.compare(previous.getElement(), x) < 0) {
			// insert as RIGHT
			previous.setRightChild(newLeaf);
		}

		// true indicates it was successfully inserted
		this.size++;
		return true;

	}

	public boolean contains(T x) {

		BinaryTreeNode<T> u = this.getNode(x);
		return (u != null);

	}

	private BinaryTreeNode<T> getNode(T x) {

		BinaryTreeNode<T> current = this.root;
		while (current != null) {
			if (this.cmp.compare(x, current.getElement()) == 0) {
				return current;
			} else if (this.cmp.compare(x, current.getElement()) < 0) {
				// b. x < current: traverse LEFT
				current = current.getLeftChild();
			} else if (this.cmp.compare(current.getElement(), x) < 0) {
				// c. current < x: traverse RIGHT
				current = current.getRightChild();
			}
		}
		return null;

	}
	
	private void deleteNode(BinaryTreeNode<T> u) {

		//TODO: redesign this function!
				
		// 2. If it is a leaf
		if( u.hasLeftChild() && u.hasRightChild() ) {
			// 4. If it has two children:
			// a) find the maximum value in the left subtree
			BinaryTreeNode<T> max = u.getLeftChild();
			while(max.hasRightChild()) {
				max = max.getRightChild();
			}
			// b) replace the value in the node with the max
			u.setElement(max.getElement());
			// c) delete the max
			deleteNode(max);

		} else if( u.isLeaf() ) {
			// Delete it by modifying its parent's reference
			//2.1: corner case, if you are deleting the root:
			if(u.isRoot()) {
				this.root = null;
			} else if(u.getParent().getLeftChild() == u) {
				u.getParent().setLeftChild(null);
			} else {
				u.getParent().setRightChild(null);
			}
		} else if(u.hasLeftChild()) {
			if(u.isLeftChild()) {
				u.getParent().setLeftChild(u.getLeftChild());				
			} else {
				u.getParent().setRightChild(u.getLeftChild());
			}
			u.getLeftChild().setParent(u.getParent());
		} else if(u.hasRightChild()) {
			if(u.isLeftChild()) {
				u.getParent().setLeftChild(u.getRightChild());
			} else {
				u.getParent().setRightChild(u.getRightChild());
			}
			u.getRightChild().setParent(u.getParent());
		}

		
	}
	

	/**
	 * Searches for the given value and removes it if it is in this tree.
	 * 
	 * Returns <code>true</code> if the delete operation had an effect on
	 * this tree, <code>false</code> otherwise.
	 * 
	 * @param x
	 */
	public boolean delete(T x) {

		BinaryTreeNode<T> u = getNode(x);
		if(u == null) {
			return false;
		} else {
			deleteNode(u);
			return true;
		}
		
	}

}
