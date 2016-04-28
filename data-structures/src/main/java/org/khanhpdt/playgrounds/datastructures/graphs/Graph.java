package org.khanhpdt.playgrounds.datastructures.graphs;

import org.khanhpdt.playgrounds.datastructures.nodes.GraphNode;
import org.khanhpdt.playgrounds.datastructures.queues.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Undirected graph.
 *
 * @author khanhpdt
 */
public class Graph {

	private List<GraphNode> vertices = new ArrayList<>();

	public GraphNode addVertex(UUID vertexKey) {
		GraphNode vertex = new GraphNode(vertexKey);
		vertices.add(vertex);
		return vertex;
	}

	public GraphNode getVertex(UUID vertexKey) {
		return vertices.stream().filter(v -> v.getKey().equals(vertexKey)).findFirst().orElseGet(null);
	}

	public GraphNode getVertex(int vertexIndex) {
		return vertices.get(vertexIndex);
	}

	public void addEdge(GraphNode vertex1, GraphNode vertex2) {
		vertex1.getAdjacents().add(vertex2);
		vertex2.getAdjacents().add(vertex1);
	}

	public void addEdge(int vertex1Index, int vertex2Index) {
		GraphNode vertex1 = vertices.get(vertex1Index);
		GraphNode vertex2 = vertices.get(vertex2Index);

		vertex1.getAdjacents().add(vertex2);
		vertex2.getAdjacents().add(vertex1);
	}

	public void addEdges(int[][] pairIndexes) {
		for (int[] pairIndex : pairIndexes) {
			addEdge(pairIndex[0], pairIndex[1]);
		}
	}

	public int numberOfVertices() {
		return vertices.size();
	}

	public void breadthFirstSearch(int sourceIndex) {
		Queue<GraphNode> queue = new Queue<>();

		// put the source to the queue
		GraphNode source = vertices.get(sourceIndex);
		source.setPredecessor(null);
		source.setDistance(0);
		source.markVisited();
		queue.enqueueRear(source);

		while (!queue.isEmpty()) {
			GraphNode current = queue.dequeueFront();
			// visits all nodes adjacent to the current node
			current.getAdjacents().stream()
					// only visits not which has not been visited
					.filter(GraphNode::isNotVisited)
					.forEach(adj -> {
						// put each adjacent nodes to the queue for later processing
						adj.setPredecessor(current);
						adj.setDistance(current.getDistance() + 1);
						adj.markVisited();
						queue.enqueueRear(adj);
					});
			current.markProcessed();
		}
	}
}
