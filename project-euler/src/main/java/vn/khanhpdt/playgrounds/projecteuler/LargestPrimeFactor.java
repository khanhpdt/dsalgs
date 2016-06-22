package vn.khanhpdt.playgrounds.projecteuler;

/**
 * @see <a href="https://projecteuler.net/problem=3">Problem 3</a>
 * @author khanhpdt
 */
public class LargestPrimeFactor {

	public static void main(String[] args) {
		final long N = 600851475143L;
		final long SQUARE_ROOT = (long) Math.floor(Math.sqrt(N));
		long result;

		if (SQUARE_ROOT < 2) {
			result = N;
			System.out.println(result);
			return;
		}

		long n = N;
		long factor = 2;
		long lastFactor = factor;
		while (n > 1 && factor <= SQUARE_ROOT) {
			if (n % factor == 0) {
				lastFactor = factor;

				// keep dividing to guarantee that every factor that we find here is a prime number.
				// this is kind of similar to prime factorization.
				do {
					n = n / factor;
				} while (n % factor == 0);
			}

			factor++;
		}

		// all of the prime factors are smaller than SQUARE_ROOT
		if (n == 1) {
			result = lastFactor;
		}
		// there exists one prime factor greater than SQUARE_ROOT. Because there is only one such prime factor,
		// it must be the quotient of the last dividing.
		else {
			result = n;
		}

		System.out.println(result);
	}

}
