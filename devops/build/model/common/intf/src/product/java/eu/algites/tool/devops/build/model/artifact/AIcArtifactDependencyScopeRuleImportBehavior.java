package eu.algites.tool.devops.build.model.artifact;

import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeRuleExportBehavior;
import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeRuleImportBehavior;
import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependencyScopeRuleRuntimeCompileBehavior;

/**
 * Implementation of {@link AIiArtifactDependencyScopeRuleImportBehavior}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeRuleImportBehavior
        implements AIiArtifactDependencyScopeRuleImportBehavior, AIiArtifactDependencyScopeRuleRuntimeCompileBehavior {

    private AIiArtifactDependencyScopeRuleImportBehavior importBehavior;

    private AIiArtifactDependencyScopeRuleExportBehavior exportBehavior;

    private Boolean useForRuntime;

    private Boolean useForCompile;

    @Override
    public AIiArtifactDependencyScopeRuleImportBehavior getImportBehavior() {
        if (importBehavior != null) {
            return importBehavior;
        }
        return this;
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

    @Override
    public Boolean getUseForRuntime() {
        return useForRuntime;
    }

    public void setUseForRuntime(final Boolean aUseForRuntime) {
        useForRuntime = aUseForRuntime;
    }

    @Override
    public Boolean getUseForCompile() {
        return useForCompile;
    }

    public void setUseForCompile(final Boolean aUseForCompile) {
        useForCompile = aUseForCompile;
    }
}
