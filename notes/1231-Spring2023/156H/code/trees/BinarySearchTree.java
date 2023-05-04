package unl.soc.trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

public class BinarySearchTree<T> {

	private int size;
	private Comparator<T> cmp;
	BinaryTreeNode<T> root;

	public BinarySearchTree(Comparator<T> cmp) {
		this.size = 0;
		this.cmp = cmp;
		this.root = root;
	}

	public boolean add(T x) {

		if (this.root == null) {
			BinaryTreeNode<T> newRoot = new BinaryTreeNode<>(x);
			this.root = newRoot;
			this.size++;
			return true;
		}

		BinaryTreeNode<T> previous = null;
		BinaryTreeNode<T> current = this.root;
		while (current != null) {
			int cmpResult = cmp.compare(x, current.getElement());
			if (cmpResult == 0) {
				// NOOP
				return false;
			} else if (cmpResult < 0) {
				// go left
				previous = current;
				current = current.getLeftChild();
			} else {
				// go right
				previous = current;
				current = current.getRightChild();
			}
		}

		// insert a new leaf:
		// 1. create a new node:
		BinaryTreeNode<T> newLeaf = new BinaryTreeNode<>(x);
		if (this.cmp.compare(x, previous.getElement()) < 0) {
			// left
			previous.setLeftChild(newLeaf);
		} else {
			// right
			previous.setRightChild(newLeaf);
		}
		newLeaf.setParent(previous);

		this.size++;
		return true;
	}

	/**
	 * Returns <code>true</code> if this BST contains the given element
	 * <code>x</code>, false otherwise.
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
			int cmpResult = cmp.compare(x, current.getElement());
			if (cmpResult == 0) {
				return current;
			} else if (cmpResult < 0) {
				// go left
				current = current.getLeftChild();
			} else {
				// go right
				current = current.getRightChild();
			}
		}

		return null;
	}

	public List<T> inOrder() {
		List<T> result = new ArrayList<>();
		inOrder(this.root, result);
		return result;
	}

	private void inOrder(BinaryTreeNode<T> u, List<T> result) {

		if (u != null) {
			inOrder(u.getLeftChild(), result);
			result.add(u.getElement());
			inOrder(u.getRightChild(), result);
		}
	}

	/**
	 * Removes the given element <code>x</code> from this tree if it exists and
	 * returns <code>true</code>. If no such element exists, returns
	 * <code>false</code>.
	 * 
	 * @param x
	 * @return
	 */
	public boolean remove(T x) {

		// 0. Find the node containing x...
		BinaryTreeNode<T> u = this.getNode(x);
		if (u == null) {
			// if no such node, return false: NOOP
			return false;
		}
		// Delete the node u...
		deleteNode(u);
		return true;
	}

	private void deleteNode(BinaryTreeNode<T> u) {
		// TODO: take care of the case where the tree has one node...

		if (u.isLeaf()) {
			// make u's parent point to null
			if (u.isLeftChild()) {
				u.getParent().setLeftChild(null);
			} else if (u.isRightChild()) {
				u.getParent().setRightChild(null);
			}
			u.setParent(null);
		} else if (u.hasLeftChild() && !u.hasRightChild()) {
			// only child and a left child
			if (u.isLeftChild()) {
				u.getParent().setLeftChild(u.getLeftChild());
			} else if (u.isRightChild()) {
				u.getParent().setRightChild(u.getLeftChild());
			}
			u.setParent(null);
		} else if (!u.hasLeftChild() && u.hasRightChild()) {
			// only child and a right child
			if (u.isLeftChild()) {
				u.getParent().setLeftChild(u.getRightChild());
			} else if (u.isRightChild()) {
				u.getParent().setRightChild(u.getRightChild());
			}
			u.setParent(null);
		} else {
			// both children are present
			// 2. Go to the left:
			BinaryTreeNode<T> current = u.getLeftChild();
			// 3. find the max: go to the right until you can't anymore...
			while (current.hasRightChild()) {
				// go right:
				current = current.getRightChild();
			}
			// at this point current is max...
			// 4. replace u's element with current(max)'s element:
			u.setElement(current.getElement());
			u.setParent(null);
			// 5. Delete the current node...
			deleteNode(current);
			
		}
	}

}
