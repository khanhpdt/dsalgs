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
	 * <p>Complexity: O(n), where n = length of the given string</p>
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

	/**
	 * Problem 1.6.
	 *
	 * <p>Complexity: O(m*n), where m and n are the number of rows and columns of the given matrix.</p>
	 */
	public static Integer[][] rotateRight_1(Integer[][] matrix) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;

		// O(m*n) memory
		Integer[][] result = new Integer[nCols][nRows];

		// right rotation
		for (int origRow = 0; origRow < nRows; origRow++) {
			int col = (nRows - 1) - origRow;
			for (int origCol = 0; origCol < nCols; origCol++) {
				int row = origCol;
				result[row][col] = matrix[origRow][origCol];
			}
		}

		return result;
	}

	/**
	 * Problem 1.6.
	 *
	 * <p>Complexity: O(m*n), where m and n are the number of rows and columns of the given matrix.</p>
	 * <p>In-place rotation.</p>
	 */
	public static void rotateRight_2(Integer[][] matrix) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;
		
		if (nRows != nCols) {
			throw new IllegalArgumentException("This in-place rotation only supports square matrix.");
		}

		// NOTE: We do the rotation in-place. The rotation is done layer by layer and in inward direction.
		int nLayers = (int) Math.ceil(nRows / 2);
		for (int layerIndex = 0; layerIndex < nLayers; layerIndex++) {
			int top = layerIndex, right = nCols - 1 - layerIndex, bottom = nRows - 1 - layerIndex, left = layerIndex;
			// Because we rotate from left to right, the element at position (nRows - layerIndex) is already the
			// correct element when we reach it. Thus, we don't rotate it.
			for (int i = layerIndex; i < (nRows - layerIndex) - 1; i++) { // nRows or nCols is fine because they are equal
				Integer tmp = matrix[top][i];

				// rotate the left-most column to become the top-most row
				matrix[top][i] = matrix[nRows - 1 - i][left];
				// rotate the bottom-most row to become the left-most column
				matrix[nRows - 1 - i][left] = matrix[bottom][nCols - 1 - i];
				// rotate the right-most column to become the bottom-most row
				matrix[bottom][nCols - 1 - i] = matrix[i][right];
				// rotate the top-most row to become the right-most column
				matrix[i][right] = tmp;
			}
		}
	}

	/**
	 * Problem 1.7.
	 *
	 * <p>Complexity: O(m*n*(m+n)), where m and n are the number of rows and columns of the given matrix.</p>
	 */
	public static Integer[][] setZeros(Integer[][] matrix) {
		int nRows = matrix.length;
		int nCols = matrix[0].length;

		Integer[][] result = new Integer[nRows][nCols];

		// clone the original matrix
		for (int i = 0; i < nRows; i++) {
			System.arraycopy(matrix[i], 0, result[i], 0, nCols);
		}

		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				if (matrix[i][j] == 0) {
					// set zeros for the whole row i
					for (int k = 0; k < nCols; k++) {
						result[i][k] = 0;
					}
					// set zeros for the whole column j
					for (int k = 0; k < nRows; k++) {
						result[k][j] = 0;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Problem 1.8.
	 * 
	 * <p>Complexity: O(n), where n = length of the two strings</p>
	 */
	public static boolean checkRotation(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}

		if (s1.equals(s2)) {
			return true;
		}

		// Assume that s1 = xy and the rotation happens at the point between x and y, then s2 is the rotation of s1 iff s2 = yx.
		// If s1.length = s2.length and s1 != s2, then s2 = yx iff s2 is a substring of s1 + s2 = xyxy

		return (s1 + s1).contains(s2);
	}

}
