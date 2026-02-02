package eu.algites.pltf.knitstro.structure.version.component;

/**
 * Determines how a component participates in version precedence comparisons.
 *
 * The utils does not enforce a specific comparator here; it only provides intent.
 */
public enum AInVersionComponentPrecedenceRole {

	/**
	 * Component affects ordering/precedence.
	 */
	PRECEDENCE,

	/**
	 * Component is metadata (usually ignored for precedence).
	 */
	METADATA,

	/**
	 * Component is for display only (ignored for precedence).
	 */
	DISPLAY

}
