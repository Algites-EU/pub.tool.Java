package eu.algites.pltf.knitstro.structure.version.format;

import eu.algites.lib.common.enums.AIiEnumItem;

import java.util.List;

/**
 * A version format (also called "view") defines:
 * - how to parse a version text (one or more regex patterns),
 * - how to render a version text (a renderPattern),
 * - its role (canonical vs output-only).
 *
 * Canonical format:
 * - Exactly one canonical format must exist per structure.
 * - Canonical renderPattern is used to normalize and persist version state.
 * - Canonical parsing is the primary mechanism for extracting all structure components.
 *
 * Output format:
 * - Output templates are derived from the same component value set.
 * - Output outputOnlyFormats are intended for logs/UI and should not be persisted as state.
 */
public interface AIiVersionFormat extends AIiEnumItem {

	/**
	 * Gets the parse patterns for this format.
	 *
	 * For canonical role, at least one canonical parse pattern must match the canonical renderPattern output.
	 * Additional patterns may exist for backward compatibility (import/migration).
	 *
	 * @return parse patterns (non-empty for canonical; may be empty for output-only)
	 */
	List<AIiVersionParsePattern> parsePatterns();

	/**
	 * Gets the rendering pattern for this format.
	 *
	 * For canonical role, exactly one canonical pattern must exist.
	 *
	 * @return renderPattern
	 */
	AIiVersionRenderPattern renderPattern();

	/**
	 * Validates internal consistency of this format (patterns/renderPattern).
	 *
	 * Implementations should validate:
	 * - non-null renderPattern,
	 * - canonical format has at least one parse pattern,
	 * - renderPattern placeholders use valid component codes (utils-level validation may also apply).
	 *
	 * @throws eu.algites.pltf.knitstro.structure.version.AIxVersionException when invalid
	 */
	void validate();

}
