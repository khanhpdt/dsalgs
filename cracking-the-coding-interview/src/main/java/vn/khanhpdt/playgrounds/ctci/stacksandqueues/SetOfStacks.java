package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

import vn.khanhpdt.playgrounds.datastructures.nodes.SinglyLinkedNode;
import vn.khanhpdt.playgrounds.datastructures.stacks.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Problem 3.3
 *
 * @author khanhpdt
 */
public class SetOfStacks {

	private final int stackCapacity;

	// it is more memory-efficient if we use a stack or linked list to implement the set of stacks, instead of using array here
	private List<Stack<SinglyLinkedNode<UUID, Integer>>> stacks;

	public SetOfStacks(int stackCapacity) {
		this.stackCapacity = stackCapacity;

		this.stacks = new ArrayList<>();
		ensureStackAvailable();
	}

	public void push(SinglyLinkedNode<UUID, Integer> node) {
		Stack<SinglyLinkedNode<UUID, Integer>> currentStack = getCurrentStack();
		if (currentStack.size() < stackCapacity) {
			currentStack.push(node);
		} else {
			addStack();
			getCurrentStack().push(node);
		}
	}

	private Stack<SinglyLinkedNode<UUID, Integer>> addStack() {
		Stack<SinglyLinkedNode<UUID, Integer>> newStack = new Stack<>();
		stacks.add(newStack);
		return newStack;
	}

	private Stack<SinglyLinkedNode<UUID, Integer>> getCurrentStack() {
		return stacks.get(stacks.size() - 1);
	}

	public SinglyLinkedNode pop() {
		int stackSizeBeforePop = getCurrentStack().size();
		SinglyLinkedNode result = getCurrentStack().pop();

		// current stack is now empty
		if (stackSizeBeforePop == 1) {
			removeCurrentStack();
		}
		return result;
	}

	private void removeCurrentStack() {
		removeStack(stacks.size() - 1);
	}

	private void ensureStackAvailable() {
		if (stacks.size() == 0) {
			addStack();
		}
	}

	public SinglyLinkedNode popAt(int stackIndex) {
		if (stacks.size() <= stackIndex) {
			return null;
		}

		Stack<SinglyLinkedNode<UUID, Integer>> stack = stacks.get(stackIndex);
		int stackSizeBeforePop = stack.size();
		SinglyLinkedNode result = stack.pop();
		// remove stack if it becomes empty after pop
		if (stackSizeBeforePop == 1) {
			removeStack(stackIndex);
		}
		return result;
	}

	private void removeStack(int stackIndex) {
		stacks.remove(stackIndex);
		ensureStackAvailable();
	}

}
