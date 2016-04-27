package org.khanhpdt.playgrounds.datastructures.nodes;

import java.util.Random;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class BinaryTreeNode implements Comparable<BinaryTreeNode>, LinkedNodeIntf<BinaryTreeNode> {

	private Node<UUID, Integer> content;

	private BinaryTreeNode left;

	private BinaryTreeNode right;

	private BinaryTreeNode next;

	public BinaryTreeNode(Integer value) {
		this.content = new Node<>(UUID.randomUUID(), value);
	}

	public Integer getValue() {
		return content.getValue();
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
		return getValue().compareTo(o.getValue());
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
		return getKey().equals(that.getKey());

	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = getKey().hashCode();
		return 31 + result * c;
	}

	public static BinaryTreeNode random() {
		return new BinaryTreeNode(new Random().nextInt());
	}

	@Override
	public Node<UUID, Integer> getContent() {
		return content;
	}

	@Override
	public UUID getKey() {
		return content.getKey();
	}

	@Override
	public BinaryTreeNode getNext() {
		return this.next;
	}

	@Override
	public void setNext(BinaryTreeNode next) {
		this.next = next;
	}

}
