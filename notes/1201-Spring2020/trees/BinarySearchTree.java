package unl.cse.bst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BinarySearchTree<T> extends BinaryTree<T> {

	private final Comparator<T> cmp;

	public BinarySearchTree(Comparator<T> cmp) {
		this.cmp = cmp;
	}

	/**
	 * Attempts to remove the node in the BST with the
	 * given value.  If removal results in a change of the 
	 * tree, true is returned.  If no effect, false.
	 * @param value
	 * @return
	 */
	public boolean delete(T value) {
		TreeNode<T> t = this.nodeSearch(value);
		if(t == null) {
			//you tried to delete a non-existant node, 
			return false;
		}
		
		//if u is a leaf:
		if( t.isLeaf() ) {
			//delete it by modifying the parent's reference
			//if I'm (t) a left child:
			if(t == t.getParent().getLeftChild()) {
				t.getParent().setLeftChild(null);
			} else {
				t.getParent().setRightChild(null);
			}			
		} else if (t.hasLeftChild() && !t.hasRightChild()) {
			//t only has a left child, promote it:
			//set t's parent's child (left or right) to t's left child
			TreeNode<T> parent = t.getParent();
			TreeNode<T> left = t.getLeftChild();
			left.setParent(parent);
			//if t is the left child of the parent
			if(t == parent.getLeftChild()) {
				//set the parent's left child to left:
				parent.setLeftChild(left);
			} else {
				parent.setRightChild(left);
			}
		} else if (!t.hasLeftChild() && t.hasRightChild()) {
			//t only has a right child, promote it:
			//set t's parent's child (left or right) to t's right child
			TreeNode<T> parent = t.getParent();
			TreeNode<T> right = t.getRightChild();
			right.setParent(parent);
			//if t is the left child of the parent
			if(t == parent.getLeftChild()) {
				//set the parent's left child to right:
				parent.setLeftChild(right);
			} else {
				parent.setRightChild(right);
			}
		}
		
		return true;
	}

	/**
	 * Searches the BST for the given value, returns null upon an unsuccessful
	 * search.
	 * 
	 * @param value
	 * @return
	 */
	private TreeNode<T> nodeSearch(T value) {

		TreeNode<T> curr = this.root;
		while (curr != null) {

			int x = cmp.compare(value, curr.getValue());
			// is curr the node I'm searching for?
			if (x == 0) {
				return curr;
			} else if (x < 0) {
				// traverse to the left subtree
				curr = curr.getLeftChild();
			} else {
				// traverse to the right subtree
				curr = curr.getRightChild();
			}
		}
		return null;
	}

	public T search(T value) {
		TreeNode<T> u = nodeSearch(value);
		return (u == null) ? null : u.getValue();
	}

	/**
	 * Inserts the given value into the BST.  If the
	 * new value is successfully inserted, returns true, 
	 * otherwise if the value is already in the BST, it is
	 * not inserted, and it return false.
	 * @param value
	 * @return
	 */
	public boolean insert(T value) {

		TreeNode<T> prev = null;
		TreeNode<T> curr = this.root;
		while (curr != null) {

			int x = cmp.compare(value, curr.getValue());
			if (x == 0) {
				return false;
			} else if (x < 0) {
				prev = curr;
				curr = curr.getLeftChild();				
			} else {
				prev = curr;
				curr = curr.getRightChild();
			}
		}

		TreeNode<T> newNode = new TreeNode<T>(value);
		newNode.setParent(prev);
		if(cmp.compare(value, prev.getValue()) < 0) {
			//insert as a left child
			prev.setLeftChild(newNode);
		} else {
			//insert as a right child
			prev.setRightChild(newNode);
		}
		return true;

	}

	public static void main(String args[]) {
		BinarySearchTree<Integer> t;

		t = new BinarySearchTree<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});

		Map<Integer, TreeNode<Integer>> m = new HashMap<>();
		int keys[] = { 37, 25, 63, 29, 62, 79, 30, 77, 87 };
		for (Integer x : keys) {
			m.put(x, new TreeNode<>(x));
		}
		t.root = m.get(37);
		m.get(37).setParent(null).setLeftChild(m.get(25)).setRightChild(m.get(63));
		m.get(25).setParent(m.get(37)).setLeftChild(null).setRightChild(m.get(29));
		m.get(63).setParent(m.get(37)).setLeftChild(m.get(62)).setRightChild(m.get(79));
		m.get(29).setParent(m.get(25)).setLeftChild(null).setRightChild(m.get(30));
		m.get(62).setParent(m.get(63)).setLeftChild(null).setRightChild(null);
		m.get(79).setParent(m.get(63)).setLeftChild(m.get(77)).setRightChild(m.get(87));
		m.get(30).setParent(m.get(29)).setLeftChild(null).setRightChild(null);
		m.get(77).setParent(m.get(79)).setLeftChild(null).setRightChild(null);
		m.get(87).setParent(m.get(79)).setLeftChild(null).setRightChild(null);
		System.out.println(t);

		Integer u = t.search(30);
		System.out.println(u);
		
		boolean b;
		
		t.insert(76);

		b = t.delete(77);
		System.out.println(b);
		u = t.search(77);
		System.out.println(u);
		
		System.out.println(t);

//		t = new BinarySearchTree<>();
//		Random r = new Random();
//		for(int i=0; i<10; i++) {
//			int x = r.nextInt(50);
//			System.out.printf("adding %d...\n", x);
//			t.add(x);
//		}
//		System.out.println(t);

	}

}
