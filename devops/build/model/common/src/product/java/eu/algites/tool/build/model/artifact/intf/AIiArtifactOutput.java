package eu.algites.tool.build.model.artifact.intf;

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
		extends AIiArtifactOutputLink<A> {
	/**
	 * Gets the local identification of the artifact output within the artifact.
	 * @return the local identification of the artifact output
	 */
	AIcArtifactOutputLocalKey getOutputLocalKey();

}
