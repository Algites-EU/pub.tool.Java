package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

import java.util.Map;

/**
 * <p>
 * Title: {@link AIiPurposeGroupData}
 * </p>
 * <p>
 * Description: Defines the purpose group
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
public interface AIiPurposeGroupData extends
		AIiUidEnumData<
				AIiPurposeGroupDataUidRecord, AInUidEnumDataOrigin,
				AIiPurposeGroupDataType>,
		AIiPurposeGroupDataUidRecord {

	/**
	 * Gets the purposes included in the group of the purposes
	 * defined by this object
	 * @return the purposes included in the group. The key is the uid of the dependency purpose
	 */
	Map<String, AIiPurposeData> purposes();

}
