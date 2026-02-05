package eu.algites.pltf.knitstro.structure.artifacts.model.common.template;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataType;
import eu.algites.lib.common.enums.uiddata.AIiUidEnumDataRecord;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;

import java.util.Map;

/**
 * <p>
 * Title: {@link AIiTemplateContainer}
 * </p>
 * <p>
 * Description: Defines the container templates
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
public interface AIiTemplateContainer<
		ITEM_REC extends AIiUidEnumDataRecord,
		ITEM_DATA extends AIiUidEnumData<
				ITEM_REC, AInUidEnumDataOrigin,
				AIiUidEnumDataType<ITEM_REC, AInUidEnumDataOrigin>>> {

	/**
	 * Gets the template items included in the template
	 * Map defined by this object
	 * @return the template items. As key is used the uid of the template
	 */
	Map<String, ITEM_DATA> templateItems();

}
