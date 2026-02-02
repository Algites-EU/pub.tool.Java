package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

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
