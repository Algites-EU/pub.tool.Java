package eu.algites.tool.devops.build.model.artifact;

import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeRuleBehavior;
import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeRuleExportBehavior;
import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeRuleImportBehavior;

/**
 * Implementation of {@link AIiArtifactDependencyScopeRuleBehavior}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeRuleBehavior implements AIiArtifactDependencyScopeRuleBehavior {

    private AIiArtifactDependencyScopeRuleImportBehavior importBehavior;

    private AIiArtifactDependencyScopeRuleExportBehavior exportBehavior;

    @Override
    public AIiArtifactDependencyScopeRuleImportBehavior getImportBehavior() {
        return importBehavior;
    }

    public void setImportBehavior(final AIiArtifactDependencyScopeRuleImportBehavior aImportBehavior) {
        importBehavior = aImportBehavior;
    }

    @Override
    public AIiArtifactDependencyScopeRuleExportBehavior getExportBehavior() {
        return exportBehavior;
    }

    public void setExportBehavior(final AIiArtifactDependencyScopeRuleExportBehavior aExportBehavior) {
        exportBehavior = aExportBehavior;
    }
}
