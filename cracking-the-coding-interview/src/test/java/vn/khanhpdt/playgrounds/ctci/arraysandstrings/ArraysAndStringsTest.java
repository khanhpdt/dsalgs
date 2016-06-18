package vn.khanhpdt.playgrounds.ctci.arraysandstrings;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.collection.IsArray;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author khanhpdt
 */
public class ArraysAndStringsTest {

	@Test
	public void testStringUniqueCharacters() throws Exception {
		String uniqueCharactersString = "abcdefgh";

		assertThat(ArraysAndStrings.hasUniqueCharacters(uniqueCharactersString), is(true));
		assertThat(ArraysAndStrings.hasUniqueCharacters_2(uniqueCharactersString), is(true));
	}

	@Test
	public void testStringNonUniqueCharacters() throws Exception {
		String nonUniqueCharactersString = "abcdefah";

		assertThat(ArraysAndStrings.hasUniqueCharacters(nonUniqueCharactersString), is(false));
		assertThat(ArraysAndStrings.hasUniqueCharacters_2(nonUniqueCharactersString), is(false));
	}

	@Test
	public void testReverseString() throws Exception {
		String s = "reverse this string";

		String reversed = ArraysAndStrings.reverseString(s);

		assertThat(reversed, is("gnirts siht esrever"));
		assertThat(reversed, is(StringUtils.reverse(s)));
	}

	@Test
	public void testCheckPermutation_1() throws Exception {
		String s1 = "abcdefghi";
		String s2 = "ighfedcab";

		assertThat(ArraysAndStrings.checkPermutation(s1, s2), is(true));
	}

	@Test
	public void testCheckPermutation_2() throws Exception {
		String s1 = "abcdefghi";
		String s2 = "ighfekcab";

		assertThat(ArraysAndStrings.checkPermutation(s1, s2), is(false));
	}

	@Test
	public void testCheckPermutation_3() throws Exception {
		String s1 = "abcdefghifda";
		String s2 = "ighfedcabadf";

		assertThat(ArraysAndStrings.checkPermutation(s1, s2), is(true));
	}

	@Test
	public void testReplaceSpaces() throws Exception {
		String s = "Mr John Smith    ";

		String result = ArraysAndStrings.replaceSpaces(s);

		assertThat(result, is("Mr%20John%20Smith"));
	}

	@Test
	public void testCompressString_1() throws Exception {
		String s = "aabcccccaaa";

		String compressed = ArraysAndStrings.compress(s);

		assertThat(compressed, is("a2b1c5a3"));
	}

	@Test
	public void testCompressString_2() throws Exception {
		String s = "aabbcaaa";

		String compressed = ArraysAndStrings.compress(s);

		assertThat("should be the original because length after the compression is not reduced",
				compressed, is(s));
	}

	@Test
	public void testCompressString_3() throws Exception {
		String s = "aabbccaa";

		String compressed = ArraysAndStrings.compress(s);

		assertThat("should be the original because length after the compression is not reduced",
				compressed, is(s));
	}

	@Test
	public void testRotateMatrix() throws Exception {
		Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

		Integer[][] rotated = ArraysAndStrings.rotateRight(matrix);

		assertThat(rotated[0], IsArray.array(is(7), is(4), is(1)));
		assertThat(rotated[1], IsArray.array(is(8), is(5), is(2)));
		assertThat(rotated[2], IsArray.array(is(9), is(6), is(3)));
	}
}