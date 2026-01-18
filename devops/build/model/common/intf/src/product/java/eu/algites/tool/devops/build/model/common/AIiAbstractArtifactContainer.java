package eu.algites.tool.devops.build.model.common;

import eu.algites.tool.devops.build.model.artifact.AIiControlledArtifact;
import eu.algites.tool.devops.build.model.srcrepo.AIiSourceRepository;

import java.util.Map;

/**
 * <p>
 * Title: {@link AIiAbstractArtifactContainer}
 * </p>
 * <p>
 * Description: Abstraction of the Artifact container used by the
 * Aggregator {@link AIiControlledArtifact}
 * and Repository {@link AIiSourceRepository}
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 09.01.26 17:39
 */
public interface AIiAbstractArtifactContainer {

	/**
	 * Gets the name of the structure representing the loading container
	 * @return gets the Name representing the given container
	 */
	String getName();

	/**
	 * Gets the description of the structure representing the loading container
	 * @return gets the Description representing the given container
	 */
	String getDescription();

	/**
	 * Gets the contained artifacts. The key contains the path for the controlled artifact configuration file resolved from the relative
	 * artifact path to the artifact contained within the aggregator or srcrepo which must be inserted by the user into the aggregator or
	 * srcrepo configuration. The second key item is the artifact Id of the artifact. During the loading the loader goes through the given
	 * paths and loads the artifacts into the map for specified paths.
	 *
	 * @return the contained artifacts. As a key is path to the Artifact configuration file with the artifact name, as the value is the
	 * 		corresponding artifact with the same {@link AIiControlledArtifact#getArtifactConfigurationFile()} value, like is the key of this
	 * 		artifact.
	 */
	Map<AIcContainedArtifactLocalKey, AIiControlledArtifact> getContainedArtifacts();

	/**
	 * Indicator if the container is a source repository
	 * @return true if the container is a source repository, false otherwise
	 */
	boolean isSourceRepository();

	/**
	 * Indicator if the container is a controlled artifact
	 * @return true if the container is a controlled artifact, false otherwise
	 */
	boolean isControlledArtifact();
}
