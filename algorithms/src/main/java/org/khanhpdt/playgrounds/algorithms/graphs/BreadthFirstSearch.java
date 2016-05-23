package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import org.khanhpdt.playgrounds.datastructures.queues.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public class BreadthFirstSearch extends GraphSearch {

	@Override
	public List<GraphVertex> doSearch(GraphVertex sourceVertex) {
		List<GraphVertex> reachableVertices = new ArrayList<>();
		Queue<GraphVertex> queue = new Queue<>();

		queue.enqueueRear(sourceVertex);
		sourceVertex.markDiscovered(null, time++);

		while (!queue.isEmpty()) {
			GraphVertex current = queue.dequeueFront();

			current.getAdjacents().stream()
					.filter(GraphVertex::isNotDiscovered)
					.forEach(adj -> {
						// breadth-first: discovers all nodes adjacent to the current node, but adds them to a queue
						// so that they will be visited before their adjacents
						adj.markDiscovered(current, time++);
						queue.enqueueRear(adj);
					});

			current.markVisited(time++);
			reachableVertices.add(current);
		}

		return reachableVertices;
	}

}
