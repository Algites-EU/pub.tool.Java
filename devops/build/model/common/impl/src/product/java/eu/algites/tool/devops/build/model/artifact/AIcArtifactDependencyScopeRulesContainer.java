package eu.algites.tool.devops.build.model.artifact;

import java.util.List;

/**
 * Implementation of {@link AIiArtifactDependencyScopeRulesContainer}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeRulesContainer implements AIiArtifactDependencyScopeRulesContainer {

    private List<AIiArtifactDependencyScopeRule> scopeRules;

    @Override
    public List<AIiArtifactDependencyScopeRule> getScopeRules() {
        return scopeRules;
    }

    public void setScopeRules(final List<AIiArtifactDependencyScopeRule> aScopeRules) {
        scopeRules = aScopeRules;
    }
}
