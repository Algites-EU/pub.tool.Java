package eu.algites.tool.devops.build.model.artifact;

import java.util.List;

/**
 * <p>
 * Title: {@link AIiArtifactDependencyScopeRulesContainer}
 * </p>
 * <p>
 * Description: TODO: Add description
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 18.01.26 18:01
 */
public interface AIiArtifactDependencyScopeRulesContainer {
	/**
	 * Gets the dependency scope
	 *
	 * @return the dependency scope
	 */
	List<AIiArtifactDependencyScopeRule> getScopeRules();
}
