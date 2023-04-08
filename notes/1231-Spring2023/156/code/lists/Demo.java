package unl.soc.lists;

public class Demo {

	public static void main(String[] args) {

		Queue<Integer> q = new Queue<>();

		q.enqueue(10);
		q.enqueue(20);
		q.enqueue(30);
		q.enqueue(40);

		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());		
		System.out.println(q.dequeue());


	}

}
