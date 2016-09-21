package vn.khanhpdt.playgrounds.datastructures.tables.probings;

import java.util.function.Function;

/**
 * @author khanhpdt
 */
public class QuadraticProbing<K> implements ProbingMethod<K> {

	private final Function<K, Integer> auxiliaryHashFunction;

	private final int nSlots;

	private int sequenceNumber;

	public QuadraticProbing(int nSlots) {
		if (!isPowerOfTwo(nSlots)) {
			throw new IllegalArgumentException("The current quadratic hash function only supports the hash table with the " +
					"number of slots that is a power of two.");
		}

		this.nSlots = nSlots;
		this.sequenceNumber = 0;
		this.auxiliaryHashFunction = this::defaultAuxiliaryHashFunction;
	}

	private boolean isPowerOfTwo(int n) {
		int power = 1;
		while (power < n) {
			power = 2 * power;
		}
		return power == n;
	}

	private Integer defaultAuxiliaryHashFunction(K key) {
		return Math.abs(key.hashCode() % nSlots);
	}

	@Override
	public int probe(K key) {
		int result = hash(key);
		sequenceNumber++;
		return result;
	}

	private Integer hash(K key) {
		// quadratic probing: the hash value is quadratic to the sequence number. However, this implementation can only
		// be guaranteed to work if the number of slots is a power of two.
		int probingOffset = ((sequenceNumber * sequenceNumber + sequenceNumber) / 2) % nSlots;
		return Math.abs((auxiliaryHashFunction.apply(key) + probingOffset) % nSlots);
	}

}
