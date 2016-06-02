package org.khanhpdt.playgrounds.datastructures.queues;

import java.util.Comparator;
import java.util.List;

/**
 * @param <T> node type
 * @author khanhpdt
 */
public class MaxPriorityQueue<T> extends PriorityQueue<T> {

	public MaxPriorityQueue(List<T> nodes, Comparator<T> nodeComparator) {
		super(nodes, nodeComparator);
	}

	@Override
	protected int getIndexGivenHeapProperty(int... nodeIndices) {
		return getIndexOfMax(nodeIndices);
	}

	private int getIndexOfMax(int[] nodeIndices) {
		int indexMax = nodeIndices[0];
		T max = getNode(indexMax);

		for (int j = 1; j < nodeIndices.length; j++) {
			int index = nodeIndices[j];
			if (index < getHeapSize() && getNodeComparator().compare(getNode(index), max) > 0) {
				indexMax = index;
				max = getNode(indexMax);
			}
		}

		return indexMax;
	}
}
