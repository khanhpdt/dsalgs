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

	private static final int DEFAULT_CAPACITY = 11;

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

		for (int i = 0; i < DEFAULT_CAPACITY; i ++) {
			Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());

			hashTable.insert(item);

			Node<UUID, Integer> inserted = hashTable.search(item.getKey());
			assertThat(inserted, is(item));
		}
	}

	@Test
	public void testSizeIncreased() throws Exception {
		int size = DEFAULT_CAPACITY;

		HashTableOpenAddressing hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);
		for (int i = 0; i < size; i ++) {
			Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());
			hashTable.insert(item);
		}

		assertThat(hashTable.size(), is(size));
	}

	@Test
	public void testSizeDecreased() throws Exception {
		HashTableOpenAddressing hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);
		Node<UUID, Integer> item1 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(hashTable, item1);
		Node<UUID, Integer> item2 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(hashTable, item2);
		Node<UUID, Integer> item3 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(hashTable, item3);

		assertThat(hashTable.size(), is(3));

		remove(hashTable, item3);
		remove(hashTable, item2);

		assertThat(hashTable.size(), is(1));
	}

	@Test
	public void testRemove() throws Exception {
		HashTableOpenAddressing hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);
		Node<UUID, Integer> item = new Node<>(UUID.randomUUID(), random.nextInt());
		hashTable.insert(item);

		hashTable.remove(item.getKey());

		assertThat(hashTable.search(item.getKey()), Matchers.nullValue());
	}

	@Test
	public void testCompositeActions() throws Exception {
		HashTableOpenAddressing hashTable = new HashTableOpenAddressing(ProbingMethodName.LINEAR_PROBING);

		Node<UUID, Integer> item1 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(hashTable, item1);
		Node<UUID, Integer> item2 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(hashTable, item2);
		Node<UUID, Integer> item3 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(hashTable, item3);

		remove(hashTable, item3);
		remove(hashTable, item1);

		Node<UUID, Integer> item4 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(hashTable, item4);
		Node<UUID, Integer> item5 = new Node<>(UUID.randomUUID(), random.nextInt());
		insertTo(hashTable, item5);

		remove(hashTable, item2);
		remove(hashTable, item4);
		remove(hashTable, item5);
	}

	private void remove(HashTableOpenAddressing hashTable, Node<UUID, Integer> item) {
		hashTable.remove(item.getKey());
		assertThat(hashTable.search(item.getKey()), Matchers.nullValue());
	}

	private void insertTo(HashTableOpenAddressing hashTable, Node<UUID, Integer> item) {
		hashTable.insert(item);
		assertThat(hashTable.search(item.getKey()), is(item));
	}

}
