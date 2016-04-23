package org.khanhpdt.playgrounds.datastructures.trees;

import org.khanhpdt.playgrounds.datastructures.linkedlists.LinkedNode;

/**
 * @author khanhpdt
 */
public class LinkedBinaryTreeNode extends LinkedNode<LinkedBinaryTreeNode> {

	private final BinaryTreeNode binaryTreeNode;

	public LinkedBinaryTreeNode(BinaryTreeNode binaryTreeNode) {
		super(binaryTreeNode.getKey());
		this.binaryTreeNode = binaryTreeNode;
	}

	public BinaryTreeNode getNode() {
		return binaryTreeNode;
	}
}
