package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

import java.util.Optional;

/**
 * <p>
 * Title: {@link AIiRuntimeReference}
 * </p>
 * <p>
 * Description: General interface for the runtime references allowing the access
 *   to some values which are not yet defined.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 17.01.26 21:49
 */
public interface AIiRuntimeReference<K, V> {
	/**
	 * @return key used by this runtime reference
	 */
	K getKey();

	/**
	 * Resolves the value from the bound map by key or throws if missing/uninitialized.
	 *
	 * @return resolved value
	 */
	V getValue();

	/**
	 * Checks whether the value is defined.
	 * @return true if value is defined, false otherwise
	 */
	boolean isValueDefined();

	/**
	 * Attempts to resolve the value from the bound map by key.
	 *
	 * @return optional resolved value
	 */
	Optional<V> findValue();
}
