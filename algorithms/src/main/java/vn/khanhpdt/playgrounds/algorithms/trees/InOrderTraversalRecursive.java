package vn.khanhpdt.playgrounds.algorithms.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author khanhpdt
 */
class InOrderTraversalRecursive {

	static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> traverse(BinaryTreeNode<K, V> sourceNode) {
		return traverseInOrderRecursive(sourceNode);
	}

	private static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> traverseInOrderRecursive(
			BinaryTreeNode<K, V> startingNode) {

		if (startingNode.isNull()) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode<K, V>> result = new ArrayList<>();
		result.addAll(traverseInOrderRecursive(startingNode.getLeft()));
		result.add(startingNode);
		result.addAll(traverseInOrderRecursive(startingNode.getRight()));
		return result;
	}
}
