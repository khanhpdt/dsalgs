package org.khanhpdt.playgrounds.algorithms.graphs;

import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.graphs.Graph;

import java.awt.Color;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class GraphSearchTest {

	@Test
	public void testColorsAfterBreadthFirstSearch() throws Exception {
		testColorsAfterTraversing(graph -> new BreadthFirstSearch().search(graph));
	}

	@Test
	public void testColorsAfterIterativeDepthFirstSearch() throws Exception {
		testColorsAfterTraversing(graph -> new DepthFirstSearchIterative().search(graph));
	}

	@Test
	public void testColorsAfterRecursiveDepthFirstSearch() throws Exception {
		testColorsAfterTraversing(graph -> new DepthFirstSearchRecursive().search(graph));
	}

	private void testColorsAfterTraversing(Consumer<Graph> traversingMethod) throws Exception {
		Graph graph = createDefaultGraph();

		traversingMethod.accept(graph);

		IntStream.range(0, graph.numberOfVertices()).forEach(
				i -> assertThat(graph.getVertex(i).getColor(), is(Color.BLACK)));
	}

	@Test
	public void testDistancesAfterBreadthFirstSearch() throws Exception {
		Graph graph = createDefaultGraph();
		int[] distances = new int[]{0, 1, 1, 2, 2, 3, 2, 3};

		new BreadthFirstSearch().search(graph);

		IntStream.range(0, graph.numberOfVertices()).forEach(
				i -> assertThat("distance to source", graph.getVertex(i).getDistance(), is(distances[i])));
	}

	private Graph createDefaultGraph() {
		Graph graph = new Graph();
		IntStream.range(0, 8).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addEdges(new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 4}, {2, 6}, {4, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}});
		return graph;
	}

}
