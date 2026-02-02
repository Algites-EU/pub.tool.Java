package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

import java.util.Map;

/**
 * <p>
 * Title: {@link AIiSourceSetGroupData}
 * </p>
 * <p>
 * Description: Defines the sources set group
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
public interface AIiSourceSetGroupData extends
		AIiUidEnumData<
				AIiSourceSetGroupDataUidRecord, AInUidEnumDataOrigin,
				AIiSourceSetGroupDataType>,
		AIiSourceSetGroupDataUidRecord {

	/**
	 * Gets the source sets included in the group of the source sets
	 * defined by this object
	 * @return the source sets. AS key is the Uid of the source set
	 */
	Map<String, AIiSourceSetData> sourceSets();

}
