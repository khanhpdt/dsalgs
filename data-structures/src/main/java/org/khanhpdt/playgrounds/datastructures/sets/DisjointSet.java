package org.khanhpdt.playgrounds.datastructures.sets;

import org.khanhpdt.playgrounds.datastructures.linkedlists.DoubleEndedLinkedList;
import org.khanhpdt.playgrounds.datastructures.nodes.DisjointSetNode;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class DisjointSet {

	private UUID key;

	private int size;

	private DoubleEndedLinkedList<DisjointSetNode> doubleEndedLinkedList;

	public DisjointSet() {
		this.key = UUID.randomUUID();
		this.size = 0;
		this.doubleEndedLinkedList = new DoubleEndedLinkedList<>();
	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = this.getKey().hashCode();
		return 31 + result * c;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DisjointSet)) {
			return false;
		}

		DisjointSet otherSet = (DisjointSet) obj;
		return this.getKey().equals(otherSet.getKey());
	}

	public UUID getKey() {
		return key;
	}

	public DisjointSetNode getHead() {
		if (doubleEndedLinkedList == null) {
			return null;
		}
		return doubleEndedLinkedList.getHead();
	}

	public DisjointSetNode getTail() {
		return doubleEndedLinkedList.getTail();
	}

	public void insert(DisjointSetNode node) {
		doubleEndedLinkedList.insertLast(node);
		node.setSet(this);
		size++;
	}

	public int size() {
		return size;
	}

	public void clear() {
		this.key = null;
		this.doubleEndedLinkedList = null;
		this.size = 0;
	}
}
