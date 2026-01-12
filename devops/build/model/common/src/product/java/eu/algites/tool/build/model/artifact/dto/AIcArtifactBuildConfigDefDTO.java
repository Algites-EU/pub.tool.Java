package eu.algites.tool.build.model.artifact.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class AIcArtifactBuildConfigDefDTO {

	@JsonProperty("generatePolicyBackgroundBom")
	private Boolean generatePolicyBackgroundBom;

	@JsonProperty("includePolicyBackgroundBomDependencies")
	private Boolean includePolicyBackgroundBomDependencies;

	/**
	 * @return the generatePolicyBackgroundBom
	 */
	public Boolean getGeneratePolicyBackgroundBom() {
		return generatePolicyBackgroundBom;
	}

	/**
	 * @param aGeneratePolicyBackgroundBom the generatePolicyBackgroundBom
	 */
	public void setGeneratePolicyBackgroundBom(final Boolean aGeneratePolicyBackgroundBom) {
		generatePolicyBackgroundBom = aGeneratePolicyBackgroundBom;
	}

	/**
	 * @return the includePolicyBackgroundBomDependencies
	 */
	public Boolean getIncludePolicyBackgroundBomDependencies() {
		return includePolicyBackgroundBomDependencies;
	}

	/**
	 * @param aIncludePolicyBackgroundBomDependencies the includePolicyBackgroundBomDependencies
	 */
	public void setIncludePolicyBackgroundBomDependencies(final Boolean aIncludePolicyBackgroundBomDependencies) {
		includePolicyBackgroundBomDependencies = aIncludePolicyBackgroundBomDependencies;
	}

	@Override
	public String toString() {
		return "AIcArtifactBuildConfigDefDTO{" +
				"generatePolicyBackgroundBom=" + generatePolicyBackgroundBom +
				", includePolicyBackgroundBomDependencies=" + includePolicyBackgroundBomDependencies +
				'}';
	}
}
