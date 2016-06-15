package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.nodes.Node;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class HashTableOpenAddresing extends HashTable {

	private final ProbingMethod probingMethod;

	public HashTableOpenAddresing(ProbingMethod probingMethod) {
		super();
		this.probingMethod = probingMethod;
	}

	@Override
	public void insert(Node<UUID, Integer> item) {
		if (nItems == nSlots) {
			throw new IndexOutOfBoundsException("The hash table is already full.");
		}



	}

	@Override
	public Node<UUID, Integer> search(UUID itemKey) {
		return null;
	}

	@Override
	public void remove(UUID itemKey) {

	}

}
