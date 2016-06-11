package vn.khanhpdt.playgrounds.algorithms.sortings;

/**
 * @author khanhpdt
 */
public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

	@Override
	public void sort(T[] elements) {
		for (int i = 0; i < elements.length; i++) {
			T element = elements[i];

			int j = i - 1;

			// shift the elements on the left of [i] one position to their right, until the right place to insert
			// the element is found
			while (j >= 0 && element.compareTo(elements[j]) < 0) {
				elements[j + 1] = elements[j];
				j--;
			}

			// insert
			elements[j + 1] = element;
		}
	}

}
