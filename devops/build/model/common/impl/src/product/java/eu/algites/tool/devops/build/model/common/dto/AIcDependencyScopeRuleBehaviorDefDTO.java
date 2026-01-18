package eu.algites.tool.devops.build.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Dependency behavior flags controlling how the dependency is imported/used and exported/managed.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyScopeRuleBehaviorDefDTO {

    @JsonProperty("importUse")
    private AIcDependencyScopeRuleBehaviorImportUseDefDTO importUse;

    @JsonProperty("exportUse")
    private AIcDependencyScopeRuleBehaviorExportUseDefDTO exportUse;

    public AIcDependencyScopeRuleBehaviorImportUseDefDTO getImportUse() {
        return importUse;
    }

    public void setImportUse(AIcDependencyScopeRuleBehaviorImportUseDefDTO aImportUse) {
        importUse = aImportUse;
    }

    public AIcDependencyScopeRuleBehaviorExportUseDefDTO getExportUse() {
        return exportUse;
    }

    public void setExportUse(AIcDependencyScopeRuleBehaviorExportUseDefDTO aExportUse) {
        exportUse = aExportUse;
    }
}
