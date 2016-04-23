package org.khanhpdt.playgrounds.datastructures.linkedlists;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class SinglyLinkedNode extends LinkedNode<SinglyLinkedNode> {

	public SinglyLinkedNode(UUID key) {
		super(key);
	}

	public static SinglyLinkedNode random() {
		UUID randomUuid = UUID.randomUUID();
		return new SinglyLinkedNode(randomUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SinglyLinkedNode)) {
			return false;
		}

		SinglyLinkedNode otherNode = (SinglyLinkedNode) obj;
		return this.getKey().equals(otherNode.getKey());
	}

}
