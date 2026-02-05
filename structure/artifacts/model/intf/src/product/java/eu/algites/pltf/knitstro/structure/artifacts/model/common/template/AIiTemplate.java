package eu.algites.pltf.knitstro.structure.artifacts.model.common.template;

import java.util.LinkedHashMap;

/**
 * <p>
 * Title: {@link AIiTemplate}
 * </p>
 * <p>
 * Description: General interface for the template objects
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 16.01.26 7:16
 */
public interface AIiTemplate<SELF extends AIiTemplate> {

	/**
	 * Unique identifier of the template
	 * @return the template id
	 */
	String getTemplateUid();

	/**
	 * Templates that are included in this template in the template cascade.
	 * As the identification is the templateUid
	 * @return the includes template
	 */
	LinkedHashMap<String, SELF> getIncludedTemplates();
}
