package vn.khanhpdt.playgrounds.datastructures.tables.probings;

/**
 * @author khanhpdt
 */
public class ProbingMethods {

	public static <K> ProbingMethod<K> create(ProbingMethodName probingMethodName, int nSlots) {
		switch (probingMethodName) {
			case LINEAR_PROBING:
				return new LinearProbing<>(nSlots);
			case QUADRATIC_PROBING:
				return new QuadraticProbing<>(nSlots);
			default:
				throw new UnsupportedOperationException("Not supported yet.");
		}
	}

}
