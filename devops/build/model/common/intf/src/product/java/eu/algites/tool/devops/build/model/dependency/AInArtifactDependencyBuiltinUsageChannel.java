package eu.algites.tool.devops.build.model.dependency;

import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Title: {@link AInArtifactDependencyBuiltinUsageChannel}
 * </p>
 * <p>
 * Description: Contains the definition of the usage channel for the dependencies
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
public enum AInArtifactDependencyBuiltinUsageChannel {
	SOURCE_PROCESSOR("builtin::source-processor"),
	COMPILATION("builtin::compilation"),
	COMPILATION_POSTPROCESSING("builtin::compilation-postprocessing"),
	RUNTIME("builtin::runtime"),
  ;
	private final String code;

	AInArtifactDependencyBuiltinUsageChannel(final String aCode) {
		code = aCode;
	}

	/**
	 * @return the code
	 */
	@JsonValue
	public String getCode() {
		return code;
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value
	 * @throws IllegalArgumentException if the code is not null but unknown
	 */
	@JsonCreator
	public static AInArtifactDependencyBuiltinUsageChannel getByCodeOrThrow(final String aCode) throws IllegalArgumentException {
		final AInArtifactDependencyBuiltinUsageChannel value = findByCode(aCode);
		if (value != null)
			return value;
		throw new IllegalArgumentException("Unknown usage: " + aCode);
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value or null if not found
	 */
	public static @Nullable AInArtifactDependencyBuiltinUsageChannel findByCode(final String aCode) {
		for (AInArtifactDependencyBuiltinUsageChannel locValue : values()) {
			if (locValue.getCode().equalsIgnoreCase(aCode)) {
				return locValue;
			}
		}
		return null;
	}
}
