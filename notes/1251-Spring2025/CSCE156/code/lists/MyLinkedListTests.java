package unl.soc.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class MyLinkedListTests {

	@Test
	public void testAddAndGet() {
		MyAbstractList<Integer> list = new MyLinkedList<>();
		assertEquals(0, list.size());

		list.add(10);
		assertEquals(1, list.size());
		list.add(20);
		assertEquals(2, list.size());
		list.add(30);
		assertEquals(3, list.size());
		// should look like: [10, 20, 30]
		int expected, actual;
		expected = 10;
		actual = list.get(0);
		assertEquals(expected, actual);
		expected = 20;
		actual = list.get(1);
		assertEquals(expected, actual);
		expected = 30;
		actual = list.get(2);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAddAndGetInterleaved() {
		MyAbstractList<Integer> list = new MyLinkedList<>();
		
		for(int i=10; i<=100; i+=10) {
			list.add(i);
		}
		//[10, 20, 30, ..., 90, 100]
		list.insert(42, 3);
		list.insert(142, 5);
		list.insert(242, 7);
		//[10, 20, 30, 42, 40, 142, 50, 242, 60, 70, 80, 90, 100]
		int expected[] = {10, 20, 30, 42, 40, 142, 50, 242, 60, 70, 80, 90, 100};
		for(int i=0; i<expected.length; i++) {
			assertEquals(expected[i], list.get(i));
		}
	}
	
	
	@Test
	public void testBulkAddAndGet() {
		MyAbstractList<Integer> list = new MyLinkedList<>();
		for (int i = 10; i <= 1000; i += 10) {
			list.add(i);
		}
		// should look like: [10, 20, 30, 40, ..., 1000]
		int expected, actual;
		for (int i = 0; i < 100; i++) {
			expected = (i + 1) * 10;
			actual = list.get(i);
			assertEquals(expected, actual);
		}
	}
	
	@Test
	public void testBulkAddAndGetFromFront() {
		MyAbstractList<Integer> list = new MyLinkedList<>();
		for (int i = 10; i <= 1000; i += 10) {
			list.insert(i, 0);
		}
		// should look like: [1000, 990, 980, ... 20, 10]
		int expected, actual;
		expected = 1000;
		for (int i = 0; i < 100; i++) {
			actual = list.get(i);
			assertEquals(expected, actual);
			expected -= 10;
		}
	}
	
	//TODO: test the delete method
	@Test
	public void testDelete() {
		// create an array with 10 elements
		MyAbstractList<Integer> list = new MyLinkedList<>();
		for(int i=10; i<=100; i+=10) {
			list.add(i);
		}
		int expected, actual;
		//[10, 20, 30, ..., 90, 100]
		
		expected = 10;
		actual = list.delete(0);
		assertEquals(expected, actual);
		assertEquals(list.size(), 9);

		expected = 100;
		actual = list.delete(list.size()-1);
		assertEquals(expected, actual);
		assertEquals(list.size(), 8);

		//[20, 30, 40, 50, 60, ..., 90]

		expected = 60;
		actual = list.delete(4);
		assertEquals(expected, actual);
		assertEquals(list.size(), 7);
	}
}
