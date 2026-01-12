package eu.algites.tool.build.model.artifact.intf;

/**
 * <p>
 * Title: {@link AIiArtifactOutputLink}
 * </p>
 * <p>
 * Description: Gets the abstraction of the link of the artifact, which is intended to be used as a target for dependency-like relations
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
public interface AIiArtifactOutputLink<A extends AIiAbstractArtifact> extends AIiArtifactLink<A> {

	/**
	 * Gets the runtime reference to the linked artifact output
	 *
	 * @return the runtime reference to linked artifact output
	 */
	AIiArtifactOutputRuntimeReference<A> getLinkedArtifactOutput();

}
