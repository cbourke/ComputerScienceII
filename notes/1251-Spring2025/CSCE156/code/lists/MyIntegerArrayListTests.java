package unl.soc.lists;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class MyIntegerArrayListTests {

	@Test
	public void testAddAndGet() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertEquals(0, list.size());

		list.add(10);
		assertEquals(1, list.size());
		list.add(20);
		assertEquals(2, list.size());
		list.add(30);
		assertEquals(3, list.size());
		// should look like: [10, 20, 30]
		// TODO test: make sure the size of the array is 3...
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
	public void testBulkAddAndGet() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		for (int i = 10; i <= 1000; i += 10) {
			list.add(i);
			// TODO test: make sure the size of the array is 100...
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
	public void testInvalid01() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertThrows(IllegalArgumentException.class, () -> list.get(4000));
	}

	@Test
	public void testInvalid02() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertThrows(IllegalArgumentException.class, () -> list.get(-10));
	}

	@Test
	public void testInvalid03() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertThrows(IllegalArgumentException.class, () -> list.get(0));
	}

	@Test
	public void testDelete() {
		// create an array with 10 elements
		MyIntegerArrayList list = new MyIntegerArrayList();
		for (int i = 10; i <= 100; i += 10) {
			list.add(i);
		}
		int expected, actual;
		// [20, 30, ..., 90]

		expected = 10;
		actual = list.delete(0);
		assertEquals(expected, actual);
		assertEquals(list.size(), 9);

		expected = 100;
		actual = list.delete(list.size() - 1);
		assertEquals(expected, actual);
		assertEquals(list.size(), 8);

		expected = 60;
		actual = list.delete(4);
		assertEquals(expected, actual);
		assertEquals(list.size(), 7);
	}

	@Test
	public void testDeleteBulkFromFront() {

		// create an array with 10 elements
		MyIntegerArrayList list = new MyIntegerArrayList();
		for (int i = 10; i <= 100; i += 10) {
			list.add(i);
		}
		int expected, actual;

		for (int i = 0; i < 10; i++) {
			actual = list.delete(0);
			expected = (i + 1) * 10;
			assertEquals(expected, actual);
		}
		assertEquals(list.size(), 0);

	}

	@Test
	public void testDeleteBulkFromBack() {

		// create an array with 10 elements
		MyIntegerArrayList list = new MyIntegerArrayList();
		for (int i = 10; i <= 100; i += 10) {
			list.add(i);
		}
		int expected, actual;

		expected = 100;
		for (int i = 9; i >= 0; i--) {
			actual = list.delete(i);
			assertEquals(expected, actual);
			expected -= 10;
		}
		assertEquals(list.size(), 0);

	}

}
