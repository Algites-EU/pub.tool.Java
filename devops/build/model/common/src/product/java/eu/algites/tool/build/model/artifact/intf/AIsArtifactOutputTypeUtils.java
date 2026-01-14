package eu.algites.tool.build.model.artifact.intf;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: {@link AIsArtifactOutputTypeUtils}
 * </p>
 * <p>
 * Description: Utilities for the Artifact OutputType handling.
 *    especially for creating and parsing Algites Artifact OutputType UIDs.
 *
 * <p>UID format (always exactly 4 segments):
 * {@code <kindClass>:<namespace>:<classifier>:<fileType>}
 *
 * <ul>
 *   <li>{@code kindClass} is {@code builtin} or {@code custom}</li>
 *   <li>For {@code builtin}, {@code namespace} MUST be empty</li>
 *   <li>For {@code custom}, {@code namespace} MUST be non-empty</li>
 *   <li>{@code classifier} MAY be empty</li>
 *   <li>{@code fileType} MUST be non-empty</li>
 * </ul>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 13.01.26 17:08
 */
public class AIsArtifactOutputTypeUtils {

	private static final String SEGMENT_SEPARATOR = ":";
	private static final int SEGMENT_COUNT = 4;

	private static final Pattern SAFE_SEGMENT_PATTERN = Pattern.compile("^[A-Za-z0-9._-]*$");
	private static final Pattern SAFE_NAMESPACE_PATTERN = Pattern.compile("^[A-Za-z0-9._-]+$");

	private AIsArtifactOutputTypeUtils() {}

	/**
	 * Parsed representation of an OutputType UID.
	 *
	 * @param kindClass kind class (builtin/custom)
	 * @param namespace namespace segment (empty for builtin, non-empty for custom)
	 * @param classifier classifier segment (may be empty)
	 * @param packagingFileTypeCode file type segment (non-empty)
	 */
	public record AIcArtifactOutputTypeUidParts(
			AInArtifactOutputTypeClass kindClass,
			String namespace,
			String classifier,
			String packagingFileTypeCode
	) { }

	/**
	 * Create a builtin UID with an optional classifier and required fileType.
	 *
	 * <p>Example: {@code builtin:::jar} or {@code builtin::sources:jar}
	 *
	 * @param aClassifier classifier (may be null/empty)
	 * @param aFileType file type (must be non-empty)
	 * @return UID string
	 */
	public static String createBuiltinUid(String aClassifier, String aFileType) {
		String locNormalizedClassifier = normalizeOptionalSegment(aClassifier);
		String locNormalizedPackagingFileTypeCode = normalizeRequiredSegment(aFileType, "fileType");
		validateSafeSegment(locNormalizedClassifier, "classifier");
		validateSafeSegment(locNormalizedPackagingFileTypeCode, "packagingFileTypeCode");
		return join(AInArtifactOutputTypeClass.BUILTIN, "", locNormalizedClassifier, locNormalizedPackagingFileTypeCode);
	}

	/**
	 * Create a custom UID with required namespace + fileType, optional classifier.
	 *
	 * <p>Example: {@code custom:eu.algites:myclassifier:myext}
	 *
	 * @param aNamespace namespace (must be non-empty)
	 * @param aClassifier classifier (may be null/empty)
	 * @param aFileType file type (must be non-empty)
	 * @return UID string
	 */
	public static String createCustomUid(String aNamespace, String aClassifier, String aFileType) {
		String locNormalizedNamespace = normalizeRequiredSegment(aNamespace, "namespace");
		String locNormalizedClassifier = normalizeOptionalSegment(aClassifier);
		String locNormalizedPackagingFileTypeCode = normalizeRequiredSegment(aFileType, "packagingFileTypeCode");

		validateSafeNamespace(locNormalizedNamespace);
		validateSafeSegment(locNormalizedClassifier, "classifier");
		validateSafeSegment(locNormalizedPackagingFileTypeCode, "packagingFileTypeCode");

		return join(AInArtifactOutputTypeClass.CUSTOM, locNormalizedNamespace, locNormalizedClassifier, locNormalizedPackagingFileTypeCode);
	}

	/**
	 * Parse and validate an OutputType UID.
	 *
	 * @param aUid uid string
	 * @return parsed parts
	 * @throws IllegalArgumentException if invalid
	 */
	public static AIcArtifactOutputTypeUidParts parseUid(String aUid) {
		String locUid = Objects.requireNonNull(aUid, "aUid must not be null");
		String[] locSegments = splitIntoFourSegments(locUid);

		AInArtifactOutputTypeClass locKindClass = AInArtifactOutputTypeClass.fromKindCode(locSegments[0]);
		String locNamespace = locSegments[1];
		String locClassifier = locSegments[2];
		String locPackagingFileTypeCode = locSegments[3];

		validateSemantics(locKindClass, locNamespace, locClassifier, locPackagingFileTypeCode);

		return new AIcArtifactOutputTypeUidParts(locKindClass, locNamespace, locClassifier, locPackagingFileTypeCode);
	}

