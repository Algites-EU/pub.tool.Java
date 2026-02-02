package eu.algites.pltf.knitstro.structure.artifacts.model.common;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

/**
 * <p>
 * Title: {@link AIiArtifactOutputTypeData}
 * </p>
 * <p>
 * Description: General type for the output type data
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 25.01.26 14:35
 */
public interface AIiArtifactOutputTypeData
		extends AIiUidEnumData<AIiArtifactOutputTypeDataUidRecord, AInUidEnumDataOrigin, AIiArtifactOutputTypeDataType>,
		AIiArtifactOutputType {
}
