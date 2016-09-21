package vn.khanhpdt.playgrounds.datastructures.stacks;

import vn.khanhpdt.playgrounds.datastructures.nodes.LinkedNodeIntf;

import java.util.UUID;

/**
 * @param <N> type of node
 * @author khanhpdt
 */
public class Stack<N extends LinkedNodeIntf<N>> {

	// used to identify this stack
	private final UUID uuid;

	private N head;

	private int size;

	public Stack() {
		this.uuid = UUID.randomUUID();
		this.head = null;
		this.size = 0;
	}

	public void push(N node) {
		node.setNext(head);
		head = node;

		size++;
	}

	public N pop() {
		if (head == null) {
			return null;
		}

		N currentHead = head;
		head = head.getNext();

		size--;

		return currentHead;
	}

	public N peek() {
		return head;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return size;
	}


}
