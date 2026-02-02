package eu.algites.pltf.knitstro.structure.version.format;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.pltf.knitstro.structure.version.AIxVersionException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Default immutable implementation of {@link AIiVersionFormat}.
 *
 * A format is a "view" that can:
 * - parse a version text via one or more regex patterns,
 * - render a version text via a render pattern.
 *
 * Whether the format is canonical is determined by the owning structure
 * (via {@code canonicalFormatCode()}).
 */
public final class AIcVersionFormat implements AIiVersionFormat {

	private final String code;
	private final List<AIiVersionParsePattern> parsePatterns;
	private final AIiVersionRenderPattern renderPattern;

	/**
	 * Creates a format.
	 *
	 * @param aCode format code
	 * @param aParsePatterns parse patterns (may be null/empty)
	 * @param aRenderPattern render pattern (required)
	 */
	public AIcVersionFormat(
			final String aCode,
			final List<AIiVersionParsePattern> aParsePatterns,
			final AIiVersionRenderPattern aRenderPattern) {

		String locCode = Objects.requireNonNull(aCode, "aCode").trim();
		if (locCode.isEmpty()) {
			throw new IllegalArgumentException("Format code must not be blank.");
		}

		code = locCode;
		renderPattern = Objects.requireNonNull(aRenderPattern, "aRenderPattern");

		List<AIiVersionParsePattern> locPatterns = aParsePatterns == null
				? Collections.emptyList()
				: aParsePatterns;

		parsePatterns = Collections.unmodifiableList(new ArrayList<>(locPatterns));
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public List<AIiVersionParsePattern> parsePatterns() {
		return parsePatterns;
	}

	@Override
	public AIiVersionRenderPattern renderPattern() {
		return renderPattern;
	}

	@Override
	public void validate() {
		if (renderPattern == null) {
			throw new AIxVersionException(
					() -> "Version format has null renderPattern. format=" + code(),
					AInStringOutputMode.USER
			);
		}

		String locSource = renderPattern.patternSource();
		if (locSource == null || locSource.trim().isEmpty()) {
			throw new AIxVersionException(
					() -> "Version format has blank renderPattern source. format=" + code(),
					AInStringOutputMode.USER
			);
		}

		for (AIiVersionParsePattern locParsePattern : parsePatterns) {
			if (locParsePattern == null) {
				throw new AIxVersionException(
						() -> "Version format contains a null parse pattern. format=" + code(),
						AInStringOutputMode.USER
				);
			}
			String locRegex = locParsePattern.patternSource();
			if (locRegex == null || locRegex.trim().isEmpty()) {
				throw new AIxVersionException(
						() -> "Version format contains a blank parse pattern source. format=" + code(),
						AInStringOutputMode.USER
				);
			}
			if (locParsePattern.compiledPattern() == null) {
				throw new AIxVersionException(
						() -> "Version format contains a parse pattern with null compiled pattern. format=" + code(),
						AInStringOutputMode.USER
				);
			}
		}
	}

}
