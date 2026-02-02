package eu.algites.pltf.knitstro.structure.artifacts.model.common.version;

/**
 * <p>
 * Title: {@link AIiVersionContext}
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
 * @date 08.01.26 0:06
 */
public interface AIiVersionContext {

	/**
	 * Gets the identification of the version context,
	 * which is used during the releases - ther elease is bound to this
	 * Id, so it means the Id string is NOT changed with the releases,
	 * it MUST be version number independent
	 *
	 * @return the unique and time-stable identification of the version context
	 */
	String getVersionContextId();

	/**
	 * Gets the basic release line of the version context. Can be null if no release context is specified
	 *
	 * @return the basic release line of the version context
	 */
	AIiVersionReleaseLine getReleaseLine();

	/**
	 * Gets the revision of the version context.
	 *
	 * @return the revision of the version context
	 */
	AIiVersionReleaseLineRevision getRevision();

	/**
	 * Gets the qualifier of the version context. Must be always specified.
	 *
	 * @return the qualifier of the version context
	 */
	AIiVersionQualifier getQualifier();
}
