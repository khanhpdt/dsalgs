package org.khanhpdt.playgrounds.datastructures.trees;

import java.util.Random;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class BinaryTreeNode implements Comparable<BinaryTreeNode> {

	private final UUID key;

	private final Integer value;

	private BinaryTreeNode left;

	private BinaryTreeNode right;

	public BinaryTreeNode(Integer value) {
		this.key = UUID.randomUUID();
		this.value = value;
	}

	public UUID getKey() {
		return key;
	}

	public Integer getValue() {
		return value;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	@Override
	public int compareTo(BinaryTreeNode o) {
		return value.compareTo(o.getValue());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}

		BinaryTreeNode that = (BinaryTreeNode) o;
		return key.equals(that.getKey());

	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = key.hashCode();
		return 31 + result * c;
	}

	public static BinaryTreeNode random() {
		return new BinaryTreeNode(new Random().nextInt());
	}

}
