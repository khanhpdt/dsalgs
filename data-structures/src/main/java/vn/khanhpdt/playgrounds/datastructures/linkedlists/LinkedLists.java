package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.nodes.LinkedNodeIntf;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class LinkedLists {

	public static <N extends LinkedNodeIntf<N>> List<N> traverse(LinkedList<N> linkedList) {
		List<N> nodes = new ArrayList<>();

		N currentNode = linkedList.getHead();
		while (currentNode != null) {
			nodes.add(currentNode);
			currentNode = currentNode.getNext();
		}

		return nodes;
	}

	public static <N extends LinkedNodeIntf<N>> void removeAll(LinkedList<N> linkedList, UUID removeKey) {
		// NOTE: duplicated nodes are allowed in the list and this method will remove
		// all nodes with the same key as the given key

		// if the first nodes are to be removed, the head needs to be relocated
		N head = linkedList.getHead();
		while (head != null && head.getKey().equals(removeKey)) {
			head = head.getNext();
			linkedList.setHead(head);
		}

		// now the head can be either null or points to a node with a key different than the given key. so we can skip the head.
		N currentNode = head == null ? null : head.getNext();
		N previousNode = head;
		while (currentNode != null) {
			if (currentNode.getKey().equals(removeKey)) {
				// no link to deleted node
				previousNode.setNext(currentNode.getNext());
				// move the currentNode. the previousNode stays where it is, as it still is the previous
				// node of the currentNode after moved.
				currentNode = currentNode.getNext();
			} else {
				previousNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
	}


	public static <N extends LinkedNodeIntf<N>> void remove(LinkedList<N> linkedList, UUID removeKey) {
		// if the first nodes are to be removed, the head needs to be relocated
		N head = linkedList.getHead();
		if (head != null && head.getKey().equals(removeKey)) {
			head = head.getNext();
			linkedList.setHead(head);
			return;
		}

		// no node left to process
		if (head == null) {
			return;
		}

		N previous = head;
		N current = head.getNext();
		while (current != null) {
			if (current.getKey().equals(removeKey)) {
				previous.setNext(current.getNext());
				// remove once
				break;
			}
			previous = current;
			current = current.getNext();
		}
	}
}
