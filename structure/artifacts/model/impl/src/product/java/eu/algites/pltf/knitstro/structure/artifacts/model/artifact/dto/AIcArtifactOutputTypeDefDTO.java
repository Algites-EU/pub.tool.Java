package eu.algites.pltf.knitstro.structure.artifacts.model.artifact.dto;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.AIiArtifactOutputTypeData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Output declaration.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcArtifactOutputTypeDefDTO {

    @JsonProperty("outputType")
    private AIiArtifactOutputTypeData outputType;

    public AIiArtifactOutputTypeData getOutputType() {
        return outputType;
    }

    public void setOutputType(AIiArtifactOutputTypeData aOutputType) {
        outputType = aOutputType;
    }

}
