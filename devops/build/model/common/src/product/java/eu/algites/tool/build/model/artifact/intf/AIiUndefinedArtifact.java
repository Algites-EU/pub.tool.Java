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
	 * Gets the class of the artifact
	 * @return the class of the artifact
	 */
	default AInArtifactClass getArtifactClass() {
		return AInArtifactClass.UNDEFINED;
	}


}
