package eu.algites.pltf.knitstro.structure.artifacts.model.common.template;

import java.util.LinkedHashMap;

/**
 * <p>
 * Title: {@link AIiAbstractTemplate}
 * </p>
 * <p>
 * Description: General interface for the renderPattern objects
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
public interface AIiAbstractTemplate<SELF extends AIiAbstractTemplate> {

	/**
	 * Unique identifier of the renderPattern
	 * @return the renderPattern id
	 */
	String getTemplateUid();

	/**
	 * Templates that are included in this renderPattern in the renderPattern cascade.
	 * As the identification is the templateUid
	 * @return the includes renderPattern
	 */
	LinkedHashMap<String, SELF> getIncludedTemplates();
}
