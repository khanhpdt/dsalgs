package vn.khanhpdt.playgrounds.projecteuler;

/**
 * @see <a href="https://projecteuler.net/problem=4">Problem 4</a>
 * @author khanhpdt
 */
public class LargestPalindromeProduct {

	public static void main(String[] args) {		
		int result = 1;
		for (int i = 999; i >= 100; i--) {
			for (int j = i; j >= 100; j--) {
				int product = i * j;
				if (result < product && Commons.isPalindrome(product)) {
					result = product;
				}
			}
		}

		System.out.println(result);
	}

}