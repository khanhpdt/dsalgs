package org.khanhpdt.playgrounds.datastructures.linkedlists;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.TestUtils;
import org.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.khanhpdt.playgrounds.datastructures.linkedlists.LinkedLists.traverse;

/**
 * @author khanhpdt
 */
public class DoubleEndedLinkedListTest {

	@Test
	public void testInsertFirstToEmptyList() throws Exception {
		DoubleEndedLinkedList linkedList = new DoubleEndedLinkedList();

		SinglyLinkedNode newNode = SinglyLinkedNode.random();
		linkedList.insertFirst(newNode);

		assertThat("head points to the first item", linkedList.getHead(), is(newNode));
		assertThat("tail points to the first item", linkedList.getTail(), is(newNode));
	}

	@Test
	public void testInsertFirstToNonEmptyList() throws Exception {
		DoubleEndedLinkedList linkedList = new DoubleEndedLinkedList();

		SinglyLinkedNode firstNode = SinglyLinkedNode.random();
		linkedList.insertFirst(firstNode);

		SinglyLinkedNode secondNode = SinglyLinkedNode.random();
		linkedList.insertFirst(secondNode);

		assertThat(linkedList.getHead(), is(secondNode));
		assertThat(linkedList.getTail(), is(firstNode));
	}

	@Test
	public void testInsertLastToEmptyList() throws Exception {
		DoubleEndedLinkedList linkedList = new DoubleEndedLinkedList();

		SinglyLinkedNode newNode = SinglyLinkedNode.random();
		linkedList.insertLast(newNode);

		assertThat("head points to the first item", linkedList.getHead(), is(newNode));
		assertThat("tail points to the first item", linkedList.getTail(), is(newNode));
	}

	@Test
	public void testInsertLastToNonEmptyList() throws Exception {
		DoubleEndedLinkedList linkedList = new DoubleEndedLinkedList();

		SinglyLinkedNode firstNode = SinglyLinkedNode.random();
		linkedList.insertFirst(firstNode);

		SinglyLinkedNode secondNode = SinglyLinkedNode.random();
		linkedList.insertLast(secondNode);

		assertThat(linkedList.getHead(), is(firstNode));
		assertThat(linkedList.getTail(), is(secondNode));
	}

	@Test
	public void testRemove() throws Exception {
		List<SinglyLinkedNode> nodes = TestUtils.randomSinglyNodes(10);

		UUID removeKey = UUID.randomUUID();
		nodes.add(0, new SinglyLinkedNode(removeKey));
		nodes.add(7, new SinglyLinkedNode(removeKey));
		nodes.add(new SinglyLinkedNode(removeKey));

		DoubleEndedLinkedList linkedList = DoubleEndedLinkedList.from(nodes);

		linkedList.remove(removeKey);

		CollectionUtils.filter(nodes, n -> !n.getKey().equals(removeKey));

		assertThat("duplicated key nodes are removed", traverse(linkedList), contains(nodes.toArray()));
		assertThat("correct tail", linkedList.getTail(), is(nodes.get(nodes.size() - 1)));
	}

}
