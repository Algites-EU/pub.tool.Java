package eu.algites.tool.devops.build.model.artifact;

import static eu.algites.tool.devops.build.model.artifact.AInArtifactClass.CONTROLLED;
import static eu.algites.tool.devops.build.model.artifact.AInArtifactClass.UNDEFINED;

import eu.algites.tool.devops.build.model.common.AIcArtifactOutputLocalKey;
import eu.algites.tool.devops.build.model.common.AIiArtifactOutputType;

import java.util.Optional;
import java.util.Set;

/**
 * <p>
 * Title: {@link AIiArtifactOutput}
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
public interface AIiArtifactOutput<A extends AIiAbstractArtifact>
		extends AIiArtifactLink<A> {
	/**
	 * Gets the local identification of the artifact output within the artifact.
	 * @return the local identification of the artifact output
	 */
	AIiArtifactOutputType getOutputLocalKey();

	/**
	 * Checks if the artifact output is consistent with the artifact definition.
	 * @return true if consistent, false otherwise
	 */
	default boolean isConsistentWithArtifactDefinition() {
		Optional<A> locDefined = getLinkedArtifact().findValue();
		if (locDefined.isEmpty()) return false;
		A locArtifact = locDefined.get();
		if (locArtifact instanceof AIiAbstractSimpleMultiOutputArtifact) return
				((AIiAbstractSimpleMultiOutputArtifact)locArtifact)
						.getDefinedOutputs().contains(getOutputLocalKey());
		if (locArtifact instanceof AIiAbstractVersionedMultiOutputArtifact) return
				((AIiAbstractVersionedMultiOutputArtifact)locArtifact)
						.getVersionedOutputs().entrySet().stream()
						.anyMatch(locEntry -> locEntry.getValue() != null && locEntry.getValue().contains(getOutputLocalKey()));
		throw new IllegalStateException("\\\\ Development error: Unsupported artifact class: " + locArtifact.getClass());
	}
}
