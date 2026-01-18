package eu.algites.tool.devops.build.model.common;

import static eu.algites.lib.common.object.stringoutput.AInStringOutputMode.USER;
import static eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils.isUsedStringOutputMode;
import static eu.algites.tool.devops.build.model.common.AInComponentOriginClass.BUILTIN;

import java.util.Objects;

import jakarta.annotation.Nonnull;

/**
 * Projection that also points to the projected artifact (useful for exclusions).
 *
 * @author linhart1
 */
public final class AIcArtifactOutputLocalKey implements AIiArtifactOutputType {

	private final String classifier;
	private final String packagingFileTypeCode;

	/**
	 * Creates a new key from the output type UID.
	 * @param aOutputTypeUid the UID of the output type
	 * @return the new key or existing builtin key
	 */
	public static AIiArtifactOutputType newKey(@Nonnull final String aOutputTypeUid) {
		AIiArtifactOutputTypeUidPartsRecord locRecord = AIsComponentUtils.parseUid(aOutputTypeUid);
		if (locRecord.originClass() == BUILTIN)
			return (AInArtifactBuiltinOutputType) locRecord;
		return new AIcArtifactOutputLocalKey(locRecord.classifier(), locRecord.packagingFileTypeCode());
	}

	/**
	 * Exclusive constructor
	 * @param aClassifier classifier for the output. The default classifier is null for the artifact of the builtin types
	 *    {@link AInArtifactBuiltinOutputType#MAIN_MAVEN_POM} and {@link AInArtifactBuiltinOutputType#MAIN_JAVA_CLASSES_JAR}
	 * @param aPackagingFileTypeCode the packaging of the output
	 */
	private AIcArtifactOutputLocalKey(final String aClassifier, final @Nonnull String aPackagingFileTypeCode) {
		this.classifier = aClassifier;
		this.packagingFileTypeCode = aPackagingFileTypeCode;
	}

	/**
	 * Gets the classifier of the output
	 *
	 * @return the classifier of the output. If returns the null value, then this is the default output of the artifact
	 */
	@Override
	public String classifier() {
		return classifier;
	}

	/**
	 * Gets the identification of the type of the output (jar/pom, etc.)
	 *
	 * @return the id of the type of the output.
	 */
	@Override
	public String packagingFileTypeCode() {
		return packagingFileTypeCode;
	}

	@Override
	public boolean equals(final Object aO) {
		if (!(aO instanceof AIcArtifactOutputLocalKey aThat))
			return false;

		return Objects.equals(classifier, aThat.classifier) && Objects.equals(
				packagingFileTypeCode,
				aThat.packagingFileTypeCode);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(classifier);
		result = 31 * result + Objects.hashCode(packagingFileTypeCode);
		return result;
	}

	@Override
	public String toString() {
		if (isUsedStringOutputMode(USER))
			return toUserString();
		return toSystemString();
	}

	public String toUserString() {
		return
				"{classifier='" + (classifier == null ? "(main output)" : classifier) + "', packaging='" + packagingFileTypeCode + "'}";
	}

	public String toSystemString() {
		return "AIcArtifactOutputLocalIdentification{" +
				"outputClassifier='" + classifier + '\'' +
				", outputPackagingId='" + packagingFileTypeCode + '\'' +
				'}';
	}
}
