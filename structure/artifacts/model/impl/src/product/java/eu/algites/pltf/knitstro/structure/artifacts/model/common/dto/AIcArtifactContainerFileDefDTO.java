package eu.algites.pltf.knitstro.structure.artifacts.model.common.dto;

import java.net.URI;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Common container-level configuration shared by artifact and source-srcrepo YAML files.
 *
 * <p>This DTO models the "container" layer: version context and renderPattern sets that are
 * inherited by contained artifacts in the same container.</p>
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcArtifactContainerFileDefDTO {

    @JsonProperty("versionContext")
    private AIcVersionContextDefDTO versionContext;

    @JsonProperty("projectPropertiesTemplates")
    private List<AIcProjectPropertiesTemplateDefDTO> projectPropertiesTemplates;

    @JsonProperty("dependencyApplyTemplates")
    private List<AIcDependencyScopeRulesTemplateDefDTO> dependencyScopeRuleTemplates;

    @JsonProperty("dependenciesTemplates")
    private List<AIcDependenciesTemplateDefDTO> dependenciesTemplates;

  	@JsonProperty("templateUrls")
  	private List<URI> templateUrls;

	/**
     * Extension point for forward-compatibility.
     */
    @JsonProperty("properties")
    private Map<String, Object> properties;

    public AIcVersionContextDefDTO getVersionContext() {
        return versionContext;
    }

    public void setVersionContext(AIcVersionContextDefDTO aVersionContext) {
        versionContext = aVersionContext;
    }

    public List<AIcProjectPropertiesTemplateDefDTO> getPropertiesTemplates() {
        return projectPropertiesTemplates;
    }

    public void setPropertiesTemplates(List<AIcProjectPropertiesTemplateDefDTO> aPropertiesTemplates) {
        projectPropertiesTemplates = aPropertiesTemplates;
    }

    public List<AIcDependencyScopeRulesTemplateDefDTO> getDependencyScopeRuleTemplates() {
        return dependencyScopeRuleTemplates;
    }

    public void setDependencyScopeRuleTemplates(List<AIcDependencyScopeRulesTemplateDefDTO> aDependencyScopeRuleTemplates) {
        dependencyScopeRuleTemplates = aDependencyScopeRuleTemplates;
    }

    public List<AIcDependenciesTemplateDefDTO> getDependenciesTemplates() {
        return dependenciesTemplates;
    }

    public void setDependenciesTemplates(List<AIcDependenciesTemplateDefDTO> aDependenciesTemplates) {
        dependenciesTemplates = aDependenciesTemplates;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> aProperties) {
        properties = aProperties;
    }

		public List<URI> getTemplateUrls() {
			return templateUrls;
		}

		public void setTemplateUrls(final List<URI> aTemplateUrls) {
			templateUrls = aTemplateUrls;
		}
}
