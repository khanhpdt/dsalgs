package org.khanhpdt.playgrounds.datastructures.nodes;

/**
 * @author khanhpdt
 */
public interface GenericLinkedNodeIntf<K, V, N extends GenericLinkedNodeIntf<K, V, N>> {

	Node<K, V> getContent();

	K getKey();

	N getNext();

	void setNext(N next);
}
