package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.linkedlists.LinkedLists;
import org.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphNode;

import java.util.List;

/**
 * @author khanhpdt
 */
public class TopologicalSort {

	private Graph graph;

	private TopologicalSort(Graph graph) {
		this.graph = graph;
	}

	private Graph get() {
		if (DirectedCycle.checkExists(graph)) {
			throw new IllegalArgumentException("Graph has directed cycles. Topological sort is not possible.");
		}

		SinglyLinkedList<GraphNode> sortedVertices = new SinglyLinkedList<>();
		topologicalSort(graph.getVertex(0), sortedVertices);

		List<GraphNode> sortedVerticesList = LinkedLists.traverse(sortedVertices);
		return new Graph(sortedVerticesList);
	}

	private void topologicalSort(GraphNode vertex, SinglyLinkedList<GraphNode> sortedVertices) {
		if (vertex.isNotDiscovered()) {
			vertex.markDiscovered();
			vertex.getAdjacents().stream()
					.filter(GraphNode::isNotDiscovered)
					.forEach(adj -> topologicalSort(adj, sortedVertices));

			// Because this traverse is depth-first, when a vertex is visited, all of its adjacents are already visited.
			// Thus, we add the vertex to the front of the linked list to make sure that it is positioned before its adjacents.
			vertex.markVisited();
			sortedVertices.insert(vertex);
		}
	}

	public static boolean checkExists(Graph graph) {
		List<GraphNode> vertices = graph.getVertices();
		for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {
			for (GraphNode adjacent : vertices.get(vertexIndex).getAdjacents()) {
				int adjacentIndex = vertices.indexOf(adjacent);
				// a graph is topologically sorted if any of its vertices stands before its adjacents in the vertices list
				if (vertexIndex > adjacentIndex) {
					return false;
				}
			}
		}
		return true;
	}

	public static Graph from(Graph graph) {
		return new TopologicalSort(graph).get();
	}
}
