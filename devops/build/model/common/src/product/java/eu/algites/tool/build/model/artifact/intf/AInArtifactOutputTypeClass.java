package eu.algites.tool.build.model.artifact.intf;

/**
 * <p>
 * Title: {@link AInArtifactOutputTypeClass}
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
 * @date 13.01.26 17:00
 */
public enum AInArtifactOutputTypeClass {
	BUILTIN("builtin"),
	CUSTOM("custom");

	private final String kindCode;

	AInArtifactOutputTypeClass(String aKindCode) {
		kindCode = aKindCode;
	}

	/**
	 * @return kind class code used in UIDs ({@code builtin} or {@code custom})
	 */
	public String getKindCode() {
		return kindCode;
	}

	/**
	 * Parse kind class from an output type UID segment.
	 *
	 * @param aKindCode kind class code
	 * @return parsed enum value
	 * @throws IllegalArgumentException if the code is unknown
	 */
	public static AInArtifactOutputTypeClass fromKindCode(String aKindCode) {
		if (BUILTIN.kindCode.equals(aKindCode)) {
			return BUILTIN;
		}
		if (CUSTOM.kindCode.equals(aKindCode)) {
			return CUSTOM;
		}
		throw new IllegalArgumentException("Unsupported kindClass: '" + aKindCode + "'");
	}
}
