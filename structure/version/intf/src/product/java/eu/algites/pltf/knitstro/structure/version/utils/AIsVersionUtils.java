package eu.algites.pltf.knitstro.structure.version.utils;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.pltf.knitstro.structure.version.AIiResolvedVersion;
import eu.algites.pltf.knitstro.structure.version.AIiVersionScheme;
import eu.algites.pltf.knitstro.structure.version.AIiVersionStructure;
import eu.algites.pltf.knitstro.structure.version.AIxVersionException;
import eu.algites.pltf.knitstro.structure.version.component.AIiVersionComponent;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionFormat;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionParsePattern;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionRenderPattern;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Version utils for parsing, canonical normalization and rendering outputs.
 *
 * Key principles:
 * <ul>
 *   <li>Persisted version state is always a canonical string (structure canonical format).</li>
 *   <li>Canonical parsing must uniquely identify exactly one structure in the scheme.</li>
 *   <li>Canonical normalization renders canonical render pattern and verifies stable canonical parsing.</li>
 *   <li>Output formats are derived from canonical component values and are not persisted as state.</li>
 * </ul>
 *
 * This class is intentionally implemented as a static utility class.
 */
public final class AIsVersionUtils {

	/**
	 * Prevent instantiation.
	 */
	private AIsVersionUtils() {
		/* no-op */
	}

	/**
	 * Parses the given version text within the scheme and requires a unique canonical structure match.
	 *
	 * Typical usage:
	 * <ul>
	 *   <li>state file contains a canonical version string</li>
	 *   <li>utils parses it using canonical parse patterns to extract all component values</li>
	 * </ul>
	 *
	 * @param aScheme scheme used for parsing
	 * @param aVersionText version text (expected canonical)
	 * @return resolved version with canonical text
	 * @throws AIxVersionNotRecognizedException when no structure matches
	 * @throws AIxVersionAmbiguousException when more than one structure matches
	 */
	public static AIiResolvedVersion parseCanonicalUnique(
			final AIiVersionScheme aScheme,
			final String aVersionText) throws AIxVersionNotRecognizedException, AIxVersionAmbiguousException {

		Objects.requireNonNull(aScheme, "aScheme");
		String locVersionText = requireNonBlank(aVersionText, "aVersionText");

		AIcMatch locMatch = matchUniqueCanonicalStructure(aScheme, locVersionText);

		return new AIcResolvedVersion(
				aScheme,
				locVersionText,
				locVersionText,
				locMatch.structure,
				locMatch.rawValuesByComponentCode
		);
	}

	/**
	 * Normalizes component values to canonical text of the given structure.
	 *
	 * The utils must:
	 * <ul>
	 *   <li>render the structure canonical format render pattern</li>
	 *   <li>ensure the rendered string is parsable as the same structure canonical format</li>
	 *   <li>ensure the canonical parsing is unambiguous within the scheme</li>
	 * </ul>
	 *
	 * @param aScheme scheme
	 * @param aStructureCode target structure code
	 * @param aRawValuesByComponentCode raw values by component code
	 * @return canonical version text
	 * @throws AIxVersionException when structure is unknown or normalization fails
	 */
	public static String normalizeToCanonical(
			final AIiVersionScheme aScheme,
			final String aStructureCode,
			final Map<String, String> aRawValuesByComponentCode) throws AIxVersionException {

		Objects.requireNonNull(aScheme, "aScheme");
		String locStructureCode = requireNonBlank(aStructureCode, "aStructureCode");

		Map<String, String> locRawValues = aRawValuesByComponentCode == null
				? Collections.emptyMap()
				: aRawValuesByComponentCode;

		AIiVersionStructure locStructure = aScheme.findStructureByCode(locStructureCode)
				.orElseThrow(() -> new AIxVersionException(
						() -> "Unknown version structure code: " + locStructureCode,
						AInStringOutputMode.USER
				));

		AIiVersionFormat locCanonicalFormat = locStructure.canonicalFormat();
		AIiVersionRenderPattern locRenderPattern = locCanonicalFormat.renderPattern();

		String locRenderedCanonical = renderUsingPatternOrThrow(
				locRenderPattern,
				locStructure,
				locRawValues,
				"canonical normalization"
		);

		try {
			AIiResolvedVersion locParsed = parseCanonicalUnique(aScheme, locRenderedCanonical);
			if (!locParsed.structure().code().equals(locStructureCode)) {
				throw new AIxVersionException(
						() -> "Canonical normalization produced a version that matches a different structure. "
								+ "Expected structure=" + locStructureCode + ", but matched=" + locParsed.structure().code()
								+ ". canonicalText=" + locRenderedCanonical,
						AInStringOutputMode.USER
				);
			}
		} catch (AIxVersionNotRecognizedException | AIxVersionAmbiguousException locEx) {
			throw new AIxVersionException(
					() -> "Canonical normalization produced a text that is not a unique canonical match in the scheme: "
							+ locRenderedCanonical,
					locEx,
					AInStringOutputMode.USER
			);
		}

		return locRenderedCanonical;
	}

