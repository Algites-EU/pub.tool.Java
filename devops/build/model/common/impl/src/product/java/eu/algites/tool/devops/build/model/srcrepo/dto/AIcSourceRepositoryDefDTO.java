package eu.algites.tool.devops.build.model.srcrepo.dto;

import eu.algites.tool.devops.build.model.common.dto.AIcArtifactContainerDefDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Source srcrepo header.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcSourceRepositoryDefDTO extends AIcArtifactContainerDefDTO {

    @JsonProperty("repositoryId")
    private String repositoryId;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String aRepositoryId) {
        repositoryId = aRepositoryId;
    }
}
