package eu.algites.tool.devops.build.model.loader;

import eu.algites.tool.devops.build.model.common.version.AIiVersionContext;

/**
 * <p>
 * Title: {@link AIcArtifactContainerLoadingDataContext}
 * </p>
 * <p>
 * Description: Context for the loading of the data of the artifact
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 17.01.26 17:38
 */
public class AIcArtifactContainerLoadingDataContext {

	private final AIcArtifactContainerLoadingDataContext parentContext;
	private AIiVersionContext versionContext;

	public AIcArtifactContainerLoadingDataContext(final AIcArtifactContainerLoadingDataContext aParentContext) {
		parentContext = aParentContext;
	}

	/**
	 * Returns the parent context of this context.
	 * @return parent context of this context
	 */
	public AIcArtifactContainerLoadingDataContext getParentContext() {
		return parentContext;
	}

	/**
	 * Returns the version context of this context.
	 * @return version context of this context
	 */
	public AIiVersionContext getVersionContext() {
		return versionContext;
	}

	/**
	 * Sets the version context of this context.
	 * @param aVersionContext version context of this context
	 */
	public void setVersionContext(AIiVersionContext aVersionContext) {
		versionContext = aVersionContext;
	}

}
