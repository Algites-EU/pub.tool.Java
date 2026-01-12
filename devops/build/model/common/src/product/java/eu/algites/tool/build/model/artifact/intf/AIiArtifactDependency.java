package eu.algites.tool.build.model.artifact.intf;

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
public interface AIiArtifactDependency<A extends AIiAbstractArtifact> {

	/**
	 * Gets the linked artifact output. The type of the output
	 * is given by its artifact and can be controlled or uncontrolled or undefined.
	 * @return the linked artifact output
	 */
	AIiArtifactOutput<A> getLinkedArtifactOutput();

	/**
	 * Gets the dependency exclusions
	 *
	 * @return the dependency exclusions
	 */
	List<AIiArtifactOutput<? extends AIiAbstractArtifact>> getDependencyExclusions();

	/**
	 * Gets the dependency scope
	 *
	 * @return the dependency scope
	 */
	AIiArtifactDependencyScope getDependencyScope();

}
