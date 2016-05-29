package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;

import java.util.List;

/**
 * @author khanhpdt
 */
public abstract class MinimumSpanningTree {

	private Graph graph;

	protected MinimumSpanningTree(Graph graph) {
		this.graph = graph;
	}

	public Graph getGraph() {
		return graph;
	}

	protected abstract List<GraphEdge> get();

}
