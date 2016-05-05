package org.khanhpdt.playgrounds.algorithms.graphs;

import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.graphs.Graph;

import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class DirectedCycleTest {

	@Test
	public void testCheckDirectedCycleOnGraphWithoutCycle() throws Exception {
		Graph dag = createDefaultDAG();
		assertThat("has no cycle", DirectedCycle.checkExists(dag), is(false));
	}

	@Test
	public void testCheckDirectedCycleOnGraphWithCycle() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 4).forEach(i -> graph.addVertex(UUID.randomUUID()));

		// add edges such that there's a cycle
		graph.addDirectedEdges(new int[][]{ {0, 1}, {0, 2}, {1, 2}, {2, 0} });

		assertThat("has cycle", DirectedCycle.checkExists(graph), is(true));
	}

	private Graph createDefaultDAG() {
		Graph graph = new Graph();
		IntStream.range(0, 8).forEach(i -> graph.addVertex(UUID.randomUUID()));
		graph.addDirectedEdges(new int[][]{ {0, 1}, {0, 2}, {0, 3}, {3, 2}, {2, 6}, {6, 4}, {6, 5}, {5, 7}, {7, 4} });
		return graph;
	}

}
