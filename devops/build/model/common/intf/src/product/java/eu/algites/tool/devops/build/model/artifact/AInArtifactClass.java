package eu.algites.tool.devops.build.model.artifact;

/**
 * <p>
 * Title: {@link AInArtifactClass}
 * </p>
 * <p>
 * Description: Defines the class of the artifact,
 *   used for the simplification during the loading of the artifacts.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 11.01.26 7:47
 */
public enum AInArtifactClass {
	UNDEFINED(true),
	CONTROLLED(false),
	UNCONTROLLED(false);

	private final boolean loadingClassOnly;

	AInArtifactClass(final boolean aLoadingClassOnly) {
		loadingClassOnly = aLoadingClassOnly;
	}

	/**
	 * @return the loadingClassOnly
	 */
	public boolean isLoadingClassOnly() {
		return loadingClassOnly;
	}
}
