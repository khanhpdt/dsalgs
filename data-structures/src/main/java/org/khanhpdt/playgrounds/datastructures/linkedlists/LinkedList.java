package org.khanhpdt.playgrounds.datastructures.linkedlists;

/**
 * @param <N>  type of node
 * @author khanhpdt
 */
public interface LinkedList<N extends LinkedNode> {

	N getHead();

	void setHead(N head);
}
