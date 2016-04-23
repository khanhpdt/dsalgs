package org.khanhpdt.playgrounds.datastructures.linkedlists;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class DoublyLinkedNode extends LinkedNode<DoublyLinkedNode> {

	private DoublyLinkedNode previous;

	public DoublyLinkedNode(UUID key) {
		super(key);
	}

	public static DoublyLinkedNode random() {
		UUID randomUuid = UUID.randomUUID();
		return new DoublyLinkedNode(randomUuid);
	}

	public DoublyLinkedNode getPrevious() {
		return previous;
	}

	public void setPrevious(DoublyLinkedNode previous) {
		this.previous = previous;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DoublyLinkedNode)) {
			return false;
		}

		DoublyLinkedNode otherNode = (DoublyLinkedNode) obj;
		return this.getKey().equals(otherNode.getKey());
	}

}
