package eu.algites.pltf.knitstro.structure.version.format;

import eu.algites.pltf.knitstro.structure.version.component.AIiVersionComponent;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Default immutable implementation of {@link AIiVersionRenderPattern}.
 *
 * The pattern source uses placeholder syntax {@code ${componentCode}}.
 *
 * Example:
 * {@code ${year}.${month}_${lane}-${rev}+${build}}
 */
public final class AIcVersionRenderPattern implements AIiVersionRenderPattern {

	private final String patternSource;
	private final Set<String> referencedComponentCodes;

	/**
	 * Creates a render pattern.
	 *
	 * @param aPatternSource pattern source with placeholders
	 */
	public AIcVersionRenderPattern(final String aPatternSource) {
		String locSource = Objects.requireNonNull(aPatternSource, "aPatternSource").trim();
		if (locSource.isEmpty()) {
			throw new IllegalArgumentException("Render pattern source must not be blank.");
		}

		patternSource = locSource;
		referencedComponentCodes = Collections.unmodifiableSet(extractReferencedCodes(locSource));
	}

	@Override
	public String patternSource() {
		return patternSource;
	}

	@Override
	public Set<String> referencedComponentCodes() {
		return referencedComponentCodes;
	}

	private static Set<String> extractReferencedCodes(final String aPatternSource) {
		Set<String> locResult = new LinkedHashSet<>();
		Matcher locMatcher = AIiVersionComponent.COMPONENT_CODE_PATTERN.matcher(aPatternSource);
		while (locMatcher.find()) {
			String locCode = locMatcher.group(1);
			if (locCode != null && !locCode.isEmpty()) {
				locResult.add(locCode);
			}
		}
		return locResult;
	}

}
