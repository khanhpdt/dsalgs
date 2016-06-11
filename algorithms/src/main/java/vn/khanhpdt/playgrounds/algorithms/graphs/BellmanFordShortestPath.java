package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

/**
 * Bellman-Ford algorithm to find single-source shortest paths.
 *
 * @author khanhpdt
 */
public class BellmanFordShortestPath extends SingleSourceShortestPath {

	public BellmanFordShortestPath(Graph graph, GraphVertex source) {
		super(graph, source);
	}

	@Override
	protected void build() {
		initialize();
		relaxAll();
		// can only be done after finishing all the relaxations
		checkNegativeWeightCycle();
	}

	private void relaxAll() {
		for (int i = 0; i < graph.getVertices().size() - 1; i ++) {
			graph.getEdges().forEach(this::relax);
		}
	}

	private void checkNegativeWeightCycle() {
		graph.getEdges().forEach(e -> {
			Double distanceVertexTo = distances.get(e.getToVertex());
			Double distanceVertexFrom = distances.get(e.getFromVertex());

			if (distanceVertexTo > distanceVertexFrom + e.getWeight()) {
				throw new IllegalArgumentException("The graph has negative-weight cycle. Could not find shortest paths.");
			}
		});
	}

}
