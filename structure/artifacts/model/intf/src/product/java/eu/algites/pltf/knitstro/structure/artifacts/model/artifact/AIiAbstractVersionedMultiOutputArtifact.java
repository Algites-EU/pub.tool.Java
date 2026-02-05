package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIiArtifactOutputType;
import eu.algites.pltf.knitstro.structure.artifacts.model.utils.AIsModelUtils;

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
	 * For the unspecified versions the key {@link AIsModelUtils#UNSPECIFIED_VERSION_PLACEHOLDER}
	 * is used.
	 *
	 * @return outputa, defined for the artifact purpose according to the given version.
	 */
	Map<String, Set<AIiArtifactOutputType>> getVersionedOutputs();


}
