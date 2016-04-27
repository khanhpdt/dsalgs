package org.khanhpdt.playgrounds.datastructures.linkedlists;

import org.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class SinglyLinkedList implements LinkedList<SinglyLinkedNode> {

	private SinglyLinkedNode head;

	public void insert(SinglyLinkedNode node) {
		// new node is inserted at the beginning of the list
		node.setNext(head);
		// new node becomes the new head
		head = node;
	}

	public SinglyLinkedNode search(UUID nodeKey) {
		SinglyLinkedNode currentNode = this.head;
		while (currentNode != null) {
			// found
			if (currentNode.getKey().equals(nodeKey)) {
				break;
			}
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	public void remove(UUID removeKey) {
		LinkedLists.remove(this, removeKey);
	}

	@Override
	public SinglyLinkedNode getHead() {
		return head;
	}

	@Override
	public void setHead(SinglyLinkedNode head) {
		this.head = head;
	}

	public static SinglyLinkedList from(List<SinglyLinkedNode> nodes) {
		SinglyLinkedList linkedList = new SinglyLinkedList();
		for (int i = nodes.size() - 1; i >= 0; i--) {
			linkedList.insert(nodes.get(i));
		}
		return linkedList;
	}
}
