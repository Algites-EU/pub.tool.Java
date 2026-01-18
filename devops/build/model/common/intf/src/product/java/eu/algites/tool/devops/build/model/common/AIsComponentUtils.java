package eu.algites.tool.devops.build.model.common;

import static eu.algites.tool.devops.build.model.common.AInComponentOriginClass.BUILTIN;
import static eu.algites.tool.devops.build.model.common.AInComponentType.NAMESPACE_UID_POSITION;

import jakarta.annotation.Nonnull;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: {@link AIsComponentUtils}
 * </p>
 * <p>
 * Description: Utilities for the Artifact OutputType handling.
 *    especially for creating and parsing Algites Artifact OutputType UIDs.
 *
 * <p>UID format (always exactly 4 segments):
 * {@code <originClass>:<namespace>:<classifier>:<fileType>}
 *
 * <ul>
 *   <li>{@code originClass} is {@code builtin} or {@code custom}</li>
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
public class AIsComponentUtils {

	private static final String SEGMENT_SEPARATOR = ":";

	private static final Pattern SAFE_SEGMENT_PATTERN = Pattern.compile("^[A-Za-z0-9._-]*$");
	private static final Pattern SAFE_NAMESPACE_PATTERN = Pattern.compile("^[A-Za-z0-9._-]+$");

	private AIsComponentUtils() {}

	/**
	 * Parsed representation of an OutputType UID.
	 *
	 * @param originClass origin class (builtin/custom)
	 * @param namespace namespace segment (empty for builtin, non-empty for custom)
	 * @param classifier classifier segment (may be empty)
	 * @param packagingFileTypeCode file type segment (non-empty)
	 */
	public record AIcArtifactOutputTypeUidPartsRecord(
			AInComponentOriginClass originClass,
			String namespace,
			String classifier,
			String packagingFileTypeCode
	) implements AIiArtifactOutputTypeUidPartsRecord { }

	/**
	 * Parsed representation of an Template UID.
	 *
	 * @param originClass origin class (builtin/custom)
	 * @param namespace namespace segment (empty for builtin, non-empty for custom)
	 * @param templateId templateId (must be not empty)
	 */
	public record AIcArtifactTemplateUidPartsRecord(
			AInComponentOriginClass originClass,
			String namespace,
			String templateId
	) implements AIiArtifactTemplateUidPartsRecord { }

