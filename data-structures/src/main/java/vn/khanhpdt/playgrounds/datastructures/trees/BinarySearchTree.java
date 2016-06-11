package vn.khanhpdt.playgrounds.datastructures.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

import java.util.*;

/**
 * @author khanhpdt
 */
public class BinarySearchTree {

	private BinaryTreeNode root;

	/**
	 * Inserts the new node as a leaf of this tree and still preserves the BST property.
	 *
	 */
	public void insert(BinaryTreeNode node) {
		// first node
		if (root == null) {
			root = node;
			// reset the root's children
			root.setLeft(null);
			root.setRight(null);
			return;
		}

		BinaryTreeNode parent = findParentOfNewNode(node);

		// add new node to appropriate place to preserve the BST property
		if (parent.compareTo(node) >= 0) {
			parent.setLeft(node);
		} else {
			parent.setRight(node);
		}
	}

	private BinaryTreeNode findParentOfNewNode(BinaryTreeNode newNode) {
		BinaryTreeNode parent = root;
		BinaryTreeNode current = root;
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

	public BinaryTreeNode getRoot() {
		return root;
	}

	public List<BinaryTreeNode> traverseInOrderRecursive() {
		return traverseInOrderRecursive(root);
	}

	private List<BinaryTreeNode> traverseInOrderRecursive(BinaryTreeNode startingNode) {
		if (startingNode == null) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode> result = new ArrayList<>();
		result.addAll(traverseInOrderRecursive(startingNode.getLeft()));
		result.add(startingNode);
		result.addAll(traverseInOrderRecursive(startingNode.getRight()));
		return result;
	}

	public List<BinaryTreeNode> traverseInOrderIterative() {
		List<BinaryTreeNode> result = new ArrayList<>();

		// for temporarily holding nodes during traverse
		vn.khanhpdt.playgrounds.datastructures.stacks.Stack<BinaryTreeNode> stack = new vn.khanhpdt.playgrounds.datastructures.stacks.Stack<>();

		/*
		Main idea: Keep traversing accordingly to the in-order order. When reaching the sentinel node, goes back to the
		most recently visited node which is at the top of the stack.
		*/

		BinaryTreeNode currentNode = this.root;
		// there is no node left to traverse when currentNode is null and no node is in the stack
		while (currentNode != null || !stack.isEmpty()) {
			if (currentNode != null) {
				// save node to move to it later
				stack.push(currentNode);
				// traverse the left subtree of the node before traversing the node
				currentNode = currentNode.getLeft();
			} else {
				// visit node
				BinaryTreeNode visitedNode = stack.pop();
				result.add(visitedNode);

				// traverse the right subtree of the node after traversing the node
				currentNode = visitedNode.getRight();
			}
		}

		return result;
	}

	public List<BinaryTreeNode> traversePreOrderRecursive() {
		return traversePreOrderRecursive(root);
	}

	private List<BinaryTreeNode> traversePreOrderRecursive(BinaryTreeNode startingNode) {
		if (startingNode == null) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode> result = new ArrayList<>();
		result.add(startingNode);
		result.addAll(traversePreOrderRecursive(startingNode.getLeft()));
		result.addAll(traversePreOrderRecursive(startingNode.getRight()));
		return result;
	}

	public List<BinaryTreeNode> traversePreOrderIterative() {
		List<BinaryTreeNode> result = new ArrayList<>();

		vn.khanhpdt.playgrounds.datastructures.stacks.Stack<BinaryTreeNode> stack = new vn.khanhpdt.playgrounds.datastructures.stacks.Stack<>();

		BinaryTreeNode currentNode = this.root;
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

	public List<BinaryTreeNode> traversePostOrderRecursive() {
		return traversePostOrderRecursive(root);
	}

	private List<BinaryTreeNode> traversePostOrderRecursive(BinaryTreeNode startingNode) {
		if (startingNode == null) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode> result = new ArrayList<>();
		result.addAll(traversePostOrderRecursive(startingNode.getLeft()));
		result.addAll(traversePostOrderRecursive(startingNode.getRight()));
		result.add(startingNode);
		return result;
	}

	public List<BinaryTreeNode> traversePostOrderIterative() {
		List<BinaryTreeNode> result = new ArrayList<>();

		vn.khanhpdt.playgrounds.datastructures.stacks.Stack<BinaryTreeNode> stack = new vn.khanhpdt.playgrounds.datastructures.stacks.Stack<>();

		// to avoid revisit a child of the current node, which will lead to endless loop
		BinaryTreeNode lastVisited = null;

		BinaryTreeNode currentNode = this.root;
		while (currentNode != null || !stack.isEmpty()) {
			if (currentNode != null) {
				// save node to traverse to it later
				stack.push(currentNode);

				// traverse left
				currentNode = currentNode.getLeft();
			} else {
				// one of the child of this node is the most recently visited node
				BinaryTreeNode parentOfLastVisited = stack.peek();

				if (parentOfLastVisited.getRight() != null && !parentOfLastVisited.getRight().equals(lastVisited)) {
					// traverse right
					currentNode = parentOfLastVisited.getRight();
				} else {
					// visit node
					BinaryTreeNode node = stack.pop();
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

	public void remove(UUID nodeKey) {
		BinaryTreeNode removedNode = findNode(nodeKey);

		// no node found with the given key
		if (removedNode == null) {
			return;
		}

		// the removed node has two children
		if (removedNode.getLeft() != null && removedNode.getRight() != null) {
			BinaryTreeNode replacingNode = findSuccessorOf(removedNode);

			// because the replacing node is the successor of the removed node, it is either the right child of the
			// removed node or the left-most node of the right subtree of the removed node.
			replacingNode.setLeft(removedNode.getLeft());
			if (!replacingNode.equals(removedNode.getRight())) {
				transplantParent(replacingNode, replacingNode.getRight());
				replacingNode.setRight(removedNode.getRight());
			}

			transplantParent(removedNode, replacingNode);
		}
		// the removed node has only one child or none at all
		else {
			BinaryTreeNode replacingNode = removedNode.getLeft() != null ? removedNode.getLeft() : removedNode.getRight();
			transplantParent(removedNode, replacingNode);
		}
	}

	private BinaryTreeNode findSuccessorOf(BinaryTreeNode node) {
		return findMinimumNode(node.getRight());
	}

	private BinaryTreeNode findMinimumNode(BinaryTreeNode bstRoot) {
		if (bstRoot == null) {
			return null;
		}

		BinaryTreeNode current = bstRoot;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}

	/**
	 * Makes the parent of fromNode become the parent of toNode and removes the link to fromNode from its parent.
	 *
	 */
	private void transplantParent(BinaryTreeNode fromNode, BinaryTreeNode toNode) {
		BinaryTreeNode parent = findParentOf(fromNode);
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
	}

	private BinaryTreeNode findNode(UUID nodeKey) {
		List<BinaryTreeNode> nodes = traverseInOrderIterative();
		return nodes.stream().filter(n -> n.getKey().equals(nodeKey)).findFirst().orElseGet(null);
	}

	/**
	 * @param node  must be in the tree
	 * @throws NoSuchElementException if the given node is not in the tree
	 * @return parent of the given node
	 */
	private BinaryTreeNode findParentOf(BinaryTreeNode node) {
		BinaryTreeNode parent = null;
		BinaryTreeNode currentNode = root;
		while (currentNode != null && !currentNode.equals(node)) {
			parent = currentNode;
			if (node.compareTo(currentNode) < 0) {
				currentNode = currentNode.getLeft();
			} else if (node.compareTo(currentNode) > 0) {
				currentNode = currentNode.getRight();
			}
		}

		if (currentNode == null) {
			throw new NoSuchElementException();
		}

		return parent;
	}
}
