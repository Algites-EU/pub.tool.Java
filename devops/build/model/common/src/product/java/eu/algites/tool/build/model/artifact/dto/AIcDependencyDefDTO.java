package eu.algites.tool.build.model.artifact.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class AIcDependencyDefDTO {

	/**
	 * ID of the target artifact in the same YAML model
	 */
	@JsonProperty(value = "ref", required = true)
	private AIcArtifactCoordinateVersionDefDTO referencedArtifact;

	@JsonProperty("scope")
	private AIcScopeDefDTO scope;

	@JsonProperty("exclusions")
	private List<AIcExclusionDefDTO> exclusions;

	public AIcArtifactCoordinateVersionDefDTO getReferencedArtifact() {
		return referencedArtifact;
	}

	public void setReferencedArtifact(AIcArtifactCoordinateVersionDefDTO aReferencedArtifact) {
		this.referencedArtifact = aReferencedArtifact;
	}

	public AIcScopeDefDTO getScope() {
		return scope;
	}

	public void setScope(AIcScopeDefDTO scope) {
		this.scope = scope;
	}

	public List<AIcExclusionDefDTO> getExclusions() {
		return exclusions;
	}

	public void setExclusions(List<AIcExclusionDefDTO> exclusions) {
		this.exclusions = exclusions;
	}

	@Override
	public String toString() {
		return "AIcDependencyDefDTO{" +
				"referencedArtifact=" + referencedArtifact +
				", scope=" + scope +
				", exclusions=" + exclusions +
				'}';
	}
}
