package vn.khanhpdt.playgrounds.datastructures.nodes;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public class DoublyLinkedNode implements DoublyLinkedNodeIntf<DoublyLinkedNode> {

	private Node<UUID, Integer> content;

	private DoublyLinkedNode previous;

	private DoublyLinkedNode next;

	public DoublyLinkedNode(UUID key) {
		this.content = new Node<>(key);
	}

	public DoublyLinkedNode(Integer value) {
		this.content = new Node<>(UUID.randomUUID(), value);
	}

	public static DoublyLinkedNode random() {
		UUID randomUuid = UUID.randomUUID();
		return new DoublyLinkedNode(randomUuid);
	}

	@Override
	public DoublyLinkedNode getPrevious() {
		return previous;
	}

	@Override
	public void setPrevious(DoublyLinkedNode previous) {
		this.previous = previous;
	}

	@Override
	public Node<UUID, Integer> getContent() {
		return this.content;
	}

	@Override
	public UUID getKey() {
		return getContent().getKey();
	}

	@Override
	public DoublyLinkedNode getNext() {
		return this.next;
	}

	@Override
	public void setNext(DoublyLinkedNode next) {
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
		if (!(obj instanceof DoublyLinkedNode)) {
			return false;
		}

		DoublyLinkedNode otherNode = (DoublyLinkedNode) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	@Override
	public String toString() {
		return "DoublyLinkedNode: {" + getKey().toString() + ", " + getContent().getValue().toString() + "}";
	}
}
