package eu.algites.pltf.knitstro.structure.version.component;

import java.util.Objects;

/**
 * Default immutable implementation of {@link AIiVersionComponent}.
 *
 * A component is an enum-like item identified by {@link #code()}.
 * This implementation is deliberately minimal and focuses on:
 * - code validation,
 * - value type hint,
 * - precedence role hint.
 */
public final class AIcVersionComponent implements AIiVersionComponent {

	private final String code;
	private final AInVersionComponentValueType valueType;
	private final AInVersionComponentPrecedenceRole precedenceRole;

	/**
	 * Creates a new component.
	 *
	 * @param aCode component code (used in placeholders as {@code ${code}})
	 * @param aValueType value type hint
	 * @param aPrecedenceRole precedence role hint
	 */
	public AIcVersionComponent(
			final String aCode,
			final AInVersionComponentValueType aValueType,
			final AInVersionComponentPrecedenceRole aPrecedenceRole) {

		String locCode = Objects.requireNonNull(aCode, "aCode").trim();
		if (locCode.isEmpty()) {
			throw new IllegalArgumentException("Component code must not be blank.");
		}
		if (!COMPONENT_CODE_PATTERN.matcher("${" + locCode + "}").matches()) {
			throw new IllegalArgumentException(
					"Component code contains illegal characters. code=" + locCode
			);
		}

		code = locCode;
		valueType = Objects.requireNonNull(aValueType, "aValueType");
		precedenceRole = Objects.requireNonNull(aPrecedenceRole, "aPrecedenceRole");
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public AInVersionComponentValueType valueType() {
		return valueType;
	}

	@Override
	public AInVersionComponentPrecedenceRole precedenceRole() {
		return precedenceRole;
	}

}
