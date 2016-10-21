package vn.khanhpdt.playgrounds.algorithms.sortings;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortTest {

	private static final Integer[] SORTED_ASCENDING = IntStream.range(1, 20).boxed().toArray(Integer[]::new);

	private Integer[] randomlyPermuted;

	@Before
	public void before() {
		randomlyPermuted = Arrays.copyOf(SORTED_ASCENDING, SORTED_ASCENDING.length);
		Collections.shuffle(Arrays.asList(randomlyPermuted));
	}

	@Test
	public void testSelectionSort() {
		testSort(new SelectionSort<>());
	}

	@Test
	public void testInsertionSort() {
		testSort(new InsertionSort<>());
	}

	@Test
	public void testShellSort() {
		testSort(new ShellSort<>());
	}

	@Test
	public void testBubbleSort() {
		testSort(new BubbleSort<>());
	}

	@Test
	public void testMergeSort() {
		testSort(new MergeSort<>());
	}

	@Test
	public void testHeapSort() {
		testSort(new HeapSort<>());
	}

	@Test
	public void testQuickSort() {
		testSort(new QuickSort<>());
	}

	private void testSort(Sorter<Integer> sorter) {
		sorter.sort(randomlyPermuted);
		assertThat("sorted in ascending order", randomlyPermuted, Matchers.arrayContaining(SORTED_ASCENDING));
	}

	@Test
	public void testCountingSort_1() {
		int[] numbers = {2, 5, 3, 0, 2, 3, 0, 3};

		int[] sorted = CountingSort.sort(numbers);

		assertThat(Arrays.equals(sorted, new int[]{0, 0, 2, 2, 3, 3, 3, 5}), is(true));
	}

	@Test
	public void testCountingSort_2() {
		int[] numbers = {4, 2, 5, 7, 3, 0, 9, 12, 7, 2, 3, 0, 3};

		int[] sorted = CountingSort.sort(numbers);

		assertThat(Arrays.equals(sorted, new int[]{0, 0, 2, 2, 3, 3, 3, 4, 5, 7, 7, 9, 12}), is(true));
	}

	@Test
	public void testRadixSort_1() {
		int[] numbers = {2, 5, 3, 0, 2, 3, 0, 3};

		int[] sorted = RadixSort.sort(numbers);

		assertThat(Arrays.equals(sorted, new int[]{0, 0, 2, 2, 3, 3, 3, 5}), is(true));
	}

	@Test
	public void testRadixSort_2() {
		int[] numbers = {4, 2, 5, 7, 3, 0, 9, 12, 7, 2, 3, 0, 3};

		int[] sorted = RadixSort.sort(numbers);

		assertThat(Arrays.equals(sorted, new int[]{0, 0, 2, 2, 3, 3, 3, 4, 5, 7, 7, 9, 12}), is(true));
	}

	@Test
	public void testRadixSort_3() {
		int[] numbers = IntStream.range(0, 101).toArray();
		Collections.shuffle(Arrays.asList(numbers));

		int[] sorted = RadixSort.sort(numbers);

		assertThat(Arrays.equals(sorted, IntStream.range(0, 101).toArray()), is(true));
	}

}
