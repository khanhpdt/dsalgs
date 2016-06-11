package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

/**
 * @author khanhpdt
 */
public class DirectedCycle {

	private boolean exists;

	private DirectedCycle(Graph graph) {
		this.exists = check(graph);
		graph.resetAfterTraverse();
	}

	private boolean check(Graph graph) {
		for (GraphVertex vertex : graph.getVertices()) {
			if (vertex.isNotDiscovered() && checkDirectedCycleFrom(vertex)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDirectedCycleFrom(GraphVertex vertex) {
		// because the traverse is depth-first, a discovered vertex must be visited before the traverse can go back to
		// the vertex
		if (vertex.isDiscovered() && vertex.isNotVisited()) {
			return true;
		} else {
			vertex.markDiscovered();
			for (GraphVertex adjacent : vertex.getAdjacents()) {
				if (checkDirectedCycleFrom(adjacent)) {
					return true;
				}
			}
			vertex.markVisited();

			return false;
		}
	}

	private boolean exists() {
		return exists;
	}

	public static boolean checkExists(Graph graph) {
		return new DirectedCycle(graph).exists();
	}
}
