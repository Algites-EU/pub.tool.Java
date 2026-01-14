package eu.algites.tool.build.model.artifact.intf;

import static eu.algites.tool.build.model.artifact.intf.AInArtifactOutputPackaging.JAR;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactOutputPackaging.POM;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactOutputPackaging.fromOutputPackagingCode;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactOutputTypeClass.BUILTIN;
import static eu.algites.tool.build.model.artifact.intf.AIsArtifactOutputTypeUtils.createBuiltinUid;

import java.util.regex.Pattern;

/**
 * <p>
 * Title: {@link AInArtifactBuiltinOutputType}
 * </p>
 * <p>
 * Description: Defines the built-in output types,
 * which can occur if the output type is of the class {@link AInArtifactOutputTypeClass#BUILTIN} and
 * supported out-of-the-box by the Algites Artifacts facility
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 11.01.26 4:55
 */
public enum AInArtifactBuiltinOutputType implements AIiArtifactOutputType {

	MAIN_MAVEN_POM(createBuiltinUid(null, POM.getPackagingFileTypeCode()), true),
	MAIN_JAVA_CLASSES_JAR(createBuiltinUid(null, JAR.getPackagingFileTypeCode()), true),
	MAIN_JAVA_SOURCE_JAR(createBuiltinUid("sources", JAR.getPackagingFileTypeCode()), true),
	MAIN_JAVA_DOC_JAR(createBuiltinUid("javadoc", JAR.getPackagingFileTypeCode()), true),
	TEST_JAVA_CLASSES_JAR(createBuiltinUid("tests", JAR.getPackagingFileTypeCode()), true),
	TEST_JAVA_SOURCE_JAR(createBuiltinUid("test-sources", JAR.getPackagingFileTypeCode()), true),
	TEST_JAVA_DOC_JAR(createBuiltinUid("test-javadoc", JAR.getPackagingFileTypeCode()), true),
	POLICY_BACKGROUND_BOM(createBuiltinUid("pbbom", POM.getPackagingFileTypeCode()), false),
	;

	private static final String SEGMENT_SEPARATOR = ":";
	private static final int SEGMENT_COUNT = 4;
	private static final Pattern SAFE_SEGMENT_PATTERN = Pattern.compile("^[A-Za-z0-9._-]*$");
	private static final Pattern SAFE_NAMESPACE_PATTERN = Pattern.compile("^[A-Za-z0-9._-]+$");

	private final String classifier;
	private final AInArtifactOutputPackaging packaging;
	private final boolean standardOutput;
	private String outputTypeCode;

	AInArtifactBuiltinOutputType(final String aOutputTypeCode, final boolean aStandardOutput) {
		standardOutput = aStandardOutput;
		AIsArtifactOutputTypeUtils.AIcArtifactOutputTypeUidParts locParsedInput = AIsArtifactOutputTypeUtils.parseUid(aOutputTypeCode);
		if (locParsedInput.kindClass() != BUILTIN)
			throw new IllegalArgumentException("Unsupported kindClass: '" + locParsedInput.kindClass() + "'");
		classifier = locParsedInput.classifier();
		packaging = fromOutputPackagingCode(locParsedInput.packagingFileTypeCode());
		if (packaging == null)
			throw new IllegalArgumentException("Unsupported packaging: '" + locParsedInput.packagingFileTypeCode() + "'");
		outputTypeCode = aOutputTypeCode;
	}

	public String getOutputTypeUid() {
		return outputTypeCode;
	}

	/**
	 * Returns the default output type for the parent artifact.
	 * @return {@link #MAIN_MAVEN_POM} as the default for parent output type
	 */
	public static AInArtifactBuiltinOutputType getDefaultParentPackagingClass() {
		return MAIN_MAVEN_POM;
	}

	/**
	 * Returns the default output type for the dependency artifacts.
	 * @return {@link #MAIN_JAVA_CLASSES_JAR} as the default for dependency packaging
	 */
	public static AInArtifactBuiltinOutputType getDefaultDependencyPackagingClass() {
		return MAIN_JAVA_CLASSES_JAR;
	}

//	/**
//	 * Convenience method to find the builtin output class for the given artifact kind and output local identification.
//	 * @param aArtifactKind the artifact kind
//	 * @param aOutputLocalIdentification the output local identification
//	 * @return the found output class or null, if no output class was found
//	 */
//	public static AInArtifactBuiltinOutputType find(AInArtifactKind aArtifactKind, AIcArtifactOutputLocalKey aOutputLocalIdentification) {
//		return find(aArtifactKind,
//				aOutputLocalIdentification == null ? null : aOutputLocalIdentification.getOutputClassifier(),
//				aOutputLocalIdentification == null ? null : aOutputLocalIdentification.getOutputPackagingId());
//	}
//
//	/**
//	 * Finds the builtin output class for the given artifact kind, classifier and packaging.
//	 * @param aArtifactKind the artifact kind
//	 * @param aClassifier classifier for which the search has to be executed
//	 * @param aPackagingId packaging for which the search has to be executed
//	 * @return the found output class or null, if no output class was found
//	 */
//	public static AInArtifactBuiltinOutputType find(String aClassifier, String aPackagingId) {
//		if (aClassifier == null || aClassifier.isBlank())
//			return aPackagingId == null || aPackagingId.isBlank()
//					|| Objects.equals(aArtifactKind.getMainOutputTypeClass().getAssignedOutputPackagingId(), aPackagingId) ? DEFAULT_MAIN : null;
//		for (AInArtifactBuiltinOutputType locOutputClass : values()) {
//			if (locOutputClass == DEFAULT_MAIN) continue;
//			if (Objects.equals(locOutputClass.getClassifier(), aClassifier)) {
//					if (aPackagingId == null || aPackagingId.isBlank()
//							|| Objects.equals(aPackagingId, locOutputClass.getPackaging().getAssignedOutputPackagingId()))
//						return locOutputClass;
//			}
//		}
//		return null;
//	}

	@Override
	public String getClassifier() {
		return classifier;
	}

	/**
	 * @return the packaging
	 */
	public AInArtifactOutputPackaging getPackaging() {
		return packaging;
	}

	@Override
	public String getPackagingFileTypeCode() {
		return packaging.getPackagingFileTypeCode();
	}

	/**
	 * @return the standardOutput
	 */
	public boolean isStandardOutput() {
		return standardOutput;
	}
}
