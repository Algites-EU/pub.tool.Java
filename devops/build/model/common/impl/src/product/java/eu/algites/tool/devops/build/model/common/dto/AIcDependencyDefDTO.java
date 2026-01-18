package eu.algites.tool.devops.build.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Dependency entry: either a template reference, or a concrete artifact reference, plus apply rules.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyDefDTO {

    @JsonProperty("templateUid")
    private String templateUid;

    @JsonProperty("artifact")
    private AIcArtifactOutputTypeVersionCoordinateDefDTO artifact;

    @JsonProperty("scopeRules")
    private List<AIcDependencyScopeRuleDefDTO> scopeRules;

    @JsonProperty("exclusions")
    private List<AIcArtifactCoordinateDefDTO> exclusions;

    public String getTemplateUid() {
        return templateUid;
    }

    public void setTemplateUid(String aTemplateUid) {
        templateUid = aTemplateUid;
    }

    public AIcArtifactOutputTypeVersionCoordinateDefDTO getArtifact() {
        return artifact;
    }

    public void setArtifact(AIcArtifactOutputTypeVersionCoordinateDefDTO aArtifact) {
        artifact = aArtifact;
    }

    public List<AIcDependencyScopeRuleDefDTO> getScopeRules() {
        return scopeRules;
    }

    public void setScopeRules(List<AIcDependencyScopeRuleDefDTO> aScopeRules) {
        scopeRules = aScopeRules;
    }

	/**
	 * @return the exclusions
	 */
	public List<AIcArtifactCoordinateDefDTO> getExclusions() {
		return exclusions;
	}

	/**
	 * @param aExclusions the exclusions
	 */
	public void setExclusions(final List<AIcArtifactCoordinateDefDTO> aExclusions) {
		exclusions = aExclusions;
	}
}
