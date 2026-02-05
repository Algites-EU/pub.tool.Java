package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIiArtifactOutputType;

import java.util.Set;

/**
 * <p>
 * Title: {@link AIiAbstractSimpleMultiOutputArtifact}
 * </p>
 * <p>
 * Description: Contains the definition of the artifacts having simple multi outputs of the artifact
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 18.01.26 5:52
 */
public interface AIiAbstractSimpleMultiOutputArtifact {

	/**
	 * Gets the defined outputs of the artifact
	 *
	 * @return outputs, defined for the artifact.
	 */
	Set<AIiArtifactOutputType> getDefinedOutputs();
}
