package eu.algites.pltf.knitstro.structure.artifacts.model.srcrepo.dto;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.dto.AIcArtifactContainerFileDefDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Source-srcrepo definition YAML root.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcSourceRepositoryFileDefDTO extends AIcArtifactContainerFileDefDTO {

    @JsonProperty("sourceRepository")
    private AIcSourceRepositoryDefDTO sourceRepository;

    public AIcSourceRepositoryDefDTO getSourceRepository() {
        return sourceRepository;
    }

    public void setSourceRepository(AIcSourceRepositoryDefDTO aSourceRepository) {
        sourceRepository = aSourceRepository;
    }
}
