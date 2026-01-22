package eu.algites.tool.devops.build.model.common.dto;

import eu.algites.tool.devops.build.model.dependency.AInArtifactDependencyBuiltinSourceSet;
import eu.algites.tool.devops.build.model.dependency.AInArtifactDependencyBuiltinUsageChannel;

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
    private AInArtifactDependencyBuiltinUsageChannel usage;

    @JsonProperty("sourceSet")
    private AInArtifactDependencyBuiltinSourceSet sourceSet;

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

    public AInArtifactDependencyBuiltinUsageChannel getUsage() {
        return usage;
    }

    public void setUsage(AInArtifactDependencyBuiltinUsageChannel aUsage) {
        usage = aUsage;
    }

    public AInArtifactDependencyBuiltinSourceSet getSourceSet() {
        return sourceSet;
    }

    public void setSourceSet(AInArtifactDependencyBuiltinSourceSet aSourceSet) {
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
