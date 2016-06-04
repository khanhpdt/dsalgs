package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;

import java.util.List;

/**
 * Main idea is based on Corollary 23.2 in Introduction to algorithms, 3rd.
 * <p>
 * Basically, the MST algorithm forms an MST by adding the light edge connecting two different components into the MST.
 * The added light edge is also called the safe edge.
 * </p>
 *
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