	/**
	 * Record characterizing the component type uid part
	 *
	 * @param displayLabel user display label of the Uid component
	 * @param requiredForBuiltin true if required for builtin component class
	 * @param requiredForCustom true if required for custom component class
	 */
	public record AIcComponentUidPartMetadataRecord(
			String displayLabel,
			boolean requiredForBuiltin,
			boolean requiredForCustom
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
	public static String createBuiltinOutputTypeUid(String aClassifier, String aFileType) {
		String locNormalizedClassifier = normalizeOptionalSegment(aClassifier);
		String locNormalizedPackagingFileTypeCode = normalizeRequiredSegment(aFileType, "fileType");
		validateSafeSegment(locNormalizedClassifier, "classifier");
		validateSafeSegment(locNormalizedPackagingFileTypeCode, "packagingFileTypeCode");
		return join(BUILTIN, "", locNormalizedClassifier, locNormalizedPackagingFileTypeCode);
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
	public static String createCustomOutputTypeUid(String aNamespace, String aClassifier, String aFileType) {
		String locNormalizedNamespace = normalizeRequiredSegment(aNamespace, "namespace");
		String locNormalizedClassifier = normalizeOptionalSegment(aClassifier);
		String locNormalizedPackagingFileTypeCode = normalizeRequiredSegment(aFileType, "packagingFileTypeCode");

		validateSafeNamespace(locNormalizedNamespace);
		validateSafeSegment(locNormalizedClassifier, "classifier");
		validateSafeSegment(locNormalizedPackagingFileTypeCode, "packagingFileTypeCode");

		return join(AInComponentOriginClass.CUSTOM, locNormalizedNamespace, locNormalizedClassifier, locNormalizedPackagingFileTypeCode);
	}

	/**
	 * Create a builtin UID with required templateId.
	 *
	 * <p>Example: {@code builtin:::mavenCompile} or {@code builtin::gradleApi}
	 *
	 * @param aTemplateId template id (must be non-empty)
	 * @return UID string
	 */
	public static String createBuiltinTemplateUid(String aTemplateId) {
		String locNormalizedTemplateId = normalizeRequiredSegment(aTemplateId, "template-id");
		validateSafeSegment(locNormalizedTemplateId, "template-id");
		return join(BUILTIN, "", locNormalizedTemplateId);
	}

	/**
	 * Create a custom UID with required namespace and templateId.
	 *
	 * <p>Example: {@code custom:eu.algites:mytemplateId}
	 *
	 * @param aNamespace namespace (must be non-empty)
	 * @param aTemplateId templateId (must be non-empty)
	 * @return UID string
	 */
	public static String createCustomTemplateUid(String aNamespace, String aTemplateId) {
		String locNormalizedNamespace = normalizeRequiredSegment(aNamespace, "namespace");
		String locNormalizedTemplateId = normalizeRequiredSegment(aTemplateId, "template-id");

		validateSafeNamespace(locNormalizedNamespace);
		validateSafeSegment(locNormalizedTemplateId, "template-id");

		return join(AInComponentOriginClass.CUSTOM, locNormalizedNamespace, locNormalizedTemplateId);
	}

	/**
	 * Parse and validate an OutputType UID.
	 *
	 * @param aComponentType component type for which the result has to be returned
	 * @param aUid uid string
	 * @return parsed parts
	 * @throws IllegalArgumentException if invalid
	 * @param <R> type of the result expected.
	 */
	@SuppressWarnings("unchecked")
	public static <R extends AIiArtifactUidPartsRecord> R parseUid(@Nonnull AInComponentType aComponentType, @Nonnull String aUid) {
		Objects.requireNonNull(aComponentType, "Component type must not be null");
		String locUid = Objects.requireNonNull(aUid, "aUid must not be null");
		String[] locSegments = splitIntoSegments(locUid, aComponentType);

		AInComponentOriginClass locOriginClass = AInComponentOriginClass.getByCodeOrThrow(locSegments[0]);

		validateSemantics(aComponentType, locOriginClass, locSegments);

		if (locOriginClass == BUILTIN)
			return (R) aComponentType.getBuiltinRecordConstructor().apply(aUid);

		return (R) aComponentType.getCustomRecordConstructor().apply(locSegments);
	}

	/**
	 * Validate a UID (throws on error).
	 *
	 * @param aComponentType component type to be examined
	 * @param aUid uid string
	 * @throws IllegalArgumentException if invalid
	 */
	public static void validateUid(final AInComponentType aComponentType, String aUid) {
		parseUid(aComponentType, aUid);
	}

	/**
	 * @param aComponentType component type to be examined
	 * @param aUid uid string
	 * @return true if valid, else false
	 */
	public static boolean isValidOutputTypeUid(final AInComponentType aComponentType, String aUid) {
		try {
			validateUid(aComponentType, aUid);
			return true;
		} catch (RuntimeException locException) {
			return false;
		}
	}

	/**
	 * @param aComponentType component type to be examined
	 * @param aUid uid string
	 * @return kind class
	 */
	public static AInComponentOriginClass getKindClass(final AInComponentType aComponentType, String aUid) {
		return parseUid(aComponentType, aUid).originClass();
	}

	/**
	 * @param aComponentType component type to be examined
	 * @param aUid uid string
	 * @return namespace segment (empty for builtin)
	 */
	public static String getNamespace(final AInComponentType aComponentType, String aUid) {
		return parseUid(aComponentType, aUid).namespace();
	}


	private static String[] splitIntoSegments(String aUid, final AInComponentType aComponentType) {
		int locFirst = aUid.indexOf(SEGMENT_SEPARATOR);
		if (locFirst < 0) {
			throw new IllegalArgumentException("Invalid UID (missing ':' separators): '" + aUid + "'");
		}
		String[] locSegments = aUid.split(SEGMENT_SEPARATOR, -1);
		if (locSegments.length != aComponentType.getUidSegmentCount()) {
			throw new IllegalArgumentException(
					"Invalid UID (expected " + aComponentType.getUidSegmentCount()
							+ " segments '" + String.join(SEGMENT_SEPARATOR, aComponentType.getUidSegmentMetadata().stream().map(locName -> "<" + locName + ">").toList()) + "': '" + aUid + "'"
			);
		}
		return locSegments;
	}

	private static void validateSemantics(
			@Nonnull AInComponentType aComponentType,
			@Nonnull AInComponentOriginClass aKindClass,
			@Nonnull String[] aSegments
	) {
		if (aSegments.length != aComponentType.getUidSegmentCount()) {
			throw new IllegalArgumentException(
					"Invalid UID (expected " + aComponentType.getUidSegmentCount()
							+ " segments but resolved " + aSegments.length + " from Uid '" + String.join(SEGMENT_SEPARATOR, aSegments) + "'"
			);
		}
		String locNamespace = Objects.requireNonNull(aSegments[NAMESPACE_UID_POSITION], "namespace segment must not be null");

		if (aKindClass == BUILTIN) {
			if (!locNamespace.isEmpty()) {
				throw new IllegalArgumentException("Invalid builtin UID: " 
						+ aComponentType.getUidSegmentMetadata().get(NAMESPACE_UID_POSITION).displayLabel() 
						+ " must be empty");
			}
		} else if (aKindClass == AInComponentOriginClass.CUSTOM) {
			if (locNamespace.isBlank()) {
				throw new IllegalArgumentException("Invalid builtin UID: "
						+ aComponentType.getUidSegmentMetadata().get(NAMESPACE_UID_POSITION).displayLabel()
						+ " must be non-blank");
			}
			validateSafeNamespace(locNamespace);
		} else {
			throw new IllegalArgumentException("Unsupported originClass: " + aKindClass);
		}
		for (int i = NAMESPACE_UID_POSITION + 1; i < aComponentType.getUidSegmentCount(); i++) {
			Objects.requireNonNull(aSegments[i], aComponentType.getUidSegmentMetadata().get(i).displayLabel() + " segment must not be null");
			validateSafeSegment(aSegments[i], aComponentType.getUidSegmentMetadata().get(i).displayLabel());
		}
	}

	private static void validateSafeSegment(CharSequence aSegment, String aSegmentName) {
		if (!SAFE_SEGMENT_PATTERN.matcher(aSegment).matches()) {
			throw new IllegalArgumentException(
					"Invalid " + aSegmentName + " segment '" + aSegment + "': allowed pattern is " + SAFE_SEGMENT_PATTERN.pattern()
			);
		}
	}

	private static void validateSafeNamespace(CharSequence aNamespace) {
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
			AInComponentOriginClass aOriginClass,
			String... aUidPartsWithoutClass
	) {
		String locOriginCode = aOriginClass.getCode();
		return locOriginCode + SEGMENT_SEPARATOR
				+ String.join(SEGMENT_SEPARATOR, aUidPartsWithoutClass);
	}
}
