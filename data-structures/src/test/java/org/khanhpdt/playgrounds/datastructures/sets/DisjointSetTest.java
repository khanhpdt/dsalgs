package org.khanhpdt.playgrounds.datastructures.sets;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.nodes.DisjointSetNode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class DisjointSetTest {

	@Test
	public void testMakeSet() throws Exception {
		DisjointSetNode node = new DisjointSetNode();

		DisjointSet set = DisjointSets.makeSet(node);

		assertThat(set.getHead(), is(node));
		assertThat(set.getTail(), is(node));
		assertThat(node.getSet(), is(set));
	}

	@Test
	public void testFindSet() throws Exception {
		DisjointSetNode node = new DisjointSetNode();
		DisjointSet set = DisjointSets.makeSet(node);

		DisjointSet enclosingSet = DisjointSets.findSet(node);

		assertThat(enclosingSet, is(set));
	}

	@Test
	public void testUnion() throws Exception {
		DisjointSetNode node1 = new DisjointSetNode();
		DisjointSets.makeSet(node1);
		DisjointSetNode node2 = new DisjointSetNode();
		DisjointSets.makeSet(node2);

		DisjointSet union = DisjointSets.union(node1, node2);

		assertThat(node1.getSet(), is(union));
		assertThat(node2.getSet(), is(union));
	}

	@Test
	public void testUnion_resultSetIsTheLongerLongerSet() throws Exception {
		DisjointSetNode node1 = new DisjointSetNode();
		DisjointSetNode node2 = new DisjointSetNode();
		DisjointSetNode node3 = new DisjointSetNode();

		DisjointSet set1 = DisjointSets.makeSet(node1, node2);
		DisjointSets.makeSet(node3);

		DisjointSet union = DisjointSets.union(node1, node3);

		assertThat(union, is(set1));
	}

	@Test
	public void testUnion_shorterSetDestroyed() throws Exception {
		DisjointSetNode node1 = new DisjointSetNode();
		DisjointSetNode node2 = new DisjointSetNode();
		DisjointSetNode node3 = new DisjointSetNode();

		DisjointSets.makeSet(node1, node2);
		DisjointSet set2 = DisjointSets.makeSet(node3);

		DisjointSets.union(node1, node3);

		assertThat(set2.size(), is(0));
		assertThat(set2.getHead(), Matchers.nullValue());
	}
}
