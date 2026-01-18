package eu.algites.tool.devops.build.model.artifact;

import static eu.algites.tool.devops.build.model.utils.AIsArtifactModelUtils.UNSPECIFIED_VERSION_PLACEHOLDER;

import eu.algites.tool.devops.build.model.utils.AIsArtifactModelUtils;

/**
 * <p>
 * Title: {@link AIiUncontrolledArtifact}
 * </p>
 * <p>
 * Description: Basic interface for the Algites uncontrolled Artifacts
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
public interface AIiUncontrolledArtifact extends AIiAbstractVersionedMultiOutputArtifact, AIiAbstractDefinedArtifact {

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

	/**
	 * Gets the class of the artifact
	 * @return the class of the artifact
	 */
	default AInArtifactClass getArtifactClass() {
		return AInArtifactClass.UNCONTROLLED;
	}


}
