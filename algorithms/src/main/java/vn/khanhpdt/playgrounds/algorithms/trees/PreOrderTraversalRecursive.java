package vn.khanhpdt.playgrounds.algorithms.trees;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author khanhpdt
 */
public class PreOrderTraversalRecursive {

	static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> traverse(BinaryTreeNode<K, V> sourceNode) {
		return doTraverse(sourceNode);
	}

	private static <K, V extends Comparable<V>> List<BinaryTreeNode<K, V>> doTraverse(BinaryTreeNode<K, V> startingNode) {
		if (startingNode.isNull()) {
			return Collections.emptyList();
		}

		List<BinaryTreeNode<K, V>> result = new ArrayList<>();
		result.add(startingNode);
		result.addAll(doTraverse(startingNode.getLeft()));
		result.addAll(doTraverse(startingNode.getRight()));
		return result;
	}

}
