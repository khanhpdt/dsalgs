package org.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @param <N> type of the nodes linked with this node
 * @author khanhpdt
 */
public interface DoublyLinkedNodeIntf<N extends DoublyLinkedNodeIntf<N>> extends LinkedNodeIntf<N> {

	N getPrevious();

	void setPrevious(N previous);

}
