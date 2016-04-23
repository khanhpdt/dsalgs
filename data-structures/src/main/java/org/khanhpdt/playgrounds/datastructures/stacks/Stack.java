package org.khanhpdt.playgrounds.datastructures.stacks;

import org.khanhpdt.playgrounds.datastructures.linkedlists.LinkedNode;

/**
 * @param <N> type of node
 * @author khanhpdt
 */
public class Stack<N extends LinkedNode<N>> {

	private N head;

	public void push(N node) {
		node.setNext(head);
		head = node;
	}

	public N pop() {
		if (head == null) {
			return null;
		}

		N currentHead = head;
		head = head.getNext();
		return currentHead;
	}

	public N peek() {
		return head;
	}

	public boolean isEmpty() {
		return head == null;
	}

}
