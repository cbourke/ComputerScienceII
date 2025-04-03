package unl.soc.lists;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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
		int expected, actual;
		// expectation: [10, 20, 30]
		// actual?
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
	public void testAddAndGetBulk() {
		MyIntegerArrayList list = new MyIntegerArrayList();

		for (int i = 10; i <= 1000; i += 10) {
			list.add(i);
		}

		int expected, actual;
		for (int i = 0; i < list.size(); i++) {
			expected = (i + 1) * 10;
			actual = list.get(i);
			assertEquals(expected, actual);
		}

	}

	@Test
	public void testIndexBounds00() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		list.add(10);
		assertDoesNotThrow(() -> list.get(0));

	}

	@Test
	public void testIndexBounds01() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertThrows(IllegalArgumentException.class, () -> list.get(0));
	}

	@Test
	public void testIndexBounds02() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertThrows(IllegalArgumentException.class, () -> list.get(-10));

	}

	@Test
	public void testIndexBounds03() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		assertThrows(IllegalArgumentException.class, () -> list.get(100));

	}

	@Test
	public void testDeleteFromFront() {
		MyIntegerArrayList list = new MyIntegerArrayList();
		for (int i = 10; i <= 1000; i += 10) {
			list.add(i);
		}
		for (int i = 0; i < 99; i++) {
			list.remove(0);
			assertEquals((i + 2) * 10, list.get(0));
		}
		list.remove(0);
		assertEquals(0, list.size());
	}

	@Test
	public void testDeleteFromBack() {
		// TODO
	}

	@Test
	public void testDeleteFromFrontAndBack() {
		// TODO
	}

}
