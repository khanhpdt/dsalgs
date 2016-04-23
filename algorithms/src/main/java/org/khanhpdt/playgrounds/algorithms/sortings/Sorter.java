package org.khanhpdt.playgrounds.algorithms.sortings;

/**
 * @author khanhpdt
*/
public interface Sorter<T extends Comparable<T>> {
	void sort(T[] elements);
}
