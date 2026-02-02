package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeBinding;
import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeBindingsContainer;

import java.util.List;

/**
 * Implementation of {@link AIiArtifactDependencyScopeBindingsContainer}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeBindingsContainer implements AIiArtifactDependencyScopeBindingsContainer {

    private List<AIiArtifactDependencyScopeBinding> scopeBindings;

    @Override
    public List<AIiArtifactDependencyScopeBinding> getBindings() {
        return scopeBindings;
    }

    public void setScopeRules(final List<AIiArtifactDependencyScopeBinding> aScopeRules) {
        scopeBindings = aScopeRules;
    }
}
