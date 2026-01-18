package eu.algites.tool.devops.build.model.loader.loading;

import eu.algites.tool.devops.build.model.artifact.AIiAbstractArtifact;
import eu.algites.tool.devops.build.model.common.template.AIcTemplateLateInitializationValidator;
import eu.algites.tool.devops.build.model.loader.AIcArtifactModelLoadContainer;

/**
 * <p>
 * Title: {@link AIcArtifactLoadContainerLateInitializationMap}
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
 * @date 16.01.26 8:08
 */
public class AIcArtifactLoadContainerLateInitializationMap<A extends AIiAbstractArtifact> extends AIcLateInitializationMap<String, AIcArtifactModelLoadContainer<A>> {

	/**
	 * Default constructor
	 */
	public AIcArtifactLoadContainerLateInitializationMap() {
		super(new AIcTemplateLateInitializationValidator<>());
	}

	@Override
	public String computeKeyFor(final AIcArtifactModelLoadContainer<A> aValue) {
		if (aValue == null) return null;
		return aValue.getCoordinateId();
	}

}
