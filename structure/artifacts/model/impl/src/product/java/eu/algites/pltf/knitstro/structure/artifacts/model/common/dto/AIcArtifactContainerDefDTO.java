package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Common header for artifact-like containers.
 *
 * <p>Both artifacts and source repositories have a human-readable label and may contain other artifacts
 * referenced by relative paths.</p>
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcArtifactContainerDefDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

	@JsonProperty("projectProperties")
	private AIcProjectPropertiesDefDTO projectProperties;

	public AIcProjectPropertiesDefDTO getProperties() {
		return projectProperties;
	}

	public void setProperties(AIcProjectPropertiesDefDTO aProperties) {
		projectProperties = aProperties;
	}


	@JsonProperty("containedArtifactRelativePaths")
    private List<String> containedArtifactRelativePaths;

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public List<String> getContainedArtifactRelativePaths() {
        return containedArtifactRelativePaths;
    }

    public void setContainedArtifactRelativePaths(List<String> aContainedArtifactRelativePaths) {
        containedArtifactRelativePaths = aContainedArtifactRelativePaths;
    }
}
