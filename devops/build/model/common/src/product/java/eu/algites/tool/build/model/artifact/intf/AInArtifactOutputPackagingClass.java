package eu.algites.tool.build.model.artifact.intf;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Title: {@link AInArtifactOutputPackagingClass}
 * </p>
 * <p>
 * Description: type class of the artifact
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 11.01.26 5:09
 */
public enum AInArtifactOutputPackagingClass {
	POM("pom", true),
	JAR("jar", true),
	CUSTOM("custom", false),
	ANY("any", false),
	;

	private static Set<AInArtifactOutputPackagingClass> knownOutputTypes = null;

	/**
	 * List of the known and direct supported output type classes
	 * @return the set of the known output types
	 */
	public static Set<AInArtifactOutputPackagingClass> getKnownOutputTypeClasses() {
		if (knownOutputTypes == null)
			knownOutputTypes = Stream.of(values())
					.filter(AInArtifactOutputPackagingClass::isAssignedPackagingId)
					.collect(Collectors.toUnmodifiableSet());
		return knownOutputTypes;
	}

	/**
	 * Returns the default packaging class for the parent artifact.
	 * @return {@link #POM} as the default for parent packaging
	 */
	public static AInArtifactOutputPackagingClass getDefaultParentPackagingClass() {
		return POM;
	}

	/**
	 * Returns the default packaging class for the dependency artifacts.
	 * @return {@link #JAR} as the default for dependency packaging
	 */
	public static AInArtifactOutputPackagingClass getDefaultDependencyPackagingClass() {
		return JAR;
	}

	private final String outputPackagingCode;
	private final boolean assignedPackagingId;

	AInArtifactOutputPackagingClass(final String aOutputPackagingCode, final boolean aAssignedPackagingId) {
		outputPackagingCode = aOutputPackagingCode;
		assignedPackagingId = aAssignedPackagingId;
	}

	/**
	 * @return the code
	 */
	public String getOutputPackagingCode() {
		return outputPackagingCode;
	}

	/**
	 * Returns the default type identifier for this type class.
	 * in the case of NOT({@link #isAssignedPackagingId()}), returns null,
	 * otherwise the output of {@link #getOutputPackagingCode()}.
	 * @return the default type identifier
	 */
	public String getAssignedOutputPackagingId() {
		if (isAssignedPackagingId()) return outputPackagingCode;
		return null;
	}

	/**
	 * @return the specificPackaging
	 */
	public boolean isAssignedPackagingId() {
		return assignedPackagingId;
	}
}
