package vn.khanhpdt.playgrounds.algorithms.sortings;

/**
 * @author khanhpdt
 */
public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

	@Override
	public void sort(T[] elements) {
		for (int i = 0; i < elements.length; i++) {
			int minIndex = findIndexOfMinimum(elements, i);
			exchangeElements(elements, i, minIndex);
		}
	}

	private int findIndexOfMinimum(T[] elements, int startingIndex) {
		int result = startingIndex;
		T minimum = elements[startingIndex];

		for (int i = startingIndex + 1; i < elements.length; i++) {
			if (elements[i].compareTo(minimum) < 0) {
				minimum = elements[i];
				result = i;
			}
		}

		return result;
	}

	private void exchangeElements(T[] elements, int indexElement1, int indexElement2) {
		T temp = elements[indexElement1];
		elements[indexElement1] = elements[indexElement2];
		elements[indexElement2] = temp;
	}

}
