package unl.soc.lists;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PriorityQueue<T> {

	private List<T> q = new LinkedList<>();
	private Map<T, Integer> priorities = new HashMap<>();
	
	public void enqueue(T x, int priority) {
		this.priorities.put(x, priority);
		//TODO: insert x somewhere in q...
		int index = 0;
		while( index < this.q.size() && this.priorities.get(q.get(index)) >= priority) {
			index++;
		}
		this.q.add(index, x);
		
	}
	
	public T dequeue() {
		return this.q.remove(0);
	}
	
}
