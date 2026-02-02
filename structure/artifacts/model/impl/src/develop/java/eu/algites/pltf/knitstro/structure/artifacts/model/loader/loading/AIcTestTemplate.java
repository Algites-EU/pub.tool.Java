package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

import eu.algites.tool.devops.build.model.common.template.AIiAbstractTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * Title: {@link AIcTestTemplate}
 * </p>
 * <p>
 * Description:
 * Minimal test renderPattern implementing {@code AIiAbstractTemplate<T>}.
 * Adjust {@link #getIncludedTemplates()} return type to match your production interface if needed.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 16.01.26 9:15
 */
public class AIcTestTemplate  implements AIiAbstractTemplate<AIcTestTemplate> {
	private final String locId;
	private final LinkedHashMap<String, AIcTestTemplate> locIncludedTemplates;

	public AIcTestTemplate(final String aId, final Map<String, AIcTestTemplate> aIncludedTemplates) {
		locId = Objects.requireNonNull(aId, "aId must not be null");
		locIncludedTemplates = new LinkedHashMap<>(Objects.requireNonNull(aIncludedTemplates, "aIncludedTemplates must not be null"));
	}

	public String getTemplateUid() {
		return locId;
	}

	@Override
	public LinkedHashMap<String, AIcTestTemplate> getIncludedTemplates() {
		return locIncludedTemplates;
	}

	@Override
	public boolean equals(final Object aOther) {
		if (this == aOther) {
			return true;
		}
		if (!(aOther instanceof AIcTestTemplate)) {
			return false;
		}
		AIcTestTemplate locOther = (AIcTestTemplate) aOther;
		return Objects.equals(locId, locOther.locId)
				&& Objects.equals(locIncludedTemplates, locOther.locIncludedTemplates);
	}

	@Override
	public int hashCode() {
		return Objects.hash(locId, locIncludedTemplates);
	}

	@Override
	public String toString() {
		return "AIcTestTemplate{id='" + locId + "', included=" + locIncludedTemplates.keySet() + "}";
	}
}
