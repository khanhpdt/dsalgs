package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.nodes.Node;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public abstract class HashTable {

	private static final int DEFAULT_NUMBER_OF_SLOTS = 10;

	/**
	 * Number of available slots in the hash table.
	 */
	protected int nSlots;

	/**
	 * Number of items currently stored in the hash table.
	 */
	protected int nItems;

	protected HashTable() {
		this.nSlots = DEFAULT_NUMBER_OF_SLOTS;
		this.nItems = 0;
	}

	public int size() {
		return nItems;
	}

	public abstract void insert(Node<UUID, Integer> item);

	public abstract Node<UUID, Integer> search(UUID itemKey);

	public abstract void remove(UUID itemKey);

}
