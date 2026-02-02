package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

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
	AInArtifactClass getArtifactClass();

}
