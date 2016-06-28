package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.nodes.LinkedNodeIntf;

import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class SinglyLinkedList<N extends LinkedNodeIntf<N>> implements LinkedList<N> {

	private N head;

	public void insert(N node) {
		// new node is inserted at the beginning of the list
		node.setNext(head);
		// new node becomes the new head
		head = node;
	}

	public N search(UUID nodeKey) {
		N currentNode = this.head;
		while (currentNode != null) {
			// found
			if (currentNode.getKey().equals(nodeKey)) {
				break;
			}
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	public void removeAll(UUID removeKey) {
		LinkedLists.removeAll(this, removeKey);
	}

	public void remove(UUID removeKey) {
		LinkedLists.remove(this, removeKey);
	}

	@Override
	public N getHead() {
		return head;
	}

	@Override
	public void setHead(N head) {
		this.head = head;
	}

	public static <N extends LinkedNodeIntf<N>> SinglyLinkedList<N> from(List<N> nodes) {
		SinglyLinkedList<N> linkedList = new SinglyLinkedList<>();
		for (int i = nodes.size() - 1; i >= 0; i--) {
			linkedList.insert(nodes.get(i));
		}
		return linkedList;
	}

	public N getKthToLast(int k) {
		int size = size();
		return getKthToLast(k, size);
	}

	private N getKthToLast(int k, int size) {
		if (k < 0 || k > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		return get(size - 1 - k, size);
	}

	private N get(int k, int size) {
		if (k < 0 || k > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		N current = head;
		for (int i = 1; i <= k; i++) {
			current = current.getNext();
		}
		return current;
	}

	private int size() {
		int result = 0;
		N current = head;
		while (current != null) {
			result++;
			current = current.getNext();
		}
		return result;
	}


}
