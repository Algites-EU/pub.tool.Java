package eu.algites.tool.build.model.artifact.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Single "union" DTO for all artifact kinds. Unused fields for a given kind may be omitted in YAML.
 * @author linhart1
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class AIcArtifactCoordinateVersionDefDTO
		extends AIcArtifactCoordinateDefDTO {

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

	@Override
	public String toString() {
		return "AIcArtifactCoordinateVersionDefDTO{" + super.toString() + "\n" +
				"version='" + version + '\'' +
				"} ";
	}
}