	/**
	 * Validate a UID (throws on error).
	 *
	 * @param aUid uid string
	 * @throws IllegalArgumentException if invalid
	 */
	public static void validateUid(String aUid) {
		parseUid(aUid);
	}

	/**
	 * @param aUid uid string
	 * @return true if valid, else false
	 */
	public static boolean isValidUid(String aUid) {
		try {
			validateUid(aUid);
			return true;
		} catch (RuntimeException locException) {
			return false;
		}
	}

	/**
	 * @param aUid uid string
	 * @return kind class
	 */
	public static AInArtifactOutputTypeClass getKindClass(String aUid) {
		return parseUid(aUid).kindClass();
	}

	/**
	 * @param aUid uid string
	 * @return namespace segment (empty for builtin)
	 */
	public static String getNamespace(String aUid) {
		return parseUid(aUid).namespace();
	}

	/**
	 * @param aUid uid string
	 * @return classifier segment (may be empty)
	 */
	public static String getClassifier(String aUid) {
		return parseUid(aUid).classifier();
	}

	/**
	 * @param aUid uid string
	 * @return fileType segment (non-empty)
	 */
	public static String getPackagingFileTypeCode(String aUid) {
		return parseUid(aUid).packagingFileTypeCode();
	}

	private static String[] splitIntoFourSegments(String aUid) {
		int locFirst = aUid.indexOf(SEGMENT_SEPARATOR);
		if (locFirst < 0) {
			throw new IllegalArgumentException("Invalid UID (missing ':' separators): '" + aUid + "'");
		}
		String[] locSegments = aUid.split(SEGMENT_SEPARATOR, -1);
		if (locSegments.length != SEGMENT_COUNT) {
			throw new IllegalArgumentException(
					"Invalid UID (expected 4 segments '<kindClass>:<namespace>:<classifier>:<fileType>'): '" + aUid + "'"
			);
		}
		return locSegments;
	}

	private static void validateSemantics(
			AInArtifactOutputTypeClass aKindClass,
			String aNamespace,
			String aClassifier,
			String aPackagingFileTypeCode
	) {
		String locNamespace = Objects.requireNonNull(aNamespace, "namespace segment must not be null");
		String locClassifier = Objects.requireNonNull(aClassifier, "classifier segment must not be null");
		String locPackagingFileTypeCode = Objects.requireNonNull(aPackagingFileTypeCode, "fileType segment must not be null");

		if (locPackagingFileTypeCode.isBlank()) {
			throw new IllegalArgumentException("Invalid UID: packagingFileTypeCode must be non-empty");
		}

		validateSafeSegment(locClassifier, "classifier");
		validateSafeSegment(locPackagingFileTypeCode, "packagingFileTypeCode");

		if (aKindClass == AInArtifactOutputTypeClass.BUILTIN) {
			if (!locNamespace.isEmpty()) {
				throw new IllegalArgumentException("Invalid builtin UID: namespace must be empty");
			}
		} else if (aKindClass == AInArtifactOutputTypeClass.CUSTOM) {
			if (locNamespace.isBlank()) {
				throw new IllegalArgumentException("Invalid custom UID: namespace must be non-empty");
			}
			validateSafeNamespace(locNamespace);
		} else {
			throw new IllegalArgumentException("Unsupported kindClass: " + aKindClass);
		}
	}

	private static void validateSafeSegment(String aSegment, String aSegmentName) {
		if (!SAFE_SEGMENT_PATTERN.matcher(aSegment).matches()) {
			throw new IllegalArgumentException(
					"Invalid " + aSegmentName + " segment '" + aSegment + "': allowed pattern is " + SAFE_SEGMENT_PATTERN.pattern()
			);
		}
	}

	private static void validateSafeNamespace(String aNamespace) {
		if (!SAFE_NAMESPACE_PATTERN.matcher(aNamespace).matches()) {
			throw new IllegalArgumentException(
					"Invalid namespace '" + aNamespace + "': allowed pattern is " + SAFE_NAMESPACE_PATTERN.pattern()
			);
		}
	}

	private static String normalizeOptionalSegment(String aValue) {
		if (aValue == null) {
			return "";
		}
		String locTrimmedValue = aValue.trim();
		return locTrimmedValue;
	}

	private static String normalizeRequiredSegment(String aValue, String aName) {
		String locValue = Objects.requireNonNull(aValue, aName + " must not be null").trim();
		if (locValue.isEmpty()) {
			throw new IllegalArgumentException(aName + " must be non-empty");
		}
		return locValue;
	}

	private static String join(
			AInArtifactOutputTypeClass aKindClass,
			String aNamespace,
			String aClassifier,
			String aPackagingFileTypeCode
	) {
		String locKindCode = aKindClass.getKindCode();
		return locKindCode + SEGMENT_SEPARATOR
				+ aNamespace + SEGMENT_SEPARATOR
				+ aClassifier + SEGMENT_SEPARATOR
				+ aPackagingFileTypeCode;
	}
}
