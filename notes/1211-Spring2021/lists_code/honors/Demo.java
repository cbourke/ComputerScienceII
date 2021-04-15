package unl.cse.lists.honors;

import java.util.Comparator;

public class Demo {

	public static void main(String args[]) {
		
		Stack<Integer> s = new Stack<>();
		
		s.push(10);
		s.push(20);
		s.push(30);
				
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		//System.out.println(s.pop());
	}

}
