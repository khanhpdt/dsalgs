package org.khanhpdt.playgrounds.datastructures.graphs;

import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author khanhpdt
 */
public class Graph {

	private List<GraphVertex> vertices;

	public Graph() {
		this.vertices = new ArrayList<>();
	}

	public Graph(List<GraphVertex> vertices) {
		this.vertices = vertices;
	}

	public GraphVertex addVertex(UUID vertexKey) {
		GraphVertex vertex = new GraphVertex(vertexKey);
		vertices.add(vertex);
		return vertex;
	}

	public GraphVertex addVertex(UUID key, int value) {
		GraphVertex vertex = new GraphVertex(key, value);
		vertices.add(vertex);
		return vertex;
	}

	public GraphVertex getVertex(UUID vertexKey) {
		return vertices.stream().filter(v -> v.getKey().equals(vertexKey)).findFirst().orElseGet(null);
	}

	public GraphVertex getVertex(int vertexIndex) {
		return vertices.get(vertexIndex);
	}

	public List<GraphVertex> getVertices(int[] vertexIndices) {
		List<GraphVertex> result = new ArrayList<>();
		Arrays.stream(vertexIndices).forEach(i -> result.add(vertices.get(i)));
		return result;
	}

	public void addEdge(GraphVertex vertex1, GraphVertex vertex2) {
		addEdge(vertex1, vertex2, 0);
	}

	private void addEdge(GraphVertex vertex1, GraphVertex vertex2, int weight) {
		vertex1.addEdge(vertex2, weight);
		vertex2.addEdge(vertex1, weight);
	}

	public void addEdge(int vertex1Index, int vertex2Index) {
		addEdge(vertices.get(vertex1Index), vertices.get(vertex2Index), 0);
	}

	private void addEdge(int vertex1Index, int vertex2Index, int weight) {
		addEdge(vertices.get(vertex1Index), vertices.get(vertex2Index), weight);
	}

	public void addEdges(int[][] pairIndexes) {
		for (int[] pairIndex : pairIndexes) {
			if (pairIndex.length == 2) {
				addEdge(pairIndex[0], pairIndex[1]);
			} else if (pairIndex.length == 3) {
				addEdge(pairIndex[0], pairIndex[1], pairIndex[2]);
			}
		}
	}

	public int numberOfVertices() {
		return vertices.size();
	}

	public void addDirectedEdges(int[][] pairIndexes) {
		for (int[] pairIndex : pairIndexes) {
			addDirectedEdge(pairIndex[0], pairIndex[1]);
		}
	}

	private void addDirectedEdge(int vertexIndex, int adjacentVertexIndex) {
		getVertex(vertexIndex).addEdge(getVertex(adjacentVertexIndex));
	}

	public List<GraphVertex> getVertices() {
		return vertices;
	}

	public boolean hasVertex(GraphVertex vertex) {
		return vertices.stream().anyMatch(v -> v.equals(vertex));
	}

	public List<GraphEdge> getEdges() {
		List<GraphEdge> edges = new ArrayList<>();
		vertices.forEach(v -> edges.addAll(v.getEdges()));
		return edges;
	}

}
