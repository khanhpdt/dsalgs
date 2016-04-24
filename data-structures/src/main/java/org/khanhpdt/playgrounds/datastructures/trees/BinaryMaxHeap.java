package org.khanhpdt.playgrounds.datastructures.trees;

/**
 * @param <T> node type
 * @author khanhpdt
 */
public class BinaryMaxHeap<T extends Comparable<T>> {

	private T[] nodes;

	private int heapSize;

	public BinaryMaxHeap(T[] nodes) {
		this.nodes = nodes;
		this.heapSize = nodes.length;

		build();
	}

	private void build() {
		// heap leaves are from (floor(nodes.length/2)) to nodes.length
		int leafStartingIndex = (int) Math.floor(heapSize / 2) + 1;

		// bottom-up heapify: starting from the bottom and from the first node which is not a leaf, makes sure that the
		// children of each node can only be smaller than or equal to the node.
		for (int i = leafStartingIndex - 1; i >= 0; i--) {
			maxHeapify(i);
		}
	}

	public void maxHeapify(int indexCurrent) {
		int indexLeftChild = 2 * indexCurrent + 1;
		int indexRightChild = 2 * indexCurrent + 2;

		int indexMax = getIndexMax(indexCurrent, indexLeftChild, indexRightChild);

		if (indexMax == indexCurrent) {
			// the node is the maximum, no need to heapify
			return;
		}

		// swap the node and its max child. so the node floats down.
		swap(indexCurrent, indexMax);

		// because the node floats down, it might break the heap property in its subtree. so we must continue to
		// heapify the subtree.
		maxHeapify(indexMax);
	}

	private void swap(int i1, int i2) {
		T temp = nodes[i1];
		nodes[i1] = nodes[i2];
		nodes[i2] = temp;
	}

	private int getIndexMax(int... indexes) {
		int indexMax = indexes[0];
		T max = nodes[indexMax];

		for (int j = 1; j < indexes.length; j++) {
			int index = indexes[j];
			if (index < heapSize && nodes[index].compareTo(max) > 0) {
				indexMax = index;
				max = nodes[indexMax];
			}
		}

		return indexMax;
	}

	public T[] getNodes() {
		return nodes;
	}

	public void maxHeapifyRoot() {
		maxHeapify(0);
	}

	public void reduceHeapSizeBy(int reductionSize) {
		this.heapSize -= reductionSize;
	}
}
