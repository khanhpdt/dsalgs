package org.khanhpdt.playgrounds.datastructures.linkedlists;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.khanhpdt.playgrounds.datastructures.TestUtils;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * @author khanhpdt
 */
public class SinglyLinkedListTest {

    @Test
    public void testHeadOfNewListPointsToTheFirstInsertedElement() throws Exception {
        SinglyLinkedList linkedList = new SinglyLinkedList();

        SinglyLinkedNode newNode = SinglyLinkedNode.random();
        linkedList.insert(newNode);

        assertThat(linkedList.getHead(), is(newNode));
    }

    @Test
    public void testHeadPointsToTheFirstInsertedElement() throws Exception {
        // list already has an item
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.insert(SinglyLinkedNode.random());

        SinglyLinkedNode newNode = SinglyLinkedNode.random();
        linkedList.insert(newNode);

        assertThat(linkedList.getHead(), is(newNode));
    }

    @Test
    public void testCreateList() throws Exception {
        List<SinglyLinkedNode> nodes = TestUtils.randomSinglyNodes(3);

        SinglyLinkedList linkedList = SinglyLinkedList.from(nodes);

		List<SinglyLinkedNode> linkedNodes = LinkedLists.traverse(linkedList);
		assertThat(linkedNodes, contains(nodes.toArray(new SinglyLinkedNode[nodes.size()])));
    }

    @Test
    public void testSearchNode() throws Exception {
        List<SinglyLinkedNode> nodes = TestUtils.randomSinglyNodes(10);
        SinglyLinkedList linkedList = SinglyLinkedList.from(nodes);

        SinglyLinkedNode nodeToFind = nodes.get(4);
        SinglyLinkedNode foundNode = linkedList.search(nodeToFind.getKey());
        assertThat("node found", foundNode, is(nodeToFind));
    }

    @Test
    public void testRemoveNode() throws Exception {
        List<SinglyLinkedNode> nodes = TestUtils.randomSinglyNodes(10);
        SinglyLinkedList linkedList = SinglyLinkedList.from(nodes);

        SinglyLinkedNode nodeToRemove = nodes.get(7);
		linkedList.remove(nodeToRemove.getKey());
		assertThat("node removed", linkedList.search(nodeToRemove.getKey()), nullValue());
    }

    @Test
    public void testRemoveAllNodesWithSameKey() throws Exception {
		testRemoveNodesAt(4, 7, 9);
	}

	@Test
	public void testRemoveFirstNodes() throws Exception {
		testRemoveNodesAt(0, 0);
	}

	@Test
	public void testRemoveConsecutiveNodes() throws Exception {
		testRemoveNodesAt(4, 5);
	}

	private void testRemoveNodesAt(int... indexes) throws Exception {
		List<SinglyLinkedNode> nodes = TestUtils.randomSinglyNodes(10);
		UUID removeKey = UUID.randomUUID();
		for (int index : indexes) {
			nodes.add(index, new SinglyLinkedNode(removeKey));
		}
		SinglyLinkedList linkedList = SinglyLinkedList.from(nodes);

		linkedList.remove(removeKey);

		CollectionUtils.filter(nodes, n -> !n.getKey().equals(removeKey));
		assertThat("duplicated key nodes are removed",
				LinkedLists.traverse(linkedList), contains(nodes.toArray()));
	}

    @Test
    public void testRemoveLastNodes() throws Exception {
        List<SinglyLinkedNode> nodes = TestUtils.randomSinglyNodes(10);

        UUID removeKey = UUID.randomUUID();
        nodes.add(new SinglyLinkedNode(removeKey));
        nodes.add(new SinglyLinkedNode(removeKey));

        SinglyLinkedList linkedList = SinglyLinkedList.from(nodes);

		linkedList.remove(removeKey);

        // remove the added nodes
		CollectionUtils.filter(nodes, n -> !n.getKey().equals(removeKey));
		assertThat("duplicated key nodes are removed", LinkedLists.traverse(linkedList), contains(nodes.toArray()));
    }

}
