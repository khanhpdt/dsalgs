package vn.khanhpdt.playgrounds.ctci.linkedlists;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.LinkedLists;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class LinkedListsSolutionsTest {

	@Test
	public void testRemoveDuplicates() {
		SinglyLinkedList<SinglyLinkedNode> list = createSinglyLinkedListWithDuplicates();

		LinkedListsSolutions.removeDuplicates(list);

		List<SinglyLinkedNode> nodes = LinkedLists.traverse(list);
		assertThat(nodes, hasSize(3));
	}

	private SinglyLinkedList<SinglyLinkedNode> createSinglyLinkedListWithDuplicates() {
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		UUID uuid3 = UUID.randomUUID();

		SinglyLinkedList<SinglyLinkedNode> list = new SinglyLinkedList<>();
		list.insert(new SinglyLinkedNode(uuid1));
		list.insert(new SinglyLinkedNode(uuid2));
		list.insert(new SinglyLinkedNode(uuid3));
		list.insert(new SinglyLinkedNode(uuid1));
		list.insert(new SinglyLinkedNode(uuid2));
		list.insert(new SinglyLinkedNode(uuid3));

		return list;
	}

	@Test
	public void testRemoveDuplicates_2() {
		SinglyLinkedList<SinglyLinkedNode> list = createSinglyLinkedListWithDuplicates();

		LinkedListsSolutions.removeDuplicates_2(list);

		List<SinglyLinkedNode> nodes = LinkedLists.traverse(list);
		assertThat(nodes, hasSize(3));
	}

	@Test
	public void testGetKthToLast() {
		int nItems = 10;
		List<SinglyLinkedNode> items = new ArrayList<>();
		IntStream.range(0, nItems).forEach(i -> items.add(new SinglyLinkedNode(UUID.randomUUID())));

		SinglyLinkedList<SinglyLinkedNode> list = new SinglyLinkedList<>();
		items.forEach(list::insert);

		for (int k = 0; k < nItems; k++) {
			SinglyLinkedNode item = LinkedListsSolutions.getKthToLast(list, k);
			assertThat(item, is(items.get(k)));
		}

	}

}