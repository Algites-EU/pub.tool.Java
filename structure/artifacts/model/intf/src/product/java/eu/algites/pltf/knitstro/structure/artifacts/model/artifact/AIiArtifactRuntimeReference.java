package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading.AIiRuntimeReference;

/**
 * <p>
 * Title: {@link AIiArtifactRuntimeReference}
 * </p>
 * <p>
 * Description: Artifact runtime reference defines the reference
 *    to the artifact, which might change its type during the loading process.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 10.01.26 11:30
 */
public interface AIiArtifactRuntimeReference<A extends AIiAbstractArtifact>
		extends AIiArtifactCoordinate, AIiRuntimeReference<AIiArtifactCoordinate, A> {

}
