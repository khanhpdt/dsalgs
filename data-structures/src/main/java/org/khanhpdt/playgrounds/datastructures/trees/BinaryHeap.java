package org.khanhpdt.playgrounds.datastructures.trees;

import java.util.Comparator;
import java.util.List;

import static org.khanhpdt.playgrounds.datastructuresalgorithms.commons.Commons.swap;

/**
 * @author khanhpdt
 */
public abstract class BinaryHeap<T> {

	private List<T> nodes;

	private Comparator<T> nodeComparator;

	private int heapSize;

	protected BinaryHeap(List<T> nodes, Comparator<T> nodeComparator) {
		this.nodes = nodes;
		this.nodeComparator = nodeComparator;
		this.heapSize = nodes.size();

		build();
	}

	private void build() {
		// heap leaves are from (floor(nodes.length/2)) to nodes.length
		int leafStartingIndex = (int) Math.floor(heapSize / 2) + 1;

		// bottom-up heapify: starting from the bottom and from the first node which is not a leaf, makes sure that the
		// children of each node satisfy the heap property, i.e., if the heap is a max-heap, the children can only be
		// smaller than or equal to the node; otherwise, if the heap is a min-heap, the children must be larger than
		// or equal to the node.
		for (int i = leafStartingIndex - 1; i >= 0; i--) {
			heapify(i);
		}
	}

	private void heapify(int indexCurrent) {
		int indexLeftChild = 2 * indexCurrent + 1;
		int indexRightChild = 2 * indexCurrent + 2;

		int heapifiedNodeIndex = getIndexGivenHeapProperty(indexCurrent, indexLeftChild, indexRightChild);

		if (heapifiedNodeIndex == indexCurrent) {
			// no need to heapify to heapify the current node
			return;
		}

		// swap the node and its heapified child. so the node floats down.
		swap(getNodes(), indexCurrent, heapifiedNodeIndex);

		// because the node floats down, it might break the heap property in its subtree. so we must continue to
		// heapify the subtree.
		heapify(heapifiedNodeIndex);
	}

	/**
	 * @return the index of the minimum node if the heap is a min-heap; otherwise, if the node is a max-heap,
	 * return the index of the maximum node.
	 */
	protected abstract int getIndexGivenHeapProperty(int... nodeIndices);

	public List<T> getNodes() {
		return nodes;
	}

	protected T getNode(int index) {
		return getNodes().get(index);
	}

	protected int getHeapSize() {
		return heapSize;
	}

	protected Comparator<T> getNodeComparator() {
		return nodeComparator;
	}

	public void reduceHeapSizeBy(int reductionSize) {
		heapSize -= reductionSize;
	}

	public void heapifyRoot() {
		heapify(0);
	}

}
