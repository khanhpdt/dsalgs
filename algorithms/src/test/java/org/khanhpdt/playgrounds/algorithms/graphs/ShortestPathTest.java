package org.khanhpdt.playgrounds.algorithms.graphs;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.graphs.Graph;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphVertex;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * @author khanhpdt
 */
public class ShortestPathTest {

	private static final double DOUBLE_ERROR = 0.0001;

	@Test
	public void testSingleSourceShortestPath_distance() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 5).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{
				{0, 1, 6}, {0, 4, 7}, {1, 2, 5}, {1, 3, -4}, {1, 4, 8},
				{2, 1, -2}, {3, 2, 7}, {3, 0, 2}, {4, 2, -3}, {4, 3, 9} });

		BellmanFordShortestPath shortestPath = new BellmanFordShortestPath(graph, graph.getVertex(0));

		assertThat(shortestPath.getDistanceTo(graph.getVertex(1)), closeTo(2, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(2)), closeTo(4, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(3)), closeTo(-2, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(4)), closeTo(7, DOUBLE_ERROR));
	}

	@Test
	public void testSingleSourceShortestPath_path() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 5).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{
				{0, 1, 6}, {0, 4, 7}, {1, 2, 5}, {1, 3, -4}, {1, 4, 8},
				{2, 1, -2}, {3, 2, 7}, {3, 0, 2}, {4, 2, -3}, {4, 3, 9} });

		BellmanFordShortestPath shortestPath = new BellmanFordShortestPath(graph, graph.getVertex(0));

		assertThat(shortestPath.getPathTo(graph.getVertex(1)), Matchers.contains(getVertexMatchers(graph, 0, 4, 2, 1)));
		assertThat(shortestPath.getPathTo(graph.getVertex(2)), Matchers.contains(getVertexMatchers(graph, 0, 4, 2)));
		assertThat(shortestPath.getPathTo(graph.getVertex(3)), Matchers.contains(getVertexMatchers(graph, 0, 4, 2, 1, 3)));
		assertThat(shortestPath.getPathTo(graph.getVertex(4)), Matchers.contains(getVertexMatchers(graph, 0, 4)));
	}
	private static List<Matcher<? super GraphVertex>> getVertexMatchers(Graph graph, int... vertexIndices) {
		return Arrays.stream(vertexIndices).boxed()
				.map(graph::getVertex)
				.map(IsEqual::new)
				.collect(Collectors.toList());
	}

	@Test
	public void testDAGShortestPath_distance() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 6).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{
				{0, 1, 2}, {0, 2, 6}, {1, 2, 7}, {1, 3, 4}, {1, 4, 2},
				{2, 3, -1}, {2, 4, 1}, {3, 4, -2}, {5, 0, 5}, {5, 1, 3} });

		DAGShortestPath shortestPath = new DAGShortestPath(graph, graph.getVertex(0));

		assertThat(shortestPath.getDistanceTo(graph.getVertex(1)), closeTo(2, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(2)), closeTo(6, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(3)), closeTo(5, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(4)), closeTo(3, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(5)), is(Double.POSITIVE_INFINITY));
	}

	@Test
	public void testDAGShortestPath_path() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 6).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{
				{0, 1, 2}, {0, 2, 6}, {1, 2, 7}, {1, 3, 4}, {1, 4, 2},
				{2, 3, -1}, {2, 4, 1}, {3, 4, -2}, {5, 0, 5}, {5, 1, 3} });

		DAGShortestPath shortestPath = new DAGShortestPath(graph, graph.getVertex(0));

		assertThat(shortestPath.getPathTo(graph.getVertex(1)), Matchers.contains(getVertexMatchers(graph, 0, 1)));
		assertThat(shortestPath.getPathTo(graph.getVertex(2)), Matchers.contains(getVertexMatchers(graph, 0, 2)));
		assertThat(shortestPath.getPathTo(graph.getVertex(3)), Matchers.contains(getVertexMatchers(graph, 0, 2, 3)));
		assertThat(shortestPath.getPathTo(graph.getVertex(4)), Matchers.contains(getVertexMatchers(graph, 0, 2, 3, 4)));
		assertThat(shortestPath.getPathTo(graph.getVertex(5)), Matchers.empty());
	}

	@Test
	public void testDijkstraShortestPath_distance() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 5).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{
				{0, 1, 10}, {0, 4, 5}, {1, 2, 1}, {1, 4, 2}, {2, 3, 4},
				{3, 2, 6}, {3, 0, 7}, {4, 1, 3}, {4, 2, 9}, {4, 3, 2} });

		DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph, graph.getVertex(0));

		assertThat(shortestPath.getDistanceTo(graph.getVertex(1)), closeTo(8, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(2)), closeTo(9, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(3)), closeTo(7, DOUBLE_ERROR));
		assertThat(shortestPath.getDistanceTo(graph.getVertex(4)), closeTo(5, DOUBLE_ERROR));
	}

	@Test
	public void testDijkstraShortestPath_path() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 5).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{
				{0, 1, 10}, {0, 4, 5}, {1, 2, 1}, {1, 4, 2}, {2, 3, 4},
				{3, 2, 6}, {3, 0, 7}, {4, 1, 3}, {4, 2, 9}, {4, 3, 2} });

		DijkstraShortestPath shortestPath = new DijkstraShortestPath(graph, graph.getVertex(0));

		assertThat(shortestPath.getPathTo(graph.getVertex(1)), Matchers.contains(getVertexMatchers(graph, 0, 4, 1)));
		assertThat(shortestPath.getPathTo(graph.getVertex(2)), Matchers.contains(getVertexMatchers(graph, 0, 4, 1, 2)));
		assertThat(shortestPath.getPathTo(graph.getVertex(3)), Matchers.contains(getVertexMatchers(graph, 0, 4, 3)));
		assertThat(shortestPath.getPathTo(graph.getVertex(4)), Matchers.contains(getVertexMatchers(graph, 0, 4)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDijkstraShortestPath_negativeWeightEdge() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 5).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addDirectedEdges(new int[][]{
				{0, 1, 10}, {0, 4, 5}, {1, 2, -1}, {1, 4, 2}, {2, 3, 4},
				{3, 2, 6}, {3, 0, 7}, {4, 1, 3}, {4, 2, 9}, {4, 3, 2} });

		new DijkstraShortestPath(graph, graph.getVertex(0));
	}

}
