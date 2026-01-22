package eu.algites.tool.devops.build.model.dependency;

/**
 * <p>
 * Title: {@link AIiArtifactDependencyScopeRuleRuntimeCompileBehavior}
 * </p>
 * <p>
 * Description: Defines the predecessor of the export and import behaviors
 *    of the rule for the dependency scope
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 18.01.26 15:28
 */
public interface AIiArtifactDependencyScopeRuleRuntimeCompileBehavior {

	Boolean getUseForRuntime();

	Boolean getUseForCompile();



}
