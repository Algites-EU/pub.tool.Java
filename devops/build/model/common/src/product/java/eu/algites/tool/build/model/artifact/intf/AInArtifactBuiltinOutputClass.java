package eu.algites.tool.build.model.artifact.intf;

import static eu.algites.tool.build.model.artifact.intf.AInArtifactKind.DEPENDENCY_DEFINITION_POLICY;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactOutputPackagingClass.JAR;
import static eu.algites.tool.build.model.artifact.intf.AInArtifactOutputPackagingClass.POM;

import java.util.Objects;

/**
 * <p>
 * Title: {@link AInArtifactBuiltinOutputClass}
 * </p>
 * <p>
 * Description: Defines the build-in output types, supported out-of-the-box by the Algites Artifacts facility
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 11.01.26 4:55
 */
public enum AInArtifactBuiltinOutputClass {
	DEFAULT_MAIN(true, null, null),
	DEFAULT_MAIN_SRC(true, "sources", JAR),
	DEFAULT_TEST_JAR(true, "tests", JAR),
	DEFAULT_TEST_SRC(true, "tests-sources", JAR),
	POLICY_BACKGROUND_BOM(false, "pbbom", POM),
	;

	private final boolean coveredByUniversalArtifactKindOutput;
	private final String classifier;
	private final AInArtifactOutputPackagingClass packaging;

	AInArtifactBuiltinOutputClass(final boolean aCoveredByUniversalArtifactKindOutput, final String aClassifier, final AInArtifactOutputPackagingClass aPackaging) {
		coveredByUniversalArtifactKindOutput = aCoveredByUniversalArtifactKindOutput;
		classifier = aClassifier;
		packaging = aPackaging;
	}

	/**
	 * Convenience method to find the builtin output class for the given artifact kind and output local identification.
	 * @param aArtifactKind the artifact kind
	 * @param aOutputLocalIdentification the output local identification
	 * @return the found output class or null, if no output class was found
	 */
	public static AInArtifactBuiltinOutputClass find(AInArtifactKind aArtifactKind, AIcArtifactOutputLocalKey aOutputLocalIdentification) {
		return find(aArtifactKind,
				aOutputLocalIdentification == null ? null : aOutputLocalIdentification.getOutputClassifier(),
				aOutputLocalIdentification == null ? null : aOutputLocalIdentification.getOutputPackagingId());
	}

	/**
	 * Finds the builtin output class for the given artifact kind, classifier and packaging.
	 * @param aArtifactKind the artifact kind
	 * @param aClassifier classifier for which the search has to be executed
	 * @param aPackagingId packaging for which the search has to be executed
	 * @return the found output class or null, if no output class was found
	 */
	public static AInArtifactBuiltinOutputClass find(AInArtifactKind aArtifactKind, String aClassifier, String aPackagingId) {
		if (aArtifactKind == null) return null;
		if (aClassifier == null || aClassifier.isBlank())
			return aPackagingId == null || aPackagingId.isBlank()
					|| Objects.equals(aArtifactKind.getMainOutputTypeClass().getAssignedOutputPackagingId(), aPackagingId) ? DEFAULT_MAIN : null;
		for (AInArtifactBuiltinOutputClass locOutputClass : values()) {
			if (locOutputClass == DEFAULT_MAIN) continue;
			if (Objects.equals(locOutputClass.getClassifier(), aClassifier)) {
					if (aPackagingId == null || aPackagingId.isBlank()
							|| Objects.equals(aPackagingId, locOutputClass.getPackaging().getAssignedOutputPackagingId()))
						return locOutputClass;
			}
		}
		return null;
	}

	/**
	 * Defines, if the artifact kind is allowed to generate this output type.
	 * @param aArtifactKind the artifact kind
	 * @return true, if the artifact kind is allowed to generate this output type
	 */
	public boolean isPossibleToGenerateForArtifactKind(AInArtifactKind aArtifactKind) {
		if (aArtifactKind == null) return false;
		if (this == DEFAULT_MAIN) return true;
		if (this == POLICY_BACKGROUND_BOM) return aArtifactKind == DEPENDENCY_DEFINITION_POLICY;
		if (isCoveredByUniversalArtifactKindOutput() && aArtifactKind.hasUniversalOutput()) return false;
		return !aArtifactKind.hasUniversalOutput();
	}

	/**
	 * Creates for the Builtin output class the local identification
	 * used for the builtin output in the artifact output definition.
	 * @param aArtifactKind kind of artifact to which the output belongs
	 * @return the local identification belonging to the given builtin output class
	 *    in the context of the given artifact kind. Returns null if the output is not possible
	 *    to generate for the given artifact kind (so if {@link #isPossibleToGenerateForArtifactKind(AInArtifactKind)}
	 *    returns false for the same artifact kind like passed into this method).
	 */
	public AIcArtifactOutputLocalKey toOutputLocalIdentification(AInArtifactKind aArtifactKind) {
		if (!isPossibleToGenerateForArtifactKind(aArtifactKind)) return null;
		if (this == DEFAULT_MAIN) return new AIcArtifactOutputLocalKey(
				null, aArtifactKind.getMainOutputTypeClass().getAssignedOutputPackagingId());
		return new AIcArtifactOutputLocalKey(
				getClassifier(), getPackaging().getAssignedOutputPackagingId());
	}

	/**
	 * @return the coveredByUniversalArtifactKindOutput
	 */
	public boolean isCoveredByUniversalArtifactKindOutput() {
		return coveredByUniversalArtifactKindOutput;
	}

	/**
	 * @return the classifier
	 */
	public String getClassifier() {
		return classifier;
	}

	/**
	 * @return the packaging
	 */
	public AInArtifactOutputPackagingClass getPackaging() {
		return packaging;
	}
}
