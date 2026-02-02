package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import static eu.algites.tool.devops.build.model.utils.AIsArtifactModelUtils.toCoordinateId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Single "union" DTO for all artifact kinds. Unused fields for a given kind may be omitted in YAML.
 * @author linhart1
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public abstract class AIcArtifactCoordinateDefDTO
		extends AIcArtifactContainerDefDTO {

	@JsonProperty(value = "groupId")
	private String groupId;

	/**
	 * default artifactId base
	 */
	@JsonProperty(value = "artifactId")
	private String artifactId;

	// getters/setters

	public String getCoordinateId() {
		return toCoordinateId(getGroupId(), getArtifactId());
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String aGroupId) {
		this.groupId = aGroupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String aArtifactId) {
		this.artifactId = aArtifactId;
	}

	@Override
	public String toString() {
		return "AIcArtifactCoordinateDefDTO{" + super.toString() + "\n" +
				"(id='" + toCoordinateId(getGroupId(), getArtifactId()) + '\'' +
				"), groupId='" + groupId + '\'' +
				", artifactId='" + artifactId + '\'' +
				"} ";
	}
}
