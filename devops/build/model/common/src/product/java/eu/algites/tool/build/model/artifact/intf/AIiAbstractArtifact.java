package eu.algites.tool.build.model.artifact.intf;

import java.util.Set;

/**
 * <p>
 * Title: {@link AIiAbstractArtifact}
 * </p>
 * <p>
 * Description: Basic interface for the Algites Artifacts
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
public interface AIiAbstractArtifact extends AIiArtifactCoordinate {

	/**
	 * Gets the class of the artifact
	 * @return the class of the artifact
	 */
	default AInArtifactClass getArtifactClass() {
		return getArtifactKind() == null
				? AInArtifactClass.UNDEFINED
				: getArtifactKind().getArtifactClass();
	}

	/**
	 * Gets the kind of the artifact
	 *
	 * @return the kind of the artifact.
	 */
	AInArtifactKind getArtifactKind();

	/**
	 * Gets the defined outputs of the artifact
	 * @return outputs, defined for the artifact.
	 */
	Set<AIcArtifactOutputLocalKey> getDefinedOutputs();
}
