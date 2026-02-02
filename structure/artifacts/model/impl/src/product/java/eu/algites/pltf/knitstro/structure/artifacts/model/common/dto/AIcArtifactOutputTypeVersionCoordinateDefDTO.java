package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Dependency artifact coordinate reference, with optional (uncontrolled) version rule.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcArtifactOutputTypeVersionCoordinateDefDTO extends AIcArtifactOutputTypeCoordinateDefDTO {

	/**
	 * for uncontrolled artifacts
	 */
	@JsonProperty("version")
	private String version;


	public String getVersion() {
		return version;
	}

	public void setVersion(String aVersion) {
		this.version = aVersion;
	}

}
