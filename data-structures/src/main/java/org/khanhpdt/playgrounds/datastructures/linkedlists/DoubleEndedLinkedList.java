package org.khanhpdt.playgrounds.datastructures.linkedlists;

import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class DoubleEndedLinkedList implements LinkedList<SinglyLinkedNode> {

	private SinglyLinkedNode head;

	private SinglyLinkedNode tail;

	public void insertFirst(SinglyLinkedNode node) {
		node.setNext(head);
		head = node;

		if (tail == null) {
			tail = node;
		}
	}

	public void insertLast(SinglyLinkedNode node) {
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

	private SinglyLinkedNode getLastNode() {
		if (head == null) {
			return null;
		}

		SinglyLinkedNode currentNode = head;
		// only last node has null "next" reference
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	@Override
	public SinglyLinkedNode getHead() {
		return head;
	}

	@Override
	public void setHead(SinglyLinkedNode head) {
		this.head = head;
	}

	public SinglyLinkedNode getTail() {
		return tail;
	}

	public static DoubleEndedLinkedList from(List<SinglyLinkedNode> nodes) {
		DoubleEndedLinkedList linkedList = new DoubleEndedLinkedList();
		for (int i = nodes.size() - 1; i >= 0; i--) {
			linkedList.insertFirst(nodes.get(i));
		}
		return linkedList;
	}
}
