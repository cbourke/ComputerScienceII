package unl.soc.trees;

import java.util.List;

public class Demo {

	public static void main(String[] args) {

		BinarySearchTree<Integer> t = TreeUtils.largeBst();
		
		System.out.println(TreeUtils.toTikz(t));

		String elements = "50,40,60,20,45,90,10,24,49,80,95,2,15,28,48,75,85,12";
		for(String s : elements.split(",")) {
			int x = Integer.parseInt(s);
			t.remove(x);
			System.out.println(TreeUtils.toTikz(t));
		}


		
	}

}
