package vn.khanhpdt.playgrounds.ctci.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.DoublyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;
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
		SinglyLinkedNode nextItem = removedItem.getNext();

		// this remove method should only apply to the middle items
		assert nextItem != null;

		// fake the removed item as its next item -> the current item now acts like its next item
		removedItem.cloneContent(nextItem);
		nextItem.removeContent();

		// cut the next item out from the list
		removedItem.setNext(nextItem.getNext());
	}

	/**
	 * Problem 2.4.
	 *
	 * <ul>
	 *     <li>Worst-case complexity: O(n), where n is the length of the given list. The worst case happens when all the
	 *     items in the list are smaller than the given partitioning value, because in that case we have to move all the
	 *     items to the head of the list.</li>
	 * </ul>
	 */
	public static void partition(SinglyLinkedList<SinglyLinkedNode> linkedList, Integer partitioningValue) {
		SinglyLinkedNode head = linkedList.getHead();

		if (head == null) {
			return;
		}

		SinglyLinkedNode previous = head;
		SinglyLinkedNode current = head.getNext();
		while (current != null) {
			// move the smaller nodes to the head of the list
			if (current.getValue() < partitioningValue) {
				previous.setNext(current.getNext());
				current.setNext(head);
				linkedList.setHead(current);
			} else {
				previous = current;
			}

			current = previous.getNext();
		}
	}

	/**
	 * <p>Problem 2.5.1: The digits are stored in backward order, e.g., 617 is stored as 7 -> 1 -> 6.</p>
	 * <ul>
	 *     <li>Worst-case complexity: O(n^2) if the result is a singly linked list, and O(n) if it is a double-ended list ,
	 *     where n is the length of the longer among the given lists.</li>
	 * </ul>
	 */
	public static SinglyLinkedList<SinglyLinkedNode> sumBackwardDigits(SinglyLinkedList<SinglyLinkedNode> firstNumber,
																	   SinglyLinkedList<SinglyLinkedNode> secondNumber) {

		SinglyLinkedList<SinglyLinkedNode> result = new SinglyLinkedList<>();
		SinglyLinkedNode current1 = firstNumber.getHead();
		SinglyLinkedNode current2 = secondNumber.getHead();

		int carry = 0;
		while (current1 != null || current2 != null) {
			int digit1 = current1 == null ? 0 : current1.getValue();
			int digit2 = current2 == null ? 0 : current2.getValue();
			int sum = digit1 + digit2 + carry;

			int digitSum = sum % 10;
			// this takes O(n). we can make it to O(1) by replacing singly linked list by the double-ended one.
			result.insertLast(new SinglyLinkedNode(digitSum));

			carry = sum / 10;

			current1 = current1 == null ? null : current1.getNext();
			current2 = current2 == null ? null : current2.getNext();
		}

		return result;
	}

	/**
	 * <p>Problem 2.5.1: The digits are stored in backward order, e.g., 617 is stored as 7 -> 1 -> 6.</p>
	 * <ul>
	 *     <li>Worst-case complexity: O(n), where n is the length of the longer among the given lists.</li>
	 * </ul>
	 */
	public static SinglyLinkedList<SinglyLinkedNode> sumBackwardDigits_2(SinglyLinkedList<SinglyLinkedNode> firstNumber,
																	   SinglyLinkedList<SinglyLinkedNode> secondNumber) {

		SinglyLinkedList<SinglyLinkedNode> result = new SinglyLinkedList<>();
		SinglyLinkedNode current1 = firstNumber.getHead();
		SinglyLinkedNode current2 = secondNumber.getHead();

		int carry = 0;
		while (current1 != null || current2 != null) {
			int digit1 = current1 == null ? 0 : current1.getValue();
			int digit2 = current2 == null ? 0 : current2.getValue();
			int sum = digit1 + digit2 + carry;

			int digitSum = sum % 10;
			// this takes O(1), but remember to reverse the result before returning it
			result.insertFirst(new SinglyLinkedNode(digitSum));

			carry = sum / 10;

			current1 = current1 == null ? null : current1.getNext();
			current2 = current2 == null ? null : current2.getNext();
		}

		return result.reverse();
	}

	/**
	 * <p>Problem 2.5.2: The digits are stored in forward order, e.g., 617 is stored as 6 -> 1 -> 7.</p>
	 * <p>Solution 1: simply reuse {@link #sumBackwardDigits_2(SinglyLinkedList, SinglyLinkedList)}</p>
	 * <ul>
	 *     <li>Worst-case complexity: O(n), where n is the length of the longer among the given lists.</li>
	 * </ul>
	 */
	public static SinglyLinkedList<SinglyLinkedNode> sumForwardDigits(SinglyLinkedList<SinglyLinkedNode> firstNumber,
																	  SinglyLinkedList<SinglyLinkedNode> secondNumber) {
		return sumBackwardDigits_2(firstNumber.reverse(), secondNumber.reverse()).reverse();
	}

	/**
	 * <p>Problem 2.5.2: The digits are stored in forward order, e.g., 617 is stored as 6 -> 1 -> 7.</p>
	 * <p>Solution 2: use a doubly-linked list so that the carry can be propagated</p>
	 * <ul>
	 *     <li>Worst-case complexity: O(n^2), where n is the length of the longer among the given lists. The worst case
	 *     happens when the carry is propagated all the way back for every calculation.</li>
	 * </ul>
	 */
	public static DoublyLinkedList sumForwardDigits_2(SinglyLinkedList<SinglyLinkedNode> firstNumber,
	                                                                    SinglyLinkedList<SinglyLinkedNode> secondNumber) {
		DoublyLinkedList result = new DoublyLinkedList();

		int firstLength = firstNumber.size();
		int secondLength = secondNumber.size();

		// find out the longer and shorter number
		SinglyLinkedList<SinglyLinkedNode> longNumber;
		SinglyLinkedList<SinglyLinkedNode> shortNumber;
		if (firstLength >= secondLength) {
			longNumber = firstNumber;
			shortNumber = secondNumber;
		} else {
			longNumber = secondNumber;
			shortNumber = firstNumber;
		}

		// handle the length difference
		SinglyLinkedNode longNumberCurrent = longNumber.getHead();
		for (int i = 0; i < firstLength - secondLength; i++) {
			result.insert(new DoublyLinkedNode(longNumberCurrent.getValue()));
			longNumberCurrent = longNumberCurrent.getNext();
		}
		SinglyLinkedNode shortNumberCurrent = shortNumber.getHead();

		// start calculating the sum
		while (longNumberCurrent != null && shortNumberCurrent != null) {
			int sum = longNumberCurrent.getValue() + shortNumberCurrent.getValue();

			int carry = sum / 10;
			if (carry > 0) {
				// propagate carry to higher-order positions in the result
				DoublyLinkedNode resultCurrent = result.getHead();
				DoublyLinkedNode resultPrevious = null;
				while (carry > 0) {
					// propagating value to the higher-order positions compared to the current sum-position
					if (resultCurrent != null) {
						int newValue = resultCurrent.getContent().getValue() + carry;
						resultCurrent.getContent().setValue(newValue % 10);

						carry = newValue / 10;
						resultPrevious = resultCurrent;
						resultCurrent = resultCurrent.getNext();
					}
					// when applying the carry to the highest-order position produces a new carry
					else if (resultPrevious != null){
						result.insertNextTo(carry, resultPrevious);
						break;
					}
					// when the first sum produces carry
					else {
						result.insert(new DoublyLinkedNode(carry));
						break;
					}
				}
			}

			result.insert(new DoublyLinkedNode(sum % 10));

			longNumberCurrent = longNumberCurrent.getNext();
			shortNumberCurrent = shortNumberCurrent.getNext();
		}

		return result.reverse();
	}

	/**
	 * Problem 2.6.
	 *
	 * <ul>
	 *     <li>Worst-case complexity: O(n), where n is the length of the given list.</li>
	 *     <li>Need O(n) extra memory</li>
	 * </ul>
	 */
	public static SinglyLinkedNode getFirstOfTheLoop(SinglyLinkedList<SinglyLinkedNode> linkedList) {
		// this set needs O(n) memory
		Set<SinglyLinkedNode> existingNodes = new HashSet<>();
		SinglyLinkedNode current = linkedList.getHead();
		while (current != null) {
			boolean newNodeAdded = existingNodes.add(current);
			// first duplicated node is the first node of the first loop in the list.
			if (!newNodeAdded) {
				return current;
			}
			current = current.getNext();
		}
		// list has no loop
		return null;
	}

	/**
	 * Problem 2.7.
	 *
	 * <ul>
	 *     <li>Worst-case complexity: O(n), where n is the length of the given list.</li>
	 * </ul>
	 */
	public static boolean checkPalindrome(SinglyLinkedList<SinglyLinkedNode> linkedList) {
		int size = linkedList.size();
		int firstHalfEndIndex = size / 2 - 1;

		// move the items in the first half of the given list to the new list
		SinglyLinkedList<SinglyLinkedNode> firstHalfList = new SinglyLinkedList<>();
		for (int i = 0; i <= firstHalfEndIndex; i++) {
			firstHalfList.insertFirst(linkedList.removeFirst());
		}

		// ignore the middle element as it does not involve in the palindrome check
		if (size % 2 > 0) {
			linkedList.removeFirst();
		}

		// compare the first and the second half of the list to see if the list is a palindrome.
		// note that the first half has already been reversed when putting to the new list.
		SinglyLinkedNode currentFirst = firstHalfList.getHead();
		SinglyLinkedNode currentSecond = linkedList.getHead();
		while (currentFirst != null && currentSecond != null) {
			// not palindrome
			if (!currentFirst.getValue().equals(currentSecond.getValue())) {
				return false;
			}

			currentFirst = currentFirst.getNext();
			currentSecond = currentSecond.getNext();
		}

		return true;
	}
}