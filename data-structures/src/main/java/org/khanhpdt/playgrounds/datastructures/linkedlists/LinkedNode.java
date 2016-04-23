package org.khanhpdt.playgrounds.datastructures.linkedlists;

import java.util.UUID;

/**
 * @param <N> type of the next node of this node
 * @author khanhpdt
 */
public abstract class LinkedNode<N extends LinkedNode> {

	private final UUID key;

	private N next;

	protected LinkedNode(UUID key) {
		this.key = key;
	}

	public UUID getKey() {
		return key;
	}

	public N getNext() {
		return next;
	}

	public void setNext(N next) {
		this.next = next;
	}

	@Override
	public int hashCode() {
		int result = 17;

		int c = this.getKey().hashCode();

		return 31 + result * c;
	}
}
