package org.khanhpdt.playgrounds.datastructures;

import org.khanhpdt.playgrounds.datastructures.nodes.DoublyLinkedNode;
import org.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author khanhpdt
 */
public class TestUtils {

	public static List<SinglyLinkedNode> randomSinglyNodes(int size) {
		List<SinglyLinkedNode> nodes = new ArrayList<>();
		IntStream.rangeClosed(0, size).forEach(i -> nodes.add(SinglyLinkedNode.random()));
		return nodes;
	}

	public static List<DoublyLinkedNode> randomDoublyNodes(int size) {
		List<DoublyLinkedNode> nodes = new ArrayList<>();
		IntStream.rangeClosed(0, size).forEach(i -> nodes.add(DoublyLinkedNode.random()));
		return nodes;
	}
}
