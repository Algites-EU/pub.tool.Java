package eu.algites.tool.build.model.artifact.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Single "union" DTO for all artifact kinds. Unused fields for a given kind may be omitted in YAML.
 * @author linhart1
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class AIcArtifactDefDTO extends AIcArtifactCoordinateDefDTO {

	@JsonProperty(value = "kind")
	private AInArtifactKind kind;

	@JsonProperty(value = "displayName", required = true)
	private String displayName;

	@JsonProperty(value = "decription", required = true)
	private String description;

	public AInArtifactKind getKind() {
		return kind;
	}

	public void setKind(AInArtifactKind kind) {
		this.kind = kind;
	}

	/**
	 * optional parent reference (id)
	 */
	@JsonProperty("parent")
	private AIcArtifactCoordinateVersionDefDTO parent;

	/**
	 * POLICY only
	 */
	@JsonProperty("policyDefinitionUid")
	private String policyDefinitionUid;

	/**
	 * POLICY only
	 */
	@JsonProperty("policyDefinitionVersion")
	private String policyDefinitionVersion;

	/**
	 * Product Variant BOM only
	 */
	@JsonProperty("buildConfig")
	private AIcArtifactBuildConfigDefDTO buildConfig;

	/**
	 * direct dependencies (Policy, ProductCore, etc.)
	 */
	@JsonProperty("directDependencies")
	private List<AIcDependencyDefDTO> directDependencies;

	/**
	 * managed deps for POLICY (background) and PB-BOM
	 */
	@JsonProperty("managedPolicyBackgroundDependencies")
	private List<AIcDependencyDefDTO> managedPolicyBackgroundDependencies;

	/**
	 * managed deps for PI-BOM
	 */
	@JsonProperty("managedInterfaceDependencies")
	private List<AIcDependencyDefDTO> managedInterfaceDependencies;

	/**
	 * managed deps for PV-BOM
	 */
	@JsonProperty("managedProductVariantDependencies")
	private List<AIcDependencyDefDTO> managedProductVariantDependencies;

	// getters/setters

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

	public AIcArtifactCoordinateVersionDefDTO getParent() {
		return parent;
	}

	public void setParent(AIcArtifactCoordinateVersionDefDTO aParent) {
		this.parent = aParent;
	}

	public String getPolicyDefinitionUid() {
		return policyDefinitionUid;
	}

	public void setPolicyDefinitionUid(String aPolicyDefinitionUid) {
		this.policyDefinitionUid = aPolicyDefinitionUid;
	}

	public String getPolicyDefinitionVersion() {
		return policyDefinitionVersion;
	}

	public void setPolicyDefinitionVersion(String policyDefinitionVersion) {
		this.policyDefinitionVersion = policyDefinitionVersion;
	}

	/**
	 * @return the buildConfig
	 */
	public AIcArtifactBuildConfigDefDTO getBuildConfig() {
		return buildConfig;
	}

	/**
	 * @param aBuildConfig the buildConfig
	 */
	public void setBuildConfig(final AIcArtifactBuildConfigDefDTO aBuildConfig) {
		buildConfig = aBuildConfig;
	}

	public List<AIcDependencyDefDTO> getDirectDependencies() {
		return directDependencies;
	}

	public void setDirectDependencies(List<AIcDependencyDefDTO> directDependencies) {
		this.directDependencies = directDependencies;
	}

	public List<AIcDependencyDefDTO> getManagedPolicyBackgroundDependencies() {
		return managedPolicyBackgroundDependencies;
	}

	public void setManagedPolicyBackgroundDependencies(List<AIcDependencyDefDTO> managedPolicyBackgroundDependencies) {
		this.managedPolicyBackgroundDependencies = managedPolicyBackgroundDependencies;
	}

	public List<AIcDependencyDefDTO> getManagedInterfaceDependencies() {
		return managedInterfaceDependencies;
	}

	public void setManagedInterfaceDependencies(List<AIcDependencyDefDTO> managedInterfaceDependencies) {
		this.managedInterfaceDependencies = managedInterfaceDependencies;
	}

	public List<AIcDependencyDefDTO> getManagedProductVariantDependencies() {
		return managedProductVariantDependencies;
	}

	public void setManagedProductVariantDependencies(List<AIcDependencyDefDTO> managedProductVariantDependencies) {
		this.managedProductVariantDependencies = managedProductVariantDependencies;
	}

	@Override
	public String toString() {
		return "AIcArtifactDefDTO{" + super.toString() + "\n" +
				"kind=" + kind +
				", displayName='" + displayName + '\'' +
				", description='" + description + '\'' +
				", parent=" + parent +
				", policyDefinitionUid='" + policyDefinitionUid + '\'' +
				", policyDefinitionVersion='" + policyDefinitionVersion + '\'' +
				", buildConfig=" + buildConfig +
				", directDependencies=" + directDependencies +
				", managedPolicyBackgroundDependencies=" + managedPolicyBackgroundDependencies +
				", managedInterfaceDependencies=" + managedInterfaceDependencies +
				", managedProductVariantDependencies=" + managedProductVariantDependencies +
				"} ";
	}
}
