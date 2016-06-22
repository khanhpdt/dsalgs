package vn.khanhpdt.playgrounds.projecteuler;

/**
 * @author khanhpdt
 */
public final class Commons {

	private Commons() {
		// no instance
	}

	public static boolean isPalindrome(int n) {
		char[] chars = String.valueOf(n).toCharArray();
		for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
			if (chars[i] != chars[j]) {
				return false;
			}
		}
		return true;
	}

	public static long leastCommonMultiple(long n1, long n2) {
		return (n1 * n2) / greatestCommonDivisor(n1, n2);
	}

	public static long greatestCommonDivisor(long n1, long n2) {
		if (n2 == 0) {
			return n1;
		}
		return greatestCommonDivisor(n2, n1 % n2);
	}
}
