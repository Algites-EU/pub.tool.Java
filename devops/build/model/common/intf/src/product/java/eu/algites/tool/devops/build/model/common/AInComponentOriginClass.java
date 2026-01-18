package eu.algites.tool.devops.build.model.common;

/**
 * <p>
 * Title: {@link AInComponentOriginClass}
 * </p>
 * <p>
 * Description: Definition of the origin of the given data - builtin or custom.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 13.01.26 17:00
 */
public enum AInComponentOriginClass {
	BUILTIN("builtin"),
	CUSTOM("custom");

	private final String kindCode;

	AInComponentOriginClass(String aKindCode) {
		kindCode = aKindCode;
	}

	/**
	 * @return kind class code used in UIDs ({@code builtin} or {@code custom})
	 */
	public String getCode() {
		return kindCode;
	}

	/**
	 * Parse kind class from an output type UID segment.
	 *
	 * @param aKindCode kind class code
	 * @return parsed enum value
	 * @throws IllegalArgumentException if the code is unknown
	 */
	public static AInComponentOriginClass getByCodeOrThrow(String aKindCode) {
		if (BUILTIN.kindCode.equals(aKindCode)) {
			return BUILTIN;
		}
		if (CUSTOM.kindCode.equals(aKindCode)) {
			return CUSTOM;
		}
		throw new IllegalArgumentException("Unsupported originClass: '" + aKindCode + "'");
	}
}
