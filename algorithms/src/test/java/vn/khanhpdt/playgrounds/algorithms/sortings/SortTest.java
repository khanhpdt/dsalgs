package vn.khanhpdt.playgrounds.algorithms.sortings;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;

public class SortTest {

	private static final Integer[] SORTED_ASCENDING = IntStream.range(1, 20).boxed().toArray(Integer[]::new);

	private Integer[] randomlyPermuted = Arrays.copyOf(SORTED_ASCENDING, SORTED_ASCENDING.length);

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
		Collections.shuffle(Arrays.asList(randomlyPermuted));

		sorter.sort(randomlyPermuted);

		assertThat("sorted in ascending order", randomlyPermuted, Matchers.arrayContaining(SORTED_ASCENDING));
	}

}
