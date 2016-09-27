package vn.khanhpdt.playgrounds.datastructures.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
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

	private BinaryTreeNode<K, V> root;

	/**
	 * Inserts the new node as a leaf of this tree and still preserves the BST property.
	 *
	 */
	public void insert(BinaryTreeNode<K, V> node) {
		// first node
		if (root == null) {
			root = node;

			root.setLeft(null);
			root.setRight(null);

			root.setParent(null);

			return;
		}

		BinaryTreeNode<K, V> parent = findParentOfNewNode(node);

		// add new node to appropriate place to preserve the BST property
		if (parent.compareTo(node) >= 0) {
			parent.setLeft(node);
		} else {
			parent.setRight(node);
		}
		node.setParent(parent);
	}

	private BinaryTreeNode<K, V> findParentOfNewNode(BinaryTreeNode<K, V> newNode) {
		BinaryTreeNode<K, V> parent = root;
		BinaryTreeNode<K, V> current = root;
		while (current != null) {
			parent = current;
			if (newNode.compareTo(current) > 0) {
				current = current.getRight();
			} else {
				current = current.getLeft();
			}
		}
		return parent;
	}

	public BinaryTreeNode<K, V> getRoot() {
		return root;
	}

	public List<BinaryTreeNode<K, V>> traverseInOrderRecursive() {
		return traverseInOrderRecursive(root);
	}

	private List<BinaryTreeNode<K, V>> traverseInOrderRecursive(BinaryTreeNode<K, V> startingNode) {
		if (startingNode == null) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode<K, V>> result = new ArrayList<>();
		result.addAll(traverseInOrderRecursive(startingNode.getLeft()));
		result.add(startingNode);
		result.addAll(traverseInOrderRecursive(startingNode.getRight()));
		return result;
	}

	public List<BinaryTreeNode<K, V>> traverseInOrderIterative() {
		List<BinaryTreeNode<K, V>> result = new ArrayList<>();

		// for temporarily holding nodes during traverse
		Stack<BinaryTreeNode<K, V>> stack = new Stack<>();

		/*
		Main idea: Keep traversing accordingly to the in-order order. When reaching the sentinel node, goes back to the
		most recently visited node which is at the top of the stack.
		*/

		BinaryTreeNode<K, V> currentNode = this.root;
		// there is no node left to traverse when currentNode is null and no node is in the stack
		while (currentNode != null || !stack.isEmpty()) {
			if (currentNode != null) {
				// save node to move to it later
				stack.push(currentNode);
				// traverse the left subtree of the node before traversing the node
				currentNode = currentNode.getLeft();
			} else {
				// visit node
				BinaryTreeNode<K, V> visitedNode = stack.pop();
				result.add(visitedNode);

				// traverse the right subtree of the node after traversing the node
				currentNode = visitedNode.getRight();
			}
		}

		return result;
	}

	public List<BinaryTreeNode<K, V>> traversePreOrderRecursive() {
		return traversePreOrderRecursive(root);
	}

	private List<BinaryTreeNode<K, V>> traversePreOrderRecursive(BinaryTreeNode<K, V> startingNode) {
		if (startingNode == null) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode<K, V>> result = new ArrayList<>();
		result.add(startingNode);
		result.addAll(traversePreOrderRecursive(startingNode.getLeft()));
		result.addAll(traversePreOrderRecursive(startingNode.getRight()));
		return result;
	}

	public List<BinaryTreeNode<K, V>> traversePreOrderIterative() {
		List<BinaryTreeNode<K, V>> result = new ArrayList<>();

		Stack<BinaryTreeNode<K, V>> stack = new Stack<>();

		BinaryTreeNode<K, V> currentNode = this.root;
		while (currentNode != null || !stack.isEmpty()) {
			if (currentNode != null) {
				// visit node
				result.add(currentNode);

				// save right to move to it later
				if (currentNode.getRight() != null) {
					stack.push(currentNode.getRight());
				}

				// traverse left
				currentNode = currentNode.getLeft();
			} else {
				// traverse right
				currentNode = stack.pop();
			}
		}

		return result;
	}

	public List<BinaryTreeNode<K, V>> traversePostOrderRecursive() {
		return traversePostOrderRecursive(root);
	}

	private List<BinaryTreeNode<K, V>> traversePostOrderRecursive(BinaryTreeNode<K, V> startingNode) {
		if (startingNode == null) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode<K, V>> result = new ArrayList<>();
		result.addAll(traversePostOrderRecursive(startingNode.getLeft()));
		result.addAll(traversePostOrderRecursive(startingNode.getRight()));
		result.add(startingNode);
		return result;
	}

	public List<BinaryTreeNode<K, V>> traversePostOrderIterative() {
		List<BinaryTreeNode<K, V>> result = new ArrayList<>();

		Stack<BinaryTreeNode<K, V>> stack = new Stack<>();

		// to avoid revisit a child of the current node, which will lead to endless loop
		BinaryTreeNode<K, V> lastVisited = null;

		BinaryTreeNode<K, V> currentNode = this.root;
		while (currentNode != null || !stack.isEmpty()) {
			if (currentNode != null) {
				// save node to traverse to it later
				stack.push(currentNode);

				// traverse left
				currentNode = currentNode.getLeft();
			} else {
				// one of the child of this node is the most recently visited node
				BinaryTreeNode<K, V> parentOfLastVisited = stack.peek();

				if (parentOfLastVisited.getRight() != null && !parentOfLastVisited.getRight().equals(lastVisited)) {
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
		if (removedNode == null) {
			return;
		}

		// the removed node has two children
		if (removedNode.getLeft() != null && removedNode.getRight() != null) {
			BinaryTreeNode<K, V> replacingNode = findDownwardSuccessorOf(removedNode);

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
			BinaryTreeNode<K, V> replacingNode = removedNode.getLeft() != null ? removedNode.getLeft() : removedNode.getRight();
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
		if (parent == null) {
			root = toNode;
		}
		// fromNode is the left child
		else if (fromNode.equals(parent.getLeft())) {
			parent.setLeft(toNode);
		}
		// fromNode is the right child
		else {
			parent.setRight(toNode);
		}

		if (toNode != null) {
			toNode.setParent(parent);
		}
	}

	private BinaryTreeNode<K, V> findNodeByKey(K nodeKey) {
		List<BinaryTreeNode<K, V>> nodes = traverseInOrderIterative();
		return nodes.stream().filter(n -> n.getKey().equals(nodeKey)).findFirst().orElseGet(null);
	}

	public BinaryTreeNode<K, V> findNodeByValue(V value) {
		BinaryTreeNode<K, V> current = root;
		while (current != null) {
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
		return null;
	}

	public BinaryTreeNode<K, V> findSuccessorOf(BinaryTreeNode<K, V> node) {
		if (node.getRight() != null) {
			return findDownwardSuccessorOf(node);
		}
		return findUpwardSuccessorOf(node);
	}

	public BinaryTreeNode<K, V> findPredecessorOf(BinaryTreeNode<K, V> node) {
		if (node.getLeft() != null) {
			return findDownwardPredecessorOf(node);
		}
		return findUpwardPredecessorOf(node);
	}

	private BinaryTreeNode<K, V> findDownwardSuccessorOf(BinaryTreeNode<K, V> node) {
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
		if (from == null) {
			return null;
		}

		BinaryTreeNode<K, V> current = from;
		BinaryTreeNode<K, V> next = nextMove.apply(current);
		while (next != null) {
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
		while (ancestor != null) {
			if (until.test(ancestor)) {
				return ancestor;
			}
			ancestor = ancestor.getParent();
		}
		// none found
		return null;
	}
}
