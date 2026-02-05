package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.version;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: {@link AIiUncontrolledVersionConstraintData}
 * </p>
 * <p>
 * Description: data, describing the uncontrolled version data.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 02.02.26 18:11
 */
public interface AIiUncontrolledVersionConstraintData {

	/**
	 * Returns constraint values keyed by their semantic kind.
	 *
	 * Typical combinations:
	 * - REQUIRED only
	 * - PINNED only
	 * - REQUIRED + PREFERRED
	 * - PINNED + PREFERRED
	 *
	 * Values use Gradle version notation (ranges, dynamic versions, etc.).
	 *
	 * @return constraints by kind (never null, may be empty)
	 */
	Map<AInUncontrolledVersionResolverConstraint, String> getConstraintsByKind();

	/**
	 * Rejected versions that must never be selected.
	 * Gradle mapping: {@code reject(...)} (multi-valued).
	 *
	 * @return rejected versions (never null, may be empty)
	 */
	List<String> getRejectedVersions();

	/**
	 * Convenience getter.
	 * @return the pinnedStrictlyConstraint
	 */
	default String getPinnedStrictlyConstraint() {
		return getConstraintsByKind().get(AInUncontrolledVersionResolverConstraint.PINNED_STRICTLY);
	}

	/** Convenience getter.
	 * @return the acceptRequireConstraint
	 */
	default String getAcceptRequireConstraint() {
		return getConstraintsByKind().get(AInUncontrolledVersionResolverConstraint.ACCEPT_REQUIRE);
	}

	/**
	 * Convenience getter.
	 * @return the preferredConstraint
	 */
	default String getPreferredConstraint() {
		return getConstraintsByKind().get(AInUncontrolledVersionResolverConstraint.PREFER);
	}
}
