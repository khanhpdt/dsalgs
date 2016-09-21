package vn.khanhpdt.playgrounds.datastructures;

import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * @author khanhpdt
 */
public class TestUtils {

	public static List<SinglyLinkedNode<UUID, Integer>> randomSinglyNodes(int size) {
		List<SinglyLinkedNode<UUID, Integer>> nodes = new ArrayList<>();
		IntStream.rangeClosed(0, size).forEach(i -> nodes.add(SinglyLinkedNode.fromKey(UUID.randomUUID())));
		return nodes;
	}

	public static SinglyLinkedNode<UUID, Integer> randomSinglyNode() {
		return SinglyLinkedNode.fromKey(UUID.randomUUID());
	}

	public static List<DoublyLinkedNode<UUID, Integer>> randomDoublyNodes(int size) {
		List<DoublyLinkedNode<UUID, Integer>> nodes = new ArrayList<>();
		IntStream.rangeClosed(0, size).forEach(i -> nodes.add(randomDoublyLinkedNode()));
		return nodes;
	}

	public static BinaryTreeNode<UUID, Integer> randomBinaryTreeNode() {
		return BinaryTreeNode.from(UUID.randomUUID(), new Random().nextInt());
	}

	public static DoublyLinkedNode<UUID, Integer> randomDoublyLinkedNode() {
		return DoublyLinkedNode.from(UUID.randomUUID(), new Random().nextInt());
	}
}
