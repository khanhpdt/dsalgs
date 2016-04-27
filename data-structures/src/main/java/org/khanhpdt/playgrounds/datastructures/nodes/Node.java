package org.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @author khanhpdt
 */
public class Node<K, V> {

	private K key;

	private V value;

	public Node(K key) {
		this.key = key;
	}

	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
}
