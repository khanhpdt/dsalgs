package org.khanhpdt.playgrounds.datastructures.trees;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class BinaryHeapTest {

	@Test
	public void testBuildHeap() {
		Integer[] nodes = IntStream.range(1, 20).boxed().toArray(Integer[]::new);
		Collections.shuffle(Arrays.asList(nodes));

		BinaryHeap<Integer> heap = new BinaryHeap<>(nodes);

		assertThat("max heap property satisfied", checkMaxHeapProperty(heap), is(true));
	}

	private boolean checkMaxHeapProperty(BinaryHeap<Integer> binaryHeap) {
		Integer[] nodes = binaryHeap.getNodes();
		for (int i = 0; i < nodes.length; i++) {
			int leftChildIndex = 2 * i + 1;
			if (leftChildIndex < nodes.length && nodes[leftChildIndex].compareTo(nodes[i]) > 0) {
				return false;
			}

			int rightChildIndex = 2 * i + 2;
			if (rightChildIndex < nodes.length && nodes[rightChildIndex].compareTo(nodes[i]) > 0) {
				return false;
			}
		}
		return true;
	}
}
