package eu.algites.tool.devops.build.model.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Single "union" DTO for all artifact kinds. Unused fields for a given kind may be omitted in YAML.
 * @author linhart1
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class AIcArtifactOutputTypeCoordinateDefDTO
		extends AIcArtifactCoordinateDefDTO {

	@JsonProperty("outputType")
	private String outputType;

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String aOutputType) {
		outputType = aOutputType;
	}


}
