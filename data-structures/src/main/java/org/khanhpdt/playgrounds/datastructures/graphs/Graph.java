package org.khanhpdt.playgrounds.datastructures.graphs;

import org.khanhpdt.playgrounds.datastructures.nodes.GraphNode;
import org.khanhpdt.playgrounds.datastructures.queues.Queue;
import org.khanhpdt.playgrounds.datastructures.stacks.Stack;

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

	public GraphNode addVertex(UUID key, int value) {
		GraphNode vertex = new GraphNode(key, value);
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

		GraphNode source = vertices.get(sourceIndex);
		source.markDiscoveredAsSource();
		queue.enqueueRear(source);

		while (!queue.isEmpty()) {
			GraphNode current = queue.dequeueFront();
			current.getAdjacents().stream()
					.filter(GraphNode::isNotDiscovered)
					.forEach(adj -> {
						// breadth-first: discovers all nodes adjacent to the current node, but adds them to a queue
						// so that they will be visited before their adjacents
						adj.markDiscovered(current);
						queue.enqueueRear(adj);
					});
			current.markVisited();
		}
	}

	public void depthFirstSearch(int sourceIndex) {
		Stack<GraphNode> stack = new Stack<>();

		GraphNode source = vertices.get(sourceIndex);
		source.markDiscoveredAsSource();
		stack.push(source);

		while (!stack.isEmpty()) {
			GraphNode current = stack.pop();
			current.getAdjacents().stream()
					.filter(GraphNode::isNotDiscovered)
					.forEach(adj -> {
						// depth-first: discovers all nodes adjacent to the current node, but adds them to a stack
						// so that they will be visited after their adjacents
						adj.markDiscovered(current);
						stack.push(adj);
					});
			current.markVisited();
		}
	}

	public void recursiveDepthFirstSearch(int sourceIndex) {
		GraphNode source = vertices.get(sourceIndex);
		recursiveDepthFirstSearch(null, source);
	}

	private void recursiveDepthFirstSearch(GraphNode predecessor, GraphNode vertex) {
		if (vertex.isNotDiscovered()) {
			vertex.markDiscovered(predecessor);
			vertex.getAdjacents().stream()
					.filter(GraphNode::isNotDiscovered)
					.forEach(adj -> recursiveDepthFirstSearch(vertex, adj));
			vertex.markVisited();
		}
	}
}
