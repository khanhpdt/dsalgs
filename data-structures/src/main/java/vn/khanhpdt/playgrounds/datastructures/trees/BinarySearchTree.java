package vn.khanhpdt.playgrounds.datastructures.trees;

import vn.khanhpdt.playgrounds.algorithms.trees.InOrderTraversalIterative;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNullNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author khanhpdt
 */
public class BinarySearchTree<K, V extends Comparable<V>> {

	private BinaryTreeNode<K, V> root = getNullNode();

	void setRoot(BinaryTreeNode<K, V> root) {
		this.root = root;
	}

	public BinaryTreeNode<K, V> getRoot() {
		return root;
	}

	/**
	 * Inserts the new node as a leaf of this tree and still preserves the BST property.
	 *
	 */
	public BinaryTreeNode<K, V> insert(BinaryTreeNode<K, V> node) {
		// first node
		if (getRoot().isNull()) {
			setRoot(node);
			getRoot().setLeft(getNullNode());
			getRoot().setRight(getNullNode());
			getRoot().setParent(getNullNode());
			return getRoot();
		}

		// add new node to appropriate place to preserve the BST property
		BinaryTreeNode<K, V> parent = findParentOfNewNode(node);
		if (parent.compareTo(node) >= 0) {
			parent.setLeft(node);
		} else {
			parent.setRight(node);
		}
		node.setParent(parent);

		return node;
	}

	BinaryTreeNode<K, V> getNullNode() {
		return BinaryTreeNullNode.getInstance();
	}

