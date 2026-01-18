package eu.algites.tool.devops.build.model.common.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>
 * Title: {@link AIcAbstractTemplateDefDTO}
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
 * @date 18.01.26 11:58
 */
public abstract class AIcAbstractTemplateDefDTO {
	@JsonProperty("templateUid")
	private String templateUid;
	@JsonProperty("includedTemplateUids")
	private List<String> includedTemplateUids;

	public String getTemplateUid() {
		return templateUid;
	}

	public void setTemplateUid(String aTemplateUid) {
		templateUid = aTemplateUid;
	}

	public List<String> getIncludedTemplateUids() {
		return includedTemplateUids;
	}

	public void setIncludedTemplateUids(List<String> aTemplates) {
		includedTemplateUids = aTemplates;
	}
}
