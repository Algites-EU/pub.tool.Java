package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

/**
 * <p>
 * Title: {@link AIiPurposeData}
 * </p>
 * <p>
 * Description: Definition of the dependency purpose data
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
public interface AIiPurposeData
		extends AIiUidEnumData<AIiPurposeDataUidRecord,
			AInUidEnumDataOrigin, AIiPurposeDataType>,
		AIiPurposeDataUidRecord {
}
