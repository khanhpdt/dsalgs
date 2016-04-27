package org.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @author khanhpdt
 */
public interface DoublyLinkedNodeIntf<N extends DoublyLinkedNodeIntf<N>> extends LinkedNodeIntf<N> {

	N getPrevious();

	void setPrevious(N previous);

}
