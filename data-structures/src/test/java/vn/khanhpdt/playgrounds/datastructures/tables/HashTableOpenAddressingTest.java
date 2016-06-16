package vn.khanhpdt.playgrounds.datastructures.tables;

import org.hamcrest.Matchers;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.tables.probings.ProbingMethodName;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class HashTableOpenAddressingTest {

	private Random random = new Random();

	@Test
	public void testInsert() throws Exception {
		HashTableOpenAddressing hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);

		Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());

		hashTable.insert(item);

		Node<UUID, Integer> inserted = hashTable.search(item.getKey());
		assertThat(inserted, is(item));
	}

	@Test
	public void testInsertMultiple() throws Exception {
		HashTableOpenAddressing hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);

		for (int i = 0; i < 11; i ++) {
			Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());

			hashTable.insert(item);

			Node<UUID, Integer> inserted = hashTable.search(item.getKey());
			assertThat(inserted, is(item));
		}
	}

	@Test
	public void testSize() throws Exception {
		HashTableOpenAddressing hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);
		int size = 11;

		for (int i = 0; i < size; i ++) {
			Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());
			hashTable.insert(item);
		}

		assertThat(hashTable.size(), is(size));
	}

	@Test
	public void testRemove() throws Exception {
		HashTableOpenAddressing hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);
		Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());
		hashTable.insert(item);

		hashTable.remove(item.getKey());

		assertThat(hashTable.search(item.getKey()), Matchers.nullValue());
	}

}
