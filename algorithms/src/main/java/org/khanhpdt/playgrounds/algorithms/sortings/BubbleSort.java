package org.khanhpdt.playgrounds.algorithms.sortings;

/**
 * @author khanhpdt
 */
public class BubbleSort<T extends Comparable<T>> implements Sorter<T> {

	@Override
	public void sort(T[] elements) {
		int sorted = 0;
		// when (elements.length - 1) elements are sorted, all elements are also sorted
		while (sorted < elements.length - 1) {
			// the last sorted elements are already sorted, so exclude them from the sort
			for (int i = 0; i < (elements.length - 1) - sorted; i++) {
				if (elements[i].compareTo(elements[i+1]) > 0) {
					// swap when out of order
					swap(elements, i, i + 1);
				}
			}
			sorted++;
		}
	}

	private void swap(T[] elements, int i, int j) {
		T temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

}
