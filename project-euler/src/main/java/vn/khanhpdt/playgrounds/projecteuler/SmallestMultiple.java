package vn.khanhpdt.playgrounds.projecteuler;

/**
 * @see <a href="https://projecteuler.net/problem=5">Problem 5</a>
 * @author khanhpdt
 */
public class SmallestMultiple {

	public static void main(String[] args) {
		int n = 20;
		long result = 1;
		for (int i = 1; i <= n; i++) {
			result = Commons.leastCommonMultiple(result, i);
		}
		System.out.println(result);
	}

}