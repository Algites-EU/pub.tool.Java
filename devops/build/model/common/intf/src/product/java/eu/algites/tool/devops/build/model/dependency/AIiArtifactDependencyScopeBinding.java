package eu.algites.tool.devops.build.model.dependency;

/**
 * <p>
 * Title: {@link AIiArtifactDependencyScopeBinding}
 * </p>
 * <p>
 * Description: Defines the rule for the dependency scope
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
public interface AIiArtifactDependencyScopeBinding {

	AInArtifactDependencyBuiltinUsageChannel getUsage();

	AInArtifactDependencyBuiltinSourceSet getSourceSet();

	AIiArtifactDependencyScopeRuleBehavior getBehavior();

	Integer getWeight();

	Boolean isLocked();
}
