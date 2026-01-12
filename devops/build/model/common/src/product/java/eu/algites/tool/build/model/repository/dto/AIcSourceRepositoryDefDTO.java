package eu.algites.tool.build.model.repository.dto;

import eu.algites.tool.build.model.common.dto.AIcArtifactContainerDefDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Single "union" DTO for all artifact kinds. Unused fields for a given kind may be omitted in YAML.
 * @author linhart1
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class AIcSourceRepositoryDefDTO extends AIcArtifactContainerDefDTO {

	@JsonProperty(value = "id", required = true)
	private String repositoryId;

	@JsonProperty(value = "displayName", required = true)
	private String displayName;

	@JsonProperty(value = "decription", required = true)
	private String description;

	/**
	 * @return the repositoryId
	 */
	public String getRepositoryId() {
		return repositoryId;
	}

	/**
	 * @param aRepositoryId the repositoryId
	 */
	public void setRepositoryId(final String aRepositoryId) {
		repositoryId = aRepositoryId;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param aDisplayName the displayName
	 */
	public void setDisplayName(final String aDisplayName) {
		displayName = aDisplayName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param aDescription the description
	 */
	public void setDescription(final String aDescription) {
		description = aDescription;
	}

	@Override
	public String toString() {
		return "AIcSourceRepositoryDefDTO{" + super.toString() + "\n" +
				"repositoryId='" + repositoryId + '\'' +
				", displayName='" + displayName + '\'' +
				", description='" + description + '\'' +
				"} ";
	}
}
