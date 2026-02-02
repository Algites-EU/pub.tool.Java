package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Dependency entry: either a renderPattern reference, or a concrete artifact reference, plus apply rules.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyDefDTO {

    @JsonProperty("templates")
    private String templateUids;

    @JsonProperty("artifact")
    private AIcArtifactOutputTypeVersionCoordinateDefDTO artifact;

    @JsonProperty("scopeBindings")
    private List<AIcDependencyBindingDefDTO> scopeBindings;

    public String getTemplateUid() {
        return templateUids;
    }

    public void setTemplateUid(String aTemplateUid) {
        templateUids = aTemplateUid;
    }

    public AIcArtifactOutputTypeVersionCoordinateDefDTO getArtifact() {
        return artifact;
    }

    public void setArtifact(AIcArtifactOutputTypeVersionCoordinateDefDTO aArtifact) {
        artifact = aArtifact;
    }

    public List<AIcDependencyBindingDefDTO> getScopeRules() {
        return scopeBindings;
    }

    public void setScopeRules(List<AIcDependencyBindingDefDTO> aScopeRules) {
        scopeBindings = aScopeRules;
    }

}
