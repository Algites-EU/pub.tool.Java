package eu.algites.pltf.knitstro.structure.artifacts.model.dependency;

import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIiAbstractArtifact;
import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIiArtifactCoordinate;
import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIiArtifactOutput;

import java.util.List;

/**
 * <p>
 * Title: {@link AIiArtifactDependency}
 * </p>
 * <p>
 * Description: Gets the dependency on the artifact
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
public interface AIiArtifactDependency<A extends AIiAbstractArtifact, ALINK extends AIiArtifactOutput<A>> extends
		AIiArtifactDependencyScopeBindingsContainer {

	/**
	 * Gets the linked artifact output. The type of artifact linked to the output
	 *  can be controlled or uncontrolled or undefined.
	 * @return the linked artifact output. In the case the artifact is uncontrolled, this can be also the versioned output.
	 */
	ALINK getLinkedArtifactOutput();

}
