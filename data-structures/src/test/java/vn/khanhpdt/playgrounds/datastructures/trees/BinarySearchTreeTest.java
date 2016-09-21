package vn.khanhpdt.playgrounds.datastructures.trees;

import org.junit.Before;
import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.TestUtils;
import vn.khanhpdt.playgrounds.datastructures.nodes.BinaryTreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class BinarySearchTreeTest {

	private static final List<Integer> IN_ORDER_INDEXES = Arrays.asList(4, 1, 7, 2, 5, 0, 3, 6);
	private static final List<Integer> PRE_ORDER_INDEXES = Arrays.asList(0, 1, 4, 2, 7, 5, 3, 6);
	private static final List<Integer> POST_ORDER_INDEXES = Arrays.asList(4, 7, 5, 2, 1, 6, 3, 0);

	private List<BinaryTreeNode<UUID, Integer>> defaultNodes;
	private BinarySearchTree<UUID, Integer> defaultBST;

	@Before
	public void init() {
		defaultNodes = getDefaultNodes();
		defaultBST = getDefaultTree(defaultNodes);
	}

	private List<BinaryTreeNode<UUID, Integer>> getDefaultNodes() {
		return Stream.of(30, 20, 25, 35, 15, 27, 40, 23)
				.map(value -> BinaryTreeNode.from(UUID.randomUUID(), value))
				.collect(Collectors.toList());
	}

	private BinarySearchTree<UUID, Integer> getDefaultTree(List<BinaryTreeNode<UUID, Integer>> defaultNodes) {
		BinarySearchTree<UUID, Integer> bst = new BinarySearchTree<>();
		defaultNodes.forEach(bst::insert);
		return bst;
	}

	@Test
	public void testInsertFirstNode() throws Exception {
		BinarySearchTree<UUID, Integer> bst = new BinarySearchTree<>();

		BinaryTreeNode<UUID, Integer> firstNode = TestUtils.randomBinaryTreeNode();
		bst.insert(firstNode);

		assertThat(bst.getRoot(), is(firstNode));
	}

	@Test
	public void testSimpleInsert() throws Exception {
		assertThat(defaultBST.getRoot(), is(defaultNodes.get(0)));
		assertThat(defaultBST.getRoot().getLeft(), is(defaultNodes.get(1)));
		assertThat(defaultBST.getRoot().getLeft().getLeft(), is(defaultNodes.get(4)));
		assertThat(defaultBST.getRoot().getLeft().getRight(), is(defaultNodes.get(2)));
		assertThat(defaultBST.getRoot().getLeft().getRight().getLeft(), is(defaultNodes.get(7)));
		assertThat(defaultBST.getRoot().getLeft().getRight().getRight(), is(defaultNodes.get(5)));
		assertThat(defaultBST.getRoot().getRight(), is(defaultNodes.get(3)));
		assertThat(defaultBST.getRoot().getRight().getRight(), is(defaultNodes.get(6)));
	}

	@Test
	public void testTraverseInOrderIterative() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> inOrderNodes = defaultBST.traverseInOrderIterative();
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(inOrderNodes.get(i), is(defaultNodes.get(IN_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraverseInOrderRecursive() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> inOrderNodes = defaultBST.traverseInOrderRecursive();
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(inOrderNodes.get(i), is(defaultNodes.get(IN_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraversePreOrderRecursive() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> preOrderNodes = defaultBST.traversePreOrderRecursive();
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(preOrderNodes.get(i), is(defaultNodes.get(PRE_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraversePreOrderIterative() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> preOrderNodes = defaultBST.traversePreOrderIterative();
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(preOrderNodes.get(i), is(defaultNodes.get(PRE_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraversePostOrderRecursive() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> postOrderNodes = defaultBST.traversePostOrderRecursive();
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(postOrderNodes.get(i), is(defaultNodes.get(POST_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testTraversePostOrderIterative() throws Exception {
		List<BinaryTreeNode<UUID, Integer>> postOrderNodes = defaultBST.traversePostOrderIterative();
		IntStream.range(0, defaultNodes.size())
				.forEach(i -> assertThat(postOrderNodes.get(i), is(defaultNodes.get(POST_ORDER_INDEXES.get(i)))));
	}

	@Test
	public void testRemoveRoot() throws Exception {
		defaultBST.remove(defaultBST.getRoot().getKey());

		int[] preOrderIndexesAfterRemove = {3, 1, 4, 2, 7, 5, 6};
		List<BinaryTreeNode<UUID, Integer>> preOrderNodes = defaultBST.traversePreOrderIterative();
		IntStream.range(0, preOrderIndexesAfterRemove.length)
				.forEach(i -> assertThat("node " + i, preOrderNodes.get(i), is(defaultNodes.get(preOrderIndexesAfterRemove[i]))));
	}

	@Test
	public void testRemoveLeaf() throws Exception {
		defaultBST.remove(defaultBST.getRoot().getLeft().getLeft().getKey());

		int[] inOrderIndexesAfterRemove = {1, 7, 2, 5, 0, 3, 6};
		List<BinaryTreeNode<UUID, Integer>> inOrderNodes = defaultBST.traverseInOrderIterative();
		IntStream.range(0, inOrderIndexesAfterRemove.length)
				.forEach(i -> assertThat("node " + i, inOrderNodes.get(i), is(defaultNodes.get(inOrderIndexesAfterRemove[i]))));
	}

	@Test
	public void testRemoveNodeWithOneChild() throws Exception {
		defaultBST.remove(defaultBST.getRoot().getRight().getKey());

		int[] preOrderIndexesAfterRemove = {0, 1, 4, 2, 7, 5, 6};
		List<BinaryTreeNode<UUID, Integer>> preOrderNodes = defaultBST.traversePreOrderIterative();
		IntStream.range(0, preOrderIndexesAfterRemove.length)
				.forEach(i -> assertThat("node " + i, preOrderNodes.get(i), is(defaultNodes.get(preOrderIndexesAfterRemove[i]))));
	}

	@Test
	public void testRemoveNodeWithTwoChildren() throws Exception {
		defaultBST.remove(defaultBST.getRoot().getLeft().getKey());

		int[] postOrderIndexesAfterRemove = {4, 5, 2, 7, 6, 3, 0};
		List<BinaryTreeNode<UUID, Integer>> postOrderNodes = defaultBST.traversePostOrderIterative();
		IntStream.range(0, postOrderIndexesAfterRemove.length)
				.forEach(i -> assertThat("node " + i, postOrderNodes.get(i), is(defaultNodes.get(postOrderIndexesAfterRemove[i]))));
	}

}
