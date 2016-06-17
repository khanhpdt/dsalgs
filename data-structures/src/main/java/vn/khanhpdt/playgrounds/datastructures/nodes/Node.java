package vn.khanhpdt.playgrounds.datastructures.nodes;

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

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		int result = 17;

		int c = this.getKey().hashCode();

		return 31 + result * c;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) {
			return false;
		}

		Node otherNode = (Node) obj;
		return this.getKey().equals(otherNode.getKey());
	}
}
