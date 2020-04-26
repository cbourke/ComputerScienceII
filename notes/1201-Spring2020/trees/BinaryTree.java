package unl.cse.bst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.List;
import java.util.Map;

public class BinaryTree<T> {

    protected TreeNode<T> root;

    public List<T> traverse() {
    	List<T> result = new ArrayList<>();
    	
    	inOrderStackTraversal(result);
    	
    	
    	return result;
    }
    
    public void bfsTraversal(List<T> result) {
    	Deque<TreeNode<T>> q = new LinkedList<>();
    	q.offer(this.root); //enqueue the root
    	
    	while(!q.isEmpty()) {
    		TreeNode<T> current = q.poll();
        	result.add(current.getValue());
        	if(current.hasLeftChild()) {
        		q.offer(current.getLeftChild());
        	}
        	if(current.hasRightChild()) {
        		q.offer(current.getRightChild());
        	}
    	}
    	return;

    }

    public void postOrderStackTraversal(List<T> result) {
    	
    	Stack<TreeNode<T>> s = new Stack<>();
    	TreeNode<T> prev = null;
    	s.push(this.root);
    	while(!s.isEmpty()) {
        	TreeNode<T> curr = s.peek();
        	if(prev == null || 
        	   prev.getLeftChild() == curr || 
        	   prev.getRightChild() == curr) {
        		//came from the parent: this is the first time we've seen curr
        		if(curr.hasLeftChild()) {
        			s.push(curr.getLeftChild());
        		} else if(curr.hasRightChild()) {
        			s.push(curr.getRightChild());
        		}
        	} else if(curr.getLeftChild() == prev) {
        		//we came back up from the left subtree
        		if(curr.hasRightChild()) {
        			s.push(curr.getRightChild());
        		}
        	} else {
        		//we came back up from the right subtree
        		result.add(curr.getValue());
        		s.pop();
        	}
        	prev = curr;
    	}
    	
    }
    
    public void inOrderStackTraversal(List<T> result) {
    	Stack<TreeNode<T>> s = new Stack<>();
    	TreeNode<T> curr = this.root;
    	while(!s.isEmpty() || curr != null) {
    		if(curr != null) {
    			s.push(curr);
    			curr = curr.getLeftChild();
    		} else {
    			curr = s.pop();
    			result.add(curr.getValue());
    			curr = curr.getRightChild();
    		}
    	}
    }
    
    public void preOrderStackTraversal(List<T> result) {
    	
    	Stack<TreeNode<T>> s = new Stack<>();
    	s.push(this.root);
    	while(!s.isEmpty()) {
    		TreeNode<T> current = s.pop();
        	result.add(current.getValue());
        	if(current.hasRightChild()) {
        		s.push(current.getRightChild());
        	}
        	if(current.hasLeftChild()) {
        		s.push(current.getLeftChild());
        	}
    	}
    	return;
    }
    
    

    public void postorderRecursive(TreeNode<T> current, List<T> result) {
    	
    	if(current == null) {
    		return;
    	}
    	
    	postorderRecursive(current.getLeftChild(), result);
    	postorderRecursive(current.getRightChild(), result);
    	result.add(current.getValue());
    }
    public void inorderRecursive(TreeNode<T> current, List<T> result) {
    	
    	if(current == null) {
    		return;
    	}
    	
    	inorderRecursive(current.getLeftChild(), result);
    	result.add(current.getValue());
    	inorderRecursive(current.getRightChild(), result);
    }

    public void preorderRecursive(TreeNode<T> current, List<T> result) {
    	
    	if(current == null) {
    		//do nothing
    		return;
    	}
    	
    	//process the current node
    	result.add(current.getValue());
    	//recursively handle/traverse the left sub tree
    	preorderRecursive(current.getLeftChild(), result);
    	//recursively handle/traverse the right sub tree:
    	preorderRecursive(current.getRightChild(), result);
    }
    
