package eu.algites.pltf.knitstro.structure.version.format;

import java.util.regex.Pattern;

/**
 * A parse pattern used to recognize and extract component values.
 *
 * Patterns are expected to use Java named capturing groups:
 * {@code (?<componentCode>...)} so the utils can extract values directly by component codes.
 */
public interface AIiVersionParsePattern {

	/**
	 * Gets the pattern source string.
	 *
	 * @return regex source
	 */
	String patternSource();

	/**
	 * Gets the compiled pattern.
	 *
	 * Implementations may compile lazily; the utils may also choose to compile itself.
	 *
	 * @return compiled pattern
	 */
	Pattern compiledPattern();

}
