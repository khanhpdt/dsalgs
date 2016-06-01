package org.khanhpdt.playgrounds.datastructures.nodes;

import org.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class GraphVertex implements DoublyLinkedNodeIntf<GraphVertex> {

	private Node<UUID, Integer> content;

	private List<GraphVertex> adjacents = new ArrayList<>();

	// outgoing edges from this vertex
	private Map<GraphVertex, GraphEdge> edges = new HashMap<>();

	// for searching (BFS, DFS)
	private int distance = Integer.MAX_VALUE;
	private Color color = Color.WHITE;
	private GraphVertex predecessor;
	private int discoveredTime;
	private int visitedTime;

	// we need to put this vertex into queue during BFS
	private GraphVertex next;
	private GraphVertex previous;

	// for finding minimum spanning tree. this represents the minimum weight among the edges connecting
	// this vertex to the vertices already in the MST
	private double minWeightToMST;

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

	public void markDiscovered(GraphVertex predecessor, int time) {
		setPredecessor(predecessor);
		setDistance(predecessor == null ? 0 : predecessor.getDistance() + 1);
		setColor(Color.GRAY);
		setDiscoveredTime(time);
	}

	private void setColor(Color color) {
		this.color = color;
	}

	public boolean isNotVisited() {
		return this.color != Color.BLACK;
	}

	public void setAdjacents(List<GraphVertex> adjacents) {
		this.adjacents = adjacents;
	}

	private void setDiscoveredTime(int discoveredTime) {
		this.discoveredTime = discoveredTime;
	}

	private void setVisitedTime(int visitedTime) {
		this.visitedTime = visitedTime;
	}

	public List<GraphEdge> getEdges() {
		return new ArrayList<>(edges.values());
	}

	public void addEdge(GraphVertex toVertex) {
		addEdge(toVertex, 0);
	}

	public void addEdge(GraphVertex toVertex, int weight) {
		adjacents.add(toVertex);
		edges.put(toVertex, new GraphEdge(this, toVertex, weight));
	}

	public double getWeightOfEdgeTo(GraphVertex toVertex) {
		return edges.get(toVertex).getWeight();
	}

	public GraphEdge getEdgeTo(GraphVertex toVertex) {
		return edges.get(toVertex);
	}

	public double getMinWeightToMST() {
		return minWeightToMST;
	}

	public void setMinWeightToMST(double minWeightToMST) {
		this.minWeightToMST = minWeightToMST;
	}

	public void setPredecessor(GraphVertex predecessor) {
		this.predecessor = predecessor;
	}

	public GraphVertex getPredecessor() {
		return predecessor;
	}
}
