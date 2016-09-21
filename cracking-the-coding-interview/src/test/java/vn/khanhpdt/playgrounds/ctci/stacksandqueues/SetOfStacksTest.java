package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import org.junit.Test;
import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author khanhpdt
 */
public class SetOfStacksTest {

	@Test
	public void testPushAndPop() {
		int stackCapacity = 5;
		int nNodes = 37;

		// given
		SetOfStacks stackSet = new SetOfStacks(stackCapacity);
		for (int i = 0; i < nNodes; i++) {
			stackSet.push(new SinglyLinkedNode(i));
		}

		// then
		for (int i = 36; i >= 0; i--) {
			assertThat("item " + i, stackSet.pop().getValue(), is(i));
		}
	}

	@Test
	public void testPopAt() {
		int stackCapacity = 5;
		int nNodes = 37;

		// given
		SetOfStacks stackSet = new SetOfStacks(stackCapacity);
		for (int i = 0; i < nNodes; i++) {
			stackSet.push(new SinglyLinkedNode(i));
		}

		// then
		for (int i = 29; i >= 25; i--) {
			assertThat("item " + i, stackSet.popAt(5).getValue(), is(i));
		}
		for (int i = 34; i >= 30; i--) {
			assertThat("item " + i, stackSet.popAt(5).getValue(), is(i));
		}
		for (int i = 36; i >= 35; i--) {
			assertThat("item " + i, stackSet.popAt(5).getValue(), is(i));
		}
	}

}
