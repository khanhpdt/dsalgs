package org.khanhpdt.playgrounds.datastructuresalgorithms.commons;

/**
 * @author khanhpdt
 */
public abstract class Commons {

	 public static <T> void swap(T[] elements, int i, int j) {
		T temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

}
