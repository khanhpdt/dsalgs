package org.khanhpdt.playgrounds.datastructures.trees;

import java.util.Comparator;
import java.util.List;

/**
 * @param <T> node type
 * @author khanhpdt
 */
public class BinaryMinHeap<T> extends BinaryHeap<T> {

	public BinaryMinHeap(List<T> nodes, Comparator<T> nodeComparator) {
		super(nodes, nodeComparator);
	}

	@Override
	protected int getIndexGivenHeapProperty(int... nodeIndices) {
		return getIndexOfMin(nodeIndices);
	}

	private int getIndexOfMin(int[] nodeIndices) {
		int indexMin = nodeIndices[0];
		T min = getNode(indexMin);

		for (int j = 1; j < nodeIndices.length; j++) {
			int index = nodeIndices[j];
			if (index < getHeapSize() && getNodeComparator().compare(getNode(index), min) < 0) {
				indexMin = index;
				min = getNode(indexMin);
			}
		}

		return indexMin;
	}

	public void minHeapifyUp(T node) {
		int nodeIndex = getIndexOf(node);
		int parentIndex = getParentIndexOf(nodeIndex);
		// heapify when the current node and its parent are out of order
		while (parentIndex >= 0 && getNodeComparator().compare(getNode(nodeIndex), getNode(parentIndex)) < 0) {
			swapNodes(nodeIndex, parentIndex);

			// keep going up to check for the heap property
			nodeIndex = parentIndex;
			parentIndex = getParentIndexOf(nodeIndex);
		}
	}

	public T extractMin() {
		return extractRoot();
	}
}
