package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependencyScopeBinding;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AInBuiltinPurpose;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AInBuiltinSourceSetGroup;

/**
 * Implementation of {@link AIiArtifactDependencyScopeBinding}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeBinding implements AIiArtifactDependencyScopeBinding {

    private AInBuiltinPurpose purpose;

    private AInBuiltinSourceSetGroup sourceSet;

    private AIiArtifactDependencyScopeRuleBehavior behavior;

    private Integer weight;

    private Boolean locked;

    @Override
    public AInBuiltinPurpose getPurposes() {
        return purpose;
    }

    public void setPurpose(final AInBuiltinPurpose aPurpose) {
        purpose = aPurpose;
    }

    @Override
    public AInBuiltinSourceSetGroup getSourceSets() {
        return sourceSet;
    }

    public void setSourceSet(final AInBuiltinSourceSetGroup aSourceSet) {
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
