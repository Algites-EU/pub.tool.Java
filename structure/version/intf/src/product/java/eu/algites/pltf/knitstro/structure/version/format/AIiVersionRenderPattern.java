package eu.algites.pltf.knitstro.structure.version.format;

import eu.algites.pltf.knitstro.structure.version.component.AIiVersionComponent;

/**
 * A renderPattern for rendering a version string from component values.
 * <p>
 * Template syntax is intentionally minimal:
 * - placeholders are written as {@code ${componentCode}}.
 * - literal text is any other content.
 *</p>
 *<p>
 * The utils may parse and validate templates in order to:
 * - identify referenced component codes,
 * - render canonical and output outputOnlyFormats.
 * </p>
 * @author linhart1
 */
public interface AIiVersionRenderPattern extends AIiVersionPattern {

	/**
	 * Gets the renderPattern source string (e.g., {@code ${year}.${month}_${lane}-${rev}+${build}}).
	 *
	 * @return renderPattern source
	 */
	@Override
	String patternSource();

	/**
	 * Gets all referenced component codes used by this render pattern.
	 * <p>
	 * Implementations may compute this set from the render pattern source.
	 * </p>
	 * <p>
	 *   The default implementation on this interface counts with the fact the pattern is specified like above documented, so
	 *   like {@code ${year}.${month}_${lane}-${rev}+${build}}
	 * </p>
	 * @return referenced component codes
	 */
	@Override
	default java.util.Set<String> referencedComponentCodes() {
		java.util.Set<String> locResult = new java.util.LinkedHashSet<>();

		String locSource = patternSource();
		if (locSource == null || locSource.isEmpty()) {
			return java.util.Collections.unmodifiableSet(locResult);
		}

		java.util.regex.Matcher locMatcher = AIiVersionComponent.COMPONENT_CODE_PATTERN.matcher(locSource);
		while (locMatcher.find()) {
			String locCode = locMatcher.group(1);
			if (locCode != null && !locCode.isEmpty()) {
				locResult.add(locCode);
			}
		}

		return java.util.Collections.unmodifiableSet(locResult);
	}

}
