package org.khanhpdt.playgrounds.datastructures.queues;

import org.khanhpdt.playgrounds.datastructures.linkedlists.DoublyLinkedNode;

import java.util.List;

/**
 * @author khanhpdt
 */
public class Deque extends Queue {

	public void enqueueFront(DoublyLinkedNode node) {
		DoublyLinkedNode currentFront = getFront();

		node.setNext(currentFront);
		if (currentFront != null) {
			currentFront.setPrevious(node);
		}
		setFront(node);

		if (getRear() == null) {
			setRear(node);
		}
	}

	public DoublyLinkedNode dequeueRear() {
		DoublyLinkedNode currentRear = getRear();

		if (currentRear == null) {
			return null;
		}

		DoublyLinkedNode newRear = currentRear.getPrevious();
		if (newRear != null) {
			newRear.setNext(null);
		} else {
			// out of items
			setFront(null);
		}
		setRear(newRear);

		return currentRear;
	}

	public static Deque from(List<DoublyLinkedNode> nodes) {
		Deque deque = new Deque();
		nodes.forEach(deque::enqueueRear);
		return deque;
	}
}
