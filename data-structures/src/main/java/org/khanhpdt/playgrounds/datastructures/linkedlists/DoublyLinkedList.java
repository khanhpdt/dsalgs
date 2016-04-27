package org.khanhpdt.playgrounds.datastructures.linkedlists;

import org.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class DoublyLinkedList implements LinkedList<DoublyLinkedNode> {

	private DoublyLinkedNode head;

	public void insert(DoublyLinkedNode node) {
		// link nodes together
		if (head != null) {
			head.setPrevious(node);
		}
		node.setNext(head);

		// set new head
		head = node;
		head.setPrevious(null);
	}

	@Override
	public DoublyLinkedNode getHead() {
		return head;
	}

	@Override
	public void setHead(DoublyLinkedNode head) {
		this.head = head;
	}

	public static DoublyLinkedList from(List<DoublyLinkedNode> nodes) {
		DoublyLinkedList linkedList = new DoublyLinkedList();
		for (int i = nodes.size() - 1; i >= 0; i--) {
			linkedList.insert(nodes.get(i));
		}
		return linkedList;
	}

	public void remove(UUID removeKey) {
		while (head != null && head.getKey().equals(removeKey)) {
			head = head.getNext();
			if (head != null) {
				head.setPrevious(null);
			}
		}

		DoublyLinkedNode currentNode = head;
		while (currentNode != null) {
			if (currentNode.getKey().equals(removeKey)) {
				currentNode.getPrevious().setNext(currentNode.getNext());
				if (currentNode.getNext() != null) {
					currentNode.getNext().setPrevious(currentNode.getPrevious());
				}
			}
			currentNode = currentNode.getNext();
		}
	}

	public List<DoublyLinkedNode> traverse() {
		return LinkedLists.traverse(this);
	}

	public List<DoublyLinkedNode> traverseBackward() {
		List<DoublyLinkedNode> result = new ArrayList<>();

		// let the current node the last node and we traverse from there using the backward link
		List<DoublyLinkedNode> linkedNodes = traverse();
		DoublyLinkedNode currentNode = linkedNodes.get(linkedNodes.size() - 1);
		while (currentNode != null) {
			result.add(currentNode);
			currentNode = currentNode.getPrevious();
		}

		return result;
	}
}
