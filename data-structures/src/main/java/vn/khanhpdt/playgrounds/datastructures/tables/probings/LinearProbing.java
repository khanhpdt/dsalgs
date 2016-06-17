package vn.khanhpdt.playgrounds.datastructures.tables.probings;

import java.util.UUID;
import java.util.function.Function;

/**
 * @author khanhpdt
 */
public class LinearProbing implements ProbingMethod {

	private final Function<UUID, Integer> auxiliaryHashFunction;

	private final int nSlots;

	private int sequenceNumber;

	public LinearProbing(int nSlots) {
		this.nSlots = nSlots;
		this.sequenceNumber = 0;
		this.auxiliaryHashFunction = this::defaultAuxiliaryHashFunction;
	}

	private Integer defaultAuxiliaryHashFunction(UUID key) {
		return Math.abs(key.hashCode() % nSlots);
	}

	@Override
	public int probe(UUID key) {
		int result = hash(key);
		sequenceNumber++;
		return result;
	}

	private Integer hash(UUID key) {
		// linear probing: the hash value is linear to the sequence number
		int probingOffset = sequenceNumber;
		return Math.abs((auxiliaryHashFunction.apply(key) + probingOffset) % nSlots);
	}

}
