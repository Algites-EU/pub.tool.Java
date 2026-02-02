package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

/**
 * <p>
 * Title: {@link AIiExportModeData}
 * </p>
 * <p>
 * Description: Defines the export mode
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
public interface AIiExportModeData extends
		AIiUidEnumData<
				AIiExportModeDataUidRecord, AInUidEnumDataOrigin,
				AIiExportModeDataType>,
		AIiExportModeDataUidRecord {

}
