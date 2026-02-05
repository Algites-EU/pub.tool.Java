package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import eu.algites.lib.common.enums.uiddata.AIiUidEnumData;
import eu.algites.lib.common.enums.uiddata.AInUidEnumDataOrigin;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.template.AIiTemplateDataType;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.template.AIiTemplateDataUidRecord;

/**
 * <p>
 * Title: {@link AIiScopeBindingTemplateItemData}
 * </p>
 * <p>
 * Description: Dependency scope binding template data
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 25.01.26 14:49
 */
public interface AIiScopeBindingTemplateItemData
		extends AIiScopeBinding,
		AIiUidEnumData<AIiTemplateDataUidRecord, AInUidEnumDataOrigin, AIiTemplateDataType>, AIiScopeBindingsContainer {
}
