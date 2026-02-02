package eu.algites.pltf.knitstro.structure.version;

import java.util.Map;

/**
 * Represents a successfully parsed and normalized canonical version within a scheme.
 *
 * Key principles:
 * - The persisted version state is a canonical string.
 * - Canonical parsing is unique within a scheme: exactly one structure must match.
 * - The resolved version exposes raw component values extracted from canonical parsing.
 *
 * Typed views of values are intentionally not enforced at this interface level.
 * Implementations may provide typed access via component policies.
 */
public interface AIiResolvedVersion {

	/**
	 * Gets the scheme used for parsing.
	 *
	 * @return version scheme
	 */
	AIiVersionScheme scheme();

	/**
	 * Gets the original input text that was parsed.
	 *
	 * @return original version text
	 */
	String originalText();

	/**
	 * Gets the canonical normalized version text.
	 *
	 * Normalization means the utils rendered the canonical format and, optionally,
	 * re-parsed it to ensure it is stable and unambiguous.
	 *
	 * @return canonical version text
	 */
	String canonicalText();

	/**
	 * Gets the uniquely matched structure.
	 *
	 * @return version structure
	 */
	AIiVersionStructure structure();

	/**
	 * Gets raw extracted component values keyed by component code.
	 *
	 * Keys correspond to {@link eu.algites.lib.common.enums.AIiEnumItem#code()}
	 * of components defined by the matched structure.
	 *
	 * @return map of componentCode -> raw value string; missing values may be absent
	 */
	Map<String, String> rawValuesByComponentCode();

}
