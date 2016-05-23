package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.linkedlists.LinkedLists;
import org.khanhpdt.playgrounds.datastructures.linkedlists.SinglyLinkedList;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.List;

/**
 * @author khanhpdt
 */
public class TopologicalSort {

	private Graph graph;

	private TopologicalSort(Graph graph) {
		this.graph = graph;
	}

	private Graph doSort() {
		if (DirectedCycle.checkExists(graph)) {
			throw new IllegalArgumentException("Graph has directed cycles. Topological sort is not possible.");
		}

		SinglyLinkedList<GraphVertex> sortedVertices = new SinglyLinkedList<>();
		topologicalSort(graph.getVertex(0), sortedVertices);

		List<GraphVertex> sortedVerticesList = LinkedLists.traverse(sortedVertices);
		return new Graph(sortedVerticesList);
	}

	private void topologicalSort(GraphVertex vertex, SinglyLinkedList<GraphVertex> sortedVertices) {
		if (vertex.isNotDiscovered()) {
			vertex.markDiscovered();
			vertex.getAdjacents().stream()
					.filter(GraphVertex::isNotDiscovered)
					.forEach(adj -> topologicalSort(adj, sortedVertices));

			// Because this traverse is depth-first, when a vertex is visited, all of its adjacents are already visited.
			// Thus, we add the vertex to the front of the linked list to make sure that it is positioned before its adjacents.
			vertex.markVisited();
			sortedVertices.insert(vertex);
		}
	}

	public static boolean verify(Graph graph) {
		List<GraphVertex> vertices = graph.getVertices();
		for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {
			for (GraphVertex adjacent : vertices.get(vertexIndex).getAdjacents()) {
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
		return new TopologicalSort(graph).doSort();
	}
}
