package vn.khanhpdt.playgrounds.ctci.arraysandstrings;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem 1.1
 *
 * @author khanhpdt
 */
public class UniqueCharacterString {

	private final String s;

	public UniqueCharacterString(String str) {
		this.s = str;
	}

	/**
	 * O(n^2)
	 */
	public boolean isValid_1() {
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
	 * O(n)
	 */
	public boolean isValid_2() {
		Set<Character> characters = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			boolean firstTimeInserted = characters.add(s.charAt(i));
			if (!firstTimeInserted) {
				return false;
			}
		}
		return true;
	}
}
