package org.khanhpdt.playgrounds.datastructures.sets;

import org.khanhpdt.playgrounds.datastructures.nodes.DisjointSetNode;

/**
 * @author khanhpdt
 */
public class DisjointSets {

	private DisjointSets() {
		// no instance
	}

	public static DisjointSet makeSet(DisjointSetNode... nodes) {
		DisjointSet disjointSet = new DisjointSet();
		for (DisjointSetNode node : nodes) {
			disjointSet.insert(node);
		}
		return disjointSet;
	}

	public static DisjointSet findSet(DisjointSetNode node) {
		return node.getSet();
	}

	public static DisjointSet union(DisjointSetNode node1, DisjointSetNode node2) {
		return union(node1.getSet(), node2.getSet());
	}

	private static DisjointSet union(DisjointSet set1, DisjointSet set2) {
		// use weighted-union heuristic: append the elements in the shorter set to the other set. This reduces the time
		// to update the set of the appended nodes.
		DisjointSet unionSet;
		DisjointSet destroyedSet;
		if (set1.size() >= set2.size()) {
			unionSet = set1;
			destroyedSet = set2;
		} else {
			unionSet = set2;
			destroyedSet = set1;
		}

		// add nodes from destroyed set to the union set
		DisjointSetNode node = destroyedSet.getHead();
		while (node != null) {
			unionSet.insert(node);
			node.setSet(unionSet);

			node = node.getNext();
		}

		// don't need this set anymore
		destroyedSet.clear();

		return unionSet;
	}
}
