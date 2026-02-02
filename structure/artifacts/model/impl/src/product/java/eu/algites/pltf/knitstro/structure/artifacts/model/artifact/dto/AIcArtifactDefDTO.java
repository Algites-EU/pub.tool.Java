package eu.algites.pltf.knitstro.structure.artifacts.model.artifact.dto;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.dto.AIcArtifactContainerDefDTO;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.dto.AIcArtifactOutputTypeVersionCoordinateDefDTO;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.dto.AIcDependencyDefDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Artifact header and body.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcArtifactDefDTO extends AIcArtifactContainerDefDTO {

    @JsonProperty("groupId")
    private String groupId;

    @JsonProperty("artifactId")
    private String artifactId;

    @JsonProperty("parent")
    private AIcArtifactOutputTypeVersionCoordinateDefDTO parent;

    @JsonProperty("outputs")
    private List<AIcArtifactOutputTypeDefDTO> outputs;

    @JsonProperty("dependencies")
    private List<AIcDependencyDefDTO> dependencies;

    /**
     * Extension point for forward-compatibility.
     */
    @JsonProperty("extras")
    private Map<String, Object> extras;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String aGroupId) {
        groupId = aGroupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String aArtifactId) {
        artifactId = aArtifactId;
    }

    public AIcArtifactOutputTypeVersionCoordinateDefDTO getParent() {
        return parent;
    }

    public void setParent(AIcArtifactOutputTypeVersionCoordinateDefDTO aParent) {
        parent = aParent;
    }

    public List<AIcArtifactOutputTypeDefDTO> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<AIcArtifactOutputTypeDefDTO> aOutputs) {
        outputs = aOutputs;
    }

    public List<AIcDependencyDefDTO> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<AIcDependencyDefDTO> aDependencies) {
        dependencies = aDependencies;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> aExtras) {
        extras = aExtras;
    }
}
