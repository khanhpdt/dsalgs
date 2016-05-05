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
public class TopologicalSortTest {

	@Test
	public void testTopologicalSort() throws Exception {
		Graph dag = createDefaultDAG();

		Graph sortedGraph = TopologicalSort.from(dag);

		assertThat("topological sorted", TopologicalSort.checkExists(sortedGraph), is(true));
	}

	private Graph createDefaultDAG() {
		Graph graph = new Graph();
		IntStream.range(0, 8).forEach(i -> graph.addVertex(UUID.randomUUID()));
		graph.addDirectedEdges(new int[][]{ {0, 1}, {0, 2}, {0, 3}, {3, 2}, {2, 6}, {6, 4}, {6, 5}, {5, 7}, {7, 4} });
		return graph;
	}

}
