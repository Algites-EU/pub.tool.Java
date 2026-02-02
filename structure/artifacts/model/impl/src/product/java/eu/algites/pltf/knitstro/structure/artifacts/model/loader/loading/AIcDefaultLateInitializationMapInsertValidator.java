package eu.algites.pltf.knitstro.structure.artifacts.model.loader.loading;

/**
 * Default validator: disallows re-initialization (setting the value twice for the same key).
 *
 * @param <K> key type
 * @param <V> value type
 */
final class AIcDefaultLateInitializationMapInsertValidator<K, V>
		implements AIiLateInitializationMapInsertValidator<K, V> {

	@Override
	public boolean validateInsert(
			final AIcLateInitializationMap<K, V> aMap,
			final K aKey,
			final AIcLateInitializationSlot<V> aSlot,
			final V aNewValue
	) {
		if (aSlot.isInitialized()) {
			throw new AIxLateInitializationMapException(
					"Value already initialized for key: " + aKey
			);
		}
		return true;
	}
}
