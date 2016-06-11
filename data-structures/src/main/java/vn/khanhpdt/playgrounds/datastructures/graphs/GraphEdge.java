package vn.khanhpdt.playgrounds.datastructures.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

/**
 * @author khanhpdt
 */
public class GraphEdge {

	private GraphVertex fromVertex;

	private GraphVertex toVertex;

	private double weight;

	public GraphEdge(GraphVertex fromVertex, GraphVertex toVertex, double weight) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.weight = weight;
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

}
