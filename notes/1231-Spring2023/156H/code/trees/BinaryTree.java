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
	
	public List<T> preOrder() {
		List<T> result = new ArrayList<>();
		preOrderRecursive(this.root, result);
		return result;
	}
	
	private void preOrderRecursive(BinaryTreeNode<T> u, List<T> result) {
		
		if(u != null) {
			result.add(u.getElement());
			preOrderRecursive(u.getLeftChild(), result);
			preOrderRecursive(u.getRightChild(), result);
		}		
	}
	
	public List<T> inOrder() {
		List<T> result = new ArrayList<>();
		inOrder(this.root, result);
		return result;
	}
	
	private void inOrder(BinaryTreeNode<T> u, List<T> result) {
		
		if(u != null) {
			inOrder(u.getLeftChild(), result);
			result.add(u.getElement());
			inOrder(u.getRightChild(), result);
		}		
	}
	
	public List<T> postOrder() {
		List<T> result = new ArrayList<>();
		postOrder(this.root, result);
		return result;
	}
	
	private void postOrder(BinaryTreeNode<T> u, List<T> result) {
		
		if(u != null) {
			postOrder(u.getLeftChild(), result);
			postOrder(u.getRightChild(), result);
			result.add(u.getElement());
		}		
	}

	public List<T> preOrderTraversalStackBased() {

		List<T> result = new ArrayList<>();
		
		Deque<BinaryTreeNode<T>> stack = new LinkedList<>();
		stack.push(this.root);
		while( !stack.isEmpty() ) {
			BinaryTreeNode<T> x = stack.pop();
			result.add(x.getElement());
			if(x.hasRightChild()) {
				stack.push(x.getRightChild());
			}
			if(x.hasLeftChild()) {
				stack.push(x.getLeftChild());
			}
		}
		return result;
	}
	
	public List<T> breadthFirstSearch() {

		List<T> result = new ArrayList<>();
		
		Deque<BinaryTreeNode<T>> queue = new LinkedList<>();
		queue.offer(this.root);
		while( !queue.isEmpty() ) {
			BinaryTreeNode<T> x = queue.poll();
			result.add(x.getElement());
			if(x.hasLeftChild()) {
				queue.offer(x.getLeftChild());
			}
			if(x.hasRightChild()) {
				queue.offer(x.getRightChild());
			}
		}
		return result;
	}

	@Override
	public Iterator<T> iterator() {

		return new Iterator<T>() {

			private Deque<BinaryTreeNode<T>> stack = new LinkedList<>();
			//the current node that the little guy is standing on...
			private BinaryTreeNode<T> current = root;

			@Override
			public boolean hasNext() {
				return (current != null);
			}

			@Override
			public T next() {
				
				T result = this.current.getElement();
				
				if(current.hasRightChild()) {
					this.stack.push(this.current.getRightChild());
				}
				if(current.hasLeftChild()) {
					this.stack.push(this.current.getLeftChild());
				}
				
				if(!stack.isEmpty()) {
					current = stack.pop();
				} else {
					current = null;
				}
				
				
				return result;
			}
			
		};
		
	}

}
