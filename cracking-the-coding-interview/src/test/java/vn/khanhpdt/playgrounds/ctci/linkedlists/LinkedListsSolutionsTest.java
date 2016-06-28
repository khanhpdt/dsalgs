package vn.khanhpdt.playgrounds.ctci.linkedlists;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.LinkedLists;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * @author khanhpdt
 */
public class LinkedListsSolutionsTest {

	@Test
	public void testRemoveDuplicates() {
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

		LinkedListsSolutions.removeDuplicates(list);

		List<SinglyLinkedNode> nodes = LinkedLists.traverse(list);
		assertThat(nodes, hasSize(3));
	}

	@Test
	public void testRemoveDuplicates_2() {
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

		LinkedListsSolutions.removeDuplicates_2(list);

		List<SinglyLinkedNode> nodes = LinkedLists.traverse(list);
		assertThat(nodes, hasSize(3));
	}

}