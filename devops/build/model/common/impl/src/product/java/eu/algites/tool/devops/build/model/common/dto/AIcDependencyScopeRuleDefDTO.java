package eu.algites.tool.devops.build.model.common.dto;

import eu.algites.tool.devops.build.model.artifact.AInDependencyBuiltinSourceSet;
import eu.algites.tool.devops.build.model.artifact.AInDependencyBuiltinUsageChannel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * One application rule for a dependency.
 * @author linhart1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIcDependencyScopeRuleDefDTO {

    @JsonProperty("templateId")
    private String templateId;

    @JsonProperty("usage")
    private AInDependencyBuiltinUsageChannel usage;

    @JsonProperty("sourceSet")
    private AInDependencyBuiltinSourceSet sourceSet;

    @JsonProperty("behavior")
    private AIcDependencyScopeRuleBehaviorDefDTO behavior;

    @JsonProperty("locked")
    private Boolean locked;

    @JsonProperty("weight")
    private Integer weight;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String aTemplateId) {
        templateId = aTemplateId;
    }

    public AInDependencyBuiltinUsageChannel getUsage() {
        return usage;
    }

    public void setUsage(AInDependencyBuiltinUsageChannel aUsage) {
        usage = aUsage;
    }

    public AInDependencyBuiltinSourceSet getSourceSet() {
        return sourceSet;
    }

    public void setSourceSet(AInDependencyBuiltinSourceSet aSourceSet) {
        sourceSet = aSourceSet;
    }

    public AIcDependencyScopeRuleBehaviorDefDTO getBehavior() {
        return behavior;
    }

    public void setBehavior(AIcDependencyScopeRuleBehaviorDefDTO aBehavior) {
        behavior = aBehavior;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean aLocked) {
        locked = aLocked;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer aWeight) {
        weight = aWeight;
    }
}
