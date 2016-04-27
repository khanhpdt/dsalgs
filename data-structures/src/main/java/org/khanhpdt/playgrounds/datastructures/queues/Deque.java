package org.khanhpdt.playgrounds.datastructures.queues;

import org.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNodeIntf;

import java.util.List;

/**
 * @author khanhpdt
 */
public class Deque<N extends DoublyLinkedNodeIntf<N>> extends Queue<N> {

	public void enqueueFront(N node) {
		N currentFront = getFront();

		node.setNext(currentFront);
		if (currentFront != null) {
			currentFront.setPrevious(node);
		}
		setFront(node);

		if (getRear() == null) {
			setRear(node);
		}
	}

	public N dequeueRear() {
		N currentRear = getRear();

		if (currentRear == null) {
			return null;
		}

		N newRear = currentRear.getPrevious();
		if (newRear != null) {
			newRear.setNext(null);
		} else {
			// out of items
			setFront(null);
		}
		setRear(newRear);

		return currentRear;
	}

	public static <N extends DoublyLinkedNodeIntf<N>> Deque<N> from(List<N> nodes) {
		Deque<N> deque = new Deque<>();
		nodes.forEach(deque::enqueueRear);
		return deque;
	}
}
