package org.khanhpdt.playgrounds.datastructures.graphs;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.nodes.GraphNode;

import java.awt.Color;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class GraphTest {

	@Test
	public void testAddVertex() throws Exception {
		Graph graph = new Graph();

		UUID vertexKey = UUID.randomUUID();
		graph.addVertex(vertexKey);

		GraphNode vertex = graph.getVertex(vertexKey);
		assertThat(vertex.getColor(), is(Color.WHITE));
	}

	@Test
	public void testAddEdge() throws Exception {
		Graph graph = new Graph();
		UUID vertex1Key = UUID.randomUUID();
		GraphNode vertex1 = graph.addVertex(vertex1Key);
		UUID vertex2Key = UUID.randomUUID();
		GraphNode vertex2 = graph.addVertex(vertex2Key);

		graph.addEdge(vertex1, vertex2);

		assertThat(vertex1.getAdjacents(), Matchers.hasItem(vertex2));
		assertThat(vertex2.getAdjacents(), Matchers.hasItem(vertex1));
	}

	@Test
	public void testAddEdgeByIndexes() throws Exception {
		Graph graph = new Graph();
		UUID vertex1Key = UUID.randomUUID();
		GraphNode vertex1 = graph.addVertex(vertex1Key);
		UUID vertex2Key = UUID.randomUUID();
		GraphNode vertex2 = graph.addVertex(vertex2Key);

		graph.addEdge(0, 1);

		assertThat(vertex1.getAdjacents(), Matchers.hasItem(vertex2));
		assertThat(vertex2.getAdjacents(), Matchers.hasItem(vertex1));
	}

	@Test
	public void testColorsAfterBreadthFirstSearch() throws Exception {
		testColorsAfterTraversing(graph -> graph.breadthFirstSearch(0));
	}

	@Test
	public void testColorsAfterDepthFirstSearch() throws Exception {
		testColorsAfterTraversing(graph -> graph.depthFirstSearch(0));
	}

	@Test
	public void testColorsAfterRecursiveDepthFirstSearch() throws Exception {
		testColorsAfterTraversing(graph -> graph.recursiveDepthFirstSearch(0));
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

		graph.breadthFirstSearch(0);

		IntStream.range(0, graph.numberOfVertices()).forEach(
				i -> assertThat("distance to source", graph.getVertex(i).getDistance(), is(distances[i])));
	}

	private Graph createDefaultGraph() {
		Graph graph = new Graph();
		IntStream.range(0, 8).forEach(i -> graph.addVertex(UUID.randomUUID(), i));
		graph.addEdges(new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 4}, {2, 6}, {4, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}});
		return graph;
	}

	@Test
	public void testTopologicalSort() throws Exception {
		Graph dag = createDefaultDAG();

		Graph sortedGraph = Graphs.topologicalSort(dag);

		assertThat("topological sorted", Graphs.checkTopologicalSort(sortedGraph), is(true));
	}

	private Graph createDefaultDAG() {
		Graph graph = new Graph();
		IntStream.range(0, 8).forEach(i -> graph.addVertex(UUID.randomUUID()));
		graph.addDirectedEdges(new int[][]{ {0, 1}, {0, 2}, {0, 3}, {3, 2}, {2, 6}, {6, 4}, {6, 5}, {5, 7}, {7, 4} });
		return graph;
	}

	@Test
	public void testCheckDirectedCycleOnGraphWithoutCycle() throws Exception {
		Graph dag = createDefaultDAG();
		assertThat("has no cycle", Graphs.checkDirectedCycle(dag), is(false));
	}

	@Test
	public void testCheckDirectedCycleOnGraphWithCycle() throws Exception {
		Graph graph = new Graph();
		IntStream.range(0, 4).forEach(i -> graph.addVertex(UUID.randomUUID()));

		// add edges such that there's a cycle
		graph.addDirectedEdges(new int[][]{ {0, 1}, {0, 2}, {1, 2}, {2, 0} });

		assertThat("has cycle", Graphs.checkDirectedCycle(graph), is(true));
	}


}