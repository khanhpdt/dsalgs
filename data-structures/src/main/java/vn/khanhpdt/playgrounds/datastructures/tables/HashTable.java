package vn.khanhpdt.playgrounds.datastructures.tables;

import vn.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import vn.khanhpdt.playgrounds.datastructures.nodes.Node;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author khanhpdt
 */
public class HashTable {

	private static final int DEFAULT_CAPACITY = 10;

	private final Function<UUID, Integer> hashFunction;

	// collision resolution by chaining
	private final List<SinglyLinkedList<SinglyLinkedNode>> slots;

	private int capacity;

	private int size;

	public HashTable() {
		this.capacity = DEFAULT_CAPACITY;
		this.slots = new ArrayList<>(DEFAULT_CAPACITY);
		this.size = 0;
		this.hashFunction = this::defaultHash;
	}

	private Integer defaultHash(UUID key) {
		return 0;
	}

	public void insert(Node<UUID, Integer> element) {
	}

	public Node<UUID, Integer> search(UUID key) {
		return null;
	}
}
