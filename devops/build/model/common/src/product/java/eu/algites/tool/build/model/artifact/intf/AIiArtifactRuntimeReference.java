package eu.algites.tool.build.model.artifact.intf;

/**
 * <p>
 * Title: {@link AIiArtifactRuntimeReference}
 * </p>
 * <p>
 * Description: TODO: Add description
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
public interface AIiArtifactRuntimeReference<A extends AIiAbstractArtifact> extends AIiArtifactCoordinate {

	/**
	 * Finds the referenced artifact in the underlying storage
	 * @return the referenced artifact loaded for the Id given by {@link #getCoordinateId()}
	 *    from the global artifact memory storage
	 */
	A findReferencedArtifact();

}
