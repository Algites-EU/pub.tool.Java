package eu.algites.tool.devops.build.model.common.template;

import java.util.Map;

/**
 * <p>
 * Title: {@link AIiPropertiesTemplate}
 * </p>
 * <p>
 * Description: TODO: Add description
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
public interface AIiPropertiesTemplate {

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
	Map<String, AIiPropertiesTemplate> getIncludedTemplates();
}
