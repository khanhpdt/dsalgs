package org.khanhpdt.playgrounds.datastructures.linkedlists;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.TestUtils;
import org.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * @author khanhpdt
 */
public class DoublyLinkedListTest {

	@Test
	public void testInsert() throws Exception {
		DoublyLinkedList linkedList = new DoublyLinkedList();

		DoublyLinkedNode firstInsertedNode = DoublyLinkedNode.random();
		linkedList.insert(firstInsertedNode);

		DoublyLinkedNode secondInsertedNode = DoublyLinkedNode.random();
		linkedList.insert(secondInsertedNode);

		assertThat("last inserted node becomes the head", linkedList.getHead(), is(secondInsertedNode));
		assertThat("head has no previous node", secondInsertedNode.getPrevious(), is(nullValue()));

		assertThat(secondInsertedNode.getNext(), is(firstInsertedNode));
		assertThat(firstInsertedNode.getPrevious(), is(secondInsertedNode));
	}

	@Test
	public void testRemoveFirstNodes() throws Exception {
		testRemoveAt(0, 1);
	}

	@Test
	public void testRemoveConsecutiveNodes() throws Exception {
		testRemoveAt(3, 4, 5, 6);
	}

	private void testRemoveAt(int... indexes) throws Exception {
		List<DoublyLinkedNode> nodes = TestUtils.randomDoublyNodes(10);
		UUID removeKey = UUID.randomUUID();
		for (int index : indexes) {
			nodes.add(index, new DoublyLinkedNode(removeKey));
		}
		DoublyLinkedList linkedList = DoublyLinkedList.from(nodes);

		linkedList.remove(removeKey);

		CollectionUtils.filter(nodes, n -> !n.getKey().equals(removeKey));
		assertThat("nodes are removed and forward links are set correctly",
				linkedList.traverse(), contains(nodes.toArray()));

		Collections.reverse(nodes);
		assertThat("nodes are removed and backward links are set correctly",
				linkedList.traverseBackward(), contains(nodes.toArray()));
	}

	@Test
	public void testRemoveLastNodes() throws Exception {
		List<DoublyLinkedNode> nodes = TestUtils.randomDoublyNodes(10);

		UUID removeKey = UUID.randomUUID();
		nodes.add(new DoublyLinkedNode(removeKey));
		nodes.add(new DoublyLinkedNode(removeKey));
		nodes.add(new DoublyLinkedNode(removeKey));

		DoublyLinkedList linkedList = DoublyLinkedList.from(nodes);

		linkedList.remove(removeKey);

		CollectionUtils.filter(nodes, n -> !n.getKey().equals(removeKey));
		assertThat("nodes are removed and forward links are set correctly",
				linkedList.traverse(), contains(nodes.toArray()));

		Collections.reverse(nodes);
		assertThat("nodes are removed and backward links are set correctly",
				linkedList.traverseBackward(), contains(nodes.toArray()));
	}
}
