package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public abstract class GraphSearch {

	protected int time = 1;

	public List<GraphVertex> search(GraphVertex sourceVertex) {
		return doSearch(sourceVertex);
	}

	public List<Graph> search(Graph graph) {
		List<Graph> graphs = new ArrayList<>();

		graph.getVertices().stream()
				.filter(GraphVertex::isNotDiscovered)
				.forEach(v -> graphs.add(new Graph(this.search(v))));

		return graphs;
	}

	protected abstract List<GraphVertex> doSearch(GraphVertex sourceVertex);

}
