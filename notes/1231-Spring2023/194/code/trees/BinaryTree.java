package uno.ece.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTree<T> implements Iterable<T> {

	private BinaryTreeNode<T> root;

	public BinaryTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public void insert(T x) {

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

	public List<T> postOrder() {
		List<T> result = new ArrayList<>();
		postOrder(this.root, result);
		return result;
	}

	private void postOrder(BinaryTreeNode<T> current, List<T> result) {

		// base case:
		// if the current node is invalid, don't do anything!
		if (current == null) {
			return;
		}

		// if the left child exists, recurse on it...
		postOrder(current.getLeftChild(), result);
		// if the right child exists, recurse on it...
		postOrder(current.getRightChild(), result);
		// process the current node
		result.add(current.getElement());
	}

	public List<T> preOrder() {

		List<T> result = new ArrayList<>();

		Stack<BinaryTreeNode<T>> s = new Stack<BinaryTreeNode<T>>();
		s.push(this.root);
		while (!s.isEmpty()) {
			BinaryTreeNode<T> x = s.pop();

			result.add(x.getElement());

			if (x.getRightChild() != null) {
				s.push(x.getRightChild());
			}
			if (x.getLeftChild() != null) {
				s.push(x.getLeftChild());
			}
		}

		return result;
	}

	public List<T> breadthFirstSearch() {

		List<T> result = new ArrayList<>();

		Deque<BinaryTreeNode<T>> q = new LinkedList<BinaryTreeNode<T>>();

		q.offer(this.root);
		while (!q.isEmpty()) {
			BinaryTreeNode<T> x = q.poll();

			result.add(x.getElement());

			if (x.getLeftChild() != null) {
				q.offer(x.getLeftChild());
			}
			if (x.getRightChild() != null) {
				q.offer(x.getRightChild());
			}
		}

		return result;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new Iterator<T>() {
			
			private Stack<BinaryTreeNode<T>> s = new Stack<BinaryTreeNode<T>>();
			private BinaryTreeNode<T> current = root;

			@Override
			public boolean hasNext() {
				return (this.current != null);
			}

			@Override
			public T next() {
				T x = current.getElement();

				if (current.getRightChild() != null) {
					s.push(current.getRightChild());
				}
				if (current.getLeftChild() != null) {
					s.push(current.getLeftChild());
				}
				if(!this.s.isEmpty()) {
					this.current = this.s.pop();
				} else {
					this.current = null;
				}
				return x;
			}
			
		};
	}

}
