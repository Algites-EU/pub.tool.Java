package eu.algites.tool.devops.build.model.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Import-side usage flags.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AIcDependencyScopeRuleCompileRuntimeBehaviorDefDTO {

    @JsonProperty("compile")
    private Boolean compile;

    @JsonProperty("runtime")
    private Boolean runtime;

    public Boolean getCompile() {
        return compile;
    }

    public void setCompile(Boolean aCompile) {
        compile = aCompile;
    }

    public Boolean getRuntime() {
        return runtime;
    }

    public void setRuntime(Boolean aRuntime) {
        runtime = aRuntime;
    }
}
