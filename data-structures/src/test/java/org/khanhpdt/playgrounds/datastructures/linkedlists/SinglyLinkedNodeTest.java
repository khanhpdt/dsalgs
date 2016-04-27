package org.khanhpdt.playgrounds.datastructures.linkedlists;

import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author khanhpdt
 */
public class SinglyLinkedNodeTest {

	@Test
	public void testEqualNodesHaveSameHashcode() throws Exception {
		SinglyLinkedNode node1 = new SinglyLinkedNode(UUID.randomUUID());
		SinglyLinkedNode node2 = new SinglyLinkedNode(node1.getKey());

		assertThat("equal nodes", node1, equalTo(node2));
		assertThat("same hash code", node1.hashCode(), is(node2.hashCode()));
	}
}
