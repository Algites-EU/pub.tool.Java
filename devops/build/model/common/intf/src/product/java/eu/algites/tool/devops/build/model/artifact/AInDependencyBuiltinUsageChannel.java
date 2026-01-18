package eu.algites.tool.devops.build.model.artifact;

import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * Title: {@link AInDependencyBuiltinUsageChannel}
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
public enum AInDependencyBuiltinUsageChannel {
	CLASSPATH_ITEM("builtin::classpathItem"),
	SOURCE_PROCESSOR("builtin::sourceProcessor"),
  ;
	private final String code;

	AInDependencyBuiltinUsageChannel(final String aCode) {
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
	public static AInDependencyBuiltinUsageChannel getByCodeOrThrow(final String aCode) throws IllegalArgumentException {
		final AInDependencyBuiltinUsageChannel value = findByCode(aCode);
		if (value != null)
			return value;
		throw new IllegalArgumentException("Unknown usage: " + aCode);
	}

	/**
	 * @param aCode code to be searched for
	 * @return the enum value or null if not found
	 */
	public static @Nullable AInDependencyBuiltinUsageChannel findByCode(final String aCode) {
		for (AInDependencyBuiltinUsageChannel locValue : values()) {
			if (locValue.getCode().equalsIgnoreCase(aCode)) {
				return locValue;
			}
		}
		return null;
	}
}
