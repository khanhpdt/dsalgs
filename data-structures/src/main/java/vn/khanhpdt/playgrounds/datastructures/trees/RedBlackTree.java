package vn.khanhpdt.playgrounds.datastructures.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.RedBlackTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.RedBlackTreeNullNode;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

/**
 * @author khanhpdt
 */
public class RedBlackTree<K, V extends Comparable<V>> extends BinarySearchTree<K, V> {

	private RedBlackTreeNode<K, V> root = getNullNode();

	@Override
	@SuppressWarnings("unchecked")
	RedBlackTreeNode<K, V> getNullNode() {
		return (RedBlackTreeNode<K, V>) RedBlackTreeNullNode.INSTANCE;
	}

	// see [1, subsection 13.3] for details
	public RedBlackTreeNode<K, V> insert(RedBlackTreeNode<K, V> node) {
		// do normal insert as in binary search tree
		RedBlackTreeNode<K, V> newNode = (RedBlackTreeNode<K, V>) super.insert(node);

		newNode.setColor(RED);

		// to preserve the red-black properties
		insertFixUp(newNode);

		return newNode;
	}

	private void insertFixUp(RedBlackTreeNode<K, V> node) {
		// first node
		if (getRoot().equals(node)) {
			// the root of a red-black tree must be black
			getRoot().setColor(BLACK);
			return;
		}

		// no need to re-arrange the node if its parent is black.
		while (node.getParent().getColor() == RED) {
			// case 1
			if (node.getUncle().getColor() == RED) {
				node.getParent().setColor(BLACK);
				node.getUncle().setColor(BLACK);
				node.getGrandParent().setColor(RED);

				node = node.getGrandParent();
			}
			// node's uncle is black.
			// note that since node's parent is red, its grandparent must be black due to property 4.
			else {
				// case 2
				if (node.getParent().getRight().equals(node)) {
					rotateLeft(node.getParent(), node);
					// transform to case 3
					node = node.getLeft();
				}

				// case 3
				if (node.getParent().getLeft().equals(node)) {
					node.getParent().setColor(BLACK);
					rotateRight(node.getGrandParent(), node.getParent());
					node.getParent().getRight().setColor(RED);
				}
			}
		}

		// always keep the root black
		getRoot().setColor(BLACK);
	}

	private void rotateLeft(RedBlackTreeNode<K, V> parent, RedBlackTreeNode<K, V> node) {
		transplantParent(parent, node);

		RedBlackTreeNode<K, V> nodeLeftChild = node.getLeft();
		node.setLeft(parent);
		parent.setParent(node);

		parent.setRight(nodeLeftChild);
		nodeLeftChild.setParent(parent);
	}

	private void rotateRight(RedBlackTreeNode<K, V> parent, RedBlackTreeNode<K, V> node) {
		transplantParent(parent, node);

		RedBlackTreeNode<K, V> nodeRightChild = node.getRight();
		node.setRight(parent);
		parent.setParent(node);

		parent.setLeft(nodeRightChild);
		nodeRightChild.setParent(parent);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected <R extends BinaryTreeNode<K, V>> void setRoot(R root) {
		this.root = (RedBlackTreeNode<K, V>) root;
	}

	@Override
	public RedBlackTreeNode<K, V> getRoot() {
		return this.root;
	}

	@Override
	public RedBlackTreeNode<K, V> findNodeByValue(V value) {
		return (RedBlackTreeNode<K, V>) super.findNodeByValue(value);
	}
}
