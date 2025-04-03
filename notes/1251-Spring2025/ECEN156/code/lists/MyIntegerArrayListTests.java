package uno.ece.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class MyIntegerArrayListTests {

	@Test
	public void testAddAndGet() {

		MyIntegerArrayList list = new MyIntegerArrayList();

		list.add(10);
		list.add(20);
		list.add(30);
		// expectation: [10, 20, 30]
		int expected, actual;
		expected = 10;
		actual = list.retrieve(0);
		assertEquals(expected, actual);
		expected = 20;
		actual = list.retrieve(1);
		assertEquals(expected, actual);
		expected = 30;
		actual = list.retrieve(2);
		assertEquals(expected, actual);

	}

	@Test
	public void testAddAndGetBulk() {

		MyIntegerArrayList list = new MyIntegerArrayList();

		for (int i = 10; i <= 1000; i += 10) {
			list.add(i);
		}
		// expectation: [10, 20, 30, ..., 1000]
		int expected, actual;
		for (int i = 0; i < list.size(); i++) {
			expected = (i + 1) * 10;
			actual = list.retrieve(i);
			assertEquals(expected, actual);
		}

	}

	@Test
	public void testIndexBoundsTest01() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertThrows(RuntimeException.class, () -> list.retrieve(5000));
	}

	@Test
	public void testIndexBoundsTest02() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertThrows(RuntimeException.class, () -> list.retrieve(-10));
	}

	@Test
	public void testIndexBoundsTest03() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		list.add(10);
		list.add(20);
		list.add(30);
		assertThrows(RuntimeException.class, () -> list.retrieve(4));
	}

	@Test
	public void testAddAndDeleteBulkFromFront() {

		MyIntegerArrayList list = new MyIntegerArrayList();

		for (int i = 10; i <= 1000; i += 10) {
			list.add(i);
		}

		for (int i = 0; i < 100; i++) {
			int expected = (i + 1) * 10;
			int actual = list.remove(0);
			assertEquals(expected, actual);
		}
		assertEquals(0, list.size());
	}

	@Test
	public void testAddAtIndex() {

		MyIntegerArrayList list = new MyIntegerArrayList();

		for (int i = 10; i <= 1000; i += 10) {
			list.add(i, 0);
		}
		// expected: [1000, 990, 980, ..., 30, 20, 10]
		int value = 1000;
		for (int i = 0; i < 100; i++) {
			int expected = value;
			int actual = list.retrieve(i);
			assertEquals(expected, actual);
			value -= 10;
		}

	}

}
