package vn.khanhpdt.playgrounds.datastructures.tables.probings;

import java.util.function.Function;

/**
 * @author khanhpdt
 */
public class LinearProbing<K> implements ProbingMethod<K> {

	private final Function<K, Integer> auxiliaryHashFunction;

	private final int nSlots;

	private int sequenceNumber;

	public LinearProbing(int nSlots) {
		this.nSlots = nSlots;
		this.sequenceNumber = 0;
		this.auxiliaryHashFunction = this::defaultAuxiliaryHashFunction;
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
		// linear probing: the hash value is linear to the sequence number
		int probingOffset = sequenceNumber;
		return Math.abs((auxiliaryHashFunction.apply(key) + probingOffset) % nSlots);
	}

}