    public List<T> traversePostOrderStack() {
    	List<T> result = new ArrayList<>();
    	
    	Stack<TreeNode<T>> s = new Stack<>();
    	TreeNode<T> prev = null;
    	s.push(this.root);
    	while(!s.isEmpty()) {
    		//establish the current node, but do not remove it
    		//as we need to (eventually) process it after its left
    		// and right subtree are done
    		TreeNode<T> curr = s.peek();
    		//if curr is the root or we came from the parent
    		//   we (curr) is the previous's left child or right child
    		if(prev == null || prev.getLeftChild() == curr || prev.getRightChild() == curr) {
    			//push left if exists because we need to go to it next
    			//else if no left child, then push the right if it exists 
    			//  because we need to go to it next
    			//else do nothing and we'll head back up the tree
    			if(curr.hasLeftChild()) {
    				s.push(curr.getLeftChild());
    			} else if(curr.hasRightChild()) {
    				s.push(curr.getRightChild());
    			} 
    		} else if(curr.getLeftChild() == prev) {
    			//we're returning from traversing the left subtree
    			//if current has a right child, push it so we can go to it
    			if(curr.hasRightChild()) {
    				s.push(curr.getRightChild());
    			}
    		} else {
    			result.add(curr.getValue());
    			s.pop();
    		}
    		prev = curr;
    	}
    	
    	return result;
    }
    
    @Override
    public String toString() {
        if(root == null)
            return "[empty]";

        StringBuilder sb = new StringBuilder();
        Stack<TreeNode<T>> s = new Stack<TreeNode<T>>();
        TreeNode<T> curr = root;
        while(curr != null) {
            if(curr.getRightChild() != null)
                s.push(curr.getRightChild());
            if(curr.getLeftChild() != null)
                s.push(curr.getLeftChild());

            for(int i=0; i<getDepth(curr); i++)
                sb.append(" ");
            sb.append("|-"+curr.getValue()+"\n");
            if(s.isEmpty())
                curr = null;
            else
                curr = s.pop();
        }
        return sb.toString();
    }
    
    private int getDepth(TreeNode<T> node) {
        int depth = 0;
        TreeNode<T> curr = node;
        while(curr != root) {
            curr = curr.getParent();
            depth++;
        }
        return depth;
    }
    
    public static void main(String args[]) {
    	
    	BinaryTree<String> t = new BinaryTree<>();
    	Map<String, TreeNode<String>> m = new HashMap<>();
    	for(Character c = 'a'; c <= 'r'; c++) {
    		m.put(c.toString(), new TreeNode<>(c.toString()));
    	}
    	t.root = m.get("a");
    	m.get("a").setParent(null).setLeftChild(m.get("b")).setRightChild(m.get("c"));
    	m.get("b").setParent(m.get("a")).setLeftChild(m.get("d")).setRightChild(m.get("e"));
    	m.get("c").setParent(m.get("a")).setLeftChild(null      ).setRightChild(m.get("f"));
    	m.get("d").setParent(m.get("b")).setLeftChild(m.get("g")).setRightChild(m.get("h"));
       	m.get("e").setParent(m.get("b")).setLeftChild(null      ).setRightChild(m.get("i"));
    	m.get("f").setParent(m.get("c")).setLeftChild(m.get("j")).setRightChild(m.get("k"));
    	m.get("g").setParent(m.get("d")).setLeftChild(m.get("l")).setRightChild(m.get("m"));
    	m.get("h").setParent(m.get("d")).setLeftChild(null      ).setRightChild(m.get("n"));
    	m.get("i").setParent(m.get("e")).setLeftChild(m.get("o")).setRightChild(null);
    	m.get("j").setParent(m.get("f")).setLeftChild(m.get("p")).setRightChild(m.get("q"));
    	m.get("k").setParent(m.get("f")).setLeftChild(null      ).setRightChild(null      );
    	m.get("l").setParent(m.get("g")).setLeftChild(null      ).setRightChild(null      );
    	m.get("m").setParent(m.get("g")).setLeftChild(m.get("r")).setRightChild(null      );
    	m.get("n").setParent(m.get("h")).setLeftChild(null      ).setRightChild(null      );
    	m.get("o").setParent(m.get("i")).setLeftChild(null      ).setRightChild(null      );
    	m.get("p").setParent(m.get("j")).setLeftChild(null      ).setRightChild(null      );
    	m.get("q").setParent(m.get("j")).setLeftChild(null      ).setRightChild(null      );    	
    	m.get("r").setParent(m.get("m")).setLeftChild(null      ).setRightChild(null      );
    	//preorder:   [a, b, d, g, l, m, r, h, n, e, i, o, c, f, j, p, q, k]
    	//inorder:    [l, g, r, m, d, h, n, b, e, o, i, a, c, p, j, q, f, k]
    	//post order: [l, r, m, g, n, h, d, o, i, e, b, p, q, j, k, f, c, a]
    	//BFS: alphabetic!
    	System.out.println(t.traverse());
    }

}
