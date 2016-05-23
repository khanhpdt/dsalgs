package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author khanhpdt
 */
public class StronglyConnectedComponents {

	public static List<Graph> of(Graph graph) {
		DepthFirstSearchRecursive dfs = new DepthFirstSearchRecursive();

		// DFS the original graph to compute the finished time of its vertices
		List<Graph> dfsGraphs = dfs.search(graph);

		Graph transposeGraph = TransposeGraph.of(graph);

		Map<UUID, GraphVertex> transposeGraphVertexByKey = transposeGraph.getVertices().stream()
				.collect(Collectors.toMap(GraphVertex::getKey, Function.identity()));

		List<Graph> stronglyConnectedComponents = new ArrayList<>();

		// traverse the vertices of the transpose graph in the decreasing order of their finished times.
		// note that the vertices in dfsGraphs are sorted in increasing order of their finished times
		for (int i = dfsGraphs.size() - 1; i >= 0; i--) {
			Graph dfsGraph = dfsGraphs.get(i);
			for (int j = dfsGraph.getVertices().size() - 1; j >= 0; j--) {
				GraphVertex transposeGraphVertex = transposeGraphVertexByKey.get(dfsGraph.getVertex(j).getKey());
				if (transposeGraphVertex.isNotDiscovered()) {
					List<GraphVertex> stronglyConnectedVertices = dfs.search(transposeGraphVertex);
					stronglyConnectedComponents.add(new Graph(stronglyConnectedVertices));
				}
			}
		}

		return stronglyConnectedComponents;
	}

}
