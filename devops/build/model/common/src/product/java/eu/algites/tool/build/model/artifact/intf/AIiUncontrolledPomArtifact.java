package eu.algites.tool.build.model.artifact.intf;

import static eu.algites.tool.build.model.artifact.intf.AInArtifactKind.UNCONTROLLED_POM;

/**
 * <p>
 * Title: {@link AIiUncontrolledPomArtifact}
 * </p>
 * <p>
 * Description: Basic interface for the Algites Unknown Artifacts
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
public interface AIiUncontrolledPomArtifact extends AIiAbstractUncontrolledArtifact {

	/**
	 * Gets the kind of the artifact {@link AInArtifactKind#UNCONTROLLED_POM}
	 *
	 * @return the kind of the artifact
	 */
	@Override
	default AInArtifactKind getArtifactKind() {
		return UNCONTROLLED_POM;
	}

}
