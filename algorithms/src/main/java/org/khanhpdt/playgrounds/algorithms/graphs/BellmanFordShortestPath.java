package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bellman-Ford algorithm to find single-source shortest paths.
 *
 * @author khanhpdt
 */
public class BellmanFordShortestPath {

	private final Graph graph;

	private final GraphVertex source;

	private final Map<GraphVertex, Double> distances;

	private final Map<GraphVertex, List<GraphVertex>> paths;

	public BellmanFordShortestPath(Graph graph, GraphVertex source) {
		this.graph = graph;
		this.source = source;
		this.distances = new HashMap<>();
		this.paths = new HashMap<>();

		build();
	}

	private void build() {
		initialize();
		relaxAll();
		// can only be done after finishing all the relaxations
		checkNegativeWeightCycle();
	}

	private void initialize() {
		graph.getVertices().forEach(v -> {
			distances.put(v, Double.POSITIVE_INFINITY);
			v.setPredecessor(null);
		});

		distances.put(source, 0d);
	}

	private void relaxAll() {
		for (int i = 0; i < graph.getVertices().size() - 1; i ++) {
			graph.getEdges().forEach(this::relax);
		}
	}

	private void relax(GraphEdge edge) {
		GraphVertex vertexTo = edge.getToVertex();
		GraphVertex vertexFrom = edge.getFromVertex();

		Double distanceVertexTo = distances.get(vertexTo);
		Double distanceVertexFrom = distances.get(vertexFrom);
		if (distanceVertexTo > distanceVertexFrom + edge.getWeight()) {
			distances.put(vertexTo, distanceVertexFrom + edge.getWeight());
			vertexTo.setPredecessor(vertexFrom);
		}
	}

	private void checkNegativeWeightCycle() {
		graph.getEdges().forEach(e -> {
			Double distanceVertexTo = distances.get(e.getToVertex());
			Double distanceVertexFrom = distances.get(e.getFromVertex());

			if (distanceVertexTo > distanceVertexFrom + e.getWeight()) {
				throw new IllegalArgumentException("The graph has negative-weight cycle. Could not find shortest paths.");
			}
		});
	}

	public double getDistanceTo(GraphVertex vertex) {
		return distances.get(vertex);
	}

	public List<GraphVertex> getPathTo(GraphVertex vertex) {
		paths.putIfAbsent(vertex, getPath(vertex));
		return paths.get(vertex);
	}

	private List<GraphVertex> getPath(GraphVertex vertex) {
		List<GraphVertex> result = new ArrayList<>();

		GraphVertex current = vertex;
		while (current != null) {
			result.add(current);
			current = current.getPredecessor();
		}

		Collections.reverse(result);

		return result;
	}
}
