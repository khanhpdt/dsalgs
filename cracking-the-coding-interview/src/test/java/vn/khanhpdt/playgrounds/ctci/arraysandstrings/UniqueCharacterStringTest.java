package vn.khanhpdt.playgrounds.ctci.arraysandstrings;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author khanhpdt
 */
public class UniqueCharacterStringTest {

	@Test
	public void testUnique() throws Exception {
		UniqueCharacterString uniqueCharacterString = new UniqueCharacterString("abcdefgh");

		assertThat(uniqueCharacterString.isValid_1(), is(true));
		assertThat(uniqueCharacterString.isValid_2(), is(true));
	}

	@Test
	public void testNonUnique() throws Exception {
		UniqueCharacterString uniqueCharacterString = new UniqueCharacterString("abcdefah");

		assertThat(uniqueCharacterString.isValid_1(), is(false));
		assertThat(uniqueCharacterString.isValid_2(), is(false));
	}

}