package vn.khanhpdt.playgrounds.datastructures.tables;

import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class HashTableTest {

	@Test
	public void testInsert() throws Exception {
		HashTable<UUID> hashTable = new HashTable<>();

		UUID element = UUID.randomUUID();
		hashTable.insert(element);

		UUID inserted = hashTable.search(element);
		assertThat(inserted, is(element));
	}
}
