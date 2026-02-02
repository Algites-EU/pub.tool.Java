package eu.algites.pltf.knitstro.structure.version.format;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Default immutable implementation of {@link AIiVersionParsePattern}.
 *
 * This class stores both:
 * - the regex source text (for debugging / documentation),
 * - the compiled {@link Pattern}.
 */
public final class AIcVersionParsePattern implements AIiVersionParsePattern {

	private final String patternSource;
	private final Pattern compiledPattern;

	/**
	 * Creates a parse pattern.
	 *
	 * @param aPatternSource regex source (Java regex)
	 */
	public AIcVersionParsePattern(final String aPatternSource) {
		String locSource = Objects.requireNonNull(aPatternSource, "aPatternSource").trim();
		if (locSource.isEmpty()) {
			throw new IllegalArgumentException("Parse pattern source must not be blank.");
		}

		patternSource = locSource;
		compiledPattern = Pattern.compile(locSource);
	}

	/**
	 * Creates a parse pattern with explicit compilation flags.
	 *
	 * @param aPatternSource regex source
	 * @param aFlags {@link Pattern} flags
	 */
	public AIcVersionParsePattern(final String aPatternSource, final int aFlags) {
		String locSource = Objects.requireNonNull(aPatternSource, "aPatternSource").trim();
		if (locSource.isEmpty()) {
			throw new IllegalArgumentException("Parse pattern source must not be blank.");
		}

		patternSource = locSource;
		compiledPattern = Pattern.compile(locSource, aFlags);
	}

	@Override
	public String patternSource() {
		return patternSource;
	}

	@Override
	public Pattern compiledPattern() {
		return compiledPattern;
	}

}
