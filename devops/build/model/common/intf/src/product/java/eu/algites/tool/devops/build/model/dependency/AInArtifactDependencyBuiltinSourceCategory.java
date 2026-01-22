package eu.algites.tool.devops.build.model.dependency;

import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Title: {@link AInArtifactDependencyBuiltinSourceCategory}
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
public enum AInArtifactDependencyBuiltinSourceCategory implements AIiArtifactDependencyScopeSourceCategory {
	MAIN("builtin::main", true, false),
	TEST("builtin::test", false, true),
	;

	private final String code;
	private final boolean main;

	private final boolean test;

	AInArtifactDependencyBuiltinSourceCategory(final String aCode, final boolean aMain, boolean aTest) {
		code = aCode;
		main = aMain;
		test = aTest;
	}

	/**
	 * @return the code
	 */
	@JsonValue
	@Override
	public String code() {
		return code;
	}

	/**
	 * @return the mainIncluded
	 */
	public boolean isMain() {
		return main;
	}

	/**
	 * @return the testIncluded
	 */
	public boolean isTest() {
		return test;
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value
	 * @throws IllegalArgumentException if the code is not null but unknown
	 */
	@JsonCreator
	public static AInArtifactDependencyBuiltinSourceCategory getByCodeOrThrow(final String aCode) throws IllegalArgumentException {
		final AInArtifactDependencyBuiltinSourceCategory value = findByCode(aCode);
		if (value != null)
			return value;
		throw new IllegalArgumentException("Unknown sourceSet: " + aCode);
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value or null if not found
	 */
	public static @Nullable AInArtifactDependencyBuiltinSourceCategory findByCode(final String aCode) {
		for (AInArtifactDependencyBuiltinSourceCategory locValue : values()) {
			if (locValue.getCode().equalsIgnoreCase(aCode)) {
				return locValue;
			}
		}
		return null;
	}
}
