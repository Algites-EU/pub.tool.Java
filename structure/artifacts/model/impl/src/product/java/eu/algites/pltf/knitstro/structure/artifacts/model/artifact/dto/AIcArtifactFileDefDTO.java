package eu.algites.pltf.knitstro.structure.artifacts.model.artifact.dto;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.dto.AIcArtifactContainerFileDefDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Artifact definition YAML root.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcArtifactFileDefDTO extends AIcArtifactContainerFileDefDTO {

    @JsonProperty("artifact")
    private AIcArtifactDefDTO artifact;

    public AIcArtifactDefDTO getArtifact() {
        return artifact;
    }

    public void setArtifact(AIcArtifactDefDTO aArtifact) {
        artifact = aArtifact;
    }
}
