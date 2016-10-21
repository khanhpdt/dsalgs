package vn.khanhpdt.playgrounds.algorithms.sortings;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author khanhpdt
 */
public class CountingSort {

	/**
	 * Sorts n integers in the range from 0 to k.
	 * <p>Worst-case complexity: O(n + k)</p>
	 */
	public static int[] sort(int[] elements) {
		return sort(elements, Function.identity());
	}

	private static void validate(int[] elements) {
		for (int element : elements) {
			if (element < 0) {
				throw new IllegalArgumentException("Only supports non-negative numbers at the moment");
			}
		}
	}

	public static int[] sort(int[] elements, Function<Integer, Integer> keyGetter) {
		int[] keys = Arrays.stream(elements).map(keyGetter::apply).toArray();

		validate(keys);

		int max = Arrays.stream(keys).max().getAsInt();

		// count the number of the keys smaller than or equal to a given key
		int[] lessThanOrEqualCounts = new int[max + 1];
		for (int key : keys) {
			lessThanOrEqualCounts[key]++;
		}
		for (int i = 1; i < lessThanOrEqualCounts.length; i++) {
			lessThanOrEqualCounts[i] += lessThanOrEqualCounts[i - 1];
		}

		// TODO sort in-place
		int[] result = new int[keys.length];
		// go backward to achieve stability
		for (int i = elements.length - 1; i >= 0; i--) {
			int element = elements[i];
			int key = keyGetter.apply(element);

			// because of the counting, we're sure that the keys less than or equal to this key
			// will be placed before this position.
			int position = lessThanOrEqualCounts[key] - 1;
			result[position] = element;

			// place the other keys equal to this element before this element
			lessThanOrEqualCounts[key]--;
		}

		return result;
	}

}