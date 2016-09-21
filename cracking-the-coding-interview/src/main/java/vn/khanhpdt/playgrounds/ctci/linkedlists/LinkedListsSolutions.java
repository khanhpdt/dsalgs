package vn.khanhpdt.playgrounds.ctci.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.DoublyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author khanhpdt
 */
class LinkedListsSolutions {

	/**
	 * <p>Problem 2.1</p>
	 * <p>Solution 1: two pointers</p>
	 * <p>Worst-case complexity: O(n^2), where n is the length of the list.</p>
	 */
	static void removeDuplicates(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list) {
		SinglyLinkedNode<UUID, Integer> current = list.getHead();
		while (current != null) {
			SinglyLinkedNode<UUID, Integer> previous = current;
			SinglyLinkedNode<UUID, Integer> item = current.getNext();
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
	 * <p>Problem 2.1</p>
	 * <p>Solution 2: use a hash table</p>
	 * <p>Worst-case complexity: O(n), where n is the length of the given list.</p>
	 * <p>Needs extra memory for the set.</p>
	 */
	static void removeDuplicates_2(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list) {
		// This set contains distinct list items.
		// To achieve O(n), it must take O(1) time to check if an item is in the set.
		Set<SinglyLinkedNode<UUID, Integer>> distinctItems = new HashSet<>();

		SinglyLinkedNode<UUID, Integer> previous = null;
		SinglyLinkedNode<UUID, Integer> current = list.getHead();
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

	private static void remove(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list,
	                           SinglyLinkedNode<UUID, Integer> previous,
	                           SinglyLinkedNode<UUID, Integer> current) {
		// head is being removed
		if (previous == null) {
			list.setHead(current.getNext());
		} else {
			previous.setNext(current.getNext());
		}
	}

	/**
	 * Problem 2.2.1: Length is known.
	 * <p>Worst-case complexity: O(n), where n is the length of the given list.</p>
	 */
	static SinglyLinkedNode<UUID, Integer> getKthToLastKnownLength(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list, int k) {
		return list.getKthToLast(k);
	}

	/**
	 * <p>Problem 2.2.2: Length is unknown.<p>
	 * <p>Solution: use two pointers</p>
	 * <p>This solution demonstrates a creative way to use multiple pointers, but it is not straightforward.</p>
	 * <p>Another simpler solution but with the same complexity is to find the size first and then use it to
	 * find the kth-to-last node.</p>
	 * <p>Worst-case complexity: O(n), where n is the length of the given list.</p>
	 */
	static SinglyLinkedNode<UUID, Integer> getKthToLastUnknownLength(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> list, int k) {
		SinglyLinkedNode<UUID, Integer> pointerToInterest = list.getHead();
		SinglyLinkedNode<UUID, Integer> pointerToLast = list.getHead();

		// make the pointers k-position apart
		for (int i = 0; i < k; i++) {
			// not enough nodes
			if (pointerToLast == null) {
				return null;
			}
			pointerToLast = pointerToLast.getNext();
		}

		while (pointerToLast.getNext() != null) {
			pointerToLast = pointerToLast.getNext();
			pointerToInterest = pointerToInterest.getNext();
		}

		// when pointerToLast reaches the last one, pointerToInterest reaches the node k-position apart from the last node
		return pointerToInterest;
	}

	/**
	 * Problem 2.3.
	 * <p>Worst-case complexity: O(1)</p>
	 */
	static void removeMiddleItem(SinglyLinkedNode<UUID, Integer> removedItem) {
		SinglyLinkedNode<UUID, Integer> nextItem = removedItem.getNext();

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
	 * <p>Worst-case complexity: O(n), where n is the length of the given list. The worst case happens when all the
	 * items in the list are smaller than the given partitioning value, because in that case we have to move all the
	 * items to the head of the list.</p>
	 */
	static void partition(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList, Integer partitioningValue) {
		SinglyLinkedNode<UUID, Integer> head = linkedList.getHead();

		if (head == null) {
			return;
		}

		SinglyLinkedNode<UUID, Integer> previous = head;
		SinglyLinkedNode<UUID, Integer> current = head.getNext();
		while (current != null) {
			// move the smaller nodes to the head of the list
			if (current.getValue() < partitioningValue) {
				SinglyLinkedNode<UUID, Integer> next = current.getNext();

				// move current to become the head of the list
				previous.setNext(next);
				linkedList.setHead(current);
				current.setNext(head);

				current = next;
			} else {
				previous = current;
				current = current.getNext();
			}
		}
	}

	/**
	 * <p>Problem 2.5.1: The digits are stored in backward order, e.g., 617 is stored as 7 -> 1 -> 6.</p>
	 * <p>Worst-case complexity: O(n^2) if the result is a singly linked list, and O(n) if it is a double-ended list,
	 * where n is the length of the longer among the given lists.</p>
	 */
	static SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> sumBackwardDigits(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber,
	                                                            SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber) {

		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> result = new SinglyLinkedList<>();
		SinglyLinkedNode<UUID, Integer> current1 = firstNumber.getHead();
		SinglyLinkedNode<UUID, Integer> current2 = secondNumber.getHead();

		int carry = 0;
		while (current1 != null || current2 != null) {
			int digit1 = current1 == null ? 0 : current1.getValue();
			int digit2 = current2 == null ? 0 : current2.getValue();
			int sum = digit1 + digit2 + carry;

			int digitSum = sum % 10;
			// this takes O(n). we can make it to O(1) by replacing singly linked list by the double-ended one.
			result.insertLast(SinglyLinkedNode.from(UUID.randomUUID(), digitSum));

			carry = sum / 10;

			current1 = current1 == null ? null : current1.getNext();
			current2 = current2 == null ? null : current2.getNext();
		}

		return result;
	}

	/**
	 * <p>Problem 2.5.1: The digits are stored in backward order, e.g., 617 is stored as 7 -> 1 -> 6.</p>
	 * <p>Worst-case complexity: O(n), where n is the length of the longer among the given lists.</p>
	 */
	static SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> sumBackwardDigits_2(
			SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber,
			SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber) {

		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> result = new SinglyLinkedList<>();
		SinglyLinkedNode<UUID, Integer> current1 = firstNumber.getHead();
		SinglyLinkedNode<UUID, Integer> current2 = secondNumber.getHead();

		int carry = 0;
		while (current1 != null || current2 != null) {
			int digit1 = current1 == null ? 0 : current1.getValue();
			int digit2 = current2 == null ? 0 : current2.getValue();
			int sum = digit1 + digit2 + carry;

			int digitSum = sum % 10;
			// this takes O(1), but remember to reverse the result before returning it
			result.insertFirst(SinglyLinkedNode.from(UUID.randomUUID(), digitSum));

			carry = sum / 10;

			current1 = current1 == null ? null : current1.getNext();
			current2 = current2 == null ? null : current2.getNext();
		}

		return result.reverse();
	}

	/**
	 * <p>Problem 2.5.2: The digits are stored in forward order, e.g., 617 is stored as 6 -> 1 -> 7.</p>
	 * <p>Solution 1: simply reuse {@link #sumBackwardDigits_2(SinglyLinkedList, SinglyLinkedList)}</p>
	 * <p>Worst-case complexity: O(n), where n is the length of the longer among the given lists.</p>
	 */
	static SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> sumForwardDigits(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber,
	                                                           SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber) {
		return sumBackwardDigits_2(firstNumber.reverse(), secondNumber.reverse()).reverse();
	}

	/**
	 * <p>Problem 2.5.2: The digits are stored in forward order, e.g., 617 is stored as 6 -> 1 -> 7.</p>
	 * <p>Solution 2: use a doubly-linked list so that the carry can be propagated</p>
	 * <p>Worst-case complexity: O(n^2), where n is the length of the longer among the given lists. The worst case
	 * happens when the carry is propagated all the way back for every calculation.</p>
	 */
	static DoublyLinkedList sumForwardDigits_2(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstNumber,
	                                           SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> secondNumber) {
		DoublyLinkedList result = new DoublyLinkedList();

		int firstLength = firstNumber.size();
		int secondLength = secondNumber.size();

		// find out the longer and shorter number
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> longNumber;
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> shortNumber;
		if (firstLength >= secondLength) {
			longNumber = firstNumber;
			shortNumber = secondNumber;
		} else {
			longNumber = secondNumber;
			shortNumber = firstNumber;
		}

		// handle the length difference
		SinglyLinkedNode<UUID, Integer> longNumberCurrent = longNumber.getHead();
		for (int i = 0; i < firstLength - secondLength; i++) {
			result.insert(new DoublyLinkedNode(longNumberCurrent.getValue()));
			longNumberCurrent = longNumberCurrent.getNext();
		}
		SinglyLinkedNode<UUID, Integer> shortNumberCurrent = shortNumber.getHead();

		// start calculating the sum
		while (longNumberCurrent != null && shortNumberCurrent != null) {
			int sum = longNumberCurrent.getValue() + shortNumberCurrent.getValue();

			int carry = sum / 10;
			if (carry > 0) {
				// propagate carry to higher-order positions in the result
				DoublyLinkedNode resultCurrent = result.getHead();
				DoublyLinkedNode resultPrevious = null;
				while (carry > 0) {
					// propagating carry to the higher-order positions compared to the current sum-position
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
	 * <p>Solution 1: use a hash table</p>
	 * <p>Worst-case complexity: O(n), where n is the length of the given list.</p>
	 * <p>Need O(n) extra memory</p>
	 */
	static SinglyLinkedNode<UUID, Integer> getFirstOfTheLoop(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList) {
		// this set needs O(n) memory
		Set<SinglyLinkedNode<UUID, Integer>> existingNodes = new HashSet<>();
		SinglyLinkedNode<UUID, Integer> current = linkedList.getHead();
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
	 * Problem 2.6.
	 * <p>Solution 2: use two pointers</p>
	 * <p>Worst-case complexity: O(n), where n is the length of the given list.</p>
	 */
	static SinglyLinkedNode<UUID, Integer> getFirstOfTheLoop_2(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList) {
		SinglyLinkedNode<UUID, Integer> slowPointer = linkedList.getHead();
		SinglyLinkedNode<UUID, Integer> fastPointer = linkedList.getHead();

		// make them collide
		// if they collide, they do at LOOP_SIZE - k, where k is the distance from the head to the loop start
		do {
			slowPointer = slowPointer.getNext();
			fastPointer = fastPointer.getNext().getNext();
		} while (slowPointer != null && fastPointer != null && !slowPointer.equals(fastPointer));

		// no loop
		if (slowPointer == null || fastPointer == null) {
			return null;
		}

		// since k is the distance from head to the loop start and also the one from the collision spot to the loop start,
		// two pointers moving from the head and from the collision spot with the same speed will collide at the loop start.
		slowPointer = linkedList.getHead();
		while (!slowPointer.equals(fastPointer)) {
			slowPointer = slowPointer.getNext();
			fastPointer = fastPointer.getNext();
		}

		// either slowPointer or fastPointer is ok
		return slowPointer;
	}

	/**
	 * Problem 2.7.
	 *
	 * <p>Worst-case complexity: O(n), where n is the length of the given list.</p>
	 */
	static boolean checkPalindrome(SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList) {
		int size = linkedList.size();
		int firstHalfEndIndex = size / 2 - 1;

		// cut the items in the first half of the given list and insert them into the new list in a reverse order
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> firstHalfList = new SinglyLinkedList<>();
		for (int i = 0; i <= firstHalfEndIndex; i++) {
			firstHalfList.insertFirst(linkedList.removeFirst());
		}

		// ignore the middle element as it does not involve in the palindrome check
		if (size % 2 > 0) {
			linkedList.removeFirst();
		}

		// compare the first and the second half of the list to see if the list is a palindrome.
		// note that the first half has already been reversed when putting to the new list.
		SinglyLinkedNode<UUID, Integer> currentFirst = firstHalfList.getHead();
		SinglyLinkedNode<UUID, Integer> currentSecond = linkedList.getHead();
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