	/**
	 * Renders a version string using an output format for the given resolved canonical version.
	 *
	 * Output formats are intended for logs/UI. The utils should not persist them as state.
	 *
	 * @param aResolvedVersion resolved canonical version
	 * @param aFormatCode output format code
	 * @return rendered output text
	 * @throws AIxVersionException when format is unknown or rendering fails
	 */
	public static String renderOutput(
			final AIiResolvedVersion aResolvedVersion,
			final String aFormatCode) throws AIxVersionException {

		Objects.requireNonNull(aResolvedVersion, "aResolvedVersion");
		String locFormatCode = requireNonBlank(aFormatCode, "aFormatCode");

		AIiVersionStructure locStructure = aResolvedVersion.structure();
		Optional<AIiVersionFormat> locFormatOpt = locStructure.findFormatByCode(locFormatCode);
		if (locFormatOpt.isEmpty()) {
			throw new AIxVersionException(
					() -> "Unknown version format code: " + locFormatCode + " for structure: " + locStructure.code(),
					AInStringOutputMode.USER
			);
		}

		AIiVersionFormat locFormat = locFormatOpt.get();
		AIiVersionRenderPattern locRenderPattern = locFormat.renderPattern();

		Map<String, String> locValues = aResolvedVersion.rawValuesByComponentCode() == null
				? Collections.emptyMap()
				: aResolvedVersion.rawValuesByComponentCode();

		return renderUsingPatternOrThrow(
				locRenderPattern,
				locStructure,
				locValues,
				"output rendering (" + locFormatCode + ")"
		);
	}

	private static String renderUsingPatternOrThrow(
			final AIiVersionRenderPattern aPattern,
			final AIiVersionStructure aStructure,
			final Map<String, String> aRawValuesByComponentCode,
			final String aContextLabel) {

		Objects.requireNonNull(aPattern, "aPattern");
		Objects.requireNonNull(aStructure, "aStructure");
		Objects.requireNonNull(aRawValuesByComponentCode, "aRawValuesByComponentCode");
		String locPatternSource = requireNonBlank(aPattern.patternSource(), "patternSource");

		StringBuilder locResult = new StringBuilder();
		Matcher locMatcher = AIiVersionComponent.COMPONENT_CODE_PATTERN.matcher(locPatternSource);

		int locLast = 0;
		while (locMatcher.find()) {
			locResult.append(locPatternSource, locLast, locMatcher.start());

			String locComponentCode = locMatcher.group(1);
			if (locComponentCode == null || locComponentCode.isEmpty()) {
				throw new AIxVersionException(
						() -> "Invalid placeholder in render pattern for " + aContextLabel + ": " + locPatternSource,
						AInStringOutputMode.USER
				);
			}

			if (aStructure.findComponentByCode(locComponentCode).isEmpty()) {
				throw new AIxVersionException(
						() -> "Render pattern references unknown component code '" + locComponentCode
								+ "' for structure '" + aStructure.code() + "' in " + aContextLabel
								+ ". pattern=" + locPatternSource,
						AInStringOutputMode.USER
				);
			}

			String locValue = aRawValuesByComponentCode.get(locComponentCode);
			if (locValue == null) {
				throw new AIxVersionException(
						() -> "Missing raw value for component '" + locComponentCode + "' required by render pattern in "
								+ aContextLabel + ". structure=" + aStructure.code() + ", pattern=" + locPatternSource,
						AInStringOutputMode.USER
				);
			}

			locResult.append(locValue);

			locLast = locMatcher.end();
		}

		locResult.append(locPatternSource.substring(locLast));
		return locResult.toString();
	}

