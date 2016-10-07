package vn.khanhpdt.playgrounds.datastructures.nodes;

import java.awt.Color;

/**
 * @author khanhpdt
 */
public class RedBlackTreeNode<K, V extends Comparable<V>> extends BinaryTreeNode<K, V> {

	private Color color;

	public RedBlackTreeNode(Node<K, V> nodeContent) {
		super(nodeContent);

		initNullNeighbors();
	}

	public RedBlackTreeNode(Node<K, V> nodeContent, Color color) {
		super(nodeContent);

		this.color = color;

		initNullNeighbors();
	}

	private void initNullNeighbors() {
		this.setLeft(getNullNode());
		this.setRight(getNullNode());
		this.setParent(getNullNode());
	}

	@SuppressWarnings("unchecked")
	private RedBlackTreeNode<K, V> getNullNode() {
		return (RedBlackTreeNode<K, V>) RedBlackTreeNullNode.INSTANCE;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public RedBlackTreeNode<K, V> getLeft() {
		return (RedBlackTreeNode<K, V>) super.getLeft();
	}

	@Override
	public RedBlackTreeNode<K, V> getRight() {
		return (RedBlackTreeNode<K, V>) super.getRight();
	}

	@Override
	public RedBlackTreeNode<K, V> getParent() {
		return (RedBlackTreeNode<K, V>) super.getParent();
	}

	public RedBlackTreeNode<K, V> getGrandParent() {
		return getParent().getParent();
	}

}
