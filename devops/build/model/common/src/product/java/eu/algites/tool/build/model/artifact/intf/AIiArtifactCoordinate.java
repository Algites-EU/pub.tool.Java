package eu.algites.tool.build.model.artifact.intf;

import eu.algites.tool.build.model.utils.AIsArtifactModelUtils;

/**
 * <p>
 * Title: {@link AIiArtifactCoordinate}
 * </p>
 * <p>
 * Description: BASIC interface for the artifact coordinate
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 10.01.26 11:38
 */
public interface AIiArtifactCoordinate {
	/**
	 * Gets the coordinated Identification if the artifact. This is the combination of the group Id and the artifact Id base, obtained by the
	 * call of {@link AIsArtifactModelUtils#toCoordinateId(String, String)}
	 *
	 * @return the coordinated Identification
	 */
	String getCoordinateId();

	/**
	 * Gets the group Id of the artifact
	 *
	 * @return the group Id of the artifact
	 */
	String getGroupId();

	/**
	 * Gets the artifact Id base of the artifact. The Id Base denotes the Artifact Id and in the case some sub-artifacts for the given
	 * artifact are generated, to this string can be appended some extra extension, like "-pom"
	 *
	 * @return the artifact Id base of the artifact
	 */
	String getArtifactId();

}
