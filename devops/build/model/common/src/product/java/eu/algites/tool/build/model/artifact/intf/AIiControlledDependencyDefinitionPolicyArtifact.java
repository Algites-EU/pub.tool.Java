package eu.algites.tool.build.model.artifact.intf;

import static eu.algites.tool.build.model.artifact.intf.AInArtifactKind.DEPENDENCY_DEFINITION_POLICY;

/**
 * <p>
 * Title: {@link AIiControlledDependencyDefinitionPolicyArtifact}
 * </p>
 * <p>
 * Description: Basic interface for the Algites Policy Artifacts
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
public interface AIiControlledDependencyDefinitionPolicyArtifact extends AIiAbstractControlledPotentiallyArtificialArtifact,
		AIiAbstractControlledPolicyBackgroundDependenciesManagerArtifact,
		AIiAbstractControlledDirectDependenciesArtifact<AIiAbstractArtifact>,
		AIiAbstractControlledParentContainerArtifact<AIiAbstractArtifact> {

	/**
	 * Returns if the policy artifact does not exist in the reality like a defined artifact,
	 * but is only the artificial policy artifact constructed only to allow the core artifact
	 * to exist in single artifact mode
	 * @return true if the Artifact is  Artificial artifact and false if not.
	 */
	default boolean isArtificialArtifactForSingleCoreArtifact() {
		;return getArtifactConfigurationFile() == null;
	}

	/**
	 * Gets the kind of the artifact {@link AInArtifactKind#DEPENDENCY_DEFINITION_POLICY}
	 *
	 * @return the kind of the artifact
	 */
	@Override
	default AInArtifactKind getArtifactKind() {
		return DEPENDENCY_DEFINITION_POLICY;
	}
}
