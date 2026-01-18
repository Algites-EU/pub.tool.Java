package eu.algites.tool.devops.build.model.common.version;

import jakarta.annotation.Nonnull;

import java.util.Objects;

import org.jetbrains.annotations.Nullable;

/**
 * <p>
 * Title: {@link AInVersionQualifierKind}
 * </p>
 * <p>
 * Description: Kind of the version of the artifact
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 08.01.26 0:44
 */
public enum AInVersionQualifierKind {
	SNAPSHOT("SNAPSHOT"),
	PRE_RELEASE("PRE_RELEASE"),
	RELEASE("RELEASE"),
	POST_RELEASE("POST_RELEASE"),
	;

	private final String kindCode;

	AInVersionQualifierKind(final String aKindCode) {
		kindCode = aKindCode;
	}

	/**
	 * Get kind by code or throw exception
	 * @param aKindCode the kind code, MUST be not null
	 * @return the kind
	 * @throws IllegalArgumentException if the code is unknown
	 */
	public static AInVersionQualifierKind getByCodeOrThrow(@Nonnull String aKindCode) throws IllegalArgumentException {
		final AInVersionQualifierKind kind = findByCode(aKindCode);
		if (kind != null)
			return kind;
		throw new IllegalArgumentException("Unsupported kind: '" + aKindCode + "'");
	}

	/**
	 * Find kind by code
	 * @param aKindCode the kind code
	 * @return the kind or null if not found
	 */
	public static @Nullable AInVersionQualifierKind findByCode(@Nonnull final String aKindCode) {
		Objects.requireNonNull(aKindCode, "Kind code MUST NOT be null");
		for (AInVersionQualifierKind kind : values()) {
			if (kind.getCode().equals(aKindCode)) {
				return kind;
			}
		}
		;
		return null;
	}

	/**
	 * @return the kind Code
	 */
	public String getCode() {
		return kindCode;
	}
}
