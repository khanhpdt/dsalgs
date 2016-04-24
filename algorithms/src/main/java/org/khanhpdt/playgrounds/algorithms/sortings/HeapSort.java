package org.khanhpdt.playgrounds.algorithms.sortings;

import org.khanhpdt.playgrounds.datastructures.trees.BinaryMaxHeap;

import static org.khanhpdt.playgrounds.datastructuresalgorithms.commons.Commons.swap;

/**
 * @author khanhpdt
 */
public class HeapSort<T extends Comparable<T>> implements Sorter<T> {

	@Override
	public void sort(T[] elements) {
		BinaryMaxHeap heap = new BinaryMaxHeap<>(elements);
		for (int i = elements.length - 1; i > 0; i--) {
			// move the largest element (at index 0) to the tail
			swap(elements, 0, i);
			// ignore the tail as it is already sorted
			heap.reduceHeapSizeBy(1);
			// restructure the heap because of the new root
			heap.maxHeapifyRoot();
		}
	}

}
