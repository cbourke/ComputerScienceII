package unl.soc.trees;

import java.util.Comparator;

public class TreeUtils {


	public static BinaryTree<String> largeTree() {
		BinaryTreeNode<String> a = new BinaryTreeNode<>("a");
		BinaryTreeNode<String> b = new BinaryTreeNode<>("b");
		BinaryTreeNode<String> c = new BinaryTreeNode<>("c");
		BinaryTreeNode<String> d = new BinaryTreeNode<>("d");
		BinaryTreeNode<String> e = new BinaryTreeNode<>("e");
		BinaryTreeNode<String> f = new BinaryTreeNode<>("f");
		BinaryTreeNode<String> g = new BinaryTreeNode<>("g");
		BinaryTreeNode<String> h = new BinaryTreeNode<>("h");
		BinaryTreeNode<String> i = new BinaryTreeNode<>("i");
		BinaryTreeNode<String> j = new BinaryTreeNode<>("j");
		BinaryTreeNode<String> k = new BinaryTreeNode<>("k");
		BinaryTreeNode<String> l = new BinaryTreeNode<>("l");
		BinaryTreeNode<String> m = new BinaryTreeNode<>("m");
		BinaryTreeNode<String> n = new BinaryTreeNode<>("n");
		BinaryTreeNode<String> o = new BinaryTreeNode<>("o");
		BinaryTreeNode<String> p = new BinaryTreeNode<>("p");
		BinaryTreeNode<String> q = new BinaryTreeNode<>("q");
		BinaryTreeNode<String> r = new BinaryTreeNode<>("r");
		
		a.setLeftChild(b);
		a.setRightChild(c);
		
		b.setParent(a);
		b.setLeftChild(d);
		b.setRightChild(e);
		
		c.setParent(a);
		c.setRightChild(f);
		
		d.setParent(b);
		d.setLeftChild(g);
		d.setRightChild(h);

		e.setParent(b);
		e.setRightChild(i);
		
		f.setParent(c);
		f.setLeftChild(j);
		f.setRightChild(k);
		
		g.setParent(d);
		g.setLeftChild(l);
		g.setRightChild(m);
		
		h.setParent(d);
		h.setRightChild(n);
		
		i.setParent(e);
		i.setLeftChild(o);
		
		j.setParent(f);
		j.setLeftChild(p);
		j.setRightChild(q);
		
		k.setParent(f);
		
		l.setParent(g);
		
		m.setParent(g);
		m.setLeftChild(r);
		
		n.setParent(h);
		
		o.setParent(i);
		
		p.setParent(j);
		
		q.setParent(j);
		
		r.setParent(m);
		
		return new BinaryTree<>(a);
	}

	public static BinaryTree<String> smallTree() {
		BinaryTreeNode<String> a = new BinaryTreeNode<>("a");
		BinaryTreeNode<String> b = new BinaryTreeNode<>("b");
		BinaryTreeNode<String> c = new BinaryTreeNode<>("c");
		BinaryTreeNode<String> d = new BinaryTreeNode<>("d");
		BinaryTreeNode<String> e = new BinaryTreeNode<>("e");
		BinaryTreeNode<String> f = new BinaryTreeNode<>("f");
		
		a.setLeftChild(b);
		a.setRightChild(c);
		
		b.setParent(a);
		b.setLeftChild(d);
		b.setRightChild(e);
		
		c.setParent(a);
		c.setRightChild(f);
		
		d.setParent(b);

		e.setParent(b);
		
		f.setParent(c);
		
		return new BinaryTree<>(a);
	}

	public static final Comparator<Integer> CMP_INT = new Comparator<>() {

		@Override
		public int compare(Integer s, Integer t) {
			return s.compareTo(t);
		}
		
	};
	
	public static BinarySearchTree<Integer> largeBst() {
		BinarySearchTree<Integer> result = new BinarySearchTree<>(CMP_INT);
		String elements = "50,40,60,20,45,90,10,24,49,80,95,2,15,28,48,75,85,12";
		for(String s : elements.split(",")) {
			int x = Integer.parseInt(s);
			result.add(x);
		}
		return result;
	}

	public static <T> String toTikz(BinarySearchTree<T> bst) {
		BinaryTreeNode<T> root = bst.root;
		if (root == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{tikzpicture}[transform shape,scale=1.0,level distance=1.25cm,level/.style={sibling distance=5cm/#1},every node/.style={circle,draw,minimum size=.75cm}]\n");
		sb.append("\\node{"+root.getElement()+"}\n");
		toTikz(root.getLeftChild(), sb);
		toTikz(root.getRightChild(), sb);		
		sb.append(";\n");
		sb.append("\\end{tikzpicture}\n");
		return sb.toString();
	}
	
	public static <T> void toTikz(BinaryTreeNode<T> u, StringBuilder sb) {
		if(u == null) {
			sb.append("child[draw opacity=0.0] {}\n");
		} else {
			sb.append("child {node {$"+u.getElement()+"$}\n");
			toTikz(u.getLeftChild(), sb);
			toTikz(u.getRightChild(), sb);				
			sb.append("}\n");
		}
	}

}
