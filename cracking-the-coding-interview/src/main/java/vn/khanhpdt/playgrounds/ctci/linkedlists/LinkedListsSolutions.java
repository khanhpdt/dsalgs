package vn.khanhpdt.playgrounds.ctci.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.LinkedList;
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
	public static void removeDuplicates(LinkedList<SinglyLinkedNode> list) {
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
	 * <p>Worst-case complexity: O(n), where n is the length of the given list</p>
	 */
	public static void removeDuplicates_2(SinglyLinkedList<SinglyLinkedNode> list) {
		// this set contains distinct list items
		Set<SinglyLinkedNode> distinctItems = new HashSet<>();
		SinglyLinkedNode current = list.getHead();
		while (current != null) {
			if (!distinctItems.contains(current)) {
				distinctItems.add(current);
			} else {
				list.remove(current.getKey());
			}
			current = current.getNext();
		}
	}
}