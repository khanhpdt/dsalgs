package org.khanhpdt.playgrounds.datastructures.linkedlists;

import org.khanhpdt.playgrounds.datastructures.nodes.LinkedNodeIntf;
import org.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class DoubleEndedLinkedList<N extends LinkedNodeIntf<N>> implements LinkedList<N> {

	private N head;

	private N tail;

	public void insertFirst(N node) {
		node.setNext(head);
		head = node;

		if (tail == null) {
			tail = node;
		}
	}

	public void insertLast(N node) {
		if (tail != null) {
			tail.setNext(node);
		}
		tail = node;

		if (head == null) {
			head = node;
		}
	}

	public void remove(UUID removeKey) {
		LinkedLists.remove(this, removeKey);
		tail = getLastNode();
	}

	private N getLastNode() {
		if (head == null) {
			return null;
		}

		N currentNode = head;
		// only last node has null "next" reference
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	@Override
	public N getHead() {
		return head;
	}

	@Override
	public void setHead(N head) {
		this.head = head;
	}

	public N getTail() {
		return tail;
	}

	public static DoubleEndedLinkedList<SinglyLinkedNode> from(List<SinglyLinkedNode> nodes) {
		DoubleEndedLinkedList<SinglyLinkedNode> linkedList = new DoubleEndedLinkedList<>();
		for (int i = nodes.size() - 1; i >= 0; i--) {
			linkedList.insertFirst(nodes.get(i));
		}
		return linkedList;
	}
}
