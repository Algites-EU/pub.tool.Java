package eu.algites.tool.devops.build.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Export-side flags.
 *
 * <p>{@code manage} corresponds to dependency steering (BOM/platform/constraints) export.</p>
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyScopeRuleBehaviorExportUseDefDTO extends AIcDependencyScopeRuleCompileRuntimeBehaviorDefDTO {

    @JsonProperty("manage")
    private Boolean manage;

    public Boolean getManage() {
        return manage;
    }

    public void setManage(Boolean aManage) {
        manage = aManage;
    }
}
