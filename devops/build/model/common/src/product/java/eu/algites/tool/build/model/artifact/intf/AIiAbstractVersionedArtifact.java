package eu.algites.tool.build.model.artifact.intf;

import static eu.algites.tool.build.model.utils.AIsArtifactModelUtils.UNSPECIFIED_VERSION_PLACEHOLDER;

import eu.algites.tool.build.model.utils.AIsArtifactModelUtils;

/**
 * <p>
 * Title: {@link AIiAbstractVersionedArtifact}
 * </p>
 * <p>
 * Description: Basic Marker interface for the uncontrolled or undefined version-based Artifacts
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 07.01.26 14:07
 */
public interface AIiAbstractVersionedArtifact extends AIiAbstractArtifact {

	/**
	 * Gets the version of the artifact
	 *
	 * @return the version of the artifact
	 */
	String getArtifactVersion();

	/**
	 * Gets the normalized version - if {@link #getArtifactVersion()} returns null or empty string,
	 * then returns the {@link AIsArtifactModelUtils#UNSPECIFIED_VERSION_PLACEHOLDER}
	 * otherwise the result of {@link #getArtifactVersion()}.
	 * @return normalized version
	 */
	default String getNormalizedArtifactVersion() {
		return getArtifactVersion() == null || getArtifactVersion().isBlank()
				? UNSPECIFIED_VERSION_PLACEHOLDER : getArtifactVersion();
	}
}
