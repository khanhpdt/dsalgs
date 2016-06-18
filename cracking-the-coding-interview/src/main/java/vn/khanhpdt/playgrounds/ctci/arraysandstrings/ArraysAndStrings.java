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

	/**
	 * Problem 1.4.
	 *
	 * <p>Complexity: O(n^2) in the worst case, where n = length of the strings.
	 * The main computation is the shifting of the array elements.</p>
	 */
	public static String replaceSpaces(String s) {
		char[] chars = s.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				// shift the array 2 positions to the right for the replacement. the shift starts
				// from (i + 1) to the end.
				// NOTE: we need to do the shifting in-place
				for (int j = chars.length - 1; j >= i + 3; j--) {
					chars[j] = chars[j - 2];
				}

				// replace space by"%20"
				chars[i] = '%';
				chars[i + 1] = '2';
				chars[i + 2] = '0';

				// no need to check the replacements
				i += 2;
			}
		}

		return String.valueOf(chars);
	}

	/**
	 * Problem 1.5.
	 *
	 * <p>Complexity: O(n)</p>
	 */
	public static String compress(String s) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int count = 1;
			// keep finding all the similar consecutive characters
			while (i < s.length() - 1 && ch == s.charAt(i + 1)) {
				count++;
				i++;
			}
			builder.append(ch).append(count);
		}

		String compressed = builder.toString();
		return compressed.length() < s.length() ? compressed : s;
	}
}
