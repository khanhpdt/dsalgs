package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.UUID;
import java.util.function.Function;

/**
 * @author khanhpdt
 */
public class HashTable {

	private static final int DEFAULT_NUMBER_OF_SLOTS = 10;

	private final Function<UUID, Integer> hashFunction;

	/**
	 * The chaining to handle collision.
	 */
	private final SinglyLinkedList<SinglyLinkedNode>[] slots;

	/**
	 * Number of available slots in the hash table.
	 */
	private int nSlots;

	/**
	 * Number of items currently stored in the hash table.
	 */
	private int nItems;

	@SuppressWarnings("unchecked")
	public HashTable() {
		this.nSlots = DEFAULT_NUMBER_OF_SLOTS;
		this.slots = (SinglyLinkedList<SinglyLinkedNode>[]) new SinglyLinkedList[this.nSlots];
		this.nItems = 0;
		this.hashFunction = this::defaultHash;
	}

	private Integer defaultHash(UUID itemKey) {
		// make sure the index is in range
		return Math.abs(itemKey.hashCode() % nSlots);
	}

	public void insert(Node<UUID, Integer> item) {
		int slotIndex = hashFunction.apply(item.getKey());
		SinglyLinkedList<SinglyLinkedNode> slot = slots[slotIndex];

		// first item in the chaining
		if (slot == null) {
			slot = new SinglyLinkedList<>();
			slots[slotIndex] = slot;
		}

		// the new item is always inserted into the head of the chaining
		slot.insert(new SinglyLinkedNode(item));

		nItems++;
	}

	private SinglyLinkedList<SinglyLinkedNode> getSlotFor(UUID itemKey) {
		int slotIndex = hashFunction.apply(itemKey);
		return slots[slotIndex];
	}

	public Node<UUID, Integer> search(UUID itemKey) {
		SinglyLinkedList<SinglyLinkedNode> slot = getSlotFor(itemKey);

		// no slot found, which means item is never in the hash table
		if (slot == null) {
			return null;
		}

		SinglyLinkedNode slotItem = slot.search(itemKey);
		if (slotItem == null) {
			return null;
		}
		return slotItem.getContent();
	}

	public int size() {
		return nItems;
	}

	public void remove(UUID itemKey) {
		SinglyLinkedList<SinglyLinkedNode> slot = getSlotFor(itemKey);

		if (slot == null) {
			return;
		}

		slot.remove(itemKey);
	}
}
