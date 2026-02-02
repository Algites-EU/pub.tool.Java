package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils;
import eu.algites.tool.devops.build.model.utils.AIsArtifactModelUtils;
import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * <p>
 * Title: {@link AIcArtifactCoordinate}
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 10.01.26 11:48
 */
public class AIcArtifactCoordinate implements AIiArtifactCoordinate {
	private final String coordinateId;
	private final String groupId;
	private final String artifactId;

	/**
	 * Constructor.
	 * @param aCoordinateId coordinate Id of the artifact
	 */
	public AIcArtifactCoordinate(@Nonnull final String aCoordinateId) {
		this(aCoordinateId, null, null);
	}

	/**
	 * Constructor.
	 * @param aGroupId group Id of the artifact
	 * @param aArtifactId artifact Id of the artifact
	 */
	public AIcArtifactCoordinate(
			@Nonnull final String aGroupId,
			@Nonnull final String aArtifactId) {
		this(null, aGroupId, aArtifactId);
	}

	/**
	 * Constructor.
	 * @param aCoordinateId coordinate Id of the artifact
	 * @param aGroupId group Id of the artifact
	 * @param aArtifactId artifact Id of the artifact
	 */
	public AIcArtifactCoordinate(
			final String aCoordinateId, final String aGroupId, final String aArtifactId) {
		if ((aCoordinateId == null || aCoordinateId.isBlank())
				&& ((aGroupId == null || aGroupId.isBlank())
				    || (aArtifactId == null || aArtifactId.isBlank())))
			throw new IllegalArgumentException("\\\\ Development error: Invalid arguments passed to Artifact coordinates argument(s) passed to constructor.");
		coordinateId = aCoordinateId == null ? null : aCoordinateId.trim();
		groupId = aGroupId == null ? null : aGroupId.trim();
		artifactId = aArtifactId == null ? null : aArtifactId.trim();
		AIsArtifactModelUtils.validateCoordinateConsistency(getGroupId(), getArtifactId(), getCoordinateId(),
				getStructureHumanReadableName());
	}

	protected @Nonnull String getStructureHumanReadableName() {
		return "Artifact coordinate";
	}

	public String getGroupId() {
		return groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * @return the coordinatedId
	 */
	public String getCoordinateId() {
		return coordinateId;
	}

	@Override
	public boolean equals(final Object aO) {
		if (aO == null || getClass() != aO.getClass())
			return false;

		AIcArtifactCoordinate locthat = (AIcArtifactCoordinate) aO;
		return Objects.equals(coordinateId, locthat.coordinateId) && Objects.equals(groupId, locthat.groupId)
				&& Objects.equals(artifactId, locthat.artifactId);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(coordinateId);
		result = 31 * result + Objects.hashCode(groupId);
		result = 31 * result + Objects.hashCode(artifactId);
		return result;
	}

	@Override
	public String toString() {
		if (AIsStringOutputUtils.isUsedStringOutputMode(
				AInStringOutputMode.USER))
			return "{" +
					"coordinateId='" + coordinateId + '\'' +
					", groupId='" + groupId + '\'' +
					", artifactId='" + artifactId + '\'' +
					'}';
		else
			return "AIcArtifactCoordinate{" +
					"            coordinateId='" + coordinateId + '\'' +
					",             groupId='" + groupId + '\'' +
					",             artifactId='" + artifactId + '\'' +
					'}';
	}
}
