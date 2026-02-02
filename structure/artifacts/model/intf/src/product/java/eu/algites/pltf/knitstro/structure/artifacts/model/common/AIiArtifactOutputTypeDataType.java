package eu.algites.pltf.knitstro.structure.artifacts.model.common;

import eu.algites.pltf.knitstro.structure.artifacts.model.utils.AIiComponentDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.utils.AInComponentType;

/**
 * <p>
 * Title: {@link AIiArtifactOutputTypeDataType}
 * </p>
 * <p>
 * Description: Defines the data type with its properties, which can be used
 *    by calling method {@link AInComponentType#getDataType()} of its component type
 *    returned by {@link #getComponentType()}.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 24.01.26 7:36
 */
public interface AIiArtifactOutputTypeDataType
		extends AIiComponentDataType<AIiArtifactOutputTypeDataUidRecord> {

}
