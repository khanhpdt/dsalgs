package org.khanhpdt.playgrounds.algorithms.graphs;

import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.graphs.GraphEdge;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;
import org.khanhpdt.playgrounds.datastructures.queues.MinPriorityQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khanhpdt
 */
public class PrimMST extends MinimumSpanningTree {

	public PrimMST(Graph graph) {
		super(graph);
	}

	@Override
	protected List<GraphEdge> get() {
		// the value of each vertex will represent the minimum weight among the edges connecting the vertex
		// to the vertices already in the MST
		getGraph().getVertices().forEach(v -> v.setMinWeightToMST(Double.MAX_VALUE));

		// use a min-priority queue to accelerate the process of finding the safe edge and also to hold
		// the vertices not yet in the MST
		MinPriorityQueue<GraphVertex> minPriorityQueue = new MinPriorityQueue<>(getGraph().getVertices(),
				(v1, v2) -> Double.compare(v1.getMinWeightToMST(), v2.getMinWeightToMST()));

		List<GraphEdge> result = new ArrayList<>();
		while (minPriorityQueue.isNotEmpty()) {
			// this vertex is not in the MST and the edge connecting it to a vertex in the MST is the minimum.
			// in other words, it is the vertex in the safe edge.
			GraphVertex v = minPriorityQueue.extractMin();

			if (v.getPredecessor()!= null) {
				// safe edge added
				result.add(v.getPredecessor().getEdgeTo(v));
			}

			v.getAdjacents().forEach(adj -> {
				double edgeWeight = v.getWeightOfEdgeTo(adj);
				// make sure that the value of each vertex represents the minimum weight among the edges
				// connecting the vertex to the vertices already in the MST
				if (minPriorityQueue.contains(adj) && adj.getMinWeightToMST() > edgeWeight) {
					adj.setPredecessor(v);
					minPriorityQueue.decreaseKey(adj, edgeWeight, GraphVertex::setMinWeightToMST);
				}
			});
		}

		return result;
	}

}