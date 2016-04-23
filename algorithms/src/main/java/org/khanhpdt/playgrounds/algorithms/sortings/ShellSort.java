package org.khanhpdt.playgrounds.algorithms.sortings;

/**
 * @author khanhpdt
 */
public class ShellSort<T extends Comparable<T>> implements Sorter<T> {

	private int gap;

	private int gapSeed;

	@Override
	public void sort(T[] elements) {
		generateGapSeed(elements.length);

		do {
			generateGap();

			// when i == gap, all the elements in the array are processed
			for (int i = 0; i < gap; i++) {
				// insertion-sort each subarray containing elements separated by gap-position
				for (int j = i + gap; j < elements.length; j = j + gap) {
					T element = elements[j];

					int k = j - gap;
					while (k >= 0 && element.compareTo(elements[k]) < 0) {
						elements[k + gap] = elements[k];
						k = k - gap;
					}
					elements[k + gap] = element;
				}
			}
		}
		// when gap = 1, the whole array will be sorted
		while (gap >= 1);
	}

	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Shellsort#Gap_sequences"></a>
	 */
	private void generateGap() {
		gap = (int) ((Math.pow(3, gapSeed) - 1) / 2);
		gapSeed--;
	}

	/**
	 * @see <a href="https://en.wikipedia.org/wiki/Shellsort#Gap_sequences"></a>
	 */
	private void generateGapSeed(int nElements) {
		gapSeed = (int) Math.floor(Math.log(2 * Math.ceil(nElements / 3) + 1) / Math.log(3));
	}

}