	private static AIcMatch matchUniqueCanonicalStructure(
			final AIiVersionScheme aScheme,
			final String aVersionText) throws AIxVersionNotRecognizedException, AIxVersionAmbiguousException {

		List<AIiVersionStructure> locStructures = aScheme.structures();
		if (locStructures == null || locStructures.isEmpty()) {
			throw new AIxVersionNotRecognizedException(
					() -> "Version scheme contains no structures, cannot parse: " + aVersionText,
					AInStringOutputMode.USER
			);
		}

		AIcMatch locUniqueMatch = null;

		for (AIiVersionStructure locStructure : locStructures) {
			if (locStructure == null) {
				continue;
			}

			AIiVersionFormat locCanonicalFormat;
			try {
				locCanonicalFormat = locStructure.canonicalFormat();
			} catch (Throwable locEx) {
				continue;
			}

			List<AIiVersionParsePattern> locParsePatterns = locCanonicalFormat.parsePatterns();
			if (locParsePatterns == null || locParsePatterns.isEmpty()) {
				continue;
			}

			AIcMatch locStructureMatch = tryMatchAnyPattern(locStructure, locParsePatterns, aVersionText);
			if (locStructureMatch != null) {
				if (locUniqueMatch == null) {
					locUniqueMatch = locStructureMatch;
				} else {
					AIcMatch finalLocUniqueMatch = locUniqueMatch;
					throw new AIxVersionAmbiguousException(
							() -> "Ambiguous canonical parsing. Version text matches more than one structure. "
									+ "text=" + aVersionText
									+ ", first=" + finalLocUniqueMatch.structure.code()
									+ ", also=" + locStructureMatch.structure.code(),
							AInStringOutputMode.USER
					);
				}
			}
		}

		if (locUniqueMatch == null) {
			throw new AIxVersionNotRecognizedException(
					() -> "Version text not recognized by any canonical parse pattern in scheme. text=" + aVersionText,
					AInStringOutputMode.USER
			);
		}

		return locUniqueMatch;
	}

	private static AIcMatch tryMatchAnyPattern(
			final AIiVersionStructure aStructure,
			final List<AIiVersionParsePattern> aParsePatterns,
			final String aVersionText) {

		for (AIiVersionParsePattern locParsePattern : aParsePatterns) {
			if (locParsePattern == null) {
				continue;
			}

			Pattern locCompiled = locParsePattern.compiledPattern();
			if (locCompiled == null) {
				String locSource = locParsePattern.patternSource();
				if (locSource != null && !locSource.isEmpty()) {
					locCompiled = Pattern.compile(locSource);
				}
			}

			if (locCompiled == null) {
				continue;
			}

			Matcher locMatcher = locCompiled.matcher(aVersionText);
			if (!locMatcher.matches()) {
				continue;
			}

			Map<String, String> locExtracted = extractNamedGroupsForStructure(aStructure, locMatcher);
			return new AIcMatch(aStructure, locExtracted);
		}

		return null;
	}

	private static Map<String, String> extractNamedGroupsForStructure(
			final AIiVersionStructure aStructure,
			final Matcher aMatcher) {

		Map<String, String> locResult = new LinkedHashMap<>();

		List<AIiVersionComponent> locComponents = aStructure.components();
		if (locComponents == null) {
			return Collections.unmodifiableMap(locResult);
		}

		for (AIiVersionComponent locComponent : locComponents) {
			if (locComponent == null) {
				continue;
			}

			String locCode = locComponent.code();
			if (locCode == null || locCode.isEmpty()) {
				continue;
			}

			try {
				String locValue = aMatcher.group(locCode);
				if (locValue != null) {
					locResult.put(locCode, locValue);
				}
			} catch (IllegalArgumentException ignored) {
				/* group not present in this pattern */
			}
		}

		return Collections.unmodifiableMap(locResult);
	}

	private static String requireNonBlank(final String aValue, final String aParamName) {
		if (aValue == null) {
			throw new NullPointerException(aParamName);
		}
		String locTrimmed = aValue.trim();
		if (locTrimmed.isEmpty()) {
			throw new IllegalArgumentException(aParamName + " must not be blank");
		}
		return aValue;
	}

	private static final class AIcMatch {
		private final AIiVersionStructure structure;
		private final Map<String, String> rawValuesByComponentCode;

		private AIcMatch(final AIiVersionStructure aStructure, final Map<String, String> aRawValuesByComponentCode) {
			structure = aStructure;
			rawValuesByComponentCode = aRawValuesByComponentCode;
		}
	}

	private static final class AIcResolvedVersion implements AIiResolvedVersion {

		private final AIiVersionScheme scheme;
		private final String originalText;
		private final String canonicalText;
		private final AIiVersionStructure structure;
		private final Map<String, String> rawValuesByComponentCode;

		private AIcResolvedVersion(
				final AIiVersionScheme aScheme,
				final String aOriginalText,
				final String aCanonicalText,
				final AIiVersionStructure aStructure,
				final Map<String, String> aRawValuesByComponentCode) {

			scheme = aScheme;
			originalText = aOriginalText;
			canonicalText = aCanonicalText;
			structure = aStructure;
			rawValuesByComponentCode = aRawValuesByComponentCode == null
					? Collections.emptyMap()
					: Collections.unmodifiableMap(new LinkedHashMap<>(aRawValuesByComponentCode));
		}

		@Override
		public AIiVersionScheme scheme() {
			return scheme;
		}

		@Override
		public String originalText() {
			return originalText;
		}

		@Override
		public String canonicalText() {
			return canonicalText;
		}

		@Override
		public AIiVersionStructure structure() {
			return structure;
		}

		@Override
		public Map<String, String> rawValuesByComponentCode() {
			return rawValuesByComponentCode;
		}
	}
}
