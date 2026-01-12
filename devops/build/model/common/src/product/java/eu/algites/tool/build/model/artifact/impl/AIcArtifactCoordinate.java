package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIiArtifactCoordinate;
import eu.algites.tool.build.model.utils.AIsArtifactModelUtils;
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
	private String coordinateId;
	private String groupId;
	private String artifactId;

	public AIcArtifactCoordinate(final String aArtifactCoordinateId) {
		this(aArtifactCoordinateId, null, null);
	}

	public AIcArtifactCoordinate(final String aGroupId, final String aArtifactId) {
		groupId = aGroupId;
		artifactId = aArtifactId;
	}

	public AIcArtifactCoordinate(final String aCoordinateId, final String aGroupId, final String aArtifactId) {
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
		if (!(aO instanceof AIcArtifactCoordinate locthat))
			return false;

		return Objects.equals(coordinateId, locthat.coordinateId) && Objects.equals(
				groupId,
				locthat.groupId) && Objects.equals(artifactId, locthat.artifactId);
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
		return "AIcArtifactCoordinate{" +
				"coordinatedId='" + coordinateId + '\'' +
				", groupId='" + groupId + '\'' +
				", artifactId='" + artifactId + '\'' +
				'}';
	}
}
