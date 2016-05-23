package org.khanhpdt.playgrounds.datastructures.nodes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class GraphVertex implements DoublyLinkedNodeIntf<GraphVertex>, Comparable<GraphVertex> {

	private Node<UUID, Integer> content;

	private List<GraphVertex> adjacents;

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
	private GraphVertex predecessor;

	private int discoveredTime;

	private int visitedTime;

	// we need to put this vertex into queue during BFS
	private GraphVertex next;
	private GraphVertex previous;

	public GraphVertex(UUID key) {
		this.content = new Node<>(key);
	}

	public GraphVertex(UUID key, int value) {
		this.content = new Node<>(key, value);
	}

	public GraphVertex(GraphVertex other) {
		this.content = new Node<>(other.getContent().getKey(), other.getContent().getValue());
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

	public List<GraphVertex> getAdjacents() {
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
		if (!(obj instanceof GraphVertex)) {
			return false;
		}

		GraphVertex otherNode = (GraphVertex) obj;
		return this.getKey().equals(otherNode.getKey());
	}

	public int getDistance() {
		return distance;
	}

	private void setDistance(int distance) {
		this.distance = distance;
	}

	private void setPredecessor(GraphVertex predecessor) {
		this.predecessor = predecessor;
	}

	@Override
	public GraphVertex getNext() {
		return this.next;
	}

	@Override
	public void setNext(GraphVertex next) {
		this.next = next;
	}

	@Override
	public GraphVertex getPrevious() {
		return this.previous;
	}

	@Override
	public void setPrevious(GraphVertex previous) {
		this.previous = previous;
	}

	public boolean isNotDiscovered() {
		return this.color == Color.WHITE;
	}

	public boolean isDiscovered() {
		return this.color != Color.WHITE;
	}

	public void markVisited() {
		setColor(Color.BLACK);
	}

	public void markVisited(int time) {
		setColor(Color.BLACK);
		setVisitedTime(time);
	}

	public void markDiscovered() {
		this.color = Color.GRAY;
	}

	public void markDiscoveredAsSource() {
		markDiscovered(null, 0);
	}

	public void markDiscovered(GraphVertex predecessor, int time) {
		setPredecessor(predecessor);
		setDistance(predecessor == null ? 0 : predecessor.getDistance() + 1);
		setColor(Color.GRAY);
		setDiscoveredTime(time);
	}

	private void setColor(Color color) {
		this.color = color;
	}

	@Override
	public int compareTo(GraphVertex o) {
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

	public boolean isNotVisited() {
		return this.color != Color.BLACK;
	}

	public void setAdjacents(List<GraphVertex> adjacents) {
		this.adjacents = adjacents;
	}

	public void setDiscoveredTime(int discoveredTime) {
		this.discoveredTime = discoveredTime;
	}

	public void setVisitedTime(int visitedTime) {
		this.visitedTime = visitedTime;
	}
}
