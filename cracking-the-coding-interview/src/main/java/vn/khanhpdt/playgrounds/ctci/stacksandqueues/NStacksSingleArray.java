package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

/**
 * Problem 3.1: Implement three stacks with a single array.
 *
 * @author khanhpdt
 */
public class NStacksSingleArray {

	private int nStacks;

	/**
	 * A single array for three stacks
	 *
	 */
	private Object[] elements;

	private Stack[] stacks;

	public NStacksSingleArray(int nStacks) {
		this.nStacks = nStacks;

		this.stacks = new Stack[nStacks];
		for (int i = 0; i < nStacks; i++) {
			this.stacks[i] = new Stack(i);
		}

		// 100 by default
		this.elements = new Object[100];
	}

	private class Stack {

		private final int stackIndex;

		private int size;

		private Stack(int stackIndex) {
			this.stackIndex = stackIndex;
			this.size = 0;
		}

		public void push(Object element) {
			elements[getIndex()] = element;
			size++;
		}

		/**
		 * Gets the index of the element of this stack stored in the common array.
		 *
		 */
		private int getIndex() {
			return nStacks * size + stackIndex;
		}

		public Object pop() {
			Object element = elements[getIndex()];
			size--;
			return element;
		}
	}

}
