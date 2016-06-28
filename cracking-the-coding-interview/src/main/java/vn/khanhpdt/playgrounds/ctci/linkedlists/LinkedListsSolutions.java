package vn.khanhpdt.playgrounds.ctci.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author khanhpdt
 */
public class LinkedListsSolutions {

	/**
	 * Problem 2.1.
	 *
	 * <p>Worst-case complexity: O(n^2), where n is the length of the list.</p>
	 */
	public static void removeDuplicates(SinglyLinkedList<SinglyLinkedNode> list) {
		SinglyLinkedNode current = list.getHead();
		while (current != null) {
			SinglyLinkedNode previous = current;
			SinglyLinkedNode item = current.getNext();
			while (item != null) {
				// remove the duplicated
				if (item.equals(current)) {
					previous.setNext(item.getNext());
				} else {
					previous = item;
				}
				item = item.getNext();
			}
			current = current.getNext();
		}
	}

	/**
	 * Problem 2.1.
	 *
	 * <ul>
	 *     <li>Worst-case complexity: O(n), where n is the length of the given list.</li>
	 *     <li>Needs extra memory for the set.</li>
	 * </ul>
	 */
	public static void removeDuplicates_2(SinglyLinkedList<SinglyLinkedNode> list) {
		// This set contains distinct list items.
		// To achieve O(n), it must take O(1) time to check if an item is in the set.
		Set<SinglyLinkedNode> distinctItems = new HashSet<>();

		SinglyLinkedNode previous = null;
		SinglyLinkedNode current = list.getHead();
		while (current != null) {
			if (!distinctItems.contains(current)) {
				distinctItems.add(current);

				previous = current;
				current = current.getNext();
			} else {
				remove(list, previous, current);

				current = current.getNext();
			}
		}
	}

	private static void remove(SinglyLinkedList<SinglyLinkedNode> list, SinglyLinkedNode previous, SinglyLinkedNode current) {
		// head is being removed
		if (previous == null) {
			list.setHead(current.getNext());
		} else {
			previous.setNext(current.getNext());
		}
	}

	/**
	 * Problem 2.2.
	 * <ul>
	 *     <li>Worst-case complexity: O(n), where n is the length of the given list.</li>
	 * </ul>
	 */
	public static SinglyLinkedNode getKthToLast(SinglyLinkedList<SinglyLinkedNode> list, int k) {
		return list.getKthToLast(k);
	}

	/**
	 * Problem 2.3.
	 *
	 * <ul>
	 *     <li>Worst-case complexity: O(1)</li>
	 * </ul>
	 */
	public static void removeMiddleItem(SinglyLinkedNode removedItem) {
		SinglyLinkedNode next = removedItem.getNext();

		// this remove method should only apply to the middle items
		assert next != null;

		// fake the removed item as its next item -> the current item now acts like its next item
		removedItem.cloneContent(next);
		next.removeContent();

		// cut the next item out from the list
		removedItem.setNext(next.getNext());
	}
}