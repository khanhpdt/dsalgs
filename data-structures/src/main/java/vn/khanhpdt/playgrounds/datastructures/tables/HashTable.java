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

	private Integer defaultHash(UUID key) {
		// make sure the index is in range
		return Math.abs(key.hashCode() % nSlots);
	}

	public void insert(Node<UUID, Integer> element) {
		int slotIndex = hashFunction.apply(element.getKey());
		SinglyLinkedList<SinglyLinkedNode> slot = slots[slotIndex];

		// first item in the chaining
		if (slot == null) {
			slot = new SinglyLinkedList<>();
			slots[slotIndex] = slot;
		}

		// the new element is always inserted into the head of the chaining
		slot.insert(new SinglyLinkedNode(element));

		nItems++;
	}

	private SinglyLinkedList<SinglyLinkedNode> getSlotFor(UUID key) {
		int slotIndex = hashFunction.apply(key);
		return slots[slotIndex];
	}

	public Node<UUID, Integer> search(UUID key) {
		SinglyLinkedList<SinglyLinkedNode> slot = getSlotFor(key);
		// node in the chaining
		SinglyLinkedNode chainNode = slot.search(key);
		return chainNode.getContent();
	}

	public int size() {
		return nItems;
	}
}
