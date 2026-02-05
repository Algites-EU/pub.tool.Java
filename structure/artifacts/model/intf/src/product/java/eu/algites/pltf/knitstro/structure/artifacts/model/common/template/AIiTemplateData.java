package eu.algites.pltf.knitstro.structure.artifacts.model.common.template;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIiArtifactOutputType;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIiArtifactOutputTypeDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIiArtifactOutputTypeDataUidRecord;

/**
 * <p>
 * Title: {@link AIiTemplateData}
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
public interface AIiTemplateData
		extends AIiUidEnumData<AIiTemplateDataUidRecord, AInUidEnumDataOrigin, AIiTemplateDataType>,
		AIiArtifactOutputType {
}
