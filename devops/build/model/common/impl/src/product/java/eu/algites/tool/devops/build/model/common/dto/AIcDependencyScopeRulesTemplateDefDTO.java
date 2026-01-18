package eu.algites.tool.devops.build.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Apply-templateUid: reusable list of apply rules.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyScopeRulesTemplateDefDTO extends AIcAbstractTemplateDefDTO {

    @JsonProperty("scopeRules")
    private List<AIcDependencyScopeRuleDefDTO> scopeRules;

    public List<AIcDependencyScopeRuleDefDTO> getScopeRules() {
        return scopeRules;
    }

    public void setScopeRules(List<AIcDependencyScopeRuleDefDTO> aScopeRules) {
        scopeRules = aScopeRules;
    }
}
