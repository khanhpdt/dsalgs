package vn.khanhpdt.playgrounds.datastructures.nodes;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class BinaryTreeNullNode extends BinaryTreeNode {

	private static final BinaryTreeNullNode INSTANCE = new BinaryTreeNullNode(new Node<>(UUID.randomUUID()));

	private BinaryTreeNullNode(Node nodeContent) {
		super(nodeContent);
	}

	public static <K, V extends Comparable<V>> BinaryTreeNode<K, V> getInstance() {
		return INSTANCE;
	}
}
