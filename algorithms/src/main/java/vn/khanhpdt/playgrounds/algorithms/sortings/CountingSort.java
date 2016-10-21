package vn.khanhpdt.playgrounds.algorithms.sortings;

import java.util.Arrays;

/**
 * @author khanhpdt
 */
public class CountingSort {

	/**
	 * Sorts n integers in the range from 0 to k.
	 * <p>Worst-case complexity: O(n + k)</p>
	 */
	public static int[] sort(int[] elements) {
		validate(elements);

		// O(n)
		int max = Arrays.stream(elements).max().getAsInt();

		// count the number of the elements smaller than or equal to a given element
		// O(k) memory in the worst case
		int[] lessThanOrEqualToElementCounts = new int[max + 1];
		// O(n)
		for (int element : elements) {
			lessThanOrEqualToElementCounts[element]++;
		}
		// O(k) in the worst case
		for (int i = 1; i < lessThanOrEqualToElementCounts.length; i++) {
			lessThanOrEqualToElementCounts[i] += lessThanOrEqualToElementCounts[i - 1];
		}

		// O(n) memory
		int[] result = new int[elements.length];
		// O(n)
		for (int element : elements) {
			// because of the counting, we're sure that the elements smaller than or equal to this element
			// will be placed before this position.
			int position = lessThanOrEqualToElementCounts[element] - 1;
			result[position] = element;

			// place the other elements equal to this element before this element
			lessThanOrEqualToElementCounts[element]--;
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