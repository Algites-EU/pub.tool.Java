package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.localusage;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

/**
 * <p>
 * Title: {@link AIiLocalUsageModeData}
 * </p>
 * <p>
 * Description: Defines the local inclusion mode
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 18.01.26 15:28
 */
public interface AIiLocalUsageModeData extends
		AIiUidEnumData<
				AIiLocalUsageModeDataUidRecord, AInUidEnumDataOrigin,
				AIiLocalUsageModeDataType>,
		AIiLocalUsageModeDataUidRecord {

}
