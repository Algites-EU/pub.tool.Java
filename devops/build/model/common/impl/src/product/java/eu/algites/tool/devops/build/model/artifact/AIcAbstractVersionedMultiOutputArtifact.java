package eu.algites.tool.devops.build.model.artifact;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils;
import eu.algites.tool.devops.build.model.common.AIiArtifactOutputType;
import jakarta.annotation.Nonnull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Artifact with a single output type.
 * @author linhart1
 */
public abstract class AIcAbstractVersionedMultiOutputArtifact extends AIcAbstractArtifact implements
		AIiAbstractVersionedMultiOutputArtifact {

	private final Map<String, Set<AIiArtifactOutputType>> outputs = new LinkedHashMap<>();


	/**
	 * Constructor.
	 * @param aCoordinateId coordinate Id of the artifact
	 * @param aGroupId group Id of the artifact
	 * @param aArtifactId artifact Id of the artifact
	 * @param aOutputs output of the artifact
	 */
	public AIcAbstractVersionedMultiOutputArtifact(
			@Nonnull final String aCoordinateId,
			@Nonnull final String aGroupId,
			@Nonnull final String aArtifactId,
			@Nonnull final AIiArtifactOutputType aOutputs) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	/**
	 * Constructor.
	 * @param aGroupId group Id of the artifact
	 * @param aArtifactId artifact Id of the artifact
	 */
	public AIcAbstractVersionedMultiOutputArtifact(
			@Nonnull final String aGroupId,
			@Nonnull final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	/**
	 * Constructor.
	 * @param aCoordinateId coordinate Id of the artifact
	 */
	public AIcAbstractVersionedMultiOutputArtifact(
			@Nonnull final String aCoordinateId) {
		super(aCoordinateId);
	}

	protected @Nonnull String getStructureHumanReadableName() {
		return "Single Output Type Artifact " + getCoordinateId() + " of type " + getVersionedOutputs();
	}

	@Override
	public Map<String, Set<AIiArtifactOutputType>> getVersionedOutputs() {
		return outputs;
	}

	@Override
	public boolean equals(final Object aO) {
		if (aO == null || getClass() != aO.getClass())
			return false;
		if (!super.equals(aO))
			return false;

		AIcAbstractVersionedMultiOutputArtifact locthat = (AIcAbstractVersionedMultiOutputArtifact) aO;
		return Objects.equals(outputs, locthat.outputs);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(outputs);
		return result;
	}

	@Override
	public String toString() {
		if (AIsStringOutputUtils.isUsedStringOutputMode(
				AInStringOutputMode.USER))
			return "{" + super.toString() + ", " +
					"outputs=" + outputs +
					"} ";
		else
			return "AIcSingleOutputTypeArtifact{" + super.toString() + "\n" +
					"            outputs=" + outputs +
					"} ";
	}
}
