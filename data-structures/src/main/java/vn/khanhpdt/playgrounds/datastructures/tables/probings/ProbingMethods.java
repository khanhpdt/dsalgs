package vn.khanhpdt.playgrounds.datastructures.tables.probings;

/**
 * @author khanhpdt
 */
public class ProbingMethods {

	public static ProbingMethod create(ProbingMethodName probingMethodName, int nSlots) {
		switch (probingMethodName) {
			case LINEAR_PROBING:
				return new LinearProbing(nSlots);
			default:
				break;
		}
		return null;
	}

}
