package vn.khanhpdt.playgrounds.projecteuler;

/**
 * @see <a href="https://projecteuler.net/problem=2">Problem 2</a>
 * @author khanhpdt
 */
public class SumEvenFibonacciNumber {

	public static void main(String[] args) {
		int f1 = 1;
		int f2 = 2;
		int f = f1 + f2;

		int result = f2;
		while (f < 4_000_000) {
			if (f % 2 == 0) {
				result += f;
			}

			f1 = f2;
			f2 = f;
			f = f1 + f2;
		}

		System.out.println(result);
	}

}
