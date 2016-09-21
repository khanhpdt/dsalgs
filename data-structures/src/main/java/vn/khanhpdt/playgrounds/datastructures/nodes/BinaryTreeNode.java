package vn.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @param <K>  type of node key
 * @param <V>  type of node value
 * @author khanhpdt
 */
public class BinaryTreeNode<K, V extends Comparable<V>> implements Comparable<BinaryTreeNode<K, V>>, ForwardLinked<BinaryTreeNode<K, V>> {

	private Node<K, V> content;

	private BinaryTreeNode<K, V> left;

	private BinaryTreeNode<K, V> right;

	private BinaryTreeNode<K, V> next;

	private BinaryTreeNode(Node<K, V> nodeContent) {
		this.content = nodeContent;
	}

	public V getValue() {
		return content.getValue();
	}

	public BinaryTreeNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<K, V> left) {
		this.left = left;
	}

	public BinaryTreeNode<K, V> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<K, V> right) {
		this.right = right;
	}

	@Override
	public int compareTo(BinaryTreeNode<K, V> o) {
		return getValue().compareTo(o.getValue());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}

		@SuppressWarnings("unchecked")
		BinaryTreeNode<K, V> that = (BinaryTreeNode<K, V>) o;
		return getKey().equals(that.getKey());

	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = getKey().hashCode();
		return 31 + result * c;
	}

	public K getKey() {
		return content.getKey();
	}

	@Override
	public BinaryTreeNode<K, V> getNext() {
		return this.next;
	}

	@Override
	public void setNext(BinaryTreeNode<K, V> next) {
		this.next = next;
	}

	public static <K, V extends Comparable<V>> BinaryTreeNode<K, V> from(K key, V value) {
		return new BinaryTreeNode<>(new Node<>(key, value));
	}

}
