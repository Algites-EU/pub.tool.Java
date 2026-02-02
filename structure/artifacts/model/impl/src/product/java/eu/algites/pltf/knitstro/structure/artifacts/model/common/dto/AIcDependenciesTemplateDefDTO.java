package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Dependencies-templateUid: reusable list of dependency entries.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependenciesTemplateDefDTO extends AIcAbstractTemplateDefDTO {

    @JsonProperty("dependencies")
    private List<AIcDependencyDefDTO> dependencies;

    public List<AIcDependencyDefDTO> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<AIcDependencyDefDTO> aDependencies) {
        dependencies = aDependencies;
    }
}
