package vn.khanhpdt.playgrounds.ctci.arraysandstrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author khanhpdt
 */
public class ArraysAndStrings {

	/**
	 * Problem 1.1.
	 *
	 * <p>Complexity: O(n^2), where n = length of the string</p>
	 */
	public static boolean hasUniqueCharacters(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			char c = s.charAt(i);
			for (int j = i + 1; j < s.length(); j++) {
				// duplicated character found
				if (c == s.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Problem 1.1.
	 *
	 * <p>Complexity: O(n), where n = length of the string</p>
	 */
	public static boolean hasUniqueCharacters_2(String s) {
		Set<Character> characters = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			boolean firstTimeInserted = characters.add(s.charAt(i));
			if (!firstTimeInserted) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Problem 1.2.
	 *
	 * <p>Complexity: O(n), where n = length of the string</p>
	 */
	public static String reverseString(String s) {
		char[] reversed = new char[s.length()];
		int j = s.length() - 1;
		for (int i = 0; i < s.length(); i++) {
			reversed[j] = s.charAt(i);
			j--;
		}
		return String.valueOf(reversed);
	}

	/**
	 * Problem 1.3.
	 *
	 * <p>Complexity: O(n), where n = length of the strings</p>
	 */
	public static boolean checkPermutation(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		Map<Character, Integer> characterCounts = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			characterCounts.putIfAbsent(c, 0);
			characterCounts.put(c, characterCounts.get(c) + 1);
		}

		for (int i = 0; i < s2.length(); i++) {
			char c = s2.charAt(i);
			// s1 does not have a character in s2
			if (characterCounts.get(c) == null) {
				return false;
			}
			// s1 has fewer of this character than s2
			else if (characterCounts.get(c) == 0) {
				return false;
			}
			else {
				characterCounts.put(c, characterCounts.get(c) - 1);
			}
			// We don't need to check for the case when s1 has more of some character than s2.
			// Because they are of same length, if this happens, s1 must have fewer of some other
			// character than s2, and this case is already handled.
		}

		return true;
	}
}
