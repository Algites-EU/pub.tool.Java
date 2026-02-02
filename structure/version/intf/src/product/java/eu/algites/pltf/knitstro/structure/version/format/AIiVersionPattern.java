package eu.algites.pltf.knitstro.structure.version.format;

import java.util.Set;

/**
 * A renderPattern for rendering a version string from component values.
 *
 * Template syntax is intentionally minimal:
 * - placeholders are written as {@code ${componentCode}}.
 * - literal text is any other content.
 *
 * The utils may parse and validate templates in order to:
 * - identify referenced component codes,
 * - render canonical and output outputOnlyFormats.
 */
public interface AIiVersionPattern {

	/**
	 * Gets the pattern source string (for parsing regexp pattern, for rendering rendering pattern).
	 *
	 * @return pattern source
	 */
	String patternSource();

	/**
	 * Gets all referenced component codes used by this pattern.
	 *
	 * Implementations may compute this set from the pattern source or by some other way.
	 *
	 * @return referenced component codes
	 */
	Set<String> referencedComponentCodes();

}
