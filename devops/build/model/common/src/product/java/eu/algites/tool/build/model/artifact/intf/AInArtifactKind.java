package eu.algites.tool.build.model.artifact.intf;

import static eu.algites.tool.build.model.artifact.intf.AInArtifactClass.CONTROLLED;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactClass.UNCONTROLLED;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactOutputPackagingClass.JAR;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactOutputPackagingClass.POM;

/**
 * <p>
 * Title: {@link AInArtifactKind}
 * </p>
 * <p>
 * Description: The Kinds of the artifacts in Algites Development paradigm, described in <a
 * href="https://github.com/Algites-EU/pub.gov.Algites.specs/blob/main/Algites-Development-Structure-Specification.md">Algites Development
 * Structure Specification</a>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 07.01.26 11:17
 */
public enum AInArtifactKind {

	/**
	 * General product artifact non-BOM intended to be productively used somewhere, external dependency (jar/…)
	 */
	UNCONTROLLED_CORE(UNCONTROLLED, "(unknown core)", false, false, true, false, JAR),

	/**
	 * General product artifact POM intended not to be productively used somewhere, external POM used for an import or parent role only
	 */
	UNCONTROLLED_POM(UNCONTROLLED, "(unknown POM)", false, true, false, false, POM),

	/**
	 * General product artifact intended to be productively used somewhere
	 */
	PRODUCT_CORE(CONTROLLED, "PROD", true, false, true, false, JAR),

	/**
	 * Product Test artifact containing the tests intended to be used to inherit the tests from it or use the tests for the testing of other
	 * artifacts
	 */
	TEST_CORE(CONTROLLED, "TST", true, false, true, false, POM),

	/**
	 * Aggregator of multiple artifacts
	 */
	AGGREGATOR(CONTROLLED, "AGG", true, true, false, true, POM),

	/**
	 * Policy artifact defining the policy of the building and dependencies used by the product and test artifacts to execute the
	 * functionality
	 */
	DEPENDENCY_DEFINITION_POLICY(CONTROLLED, "POL", true, true, false, true, POM),

	/**
	 * Product interface BOM artifact, containing manually settled list of the given product artifacts, depending on the given policy
	 * artifact, without any pre-selection of the usage variant of the given product artifacts, open for construction of own variant (e.g.
	 * Spring framework BOM)
	 */
	PRODUCT_INTERFACE_BOM(CONTROLLED, "PIBOM", true, true, false, false, POM),

	/**
	 * Product varian BOM artifact, containing manually settled list of the product artifacts, composing together some specific product or
	 * product line, contianing already the compatible frameworks used by the products etc. (e.g. Spring boot BOM)
	 */
	PRODUCT_VARIANT_BOM(CONTROLLED, "PVBOM", true, true, false, false, POM),
	;

	private final AInArtifactClass artifactClass;
	private final String code;
	private final boolean controlled;
	private final boolean mavenPomArtifactTypeRequired;
	private final boolean coreFunctionalityArtifact;
	private final boolean potentialArtificialArtifact;
	private final AInArtifactOutputPackagingClass mainOutputTypeClass;

	AInArtifactKind(
			final AInArtifactClass aArtifactClass, String aCode, final boolean aControlled, final boolean aMavenPomArtifactTypeRequired,
			final boolean aCoreFunctionalityArtifact, final boolean aPotentialArtificialArtifact, 
			final AInArtifactOutputPackagingClass aMainOutputTypeClass) {
		artifactClass = aArtifactClass;
		code = aCode;
		controlled = aControlled;
		mavenPomArtifactTypeRequired = aMavenPomArtifactTypeRequired;
		coreFunctionalityArtifact = aCoreFunctionalityArtifact;
		potentialArtificialArtifact = aPotentialArtificialArtifact;
		mainOutputTypeClass = aMainOutputTypeClass;
	}

	/**
	 * Finds the artifact kind by its short code.
	 *
	 * @param aCode the short aCode (e.g. "PROD", "TST")
	 * @return matching {@link AInArtifactKind} or {@code null} if not found
	 */
	public static AInArtifactKind findByCode(String aCode) {
		if (aCode == null) {
			return null;
		}
		for (AInArtifactKind kind : values()) {
			if (kind.code.equals(aCode)) {
				return kind;
			}
		}
		return null;
	}

	/**
	 * @return the artifactClass
	 */
	public AInArtifactClass getArtifactClass() {
		return artifactClass;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Indicates, if the artifact is controlled by the framework (true) or uncontrolled (external - false)
	 *
	 * @return the controlled
	 */
	public boolean isControlled() {
		return controlled;
	}

	/**
	 * @return the mavenPomArtifactTypeRequired
	 */
	public boolean isMavenPomArtifactTypeRequired() {
		return mavenPomArtifactTypeRequired;
	}

	/**
	 * Defines if the artifact is the core functionality artifact (true) or some management artifact kind
	 *
	 * @return true if the artifact contains the core functionality and false if is for management purpose only
	 */
	public boolean isCoreFunctionalityArtifact() {
		return coreFunctionalityArtifact;
	}

	/**
	 * @return the potentialArtificialArtifact
	 */
	public boolean isPotentialArtificialArtifact() {
		return potentialArtificialArtifact;
	}

	/**
	 * @return the defaultOutputType
	 */
	public AInArtifactOutputPackagingClass getMainOutputTypeClass() {
		return mainOutputTypeClass;
	}

	/**
	 * Defines, if the output is universal - so if the output classes
	 * {@link AInArtifactOutputPackagingClass}, so has only the output
	 * of the type defined by {@link #getMainOutputTypeClass()}.
	 * Effectively this is a negation of the #isCoreFunctionalityArtifact().
	 * @return true if the artifact build has a universal output and false if not
	 */
	public boolean hasUniversalOutput() {
		return !isCoreFunctionalityArtifact();
	}

}
