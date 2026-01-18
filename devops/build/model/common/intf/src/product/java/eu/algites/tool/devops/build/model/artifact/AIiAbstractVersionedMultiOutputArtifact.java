package eu.algites.tool.devops.build.model.artifact;

import eu.algites.tool.devops.build.model.common.AIiArtifactOutputType;
import eu.algites.tool.devops.build.model.utils.AIsArtifactModelUtils;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Title: {@link AIiAbstractVersionedMultiOutputArtifact}
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
public interface AIiAbstractVersionedMultiOutputArtifact extends AIiAbstractArtifact {

	/**
	 * Gets the outputs of the artifact according to specified versions as keys.
	 * For the unspecified versions the key {@link AIsArtifactModelUtils#UNSPECIFIED_VERSION_PLACEHOLDER}
	 * is used.
	 *
	 * @return outputa, defined for the artifact usage according to the given version.
	 */
	Map<String, Set<AIiArtifactOutputType>> getVersionedOutputs();


}
