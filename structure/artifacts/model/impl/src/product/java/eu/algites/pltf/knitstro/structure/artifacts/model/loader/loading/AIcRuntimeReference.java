package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

import java.util.Objects;
import java.util.Optional;

/**
 * A runtime reference to a value stored in {@link AIcLateInitializationMap}, resolved by key.
 *
 * @param <K> key type
 * @param <V> value type
 */
final class AIcRuntimeReference<K, V> implements AIiRuntimeReference<K, V> {

	private final AIcLateInitializationMap<K, V> locMap;
	private final K locKey;

	/**
	 * @param aMap map holding the values
	 * @param aKey key used for resolution
	 */
	public AIcRuntimeReference(final AIcLateInitializationMap<K, V> aMap, final K aKey) {
		locMap = Objects.requireNonNull(aMap, "aMap must not be null");
		locKey = Objects.requireNonNull(aKey, "aKey must not be null");
	}

	@Override
	public K getKey() {
		return locKey;
	}

	@Override
	public V getValue() {
		return locMap.getValueOrThrow(locKey);
	}

	@Override
	public boolean isValueDefined() {
		return locMap.isInitialized(locKey);
	}

	@Override
	public Optional<V> findValue() {
		return locMap.findValue(locKey);
	}
}
