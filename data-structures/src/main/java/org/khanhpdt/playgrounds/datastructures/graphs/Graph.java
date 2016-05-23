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
		vertex1.getAdjacents().add(vertex2);
		vertex2.getAdjacents().add(vertex1);
	}

	public void addEdge(int vertex1Index, int vertex2Index) {
		GraphVertex vertex1 = vertices.get(vertex1Index);
		GraphVertex vertex2 = vertices.get(vertex2Index);

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

	public void addDirectedEdges(int[][] pairIndexes) {
		for (int[] pairIndex : pairIndexes) {
			addDirectedEdge(pairIndex[0], pairIndex[1]);
		}
	}

	private void addDirectedEdge(int vertexIndex, int adjacentVertexIndex) {
		GraphVertex vertex = getVertex(vertexIndex);
		GraphVertex adjacentVertex = getVertex(adjacentVertexIndex);

		vertex.getAdjacents().add(adjacentVertex);
	}

	public List<GraphVertex> getVertices() {
		return vertices;
	}

	public boolean hasVertex(GraphVertex vertex) {
		return vertices.stream().anyMatch(v -> v.equals(vertex));
	}

}
