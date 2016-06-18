package vn.khanhpdt.playgrounds.ctci.arraysandstrings;

import org.apache.commons.lang3.StringUtils;
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
}