package eu.algites.tool.build.model.common.intf;

import eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledArtifact;
import eu.algites.tool.build.model.repository.intf.AIiSourceRepository;

import java.util.Map;

/**
 * <p>
 * Title: {@link AIiAbstractArtifactContainer}
 * </p>
 * <p>
 * Description: Abstraction of the Artifact container used by the
 * Aggregator {@link AIiControlledAggregatorArtifact}
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
	 * Gets the contained artifacts. The key contains the path for the controlled artifact configuration file resolved from the relative
	 * artifact path to the artifact contained within the aggregator or repository which must be inserted by the user into the aggregator or
	 * repository configuration. The second key item is the artifact Id of the artifact. During the loading the loader goes through
	 * the given paths and loads the artifacts into the map for specified
	 * paths.
	 *
	 * @return the contained artifacts. As a key is path to the Artifact configuration file with the artifact name,
	 *    as the value is the corresponding artifact with
	 * 		the same {@link AIiAbstractControlledArtifact#getArtifactConfigurationFile()} value, like is the key of this artifact.
	 */
	Map<AIcContainedArtifactLocalKey, AIiAbstractControlledArtifact> getContainedArtifacts();
}