	private BinaryTreeNode<K, V> findParentOfNewNode(BinaryTreeNode<K, V> newNode) {
		BinaryTreeNode<K, V> parent = getRoot();
		BinaryTreeNode<K, V> current = getRoot();
		while (current.isNotNull()) {
			parent = current;
			if (newNode.compareTo(current) > 0) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}
		}
		return parent;
	}

	List<BinaryTreeNode<K, V>> traversePostOrderRecursive() {
		return traversePostOrderRecursive(getRoot());
	}

	private List<BinaryTreeNode<K, V>> traversePostOrderRecursive(BinaryTreeNode<K, V> startingNode) {
		if (startingNode.isNull()) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode<K, V>> result = new ArrayList<>();
		result.addAll(traversePostOrderRecursive(startingNode.getLeft()));
		result.addAll(traversePostOrderRecursive(startingNode.getRight()));
		result.add(startingNode);
		return result;
	}

	List<BinaryTreeNode<K, V>> traversePostOrderIterative() {
		List<BinaryTreeNode<K, V>> result = new ArrayList<>();

		Stack<BinaryTreeNode<K, V>> stack = new Stack<>();

		// to avoid revisit a child of the current node, which will lead to endless loop
		BinaryTreeNode<K, V> lastVisited = getNullNode();

		BinaryTreeNode<K, V> currentNode = this.getRoot();
		while (currentNode.isNotNull() || !stack.isEmpty()) {
			if (currentNode.isNotNull()) {
				// save node to traverse to it later
				stack.push(currentNode);

				// traverse left
				currentNode = currentNode.getLeft();
			} else {
				// one of the child of this node is the most recently visited node
				BinaryTreeNode<K, V> parentOfLastVisited = stack.peek();

				if (parentOfLastVisited.getRight().isNotNull() && !parentOfLastVisited.getRight().equals(lastVisited)) {
					// traverse right
					currentNode = parentOfLastVisited.getRight();
				} else {
					// visit node
					BinaryTreeNode<K, V> node = stack.pop();
					result.add(node);
					lastVisited = node;

					// don't update the currentNode (== null) here because we want to go back to the just-visited node's
					// parent which is at the top of the stack. we can do this because the traversal is post-order, which
					// means that all nodes under the just-visited node have already been visited.
				}
			}
		}

		return result;
	}

	public void remove(K nodeKey) {
		BinaryTreeNode<K, V> removedNode = findNodeByKey(nodeKey);

		// no node found with the given key
		if (removedNode.isNull()) {
			return;
		}

		// this node will replace the removed node
		BinaryTreeNode<K, V> replacingNode;

		// the removed node has two children
		if (removedNode.getLeft().isNotNull() && removedNode.getRight().isNotNull()) {
			replacingNode = findDownwardSuccessorOf(removedNode);

			// because the replacing node is the successor of the removed node, it is either the right child of the
			// removed node or the left-most node of the right subtree of the removed node.
			replacingNode.setLeft(removedNode.getLeft());
			removedNode.getLeft().setParent(replacingNode);
			if (!replacingNode.equals(removedNode.getRight())) {
				transplantParent(replacingNode, replacingNode.getRight());
				replacingNode.setRight(removedNode.getRight());
				removedNode.getRight().setParent(replacingNode);
			}
			transplantParent(removedNode, replacingNode);
		}
		// the removed node has only one child or none at all
		else {
			replacingNode = removedNode.getLeft().isNotNull() ? removedNode.getLeft() : removedNode.getRight();
			transplantParent(removedNode, replacingNode);
		}
	}

	/**
	 * Makes the parent of fromNode become the parent of toNode and removes the link to fromNode from its parent.
	 *
	 */
	private void transplantParent(BinaryTreeNode<K, V> fromNode, BinaryTreeNode<K, V> toNode) {
		BinaryTreeNode<K, V> parent = fromNode.getParent();
		// fromNode is the root
		if (parent.isNull()) {
			setRoot(toNode);
		}
		// fromNode is the left child
		else if (fromNode.equals(parent.getLeft())) {
			parent.setLeft(toNode);
		}
		// fromNode is the right child
		else {
			parent.setRight(toNode);
		}

		if (toNode.isNotNull()) {
			toNode.setParent(parent);
		}
	}

	protected BinaryTreeNode<K, V> findNodeByKey(K nodeKey) {
		List<BinaryTreeNode<K, V>> nodes = InOrderTraversalIterative.traverse(getRoot());
		return nodes.stream().filter(n -> n.getKey().equals(nodeKey)).findFirst().orElse(getNullNode());
	}

	public BinaryTreeNode<K, V> findNodeByValue(V value) {
		BinaryTreeNode<K, V> current = getRoot();
		while (current.isNotNull()) {
			if (current.getValue().equals(value)) {
				return current;
			}
			if (current.getValue().compareTo(value) < 0) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}
		}
		// none found
		return getNullNode();
	}

	BinaryTreeNode<K, V> findSuccessorOf(BinaryTreeNode<K, V> node) {
		if (node.getRight().isNotNull()) {
			return findDownwardSuccessorOf(node);
		}
		return findUpwardSuccessorOf(node);
	}

	BinaryTreeNode<K, V> findPredecessorOf(BinaryTreeNode<K, V> node) {
		if (node.getLeft().isNotNull()) {
			return findDownwardPredecessorOf(node);
		}
		return findUpwardPredecessorOf(node);
	}

	BinaryTreeNode<K, V> findDownwardSuccessorOf(BinaryTreeNode<K, V> node) {
		return findMinimumNode(node.getRight());
	}

	private BinaryTreeNode<K, V> findDownwardPredecessorOf(BinaryTreeNode<K, V> node) {
		return findMaximumNode(node.getLeft());
	}

	private BinaryTreeNode<K, V> findMinimumNode(BinaryTreeNode<K, V> bstRoot) {
		return findOutermostNode(bstRoot, BinaryTreeNode::getLeft);
	}

	private BinaryTreeNode<K, V> findMaximumNode(BinaryTreeNode<K, V> bstRoot) {
		return findOutermostNode(bstRoot, BinaryTreeNode::getRight);
	}

	private BinaryTreeNode<K, V> findOutermostNode(BinaryTreeNode<K, V> from,
	                                               Function<BinaryTreeNode<K, V>, BinaryTreeNode<K, V>> nextMove) {
		if (from.isNull()) {
			return getNullNode();
		}

		BinaryTreeNode<K, V> current = from;
		BinaryTreeNode<K, V> next = nextMove.apply(current);
		while (next.isNotNull()) {
			current = next;
			next = nextMove.apply(current);
		}
		return current;
	}

	private BinaryTreeNode<K, V> findUpwardSuccessorOf(BinaryTreeNode<K, V> node) {
		return goUpUntil(node, upNode -> upNode.getValue().compareTo(node.getValue()) >= 0);
	}

	private BinaryTreeNode<K, V> findUpwardPredecessorOf(BinaryTreeNode<K, V> node) {
		return goUpUntil(node, upNode -> upNode.getValue().compareTo(node.getValue()) <= 0);
	}

	private BinaryTreeNode<K, V> goUpUntil(BinaryTreeNode<K, V> node, Predicate<BinaryTreeNode<K, V>> until) {
		BinaryTreeNode<K, V> ancestor = node.getParent();
		while (ancestor.isNotNull()) {
			if (until.test(ancestor)) {
				return ancestor;
			}
			ancestor = ancestor.getParent();
		}
		// none found
		return getNullNode();
	}

}
