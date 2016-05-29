package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;
import org.khanhpdt.playgrounds.datastructures.nodes.DisjointSetNode;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import org.khanhpdt.playgrounds.datastructures.sets.DisjointSets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Kruskal algorithm to find the minimum spanning tree of a graph.
 *
 * @author khanhpdt
 */
public class KruskalMST extends MinimumSpanningTree {

	public KruskalMST(Graph graph) {
		super(graph);
	}

	@Override
	public List<GraphEdge> get() {
		Map<GraphVertex, DisjointSetNode> disjointSetNodes = getGraph().getVertices().stream()
				.collect(Collectors.toMap(Function.identity(), v -> new DisjointSetNode(v.getKey())));

		disjointSetNodes.values().forEach(DisjointSets::makeSet);

		List<GraphEdge> edges = getGraph().getEdges();
		Collections.sort(edges);

		List<GraphEdge> result = new ArrayList<>();
		edges.forEach(e -> {
			DisjointSetNode nodeForFromVertex = disjointSetNodes.get(e.getFromVertex());
			DisjointSetNode nodeForToVertex = disjointSetNodes.get(e.getToVertex());
			// Only add the new edge if it is formed by vertices in different sets. If nodes are in the same set, they
			// are in the same connected component, meaning that there is already a link between them. Now if we add a
			// direct link between them to the tree, a cycle will appear, which makes the tree not a tree anymore.
			if (!DisjointSets.findSet(nodeForFromVertex).equals(DisjointSets.findSet(nodeForToVertex))) {
				result.add(e);
				DisjointSets.union(nodeForFromVertex, nodeForToVertex);
			}
		});

		return result;
	}
}