package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIiUndefinedArtifact;

import java.util.Objects;

import jakarta.annotation.Nonnull;

public class AIcUndefinedArtifact extends AIcAbstractArtifact implements AIiUndefinedArtifact {

	private String artifactVersion;

	public AIcUndefinedArtifact(
			final String aCoordinateId,
			final String aGroupId,
			final String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcUndefinedArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcUndefinedArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	@Override
	public AInArtifactKind getArtifactKind() {
		/* Undefined kind has no kind selected: */
		return null;
	}

	protected @Nonnull String getStructureHumanReadableName() {
		return "Undefined Artifact";
	}

	@Override
	public String getArtifactVersion() {
		return artifactVersion;
	}

	public void setArtifactVersion(String artifactVersion) {
		this.artifactVersion = artifactVersion;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(artifactVersion);
		return result;
	}

	@Override
	public boolean equals(final Object aO) {
		if (!(aO instanceof AIcUndefinedArtifact locthat))
			return false;
		if (!super.equals(aO))
			return false;

		return Objects.equals(artifactVersion, locthat.artifactVersion);
	}

	@Override
	public String toString() {
		return "AIcUncontrolledArtifact{" + super.toString() + "\n" +
				"artifactVersion=" + artifactVersion +
				"} ";
	}
}
