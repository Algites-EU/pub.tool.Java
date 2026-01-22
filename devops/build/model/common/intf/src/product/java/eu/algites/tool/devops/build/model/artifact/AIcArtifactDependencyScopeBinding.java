package eu.algites.tool.devops.build.model.artifact;

import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeBinding;
import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeRuleBehavior;
import eu.algites.tool.devops.build.model.dependency.AInArtifactDependencyBuiltinSourceSet;
import eu.algites.tool.devops.build.model.dependency.AInArtifactDependencyBuiltinUsageChannel;

/**
 * Implementation of {@link AIiArtifactDependencyScopeBinding}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeBinding implements AIiArtifactDependencyScopeBinding {

    private AInArtifactDependencyBuiltinUsageChannel usage;

    private AInArtifactDependencyBuiltinSourceSet sourceSet;

    private AIiArtifactDependencyScopeRuleBehavior behavior;

    private Integer weight;

    private Boolean locked;

    @Override
    public AInArtifactDependencyBuiltinUsageChannel getUsage() {
        return usage;
    }

    public void setUsage(final AInArtifactDependencyBuiltinUsageChannel aUsage) {
        usage = aUsage;
    }

    @Override
    public AInArtifactDependencyBuiltinSourceSet getSourceSet() {
        return sourceSet;
    }

    public void setSourceSet(final AInArtifactDependencyBuiltinSourceSet aSourceSet) {
        sourceSet = aSourceSet;
    }

    @Override
    public AIiArtifactDependencyScopeRuleBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(final AIiArtifactDependencyScopeRuleBehavior aBehavior) {
        behavior = aBehavior;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(final Integer aWeight) {
        weight = aWeight;
    }

    @Override
    public Boolean isLocked() {
        return locked;
    }

    public void setLocked(final Boolean aLocked) {
        locked = aLocked;
    }
}
