package vn.khanhpdt.playgrounds.algorithms.sortings;

import java.util.Arrays;

/**
 * @author khanhpdt
 */
public class CountingSort {

	public static int[] sort(int[] elements) {
		validate(elements);

		int max = Arrays.stream(elements).max().getAsInt();
		int[] counts = new int[max + 1];

		// count the number of times each value appears in the given array
		for (int element : elements) {
			counts[element]++;
		}

		// count the number of the elements smaller than or equal to a given element
		for (int i = 1; i < counts.length; i++) {
			counts[i] += counts[i - 1];
		}

		int[] result = new int[elements.length];
		for (int element : elements) {
			// because of the counting, we're sure that the elements smaller than or equal to this element
			// will be placed before this position.
			int position = counts[element] - 1;
			result[position] = element;

			// place the other elements equal to this element before this element
			counts[element]--;
		}

		return result;
	}

	private static void validate(int[] elements) {
		for (int element : elements) {
			if (element < 0) {
				throw new IllegalArgumentException("Only supports non-negative numbers at the moment");
			}
		}
	}

}