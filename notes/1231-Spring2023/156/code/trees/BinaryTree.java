package unl.soc.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree<T> implements Iterable<T> {

	private BinaryTreeNode<T> root;

	public BinaryTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public void insert(T x) {
		// TODO
	}
	
	
	public List<T> preOrderRecursive() {
		List<T> result = new ArrayList<>();
		preOrderRecursive(this.root, result);
		return result;
	}
	
	private void preOrderRecursive(BinaryTreeNode<T> current, List<T> result) {
		if(current != null) {
			result.add(current.getElement());
			preOrderRecursive(current.getLeftChild(), result);
			preOrderRecursive(current.getRightChild(), result);
			return;
		}
	}
	
	public List<T> inOrderRecursive() {
		List<T> result = new ArrayList<>();
		inOrderRecursive(this.root, result);
		return result;
	}
	
	private void inOrderRecursive(BinaryTreeNode<T> current, List<T> result) {
		if(current != null) {
			inOrderRecursive(current.getLeftChild(), result);
			result.add(current.getElement());
			inOrderRecursive(current.getRightChild(), result);
			return;
		}
	}
	
	public List<T> postOrderRecursive() {
		List<T> result = new ArrayList<>();
		postOrderRecursive(this.root, result);
		return result;
	}
	
	private void postOrderRecursive(BinaryTreeNode<T> current, List<T> result) {
		if(current != null) {
			postOrderRecursive(current.getLeftChild(), result);
			postOrderRecursive(current.getRightChild(), result);
			result.add(current.getElement());
			return;
		}
	}

	public List<T> preOrderTraversal() {

		List<T> result = new ArrayList<>();
		Deque<BinaryTreeNode<T>> stack = new LinkedList<>();
		stack.push(this.root);
		BinaryTreeNode<T> x;
		while (!stack.isEmpty()) {
			x = stack.pop();
			// process x:
			// System.out.println(x.getElement());
			result.add(x.getElement());
			// push the right child if it exists:
			if (x.hasRightChild()) {
				stack.push(x.getRightChild());
			}
			// push the left child if it exists:
			if (x.hasLeftChild()) {
				stack.push(x.getLeftChild());
			}
		}
		return result;

	}
	
	public List<T> breadthFirstSearch() {

		List<T> result = new ArrayList<>();
		Deque<BinaryTreeNode<T>> queue = new LinkedList<>();
		queue.offer(this.root);
		BinaryTreeNode<T> x;
		while (!queue.isEmpty()) {
			x = queue.poll();
			// process x:
			// System.out.println(x.getElement());
			result.add(x.getElement());
			// enqueue the left child if it exists:
			if (x.hasLeftChild()) {
				queue.offer(x.getLeftChild());
			}
			// enqueue the right child if it exists:
			if (x.hasRightChild()) {
				queue.offer(x.getRightChild());
			}
		}
		return result;

	}

	@Override
	public Iterator<T> iterator() {

		return new Iterator<T>() {

			private Deque<BinaryTreeNode<T>> stack = new LinkedList<>();
			private BinaryTreeNode<T> current = root;

			@Override
			public boolean hasNext() {
				return (current != null);
			}

			@Override
			public T next() {
				T result = current.getElement();
				
				if(current.hasRightChild()) {
					stack.push(current.getRightChild());
				}
				if(current.hasLeftChild()) {
					stack.push(current.getLeftChild());
				}
//				if(!stack.isEmpty()) {
//					current = stack.pop();
//				} else {
//					current = null;
//				}
//				
				current = stack.pollFirst();

				
				return result;
			}

		};

	}

}
