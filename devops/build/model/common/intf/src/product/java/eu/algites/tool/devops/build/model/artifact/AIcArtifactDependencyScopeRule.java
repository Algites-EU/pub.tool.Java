package eu.algites.tool.devops.build.model.artifact;

/**
 * Implementation of {@link AIiArtifactDependencyScopeRule}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeRule implements AIiArtifactDependencyScopeRule {

    private AInDependencyBuiltinUsageChannel usage;

    private AInDependencyBuiltinSourceSet sourceSet;

    private AIiArtifactDependencyScopeRuleBehavior behavior;

    private Integer weight;

    private Boolean locked;

    @Override
    public AInDependencyBuiltinUsageChannel getUsage() {
        return usage;
    }

    public void setUsage(final AInDependencyBuiltinUsageChannel aUsage) {
        usage = aUsage;
    }

    @Override
    public AInDependencyBuiltinSourceSet getSourceSet() {
        return sourceSet;
    }

    public void setSourceSet(final AInDependencyBuiltinSourceSet aSourceSet) {
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
