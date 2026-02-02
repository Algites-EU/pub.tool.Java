package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.AIiAbstractArtifactContainer;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.version.AIiVersionContext;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependency;

import java.nio.file.Path;
import java.util.List;

/**
 * <p>
 * Title: {@link AIiControlledArtifact}
 * </p>
 * <p>
 * Description: Basic interface for the Algites Known artifacts (wth known structure, dependencies, etc)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 07.01.26 14:07
 */
public interface AIiControlledArtifact<PARENT extends AIiAbstractArtifact, PARENTLINK extends AIiArtifactOutput<PARENT>>
		extends AIiAbstractDefinedArtifact, AIiAbstractArtifactContainer, AIiAbstractSimpleMultiOutputArtifact {

	/**
	 * Gets the link to parent of the artifact
	 *
	 * @return the parent of the artifact
	 */
	PARENTLINK getParent();

	/**
	 * Sets the link to parent of the artifact
	 * @param aParent the parent of the artifact
	 */
	void setParent(PARENTLINK aParent);

	/**
	 * Gets the class of the artifact
	 * @return the class of the artifact
	 */
	default AInArtifactClass getArtifactClass() {
		return AInArtifactClass.CONTROLLED;
	}

	String ARTIFACT_CONFIG_FILE_NAME_WITHOUT_EXT = "algites-artifact";

	/**
	 * Gets the version context of the known artifact
	 *
	 * @return the version context of the known artifact
	 */
	AIiVersionContext getVersionContext();

	/**
	 * Gets the path to the artifact configuration file.
	 * @return the path to the artifact configuration file.
	 */
	Path getArtifactConfigurationFile();

	/**
	 * gets the dependencies of the artifact. Dependencies mean the dírectly transitive dependencies applied to the artifact or its children.
	 *
	 * @return the dependencies of the artifact
	 */
	List<AIiArtifactDependency<? extends AIiAbstractArtifact>> getDependencies();

	@Override
	default boolean isSourceRepository() {
		return false;
	}

	@Override
	default boolean isControlledArtifact() {
		return true;
	}

}
