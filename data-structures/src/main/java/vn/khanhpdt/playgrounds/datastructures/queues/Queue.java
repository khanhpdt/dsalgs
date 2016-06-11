package vn.khanhpdt.playgrounds.datastructures.queues;

import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNodeIntf;

import java.util.List;

/**
 * An implementation of queues using linked lists.
 *
 * @author khanhpdt
 */
public class Queue<N extends DoublyLinkedNodeIntf<N>> {

	private N front;

	private N rear;

	public void enqueueRear(N node) {
		if (rear != null) {
			rear.setNext(node);
			node.setPrevious(rear);
		}

		rear = node;
		rear.setNext(null);

		if (front == null) {
			front = node;
		}
	}

	public N dequeueFront() {
		N currentFront = front;

		if (currentFront == null) {
			return null;
		}

		N newFront = front.getNext();
		if (newFront != null) {
			newFront.setPrevious(null);
		} else {
			// out of items
			rear = null;
		}
		setFront(newFront);

		return currentFront;
	}

	public static <N extends DoublyLinkedNodeIntf<N>> Queue from(List<N> nodes) {
		Queue<N> queue = new Queue<>();
		nodes.forEach(queue::enqueueRear);
		return queue;
	}

	public N getFront() {
		return front;
	}

	public void setFront(N front) {
		this.front = front;
	}

	public N getRear() {
		return rear;
	}

	public void setRear(N rear) {
		this.rear = rear;
	}

	public boolean isEmpty() {
		return front == null;
	}
}
