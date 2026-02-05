package eu.algites.pltf.knitstro.structure.version;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.pltf.knitstro.structure.version.component.AIiVersionComponent;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionFormat;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionParsePattern;
import eu.algites.pltf.knitstro.structure.version.format.AIiVersionRenderPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Default immutable implementation of {@link AIiVersionStructure}.
 *
 * <p>
 * The structure defines:
 * </p>
 * <ul>
 *   <li>the complete component set required to represent a version state</li>
 *   <li>a set of formats (views) that can parse and/or render version strings</li>
 *   <li>exactly one canonical format (by {@link #canonicalFormatCode()})</li>
 * </ul>
 *
 * <p>
 * This implementation also supports lightweight validation and provides
 * a sample value map for round-trip smoke testing.
 * </p>
 */
public final class AIcVersionStructure implements AIiVersionStructure {

	private final String code;
	private final List<AIiVersionComponent> components;
	private final List<AIiVersionFormat> outputOnlyFormats;
	private final String canonicalFormatCode;
	private final Map<String, String> sampleRawValues;

	/**
	 * Creates a version structure.
	 *
	 * @param aCode structure code
	 * @param aComponents components used by this structure
	 * @param aFormats formats (canonical + output-only)
	 * @param aCanonicalFormatCode canonical format code (must exist in formats)
	 * @param aSampleRawValues sample raw values by component code (may be empty)
	 */
	public AIcVersionStructure(
			final String aCode,
			final List<AIiVersionComponent> aComponents,
			final List<AIiVersionFormat> aFormats,
			final String aCanonicalFormatCode,
			final Map<String, String> aSampleRawValues) {

		String locCode = Objects.requireNonNull(aCode, "aCode").trim();
		if (locCode.isEmpty()) {
			throw new IllegalArgumentException("Structure code must not be blank.");
		}
		code = locCode;

		List<AIiVersionComponent> locComponents = aComponents == null
				? Collections.emptyList()
				: aComponents;
		components = Collections.unmodifiableList(new ArrayList<>(locComponents));

		List<AIiVersionFormat> locFormats = aFormats == null
				? Collections.emptyList()
				: aFormats;
		outputOnlyFormats = Collections.unmodifiableList(new ArrayList<>(locFormats));

		canonicalFormatCode = Objects.requireNonNull(aCanonicalFormatCode, "aCanonicalFormatCode").trim();
		if (canonicalFormatCode.isEmpty()) {
			throw new IllegalArgumentException("Canonical format code must not be blank.");
		}

		Map<String, String> locSample = aSampleRawValues == null
				? Collections.emptyMap()
				: aSampleRawValues;
		sampleRawValues = Collections.unmodifiableMap(new LinkedHashMap<>(locSample));
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public List<AIiVersionComponent> components() {
		return components;
	}

	@Override
	public Optional<AIiVersionComponent> findComponentByCode(final String aComponentCode) {
		if (aComponentCode == null) {
			return Optional.empty();
		}

		for (AIiVersionComponent locComponent : components) {
			if (locComponent != null && aComponentCode.equals(locComponent.code())) {
				return Optional.of(locComponent);
			}
		}

		return Optional.empty();
	}

	@Override
	public List<AIiVersionFormat> outputOnlyFormats() {
		return outputOnlyFormats;
	}

	@Override
	public Optional<AIiVersionFormat> findFormatByCode(final String aFormatCode) {
		if (aFormatCode == null) {
			return Optional.empty();
		}

		for (AIiVersionFormat locFormat : outputOnlyFormats) {
			if (locFormat != null && aFormatCode.equals(locFormat.code())) {
				return Optional.of(locFormat);
			}
		}

		return Optional.empty();
	}

	@Override
	public String canonicalFormatCode() {
		return canonicalFormatCode;
	}

	@Override
	public AIiVersionFormat canonicalFormat() {
		return findFormatByCode(canonicalFormatCode)
				.orElseThrow(() -> new AIxVersionException(
						() -> "Canonical format not found in structure. structure=" + code + ", canonicalFormatCode=" + canonicalFormatCode,
						AInStringOutputMode.USER
				));
	}

	@Override
	public void validate() throws AIxVersionException {
		validateUniqueComponentCodes();
		validateUniqueFormatCodes();
		validateCanonicalFormatExistsOnce();
		validateFormats();
		validateCanonicalRenderReferencesKnownComponents();
		validateCanonicalRoundTripWithSample();
	}

	@Override
	public Map<String, String> sampleRawValues() {
		return sampleRawValues;
	}

	private void validateUniqueComponentCodes() {
		Set<String> locCodes = new LinkedHashSet<>();
		for (AIiVersionComponent locComponent : components) {
			if (locComponent == null) {
				throw new AIxVersionException(
						() -> "Structure contains null component. structure=" + code,
						AInStringOutputMode.USER
				);
			}
			String locCode = locComponent.code();
			if (locCode == null || locCode.trim().isEmpty()) {
				throw new AIxVersionException(
						() -> "Structure contains component with blank code. structure=" + code,
						AInStringOutputMode.USER
				);
			}
			if (!locCodes.add(locCode)) {
				throw new AIxVersionException(
						() -> "Duplicate component code in structure. structure=" + code + ", componentCode=" + locCode,
						AInStringOutputMode.USER
				);
			}
		}
	}

	private void validateUniqueFormatCodes() {
		Set<String> locCodes = new LinkedHashSet<>();
		for (AIiVersionFormat locFormat : outputOnlyFormats) {
			if (locFormat == null) {
				throw new AIxVersionException(
						() -> "Structure contains null format. structure=" + code,
						AInStringOutputMode.USER
				);
			}
			String locCode = locFormat.code();
			if (locCode == null || locCode.trim().isEmpty()) {
				throw new AIxVersionException(
						() -> "Structure contains format with blank code. structure=" + code,
						AInStringOutputMode.USER
				);
			}
			if (!locCodes.add(locCode)) {
				throw new AIxVersionException(
						() -> "Duplicate format code in structure. structure=" + code + ", formatCode=" + locCode,
						AInStringOutputMode.USER
				);
			}
		}
	}

	private void validateCanonicalFormatExistsOnce() {
		int locCount = 0;
		for (AIiVersionFormat locFormat : outputOnlyFormats) {
			if (locFormat != null && canonicalFormatCode.equals(locFormat.code())) {
				locCount++;
			}
		}
		if (locCount != 1) {
			int finalLocCount = locCount;
			throw new AIxVersionException(
					() -> "Structure must contain exactly one canonical format. structure=" + code
							+ ", canonicalFormatCode=" + canonicalFormatCode + ", found=" + finalLocCount,
					AInStringOutputMode.USER
			);
		}
	}

	private void validateFormats() {
		for (AIiVersionFormat locFormat : outputOnlyFormats) {
			try {
				locFormat.validate();
			} catch (RuntimeException locEx) {
				throw new AIxVersionException(
						() -> "Format validation failed. structure=" + code + ", format=" + locFormat.code(),
						locEx,
						AInStringOutputMode.USER
				);
			}
		}

		AIiVersionFormat locCanonical = canonicalFormat();
		if (locCanonical.parsePatterns() == null || locCanonical.parsePatterns().isEmpty()) {
			throw new AIxVersionException(
					() -> "Canonical format must have at least one parse pattern. structure=" + code
							+ ", canonicalFormat=" + locCanonical.code(),
					AInStringOutputMode.USER
			);
		}
	}

	private void validateCanonicalRenderReferencesKnownComponents() {
		AIiVersionFormat locCanonical = canonicalFormat();
		AIiVersionRenderPattern locRender = locCanonical.renderPattern();

		Set<String> locReferenced = locRender.referencedComponentCodes();
		for (String locCode : locReferenced) {
			if (findComponentByCode(locCode).isEmpty()) {
				throw new AIxVersionException(
						() -> "Canonical render pattern references an unknown component code. structure=" + code
								+ ", componentCode=" + locCode + ", template=" + locRender.patternSource(),
						AInStringOutputMode.USER
				);
			}
		}
	}

	private void validateCanonicalRoundTripWithSample() {
		if (sampleRawValues.isEmpty()) {
			return;
		}

		AIiVersionFormat locCanonical = canonicalFormat();
		String locRendered = renderUsingPatternOrThrow(
				locCanonical.renderPattern(),
				sampleRawValues,
				"canonical sample round-trip validation"
		);

		boolean locMatched = false;
		for (AIiVersionParsePattern locParse : locCanonical.parsePatterns()) {
			if (locParse.compiledPattern().matcher(locRendered).matches()) {
				locMatched = true;
				break;
			}
		}

		if (!locMatched) {
			throw new AIxVersionException(
					() -> "Canonical render pattern output does not match any canonical parse pattern. structure=" + code
							+ ", canonicalFormat=" + locCanonical.code() + ", rendered=" + locRendered,
					AInStringOutputMode.USER
			);
		}
	}

	private static String renderUsingPatternOrThrow(
			final AIiVersionRenderPattern aPattern,
			final Map<String, String> aRawValuesByComponentCode,
			final String aPurpose) {

		String locSource = aPattern.patternSource();
		StringBuilder locResult = new StringBuilder();

		Matcher locMatcher = eu.algites.pltf.knitstro.structure.version.component.AIiVersionComponent.COMPONENT_CODE_PATTERN.matcher(locSource);
		int locLast = 0;

		while (locMatcher.find()) {
			locResult.append(locSource, locLast, locMatcher.start());

			String locCode = locMatcher.group(1);
			String locValue = aRawValuesByComponentCode.get(locCode);
			if (locValue == null) {
				throw new AIxVersionException(
						() -> "Missing value for component '" + locCode + "' during " + aPurpose
								+ ". renderPattern=" + locSource,
						AInStringOutputMode.USER
				);
			}

			locResult.append(locValue);
			locLast = locMatcher.end();
		}

		locResult.append(locSource.substring(locLast));
		return locResult.toString();
	}

}
