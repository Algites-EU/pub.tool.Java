package eu.algites.pltf.knitstro.structure.artifacts.model.utils;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataType;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

/**
 * <p>
 * Title: {@link AIiComponentDataType}
 * </p>
 * <p>
 * Description: Gets the component data type
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 25.01.26 18:52
 */
public interface AIiComponentDataType<R extends AIiUidEnumDataRecord> extends AIiUidEnumDataType<R, AInUidEnumDataOrigin> {
	/**
	 * @return the componentType
	 */
	AInComponentType getComponentType();
}
