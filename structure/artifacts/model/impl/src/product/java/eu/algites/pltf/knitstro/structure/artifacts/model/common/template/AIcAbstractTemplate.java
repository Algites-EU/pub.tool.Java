package eu.algites.pltf.knitstro.structure.artifacts.model.common.template;

import java.util.LinkedHashMap;

/**
 * <p>
 * Title: {@link AIcAbstractTemplate}
 * </p>
 * <p>
 * Description: General renderPattern abstract predecessor
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 16.01.26 7:33
 */
public class AIcAbstractTemplate<SELF extends AIcAbstractTemplate> implements AIiAbstractTemplate<SELF> {
	private String templateUid;
	private LinkedHashMap<String, SELF> includedTemplates;

	@Override
	public String getTemplateUid() {
		return templateUid;
	}

	@Override
	public LinkedHashMap<String, SELF> getIncludedTemplates() {
		return includedTemplates;
	}
}
