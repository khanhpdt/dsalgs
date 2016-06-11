package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import vn.khanhpdt.playgrounds.datastructures.nodes.LinkedNodeIntf;

/**
 * @param <N>  type of node
 * @author khanhpdt
 */
public interface LinkedList<N extends LinkedNodeIntf<N>> {

	N getHead();

	void setHead(N head);
}
