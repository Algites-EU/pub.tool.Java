package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import java.util.Objects;

/**
 * <p>
 * Title: {@link AIiUncontrolledArtifactVersionedOutput}
 * </p>
 * <p>
 * Description: Gets the definition of the artifact projection, defining which projection should be used in the used context.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 07.01.26 15:53
 */
public interface AIiUncontrolledArtifactVersionedOutput<A extends AIiAbstractArtifact>
		extends AIiArtifactOutput<A> {
	/**
	 * Gets the version of the linked artefact output which has to be considered in the dependency mechanisms
	 * @return the version of the linked artifact output
	 */
	String getArtifactOutputVersion();

	/**
	 * Checks if the artifact output is consistent with the artifact definition.
	 * @return true if consistent, false otherwise
	 */
	@Override
	default boolean isConsistentWithArtifactDefinition() {
		if (!AIiArtifactOutput.super.isConsistentWithArtifactDefinition())
			return false;
		A locArtifact = getLinkedArtifact().getValue();
		if (locArtifact instanceof AIiAbstractVersionedMultiOutputArtifact)
			   return
				((AIiAbstractVersionedMultiOutputArtifact)locArtifact)
						.getVersionedOutputs().entrySet().stream()
						.filter(locEntry -> Objects.equals(locEntry.getKey(), getArtifactOutputVersion()))
						.anyMatch(locEntry -> locEntry.getValue() != null && locEntry.getValue().contains(getOutputLocalKey()));
		else
			return true;
	}

}
