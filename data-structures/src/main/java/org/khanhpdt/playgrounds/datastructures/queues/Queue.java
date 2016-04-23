package org.khanhpdt.playgrounds.datastructures.queues;

import org.khanhpdt.playgrounds.datastructures.linkedlists.DoublyLinkedNode;

import java.util.List;

/**
 * An implementation of queues by using linked list.
 *
 * @author khanhpdt
 */
public class Queue {

	private DoublyLinkedNode front;

	private DoublyLinkedNode rear;

	public void enqueueRear(DoublyLinkedNode node) {
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

	public DoublyLinkedNode dequeueFront() {
		DoublyLinkedNode currentFront = front;

		if (currentFront == null) {
			return null;
		}

		DoublyLinkedNode newFront = front.getNext();
		if (newFront != null) {
			newFront.setPrevious(null);
		} else {
			// out of items
			rear = null;
		}
		setFront(newFront);

		return currentFront;
	}

	public static Queue from(List<DoublyLinkedNode> nodes) {
		Queue queue = new Queue();
		nodes.forEach(queue::enqueueRear);
		return queue;
	}

	public DoublyLinkedNode getFront() {
		return front;
	}

	public void setFront(DoublyLinkedNode front) {
		this.front = front;
	}

	public DoublyLinkedNode getRear() {
		return rear;
	}

	public void setRear(DoublyLinkedNode rear) {
		this.rear = rear;
	}
}
