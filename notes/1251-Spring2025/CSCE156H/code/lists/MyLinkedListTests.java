package unl.soc.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class MyLinkedListTests {

	private static final Random R = new Random();

	@Test
	public void testAddAndGet() {
		MyLinkedList<Integer> list = new MyLinkedList<>();
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
		MyLinkedList<Integer> list = new MyLinkedList<>();

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
	public void testAddAndGetBulkFromFront() {
		MyLinkedList<Integer> list = new MyLinkedList<>();

		for (int i = 10; i <= 1000; i += 10) {
			list.insert(i, 0);
		}
		// [1000, 990, 980, ..., 20, 10]

		int expected, actual;
		expected = 1000;
		for (int i = 0; i < list.size(); i++) {
			actual = list.get(i);
			assertEquals(expected, actual);
			expected -= 10;
		}

	}

	@Test
	public void testAddAndGetInterleaved() {

		MyLinkedList<Integer> list = new MyLinkedList<>();

		for (int i = 10; i <= 100; i += 10) {
			list.add(i);
		}
		// [10, 20, 30... 100]
		list.insert(42, 3);
		list.insert(142, 5);
		list.insert(242, 7);
		// [10, 20, 30, 42, 40, 142, 50, 242, 60, 70, 80, 90, 100]
		int expected[] = { 10, 20, 30, 42, 40, 142, 50, 242, 60, 70, 80, 90, 100 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], list.get(i));
		}

	}

	@Test
	@RepeatedTest(100)
	public void testRandomValues() {
		MyLinkedList<Integer> list = new MyLinkedList<>();

		int numValues = R.nextInt(100) + 1;

		for (int i = 10; i <= numValues * 10; i += 10) {
			list.add(i);
		}
		int value = R.nextInt();
		int index = R.nextInt(numValues);
		list.insert(value, index);
		assertEquals(value, list.get(index));

	}

	@Test
	public void testDeleteBulkFromFront() {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		for (int i = 10; i <= 1000; i += 10) {
			list.add(i);
		}
		int actual, expected;
		for (int i = 0; i < 100; i++) {
			actual = list.remove(0);
			expected = (i + 1) * 10;
			assertEquals(expected, actual);
		}
		assertEquals(0, list.size());
	}

	@Test
	public void testDeleteBulkFromBack() {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		for (int i = 10; i <= 1000; i += 10) {
			list.add(i);
		}
		int actual, expected;
		expected = 1000;
		for (int i = 99; i >= 0; i--) {
			actual = list.remove(i);
			assertEquals(expected, actual);
			expected -= 10;
		}
		assertEquals(0, list.size());
	}

	@Test
	public void testDeleteFromMiddle() {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		for (int i = 10; i <= 100; i += 10) {
			list.add(i);
		}
		// [10, 20, 30, 40, 50, 60, ..., 100]
		int actual, expected;

		expected = 50;
		actual = list.remove(4);
		assertEquals(expected, actual);
		assertEquals(9, list.size());

		expected = 60;
		actual = list.remove(4);
		assertEquals(expected, actual);
		assertEquals(8, list.size());

		expected = 30;
		actual = list.remove(2);
		assertEquals(expected, actual);
		assertEquals(7, list.size());

		// 10, 20, 40, 70, 80, 90, 100
		expected = 100;
		actual = list.remove(6);
		assertEquals(expected, actual);
		assertEquals(6, list.size());

	}
}
