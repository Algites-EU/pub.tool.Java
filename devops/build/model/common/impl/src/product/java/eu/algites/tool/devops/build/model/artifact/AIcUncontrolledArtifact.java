package eu.algites.tool.devops.build.model.artifact;

import java.util.Objects;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils;
import eu.algites.tool.devops.build.model.common.AIiArtifactOutputType;
import jakarta.annotation.Nonnull;

/**
 * Uncontrolled Artifact implementation.
 * @author linhart1
 */
public class AIcUncontrolledArtifact extends AIcAbstractVersionedMultiOutputArtifact implements AIiUncontrolledArtifact {

	private String artifactVersion;

	/**
	 * Constructor.
	 * @param aCoordinateId coordinate Id of the artifact
	 * @param aGroupId group Id of the artifact
	 * @param aArtifactId artifact Id of the artifact
	 * @param aOutput output of the artifact
	 * @param aArtifactVersion version of the uncontrolled artifact
	 */
	public AIcUncontrolledArtifact(
			final String aCoordinateId,
			final String aGroupId,
			final String aArtifactId,
			@Nonnull final AIiArtifactOutputType aOutput,
			@Nonnull final String aArtifactVersion) {
		super(aCoordinateId, aGroupId, aArtifactId, aOutput);
		artifactVersion = aArtifactVersion;
	}

	public AIcUncontrolledArtifact(
			final String aGroupId,
			final String aArtifactId,
			@Nonnull final AIiArtifactOutputType aOutput,
			@Nonnull final String aArtifactVersion) {
		super(aGroupId, aArtifactId, aOutput);
		artifactVersion = aArtifactVersion;
	}

	public AIcUncontrolledArtifact(final String aCoordinateId,
			@Nonnull final AIiArtifactOutputType aOutput,
			@Nonnull final String aArtifactVersion) {
		super(aCoordinateId, aOutput);
		artifactVersion = aArtifactVersion;
	}

	protected @Nonnull String getStructureHumanReadableName() {
		return "Uncontrolled Artifact";
	}

	@Override
	public String getArtifactVersion() {
		return artifactVersion;
	}

	public void setArtifactVersion(String artifactVersion) {
		this.artifactVersion = artifactVersion;
	}

	@Override
	public boolean equals(final Object aO) {
		if (aO == null || getClass() != aO.getClass())
			return false;
		if (!super.equals(aO))
			return false;

		AIcUncontrolledArtifact locthat = (AIcUncontrolledArtifact) aO;
		return Objects.equals(artifactVersion, locthat.artifactVersion);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(artifactVersion);
		return result;
	}

	@Override
	public String toString() {
		if (AIsStringOutputUtils.isUsedStringOutputMode(
				AInStringOutputMode.USER))
			return "{" + super.toString() + ", " +
					"artifactVersion='" + artifactVersion + '\'' +
					"} ";
		else
			return "AIcUncontrolledArtifact{" + super.toString() + "\n" +
					"            artifactVersion='" + artifactVersion + '\'' +
					"} ";
	}
}
