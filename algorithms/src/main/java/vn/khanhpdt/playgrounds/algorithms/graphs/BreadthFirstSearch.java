package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import vn.khanhpdt.playgrounds.datastructures.queues.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public class BreadthFirstSearch<K, V> extends GraphSearch<K, V> {

	@Override
	public List<GraphVertex<K, V>> doSearch(GraphVertex<K, V> sourceVertex) {
		List<GraphVertex<K, V>> reachableVertices = new ArrayList<>();
		Queue<GraphVertex<K, V>> queue = new Queue<>();

		queue.enqueueRear(sourceVertex);
		sourceVertex.markDiscovered(null, time++);

		while (!queue.isEmpty()) {
			GraphVertex<K, V> current = queue.dequeueFront();

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
