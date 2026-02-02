package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Apply-templateUid: reusable list of apply rules.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyScopeRulesTemplateDefDTO extends AIcAbstractTemplateDefDTO {

    @JsonProperty("scopeBindings")
    private List<AIcDependencyBindingDefDTO> scopeBindings;

    public List<AIcDependencyBindingDefDTO> getScopeRules() {
        return scopeBindings;
    }

    public void setScopeRules(List<AIcDependencyBindingDefDTO> aScopeRules) {
        scopeBindings = aScopeRules;
    }
}
