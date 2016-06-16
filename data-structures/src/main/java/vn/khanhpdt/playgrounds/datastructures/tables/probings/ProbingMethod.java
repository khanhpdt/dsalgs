package vn.khanhpdt.playgrounds.datastructures.tables.probings;

import java.util.UUID;

/**
 * @author khanhpdt
 */
public interface ProbingMethod {

	int probe(UUID key);

	int firstProbe(UUID key);
}
