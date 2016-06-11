package vn.khanhpdt.playgrounds.projecteuler;

/**
 * @see <a href="https://projecteuler.net/problem=1">Problem 1</a>
 * @author khanhpdt
 */
public class MultipleThreeOrFive {

	public static void main(String[] args) {
		final int n = 1_000;

		int result = 0;
		for (int i = 1; i < n; i ++) {
			if (i % 3 == 0 || i % 5 == 0) {
				result += i;
			}
		}

		System.out.println(result);
	}

}
