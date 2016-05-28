package org.khanhpdt.playgrounds.datastructures.nodes;

import org.khanhpdt.playgrounds.datastructures.sets.DisjointSet;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class DisjointSetNode implements LinkedNodeIntf<DisjointSetNode> {
	
	private Node<UUID, Integer> content;

	private DisjointSetNode next;

	private DisjointSet set;

	public DisjointSetNode() {
		this.content = new Node<>(UUID.randomUUID());
	}

	public DisjointSetNode(UUID key) {
		this.content = new Node<>(key);
	}

	public static DisjointSetNode random() {
		UUID randomUuid = UUID.randomUUID();
		return new DisjointSetNode(randomUuid);
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
	public DisjointSetNode getNext() {
		return next;
	}

	@Override
	public void setNext(DisjointSetNode next) {
		this.next = next;
	}

	@Override
	public int hashCode() {
		int result = 17;

		int c = this.getKey().hashCode();

		return 31 + result * c;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DisjointSetNode)) {
			return false;
		}

		DisjointSetNode otherNode = (DisjointSetNode) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	public DisjointSet getSet() {
		return set;
	}

	public void setSet(DisjointSet set) {
		this.set = set;
	}
}
