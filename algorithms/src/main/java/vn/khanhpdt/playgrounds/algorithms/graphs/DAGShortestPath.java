package vn.khanhpdt.playgrounds.algorithms.graphs;

import vn.khanhpdt.playgrounds.datastructures.graphs.Graph;
import vn.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

/**
 * Algorithm to find shortest paths in a directed acyclic graph.
 *
 * @author khanhpdt
 */
public class DAGShortestPath extends SingleSourceShortestPath {

	public DAGShortestPath(Graph graph, GraphVertex source) {
		super(graph, source);
	}

	@Override
	protected void build() {
		initialize();

		// this will also check for cycles in the given graph, as topological sort cannot be applied on graphs with cycles
		Graph topologicallySortedGraph = TopologicalSort.from(graph);

		topologicallySortedGraph.getVertices()
				.forEach(v -> v.getOutgoingEdges()
						.forEach(this::relax));
	}
}
