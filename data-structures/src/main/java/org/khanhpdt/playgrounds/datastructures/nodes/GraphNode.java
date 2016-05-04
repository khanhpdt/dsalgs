package org.khanhpdt.playgrounds.datastructures.nodes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class GraphNode implements DoublyLinkedNodeIntf<GraphNode>, Comparable<GraphNode> {

	private Node<UUID, Integer> content;

	private List<GraphNode> adjacents;

	/**
	 * Distance to the source in breadth-first search (BFS) or depth-first search (DFS)
	 *
	 */
	private int distance = Integer.MAX_VALUE;

	/**
	 * Used as marker during BFS and DFS
	 *
	 */
	private Color color = Color.WHITE;

	/**
	 * Predecessor of this vertex during BFS and DFS
	 *
	 */
	private GraphNode predecessor;

	// we need to put this node into queue during BFS
	private GraphNode next;
	private GraphNode previous;

	public GraphNode(UUID key) {
		this.content = new Node<>(key);
	}

	public GraphNode(UUID key, int value) {
		this.content = new Node<>(key, value);
	}

	@Override
	public Node<UUID, Integer> getContent() {
		return this.content;
	}

	@Override
	public UUID getKey() {
		return content.getKey();
	}

	public Color getColor() {
		return color;
	}

	public List<GraphNode> getAdjacents() {
		if (adjacents == null) {
			adjacents = new ArrayList<>();
		}
		return adjacents;
	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = this.getKey().hashCode();
		return 31 + result * c;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GraphNode)) {
			return false;
		}

		GraphNode otherNode = (GraphNode) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	public int getDistance() {
		return distance;
	}

	private void setDistance(int distance) {
		this.distance = distance;
	}

	private void setPredecessor(GraphNode predecessor) {
		this.predecessor = predecessor;
	}

	@Override
	public GraphNode getNext() {
		return this.next;
	}

	@Override
	public void setNext(GraphNode next) {
		this.next = next;
	}

	@Override
	public GraphNode getPrevious() {
		return this.previous;
	}

	@Override
	public void setPrevious(GraphNode previous) {
		this.previous = previous;
	}

	public boolean isNotDiscovered() {
		return this.color == Color.WHITE;
	}

	public void markVisited() {
		this.color = Color.BLACK;
	}

	public void markDiscovered() {
		this.color = Color.GRAY;
	}

	public void markDiscoveredAsSource() {
		markDiscovered(null);
	}

	public void markDiscovered(GraphNode predecessor) {
		setPredecessor(predecessor);
		setDistance(predecessor == null ? 0 : predecessor.getDistance() + 1);
		setColor(Color.GRAY);
	}

	private void setColor(Color color) {
		this.color = color;
	}

	@Override
	public int compareTo(GraphNode o) {
		Integer thisValue = this.getContent().getValue();
		Integer otherValue = o.getContent().getValue();

		if (thisValue == null && otherValue == null) {
			return 0;
		}
		if (otherValue == null) {
			return 1;
		}
		if (thisValue == null) {
			return -1;
		}
		return thisValue.compareTo(otherValue);
	}
}
