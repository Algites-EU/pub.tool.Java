package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

import eu.algites.tool.devops.build.model.common.template.AIiAbstractTemplate;

/**
 * <p>
 * Title: {@link AIcTemplateLateInitializationMap}
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
 * @date 16.01.26 8:08
 */
public class AIcTemplateLateInitializationMap<T extends AIiAbstractTemplate<T>> extends AIcLateInitializationMap<String, T> {

	/**
	 * Default constructor
	 */
	public AIcTemplateLateInitializationMap() {
		super(new AIcTemplateLateInitializationValidator<>());
	}

	@Override
	public String computeKeyFor(final T aValue) {
		if (aValue == null) return null;
		return aValue.getTemplateUid();
	}

}
