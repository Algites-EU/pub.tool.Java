package eu.algites.pltf.knitstro.structure.version.component;

import eu.algites.lib.common.enums.AIiEnumItem;

import java.util.regex.Pattern;

/**
 * A semantic version component definition.
 *
 * A component is an enum-like item identified by {@link #code()}.
 * Components may be reused across structures (e.g., year, month, lane, revision, qualifier, buildCounter).
 *
 * Components define:
 * - value type,
 * - precedence role,
 * - increment policy (optional).
 */
public interface AIiVersionComponent extends AIiEnumItem {

	/**
	 * Placeholder syntax: ${componentCode}
	 * <p>
	 * Allowed characters in componentCode here are: A-Z a-z 0-9 _ . -
	 * If we later want stricter rules, we can tighten this regex.
	 * </p>
	 */
	Pattern COMPONENT_CODE_PATTERN = Pattern.compile("\\$\\{([A-Za-z0-9_.-]+)\\}");

	/**
	 * The code of the component must fulfill the pattern given by {@link #COMPONENT_CODE_PATTERN}.
	 * @see AIiEnumItem#code()
	 */
	@Override
	String code();

	/**
	 * Gets the value type of the component.
	 *
	 * @return value type
	 */
	AInVersionComponentValueType valueType();

	/**
	 * Gets the precedence role of the component.
	 *
	 * @return precedence role
	 */
	AInVersionComponentPrecedenceRole precedenceRole();

}
