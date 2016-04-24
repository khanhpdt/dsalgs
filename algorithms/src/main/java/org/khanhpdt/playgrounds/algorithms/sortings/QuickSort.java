package org.khanhpdt.playgrounds.algorithms.sortings;

import static org.khanhpdt.playgrounds.datastructuresalgorithms.commons.Commons.swap;

/**
 * @author khanhpdt
 */
public class QuickSort<T extends Comparable<T>> implements Sorter<T> {

	@Override
	public void sort(T[] elements) {
		doSort(elements, 0, elements.length - 1);
	}

	private void doSort(T[] elements, int start, int end) {
		if (start >= end) {
			return;
		}

		int pivotIndex = partition(elements, start, end);
		doSort(elements, start, pivotIndex - 1);
		doSort(elements, pivotIndex + 1, end);
	}

	private int partition(T[] elements, int start, int end) {
		// use the last element as the pivot
		T pivot = elements[end];

		// at the beginning, the two subarrays are empty
		int leftSubArrayIndex = start - 1;
		int rightSubArrayIndex = end;

		// All elements are processed when they are all put to the two subarrays, which is when the leftArrayIndex and
		// the rightArrayIndex are next to each other.
		while (rightSubArrayIndex - leftSubArrayIndex > 1) {
			// process the array from left to right and from the first unprocessed item.
			int currentIndex = leftSubArrayIndex + 1;

			if (elements[currentIndex].compareTo(pivot) > 0) {
				// expand the right subarray to the left
				rightSubArrayIndex--;
				swap(elements, currentIndex, rightSubArrayIndex);
			} else {
				// expand the left subarray to the right. no need to swap elements as we are processing the array
				// from left to right.
				leftSubArrayIndex++;
			}
		}

		// move the pivot to its correct position
		int pivotIndex = leftSubArrayIndex + 1;
		swap(elements, end, pivotIndex);

		return pivotIndex;
	}

}
