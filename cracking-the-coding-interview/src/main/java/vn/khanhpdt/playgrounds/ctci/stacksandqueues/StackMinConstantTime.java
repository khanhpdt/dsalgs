package vn.khanhpdt.playgrounds.ctci.stacksandqueues;

/**
 * Problem 3.2
 *
 * @author khanhpdt
 */
public class StackMinConstantTime {

	private int size;

	private Comparable[] elements;

	/**
	 * Store the minimum elements for each push.
	 * This array helps to keep the list of the minimum elements associated with each pushing element into the stack.
	 *
	 */
	private Comparable[] mins;

	public StackMinConstantTime() {
		this.size = 0;
		// 100 by default
		this.elements = new Comparable[100];
		this.mins = new Comparable[100];
	}

	public void push(Comparable object) {
		elements[size] = object;

		// keep track of the minimum element
		Comparable currentMin = size == 0 ? null : mins[size - 1];
		if (currentMin == null || currentMin.compareTo(object) > 0) {
			mins[size] = object;
		} else {
			mins[size] = currentMin;
		}

		size++;
	}

	public Comparable pop() {
		Comparable top = elements[size];

		// remove the element
		elements[size] = null;
		// remove the corresponding min
		mins[size] = null;

		size--;

		return top;
	}

	public Comparable min() {
		return mins[size - 1];
	}

}
