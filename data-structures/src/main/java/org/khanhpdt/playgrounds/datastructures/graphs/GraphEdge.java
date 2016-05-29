package org.khanhpdt.playgrounds.datastructures.graphs;

import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

/**
 * @author khanhpdt
 */
public class GraphEdge implements Comparable<GraphEdge> {

	private GraphVertex fromVertex;

	private GraphVertex toVertex;

	private double weight;

	public GraphEdge(GraphVertex fromVertex, GraphVertex toVertex, double weight) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.weight = weight;
	}

	public GraphEdge(GraphVertex fromVertex, GraphVertex toVertex) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.weight = 0;
	}

	public GraphVertex getFromVertex() {
		return fromVertex;
	}

	public GraphVertex getToVertex() {
		return toVertex;
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public int compareTo(GraphEdge o) {
		return Double.compare(this.getWeight(), o.getWeight());
	}
}
