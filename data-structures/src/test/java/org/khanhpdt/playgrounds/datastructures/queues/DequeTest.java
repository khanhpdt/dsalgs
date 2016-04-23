package org.khanhpdt.playgrounds.datastructures.queues;

import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.TestUtils;
import org.khanhpdt.playgrounds.datastructures.linkedlists.DoublyLinkedNode;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class DequeTest {

	@Test
	public void testEnqueueFrontToEmptyQueue() throws Exception {
		Deque queue = new Deque();

		DoublyLinkedNode newNode = DoublyLinkedNode.random();
		queue.enqueueFront(newNode);

		assertThat(queue.getRear(), is(newNode));
		assertThat(queue.getFront(), is(newNode));
	}

	@Test
	public void testEnqueueFrontToNonEmptyQueue() throws Exception {
		Deque queue = new Deque();

		DoublyLinkedNode firstInsertedNode = DoublyLinkedNode.random();
		queue.enqueueFront(firstInsertedNode);

		DoublyLinkedNode secondInsertedNode = DoublyLinkedNode.random();
		queue.enqueueFront(secondInsertedNode);

		assertThat(queue.getFront(), is(secondInsertedNode));
	}

	@Test
	public void testDequeueRear() throws Exception {
		List<DoublyLinkedNode> nodes = TestUtils.randomDoublyNodes(10);
		Deque queue = Deque.from(nodes);
		assertThat(queue.dequeueRear(), is(nodes.get(nodes.size() - 1)));
	}

	@Test
	public void testEnqueueFrontAndDequeueRear() throws Exception {
		List<DoublyLinkedNode> nodes = TestUtils.randomDoublyNodes(10);

		Deque queue = new Deque();
		nodes.forEach(queue::enqueueFront);

		nodes.forEach(n -> assertThat(queue.dequeueRear(), is(n)));
	}
}
