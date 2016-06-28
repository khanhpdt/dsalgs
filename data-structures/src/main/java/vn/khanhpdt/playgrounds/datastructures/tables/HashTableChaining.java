package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.UUID;
import java.util.function.Function;

/**
 * @author khanhpdt
 */
public class HashTableChaining extends HashTable {

	private final Function<UUID, Integer> hashFunction;

	/**
	 * The chaining to handle collision.
	 */
	private final SinglyLinkedList<SinglyLinkedNode>[] slots;

	@SuppressWarnings("unchecked")
	public HashTableChaining() {
		super();
		this.slots = (SinglyLinkedList<SinglyLinkedNode>[]) new SinglyLinkedList[this.nSlots];
		this.hashFunction = this::defaultHash;
	}

	private Integer defaultHash(UUID itemKey) {
		// make sure the index is in range
		return Math.abs(itemKey.hashCode() % nSlots);
	}

	@Override
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

	@Override
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

	@Override
	public void remove(UUID itemKey) {
		SinglyLinkedList<SinglyLinkedNode> slot = getSlotFor(itemKey);

		if (slot == null) {
			return;
		}

		slot.removeAll(itemKey);

		nItems--;
	}
}
