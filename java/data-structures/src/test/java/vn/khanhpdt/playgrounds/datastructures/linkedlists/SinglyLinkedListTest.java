package vn.khanhpdt.playgrounds.datastructures.linkedlists;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.TestUtils;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

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
        SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();

        SinglyLinkedNode<UUID, Integer> newNode = TestUtils.randomSinglyNode();
        linkedList.insertFirst(newNode);

        assertThat(linkedList.getHead(), is(newNode));
    }

    @Test
    public void testHeadPointsToTheFirstInsertedElement() throws Exception {
        // list already has an item
        SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = new SinglyLinkedList<>();
        linkedList.insertFirst(TestUtils.randomSinglyNode());

        SinglyLinkedNode<UUID, Integer> newNode = TestUtils.randomSinglyNode();
        linkedList.insertFirst(newNode);

        assertThat(linkedList.getHead(), is(newNode));
    }

    @Test
    public void testCreateList() throws Exception {
        List<SinglyLinkedNode<UUID, Integer>> nodes = TestUtils.randomSinglyNodes(3);

        SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = SinglyLinkedList.from(nodes);

		List<SinglyLinkedNode<UUID, Integer>> linkedNodes = LinkedLists.traverse(linkedList);
	    assertThat(linkedNodes, contains(nodes.toArray()));
    }

    @Test
    public void testSearchNode() throws Exception {
        List<SinglyLinkedNode<UUID, Integer>> nodes = TestUtils.randomSinglyNodes(10);
        SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = SinglyLinkedList.from(nodes);

        SinglyLinkedNode<UUID, Integer> nodeToFind = nodes.get(4);
        SinglyLinkedNode<UUID, Integer> foundNode = linkedList.search(nodeToFind);
        assertThat("node found", foundNode, is(nodeToFind));
    }

    @Test
    public void testRemoveNode() throws Exception {
        List<SinglyLinkedNode<UUID, Integer>> nodes = TestUtils.randomSinglyNodes(10);
        SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = SinglyLinkedList.from(nodes);

        SinglyLinkedNode<UUID, Integer> nodeToRemove = nodes.get(7);
		linkedList.removeAll(nodeToRemove);
		assertThat("node removed", linkedList.search(nodeToRemove), nullValue());
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
		List<SinglyLinkedNode<UUID, Integer>> nodes = TestUtils.randomSinglyNodes(10);
		UUID removeKey = UUID.randomUUID();
		for (int index : indexes) {
			nodes.add(index, SinglyLinkedNode.fromKey(removeKey));
		}
		SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = SinglyLinkedList.from(nodes);

		linkedList.removeAll(SinglyLinkedNode.fromKey(removeKey));

		CollectionUtils.filter(nodes, n -> !n.getKey().equals(removeKey));
		assertThat("duplicated key nodes are removed",
				LinkedLists.traverse(linkedList), contains(nodes.toArray()));
	}

    @Test
    public void testRemoveLastNodes() throws Exception {
        List<SinglyLinkedNode<UUID, Integer>> nodes = TestUtils.randomSinglyNodes(10);

        UUID removeKey = UUID.randomUUID();
        nodes.add(SinglyLinkedNode.fromKey(removeKey));
        nodes.add(SinglyLinkedNode.fromKey(removeKey));

        SinglyLinkedList<SinglyLinkedNode<UUID, Integer>> linkedList = SinglyLinkedList.from(nodes);

		linkedList.removeAll(SinglyLinkedNode.fromKey(removeKey));

        // remove the added nodes
		CollectionUtils.filter(nodes, n -> !n.getKey().equals(removeKey));
		assertThat("duplicated key nodes are removed", LinkedLists.traverse(linkedList), contains(nodes.toArray()));
    }

}
