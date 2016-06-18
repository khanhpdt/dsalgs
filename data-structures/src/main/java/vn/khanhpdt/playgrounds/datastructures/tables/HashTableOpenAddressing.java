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

	private ProbingMethodName probingMethodName;

	private SlotStatus[] slotStatuses;

	public HashTableOpenAddressing(ProbingMethodName probingMethodName) {
		super();
		init(probingMethodName);
	}

	@SuppressWarnings("unchecked")
	private void init(ProbingMethodName probingMethodName) {
		this.probingMethodName = probingMethodName;
		this.slots = (Node<UUID, Integer>[]) new Node[nSlots];
		this.slotStatuses = new SlotStatus[nSlots];
		Arrays.fill(slotStatuses, SlotStatus.AVAILABLE);
	}

	public HashTableOpenAddressing(int nSlots, ProbingMethodName probingMethodName) {
		super();
		this.nSlots = nSlots;
		init(probingMethodName);
	}

	@Override
	public void insert(Node<UUID, Integer> item) {
		if (nItems == nSlots) {
			throw new IllegalStateException("The hash table is already full.");
		}

		final ProbingMethod probingMethod = ProbingMethods.create(probingMethodName, nSlots);

		// keep searching until either finding an available slot or all the slots are probed
		int slotIndex = probingMethod.probe(item.getKey());
		int nProbes = 1;
		while (slotStatuses[slotIndex] == SlotStatus.ALLOCATED && nProbes <= nSlots) {
			slotIndex = probingMethod.probe(item.getKey());
			nProbes++;
		}

		if (slotStatuses[slotIndex] == SlotStatus.AVAILABLE) {
			insertItem(item, slotIndex);
		}
		else {
			throw new IllegalStateException("Could not find any available slot.");
		}
	}

	private void insertItem(Node<UUID, Integer> item, int slotIndex) {
		slots[slotIndex] = item;
		slotStatuses[slotIndex] = SlotStatus.ALLOCATED;
		nItems++;
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

		int slotIndex = probingMethod.probe(itemKey);
		int nProbes = 1;

		// if the slot at slotIndex is still available, the item is not in the hash table, because otherwise it would have
		// been inserted into the slot
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
			removeItem(slotIndex);
		}
	}

	private void removeItem(int slotIndex) {
		slots[slotIndex] = null;
		slotStatuses[slotIndex] = SlotStatus.AVAILABLE;
		nItems--;
	}

}