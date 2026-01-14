package eu.algites.tool.build.model.artifact.intf;

import static eu.algites.lib.common.object.stringoutput.AInStringOutputMode.USER;
import static eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils.isUsedStringOutputMode;

import java.util.Objects;

import jakarta.annotation.Nonnull;

/**
 * Projection that also points to the projected artifact (useful for exclusions).
 *
 * @author linhart1
 */
public final class AIcArtifactOutputLocalKey {

	private String outputClassifier;
	private String outputPackagingId;

	/**
	 * Exclusive constructor
	 * @param aOutputClassifier classifier for the output. The default classifier is null for the artifact of the builtin class
	 *    {@link AInArtifactBuiltinOutputType#DEFAULT_MAIN}
	 * @param aOutputPackagingId the packaging of the output
	 */
	public AIcArtifactOutputLocalKey(final String aOutputClassifier, final @Nonnull String aOutputPackagingId) {
		this.outputClassifier = aOutputClassifier;
		this.outputPackagingId = aOutputPackagingId;
	}

	/**
	 * Gets the classifier of the output
	 *
	 * @return the classifier of the output. If returns the null value, then this is the default output of the artifact
	 */
	public String getOutputClassifier() {
		return outputClassifier;
	}

	public void setOutputClassifier(String aOutputClassifier) {
		this.outputClassifier = aOutputClassifier;
	}

	/**
	 * Gets the identification of the type of the output (jar/pom, etc.) In the case of the internal artifacts or just the known types like
	 * represented by {@link AInArtifactOutputPackaging#getKnownOutputTypeClasses()} it should return the same code like has
	 * {@link AInArtifactOutputPackaging#getAssignedOutputPackagingId()}
	 *
	 * @return the id of the type of the output.
	 */
	public String getOutputPackagingId() {
		return outputPackagingId;
	}

	public void setOutputPackagingId(String aOutputPackagingId) {
		this.outputPackagingId = aOutputPackagingId;
	}

	@Override
	public boolean equals(final Object aO) {
		if (!(aO instanceof AIcArtifactOutputLocalKey locthat))
			return false;

		return Objects.equals(outputClassifier, locthat.outputClassifier) && Objects.equals(
				outputPackagingId,
				locthat.outputPackagingId);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(outputClassifier);
		result = 31 * result + Objects.hashCode(outputPackagingId);
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
				"{classifier='" + (outputClassifier == null ? "(main output)" : outputClassifier) + "', packaging='" + outputPackagingId + "'}";
	}

	public String toSystemString() {
		return "AIcArtifactOutputLocalIdentification{" +
				"outputClassifier='" + outputClassifier + '\'' +
				", outputPackagingId='" + outputPackagingId + '\'' +
				'}';
	}
}
