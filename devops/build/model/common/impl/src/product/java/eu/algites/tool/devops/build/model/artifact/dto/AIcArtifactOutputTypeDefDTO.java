package eu.algites.tool.devops.build.model.artifact.dto;

import eu.algites.tool.devops.build.model.common.AIiArtifactOutputTypeUidPartsRecord;
import eu.algites.tool.devops.build.model.common.AIsComponentUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Output declaration.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcArtifactOutputTypeDefDTO {

    @JsonProperty("outputType")
    private String outputType;

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String aOutputType) {
        outputType = aOutputType;
    }

	  public AIiArtifactOutputTypeUidPartsRecord toOutputParts() {
			return AIsComponentUtils.parseUid( outputType);
		}
}
