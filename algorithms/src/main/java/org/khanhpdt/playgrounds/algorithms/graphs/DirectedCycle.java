package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphNode;

/**
 * @author khanhpdt
 */
public class DirectedCycle {

	private Graph graph;

	private DirectedCycle(Graph graph) {
		this.graph = graph;
	}

	private boolean exists() {
		for (GraphNode vertex : graph.getVertices()) {
			if (vertex.isNotDiscovered() && checkDirectedCycleFrom(vertex)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDirectedCycleFrom(GraphNode vertex) {
		// because the traverse is depth-first, a discovered vertex must be visited before the traverse can go back to
		// the vertex
		if (vertex.isDiscovered() && vertex.isNotVisited()) {
			return true;
		} else {
			vertex.markDiscovered();
			for (GraphNode adjacent : vertex.getAdjacents()) {
				if (checkDirectedCycleFrom(adjacent)) {
					return true;
				}
			}
			vertex.markVisited();

			return false;
		}
	}

	public static boolean checkExists(Graph graph) {
		return new DirectedCycle(graph).exists();
	}
}
