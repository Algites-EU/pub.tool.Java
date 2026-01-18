package eu.algites.tool.devops.build.model.artifact;

/**
 * Implementation of {@link AIiArtifactDependencyScopeRuleRuntimeCompileBehavior}.
 *
 * @author linhart1
 */
public class AIcArtifactDependencyScopeRuleRuntimeCompileBehavior implements AIiArtifactDependencyScopeRuleRuntimeCompileBehavior {

    private Boolean useForRuntime;

    private Boolean useForCompile;

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
