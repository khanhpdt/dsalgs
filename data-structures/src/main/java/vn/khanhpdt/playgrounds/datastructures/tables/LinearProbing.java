package vn.khanhpdt.playgrounds.datastructures.tables;

import java.util.UUID;
import java.util.function.Function;

/**
 * @author khanhpdt
 */
public class LinearProbing implements ProbingMethod {

	private final int nSlots;

	private final Function<UUID, Integer> auxiliaryHashFunction;

	private int sequenceNumber;

	private Integer auxiliaryHash;

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
		// save the result because it will not change during the usage of this probing method
		if (auxiliaryHash == null) {
			auxiliaryHash = auxiliaryHashFunction.apply(key);
		}

		// linear probing because the sequence number is linearly increased after each probe
		return Math.abs((auxiliaryHash + sequenceNumber) % nSlots);
	}

}
