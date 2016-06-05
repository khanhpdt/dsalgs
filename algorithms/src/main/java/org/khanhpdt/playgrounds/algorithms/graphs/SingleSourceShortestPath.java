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
 * @author khanhpdt
 */
public abstract class SingleSourceShortestPath {

	protected final Graph graph;

	protected final GraphVertex source;

	protected final Map<GraphVertex, Double> distances;

	protected final Map<GraphVertex, List<GraphVertex>> paths;

	protected SingleSourceShortestPath(Graph graph, GraphVertex source) {
		this.graph = graph;
		this.source = source;
		this.distances = new HashMap<>();
		this.paths = new HashMap<>();

		build();
	}

	protected abstract void build();

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

		// the given vertex is not reachable from the source, thus no path exists
		if (!result.get(result.size() - 1).equals(source)) {
			return new ArrayList<>();
		}

		Collections.reverse(result);

		return result;
	}

	protected void initialize() {
		graph.getVertices().forEach(v -> {
			distances.put(v, Double.POSITIVE_INFINITY);
			v.setPredecessor(null);
		});

		distances.put(source, 0d);
	}

	protected void relax(GraphEdge edge) {
		GraphVertex vertexTo = edge.getToVertex();
		GraphVertex vertexFrom = edge.getFromVertex();

		Double distanceVertexTo = distances.get(vertexTo);
		Double distanceVertexFrom = distances.get(vertexFrom);

		// This is the key to the algorithms to find single-source shortest paths. This updates the shortest path found
		// so far if the newly found path is shorter.
		if (distanceVertexTo > distanceVertexFrom + edge.getWeight()) {
			distances.put(vertexTo, distanceVertexFrom + edge.getWeight());
			vertexTo.setPredecessor(vertexFrom);
		}
	}
}
