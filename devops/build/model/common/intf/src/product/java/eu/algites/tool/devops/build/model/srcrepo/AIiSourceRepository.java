package eu.algites.tool.devops.build.model.srcrepo;

import eu.algites.tool.devops.build.model.common.AIiAbstractArtifactContainer;
import eu.algites.tool.devops.build.model.common.version.AIiVersionContext;

import java.nio.file.Path;

/**
 * <p>
 * Title: {@link AIiSourceRepository}
 * </p>
 * <p>
 * Description: Basic interface of the artifact source srcrepo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 09.01.26 11:31
 */
public interface AIiSourceRepository extends AIiAbstractArtifactContainer {

	String SOURCE_REPOSITORY_CONFIG_FILE_NAME_WITHOUT_EXT = "algites-source-repository";

	/**
	 * Gets the unique identifier of the srcrepo
	 * @return the unique identifier of the srcrepo
	 */
	String getId();

	/**
	 * Gets the name of the srcrepo
	 * @return the name of the srcrepo
	 */
	String getName();

	/**
	 * Gets the description of the srcrepo
	 * @return the description of the srcrepo
	 */
	String getDescription();

	/**
	 * Gets the version context of the srcrepo
	 *
	 * @return the version context of the srcrepo. It may be empty if no is defined, then this returns null
	 */
	AIiVersionContext getVersionContext();

	/**
	 * Gets the path to the srcrepo configuration file.
	 * @return the path to the srcrepo configuration file.
	 */
	Path getRepositoryConfigurationFile();

	@Override
	default boolean isSourceRepository() {
		return true;
	}

	@Override
	default boolean isControlledArtifact() {
		return false;
	}
}
