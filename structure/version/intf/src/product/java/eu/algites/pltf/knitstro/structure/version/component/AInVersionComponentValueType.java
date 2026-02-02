package eu.algites.pltf.knitstro.structure.version.component;

/**
 * Defines supported primitive value types for version components.
 *
 * This enum provides an utils-level hint for parsing and incrementing.
 * Implementations may support additional types via custom policies.
 */
public enum AInVersionComponentValueType {

	/**
	 * Arbitrary text (no numeric semantics).
	 */
	TEXT,

	/**
	 * Non-negative integer value represented as decimal digits.
	 */
	NON_NEGATIVE_INTEGER,

	/**
	 * Alphanumeric token (letters/digits plus selected separators). Exact validation is policy-defined.
	 */
	ALPHANUMERIC_TOKEN

}
