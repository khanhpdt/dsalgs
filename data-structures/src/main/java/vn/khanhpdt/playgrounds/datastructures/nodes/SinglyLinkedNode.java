package vn.khanhpdt.playgrounds.datastructures.nodes;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class SinglyLinkedNode implements LinkedNodeIntf<SinglyLinkedNode> {

	private Node<UUID, Integer> content;

	private SinglyLinkedNode next;

	public SinglyLinkedNode(UUID key) {
		this.content = new Node<>(key);
	}

	public SinglyLinkedNode(Node<UUID, Integer> content) {
		this.content = content;
	}

	public static SinglyLinkedNode random() {
		UUID randomUuid = UUID.randomUUID();
		return new SinglyLinkedNode(randomUuid);
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
	public SinglyLinkedNode getNext() {
		return next;
	}

	@Override
	public void setNext(SinglyLinkedNode next) {
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
		if (!(obj instanceof SinglyLinkedNode)) {
			return false;
		}

		SinglyLinkedNode otherNode = (SinglyLinkedNode) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	public void cloneContent(SinglyLinkedNode other) {
		this.content = other.getContent();
	}

	public void removeContent() {
		this.content = null;
	}

	public Integer getValue() {
		return getContent().getValue();
	}

}
