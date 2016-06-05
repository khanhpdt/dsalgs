package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import org.khanhpdt.playgrounds.datastructures.queues.MinPriorityQueue;

/**
 * @author khanhpdt
 */
public class DijkstraShortestPath extends SingleSourceShortestPath {

	public DijkstraShortestPath(Graph graph, GraphVertex source) {
		super(graph, source);
	}

	@Override
	protected void build() {
		checkNonNegativeWeightEdges();

		initialize();

		// vertices whose shortest paths from the source have not been settled yet
		MinPriorityQueue<GraphVertex> notSettledVertices = new MinPriorityQueue<>(graph.getVertices(),
				(v1, v2) -> Double.compare(distances.get(v1), distances.get(v2)));

		while (notSettledVertices.isNotEmpty()) {
			// greedy heuristic: select the vertex with the smallest shortest paths
			GraphVertex settledVertex = notSettledVertices.extractMin();

			// update the shortest paths of the adjacents of the settled vertex, because the just-settled vertex can
			// lead to shorter paths than the ones found so far.
			settledVertex.getOutgoingEdges().forEach(this::relax);
		}
	}

	private void checkNonNegativeWeightEdges() {
		graph.getEdges().forEach(e -> {
			if (e.getWeight() < 0) {
				throw new IllegalArgumentException("The given graph has negative-weight edges. Dijkstra algorithm cannot be applied.");
			}
		});
	}

}
