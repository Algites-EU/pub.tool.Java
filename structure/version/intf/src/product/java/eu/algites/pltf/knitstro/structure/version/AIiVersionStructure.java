package eu.algites.pltf.knitstro.structure.version;

import eu.algites.lib.common.enums.AIiEnumItem;
import eu.algites.pltf.knitstro.structure.version.component.AIiVersionComponent;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionFormat;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A version structure defines the full component set required to represent a version state.
 *
 * The structure itself does not define parsing/rendering directly. Instead, it contains outputOnlyFormats:
 * - Exactly one canonical format (used for persisted version state and canonical normalization).
 * - Zero or more output outputOnlyFormats (for logs/UI).
 *
 * Structures typically represent "states" in a scheme graph (e.g., snapshot, release, rc).
 * If the syntax category changes (e.g., snapshot vs rc vs final), it should be modeled as a different structure.
 */
public interface AIiVersionStructure extends AIiEnumItem {

	/**
	 * Gets all components that constitute the structure state.
	 *
	 * @return components
	 */
	List<AIiVersionComponent> components();

	/**
	 * Finds a component by code.
	 *
	 * @param aComponentCode component code
	 * @return optional component
	 */
	Optional<AIiVersionComponent> findComponentByCode(String aComponentCode);

	/**
	 * Gets outputOnlyFormats (canonical and output-only) defined for this structure.
	 *
	 * @return outputOnlyFormats
	 */
	List<AIiVersionFormat> outputOnlyFormats();

	/**
	 * Finds a format by code.
	 *
	 * @param aFormatCode format code
	 * @return optional format
	 */
	Optional<AIiVersionFormat> findFormatByCode(String aFormatCode);

	/**
	 * Gets the canonical format code for this structure.
	 *
	 * @return canonical format code
	 */
	String canonicalFormatCode();

	/**
	 * Returns the canonical format.
	 *
	 * @return canonical format
	 */
	AIiVersionFormat canonicalFormat();

	/**
	 * Validates that this structure is internally consistent.
	 *
	 * Implementations should validate:
	 * - unique component codes,
	 * - exactly one canonical format and it matches {@link #canonicalFormatCode()},
	 * - each format validates itself,
	 * - canonical renderPattern references only known component codes,
	 * - a canonical format can parse its own canonical renderPattern output using at least one canonical parse pattern.
	 *
	 * Some validations may be deferred to the scheme / utils level (e.g., cross-structure ambiguities).
	 *
	 * @throws AIxVersionException when invalid
	 */
	void validate() throws AIxVersionException;

	/**
	 * Provides a minimal sample raw-values set that should render and parse back via canonical format.
	 *
	 * This is intended for scheme-level smoke tests and round-trip validation.
	 *
	 * @return sample values by component code
	 */
	Map<String, String> sampleRawValues();

}
