package vn.khanhpdt.playgrounds.ctci.linkedlists;

import org.hamcrest.Matchers;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.LinkedLists;
import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * @author khanhpdt
 */
public class LinkedListsSolutionsTest {

	private static final Random RANDOM = new Random();

	@Test
	public void testRemoveDuplicates() {
		SinglyLinkedList<SinglyLinkedNode> list = createSinglyLinkedListWithDuplicates();

		LinkedListsSolutions.removeDuplicates(list);

		List<SinglyLinkedNode> nodes = LinkedLists.traverse(list);
		assertThat(nodes, hasSize(3));
	}

	private SinglyLinkedList<SinglyLinkedNode> createSinglyLinkedListWithDuplicates() {
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		UUID uuid3 = UUID.randomUUID();

		SinglyLinkedList<SinglyLinkedNode> list = new SinglyLinkedList<>();
		list.insertFirst(new SinglyLinkedNode(uuid1));
		list.insertFirst(new SinglyLinkedNode(uuid2));
		list.insertFirst(new SinglyLinkedNode(uuid3));
		list.insertFirst(new SinglyLinkedNode(uuid1));
		list.insertFirst(new SinglyLinkedNode(uuid2));
		list.insertFirst(new SinglyLinkedNode(uuid3));

		return list;
	}

	@Test
	public void testRemoveDuplicates_2() {
		SinglyLinkedList<SinglyLinkedNode> list = createSinglyLinkedListWithDuplicates();

		LinkedListsSolutions.removeDuplicates_2(list);

		List<SinglyLinkedNode> nodes = LinkedLists.traverse(list);
		assertThat(nodes, hasSize(3));
	}

	@Test
	public void testGetKthToLast() {
		int nItems = 10;
		List<SinglyLinkedNode> items = randomItems(nItems);

		SinglyLinkedList<SinglyLinkedNode> list = createSinglyLinkedList(items);

		for (int k = 0; k < nItems; k++) {
			SinglyLinkedNode item = LinkedListsSolutions.getKthToLast(list, k);
			assertThat(item, is(items.get(k)));
		}
	}

