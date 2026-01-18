package eu.algites.tool.devops.build.model.common;

import static eu.algites.tool.devops.build.model.artifact.AInArtifactOutputPackaging.JAR;
import static eu.algites.tool.devops.build.model.artifact.AInArtifactOutputPackaging.POM;
import static eu.algites.tool.devops.build.model.artifact.AInArtifactOutputPackaging.getByCodeOrThrow;
import static eu.algites.tool.devops.build.model.common.AInComponentOriginClass.BUILTIN;
import static eu.algites.tool.devops.build.model.common.AInComponentType.OUTPUT_TYPE;
import static eu.algites.tool.devops.build.model.common.AIsComponentUtils.createBuiltinOutputTypeUid;

import eu.algites.tool.devops.build.model.artifact.AInArtifactOutputPackaging;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: {@link AInArtifactBuiltinOutputType}
 * </p>
 * <p>
 * Description: Defines the built-in output types,
 * which can occur if the output type is of the class {@link AInComponentOriginClass#BUILTIN} and
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
public enum AInArtifactBuiltinOutputType implements AIiArtifactOutputType, AIiArtifactOutputTypeUidPartsRecord {

	MAIN_MAVEN_POM(createBuiltinOutputTypeUid(null, POM.getPackagingFileTypeCode()), true),
	MAIN_JAVA_CLASSES_JAR(createBuiltinOutputTypeUid(null, JAR.getPackagingFileTypeCode()), true),
	MAIN_JAVA_SOURCE_JAR(createBuiltinOutputTypeUid("sources", JAR.getPackagingFileTypeCode()), true),
	MAIN_JAVA_DOC_JAR(createBuiltinOutputTypeUid("javadoc", JAR.getPackagingFileTypeCode()), true),
	TEST_JAVA_CLASSES_JAR(createBuiltinOutputTypeUid("tests", JAR.getPackagingFileTypeCode()), true),
	TEST_JAVA_SOURCE_JAR(createBuiltinOutputTypeUid("test-sources", JAR.getPackagingFileTypeCode()), true),
	TEST_JAVA_DOC_JAR(createBuiltinOutputTypeUid("test-javadoc", JAR.getPackagingFileTypeCode()), true),
	POLICY_BACKGROUND_BOM(createBuiltinOutputTypeUid("pbbom", POM.getPackagingFileTypeCode()), false),
	;

	private final String classifier;
	private final AInArtifactOutputPackaging packaging;
	private final boolean standardOutput;
	private final String outputTypeUid;

	AInArtifactBuiltinOutputType(final String aOutputTypeUid, final boolean aStandardOutput) {
		standardOutput = aStandardOutput;
		AIiArtifactOutputTypeUidPartsRecord locParsedInput = AIsComponentUtils.parseUid(OUTPUT_TYPE, aOutputTypeUid);
		if (locParsedInput.originClass() != BUILTIN)
			throw new IllegalArgumentException("Unsupported originClass: '" + locParsedInput.originClass() + "'");
		classifier = locParsedInput.classifier();
		packaging = getByCodeOrThrow(locParsedInput.packagingFileTypeCode());
		if (packaging == null)
			throw new IllegalArgumentException("Unsupported packaging: '" + locParsedInput.packagingFileTypeCode() + "'");
		outputTypeUid = aOutputTypeUid;
	}

	/**
	 * Returns the output type Uid.
	 * @return the output type Uid
	 */
	public String getOutputTypeUid() {
		return outputTypeUid;
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

	@Override
	public String classifier() {
		return classifier;
	}

	/**
	 * @return the packaging
	 */
	public AInArtifactOutputPackaging getPackaging() {
		return packaging;
	}

	@Override
	public String packagingFileTypeCode() {
		return packaging.getPackagingFileTypeCode();
	}

	/**
	 * @return the standardOutput
	 */
	public boolean isStandardOutput() {
		return standardOutput;
	}

	/**
	 * Gets the {@link AInArtifactBuiltinOutputType} by its properties.
	 * @param aOriginClass the origin class
	 * @param aNamespace the namespace
	 * @param aClassifier the classifier
	 * @param aPackagingFileTypeCode the packagingFileTypeCode
	 * @return the found output type or throws an exception if not found
	 * @throws IllegalArgumentException if origin class is not {@link AInComponentOriginClass#BUILTIN} or {@link #getByUidOrThrow(String)}
	 *    throws an exception
	 */
	public static AIiArtifactOutputTypeUidPartsRecord getByPropsOrThrow(
			final AInComponentOriginClass aOriginClass,
			final String aNamespace,
			final String aClassifier,
			final String aPackagingFileTypeCode) throws IllegalArgumentException {
		if (aOriginClass != BUILTIN)
			throw new IllegalArgumentException("Unsupported originClass: '" + aOriginClass + "' for parameters namespace='"
					+ aNamespace + "', classifier='" + aClassifier + "', packagingFileTypeCode'" + aPackagingFileTypeCode + "'");
		return getByUidOrThrow(createBuiltinOutputTypeUid(aClassifier, aPackagingFileTypeCode));
	}

	/**
	 * Gets the {@link AInArtifactBuiltinOutputType} by its output type Uid.
	 * @param aOutputTypeUid the output type Uid
	 * @return the found output type or throws an exception if not found
	 * @throws IllegalArgumentException if output type Uid is invalid - not found between the enum values
	 */
	public static AIiArtifactOutputTypeUidPartsRecord getByUidOrThrow(final String aOutputTypeUid) throws IllegalArgumentException {
		return Stream.of(values()).filter(
						aAInArtifactBuiltinOutputType -> Objects.equals(aOutputTypeUid, aAInArtifactBuiltinOutputType.outputTypeUid))
				.findAny().orElseThrow(() -> new IllegalArgumentException("Illegal outputTypeUid: '" + aOutputTypeUid + "'"));
	}

	/**
	 * Finds the given builtin by the builtin-Uid
	 * @param aUid Uid for which the builtIn item has to be searched for
	 * @return the found builtIn item or empty optional, if no builtIn item was found
	 */
	public Optional<AInArtifactBuiltinOutputType> findByUid(String aUid) {
		if (aUid == null || aUid.isBlank()) return Optional.empty();
		return Stream.of(values()).filter(locOutputType -> locOutputType.getOutputTypeUid().equals(aUid)).findFirst();
	}

	@Override
	public AInComponentOriginClass originClass() {
		return AInComponentOriginClass.BUILTIN;
	}

	@Override
	public String namespace() {
		return "";
	}

}
