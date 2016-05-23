package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author khanhpdt
 */
public class TransposeGraph {

	public static Graph of(Graph graph) {
		List<GraphVertex> vertices = graph.getVertices();

		Map<UUID, List<UUID>> adjacentsOfTransposeGraph = new HashMap<>();
		vertices.forEach(v -> v.getAdjacents().forEach(adj -> {
			adjacentsOfTransposeGraph.putIfAbsent(adj.getKey(), new ArrayList<>());
			adjacentsOfTransposeGraph.get(adj.getKey()).add(v.getKey());
		}));

		List<GraphVertex> verticesOfTransposeGraph = vertices.stream()
				.map(GraphVertex::new)
				.collect(Collectors.toList());

		Map<UUID, GraphVertex> vertexOfTransposeGraphByKey = verticesOfTransposeGraph.stream()
				.collect(Collectors.toMap(GraphVertex::getKey, Function.identity()));

		verticesOfTransposeGraph.forEach(v -> {
			List<UUID> adjacentKeys = adjacentsOfTransposeGraph.get(v.getKey());
			List<GraphVertex> adjacents = adjacentKeys.stream()
					.map(vertexOfTransposeGraphByKey::get)
					.collect(Collectors.toList());
			v.setAdjacents(adjacents);
		});

		return new Graph(verticesOfTransposeGraph);
	}

}