	private List<SinglyLinkedNode> randomItems(int nItems) {
		List<SinglyLinkedNode> items = new ArrayList<>();
		IntStream.range(0, nItems).forEach(i -> items.add(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), RANDOM.nextInt()))));
		return items;
	}

	private SinglyLinkedList<SinglyLinkedNode> createSinglyLinkedList(List<SinglyLinkedNode> items) {
		SinglyLinkedList<SinglyLinkedNode> list = new SinglyLinkedList<>();
		items.forEach(list::insertFirst);
		return list;
	}

	@Test
	public void testRemoveMiddle() throws Exception {
		int nItems = 10;
		SinglyLinkedList<SinglyLinkedNode> linkedList = createSinglyLinkedList(nItems);

		SinglyLinkedNode removedItem = linkedList.get(3);
		UUID removedItemKey = removedItem.getKey();
		LinkedListsSolutions.removeMiddleItem(removedItem);

		List<SinglyLinkedNode> remainingItems = LinkedLists.traverse(linkedList);
		assertThat(remainingItems, hasSize(nItems - 1));
		assertThat(remainingItems.stream().map(SinglyLinkedNode::getKey).collect(Collectors.toList()),
				not(hasItem(removedItemKey)));
	}

	private SinglyLinkedList<SinglyLinkedNode> createSinglyLinkedList(int nItems) {
		return createSinglyLinkedList(randomItems(nItems));
	}

	@Test
	public void testPartition() throws Exception {
		// given
		SinglyLinkedList<SinglyLinkedNode> linkedList = createSinglyLinkedList(15);

		// when
		Integer partitioningValue = linkedList.get(5).getContent().getValue();
		LinkedListsSolutions.partition(linkedList, partitioningValue);

		// then
		SinglyLinkedNode current = linkedList.getHead();
		boolean smaller = current.getValue() < partitioningValue;
		while (current != null) {
			if (smaller && current.getValue() >= partitioningValue) {
				smaller = false;
			}
			if (smaller) {
				assertThat(current.getValue(), Matchers.lessThan(partitioningValue));
			} else {
				assertThat(current.getValue(), Matchers.greaterThanOrEqualTo(partitioningValue));
			}

			current = current.getNext();
		}
	}

	@Test
	public void testSumBackwardDigits_1() throws Exception {
		// 617
		SinglyLinkedList<SinglyLinkedNode> firstNumber = new SinglyLinkedList<>();
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 6)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 1)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 7)));

		// 295
		SinglyLinkedList<SinglyLinkedNode> secondNumber = new SinglyLinkedList<>();
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 2)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 9)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));

		SinglyLinkedList<SinglyLinkedNode> sum = LinkedListsSolutions.sumBackwardDigits(firstNumber, secondNumber);

		// 617 + 295 = 912
		assertThat(sum.get(0).getValue(), is(2));
		assertThat(sum.get(1).getValue(), is(1));
		assertThat(sum.get(2).getValue(), is(9));
	}

	@Test
	public void testSumBackwardDigits_2() throws Exception {
		// 1548
		SinglyLinkedList<SinglyLinkedNode> firstNumber = new SinglyLinkedList<>();
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 1)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 4)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 8)));

		// 295
		SinglyLinkedList<SinglyLinkedNode> secondNumber = new SinglyLinkedList<>();
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 2)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 9)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));

		SinglyLinkedList<SinglyLinkedNode> sum = LinkedListsSolutions.sumBackwardDigits(firstNumber, secondNumber);

		// 1548 + 295 = 1843
		assertThat(sum.get(0).getValue(), is(3));
		assertThat(sum.get(1).getValue(), is(4));
		assertThat(sum.get(2).getValue(), is(8));
		assertThat(sum.get(3).getValue(), is(1));
	}

	@Test
	public void testSumBackwardDigits2_1() throws Exception {
		// 617
		SinglyLinkedList<SinglyLinkedNode> firstNumber = new SinglyLinkedList<>();
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 6)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 1)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 7)));

		// 295
		SinglyLinkedList<SinglyLinkedNode> secondNumber = new SinglyLinkedList<>();
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 2)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 9)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));

		SinglyLinkedList<SinglyLinkedNode> sum = LinkedListsSolutions.sumBackwardDigits_2(firstNumber, secondNumber);

		// 617 + 295 = 912
		assertThat(sum.get(0).getValue(), is(2));
		assertThat(sum.get(1).getValue(), is(1));
		assertThat(sum.get(2).getValue(), is(9));
	}

	@Test
	public void testSumBackwardDigits2_2() throws Exception {
		// 1548
		SinglyLinkedList<SinglyLinkedNode> firstNumber = new SinglyLinkedList<>();
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 1)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 4)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 8)));

		// 295
		SinglyLinkedList<SinglyLinkedNode> secondNumber = new SinglyLinkedList<>();
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 2)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 9)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));

		SinglyLinkedList<SinglyLinkedNode> sum = LinkedListsSolutions.sumBackwardDigits_2(firstNumber, secondNumber);

		// 1548 + 295 = 1843
		assertThat(sum.get(0).getValue(), is(3));
		assertThat(sum.get(1).getValue(), is(4));
		assertThat(sum.get(2).getValue(), is(8));
		assertThat(sum.get(3).getValue(), is(1));
	}

	@Test
	public void testSumForwardDigits_1() throws Exception {
		// 617
		SinglyLinkedList<SinglyLinkedNode> firstNumber = new SinglyLinkedList<>();
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 7)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 1)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 6)));

		// 295
		SinglyLinkedList<SinglyLinkedNode> secondNumber = new SinglyLinkedList<>();
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 9)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 2)));

		SinglyLinkedList<SinglyLinkedNode> sum = LinkedListsSolutions.sumForwardDigits(firstNumber, secondNumber);

		// 617 + 295 = 912
		assertThat(sum.get(0).getValue(), is(9));
		assertThat(sum.get(1).getValue(), is(1));
		assertThat(sum.get(2).getValue(), is(2));
	}

	@Test
	public void testSumForwardDigits_2() throws Exception {
		// 1548
		SinglyLinkedList<SinglyLinkedNode> firstNumber = new SinglyLinkedList<>();
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 8)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 4)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));
		firstNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 1)));

		// 295
		SinglyLinkedList<SinglyLinkedNode> secondNumber = new SinglyLinkedList<>();
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 5)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 9)));
		secondNumber.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID(), 2)));

		SinglyLinkedList<SinglyLinkedNode> sum = LinkedListsSolutions.sumForwardDigits(firstNumber, secondNumber);

		// 1548 + 295 = 1843
		assertThat(sum.get(0).getValue(), is(1));
		assertThat(sum.get(1).getValue(), is(8));
		assertThat(sum.get(2).getValue(), is(4));
		assertThat(sum.get(3).getValue(), is(3));
	}

	@Test
	public void testFirstOfTheLoop_1() throws Exception {
		SinglyLinkedNode theFirstOfTheLoop = new SinglyLinkedNode(new Node<>(UUID.randomUUID()));
		SinglyLinkedList<SinglyLinkedNode> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(theFirstOfTheLoop);
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(theFirstOfTheLoop);
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));

		SinglyLinkedNode firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop(linkedList);

		assertThat(firstOfTheLoop, is(theFirstOfTheLoop));
	}

	@Test
	public void testFirstOfTheLoop_2() throws Exception {
		SinglyLinkedNode theFirstOfTheFirstLoop = new SinglyLinkedNode(new Node<>(UUID.randomUUID()));
		SinglyLinkedNode theFirstOfTheSecondLoop = new SinglyLinkedNode(new Node<>(UUID.randomUUID()));
		SinglyLinkedList<SinglyLinkedNode> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(theFirstOfTheSecondLoop);
		linkedList.insertFirst(theFirstOfTheFirstLoop);
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(theFirstOfTheSecondLoop);
		linkedList.insertFirst(theFirstOfTheFirstLoop);
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));

		SinglyLinkedNode firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop(linkedList);

		assertThat(firstOfTheLoop, is(theFirstOfTheFirstLoop));
	}

	@Test
	public void testFirstOfTheLoop_3() throws Exception {
		SinglyLinkedList<SinglyLinkedNode> linkedList = new SinglyLinkedList<>();
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));
		linkedList.insertFirst(new SinglyLinkedNode(new Node<>(UUID.randomUUID())));

		SinglyLinkedNode firstOfTheLoop = LinkedListsSolutions.getFirstOfTheLoop(linkedList);

		assertThat(firstOfTheLoop, is(nullValue()));
	}

}