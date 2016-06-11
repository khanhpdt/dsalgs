package vn.khanhpdt.playgrounds.datastructures.stacks;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.TestUtils;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class StackTest {

	@Test
	public void testPush() throws Exception {
		Stack<SinglyLinkedNode> stack = new Stack<>();

		SinglyLinkedNode newNode = SinglyLinkedNode.random();
		stack.push(newNode);

		assertThat(stack.peek(), is(newNode));
	}
	@Test
	public void testPop() throws Exception {
		Stack<SinglyLinkedNode> stack = new Stack<>();

		SinglyLinkedNode newNode = SinglyLinkedNode.random();
		stack.push(newNode);

		assertThat(stack.pop(), is(newNode));
	}

	@Test
	public void testPushAndPopMultipleItems() throws Exception {
		List<SinglyLinkedNode> nodes = TestUtils.randomSinglyNodes(10);

		Stack<SinglyLinkedNode> stack = new Stack<>();
		nodes.forEach(stack::push);

		Collections.reverse(nodes);
		nodes.forEach(n -> assertThat(stack.pop(), is(n)));
	}
}
