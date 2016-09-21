package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author khanhpdt
 */
public class TransposeGraph {

	public static <K, V> Graph<K, V> of(Graph<K, V> graph) {
		List<GraphVertex<K, V>> vertices = graph.getVertices();

		Map<K, List<K>> adjacentsOfTransposeGraph = new HashMap<>();
		vertices.forEach(v -> v.getAdjacents().forEach(adj -> {
			adjacentsOfTransposeGraph.putIfAbsent(adj.getKey(), new ArrayList<>());
			adjacentsOfTransposeGraph.get(adj.getKey()).add(v.getKey());
		}));

		List<GraphVertex<K, V>> verticesOfTransposeGraph = vertices.stream()
				.map(GraphVertex::new)
				.collect(Collectors.toList());

		Map<K, GraphVertex<K, V>> vertexOfTransposeGraphByKey = verticesOfTransposeGraph.stream()
				.collect(Collectors.toMap(GraphVertex::getKey, Function.identity()));

		verticesOfTransposeGraph.forEach(v -> {
			List<K> adjacentKeys = adjacentsOfTransposeGraph.get(v.getKey());
			List<GraphVertex<K, V>> adjacents = adjacentKeys.stream()
					.map(vertexOfTransposeGraphByKey::get)
					.collect(Collectors.toList());
			v.setAdjacents(adjacents);
		});

		return new Graph<>(verticesOfTransposeGraph);
	}

}
