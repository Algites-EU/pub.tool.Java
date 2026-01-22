package eu.algites.tool.devops.build.model.dependency;

import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Title: {@link AInArtifactDependencyBuiltinSourceSet}
 * </p>
 * <p>
 * Description: Contains the definition of the source sets
 *    on which the operations like dependencies etc. are being applied.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 11.01.26 7:47
 */
public enum AInArtifactDependencyBuiltinSourceSet {
	MAIN_ONLY("builtin::mainOnly", true, false),
	TEST_ONLY("builtin::testOnly", false, true),
	MAIN_AND_TEST("builtin::mainAndTest", true, true);

	private final String code;
	private final boolean mainIncluded;

	private final boolean testIncluded;

	AInArtifactDependencyBuiltinSourceSet(final String aCode, final boolean aMainIncluded, boolean aTestIncluded) {
		code = aCode;
		mainIncluded = aMainIncluded;
		testIncluded = aTestIncluded;
	}

	/**
	 * @return the code
	 */
	@JsonValue
	public String getCode() {
		return code;
	}

	/**
	 * @return the mainIncluded
	 */
	public boolean isMainIncluded() {
		return mainIncluded;
	}

	/**
	 * @return the testIncluded
	 */
	public boolean isTestIncluded() {
		return testIncluded;
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value
	 * @throws IllegalArgumentException if the code is not null but unknown
	 */
	@JsonCreator
	public static AInArtifactDependencyBuiltinSourceSet getByCodeOrThrow(final String aCode) throws IllegalArgumentException {
		final AInArtifactDependencyBuiltinSourceSet value = findByCode(aCode);
		if (value != null)
			return value;
		throw new IllegalArgumentException("Unknown sourceSet: " + aCode);
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value or null if not found
	 */
	public static @Nullable AInArtifactDependencyBuiltinSourceSet findByCode(final String aCode) {
		for (AInArtifactDependencyBuiltinSourceSet locValue : values()) {
			if (locValue.getCode().equalsIgnoreCase(aCode)) {
				return locValue;
			}
		}
		return null;
	}
}
