package eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype;

import static eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin.BUILTIN;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.createBuiltinUid;
import static eu.algites.lib.common.enums.uiddata.AIsUidEnumDataUtils.parseUid;
import static eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AInArtifactOutputPackaging.JAR;
import static eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AInArtifactOutputPackaging.POM;
import static eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AInArtifactOutputPackaging.getByCodeOrThrow;
import static eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType.OUTPUT_TYPE;

import eu.algites.lib.common.enums.uiddata.AIcUidEnumDataRegistry;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AInArtifactOutputPackaging;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * Title: {@link AInArtifactBuiltinOutputType}
 * </p>
 * <p>
 * Description: Defines the built-in output types,
 * which can occur if the output type is of the class {@link AInUidEnumDataOrigin#BUILTIN} and
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
public enum AInArtifactBuiltinOutputType implements
		AIiArtifactOutputTypeDataUidRecord, AIiArtifactOutputTypeData {

	MAIN_MAVEN_POM(createBuiltinUid(List.of("", POM.getPackagingFileTypeCode()),
			AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA),true),
	MAIN_JAVA_CLASSES_JAR(createBuiltinUid(List.of("", JAR.getPackagingFileTypeCode()),
			AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA),true),
	MAIN_JAVA_SOURCE_JAR(createBuiltinUid(List.of("sources", JAR.getPackagingFileTypeCode()),
			AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA), true),
	MAIN_JAVA_DOC_JAR(createBuiltinUid(List.of("javadoc", JAR.getPackagingFileTypeCode()),
			AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA), true),
	TEST_JAVA_CLASSES_JAR(createBuiltinUid(List.of("tests", JAR.getPackagingFileTypeCode()),
			AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA), true),
	TEST_JAVA_SOURCE_JAR(createBuiltinUid(List.of("test-sources", JAR.getPackagingFileTypeCode()),
			AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA), true),
	TEST_JAVA_DOC_JAR(createBuiltinUid(List.of("test-javadoc", JAR.getPackagingFileTypeCode()),
			AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA), true),
	POLICY_BACKGROUND_BOM(createBuiltinUid(List.of("pbbom", POM.getPackagingFileTypeCode()),
			AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA), false),
	;

	private final String classifier;
	private final AInArtifactOutputPackaging packaging;
	private final boolean standardOutput;
	private final String uid;

	AInArtifactBuiltinOutputType(final String aUid, final boolean aStandardOutput) {
		standardOutput = aStandardOutput;
		AIiArtifactOutputTypeDataUidRecord locParsedInput = (AIiArtifactOutputTypeDataUidRecord) parseUid(
				OUTPUT_TYPE.getDataType(), aUid);
		if (locParsedInput.origin() != BUILTIN)
			throw new IllegalArgumentException("Unsupported origin: '" + locParsedInput.origin() + "'");
		classifier = locParsedInput.classifier();
		packaging = getByCodeOrThrow(locParsedInput.packagingFileTypeCode());
		if (packaging == null)
			throw new IllegalArgumentException("Unsupported packaging: '" + locParsedInput.packagingFileTypeCode() + "'");
		uid = aUid;
		AIcUidEnumDataRegistry.getInstance().registerData(true, true, true, this);
	}

	@Override
	public AIiArtifactOutputTypeDataType getDataType() {
		return (AIiArtifactOutputTypeDataType) OUTPUT_TYPE.getDataType();
	}

	/**
	 * Returns the output type Uid.
	 * @return the output type Uid
	 */
	@Override
	public String uid() {
		return uid;
	}

	@Override
	public AIiArtifactOutputTypeDataUidRecord getDataRecord() {
		return this;
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
	 * @param aOrigin the origin
	 * @param aNamespace the namespace
	 * @param aClassifier the classifier
	 * @param aPackagingFileTypeCode the packagingFileTypeCode
	 * @return the found output type or throws an exception if not found
	 * @throws IllegalArgumentException if origin class is not {@link AInUidEnumDataOrigin#BUILTIN} or {@link #getByUidOrThrow(String)}
	 *    throws an exception
	 */
	public static AIiArtifactOutputTypeDataUidRecord getByPropsOrThrow(
			final AInUidEnumDataOrigin aOrigin,
			final String aNamespace,
			final String aClassifier,
			final String aPackagingFileTypeCode) throws IllegalArgumentException {
		if (aOrigin != BUILTIN)
			throw new IllegalArgumentException("Unsupported origin: '" + aOrigin + "' for parameters namespace='"
					+ aNamespace + "', classifier='" + aClassifier + "', packagingFileTypeCode'" + aPackagingFileTypeCode + "'");
		return getByUidOrThrow(createBuiltinUid(List.of(aClassifier, aPackagingFileTypeCode), AIiArtifactOutputTypeDataUidRecord.RECORD_SPECIFIC_PARTS_METADATA));
	}

	/**
	 * Gets the {@link AInArtifactBuiltinOutputType} by its output type Uid.
	 * @param aOutputTypeUid the output type Uid
	 * @return the found output type or throws an exception if not found
	 * @throws IllegalArgumentException if output type Uid is invalid - not found between the enum values
	 */
	public static AIiArtifactOutputTypeDataUidRecord getByUidOrThrow(final String aOutputTypeUid) throws IllegalArgumentException {
		return Stream.of(values()).filter(
						aAInArtifactBuiltinOutputType -> Objects.equals(aOutputTypeUid, aAInArtifactBuiltinOutputType.uid))
				.findAny().orElseThrow(() -> new IllegalArgumentException("Illegal outputTypeUid: '" + aOutputTypeUid + "'"));
	}

	/**
	 * Finds the given builtin by the builtin-Uid
	 * @param aUid Uid for which the builtIn item has to be searched for
	 * @return the found builtIn item or empty optional, if no builtIn item was found
	 */
	public static Optional<AInArtifactBuiltinOutputType> findByUid(String aUid) {
		if (aUid == null || aUid.isBlank()) return Optional.empty();
		return Stream.of(values()).filter(locOutputType -> locOutputType.uid().equals(aUid)).findFirst();
	}

	@Override
	public AInUidEnumDataOrigin origin() {
		return BUILTIN;
	}

	@Override
	public String namespace() {
		return "";
	}

}
