package eu.algites.tool.build.model.artifact.intf;

/**
 * <p>
 * Title: {@link AIiUndefinedArtifact}
 * </p>
 * <p>
 * Description: Basic interface for the Algites Unknown Artifacts
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
public interface AIiUndefinedArtifact extends AIiAbstractVersionedArtifact {

	/**
	 * Gets the kind of the artifact of the class {@link AInArtifactClass#UNDEFINED}
	 * which is null
	 * @return null kind of the artefact (for type unknown yet)
	 */
	@Override
	default AInArtifactKind getArtifactKind() {
		return null;
	}

}
