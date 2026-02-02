package eu.algites.pltf.knitstro.structure.version;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * Default immutable implementation of {@link AIiResolvedVersion}.
 * </p>
 *
 * <p>
 * This object is a value carrier for results produced by the version parsing/normalization utilities:
 * </p>
 * <ul>
 *   <li>original text that was parsed</li>
 *   <li>canonical text (normalized)</li>
 *   <li>uniquely matched {@link AIiVersionStructure}</li>
 *   <li>raw extracted component values (by component code)</li>
 * </ul>
 *
 * <p>
 * The implementation does not attempt to interpret the values (numeric vs text).
 * That remains responsibility of the scheme/structure/component policies.
 * </p>
 */
public final class AIcResolvedVersion implements AIiResolvedVersion {

	private final AIiVersionScheme scheme;
	private final String originalText;
	private final String canonicalText;
	private final AIiVersionStructure structure;
	private final Map<String, String> rawValuesByComponentCode;

	/**
	 * Creates a resolved version.
	 *
	 * @param aScheme scheme used for parsing
	 * @param aOriginalText original text
	 * @param aCanonicalText canonical text
	 * @param aStructure matched structure
	 * @param aRawValuesByComponentCode raw values by component code
	 */
	public AIcResolvedVersion(
			final AIiVersionScheme aScheme,
			final String aOriginalText,
			final String aCanonicalText,
			final AIiVersionStructure aStructure,
			final Map<String, String> aRawValuesByComponentCode) {

		scheme = Objects.requireNonNull(aScheme, "aScheme");
		originalText = Objects.requireNonNull(aOriginalText, "aOriginalText");
		canonicalText = Objects.requireNonNull(aCanonicalText, "aCanonicalText");
		structure = Objects.requireNonNull(aStructure, "aStructure");

		Map<String, String> locValues = aRawValuesByComponentCode == null
				? Collections.emptyMap()
				: aRawValuesByComponentCode;

		rawValuesByComponentCode = Collections.unmodifiableMap(new LinkedHashMap<>(locValues));
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
