package eu.algites.tool.devops.build.model.artifact;

/**
 * Implementation of {@link AIiArtifactDependencyScopeRuleExportBehavior}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeRuleExportBehavior
        implements AIiArtifactDependencyScopeRuleExportBehavior, AIiArtifactDependencyScopeRuleRuntimeCompileBehavior {

    private AIiArtifactDependencyScopeRuleImportBehavior importBehavior;

    private AIiArtifactDependencyScopeRuleExportBehavior exportBehavior;

    private Boolean useForManagement;

    private Boolean useForRuntime;

    private Boolean useForCompile;

    @Override
    public AIiArtifactDependencyScopeRuleImportBehavior getImportBehavior() {
        return importBehavior;
    }

    public void setImportBehavior(final AIiArtifactDependencyScopeRuleImportBehavior aImportBehavior) {
        importBehavior = aImportBehavior;
    }

    @Override
    public AIiArtifactDependencyScopeRuleExportBehavior getExportBehavior() {
        if (exportBehavior != null) {
            return exportBehavior;
        }
        return this;
    }

    public void setExportBehavior(final AIiArtifactDependencyScopeRuleExportBehavior aExportBehavior) {
        exportBehavior = aExportBehavior;
    }

    @Override
    public Boolean getUseForManagement() {
        return useForManagement;
    }

    public void setUseForManagement(final Boolean aUseForManagement) {
        useForManagement = aUseForManagement;
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
