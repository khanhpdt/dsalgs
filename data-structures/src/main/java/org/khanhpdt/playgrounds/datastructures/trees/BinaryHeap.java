package org.khanhpdt.playgrounds.datastructures.trees;

import org.khanhpdt.playgrounds.datastructuresalgorithms.commons.Commons;

import java.util.Comparator;
import java.util.List;

/**
 * @author khanhpdt
 */
public abstract class BinaryHeap<T> {

	private List<T> nodes;

	private Comparator<T> nodeComparator;

	private int heapSize;

	BinaryHeap(List<T> nodes, Comparator<T> nodeComparator) {
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
		int indexLeftChild = getLeftChildIndexOf(indexCurrent);
		int indexRightChild = getRightChildIndexOf(indexCurrent);

		int heapifiedNodeIndex = getIndexGivenHeapProperty(indexCurrent, indexLeftChild, indexRightChild);

		if (heapifiedNodeIndex == indexCurrent) {
			// no need to heapify the current node
			return;
		}

		// swap the node and its heapified child. so the node floats down.
		swapNodes(indexCurrent, heapifiedNodeIndex);

		// because the node floats down, it might break the heap property in its subtree. so we must continue to
		// heapify the subtree.
		heapify(heapifiedNodeIndex);
	}

	private void swapNodes(int i1, int i2) {
		Commons.swap(nodes, i1, i2);
	}

	private int getRightChildIndexOf(int indexCurrent) {
		return 2 * indexCurrent + 2;
	}

	private int getLeftChildIndexOf(int indexCurrent) {
		return 2 * indexCurrent + 1;
	}

	/**
	 * @return the index of the minimum node if the heap is a min-heap; otherwise, if the node is a max-heap,
	 * return the index of the maximum node.
	 */
	protected abstract int getIndexGivenHeapProperty(int... nodeIndices);

	public List<T> getNodes() {
		return nodes;
	}

	T getNode(int index) {
		return nodes.get(index);
	}

	public int getHeapSize() {
		return heapSize;
	}

	Comparator<T> getNodeComparator() {
		return nodeComparator;
	}

	public void reduceHeapSizeBy(int reductionSize) {
		heapSize -= reductionSize;
	}

	public void heapifyRoot() {
		heapify(0);
	}

}
