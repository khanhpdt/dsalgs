package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.tables.probings.ProbingMethod;
import vn.khanhpdt.playgrounds.datastructures.tables.probings.ProbingMethodName;
import vn.khanhpdt.playgrounds.datastructures.tables.probings.ProbingMethods;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class HashTableOpenAddressing extends HashTable {

	private Node<UUID, Integer>[] slots;

	private final ProbingMethodName probingMethodName;

	private SlotStatus[] slotStatuses;

	@SuppressWarnings("unchecked")
	public HashTableOpenAddressing(ProbingMethodName probingMethodName) {
		super();
		this.probingMethodName = probingMethodName;
		this.slots = (Node<UUID, Integer>[]) new Node[nSlots];
		this.slotStatuses = new SlotStatus[nSlots];
		Arrays.fill(slotStatuses, SlotStatus.AVAILABLE);
	}

	@Override
	public void insert(Node<UUID, Integer> item) {
		if (nItems == nSlots) {
			throw new IllegalStateException("The hash table is already full.");
		}

		final ProbingMethod probingMethod = ProbingMethods.create(probingMethodName, nSlots);

		// keep searching until either finding an available slot or all the slots are probed
		int slotIndex = probingMethod.firstProbe(item.getKey());
		int nProbes = 1;
		while (slotStatuses[slotIndex] == SlotStatus.ALLOCATED && nProbes <= nSlots) {
			slotIndex = probingMethod.probe(item.getKey());
			nProbes++;
		}

		if (slotStatuses[slotIndex] == SlotStatus.AVAILABLE) {
			slots[slotIndex] = item;
			slotStatuses[slotIndex] = SlotStatus.ALLOCATED;
			nItems++;
		}
		else {
			throw new IllegalStateException("Could not find any available slot.");
		}
	}

	@Override
	public Node<UUID, Integer> search(UUID itemKey) {
		int slotIndex = searchSlot(itemKey);

		if (slotIndex == -1) {
			return null;
		}
		return slots[slotIndex];
	}

	private int searchSlot(UUID itemKey) {
		final ProbingMethod probingMethod = ProbingMethods.create(probingMethodName, nSlots);

		int slotIndex = probingMethod.firstProbe(itemKey);
		int nProbes = 1;

		// if the slot slotIndex is still available, the item is not in the hash table, because otherwise it would have
		// been inserted into the slot slotIndex
		while (slotStatuses[slotIndex] == SlotStatus.ALLOCATED && !isSlotWithKey(slotIndex, itemKey) && nProbes <= nSlots) {
			slotIndex = probingMethod.probe(itemKey);
			nProbes++;
		}

		// found an existing slot with the given key
		if (slotStatuses[slotIndex] == SlotStatus.ALLOCATED && isSlotWithKey(slotIndex, itemKey)) {
			return slotIndex;
		}
		// not found
		return -1;
	}

	private boolean isSlotWithKey(int slotIndex, UUID key) {
		return slots[slotIndex] != null && slots[slotIndex].getKey().equals(key);
	}

	@Override
	public void remove(UUID itemKey) {
		int slotIndex = searchSlot(itemKey);
		// item found
		if (slotIndex >= 0) {
			slots[slotIndex] = null;
			slotStatuses[slotIndex] = SlotStatus.AVAILABLE;
		}
	}

}
