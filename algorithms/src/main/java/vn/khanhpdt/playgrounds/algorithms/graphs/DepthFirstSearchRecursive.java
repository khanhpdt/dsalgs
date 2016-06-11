package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public class DepthFirstSearchRecursive extends GraphSearch {

	@Override
	protected List<GraphVertex> doSearch(GraphVertex sourceVertex) {
		List<GraphVertex> reachableVertices = new ArrayList<>();
		doSearch(sourceVertex, null, reachableVertices);
		return reachableVertices;
	}

	private void doSearch(GraphVertex vertex, GraphVertex predecessor, List<GraphVertex> reachableVertices) {
		if (vertex.isNotDiscovered()) {
			vertex.markDiscovered(predecessor, time++);

			vertex.getAdjacents().stream()
					.filter(GraphVertex::isNotDiscovered)
					.forEach(adj -> doSearch(adj, vertex, reachableVertices));

			vertex.markVisited(time++);
			reachableVertices.add(vertex);
		}
	}

}
